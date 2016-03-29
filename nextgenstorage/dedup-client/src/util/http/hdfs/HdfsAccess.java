package util.http.hdfs;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.InputStreamEntity;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import model.ContentSummary;
import model.FileChecksum;
import model.FileStatus;
import model.FileStatuses;
import util.http.base.HttpClientHelper.METHOD;

public class HdfsAccess {
	// Load properties from file.
	private static HdfsAccess INSTANCE = null;

	public static HdfsAccess getInstance() {
		if(INSTANCE == null) {
			synchronized (HdfsAccess.class) {
				if (INSTANCE == null) {
					INSTANCE = new HdfsAccess("139.129.17.212", "50070", null);
				}
			}
		}
		return INSTANCE;
	}

	private HdfsHttpClientHelper hdfsHttpClientHelper;

	public HdfsAccess(HdfsHttpAddress hdfsHttpAddress) {
		this.hdfsHttpClientHelper = new HdfsHttpClientHelper(hdfsHttpAddress);
	}

	public HdfsAccess(String host, String port, String username) {
		this.hdfsHttpClientHelper = new HdfsHttpClientHelper(new HdfsHttpAddress(host, port, username));
	}

	public HdfsAccess(String protocol, String host, String port, String username) {
		this.hdfsHttpClientHelper = new HdfsHttpClientHelper(new HdfsHttpAddress(protocol, host, port, username));
	}

	private abstract class OPERATIONS {
		public static final String CREATE = "CREATE";
		public static final String APPEND = "APPEND";
		public static final String OPEN   = "OPEN";
		public static final String MKDIRS = "MKDIRS";
		public static final String RENAME = "RENAME";
		public static final String GETFILESTATUS = "GETFILESTATUS";
		public static final String LISTSTATUS = "LISTSTATUS";
		public static final String GETCONTENTSUMMARY = "GETCONTENTSUMMARY";
		public static final String GETFILECHECKSUM = "GETFILECHECKSUM";
		public static final String GETHOMEDIRECTORY = "GETHOMEDIRECTORY";
		public static final String SETPERMISSION = "SETPERMISSION";
		public static final String SETOWNER = "SETOWNER";
		public static final String SETREPLICATION = "SETREPLICATION";
		public static final String GETDELEGATIONTOKEN = "GETDELEGATIONTOKEN";
		public static final String RENEWDELEGATIONTOKEN = "RENEWDELEGATIONTOKEN";
		public static final String CANCELDELEGATIONTOKEN = "CANCELDELEGATIONTOKEN";
		public static final String SETTIMES = "SETTIMES";
		public static final String DELETE = "DELETE";
	}

	private abstract class STATUS {
		public static final int CREATED = 201;
		public static final int OK = 200;
	}

	private abstract class LITERAL {
		public static final String TRUE = "true";
		public static final String FILESTATUS = "FileStatus";
		public static final String FILESTATUSES = "FileStatuses";
		public static final String CONTENTSUMMARY = "ContentSummary";
		public static final String FILECHECKSUM = "FileChecksum";
		public static final String HOMEDIR = "/";
		public static final String LOCATION = "Location";

		private abstract class ERROR {
			public static final String ERROR01 = "Local absolute file path could not be a directory.";
		}
	}


	public abstract class OPTIONS {
		public static final String DESTINATION = "destination";
		public static final String PERMISSION = "permission";
		public static final String OWNER = "owner";
		public static final String GROUP = "group";
		public static final String REPLICATION = "replication";
		public static final String RENEWER = "renewer";
		public static final String TOKEN = "token";
		public static final String MODIFICATIONTIME = "modificationtime";
		public static final String ACCESSTIME = "accesstime";
		public static final String RECURSIVE = "recursive";
	}

