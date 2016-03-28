package util.http;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Preconditions;


public class HdfsHttpAddress extends HttpAddress {

	private static final String WEBHDFS_FLAG = "/webhdfs/v1";
	private String username;

	public HdfsHttpAddress(String host, String port, String username) {
		super(host, port);
		this.username = username;
	}

	public HdfsHttpAddress(String protocol, String host, String port, String username) {
		super(protocol, host, port);
		this.username = username;
	}

	public String createHdfsURL(String path, String op, Map<String, String> options) {
		Preconditions.checkArgument(path != null && !path.equals(""), "HDFS url's path can't be null/empty");
		Preconditions.checkArgument(op != null && !op.equals(""), "HDFS url's operation can't be null/empty");

		StringBuilder url = new StringBuilder(super.getBasicAddress() + WEBHDFS_FLAG);
		appendPath(url, path);
		appendUserName(url);
		appendOperation(url, op);
		appendOptions(url, options);
		setCurrentURL(url.toString());
		return currentURL;
	}

	@Override
	public String getCurrentURL() {
		return super.currentURL;
	}

	@Override
	public void setCurrentURL(String currentURL) {
		super.currentURL = currentURL;
	}

	private void appendPath(StringBuilder url, String path) {
		url.append(path).append("?");
	}

	private void appendUserName(StringBuilder url) {
		if (username != null) {
			url.append("user.name=").append(username).append("&");
		}
	}

	private void appendOperation(StringBuilder url, String op) {
		url.append("op=").append(op);
	}

	private void appendOptions(StringBuilder url, Map<String, String> options) {
		if(options != null) {
			for(Entry<String, String> option : options.entrySet()) {
				url.append("&")
				.append(option.getKey())
				.append("=")
				.append(option.getValue());
			}
		}
	}
}
