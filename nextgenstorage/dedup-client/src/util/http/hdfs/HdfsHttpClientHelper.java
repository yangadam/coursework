package util.http.hdfs;

import java.util.Map;

import util.http.base.HttpClientHelper;

public class HdfsHttpClientHelper extends HttpClientHelper {

	public HdfsHttpClientHelper(HdfsHttpAddress hdfsHttpAddress) {
		super(hdfsHttpAddress);
	}

	public HdfsHttpClientHelper createHdfsURL(String path, String op, Map<String, String> options) {
		HdfsHttpAddress hdfsHttpAddress = (HdfsHttpAddress)httpAddress;

		String url = hdfsHttpAddress.createHdfsURL(path, op, options);

		System.out.println(url);

		return this;
	}
}
