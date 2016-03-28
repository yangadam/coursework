package util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.common.base.Preconditions;

public abstract class HttpClientHelper {
	static String HTTP_DEFAULT_PROTOCOL  = "http";

	protected HttpClient client;
	protected HttpAddress httpAddress;

	public HttpClientHelper(HttpAddress httpAddress) {
		client = HttpClientBuilder.create().build();
		this.httpAddress = httpAddress;
	}

	public static enum METHOD {
		GET, PUT, POST, DELETE
	}

	/**
	 * Get HTTP result's first header.
	 * @param method
	 * @param headerName
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Header getHttpResultFirstHeader(METHOD method, String headerName, HttpEntity entity) throws ClientProtocolException, IOException {
		Preconditions.checkArgument(headerName != null && !headerName.equals(""), "Header name can't be null/empty");
		return getHttpResponse(method, entity).getFirstHeader(headerName);
	}

	/**
	 * Get HTTP result's first header.
	 * @param method
	 * @param headerName
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Header getHttpResultFirstHeader(METHOD method, String headerName) throws ClientProtocolException, IOException {
		return getHttpResultFirstHeader(method, headerName, null);
	}


	/**
	 * Get HTTP result's status.
	 * @param method
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public StatusLine getHttpResultStatus(METHOD method, HttpEntity entity) throws ClientProtocolException, IOException {
		return getHttpResponse(method, entity).getStatusLine();
	}

	/**
	 * Get HTTP result's status.
	 * @param method
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public StatusLine getHttpResultStatus(METHOD method) throws ClientProtocolException, IOException {
		return getHttpResultStatus(method, null);
	}


	/**
	 * Get HTTP result's status code.
	 * @param method
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public int getHttpResultStatusCode(METHOD method, HttpEntity entity) throws ClientProtocolException, IOException {
		return getHttpResultStatus(method, entity).getStatusCode();
	}

	/**
	 * Get HTTP result's status code.
	 * @param method
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public int getHttpResultStatusCode(METHOD method) throws ClientProtocolException, IOException {
		return getHttpResultStatusCode(method, null);
	}


	/**
	 * Get HTTP result's content.
	 * @param method
	 * @param entity
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getHttpResultContent(METHOD method, HttpEntity entity) throws UnsupportedOperationException, IOException {
		HttpResponse response = getHttpResponse(method, entity);
		InputStream contentInputStream = response.getEntity().getContent();
		BufferedReader rd = new BufferedReader(new InputStreamReader(contentInputStream));

		StringBuilder result = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}

	/**
	 * Get HTTP result's content.
	 * @param method
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getHttpResultContent(METHOD method) throws UnsupportedOperationException, IOException {
		return getHttpResultContent(method, null);
	}

	public void setCurrentURL(String currentURL) {
		httpAddress.setCurrentURL(currentURL);
	}

	private HttpResponse getHttpResponse(METHOD method, HttpEntity entity) throws ClientProtocolException, IOException {
		if(method == METHOD.GET)
			return executeHttpGet();
		if(method == METHOD.PUT)
			return executeHttpPut(entity);
		if(method == METHOD.POST)
			return executeHttpPost(entity);
		if(method == METHOD.DELETE)
			return executeHttpDelete();
		throw new IllegalArgumentException();
	}

	private HttpResponse executeHttpGet() throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(httpAddress.getCurrentURL());
		return client.execute(httpGet);
	}

	private HttpResponse executeHttpPut(HttpEntity entity) throws ClientProtocolException, IOException {
		HttpPut httpPut = new HttpPut(httpAddress.getCurrentURL());
		httpPut.setEntity(entity);
		return client.execute(httpPut);
	}

	private HttpResponse executeHttpPost(HttpEntity entity) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(httpAddress.getCurrentURL());
		httpPost.setEntity(entity);
		return client.execute(httpPost);
	}

	private HttpResponse executeHttpDelete() throws ClientProtocolException, IOException {
		HttpDelete httpDelete = new HttpDelete(httpAddress.getCurrentURL());
		return client.execute(httpDelete);
	}
}