	/**
	 * [PUT] Create and Write to a File.
	 * @param localPath
	 * @param remotePath
	 * @param options
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public boolean upload(String localPath, String remotePath, Map<String, String> options)
			throws ClientProtocolException, IOException, IllegalArgumentException {
		return uploadFile(OPERATIONS.CREATE, localPath, remotePath, METHOD.PUT, options) == STATUS.CREATED;
	}

	/**
	 * [POST] Append to a File.
	 * @param localPath
	 * @param remotePath
	 * @param options
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean append(String localPath, String remotePath, Map<String, String> options)
			throws ClientProtocolException, IOException {
		return uploadFile(OPERATIONS.APPEND, localPath, remotePath, METHOD.POST, options) == STATUS.OK;
	}

	/**
	 * [GET] Open and Read a File.
	 * @param remotePath
	 * @param options
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String open(String remotePath, Map<String, String> options)
			throws UnsupportedOperationException, IOException {
		return hdfsHttpClientHelper
				.createHdfsURL(remotePath, OPERATIONS.OPEN, options)
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * [PUT] Make a Directory.
	 * @param remotePath
	 * @param options
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean mkdir(String remotePath, Map<String, String> options)
			throws ClientProtocolException, IOException {
		return hdfsHttpClientHelper
				.createHdfsURL(remotePath, OPERATIONS.MKDIRS, options)
				.getHttpResultContent(METHOD.PUT)
				.contains(LITERAL.TRUE);
	}

	/**
	 * [PUT] Rename a File/Directory.
	 * @param remotePath
	 * @param destination
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean rename(String remotePath, String destination)
			throws ClientProtocolException, IOException {
		Map<String, String> options = new HashMap<>();
		options.put(OPTIONS.DESTINATION, destination);
		String content =  hdfsHttpClientHelper
				.createHdfsURL(remotePath, OPERATIONS.RENAME, options)
				.getHttpResultContent(METHOD.PUT);
		return content.contains(LITERAL.TRUE);
	}

	/**
	 * Delete a File.
	 * @param remotePath
	 * @param options
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public boolean removeFile(String remotePath)
			throws UnsupportedOperationException, IOException {
		return remove(remotePath, false);
	}

	/**
	 * Delete a Directory
	 * @param remotePath
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public boolean removeDirectory(String remotePath)
			throws UnsupportedOperationException, IOException {
		return remove(remotePath, true);
	}

	/**
	 * Get the status of a File/Directory and the client receives a response with a FileStatus JSON object.
	 * @param remotePath
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public FileStatus getStatus(String remotePath)
			throws UnsupportedOperationException, IOException {
		String jsonResult = hdfsHttpClientHelper
							 .createHdfsURL(remotePath, OPERATIONS.GETFILESTATUS, null)
				 			 .getHttpResultContent(METHOD.GET);

		if(jsonResult.contains(LITERAL.FILESTATUS)) {
			String fileStatusJsonStr = new JsonParser().parse(jsonResult)
													   .getAsJsonObject()
													   .get(LITERAL.FILESTATUS)
													   .toString();

			return new Gson().fromJson(fileStatusJsonStr, FileStatus.class);
		}
		return null;
	}

	/**
	 * List a Directory and the client receives a response with a FileStatuses JSON object.
	 * @param remotePath
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public FileStatuses listDirectory(String remotePath)
			throws UnsupportedOperationException, IOException {
		String jsonResult = hdfsHttpClientHelper
							 .createHdfsURL(remotePath, OPERATIONS.LISTSTATUS, null)
							 .getHttpResultContent(METHOD.GET);

		if(jsonResult.contains(LITERAL.FILESTATUSES)) {
			String fileStatusesJsonStr = new JsonParser().parse(jsonResult)
											.getAsJsonObject()
											.get(LITERAL.FILESTATUSES)
											.getAsJsonObject()
											.get(LITERAL.FILESTATUS)
											.toString();

			List<FileStatus> fileStatusList =
					new Gson().fromJson(fileStatusesJsonStr, new TypeToken<List<FileStatus>>() {}.getType());
			return new FileStatuses(fileStatusList);
		}
		return null;
	}

	/**
	 * Get Content Summary of a Directory and the client receives a response with a ContentSummary JSON object
	 * @param remotePath
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public ContentSummary getDirectoryContentSummary(String remotePath)
			throws UnsupportedOperationException, IOException {
		String jsonResult = hdfsHttpClientHelper
							 .createHdfsURL(remotePath, OPERATIONS.GETCONTENTSUMMARY, null)
							 .getHttpResultContent(METHOD.GET);

		if(jsonResult.contains(LITERAL.CONTENTSUMMARY)) {
			String contentSummaryJsonStr = new JsonParser().parse(jsonResult)
														   .getAsJsonObject()
														   .get(LITERAL.CONTENTSUMMARY)
														   .toString();

			return new Gson().fromJson(contentSummaryJsonStr, ContentSummary.class);
		}
		return null;
	}

	/**
	 * Get File Checksum and the client follows the redirect to the datanode and receives a FileChecksum JSON object
	 * @param remotePath
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public FileChecksum getFileChecksum(String remotePath)
			throws UnsupportedOperationException, IOException {
		String jsonResult = hdfsHttpClientHelper
							 .createHdfsURL(remotePath, OPERATIONS.GETFILECHECKSUM, null)
							 .getHttpResultContent(METHOD.GET);

		if(jsonResult.contains(LITERAL.FILECHECKSUM)) {
			String fileChecksumJsonStr = new JsonParser().parse(jsonResult)
														 .getAsJsonObject()
														 .get(LITERAL.FILECHECKSUM)
														 .toString();

			return new Gson().fromJson(fileChecksumJsonStr, FileChecksum.class);
		}
		return null;
	}

	/**
	 * Get Home Directory.
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getHomeDirectory()
			throws UnsupportedOperationException, IOException {
		return hdfsHttpClientHelper
				.createHdfsURL(LITERAL.HOMEDIR, OPERATIONS.GETHOMEDIRECTORY, null)
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * Set Permission.
	 * @param remotePath
	 * @param permission
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean setPermission(String remotePath, int permission)
			throws ClientProtocolException, IOException {
		Map<String, String> options = new HashMap<>();
		options.put(OPTIONS.PERMISSION, String.valueOf(permission));
		return hdfsHttpClientHelper
				.createHdfsURL(remotePath, OPERATIONS.SETPERMISSION, options)
				.getHttpResultStatusCode(METHOD.PUT) == STATUS.OK;
	}

	/**
	 * Set Owner.
	 * @param remotePath
	 * @param owner
	 * @param group
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean setOwner(String remotePath, String owner, String group)
			throws ClientProtocolException, IOException {
		Map<String, String> options = new HashMap<>();
		options.put(OPTIONS.OWNER, owner);
		options.put(OPTIONS.GROUP, group);
		return hdfsHttpClientHelper
				.createHdfsURL(remotePath, OPERATIONS.SETOWNER, options)
				.getHttpResultStatusCode(METHOD.PUT) == STATUS.OK;
	}

	/**
	 * Set Replication Factor.
	 * @param remotePath
	 * @param replication
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean setReplicationFactor(String remotePath, short replication)
			throws ClientProtocolException, IOException {
		Map<String, String> options = new HashMap<>();
		options.put(OPTIONS.REPLICATION, String.valueOf(replication));
		return hdfsHttpClientHelper
				.createHdfsURL(remotePath, OPERATIONS.SETREPLICATION, options)
				.getHttpResultStatusCode(METHOD.PUT) == STATUS.OK;
	}

	/**
	 * Set AccessTime.
	 * @param remotePath
	 * @param accesstime
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean setAccessTime(String remotePath, String accesstime)
			throws ClientProtocolException, IOException {
		return setTimes(remotePath, null, accesstime);
	}

	/**
	 * Set ModificationTime.
	 * @param remotePath
	 * @param modificationtime
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean setModificationTime(String remotePath, String modificationtime)
			throws ClientProtocolException, IOException {
		return setTimes(remotePath, modificationtime, null);
	}

	/**
	 * Get Delegation Token.
	 * @param remotePath
	 * @param renewer
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String getDelegationToken(String renewer)
			throws UnsupportedOperationException, IOException {
		Map<String, String> options = new HashMap<>();
		options.put(OPTIONS.RENEWER, renewer);
		return hdfsHttpClientHelper
				.createHdfsURL(LITERAL.HOMEDIR, OPERATIONS.GETDELEGATIONTOKEN, options)
				.getHttpResultContent(METHOD.GET);
	}

	/**
	 * Renew Delegation Token.
	 * @param remotePath
	 * @param token
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public String renewDelegationToken(String token)
			throws UnsupportedOperationException, IOException {
		Map<String, String> options = new HashMap<>();
		options.put(OPTIONS.TOKEN, token);
		return hdfsHttpClientHelper
				.createHdfsURL(LITERAL.HOMEDIR, OPERATIONS.RENEWDELEGATIONTOKEN, options)
				.getHttpResultContent(METHOD.PUT);
	}

	/**
	 * Cancel Delegation Token.
	 * @param token
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean cancelDelegationToken(String token)
			throws ClientProtocolException, IOException {
		Map<String, String> options = new HashMap<>();
		options.put(OPTIONS.TOKEN, token);
		return hdfsHttpClientHelper
				.createHdfsURL(LITERAL.HOMEDIR, OPERATIONS.CANCELDELEGATIONTOKEN, options)
				.getHttpResultStatusCode(METHOD.PUT) == STATUS.OK;
	}

	private boolean setTimes(String remotePath, String modificationtime, String accesstime)
			throws ClientProtocolException, IOException {
		Map<String, String> options = new HashMap<>();
		if(modificationtime != null)
			options.put(OPTIONS.MODIFICATIONTIME, modificationtime);

		if(accesstime != null)
			options.put(OPTIONS.ACCESSTIME, accesstime);

		return hdfsHttpClientHelper
				.createHdfsURL(remotePath, OPERATIONS.SETTIMES, options)
				.getHttpResultStatusCode(METHOD.PUT) == STATUS.OK;
	}

	private boolean remove(String remotePath, boolean recursive)
			throws UnsupportedOperationException, IOException {
		Map<String, String> options = new HashMap<>();
		options.put(OPTIONS.RECURSIVE, String.valueOf(recursive));

		return hdfsHttpClientHelper
				.createHdfsURL(remotePath, OPERATIONS.DELETE, options)
				.getHttpResultContent(METHOD.DELETE)
				.contains(LITERAL.TRUE);
	}

	private int uploadFile(String op, String localPath, String remotePath, METHOD method, Map<String, String> options)
			throws ClientProtocolException, IOException {
		File file = new File(localPath);
		if(file.isDirectory())
			throw new IllegalArgumentException(LITERAL.ERROR.ERROR01);


		// Step 1: Submit a HTTP PUT/POST request without automatically following redirects and without sending the file data.
		String redirectUrl = hdfsHttpClientHelper.createHdfsURL(remotePath, op, options)
				  								 .getHttpResultFirstHeader(method, LITERAL.LOCATION)
				  								 .getValue();
		hdfsHttpClientHelper.setCurrentURL(redirectUrl);

		// Step 2: Submit another HTTP PUT/POST request using the URL in the Location header with the file data to be written/appended.
		HttpEntity entity = new InputStreamEntity(new FileInputStream(file));
		return hdfsHttpClientHelper.getHttpResultStatusCode(method, entity);
	}

	public static String parsePermission(String permission) {
		StringBuilder result = new StringBuilder();

		for(int i = 0; i < permission.length(); i++) {
			switch (permission.charAt(i)) {
			case '0':
				result.append("---");
				break;
			case '1':
				result.append("--x");
				break;
			case '2':
				result.append("-w-");
				break;
			case '3':
				result.append("-wx");
				break;
			case '4':
				result.append("r--");
				break;
			case '5':
				result.append("r-x");
				break;
			case '6':
				result.append("rw-");
				break;
			case '7':
				result.append("rwx");
				break;
			default:
				throw new IllegalArgumentException("Illegal permission argument");
			}
		}
		return result.toString();
	}


	public static void main(String[] args) {
		try {
//			Map<String, String> options = new HashMap<>();
//			options.put("overwrite", "true");
//			options.put("replication", "3");
//			options.put("buffersize", "4096");
//			options.put("permission", "755");

			HdfsAccess hdfsRESTfulUtil = new HdfsAccess("vm-45e5-3412", "50070", "bigdatagfts");
//			System.out.println(hdfsRESTfulUtil.upload("C:/Users/YM59750/Desktop/test.sh", "/test2.sh", null));
//			System.out.println(hdfsRESTfulUtil.append("C:/Users/YM59750/Desktop/test.sh", "/test2.sh", null));
//			System.out.println(hdfsRESTfulUtil.open("/test.sh", null));
//			System.out.println(hdfsRESTfulUtil.mkdir("/restful", null));
//			System.out.println(hdfsRESTfulUtil.rename("/tmp2.sql", "/tmp3.sql"));
//			System.out.println(hdfsRESTfulUtil.removeFile("/tmp3.sql"));
//			System.out.println(hdfsRESTfulUtil.removeDirectory("/restful"));
//			System.out.println(hdfsRESTfulUtil.getStatus("/test2.sh"));
//			System.out.println(hdfsRESTfulUtil.listDirectory("/user"));
//			System.out.println(hdfsRESTfulUtil.getDirectoryContentSummary("/"));
//			System.out.println(hdfsRESTfulUtil.getFileChecksum("/test2.sh"));
//			System.out.println(hdfsRESTfulUtil.getHomeDirectory());
//			System.out.println(hdfsRESTfulUtil.setPermission("/test2.sh", 755));
//			short replication = 4;
//			System.out.println(hdfsRESTfulUtil.setReplicationFactor("/test2.sh", replication));
//			System.out.println(hdfsRESTfulUtil.setAccessTime("/test2.sh", String.valueOf(System.currentTimeMillis())));
//			System.out.println(hdfsRESTfulUtil.setModificationTime("/tmp2.sql", String.valueOf(System.currentTimeMillis())));
			System.out.println(hdfsRESTfulUtil.getDelegationToken("bigdatagfts"));

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
