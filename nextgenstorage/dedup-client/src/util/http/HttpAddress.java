package util.http;

public abstract class HttpAddress {
	final static String HTTP_DEFAULT_PROTOCOL = "http";
	protected String protocol;
	protected String host;
	protected String port;
	protected String currentURL;

	public HttpAddress(String protocol, String host, String port) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
	}

	public HttpAddress(String host, String port) {
		this(HTTP_DEFAULT_PROTOCOL, host, port);
	}

	public String getBasicAddress() {
		return protocol + "://" + host + ":" + port;
	}

	public abstract String getCurrentURL();

	public abstract void setCurrentURL(String currentURL);
}
