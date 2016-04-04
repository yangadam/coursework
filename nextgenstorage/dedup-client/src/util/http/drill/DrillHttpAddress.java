package util.http.drill;

import com.google.common.base.Preconditions;

import util.http.base.HttpAddress;

public class DrillHttpAddress extends HttpAddress {

	public DrillHttpAddress(String host, String port) {
		super(host, port);
	}

	public DrillHttpAddress(String protocol, String host, String port) {
		super(protocol, host, port);
	}

	public String createDrillURL(String... params) {
		Preconditions.checkArgument(params != null && params.length > 0, "Parameter can't be null/empty.");

		StringBuilder url = new StringBuilder();
		url.append(super.getBasicAddress());

		for (String param : params) {
			url.append("/").append(param);
		}
		setCurrentURL(url.toString());
		return currentURL;
	}
}