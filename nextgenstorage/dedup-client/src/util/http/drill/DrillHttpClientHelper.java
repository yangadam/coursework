package util.http.drill;

import java.nio.channels.UnsupportedAddressTypeException;

import util.http.base.HttpClientHelper;


public class DrillHttpClientHelper extends HttpClientHelper {

	public DrillHttpClientHelper(DrillHttpAddress drillHttpAddress) {
		super(drillHttpAddress);
	}

	public DrillHttpClientHelper createDrillURL(String... params) {
		if(httpAddress instanceof DrillHttpAddress) {
			DrillHttpAddress drillHttpAddress = (DrillHttpAddress)httpAddress;
			drillHttpAddress.createDrillURL(params);
			return this;
		}
		throw new UnsupportedAddressTypeException();
	}
}
