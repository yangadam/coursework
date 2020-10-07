package util.http.drill;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import model.drill.DrillQueryReqBody;
import util.http.base.HttpClientHelper.METHOD;

public class DrillAccess {

	// https://drill.apache.org/docs/rest-api/

	private DrillHttpClientHelper drillHttpClientHelper;

	public DrillAccess(DrillHttpAddress drillHttpAddress) {
		this.drillHttpClientHelper = new DrillHttpClientHelper(drillHttpAddress);
	}

	public DrillAccess(String host, String port) {
		this.drillHttpClientHelper = new DrillHttpClientHelper(new DrillHttpAddress(host, port));
	}

	public DrillAccess(String protocol, String host, String port) {
		this.drillHttpClientHelper = new DrillHttpClientHelper(new DrillHttpAddress(protocol, host, port));
	}

	// Query: Using the query method, you can programmatically run queries.

	/**
	 * [POST] Submit a query and return results.
	 * @param queryRequestBody
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String executeQuery(DrillQueryReqBody queryRequestBody) throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("query.json")
				.getHttpResultContent(METHOD.POST, queryRequestBody.getHttpEntity());
	}

	/**
	 * Submit a query and return results.
	 * @param query
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String executeQuery(String query) throws UnsupportedOperationException, IOException {
		DrillQueryReqBody queryRequestBody = new DrillQueryReqBody(query);
		return executeQuery(queryRequestBody);
	}

	// Profiles: These methods get query profiles.

	/**
	 * [GET] Get all profiles.
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getProfiles() throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("profiles.json")
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * [GET] Get profile by query id.
	 * @param queryid
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getProfile(String queryid) throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("profiles", queryid + ".json")
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * [GET] Cancel query by query id.
	 * @param queryid
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String cancelQuery(String queryid) throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("profiles", "cancel", queryid)
				.getHttpResultContent(METHOD.GET);
	}

	// Storage: The storage REST methods manage Drill storage plugin configurations.

	/**
	 * [GET] Get the list of storage plugin names and configurations.
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getStorages () throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("storage.json")
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * [GET] Get the definition of the named storage plugin.
	 * @param name
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getStorage(String name) throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("storage", name + ".json")
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * [GET] Enable the named storage plugin.
	 * @param name
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String enableStorage(String name) throws UnsupportedOperationException, IOException {
		return setStorageStatus(name, true);
	}

	/**
	 * [GET] Disable the named storage plugin.
	 * @param name
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String disableStorage(String name) throws UnsupportedOperationException, IOException {
		return setStorageStatus(name, false);
	}

	private String setStorageStatus(String name, boolean isEnable) throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("storage", name, "enable", String.valueOf(isEnable))
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * [POST] Update a storage plugin configuration.
	 * @param name
	 * @param requestBody
	 * @return
	 * @throws UnsupportedCharsetException
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String updateStorage(String name, String requestBody) throws UnsupportedCharsetException, UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("storage", name + ".json")
				.getHttpResultContent(METHOD.POST, new StringEntity(requestBody, ContentType.APPLICATION_JSON));
	}

	/**
	 * [POST] Create a storage plugin configuration.
	 * @param name
	 * @param requestBody
	 * @return
	 * @throws UnsupportedCharsetException
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String createStorage(String name, String requestBody) throws UnsupportedCharsetException, UnsupportedOperationException, IOException {
		return updateStorage(name, requestBody);
	}

	/**
	 * [DELETE] Delete a storage plugin configuration.
	 * @param name
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String deleteStorage(String name) throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("storage", name + ".json")
				.getHttpResultContent(METHOD.DELETE);
	}

	// Metrics: Gets metric information.

	/**
	 * [GET] Get Drillbit information, such as ports numbers.
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getDrillbitInfos() throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("stats.json")
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * [GET] Get the status of Drill.
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getDrillXmlInfos() throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("status")
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * [GET] Get the current memory metrics.
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getCurrentMemoryMetrics() throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("status", "metrics")
				.getHttpResultContent(METHOD.GET);
	}

	// Threads: Get information about threads.

	/**
	 * [GET] Get the status of threads.
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getThredsStatus() throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("status", "threads")
				.getHttpResultContent(METHOD.GET);
	}

	// Options: This method gets and sets system options.

	/**
	 *
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getOptions() throws UnsupportedOperationException, IOException {
		return drillHttpClientHelper
				.createDrillURL("options.json")
				.getHttpResultContent(METHOD.GET);
	}

	public static void main(String[] args) {
		DrillAccess drillAccess = new DrillAccess("sd-6286-1278", "8047");

		try {
			//System.out.println(drillAccess.getProfiles());
//			System.out.println(drillAccess.getProfile("29170e86-4bc8-c73e-e3fd-3568ba50a32e"));
//			System.out.println(drillAccess.cancelQuery("061ac3-0222-58da-2339-98b424494d75"));
//			System.out.println(drillAccess.getStorages());
			System.out.println(drillAccess.getStorage("hive"));
//			System.out.println(drillAccess.disableStorage("hive"));
//			System.out.println(drillAccess.enableStorage("hive"));
//			System.out.println(drillAccess.getDrillbitInfos());
//			System.out.println(drillAccess.getDrillXmlInfos());
//			System.err.println(drillAccess.getCurrentMemoryMetrics());
//			System.out.println(drillAccess.getThredsStatus());
//			System.out.println(drillAccess.getOptions());

//			String hiveStoragerequestBody = "{\"name\" : \"hive\",\"config\" : {\"type\": \"hive\",\"enabled\": false,\"configProps\": {\"hive.metastore.uris\": \"thrift://169.172.134.169:9083\",\"javax.jdo.option.ConnectionURL\": \"jdbc:oracle:thin:@//vm-f221-fe40.nam.nsroot.net:1528/PRIMEDB\",\"hive.metastore.warehouse.dir\": \"/user/hive/warehouse\",\"fs.default.name\": \"hdfs://gft.hadoop.master:9000/\",\"hive.metastore.sasl.enabled\": \"false\"}}}";
//			System.out.println(drillAccess.updateStorage("hive", hiveStoragerequestBody));

//			System.out.println(drillAccess.deleteStorage("hive"));

//			String hiveStoragerequestBody = "{\"name\" : \"hive\",\"config\" : {\"type\": \"hive\",\"enabled\": true,\"configProps\": {\"hive.metastore.uris\": \"thrift://169.172.134.169:9083\",\"javax.jdo.option.ConnectionURL\": \"jdbc:oracle:thin:@//vm-f221-fe40.nam.nsroot.net:1528/PRIMEDB\",\"hive.metastore.warehouse.dir\": \"/user/hive/warehouse\",\"fs.default.name\": \"hdfs://gft.hadoop.master:9000/\",\"hive.metastore.sasl.enabled\": \"false\"}}}";
//			System.out.println(drillAccess.createStorage("hive", hiveStoragerequestBody));

//			System.out.println(drillAccess.executeQuery("SELECT * FROM hive.refdata1.dsmt_goc limit 10"));

		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

