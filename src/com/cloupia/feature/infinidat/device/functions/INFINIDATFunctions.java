package com.cloupia.feature.infinidat.device.functions;

/**
 * Returns an Image object that can then be painted on the screen. 
 * The url argument must specify an absolute {@link URL}. The name
 * argument is a specifier that is relative to the url argument. 
 * <p>
 * This method always returns immediately, whether or not the 
 * image exists. When this applet attempts to draw the image on
 * the screen, the data will be loaded. The graphics primitives 
 * that draw the image will incrementally paint on the screen. 
 *
 * @author		roporter
 * @email		roporter@cisco.com
 * @version		0.8
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import javax.jdo.annotations.Persistent;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import java.util.regex.Matcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.accounts.INFINIDATAccount;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.INFINIDATDeviceInfo;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATServices;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATUsers;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATNodes;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATPermissions;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATPools;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATClusters;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATData;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATDrives;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATEventLog;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATExports;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATFCNetwork;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATFileSystem;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATHosts;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATIBNetwork;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATVolume;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATEthNetwork;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.cIaaS.network.model.DeviceCredential;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.lib.util.Base64Coder;
import com.cloupia.model.cIM.ReportContext;
import com.roporter.feature.format.myFormat;
import com.roporter.feature.json.myJson;
import com.roporter.feature.timer.myTimer;

@SuppressWarnings("unused")
public class INFINIDATFunctions {
	private static Logger logger = Logger.getLogger(INFINIDATFunctions.class);
	private DeviceCredential credential;
	private String accountName;
	private INFINIDATData newData = new INFINIDATData();
	private int intHostCount = 0;
	private int intNodesCount = 0;
	private int intVolumeCount = 0;
	private int intFileSystemCount = 0;
	private int intSnapshotCount = 0;
	private int intClusterCount = 0;
	private int intPoolCount = 0;
	private int intCloneCount = 0;
	private int intExportsCount = 0;
	private long longPoolPhysicalCapacity = 0;
	private long longPoolVirtualCapacity = 0;
	private long longPoolPhysicalFree = 0;
	private long longPoolVirtualFree = 0;
	private long longCapacity = 0;
	private long longFree = 0;
	private long longVirtualCapacity = 0;
	private long longVirtualFree = 0;
	private int intEventCount = 0;
	private int intDriveCount = 0;
	private long uptime = 0;
	private int enclousures = 0;
	private int racks = 0;
	private long localtime = 0;
	private int intInfoCount = 0;
	private int intWarningCount = 0;
	private int intErrorCount = 0;
	private int intCriticalCount = 0;
	private double lowestAverageReadLatency = 10000.0;
	private double lowestAverageWriteLatency = 10000.0;
	private double highestAverageReadLatency = 0;
	private double highestAverageWriteLatency = 0;
	private int localUsersCount = 0;
	private String controllerIP = "";
	private String controllerNetmask = "";
	private String controllerGateway = "";
	private String systemVersion = "";
	private String guiVersion = "";
	private String shellVersion = "";
	private String lastStatusResponse = null;
	private String lastResponseCode = "";
	private String lastResponseMessage = "";

	// SETUP / SYSTEM
	public INFINIDATFunctions() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:INFINIDATFunctions ####----");}
	}
	public INFINIDATFunctions(DeviceCredential credential) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:INFINIDATFunctions ####----");}
		this.credential = credential;
	}
	public DeviceCredential getCredential() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getCredential ####----");}
		return this.credential;
	}
	public void setAccountName(String accName) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:setAccountName ####----");}
		this.accountName = accName;
	}
	public void setCredential(DeviceCredential credential) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:setCredential ####----");}
		this.credential = credential;
	}
	public static DeviceCredential getDeviceCredentials(String accountName) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getDeviceCredentials ####----");}
		PhysicalInfraAccount phyAccount;
		try {
			phyAccount = AccountUtil.getAccountByName( accountName );
			DeviceCredential cred = phyAccount.toDeviceCredential();
			return cred;
		} catch (Exception e) {
			return null;
		}
	}
	public String getAccountName(ReportContext context) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getAccountName ####----");}
		String[] arr = null;
		if (context.getId() != null) {
			arr = context.getId().split(";");
			return "accountName == '" + arr[0] + "'";
		}
		return null;
	}
	
	// CORE HTTP REQUEST HANDLERS
	private String getResponse(String baseURL, String customURL, String method, String username, String password, String payload) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getResponse ####----");}
		try {
			if (method.toUpperCase().equals("GET")) {
				if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getResponse ####---- GET");}
				String url = baseURL + customURL;
				String response = getData(url, username, password);
				if (response != null && response != "") {
					return response;
				}
			} else if(method.toUpperCase().equals("POST")) {
				if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getResponse ####---- POST");}
				String url = baseURL  + customURL;
				String response = setData(url, username, password, payload);
				if (response != null && response != "") {
					return response;
				}
			} else if(method.toUpperCase().equals("DELETE")) {
				if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getResponse ####---- DELETE");}
				String url = baseURL + customURL;
				String response = deleteData(url, username, password, payload);
				if (response != null && response != "") {
					return response;
				}
			} else if(method.toUpperCase().equals("PUT")) {
				if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getResponse ####---- PUT");}
				String url = baseURL + customURL;
				String response = putData(url, username, password, payload);
				if(response != null && response != "") {
					return response;
				}
			}
			return null;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getResponse ####---- ERROR - ", e);}
			return null;
		}
	}
	private String getInputStream(HttpsURLConnection connection) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getInputStream ####----");}
		String resp = "";
		try {
			InputStream content = (InputStream) connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(content));
			this.lastResponseCode = String.valueOf(connection.getResponseCode()).trim();
			String thisLine;
			while ((thisLine = reader.readLine()) != null) {
				resp += thisLine;
			}
		} catch (IOException e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getInputStream ####---- ERROR: " + e.getMessage());}
		}
		return resp;
	}
	private String getErrorStream(HttpsURLConnection connection) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getErrorStream ####----");}
		String resp = null;
		try {
			InputStream content = (InputStream) connection.getErrorStream();
			if(content != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String thisLine;
				while ((thisLine = reader.readLine()) != null) {
					resp += thisLine;
				}
			}
		} catch (IOException e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getErrorStream ####---- ERROR: " + e.getMessage());}
		}
		return resp;
	}
	private HttpsURLConnection getConnection(String strURL, String method, String user, String pass, boolean ignoreInvalidCertificate, String payload) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getConnection ####----");}
		HttpsURLConnection connection = null;
		SSLContext ctx;
		try {
			ctx = SSLContext.getInstance("TLS");
		
			if (ignoreInvalidCertificate) {
				ctx.init(null, new TrustManager[] { new InvalidCertificateTrustManager() }, null);
			}
			SSLContext.setDefault(ctx);
	
			String authStr = user + ":" + pass;
			String authEncoded = Base64Coder.encodeString(authStr);
	
			SSLUtilities.trustAllHostnames();
			SSLUtilities.trustAllHttpsCertificates();
			
			URL url = new URL(strURL);
			connection = (HttpsURLConnection) url.openConnection();
	
			SSLUtilities.trustAllHostnames();
			SSLUtilities.trustAllHttpsCertificates();
			//if (ignoreInvalidCertificate) {
			//	connection.setHostnameVerifier(new InvalidCertificateHostVerifier());
			//}
			
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + authEncoded);
			connection.setRequestProperty("Content-Type","application/json");
			
			if (payload != null && payload != "") {
	            //set the content length of the body
	            connection.setRequestProperty("Content-length", payload.getBytes().length + "");
	            connection.setDoInput(true);
	            connection.setDoOutput(true);
	            connection.setUseCaches(false);
	 
	            //send the json as body of the request
	            OutputStream outputStream = connection.getOutputStream();
	            outputStream.write(payload.getBytes("UTF-8"));
	            outputStream.close();
	        }
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getConnection ####---- ERROR: " + e.getMessage());}
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getConnection ####---- ERROR CONNECTION: " + connection.getErrorStream());}
		}

		return connection;
	}
	public static String httpSimpleGet(String strURL) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:httpSimpleGet ####----");}
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { new InvalidCertificateTrustManager() }, null);
			SSLContext.setDefault(ctx);
			SSLUtilities.trustAllHostnames();
			SSLUtilities.trustAllHttpsCertificates();
			URL url = new URL(strURL);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			SSLUtilities.trustAllHostnames();
			SSLUtilities.trustAllHttpsCertificates();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type","application/json");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:httpSimpleGet ####---- RESPONSE CODE: " + connection.getResponseCode());}
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:httpSimpleGet ####---- RESPONSE: " + response.toString());}
			return response.toString();
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:httpSimpleGet ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public static String httpSimpleGet2(String url) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:httpSimpleGet2 ####----");}
		try {
			URL obj = new URL(url);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(INFINIDATConstants.HTTP_PROXY_SERVER_URL, INFINIDATConstants.HTTP_PROXY_SERVER_PORT));

			Properties systemProperties = System.getProperties();
			systemProperties.setProperty("http.proxyHost",INFINIDATConstants.HTTP_PROXY_SERVER_URL);
			systemProperties.setProperty("http.proxyPort", String.valueOf(INFINIDATConstants.HTTP_PROXY_SERVER_PORT));
			HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
	
			// optional default is GET
			con.setConnectTimeout(INFINIDATConstants.HTTP_CONNECTION_TIMEOUT);
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", INFINIDATConstants.HTTP_USER_AGENT);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:httpSimpleGet2 ####---- RESPONSE CODE: " + con.getResponseCode());}
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:httpSimpleGet2 ####---- RESPONSE: " + response.toString());}
	
			//print result
			return response.toString();
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:httpSimpleGet2 ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	private String getData(String url, String username, String password) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getData ####----");}
		String resp = null;
		HttpsURLConnection connection = null;
		try {
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getData ####---- URL: " + url);}
			connection = this.getConnection(url, "GET", username, password, true, null);
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getData ####---- CODE: " + connection.getResponseCode());}
			resp = getErrorStream(connection);
			if(resp == null) {
				resp = getInputStream(connection);
			}
			this.lastResponseCode = String.valueOf(connection.getResponseCode()).trim();
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getData ####---- HTTP RESPONSE: " + this.lastResponseCode);}
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getData ####---- URL: " + url + " | Response: " + resp);}
		} catch (IOException e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getData ####---- ERROR: " + e.getMessage());}
		}
		return resp;
	}
	private String putData(String url, String username, String password, String payload) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:putData ####----");}
		String resp = null;
		HttpsURLConnection connection = null;
		try {
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putData ####---- URL: " + url);}
			connection = this.getConnection(url, "PUT", username, password, true, payload);
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putData ####---- CODE: " + connection.getResponseCode());}
			resp = getErrorStream(connection);
			if(resp == null) {
				resp = getInputStream(connection);
			}
			this.lastResponseCode = String.valueOf(connection.getResponseCode()).trim();
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:putData ####---- HTTP RESPONSE: " + this.lastResponseCode);}
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putData ####---- URL: " + url + " | Response: " + resp);}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:putData ####---- ERROR: ", e);}
		}
		return resp;
	}
	private String setData(String url, String username, String password, String payload) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:setData ####----");}
		String resp = null;
		HttpsURLConnection connection = null;
		try {
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:setData ####---- URL: " + url);}
			connection = this.getConnection(url, "POST", username, password, true, payload);
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:setData ####---- CODE: " + connection.getResponseCode());}
			resp = getErrorStream(connection);
			if(resp == null) {
				resp = getInputStream(connection);
			}
			this.lastResponseCode = String.valueOf(connection.getResponseCode());
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getData ####---- HTTP RESPONSE: " + this.lastResponseCode);}
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:setData ####---- URL: " + url + " | Response: " + resp);}
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:setData ####---- ERROR - IO Exception occurred - " + ex.getMessage());}
			connection.disconnect();
		} 
		return resp;
	}
	private String deleteData(String url, String username, String password, String payload) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteData ####----");}
		String resp = null;
		HttpsURLConnection connection = null;
		try {
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:deleteData ####---- URL: " + url);}
			connection = this.getConnection(url, "DELETE", username, password, true, payload);
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteData ####---- CODE: " + connection.getResponseCode());}
			resp = getErrorStream(connection);
			logger.info("----#### INFINIDATFunctions:deleteData ####---- RESP: " + resp);
			//if(resp.equals(null)) {
			//	logger.info("----#### INFINIDATFunctions:deleteData ####---- EQUALS");
			//	resp = getInputStream(connection);
			//}
			if(resp == null) {
				logger.info("----#### INFINIDATFunctions:deleteData ####---- ==");
				resp = getInputStream(connection);
			}
			logger.info("----#### INFINIDATFunctions:deleteData ####---- RESP: " + resp);
			this.lastResponseCode = String.valueOf(connection.getResponseCode());
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:deleteData ####---- HTTP RESPONSE: " + this.lastResponseCode);}
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:deleteData ####---- URL: " + url + " | Response: " + resp);}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteData ####---- ERROR: " + e.getMessage());}
			e.printStackTrace();
			connection.disconnect();
		} 
		return resp;
	}
	
	// CORE INVENTORY
	public void getSummaryInventory(String accountName) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getSummaryInventory ####----");}
		myTimer timer = new myTimer();
		timer.start("getSummaryInventory");
		this.accountName = accountName;
		this.newData.setAccountName(this.accountName);
		String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";

		try {
			this.getNetworkInformation(baseURL);
			this.getSystemSummary(baseURL);
			this.getVolumesSummary(baseURL);
			this.getFileSystemSummary(baseURL);
			this.getHostsSummary(baseURL);
			this.getClustersSummary(baseURL);
			this.getEventsLog(baseURL);
			this.getNodesServicesDrivesIBFCEthPSUHBASummary(baseURL);
			this.getPools(baseURL);
			this.getExports(baseURL);
			this.getUsers(baseURL);
			this.putSystemDetails(baseURL);

			if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getSummaryInventory ####---- ################################################################");}
			if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getSummaryInventory ####---- # INFINIDAT INVENTORY COLLECTION COMPLETE : " + timer.stop());}
			if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getSummaryInventory ####---- ################################################################");}

		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getSummaryInventory ####---- COLLECTION FAILED", e);}
			throw (e);
		}
	}
	private void putSystemDetails(String url) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:putSystemDetails ####----");}
		int inserted = 0;
		try {
			String payload = "";
			String response = getResponse(url, "api/rest/counters/system/total", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
	
			ObjStore<INFINIDATData> store = ObjStoreHelper.getStore(INFINIDATData.class);
			String query = "accountName == '" + this.accountName +"'";
			List<INFINIDATData> objs = store.query(query);
			if(objs.size() == 1) {
				INFINIDATData pojo = objs.get(0);
				this.lowestAverageReadLatency = pojo.getLowestAverageReadLatency();
				this.lowestAverageWriteLatency = pojo.getLowestAverageWriteLatency();
				this.highestAverageReadLatency = pojo.getHighestAverageReadLatency();
				this.highestAverageWriteLatency = pojo.getHighestAverageWriteLatency();
				if(this.lowestAverageReadLatency == 0.0) {this.lowestAverageReadLatency = 100000;}
				if(this.lowestAverageWriteLatency == 0.0) {this.lowestAverageWriteLatency = 100000;}
			}
			long count = store.delete("accountName == '" + this.accountName + "'");
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- DATA DELETED = " + count + " ENTRIES");}
			
			Map<String,String> ops = myJson.getRootElementAsMapString(response,"result");

			double a = 0;
			double b = 0;
			if(myFormat.safeStringToLong(ops.get("read_latency")) > 0 && myFormat.safeStringToLong(ops.get("read_ops")) > 0 ) {
				a = myFormat.safeStringToLong(ops.get("read_latency")) / myFormat.safeStringToLong(ops.get("read_ops"));
			}
			if(myFormat.safeStringToLong(ops.get("write_latency")) > 0 && myFormat.safeStringToLong(ops.get("write_ops")) > 0 ) {
				b = myFormat.safeStringToLong(ops.get("write_latency")) / myFormat.safeStringToLong(ops.get("write_ops"));
			}

			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- lowestAverageReadLatency = " + lowestAverageReadLatency);}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- lowestAverageWriteLatency = " + lowestAverageWriteLatency);}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- highestAverageReadLatency = " + highestAverageReadLatency);}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- highestAverageWriteLatency = " + highestAverageWriteLatency);}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- A = " + a);}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- B = " + b);}
			
			if(a < lowestAverageReadLatency) {
				lowestAverageReadLatency = a;
			}
			if(a > highestAverageReadLatency) {
				highestAverageReadLatency = a;
			}
			if(b < lowestAverageWriteLatency) {
				lowestAverageWriteLatency = b;
			}
			if(b > highestAverageWriteLatency) {
				highestAverageWriteLatency = b;
			}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- lowestAverageReadLatency = " + lowestAverageReadLatency);}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- lowestAverageWriteLatency = " + lowestAverageWriteLatency);}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- highestAverageReadLatency = " + highestAverageReadLatency);}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- highestAverageWriteLatency = " + highestAverageWriteLatency);}
			
			INFINIDATData newData = new INFINIDATData(
					this.accountName,
					longCapacity,
					longFree,
					longVirtualCapacity,
					longVirtualFree,
					intNodesCount,
					intEventCount,
					intPoolCount,
					intVolumeCount,
					intSnapshotCount,
					intHostCount,
					intClusterCount,
					intFileSystemCount,
					intDriveCount,
					intCloneCount,
					intExportsCount,
					longPoolPhysicalCapacity,
					longPoolVirtualCapacity,
					longPoolPhysicalFree,
					longPoolVirtualFree,
					enclousures,
					racks,
					uptime,
					localtime,
					controllerIP,
					controllerNetmask,
					controllerGateway,
					systemVersion,
					guiVersion,
					shellVersion,
					myFormat.safeStringToLong(ops.get("read_latency")),
					myFormat.safeStringToLong(ops.get("write_latency")),
					myFormat.safeStringToLong(ops.get("read_bytes")),
					myFormat.safeStringToLong(ops.get("write_bytes")),
					myFormat.safeStringToLong(ops.get("read_ops")),
					myFormat.safeStringToLong(ops.get("write_ops")),
					myFormat.safeStringToLong(ops.get("ops")),
					myFormat.safeStringToLong(ops.get("bytes")),
					myFormat.safeStringToLong(ops.get("latency")),
					intInfoCount,
					intWarningCount,
					intErrorCount,
					intCriticalCount,
					lowestAverageReadLatency,
					lowestAverageWriteLatency,
					highestAverageReadLatency,
					highestAverageWriteLatency,
					localUsersCount
			);
			store.insert( newData );
			inserted += 1;

			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- DATA INSERTED = " + inserted);}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- COLLECTION SUCCEEDED");}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:putSystemDetails ####---- COLLECTION FAILED", e);}
		}
	}
	private void getPools(String url) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getPools ####----");}
		int inserted = 0;
		try {
			String payload = "";
			String response = getResponse(url, "api/rest/pools?page_size=" + INFINIDATConstants.INFINIDAT_HTTP_REQUEST_MAX_RESULTS, "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}

			ObjStore<INFINIDATPools> store = ObjStoreHelper.getStore(INFINIDATPools.class);
			long count = store.delete("accountName == '" + this.accountName + "'");
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getVolumes ####---- POOLS DELETED = " + count + " ENTRIES");}
			

			
			
			int volumeCount = myJson.getElementCountAsInt(response,"result");
			for( int i = 0; i < volumeCount; i++ ) {
				Map<String, String> data = myJson.getExactArrayItemsAsMapString(response, "result", Integer.toString(i), (new String[]{"id", "name", "state", "created_at", "updated_at", "physical_capacity", "virtual_capacity", "physical_capacity_warning", "physical_capacity_critical", "reserved_capacity", "entities_count", "volumes_count", "filesystems_count", "snapshots_count", "clones_count", "allocated_physical_space", "free_physical_space", "free_virtual_space" }));
				INFINIDATPools newPool = new INFINIDATPools(
					this.accountName,
					myFormat.safeStringToInt(data.get("id")),
					data.get("name"),
					data.get("state"),
					myFormat.safeStringToLong(data.get("created_at")),
					myFormat.safeStringToLong(data.get("updated_at")),
					myFormat.safeStringToLong(data.get("physical_capacity")),
					myFormat.safeStringToLong(data.get("virtual_capacity")),
					myFormat.safeStringToInt(data.get("physical_capacity_warning")),
					myFormat.safeStringToInt(data.get("physical_capacity_critical")),
					myFormat.safeStringToLong(data.get("reserved_capacity")),
					myFormat.safeStringToInt(data.get("entities_count")),
					myFormat.safeStringToInt(data.get("volumes_count")),
					myFormat.safeStringToInt(data.get("filesystems_count")),
					myFormat.safeStringToInt(data.get("snapshots_count")),
					myFormat.safeStringToInt(data.get("clones_count")),
					myFormat.safeStringToLong(data.get("allocated_physical_space")),
					myFormat.safeStringToLong(data.get("free_physical_space")),
					myFormat.safeStringToLong(data.get("free_virtual_space")),
					INFINIDATConstants.INFINIDAT_NEW_ITEM_COMMENT
				);
				store.insert( newPool );
				inserted += 1;
				
				longPoolPhysicalCapacity += myFormat.safeStringToLong(data.get("physical_capacity"));
				longPoolVirtualCapacity += myFormat.safeStringToLong(data.get("virtual_capacity"));
				longPoolPhysicalFree += myFormat.safeStringToLong(data.get("free_physical_space"));
				longPoolVirtualFree += myFormat.safeStringToLong(data.get("free_virtual_space"));
			}
			this.intPoolCount = volumeCount;
			
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getPools ####---- POOLS INSERTED = " + inserted);}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getPools ####---- COLLECTION SUCCEEDED");}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getPools ####---- COLLECTION FAILED", e);}
		}
	}
	private void getNodesServicesDrivesIBFCEthPSUHBASummary(String url) {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####----");}
		try {
			int servicesInserted = 0; int nodesInserted = 0; int drivesInserted = 0; int infinibandInserted = 0;
			int fibrechannelInserted = 0; int ethernetInserted = 0; int powersupplyInserted = 0; int hbaInserted = 0;
			int ibInserted = 0;
			String payload = "";
			
			String response = getResponse(url, "api/rest/components", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			
			if( response != null ) {
				int nodeCount = myJson.getElementCountAsInt(response,"result.nodes");
				this.enclousures = myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", "enclosures_number"));
				this.racks = myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", "rack"));
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- NODE COUNT: " + nodeCount);}
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- ENCLOUSURE COUNT: " + this.enclousures);}
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- RACK COUNT: " + this.racks);}
				
				ObjStore<INFINIDATNodes> nodeStore = ObjStoreHelper.getStore(INFINIDATNodes.class);
				ObjStore<INFINIDATServices> serviceStore = ObjStoreHelper.getStore(INFINIDATServices.class);
				ObjStore<INFINIDATDrives> driveStore = ObjStoreHelper.getStore(INFINIDATDrives.class);
				ObjStore<INFINIDATEthNetwork> ethernetStore = ObjStoreHelper.getStore(INFINIDATEthNetwork.class);
				ObjStore<INFINIDATFCNetwork> fcStore = ObjStoreHelper.getStore(INFINIDATFCNetwork.class);
				ObjStore<INFINIDATIBNetwork> ibStore = ObjStoreHelper.getStore(INFINIDATIBNetwork.class);
				
				long count = nodeStore.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getNodes ####---- NODES DELETED = " + count + " ENTRIES");}
				count = serviceStore.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getServices ####---- SERVICES DELETED = " + count + " ENTRIES");}
				count = driveStore.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getDrives ####---- DRIVES DELETED = " + count + " ENTRIES");}
				count = ethernetStore.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getEthernet ####---- ETHERNET DELETED = " + count + " ENTRIES");}
				count = fcStore.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getEthernet ####---- FC DELETED = " + count + " ENTRIES");}
				count = ibStore.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getEthernet ####---- IB DELETED = " + count + " ENTRIES");}
								
				for(int i = 0; i < nodeCount; i++) {
					int nodeServicesCount = 0;
					int nodeDrivesCount = 0;
					int nodeIBCount = 0;
					int nodeECount = 0;
					int nodeFCCount = 0;
					
					int nodeid = myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", "nodes."+i+".id"));
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- NODE " + i);}
					// NODE
					INFINIDATNodes newNode = addNode(response, nodeid, i);
					nodeStore.insert( newNode );
					nodesInserted += 1;
					// SERVICES
					int servicesCount = myJson.getElementCountAsInt(response,"result.nodes."+i+".services");
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- IDENTIFIED " + servicesCount + " Services");}
					for( int j = 0; j < servicesCount; j++ ) {
						INFINIDATServices newService = addService(response, nodeid, i, j);
						if(newService != null) {
							serviceStore.insert( newService );
							servicesInserted += 1;
							nodeServicesCount += 1;
						}
					}
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- ADDED " + nodeServicesCount + " Services");}
					// DRIVES
					int drivesCount = myJson.getElementCountAsInt(response,"result.nodes."+i+".drives");
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- IDENTIFIED " + drivesCount + " Drives");}
					for( int j = 0; j < drivesCount; j++ ) {
						INFINIDATDrives newDrive = addDrive(response, nodeid, i, j);
						if(newDrive != null) {
							driveStore.insert( newDrive );
							drivesInserted += 1;
							nodeDrivesCount += 1;
						}
					}
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- ADDED " + nodeDrivesCount + " Drives");}
					
					// INFINIBAND
					int infinibandCount = myJson.getElementCountAsInt(response,"result.nodes."+i+".ib_ports");
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- IDENTIFIED " + infinibandCount + " Infiniband Apaters");}
					for( int j = 0; j < infinibandCount; j++ ) {
						INFINIDATIBNetwork newFC = addIBNetwork(response, nodeid, i, j);
						if(newFC != null) {
							ibStore.insert(newFC);
							ibInserted += 1;
							nodeIBCount += 1;
						}
					}
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- ADDED " + nodeIBCount + " InfiniBand Adapters");}
					
					// FIBRE CHANNEL
					int fibrechannelCount = myJson.getElementCountAsInt(response,"result.nodes."+i+".fc_ports");
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- IDENTIFIED " + fibrechannelCount + " Fibre Channel Adapters");}
					for( int j = 0; j < fibrechannelCount; j++ ) {
						INFINIDATFCNetwork newFC = addFibreChannel(response, nodeid, i, j);
						if(newFC != null) {
							fcStore.insert(newFC);
							fibrechannelInserted += 1;
							nodeFCCount += 1;
						}
					}
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- ADDED " + nodeFCCount + " Fibre Channel Adapters");}
					
					// ETHERNET
					int ethernetCount = myJson.getElementCountAsInt(response,"result.nodes."+i+".eth_ports");
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- IDENTIFIED " + ethernetCount + " Ethernet Adapters");}
					for( int j = 0; j < ethernetCount; j++ ) {
						INFINIDATEthNetwork newEthernet = addEthernet(response, nodeid, i, j);
						if(newEthernet != null) {
							ethernetStore.insert( newEthernet );
							ethernetInserted += 1;
							nodeECount += 1;
						}
					}
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- ADDED " + nodeECount + " Ethernet Adapters");}
					// POWER SUPPLIES
					int powersupplyCount = myJson.getElementCountAsInt(response,"result.nodes."+i+".power_supplies");
					if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- IDENTIFIED " + powersupplyCount + " Power Supplies");}
				}
				
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTED " + nodesInserted + " NODES");}
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTED " + servicesInserted + " SERVICES");}
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTED " + drivesInserted + " DRIVES");}
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTED " + infinibandInserted + " INFINIBAND ADAPTERS");}
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTED " + fibrechannelInserted + " FIBRE CHANNEL ADAPTERS");}
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTED " + ethernetInserted + " ETHERNET ADAPTERS");}
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTED " + powersupplyInserted + " POWER SUPPLIES");}
				if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTED " + hbaInserted + " HBA's");}	
					
				this.intNodesCount = nodesInserted;
				this.intDriveCount = drivesInserted;
					
			}
			if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTION SUCCEEDED");}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getNodesServicesDrivesIBFCEthPSUHBASummary ####---- COLLECTION FAILED", e);}
		}
	}
	private INFINIDATEthNetwork addEthernet(String response, int nodeid, int pos, int j) {
		if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addEthernet ####----");}
		try {
			String tmpSpeed = myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".connection_speed");
			String ip = myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".ip_v4_addr");
			String broadcast = myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".ip_v4_broadcast");
			String netmask = myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".ip_v4_netmask");
			String name = myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".name");
			long speed = 0;
			if(tmpSpeed != null && !tmpSpeed.equals("null")){speed=myFormat.safeStringToLong(tmpSpeed);}
			if(ip == null || ip.equals("null")){ip="";}
			if(broadcast == null || broadcast.equals("null")){broadcast="";}
			if(netmask == null || netmask.equals("null")){netmask="";}
			if(name == null || name.equals("null")){name="";}
			INFINIDATEthNetwork newEthernet = new INFINIDATEthNetwork(
					this.accountName, nodeid,
					myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".id")),
					myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".port_number")),
					name,
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".role"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".model"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".vendor"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".firmware"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".state"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".hw_addr"),
					ip,
					broadcast,
					netmask,
					speed,
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".eth_ports."+j+".link_state")
				);
			if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addEthernet ####---- NEW ETHERNET: " + newEthernet.debug());}
			return newEthernet;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:addEthernet ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	private INFINIDATFCNetwork addFibreChannel(String response, int nodeid, int pos, int j) {
		if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addFibreChannel ####----");}
		try {
			String tmpSpeed = myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".connection_speed");
			long speed = 0;
			if(tmpSpeed != null && !tmpSpeed.equals("null")){speed=myFormat.safeStringToLong(tmpSpeed);}
			INFINIDATFCNetwork newFC = new INFINIDATFCNetwork(
					this.accountName, nodeid,
					myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".id")),
					myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".hba_port_number")),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".link_state"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".state"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".hardware_revision"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".firmware"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".role"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".switch_vendor"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".vendor"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".model"),
					myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".enabled")),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".wwpn"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".fc_ports."+j+".wwnn"),
					speed
			);
			if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addFibreChannel ####---- NEW FC: " + newFC.debug());}
			return newFC;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:addFibreChannel ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	private INFINIDATDrives addDrive(String response, int nodeid, int pos, int j) {
		if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addDrive ####----");}
		try {
			INFINIDATDrives newDrive = new INFINIDATDrives(
					this.accountName, nodeid,
					myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".drives."+j+".id")),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".drives."+j+".model"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".drives."+j+".vendor"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".drives."+j+".firmware"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".drives."+j+".state"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".drives."+j+".type"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".drives."+j+".serial_number")
			);
			return newDrive;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addDrive ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	private INFINIDATServices addService(String response, int nodeid, int pos, int j) {
		if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addService ####----");}
		try {
			INFINIDATServices newService = new INFINIDATServices(
					this.accountName, nodeid,
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".services."+j+".role"),
					myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".services."+j+".name"),
					myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response,"result", "nodes."+pos+".services."+j+".state"))
			);
			return newService;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addService ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	private INFINIDATNodes addNode(String response, int nodeid, int pos) {
		if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addNode ####----");}
		try {
			INFINIDATNodes newNode = new INFINIDATNodes(
					this.accountName, nodeid,
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".name"),
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".model"),
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".vendor"),
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".firmware"),
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".state")
			);
			return newNode;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addNode ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	private INFINIDATIBNetwork addIBNetwork(String response, int nodeid, int pos, int j) {
		if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addIBNetwork ####----");}
		try {
			INFINIDATIBNetwork newNode = new INFINIDATIBNetwork(
					this.accountName, nodeid,
					myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".ib_ports."+j+".id")),
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".ib_ports."+j+".model"),
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".ib_ports."+j+".vendor"),
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".ib_ports."+j+".firmware"),
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".ib_ports."+j+".state"),
					myJson.getExactSingleItemAsString(response, "result", "nodes."+pos+".ib_ports."+j+".state_description"),
					INFINIDATConstants.INFINIDAT_NEW_ITEM_COMMENT
			);
			return newNode;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addIBNetwork ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	private void getNetworkInformation(String url) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getNetworkInformation ####----");}
		try {		
			myTimer timer = new myTimer();
			timer.start("getNetworkInformation");
			String payload = "";
			String response2 = getResponse(url, "api/rest/config/ip_config", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			String response3 = getResponse(url, "api/rest/config/node_interfaces", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response2.substring(0, 4).equals("null")) {response2 = response2.substring(4, response2.length());}
			if( response3.substring(0, 4).equals("null")) {response3 = response3.substring(4, response3.length());}
			
			if (response2 != null) {
				 this.controllerIP = myJson.getExactSingleItemAsString(response3,"result","0.ip");
				 this.controllerNetmask = myJson.getExactSingleItemAsString(response3,"result","0.netmask");
				 this.controllerGateway = myJson.getExactSingleItemAsString(response2,"result","gateway");
			}

			if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getSummaryInventory ####---- # INFINIDAT NETWORK INVENTORY COLLECTION COMPLETE : " + timer.stop());}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----------#### INFINIDATFunctions:getNetworkInformation ####---- ERROR - " + e);}
		}
	}
	private void getSystemSummary( String url ) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getSystemSummary ####----");}
		try {
			myTimer timer = new myTimer();
			timer.start("getNetworkInformation");
			String payload = "";
			String response = getResponse(url, "api/rest/gui/overview", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- RESPONSE: " + response );}
			
			int inserted = 0;
			int modified = 0;
			
			if( response != null ) {
				String model = myJson.getExactSingleItemAsString(response, "result", "system.model");
				String name = myJson.getExactSingleItemAsString(response, "result", "system.name");
				String version = myJson.getExactSingleItemAsString(response, "result", "system.version");
				String uptime = myJson.getExactSingleItemAsString(response, "result", "system.uptime");
				String serial = myJson.getExactSingleItemAsString(response, "result", "system.serial_number");
				String gid = myJson.getExactSingleItemAsString(response, "result", "system.deployment_id");
				String id = myJson.getExactSingleItemAsString(response, "result", "system.product_id");
				String capacity = myJson.getExactSingleItemAsString(response, "result", "system.capacity.total_physical_capacity");
				String free = myJson.getExactSingleItemAsString(response, "result", "system.capacity.free_physical_space");
				String vcapacity = myJson.getExactSingleItemAsString(response, "result", "system.capacity.total_virtual_capacity");
				String vfree  = myJson.getExactSingleItemAsString(response, "result", "system.capacity.free_virtual_space");
				String file = myJson.getExactSingleItemAsString(response, "result", "system.primary_filesystem_count");
				String local =  myJson.getExactSingleItemAsString(response, "result", "system.localtime.utc_time");

				this.intFileSystemCount = myFormat.safeStringToInt(file);
				this.systemVersion=  myJson.getExactSingleItemAsString(response, "result", "system.release.system.version");
				this.guiVersion=  myJson.getExactSingleItemAsString(response, "result", "system.release.gui.version");
				this.shellVersion =  myJson.getExactSingleItemAsString(response, "result", "system.release.infinishell.version");
				this.localtime = myFormat.safeStringToLong(local);
				this.uptime = myFormat.safeStringToLong(uptime);
				this.longCapacity = myFormat.safeStringToLong(capacity);
				this.longFree = myFormat.safeStringToLong(free);
				this.longVirtualCapacity = myFormat.safeStringToLong(vcapacity);
				this.longVirtualFree = myFormat.safeStringToLong(vfree);
				
				free = myFormat.humanReadableFromString(free, true);
				capacity =  myFormat.humanReadableFromString(capacity,true);
		
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- IP: " + this.controllerIP );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- NETMASK: " + this.controllerNetmask );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- GATEWAY: " + this.controllerGateway );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- FILESYSTEM COUNT: " + this.intFileSystemCount );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- SYSTEM VERSION: " + this.systemVersion );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- GUI VERSION: " + this.guiVersion );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- SHELL VERSION: " + this.shellVersion );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- LOCAL TIME: " + this.localtime );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- UPTIME: " + this.uptime );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- CAPACITY: " + this.longCapacity );}
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getSystemSummary ####---- FREE: " + this.longFree );}
				
				ObjStore<INFINIDATDeviceInfo> store = ObjStoreHelper.getStore(INFINIDATDeviceInfo.class);

				INFINIDATDeviceInfo newDevice = new INFINIDATDeviceInfo(
					this.accountName,
					id,
					controllerIP,
					name,
					uptime,
					gid,
					serial,
					model,
					version,
					capacity,
					free,
					"INFINIDAT",
					"OK"
				);
				
				String query = "accountName == '" + newDevice.getAccountName() +"'";
				if( store.queryCount( "accountName", query ) == 0 ) {
					store.insert( newDevice );
					inserted += 1;
				} else {
					store.modifySingleObject( query, newDevice );
					modified += 1;
				}
				if( inserted > 0 )
					if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info( "-----------#### INFINIDATFunctions:getSystemInventory ####---- SYSTEM OVERVIEW INFO INSERTED = " + inserted );}
				if( modified > 0 )
					if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info( "-----------#### INFINIDATFunctions:getSystemInventory ####---- SYSTEM OVERVIEW INFO MODIFIED = " + modified );}
				

				if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getSystemInventory ####---- # INFINIDAT SYSTEM INVENTORY COLLECTION COMPLETE : " + timer.stop());}
			} else {
				if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info("----------#### INFINIDATFunctions:getSystemSummary ####---- GET SYSTEM SUMMARY - COLLECTION FAILED");}
			}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----------#### INFINIDATFunctions:getSystemSummary ####---- ERROR - " + e);}
		}
	}
	private void getVolumesSummary(String url) throws Exception {
		if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getVolumesSummary ####----");}
		try {
			myTimer timer = new myTimer();
			timer.start("getNetworkInformation");
			String payload = "";
			String response = getResponse(url, "api/rest/volumes?page_size=" + INFINIDATConstants.INFINIDAT_HTTP_REQUEST_MAX_RESULTS, "GET", this.credential.getLogin(),this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			
			String ip = "";
			int inserted = 0;
			int modified = 0;
			
			if (response != null && response != "") {
				ObjStore<INFINIDATVolume> store = ObjStoreHelper.getStore(INFINIDATVolume.class);
				long count = store.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getVolumesSummary ####---- VOLUMES DELETED = " + count + " ENTRIES");}
				
				int volumeCount = myJson.getElementCountAsInt(response,"result");
				for( int i = 0; i < volumeCount; i++ ) {
					int id = myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".id"));
					int poolid = myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".pool_id"));
					String type = myJson.getExactSingleItemAsString(response, "result", i + ".type");
					if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getVolumesSummary ####---- STARTING NEWVOLUME");}
					
					INFINIDATVolume newVolume = new INFINIDATVolume(
						this.accountName,
						myJson.getExactSingleItemAsString(response, "result", i + ".name"),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".size")),
						id,
						poolid,
						getPoolNameFromID(poolid),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".created_at")),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".updated_at")),
						myJson.getExactSingleItemAsString(response, "result", i + ".provtype"),
						type,
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".used")),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".mapped")),
						myJson.getExactSingleItemAsString(response, "result", i + ".serial"),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".write_protected")),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".ssd_enabled")),
						INFINIDATConstants.INFINIDAT_NEW_ITEM_COMMENT
					);
					store.insert( newVolume );
					inserted += 1;
					
					if(type.equals("SNAP")) {
						this.intSnapshotCount += 1;
					}else if(type.equals("CLONE")) {
						this.intCloneCount += 1;
					}else if(type.equals("MASTER")) {
						this.intVolumeCount += 1;
					}

					//String response2 = getResponse(url, "api/rest/counters/volumes/"+id, "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
					//if( response2.substring(0, 4).equals("null")) {response2 = response2.substring(4, response2.length());}
					//if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getVolumesSummary ####---- VOLUMES STATS = " + response2);}	
				}

				if(INFINIDATConstants.DEBUG){logger.info("----------#### INFINIDATFunctions:getVolumesSummary ####---- VOLUMES INSERTED = " + inserted);}
				if(INFINIDATConstants.DEBUG){logger.info("----------#### INFINIDATFunctions:getVolumesSummary ####---- VOLUME MASTERS INSERTED = " + this.intVolumeCount);}
				if(INFINIDATConstants.DEBUG){logger.info("----------#### INFINIDATFunctions:getVolumesSummary ####---- VOLUME SNAPSHOTS INSERTED = " + this.intSnapshotCount);}
				if(INFINIDATConstants.DEBUG){logger.info("----------#### INFINIDATFunctions:getVolumesSummary ####---- VOLUME CLONES INSERTED = " + this.intCloneCount);}
				
				if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getVolumesSummary ####---- # INFINIDAT VOLUME INVENTORY COLLECTION COMPLETE : " + timer.stop());}
			} else {
				if(INFINIDATConstants.DEBUG){logger.info("----------#### INFINIDATFunctions:getVolumesSummary ####---- getVolumes - COLLECTION FAILED");}
			}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG){logger.info("----------#### INFINIDATFunctions:getVolumesSummary ####---- ERROR: " + e);}
			e.printStackTrace();
		}
	}
	private void getHostsSummary(String url) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getHostsSummarys ####----");}
		try {
			myTimer timer = new myTimer();
			timer.start("getNetworkInformation");
			String payload = "";
			String response = getResponse(url, "api/rest/hosts?page_size=" + INFINIDATConstants.INFINIDAT_HTTP_REQUEST_MAX_RESULTS, "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			
			String ip = "";
			int inserted = 0;
			int modified = 0;
			
			if (response != null) {
				ObjStore<INFINIDATHosts> store = ObjStoreHelper.getStore(INFINIDATHosts.class);
				long count = store.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("-------------#### INFINIDATFunctions:getHostsSummarys ####---- HOSTS DELETED = " + count + " ENTRIES");}
				
				int hostCount = myJson.getElementCountAsInt(response,"result");
				String clusterName = "";
				for( int i = 0; i < hostCount; i++ ) {
					clusterName = getClusterNameLIVEFromID(myJson.getExactSingleItemAsString(response,"result", i + ".host_cluster_id"));
					INFINIDATHosts newHost = new INFINIDATHosts(
						this.accountName,
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".id")),
						myJson.getExactSingleItemAsString(response, "result", i + ".host_type"),
						myJson.getExactSingleItemAsString(response, "result", i + ".name"),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".host_cluster_id")),
						clusterName,
						myJson.getElementCountAsInt(response,"result."+i+".ports"),
						myJson.getElementCountAsInt(response,"result."+i+".luns"),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".created_at")),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".updated_at")),
						INFINIDATConstants.INFINIDAT_NEW_ITEM_COMMENT
					);
					store.insert( newHost );
					inserted += 1;
				}
				this.intHostCount = inserted;
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info("----------#### INFINIDATFunctions:getHostsSummarys ####---- getHosts INSERTED = " + inserted);}
				
				if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getHostsSummarys ####---- # INFINIDAT HOST INVENTORY COLLECTION COMPLETE : " + timer.stop());}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----------#### INFINIDATFunctions:getHostsSummarys ####---- getHosts - COLLECTION FAILED");}
			}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----------#### INFINIDATFunctions:getHostsSummarys ####---- ERROR - " + e);}
		}
	}
	private String getClusterNameLIVEFromID(String exactSingleItemAsString) {
		// TODO
		return "";
	}
	private void getClustersSummary(String url) {
		if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getClustersSummary ####----");}
		try {
			myTimer timer = new myTimer();
			timer.start("getNetworkInformation");
			String payload = "";
			String response = getResponse(url, "api/rest/clusters?page_size=" + INFINIDATConstants.INFINIDAT_HTTP_REQUEST_MAX_RESULTS, "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			
			String ip = "";
			int inserted = 0;
			
			if (response != null) {
				ObjStore<INFINIDATClusters> store = ObjStoreHelper.getStore(INFINIDATClusters.class);
				long count = store.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getClustersSummary ####---- CLUSTERS DELETED = " + count + " ENTRIES");}
				
				int clusterCount = myJson.getElementCountAsInt(response,"result");
				for( int i = 0; i < clusterCount; i++ ) {
					INFINIDATClusters newCluster = new INFINIDATClusters(
						this.accountName,
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".id")),
						myJson.getExactSingleItemAsString(response, "result", i + ".name"),
						myJson.getElementCountAsInt(response,"result."+i+".luns"),
						myJson.getElementCountAsInt(response,"result."+i+".hosts"),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".created_at")),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".updated_at")),
						INFINIDATConstants.INFINIDAT_NEW_ITEM_COMMENT
					);
					store.insert( newCluster );
					inserted += 1;
				}
				this.intClusterCount = inserted;
				if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getClustersSummary ####---- getClustersSummary INSERTED = " + inserted);}

				if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getClustersSummary ####---- # INFINIDAT CLUSTER INVENTORY COLLECTION COMPLETE : " + timer.stop());}
			} else {
				if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getClustersSummary ####---- getClustersSummary - COLLECTION FAILED");}
			}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getClustersSummary ####---- ERROR - " + e);}
		}
	}
	private void getEventsLog(String url) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getEventsLog ####----");}}
		try {
			myTimer timer = new myTimer();
			timer.start("getNetworkInformation");
			String payload = "";
			String response = getResponse(url, "api/rest/events/?sort=-timestamp&page_size=" + INFINIDATConstants.EVENTS_NO_EVENTS_TO_FETCH, "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			
			String ip = "";
			int inserted = 0;

			if (response != null) {
				ObjStore<INFINIDATEventLog> store = ObjStoreHelper.getStore(INFINIDATEventLog.class);
				long count = store.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getEventsLog ####---- EVENTS DELETED = " + count + " ENTRIES");}
				
				int eventCount = myJson.getElementCountAsInt(response,"result");
				for( int i = 0; i < eventCount; i++ ) {
					String eventLevel = myJson.getExactSingleItemAsString(response, "result", i + ".level");
					if( eventLevel.equals("INFO")) { intInfoCount+=1; } else if( eventLevel.equals("WARNING")) { intWarningCount+=1; } else if(eventLevel.equals("ERROR")) { intErrorCount+=1;} else if(eventLevel.equals("CRITICAL")) { intCriticalCount+=1; }
					INFINIDATEventLog newEvent = new INFINIDATEventLog(
						this.accountName,
						myJson.getExactSingleItemAsString(response, "result", i + ".reporter"),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".id")),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".timestamp")),
						myJson.getExactSingleItemAsString(response, "result", i + ".level"),
						myJson.getExactSingleItemAsString(response, "result", i + ".visibility"),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".source_node_id")),
						myJson.getExactSingleItemAsString(response, "result", i + ".system_version"),
						myJson.getExactSingleItemAsString(response, "result", i + ".description")
					);
					store.insert( newEvent );
					inserted += 1;
				}
				this.intEventCount = inserted;
				if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getEventsLog ####---- getEvents - COLLECTION SUCCEEDED");}

				if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getEventsLog ####---- # INFINIDAT EVENT INVENTORY COLLECTION COMPLETE : " + timer.stop());}
			}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getEventsLog ####---- ERROR ", e);}
		}
	}
	private void getFileSystemSummary(String url) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getFileSystemSummary ####----");}}
		try {
			myTimer timer = new myTimer();
			timer.start("getNetworkInformation");
			String payload = "";
			String response = getResponse(url, "api/rest/filesystems?page_size=" + INFINIDATConstants.INFINIDAT_HTTP_REQUEST_MAX_RESULTS, "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			
			int inserted = 0;

			if (response != null) {
				ObjStore<INFINIDATFileSystem> store = ObjStoreHelper.getStore(INFINIDATFileSystem.class);
				long count = store.delete("accountName == '" + this.accountName + "'");
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getFileSystemSummary ####---- FILESYSTEMS DELETED = " + count + " ENTRIES");}
				
				int filesystemCount = myJson.getElementCountAsInt(response,"result");
				for( int i = 0; i < filesystemCount; i++ ) {
					int poolid = myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".pool_id"));
					INFINIDATFileSystem newEvent = new INFINIDATFileSystem(
						this.accountName,
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".id")),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".nfs_filesystem_id")),
						poolid,
						getPoolNameFromID(poolid),
						myJson.getExactSingleItemAsString(response, "result", i + ".name"),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".num_blocks")),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".size")),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".ssd_enabled")),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".parent_id")),
						myJson.getExactSingleItemAsString(response, "result", i + ".type"),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".used")),
						myJson.getExactSingleItemAsString(response, "result", i + ".dataset_type"),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".data")),
						myJson.getExactSingleItemAsString(response, "result", i + ".provtype"),
						myJson.getExactSingleItemAsString(response, "result", i + ".root_mode"),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".write_protected")),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".mapped")),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".created_at")),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".updated_at")),
						INFINIDATConstants.INFINIDAT_NEW_ITEM_COMMENT
					);
					
					store.insert( newEvent );
					inserted += 1;
				}
				this.intFileSystemCount = inserted;
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getFileSystemSummary ####---- FILESYSTEMS INSERTED = " + inserted);}

				if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getFileSystemSummary ####---- # INFINIDAT FILESYSTEM INVENTORY COLLECTION COMPLETE : " + timer.stop());}
			}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getFileSystemSummary ####---- ERROR ", e);}
		}
	}
	private void getExports(String url) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getExports ####----");}}
		try {
			myTimer timer = new myTimer();
			timer.start("getExports");
			String payload = "";
			
			String response = getResponse(url, "api/rest/exports?page_size=" + INFINIDATConstants.INFINIDAT_HTTP_REQUEST_MAX_RESULTS, "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}

			int exportsInserted = 0;
			int permissionsInserted = 0;
			
			if (response != null) {
				ObjStore<INFINIDATExports> exportStore = ObjStoreHelper.getStore(INFINIDATExports.class);
				ObjStore<INFINIDATPermissions> permissionStore = ObjStoreHelper.getStore(INFINIDATPermissions.class);
				long exportsCount = exportStore.delete("accountName == '" + this.accountName + "'");
				long permissionsCount = permissionStore.delete("accountName == '" + this.accountName + "'");

				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getExports ####---- EXPORTS DELETED = " + exportsCount + " ENTRIES");}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getExports ####---- PERMISSIONS DELETED = " + permissionsCount + " ENTRIES");}
				
				exportsCount = myJson.getElementCountAsInt(response,"result");
				for( int i = 0; i < exportsCount; i++ ) {
					int fid = myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".filesystem_id"));
					INFINIDATExports newExport = new INFINIDATExports(
						this.accountName,
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".id")),
						fid,
						getFileSystemNameByID(fid),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".enabled")),
						myJson.getExactSingleItemAsString(response, "result", i + ".export_path"),
						myJson.getExactSingleItemAsString(response, "result", i + ".inner_path"),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".anonymous_uid")),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".anonymous_gid")),
						myJson.getExactSingleItemAsString(response, "result", i + ".transport_protocols"),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".max_read")),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".max_write")),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".pref_read")),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".pref_write")),
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".pref_readdir")),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".privileged_port")),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".make_all_users_anonymous")),
						myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".32bit_file_id")),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".created_at")),
						myFormat.safeStringToLong(myJson.getExactSingleItemAsString(response, "result", i + ".updated_at")),
						INFINIDATConstants.INFINIDAT_NEW_ITEM_COMMENT
					);
					
					exportStore.insert( newExport );
					exportsInserted += 1;
					if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getExports ####---- NEW EXPORT SAVED");}
					
					permissionsCount = myJson.getElementCountAsInt(response, "result." + i + ".permissions");
					for(int j = 0; j < permissionsCount; j++) {
						INFINIDATPermissions newPermission = new INFINIDATPermissions(
							this.accountName,
							fid,
							myJson.getExactSingleItemAsString(response, "result", i + ".permissions." + j + ".access"),
							myJson.getExactSingleItemAsString(response, "result", i + ".permissions." + j + ".client"),
							myFormat.safeStringToBoolean(myJson.getExactSingleItemAsString(response, "result", i + ".permissions." + j + ".no_root_squash"))
						);
						permissionStore.insert( newPermission );
						permissionsInserted += 1;
						if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getExports ####---- NEW PERMISSION SAVED");}
					}
				}
				
				this.intExportsCount = exportsInserted;
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getExports ####---- FILESYSTEM EXPORTS INSERTED = " + exportsInserted);}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getExports ####---- FILESYSTEM PERMISSIONS INSERTED = " + permissionsInserted);}

				if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getExports ####---- # INFINIDAT EXPORT & PERMISSION INVENTORY COLLECTION COMPLETE : " + timer.stop());}
			}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getExports ####---- ERROR ", e);}
		}
	}
	private void getUsers(String url) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){if(INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:getUsers ####----");}}
		try {
			myTimer timer = new myTimer();
			timer.start("getUsers");
			String payload = "";
			int usersInserted = 0;
			
			String response = getResponse(url, "api/rest/users?page_size=" + INFINIDATConstants.INFINIDAT_HTTP_REQUEST_MAX_RESULTS, "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}

			if (response != null) {
				ObjStore<INFINIDATUsers> usersStore = ObjStoreHelper.getStore(INFINIDATUsers.class);
				long usersCount = usersStore.delete("accountName == '" + this.accountName + "'");
				
				if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getUsers ####---- USERS DELETED = " + usersCount + " ENTRIES");}
				
				usersCount = myJson.getElementCountAsInt(response,"result");
				for( int i = 0; i < usersCount; i++ ) {
					INFINIDATUsers newUser = new INFINIDATUsers(
						this.accountName,
						myFormat.safeStringToInt(myJson.getExactSingleItemAsString(response, "result", i + ".id")),
						myJson.getExactSingleItemAsString(response, "result", i + ".name"),
						myJson.getElementArrayAsString(response, "result", i + ".roles"),
						myJson.getExactSingleItemAsString(response, "result", i + ".role"),
						myJson.getExactSingleItemAsString(response, "result", i + ".type"),
						myJson.getExactSingleItemAsString(response, "result", i + ".email"),
						INFINIDATConstants.INFINIDAT_NEW_ITEM_COMMENT
					);
					
					usersStore.insert( newUser );
					usersInserted += 1;
					if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:getUsers ####---- NEW USER SAVED");}
				}
				
				this.localUsersCount = usersInserted;
				if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:getUsers ####---- USERS INSERTED = " + usersInserted);}

				if(INFINIDATConstants.DEBUG){logger.info("---#### INFINIDATFunctions:getUsers ####---- # INFINIDAT USERS INVENTORY COLLECTION COMPLETE : " + timer.stop());}
			}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:getUsers ####---- ERROR ", e);}
		}
	}
	
	// INVENTORY INFO
	public String getDeviceModel( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getDeviceModel ####----" );}
		ObjStore<INFINIDATDeviceInfo> store = ObjStoreHelper.getStore( INFINIDATDeviceInfo.class );
		String query = "accountName == '" + accountName + "'";
		List<INFINIDATDeviceInfo> summaryList = store.query( query ); 
		if( summaryList.isEmpty() == false ) {
			INFINIDATDeviceInfo infinidatDeviceInfo = summaryList.get( 0 );
			return infinidatDeviceInfo.getModel();
		} else {
			return "VMWARE";
		}
	}
	public String getDeviceVersion( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getDeviceVersion ####----" );}
		ObjStore<INFINIDATDeviceInfo> store = ObjStoreHelper.getStore( INFINIDATDeviceInfo.class );
		String query = "accountName == '" + accountName + "'";
		List<INFINIDATDeviceInfo> summaryList = store.query( query ); 
		if( summaryList.isEmpty() == false ) {
			INFINIDATDeviceInfo infinidatDeviceInfo = summaryList.get( 0 );
			return infinidatDeviceInfo.getFWVersion();
		} else {
			return "2.2.2.1";
		}
	}
	public String getDeviceGlobalID( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getDeviceGlobalID ####----" );}
		ObjStore<INFINIDATDeviceInfo> store = ObjStoreHelper.getStore( INFINIDATDeviceInfo.class );
		String query = "accountName == '" + accountName + "'";
		List<INFINIDATDeviceInfo> summaryList = store.query( query ); 
		if( summaryList.isEmpty() == false ) {
			INFINIDATDeviceInfo INFINIDATDeviceInfo = summaryList.get( 0 );
			return INFINIDATDeviceInfo.getGlobalID();
		} else {
			return null;
		}
	}
	
	// VOLUMES
	public void refreshVolumes() {
		logger.info("----#### INFINIDATFunctions:refreshVolumes ####---- ");
		try {
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			this.getVolumesSummary(baseURL);
		} catch (Exception e) {
			logger.info("----#### INFINIDATFunctions:refreshVolumes ####---- ERROR: " + e.getMessage());
		}
	}
	public String createNewVolume(String volumeName, String volumeSizeUnit, String volumeSize, String volumeProvision, String Poolid, boolean ssd) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:createNewVolume ####----" );}
		try {
			volumeName = volumeName.trim();
			volumeSizeUnit = volumeSizeUnit.trim();
			volumeSize = volumeSize.trim();
			volumeProvision = volumeProvision.trim();
			Poolid = Poolid.trim();
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- VOLUME NAME: " + volumeName );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- VOLUME UNIT: " + volumeSizeUnit );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- VOLUME SIZE: " + volumeSize);}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- VOLUME PROVISION: " + volumeProvision);}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- POOL ID: " + Poolid );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- SSD: " + ssd );}
			
			String result = "";
			String tmp = "{";
			tmp +=       "\"pool_id\": " + myFormat.safeStringToInt(Poolid) + ","; 
			tmp +=       "\"provtype\": \"" + myFormat.stringToUpperCase(volumeProvision)+ "\","; 
			tmp +=       "\"name\": \""+volumeName+"\","; 
			tmp +=       "\"size\": " + myFormat.longFromHumanReadable(volumeSizeUnit, volumeSize, false) + ",";
			tmp +=       "\"ssd_enabled\": " + ssd;
			tmp +=       "}";
			
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- SEND DATA: " + tmp );}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/volumes", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SERIAL:" + myJson.getExactSingleItemAsString(response, "result", "serial") + " | ";
					result += "BLOCKS:" + myJson.getExactSingleItemAsString(response, "result", "num_blocks") + " | ";
					result += "ALLOCATED:" + myJson.getExactSingleItemAsString(response, "result", "allocated") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "PARENTID:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "DATASET:" + myJson.getExactSingleItemAsString(response, "result", "dataset_type") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "result", "data") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "USED:" + myJson.getExactSingleItemAsString(response, "result", "used") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewVolume ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String deleteVolume(String volumeID) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:deleteVolume ####----" );}
		try {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteVolume ####---- " + this.credential.getLogin());}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteVolume ####---- " + volumeID);}
			
			String result = "";
			String tmp = null;
			
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteVolume ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteVolume ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/volumes/" + volumeID + "?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteVolume ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteVolume ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SERIAL:" + myJson.getExactSingleItemAsString(response, "result", "serial") + " | ";
					result += "BLOCKS:" + myJson.getExactSingleItemAsString(response, "result", "num_blocks") + " | ";
					result += "ALLOCATED:" + myJson.getExactSingleItemAsString(response, "result", "allocated") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "PARENTID:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "DATASET:" + myJson.getExactSingleItemAsString(response, "result", "dataset_type") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "result", "data") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteVolume ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteVolume ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteVolume ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String getAccountNameFromVolumeID(String id) {
		try {
			logger.info( "----#### INFINIDATFuncitons:getAccountNameFromVolumeID ####---- VOLUME ID: " + id );
			if(id.isEmpty() || id == null || id.equals("")) {
				logger.info( "----#### INFINIDATFuncitons:getAccountNameFromVolumeID ####---- VOLUME ID IS BLANK" );
				return null;
			} else {
				ObjStore<INFINIDATVolume> store = ObjStoreHelper.getStore(INFINIDATVolume.class);
				String query = "id == " + id;
				List<INFINIDATVolume> objs = store.query(query);
				logger.info( "----#### INFINIDATFuncitons:getAccountNameFromVolumeID ####---- VOLUMES FOUND: " + objs.size());
				if(objs.size() == 1) {
					INFINIDATVolume pojo = objs.get(0);
					return pojo.getAccountName();
				}
			}
			return null;
		} catch(Exception e) {
			logger.info( "----#### INFINIDATFuncitons:getAccountNameFromVolumeID ####---- ERROR: " + e.getMessage());
			return null;
		}
	}
	public String cloneVolumeByID(String vid, String name) {
		try {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeByID ####---- VOLUME ID: " + vid);}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeByID ####---- VOLUME NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"parent_id\": " + vid + ",";
			tmp += "\"name\": \"" + name + "\"";
			tmp += "}";
			
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeByID ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeByID ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/volumes", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeByID ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "VOLUMECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "volumes_count") + " | ";
					result += "MAXEXTEND:" + myJson.getExactSingleItemAsString(response, "result", "max_extend") + " | ";
					result += "ALLOCATED:" + myJson.getExactSingleItemAsString(response, "result", "allocated_physical_space") + " | ";
					result += "RESERVEDCAPACITY:" + myJson.getExactSingleItemAsString(response, "result", "reserved_capacity") + " | ";
					result += "FILESYSTEMCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystems_count") + " | ";
					result += "FILESYSTEMCLONECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_clones_count") + " | ";
					result += "FILESYSTEMSNAPCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_snapshots_count") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "CLONECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "clones_count") + " | ";
					result += "SNAPCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "snapshots_count") + " | ";
					result += "STATE:" + myJson.getExactSingleItemAsString(response, "result", "state") + " | ";
					result += "FREEPHYSICAL:" + myJson.getExactSingleItemAsString(response, "result", "free_physical_space") + " | ";
					result += "FREEVIRTUAL:" + myJson.getExactSingleItemAsString(response, "result", "free_virtual_space") + " | ";
					result += "PHYSICAL:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity") + " | ";
					result += "VIRTUAL:" + myJson.getExactSingleItemAsString(response, "result", "virtual_capacity") + " | ";
					result += "WARNING:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity_warning") + " | ";
					result += "CRITICAL:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity_critical") + " | ";
					result += "USED:" + myJson.getExactSingleItemAsString(response, "result", "used") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeByID ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:cloneVolumeByID ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:cloneVolumeByID ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String getVolumeInfoFromID(String id) {
		try {
			logger.info( "----#### INFINIDATFunctions:getVolumeInfoFromID ####---- VOLUME ID: " + id );
			if(id.isEmpty() || id == null || id.equals("")) {
				logger.info( "----#### INFINIDATFunctions:getVolumeInfoFromID ####---- VOLUME ID IS BLANK" );
				return null;
			} else {
				ObjStore<INFINIDATVolume> store = ObjStoreHelper.getStore(INFINIDATVolume.class);
				String query = "id == " + id;
				List<INFINIDATVolume> objs = store.query(query);
				logger.info( "----#### INFINIDATFunctions:getVolumeInfoFromID ####---- VOLUME FOUND: " + objs.size());
				if(objs.size() == 1) {
					INFINIDATVolume pojo = objs.get(0);
					return pojo.getName() + " | " + pojo.getPoolID() + " | " + pojo.getVolumeProvType() + " | " + pojo.getSize() + " | " + pojo.getSSDEnabled();
				}
			}
			return null;
		} catch(Exception e) {
			logger.info( "----#### INFINIDATFunctions:getVolumeInfoFromID ####---- ERROR: " + e.getMessage());
			return null;
		}
	}
	public String editVolumeByID(String vid, String object, String value) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:editVolumeByID ####----" );}
		try {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- VOLUME ID: " + vid);}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- OBJECT: " + object);}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- VALUE: " + value);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"" + object + "\": \"" + value + "\"";
			tmp += "}";
			
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/volumes/"+vid, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SERIAL:" + myJson.getExactSingleItemAsString(response, "result", "serial") + " | ";
					result += "BLOCKS:" + myJson.getExactSingleItemAsString(response, "result", "num_blocks") + " | ";
					result += "ALLOCATED:" + myJson.getExactSingleItemAsString(response, "result", "allocated") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "PARENTID:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "DATASET:" + myJson.getExactSingleItemAsString(response, "result", "dataset_type") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "result", "data") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "USED:" + myJson.getExactSingleItemAsString(response, "result", "used") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- Error: " + ex);}
			return null;
		}
	}
	public String moveVolumeByID(String vid, int value) {
		try {
			logger.info("----#### INFINIDATFunctions:moveVolumeByID ####---- VOLUME ID: " + vid);
			logger.info("----#### INFINIDATFunctions:moveVolumeByID ####---- VALUE: " + value);
			
			String result = "";
			String tmp = "{";
			tmp += "\"pool_id\": " + value + ",";
			tmp += "\"with_capacity\": false";
			tmp += "}";

			logger.info("----#### INFINIDATFunctions:moveVolumeByID ####---- SEND DATA: " + tmp);
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:moveVolumeByID ####---- GOT CREDENTIALS");
					
			String response = getResponse(baseURL, "api/rest/volumes/"+vid+"/move", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			logger.info("----#### INFINIDATFunctions:moveVolumeByID ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
	
				logger.info("----#### INFINIDATFunctions:moveVolumeByID ####---- " + response );
				
				result = "CODE:" + this.lastResponseCode;
				
				logger.info("----#### INFINIDATFunctions:moveVolumeByID ####---- " + result );
			} else {
				logger.info("----#### INFINIDATFunctions:moveVolumeByID ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:moveVolumeByID ####---- Error: " + ex);
			return null;
		}
	}
	public String editVolumeByID(String vid, String object, long value) {
		try {
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- VOLUME ID: " + vid);
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- OBJECT: " + object);
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- VALUE: " + value);
			
			String result = "";
			String tmp = "{";
			tmp += "\"" + object + "\": " + value;
			tmp += "}";
			
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- SEND DATA: " + tmp);
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/volumes/"+vid, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
	
				logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- " + response );
				
				result = "CODE:" + this.lastResponseCode;
				
				logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- " + result );
			} else {
				logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- Error: " + ex);
			return null;
		}
	}
	public String editVolumeByID(String vid, String object, boolean value) {
		try {
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- VOLUME ID: " + vid);
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- OBJECT: " + object);
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- VALUE: " + value);
			
			String result = "";
			String tmp = "{";
			tmp += "\"" + object + "\": " + value;
			tmp += "}";
			
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- SEND DATA: " + tmp);
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/volumes/"+vid, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
	
				logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- " + response );
				
				result = "CODE:" + this.lastResponseCode;
				
				logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- " + result );
			} else {
				logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:editVolumeByID ####---- Error: " + ex);
			return null;
		}
	}
	
	// FILESYSTEMS
	public void refreshFileSystems() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:refreshFileSystems ####---- ");}
		try {
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			this.getFileSystemSummary(baseURL);
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:refreshFileSystems ####---- ERROR: " + e.getMessage());}
		}
	}
	public String createNewFileSystem(String name, String format, String size, String provtype, String id, boolean ssd) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewFileSystem ####---- NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp +=       "\"atime_mode\": \"NOATIME\",";
			tmp +=       "\"pool_id\": " + myFormat.safeStringToInt(id) + ",";
			tmp +=       "\"name\": \""+name+"\","; 
			tmp +=       "\"provtype\": \"" + provtype + "\",";
			tmp +=       "\"size\": " + myFormat.longFromHumanReadable(format, size, false) + ",";
			tmp +=       "\"ssd_enabled\": " + ssd;
			tmp +=       "}";
			
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewFileSystem ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:createNewFileSystem ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/filesystems", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewFileSystem ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewFileSystem ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "PARENT:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SECURITY:" + myJson.getExactSingleItemAsString(response, "result", "root_mode") + " | ";
					result += "NFSID:" + myJson.getExactSingleItemAsString(response, "result", "nfs_filesystem_id") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewFileSystem ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewFileSystem ####---- RESPONSE IS NULL!!" );}
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewFileSystem ####---- Error: " + ex.getMessage());}
			return null;
		}
	}
	public String deleteFileSystem(String id) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystem ####---- HOST ID: " + id );}
			
			String result = "";
			String tmp = null;

			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystem ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:deleteFileSystem ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/filesystems/" + id + "?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystem ####---- " + response );}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystem ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | "; 
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "PARENT:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SECURITY:" + myJson.getExactSingleItemAsString(response, "result", "root_mode") + " | ";
					result += "NFSID:" + myJson.getExactSingleItemAsString(response, "result", "nfs_filesystem_id") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystem ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteFileSystem ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteFileSystem ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String getAccountNameFromFileSystemID(String id) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getAccountNameFromHostID ####---- HOST ID: " + id );}
			if(id.isEmpty() || id == null || id.equals("")) {
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getAccountNameFromHostID ####---- HOST ID IS BLANK" );}
				return null;
			} else {
				ObjStore<INFINIDATFileSystem> store = ObjStoreHelper.getStore(INFINIDATFileSystem.class);
				String query = "id == " + id;
				List<INFINIDATFileSystem> objs = store.query(query);
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getAccountNameFromHostID ####---- HOST FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATFileSystem pojo = objs.get(0);
					return pojo.getAccountName();
				}
			}
			return null;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getAccountNameFromHostID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String editFileSystemByID(String fid, String object, String value) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- VOLUME ID: " + fid);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- OBJECT: " + object);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- VALUE: " + value);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"" + object + "\": \"" + value + "\"";
			tmp += "}";
			
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/filesystems/"+fid, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | "; 
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "PARENT:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SECURITY:" + myJson.getExactSingleItemAsString(response, "result", "root_mode") + " | ";
					result += "NFSID:" + myJson.getExactSingleItemAsString(response, "result", "nfs_filesystem_id") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- Error: " + ex);}
			return null;
		}
	}
	public String editFileSystemByID(String fid, String object, boolean value) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- VOLUME ID: " + fid);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- OBJECT: " + object);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- VALUE: " + value);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"" + object + "\": \"" + value + "\"";
			tmp += "}";
			
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- SEND DATA: " + tmp);}	
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/filesystems/"+fid, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | "; 
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "PARENT:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SECURITY:" + myJson.getExactSingleItemAsString(response, "result", "root_mode") + " | ";
					result += "NFSID:" + myJson.getExactSingleItemAsString(response, "result", "nfs_filesystem_id") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- Error: " + ex);}
			return null;
		}
	}
	public String editFileSystemByID(String fid, String object, long value) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- VOLUME ID: " + fid);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- OBJECT: " + object);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- VALUE: " + value);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"" + object + "\": \"" + value + "\"";
			tmp += "}";
			
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/filesystems/"+fid, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- " + response );}
			
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | "; 
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "PARENT:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SECURITY:" + myJson.getExactSingleItemAsString(response, "result", "root_mode") + " | ";
					result += "NFSID:" + myJson.getExactSingleItemAsString(response, "result", "nfs_filesystem_id") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editFileSystemByID ####---- Error: " + ex);}
			return null;
		}
	}
	public String createFileSystemSnapshot(String fid, String name) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemSnapshot ####---- VOLUME ID: " + fid);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemSnapshot ####---- VOLUME NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"parent_id\": " + fid + ",";
			tmp += "\"name\": \"" + name + "\"";
			tmp += "}";
			
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemSnapshot ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:createFileSystemSnapshot ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/filesystems", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemSnapshot ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemSnapshot ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "PARENT:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SECURITY:" + myJson.getExactSingleItemAsString(response, "result", "root_mode") + " | ";
					result += "NFSID:" + myJson.getExactSingleItemAsString(response, "result", "nfs_filesystem_id") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemSnapshot ####---- RESULT: " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createFileSystemSnapshot ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createFileSystemSnapshot ####---- Error: " + e.getMessage());}
			return null;
		}
	}	
	public String restoreFileSystem(String fid) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:restoreFileSystem ####---- FILESYSTEM ID: " + fid);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"source_id\": " + fid;
			tmp += "}";

			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:restoreFileSystem ####---- SEND DATA: " + tmp);}
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:restoreFileSystem ####---- GOT CREDENTIALS");}
					
			String response = getResponse(baseURL, "api/rest/filesystems/"+fid+"/move", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:restoreFileSystem ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
	
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:restoreFileSystem ####---- " + response );}
				
				result = "CODE:" + this.lastResponseCode;
				
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:restoreFileSystem ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:restoreFileSystem ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:restoreFileSystem ####---- Error: " + ex);}
			return null;
		}
	}
	public String getFileSystemInfoByID(String id) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getFileSystemInfoByID ####---- FILESYSTEM ID: " + id );}
			if(id.isEmpty() || id == null || id.equals("")) {
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getFileSystemInfoByID ####---- FILESYSTEM ID IS BLANK" );}
				return null;
			} else {
				ObjStore<INFINIDATFileSystem> store = ObjStoreHelper.getStore(INFINIDATFileSystem.class);
				String query = "id == " + id;
				List<INFINIDATFileSystem> objs = store.query(query);
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getFileSystemInfoByID ####---- VOLUME FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATFileSystem pojo = objs.get(0);
					return pojo.getName() + " | " + pojo.getPoolID() + " | " + pojo.getProvType() + " | " + pojo.getSize() + " | " + pojo.getSSD();
				}
			}
			return null;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getFileSystemInfoByID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String getFileSystemNameByID(int id) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getFileSystemInfoByID ####---- FILESYSTEM ID: " + id );}
			if(id < 1) {
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getFileSystemInfoByID ####---- FILESYSTEM ID IS BLANK OR LESS THAN 1" );}
				return null;
			} else {
				ObjStore<INFINIDATFileSystem> store = ObjStoreHelper.getStore(INFINIDATFileSystem.class);
				String query = "id == " + id;
				List<INFINIDATFileSystem> objs = store.query(query);
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getFileSystemInfoByID ####---- VOLUME FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATFileSystem pojo = objs.get(0);
					return pojo.getName();
				}
			}
			return null;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getFileSystemInfoByID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String moveFileSystemByID(String fid, int pool) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:moveFileSystemByID ####---- FILESYSTEM ID: " + fid);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:moveFileSystemByID ####---- POOL ID: " + pool);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"pool_id\": " + pool + ",";
			tmp += "\"with_capacity\": false";
			tmp += "}";

			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:moveFileSystemByID ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:moveFileSystemByID ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/filesystems/"+fid+"/move", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:moveFileSystemByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:moveFileSystemByID ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "PARENT:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SECURITY:" + myJson.getExactSingleItemAsString(response, "result", "root_mode") + " | ";
					result += "NFSID:" + myJson.getExactSingleItemAsString(response, "result", "nfs_filesystem_id") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:moveFileSystemByID ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:moveFileSystemByID ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:moveFileSystemByID ####---- Error: " + ex);}
			return null;
		}
	}
	public String cloneFileSystemSnapshot(String vid, String name) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneFileSystemSnapshot ####---- VOLUME ID: " + vid);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneFileSystemSnapshot ####---- VOLUME NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"parent_id\": " + vid + ",";
			tmp += "\"name\": \"" + name + "\"";
			tmp += "}";
			
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneFileSystemSnapshot ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneFileSystemSnapshot ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/filesystems", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneFileSystemSnapshot ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneFileSystemSnapshot ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "PARENT:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SECURITY:" + myJson.getExactSingleItemAsString(response, "result", "root_mode") + " | ";
					result += "NFSID:" + myJson.getExactSingleItemAsString(response, "result", "nfs_filesystem_id") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneFileSystemSnapshot ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:cloneFileSystemSnapshot ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:cloneFileSystemSnapshot ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	
	// FILESYSTEM EXPORTS
	public String createFileSystemExport(String id, String name) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemExport ####---- ID: " + id);}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemExport ####---- NAME: " + name);}
			
			if(!name.substring(0,1).equals("/")){
				name = "/" + name; 
			}
			
			String result = "";
			String tmp = "{";
			tmp +=       "\"inner_path\": \"/\",";
			tmp +=       "\"pref_write\": " + 16384 + ",";
			tmp +=       "\"pref_read\": " + 16384 + ","; 
			tmp +=       "\"max_read\": " + 32768 + ","; 
			tmp +=       "\"pref_readdir\": " + 16384 + ",";
			tmp +=       "\"transport_protocols\": \"TCP\",";
			tmp +=       "\"filesystem_id\": " + id + ",";
			tmp +=       "\"max_write\": " + 32768 + ",";
			tmp +=       "\"privileged_port\": false,";
			tmp +=       "\"export_path\": \"" + name + "\"";
			tmp +=       "}";
			
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemExport ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:createFileSystemExport ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/exports", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createFileSystemExport ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemExport ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "FILEID:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_id") + " | ";
					result += "INNERPATH:" + myJson.getExactSingleItemAsString(response, "result", "inner_path") + " | ";
					result += "PERFWRITE:" + myJson.getExactSingleItemAsString(response, "result", "pref_write") + " | ";
					result += "BITFILE:" + myJson.getExactSingleItemAsString(response, "result", "32bit_file_id") + " | ";
					result += "PERFREAD:" + myJson.getExactSingleItemAsString(response, "result", "pref_read") + " | ";
					result += "MAXREAD:" + myJson.getExactSingleItemAsString(response, "result", "max_read") + " | ";
					result += "PERFREADDIR:" + myJson.getExactSingleItemAsString(response, "result", "pref_readdir") + " | ";
					result += "ENABLED:" + myJson.getExactSingleItemAsString(response, "result", "enabled") + " | ";
					result += "ALLUSERS:" + myJson.getExactSingleItemAsString(response, "result", "make_all_users_anonymous") + " | ";
					result += "TRANSPORT:" + myJson.getExactSingleItemAsString(response, "result", "transport_protocols") + " | ";
					result += "ANONYMOUSUID:" + myJson.getExactSingleItemAsString(response, "result", "anonymous_uid") + " | ";
					result += "ANONYMOUSGID:" + myJson.getExactSingleItemAsString(response, "result", "anonymous_gid") + " | ";
					result += "MAXWRITE:" + myJson.getExactSingleItemAsString(response, "result", "max_write") + " | ";
					result += "PORT:" + myJson.getExactSingleItemAsString(response, "result", "privileged_port") + " | ";
					result += "PATH:" + myJson.getExactSingleItemAsString(response, "result", "export_path") + " | ";
					result += "CREATED:" + myJson.getExactSingleItemAsString(response, "result", "created_at") + " | ";
					result += "UPDATED:" + myJson.getExactSingleItemAsString(response, "result", "updated_at") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createFileSystemExport ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createFileSystemExport ####---- RESPONSE IS NULL!!" );}
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createFileSystemExport ####---- Error: " + ex.getMessage());}
			return null;
		}
	}
	public void refreshExports() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:refreshExports ####---- ");}
		try {
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			this.getExports(baseURL);
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:refreshExports ####---- ERROR: " + e.getMessage());}
		}
	}
	public String deleteFileSystemExport(String id) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystemExport ####---- ID: " + id );}
			
			String result = "";
			String tmp = null;

			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystemExport ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:deleteFileSystemExport ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/exports/" + id + "?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystemExport ####---- " + response );}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystemExport ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "FILEID:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_id") + " | ";
					result += "INNERPATH:" + myJson.getExactSingleItemAsString(response, "result", "inner_path") + " | ";
					result += "PERFWRITE:" + myJson.getExactSingleItemAsString(response, "result", "pref_write") + " | ";
					result += "BITFILE:" + myJson.getExactSingleItemAsString(response, "result", "32bit_file_id") + " | ";
					result += "PERFREAD:" + myJson.getExactSingleItemAsString(response, "result", "pref_read") + " | ";
					result += "MAXREAD:" + myJson.getExactSingleItemAsString(response, "result", "max_read") + " | ";
					result += "PERFREADDIR:" + myJson.getExactSingleItemAsString(response, "result", "pref_readdir") + " | ";
					result += "ENABLED:" + myJson.getExactSingleItemAsString(response, "result", "enabled") + " | ";
					result += "ALLUSERS:" + myJson.getExactSingleItemAsString(response, "result", "make_all_users_anonymous") + " | ";
					result += "TRANSPORT:" + myJson.getExactSingleItemAsString(response, "result", "transport_protocols") + " | ";
					result += "ANONYMOUSUID:" + myJson.getExactSingleItemAsString(response, "result", "anonymous_uid") + " | ";
					result += "ANONYMOUSGID:" + myJson.getExactSingleItemAsString(response, "result", "anonymous_gid") + " | ";
					result += "MAXWRITE:" + myJson.getExactSingleItemAsString(response, "result", "max_write") + " | ";
					result += "PORT:" + myJson.getExactSingleItemAsString(response, "result", "privileged_port") + " | ";
					result += "PATH:" + myJson.getExactSingleItemAsString(response, "result", "export_path") + " | ";
					result += "CREATED:" + myJson.getExactSingleItemAsString(response, "result", "created_at") + " | ";
					result += "UPDATED:" + myJson.getExactSingleItemAsString(response, "result", "updated_at") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteFileSystemExport ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteFileSystemExport ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteFileSystemExport ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String getExportInfoByID(String id) {
		try {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getExportInfoByID ####---- EXPORT ID: " + id );}
			if(id.isEmpty() || id == null || id.equals("")) {
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getExportInfoByID ####---- EXPORT ID IS BLANK" );}
				return null;
			} else {
				ObjStore<INFINIDATExports> store = ObjStoreHelper.getStore(INFINIDATExports.class);
				String query = "id == " + id;
				List<INFINIDATExports> objs = store.query(query);
				if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getExportInfoByID ####---- EXPORT FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATExports pojo = objs.get(0);
					return pojo.getExportPath() + " | " + pojo.getAnonymousUID() + " | " + pojo.getAnonymousGID() + " | " + pojo.getAllUsersAnonymous() + " | " + pojo.getPrivilegedPort() + " | " + pojo.getMaxRead() + " | " + pojo.getMaxWrite() + " | " + pojo.getPerfRead() + " | " + pojo.getPerfWrite() + " | " + pojo.getPerfReadDir();
				}
			}
			return null;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getExportInfoByID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String editExportByID(String id, String send) {
		try {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editExportByID ####---- ID: " + id);}
			
			String result = "";
			String tmp = send;
			
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editExportByID ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editExportByID ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/exports/"+id, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editExportByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editExportByID ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "FILEID:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_id") + " | ";
					result += "INNERPATH:" + myJson.getExactSingleItemAsString(response, "result", "inner_path") + " | ";
					result += "PERFWRITE:" + myJson.getExactSingleItemAsString(response, "result", "pref_write") + " | ";
					result += "BITFILE:" + myJson.getExactSingleItemAsString(response, "result", "32bit_file_id") + " | ";
					result += "PERFREAD:" + myJson.getExactSingleItemAsString(response, "result", "pref_read") + " | ";
					result += "MAXREAD:" + myJson.getExactSingleItemAsString(response, "result", "max_read") + " | ";
					result += "PERFREADDIR:" + myJson.getExactSingleItemAsString(response, "result", "pref_readdir") + " | ";
					result += "ENABLED:" + myJson.getExactSingleItemAsString(response, "result", "enabled") + " | ";
					result += "ALLUSERS:" + myJson.getExactSingleItemAsString(response, "result", "make_all_users_anonymous") + " | ";
					result += "TRANSPORT:" + myJson.getExactSingleItemAsString(response, "result", "transport_protocols") + " | ";
					result += "ANONYMOUSUID:" + myJson.getExactSingleItemAsString(response, "result", "anonymous_uid") + " | ";
					result += "ANONYMOUSGID:" + myJson.getExactSingleItemAsString(response, "result", "anonymous_gid") + " | ";
					result += "MAXWRITE:" + myJson.getExactSingleItemAsString(response, "result", "max_write") + " | ";
					result += "PORT:" + myJson.getExactSingleItemAsString(response, "result", "privileged_port") + " | ";
					result += "PATH:" + myJson.getExactSingleItemAsString(response, "result", "export_path") + " | ";
					result += "CREATED:" + myJson.getExactSingleItemAsString(response, "result", "created_at") + " | ";
					result += "UPDATED:" + myJson.getExactSingleItemAsString(response, "result", "updated_at") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editExportByID ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editExportByID ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editExportByID ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String deleteExportPermission(String id, String permission) {
		try {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteExportPermission ####---- PERMISSION ID: " + id );}
			
			String result = "";
			String tmp = null;

			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteExportPermission ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:deleteExportPermission ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/exports/" + id, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteExportPermission ####---- " + response );}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteExportPermission ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "FILEID:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_id") + " | ";
					result += "INNERPATH:" + myJson.getExactSingleItemAsString(response, "result", "inner_path") + " | ";
					result += "PERFWRITE:" + myJson.getExactSingleItemAsString(response, "result", "pref_write") + " | ";
					result += "BITFILE:" + myJson.getExactSingleItemAsString(response, "result", "32bit_file_id") + " | ";
					result += "PERFREAD:" + myJson.getExactSingleItemAsString(response, "result", "pref_read") + " | ";
					result += "MAXREAD:" + myJson.getExactSingleItemAsString(response, "result", "max_read") + " | ";
					result += "PERFREADDIR:" + myJson.getExactSingleItemAsString(response, "result", "pref_readdir") + " | ";
					result += "ENABLED:" + myJson.getExactSingleItemAsString(response, "result", "enabled") + " | ";
					result += "ALLUSERS:" + myJson.getExactSingleItemAsString(response, "result", "make_all_users_anonymous") + " | ";
					result += "TRANSPORT:" + myJson.getExactSingleItemAsString(response, "result", "transport_protocols") + " | ";
					result += "ANONYMOUSUID:" + myJson.getExactSingleItemAsString(response, "result", "anonymous_uid") + " | ";
					result += "ANONYMOUSGID:" + myJson.getExactSingleItemAsString(response, "result", "anonymous_gid") + " | ";
					result += "MAXWRITE:" + myJson.getExactSingleItemAsString(response, "result", "max_write") + " | ";
					result += "PORT:" + myJson.getExactSingleItemAsString(response, "result", "privileged_port") + " | ";
					result += "PATH:" + myJson.getExactSingleItemAsString(response, "result", "export_path") + " | ";
					result += "CREATED:" + myJson.getExactSingleItemAsString(response, "result", "created_at") + " | ";
					result += "UPDATED:" + myJson.getExactSingleItemAsString(response, "result", "updated_at") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteExportPermission ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteExportPermission ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteExportPermission ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	
	// HOSTS
	public void refreshHosts() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:refreshHosts ####---- ");}
		try {
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			this.getHostsSummary(baseURL);
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:refreshHosts ####---- ERROR: " + e.getMessage());}
		}		
	}
	public String createNewHost(String name) {
		try {
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHost ####---- NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp +=       "\"name\": \""+name+"\""; 
			tmp +=       "}";
			
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewHost ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:createNewHost ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/hosts", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewHost ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHost ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "host_type") + " | ";
					result += "CLUSTER:" + myJson.getExactSingleItemAsString(response, "result", "host_cluster_id") + " | ";
					result += "LUNS:" + myJson.getElementCountAsInt(response,"result.luns") + " | ";
					result += "PORTS:" + myJson.getElementCountAsInt(response,"result.ports") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
		       
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHost ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewHost ####---- RESPONSE IS NULL!!" );}
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewHost ####---- Error: " + ex.getMessage());}
			return null;
		}
	}
	public String deleteHost(String id) {
		try {
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHost ####---- HOST ID: " + id );}

			String result = "";
			String tmp = null;
			
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:deleteHost ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:deleteHost ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/hosts/" + id + "?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:deleteHost ####---- " + response );}

			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHost ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "host_type") + " | ";
					result += "CLUSTER:" + myJson.getExactSingleItemAsString(response, "result", "host_cluster_id") + " | ";
					result += "LUNS:" + myJson.getElementCountAsInt(response,"result.luns") + " | ";
					result += "PORTS:" + myJson.getElementCountAsInt(response,"result.ports") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHost ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteHost ####---- RESPONSE IS NULL!!" );}
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteHost ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String getAccountNameFromHostID(String id) {
		try {
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getAccountNameFromHostID ####---- HOST ID: " + id );}
			if(id.isEmpty() || id == null || id.equals("")) {
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getAccountNameFromHostID ####---- HOST ID IS BLANK" );}
				return null;
			} else {
				ObjStore<INFINIDATHosts> store = ObjStoreHelper.getStore(INFINIDATHosts.class);
				String query = "id == " + id;
				List<INFINIDATHosts> objs = store.query(query);
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getAccountNameFromHostID ####---- HOST FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATHosts pojo = objs.get(0);
					return pojo.getAccountName();
				}
			}
			return null;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getAccountNameFromHostID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String editHostByID(String hostid, String name) {
		try {
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editHostByID ####---- ID: " + hostid);}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editHostByID ####---- NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"name\": \"" + name + "\"}";
			
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editHostByID ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editHostByID ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/hosts/"+hostid, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editHostByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editHostByID ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "host_type") + " | ";
					result += "CLUSTER:" + myJson.getExactSingleItemAsString(response, "result", "host_cluster_id") + " | ";
					result += "LUNS:" + myJson.getElementCountAsInt(response,"result.luns") + " | ";
					result += "PORTS:" + myJson.getElementCountAsInt(response,"result.ports") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editHostByID ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editHostByID ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editHostByID ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String getHostNameFromID(String id) {
		try {
			logger.info( "----#### INFINIDATFuncitons:getHostNameFromID ####---- HOST ID: " + id );
			if(id.isEmpty() || id == null || id.equals("")) {
				logger.info( "----#### INFINIDATFuncitons:getHostNameFromID ####---- HOST ID IS BLANK" );
				return null;
			} else {
				ObjStore<INFINIDATHosts> store = ObjStoreHelper.getStore(INFINIDATHosts.class);
				String query = "id == " + id;
				List<INFINIDATHosts> objs = store.query(query);
				logger.info( "----#### INFINIDATFuncitons:getHostNameFromID ####---- HOST FOUND: " + objs.size());
				if(objs.size() == 1) {
					INFINIDATHosts pojo = objs.get(0);
					return pojo.getName();
				}
			}
			return null;
		} catch(Exception e) {
			logger.info( "----#### INFINIDATFuncitons:getHostNameFromID ####---- ERROR: " + e.getMessage());
			return null;
		}
	}
	
	// HOST PORTS
	public String getAllHostPorts(String hostid) {
		logger.info("----#### INFINIDATFunctions:getAllHostPorts ####----");
		String result = "";
		try {
			logger.info("----#### INFINIDATFunctions:getAllHostPorts ####---- HOST ID: " + hostid);
			String payload = "";
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:getAllHostPorts ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/hosts/"+hostid+"/ports", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			logger.info("----#### INFINIDATFunctions:getAllHostPorts ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				logger.info("----#### INFINIDATFunctions:getAllHostPorts ####---- " + response );
				
				return response;
				
			} else {
				logger.info("----#### INFINIDATFunctions:getAllHostPorts ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- Error: " + ex);
			return null;
		}
	}
	public String createNewHostPort(String hostid, String wwpn) {
		try {
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- HOST ID: " + hostid);}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- WWPN: " + wwpn);}
			
			wwpn = wwpn.replace(":", "");
			String result = "";
			String tmp = "{";
			tmp +=       "\"type\": \"FC\","; 
			tmp +=       "\"address\": \""+wwpn+"\""; 
			tmp +=       "}";
			
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/hosts/"+hostid+"/ports", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "HOSTID:" + myJson.getExactSingleItemAsString(response, "result", "host_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "ADDRESS:" + myJson.getExactSingleItemAsString(response, "result", "address") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewHostPort ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String deleteHostPort(String id, String port) {
		try {
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- " + this.credential.getLogin() );}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- PORT: " + port );}

			String result = "";
			String tmp = null;
			
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/hosts/" + id + "/ports/FC/" + port + "?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "HOSTID:" + myJson.getExactSingleItemAsString(response, "result", "host_id") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "ADDRESS:" + myJson.getExactSingleItemAsString(response, "result", "address") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteHostPort ####---- Error: " + e.getMessage());}
			return null;
		}
	}

	// LUN TO HOST MAPPING
	public String AddLunToHost(String hostid, String lunid) {
		try {
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:AddLunToHost ####---- HOST ID: " + hostid);}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:AddLunToHost ####---- WWPN: " + lunid);}
			
			String result = "";
			String tmp = "{";
			tmp +=       "\"volume_id\": " + lunid;
			tmp +=       "}";
			
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:AddLunToHost ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:AddLunToHost ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/hosts/"+hostid+"/luns", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:AddLunToHost ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:AddLunToHost ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "HOSTCLUSTERID:" + myJson.getExactSingleItemAsString(response, "result", "host_cluster_id") + " | ";
					result += "VOLUMEID:" + myJson.getExactSingleItemAsString(response, "result", "volume_id") + " | ";
					result += "CLUSTERED:" + myJson.getExactSingleItemAsString(response, "result", "clustered") + " | ";
					result += "HOSTID:" + myJson.getExactSingleItemAsString(response, "result", "host_id") + " | ";
					result += "LUNID:" + myJson.getExactSingleItemAsString(response, "result", "lun") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
					
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:AddLunToHost ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:AddLunToHost ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:AddLunToHost ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String getAllLuns() {
		logger.info("----#### INFINIDATFunctions:getAllLuns ####----");
		String result = "";
		try {
			String payload = "";
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:getAllLuns ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/volumes", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			logger.info("----#### INFINIDATFunctions:getAllLuns ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				logger.info("----#### INFINIDATFunctions:getAllLuns ####---- " + response );
				
				return response;
				
			} else {
				logger.info("----#### INFINIDATFunctions:getAllLuns ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:getAllLuns ####---- Error: " + ex);
			return null;
		}
	}
	public String getAllMappedLuns(String hostid) {
		logger.info("----#### INFINIDATFunctions:getAllMappedLuns ####---- HOST ID: " + hostid);
		String result = "";
		try {
			String payload = "";
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:getAllMappedLuns ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/hosts/"+hostid+"/luns", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			logger.info("----#### INFINIDATFunctions:getAllMappedLuns ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				logger.info("----#### INFINIDATFunctions:getAllMappedLuns ####---- " + response );
				
				return response;
				
			} else {
				logger.info("----#### INFINIDATFunctions:getAllMappedLuns ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:getAllMappedLuns ####---- Error: " + ex);
			return null;
		}
	}
	public String DeleteLunToHost(String hostid, String lunid) {
		try {
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:DeleteLunToHost ####---- HOST ID: " + hostid);}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:DeleteLunToHost ####---- LUN ID: " + lunid);}
			
			String result = "";
			String tmp = "";
			
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:DeleteLunToHost ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:DeleteLunToHost ####---- URL: " + baseURL);}	
			String response = getResponse(baseURL, "api/rest/hosts/"+hostid+"/luns/lun/"+lunid+"?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:DeleteLunToHost ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:DeleteLunToHost ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "HOSTCLUSTERID:" + myJson.getExactSingleItemAsString(response, "result", "host_cluster_id") + " | ";
					result += "VOLUMEID:" + myJson.getExactSingleItemAsString(response, "result", "volume_id") + " | ";
					result += "CLUSTERED:" + myJson.getExactSingleItemAsString(response, "result", "clustered") + " | ";
					result += "HOSTID:" + myJson.getExactSingleItemAsString(response, "result", "host_id") + " | ";
					result += "LUNID:" + myJson.getExactSingleItemAsString(response, "result", "lun") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:DeleteLunToHost ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:DeleteLunToHost ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:DeleteLunToHost ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String getVolumeInfo(String vid) {
		logger.info("----#### INFINIDATFunctions:getVolumeInfo ####---- VOLUME ID: " + vid);
		String result = "";
		try {
			String payload = "";
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:getVolumeInfo ####---- GOT CREDENTIALS");

			String response = getResponse(baseURL, "api/rest/volumes/"+vid, "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			logger.info("----#### INFINIDATFunctions:getVolumeInfo ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				logger.info("----#### INFINIDATFunctions:getVolumeInfo ####---- " + response );
				
				return response;
				
			} else {
				logger.info("----#### INFINIDATFunctions:getVolumeInfo ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:getVolumeInfo ####---- Error: " + ex);
			return null;
		}
	}

	// POOLS
	public void refreshPools() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:refreshPools ####---- ");}
		try {
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			this.getPools(baseURL);
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:refreshPools ####---- ERROR: " + e.getMessage());}
		}		
	}
	public String createNewPool(String name, String measure, Boolean link, String pSize, String vSize) {
		return this.createNewPool(name, measure, link, pSize, vSize, true);
	}
	public String createNewPool(String name, String measure, Boolean link, String pSize, String vSize, boolean si) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewPool ####----");}
		try {
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewPool ####---- NAME: " + name);}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewPool ####---- MEASURE: " + measure);}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewPool ####---- LINK: " + link);}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewPool ####---- pSIZE: " + pSize);}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:createNewPool ####---- vSIZE: " + vSize );}
			
			String result = "";
			String tmp = "{";
			tmp +=       "\"virtual_capacity\": " + myFormat.longFromHumanReadable(measure, vSize, si) + ","; 
			tmp +=       "\"max_extend\": " + INFINIDATConstants.INFINIDAT_POOL_MAX_EXTEND + ","; 
			tmp +=       "\"name\": \""+name+"\","; 
			tmp +=       "\"physical_capacity\": " + myFormat.longFromHumanReadable(measure, pSize, si);
			tmp +=       "}";
			
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewPool ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:createNewPool ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/pools", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewPool ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewPool ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "VOLUMECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "volumes_count") + " | ";
					result += "MAXEXTEND:" + myJson.getExactSingleItemAsString(response, "result", "max_extend") + " | ";
					result += "ALLOCATED:" + myJson.getExactSingleItemAsString(response, "result", "allocated_physical_space") + " | ";
					result += "RESERVEDCAPACITY:" + myJson.getExactSingleItemAsString(response, "result", "reserved_capacity") + " | ";
					result += "FILESYSTEMCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystems_count") + " | ";
					result += "FILESYSTEMCLONECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_clones_count") + " | ";
					result += "FILESYSTEMSNAPCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_snapshots_count") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "CLONECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "clones_count") + " | ";
					result += "SNAPCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "snapshots_count") + " | ";
					result += "STATE:" + myJson.getExactSingleItemAsString(response, "result", "state") + " | ";
					result += "FREEPHYSICAL:" + myJson.getExactSingleItemAsString(response, "result", "free_physical_space") + " | ";
					result += "FREEVIRTUAL:" + myJson.getExactSingleItemAsString(response, "result", "free_virtual_space") + " | ";
					result += "PHYSICAL:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity") + " | ";
					result += "VIRTUAL:" + myJson.getExactSingleItemAsString(response, "result", "virtual_capacity") + " | ";
					result += "WARNING:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity_warning") + " | ";
					result += "CRITICAL:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity_critical") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewPool ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewPool ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewPool ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String deletePool(String poolID) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deletePool ####---- ");}
		try {
			String result = "";
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:deletePool ####---- " + this.credential.getLogin() );}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:deletePool ####---- " + poolID );}
			
			String tmp = null;
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:deletePool ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/pools/" + poolID + "?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deletePool ####---- " + response );}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewPool ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "VOLUMECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "volumes_count") + " | ";
					result += "MAXEXTEND:" + myJson.getExactSingleItemAsString(response, "result", "max_extend") + " | ";
					result += "ALLOCATED:" + myJson.getExactSingleItemAsString(response, "result", "allocated_physical_space") + " | ";
					result += "RESERVEDCAPACITY:" + myJson.getExactSingleItemAsString(response, "result", "reserved_capacity") + " | ";
					result += "FILESYSTEMCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystems_count") + " | ";
					result += "FILESYSTEMCLONECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_clones_count") + " | ";
					result += "FILESYSTEMSNAPCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_snapshots_count") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "CLONECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "clones_count") + " | ";
					result += "SNAPCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "snapshots_count") + " | ";
					result += "STATE:" + myJson.getExactSingleItemAsString(response, "result", "state") + " | ";
					result += "FREEPHYSICAL:" + myJson.getExactSingleItemAsString(response, "result", "free_physical_space") + " | ";
					result += "FREEVIRTUAL:" + myJson.getExactSingleItemAsString(response, "result", "free_virtual_space") + " | ";
					result += "PHYSICAL:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity") + " | ";
					result += "VIRTUAL:" + myJson.getExactSingleItemAsString(response, "result", "virtual_capacity") + " | ";
					result += "WARNING:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity_warning") + " | ";
					result += "CRITICAL:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity_critical") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deletePool ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deletePool ####---- RESPONSE IS NULL!!" );}
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deletePool ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String deletePoolByName(String poolName) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:deletePoolByName ####----");}
		int value = this.getPoolIDFromPoolName(poolName);
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:deletePoolByName ####---- POOL ID: " + value);}
		return this.deletePool(myFormat.safeIntToString(value));
	}
	private int getPoolIDFromPoolName(String name) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolIDFromPoolName ####----");}
		try {
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolIDFromPoolName ####---- POOL NAME: " + name );}
			if(name.isEmpty() || name == null || name.equals("")) {
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolIDFromPoolName ####---- POOL NAME IS BLANK" );}
				return 0;
			} else {
				ObjStore<INFINIDATPools> store = ObjStoreHelper.getStore(INFINIDATPools.class);
				String query = " name == '" + name + "'";
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolIDFromPoolName ####---- QUERY: " + query );}
				List<INFINIDATPools> objs = store.query(query);
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolIDFromPoolName ####---- POOL FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATPools pojo = objs.get(0);
					return pojo.getPoolID();
				}
			}
			return 0;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getPoolIDFromPoolName ####---- ERROR: " + e.getMessage());}
			return 0;
		}
	}
	public String getAccountNameFromPoolID(String id) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getAccountNameFromPoolID ####----");}
		try {
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getAccountNameFromPoolID ####---- POOL ID: " + id );}
			if(id.isEmpty() || id == null || id.equals("")) {
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getAccountNameFromPoolID ####---- POOL ID IS BLANK" );}
				return null;
			} else {
				ObjStore<INFINIDATPools> store = ObjStoreHelper.getStore(INFINIDATPools.class);
				String query = "id == " + id;
				List<INFINIDATPools> objs = store.query(query);
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getAccountNameFromPoolID ####---- POOLS FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATPools pojo = objs.get(0);
					return pojo.getAccountName();
				}
			}
			return null;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFuncitons:getAccountNameFromPoolID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String getPoolNameFromID(String id) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####----");}
		try {
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####---- POOL ID: " + id );}
			if(id.isEmpty() || id == null || id.equals("")) {
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####---- POOL ID IS BLANK" );}
				return null;
			} else {
				ObjStore<INFINIDATPools> store = ObjStoreHelper.getStore(INFINIDATPools.class);
				String query = "id == " + id;
				List<INFINIDATPools> objs = store.query(query);
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####---- POOL FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATPools pojo = objs.get(0);
					return pojo.getName();
				}
			}
			return null;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String editPoolByID(String poolid, String name) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editPoolByID ####----");}
		try {
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:editPoolByID ####---- POOL ID: " + poolid);}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:editPoolByID ####---- NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"name\": \"" + name + "\"}";
			
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:editPoolByID ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:editPoolByID ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/pools/"+poolid, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editPoolByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:editPoolByID ####---- " + response);}
			
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "VOLUMECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "volumes_count") + " | ";
					result += "MAXEXTEND:" + myJson.getExactSingleItemAsString(response, "result", "max_extend") + " | ";
					result += "ALLOCATED:" + myJson.getExactSingleItemAsString(response, "result", "allocated_physical_space") + " | ";
					result += "RESERVEDCAPACITY:" + myJson.getExactSingleItemAsString(response, "result", "reserved_capacity") + " | ";
					result += "FILESYSTEMCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystems_count") + " | ";
					result += "FILESYSTEMCLONECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_clones_count") + " | ";
					result += "FILESYSTEMSNAPCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "filesystem_snapshots_count") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "CLONECOUNT:" + myJson.getExactSingleItemAsString(response, "result", "clones_count") + " | ";
					result += "SNAPCOUNT:" + myJson.getExactSingleItemAsString(response, "result", "snapshots_count") + " | ";
					result += "STATE:" + myJson.getExactSingleItemAsString(response, "result", "state") + " | ";
					result += "FREEPHYSICAL:" + myJson.getExactSingleItemAsString(response, "result", "free_physical_space") + " | ";
					result += "FREEVIRTUAL:" + myJson.getExactSingleItemAsString(response, "result", "free_virtual_space") + " | ";
					result += "PHYSICAL:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity") + " | ";
					result += "VIRTUAL:" + myJson.getExactSingleItemAsString(response, "result", "virtual_capacity") + " | ";
					result += "WARNING:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity_warning") + " | ";
					result += "CRITICAL:" + myJson.getExactSingleItemAsString(response, "result", "physical_capacity_critical") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editPoolByID ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editPoolByID ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:editPoolByID ####---- Error: " + ex);}
			return null;
		}
	}
	public String getPoolNameFromID(int poolid) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####----");} 
		try {
			String id = myFormat.safeIntToString(poolid);
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####---- POOL ID: " + id );}
			if(id.isEmpty() || id == null || id.equals("")) {
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####---- POOL ID IS BLANK" );}
				return null;
			} else {
				ObjStore<INFINIDATPools> store = ObjStoreHelper.getStore(INFINIDATPools.class);
				String query = "id == " + id;
				List<INFINIDATPools> objs = store.query(query);
				if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####---- POOLS FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATPools pojo = objs.get(0);
					return pojo.getName();
				}
			}
			return null;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getPoolNameFromID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	
	// CLUSTERS
	public String createNewCluster(String name) {
		try {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewCluster ####---- Name: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp +=       "\"name\": \"" + name + "\"";  
			tmp +=       "}";
			
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewCluster ####---- SEND DATA: " + tmp);}
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:createNewCluster ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/clusters", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewCluster ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewCluster ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createNewCluster ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewCluster ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createNewCluster ####---- Error: " + ex);}
			return null;
		}
	}
	public void refreshClusters() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:refreshClusters ####---- ");}
		try {
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			this.getClustersSummary(baseURL);
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:refreshClusters ####---- ERROR: " + e.getMessage());}
		}	
	}
	public String getAccountNameFromClusterID(String id) {
		try {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFunctions:getAccountNameFromClusterID ####---- CLUSTER ID: " + id );}
			if(id.isEmpty() || id == null || id.equals("")) {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getAccountNameFromClusterID ####---- CLUSTER ID IS BLANK" );}
				return null;
			} else {
				ObjStore<INFINIDATClusters> store = ObjStoreHelper.getStore(INFINIDATClusters.class);
				String query = "id == " + id;
				List<INFINIDATClusters> objs = store.query(query);
				
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATFunctions:getAccountNameFromClusterID ####---- CLUSTERS FOUND: " + objs.size());}
				if(objs.size() == 1) {
					INFINIDATClusters pojo = objs.get(0);
					return pojo.getAccountName();
				}
			}
			return null;
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATFunctions:getAccountNameFromClusterID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String deleteCluster(String id) {
		try {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteCluster ####---- " + this.credential.getLogin() );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteCluster ####---- " + id );}

			String result = "";
			String tmp = null;

			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteCluster ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:deleteCluster ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/clusters/" + id + "?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteCluster ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteCluster ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "LUNS:" + myJson.getElementCountAsInt(response,"result.luns") + " | ";
					result += "HOSTS:" + myJson.getElementCountAsInt(response,"result.hosts") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteCluster ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteCluster ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteCluster ####---- Error: " + ex.getMessage());}
			return null;
		}
	}
	public String editClusterByID(String clusterid, String name) {
		try {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editClusterByID ####---- ID: " + clusterid);}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editClusterByID ####---- NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"name\": \"" + name + "\"}";
			
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editClusterByID ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info("----#### INFINIDATFunctions:editClusterByID ####---- URL: " + baseURL);}	
			String response = getResponse(baseURL, "api/rest/clusters/"+clusterid, "PUT", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editClusterByID ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editClusterByID ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "LUNS:" + myJson.getElementCountAsInt(response,"result.luns") + " | ";
					result += "HOSTS:" + myJson.getElementCountAsInt(response,"result.hosts") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editClusterByID ####---- " + result );}
			} else {
				logger.info("----#### INFINIDATFunctions:editClusterByID ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:editClusterByID ####---- ERROR: " + e.getMessage());}
			return null;
		}
	}
	public String getClusterNameFromID(String id) {
		try {
			logger.info( "----#### INFINIDATFuncitons:getClusterNameFromID ####---- CLUSTER ID: " + id );
			if(id.isEmpty() || id == null || id.equals("")) {
				logger.info( "----#### INFINIDATFuncitons:getClusterNameFromID ####---- CLUSTER ID IS BLANK" );
				return null;
			} else {
				ObjStore<INFINIDATClusters> store = ObjStoreHelper.getStore(INFINIDATClusters.class);
				String query = "id == " + id;
				List<INFINIDATClusters> objs = store.query(query);
				logger.info( "----#### INFINIDATFuncitons:getClusterNameFromID ####---- POOLS FOUND: " + objs.size());
				if(objs.size() == 1) {
					INFINIDATClusters pojo = objs.get(0);
					return pojo.getName();
				}
			}
			return null;
		} catch(Exception e) {
			logger.info( "----#### INFINIDATFuncitons:getClusterNameFromID ####---- ERROR: " + e.getMessage());
			return null;
		}
	}
	
	// HOST TO CLUSTER
	public String getAllHosts() {
		logger.info("----#### INFINIDATFunctions:getAllHosts ####----");
		String result = "";
		try {
			String payload = "";
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:getAllHosts ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/hosts", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			logger.info("----#### INFINIDATFunctions:getAllHosts ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				logger.info("----#### INFINIDATFunctions:getAllHosts ####---- " + response );
				
				return response;
				
			} else {
				logger.info("----#### INFINIDATFunctions:getAllHosts ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:getAllHosts ####---- Error: " + ex);
			return null;
		}
	}
	public String addHostToCluster(String clusterid, String hostid) {
		logger.info("----#### INFINIDATFunctions:AddHostToCluster ####----");
		try {
			logger.info("----#### INFINIDATFunctions:AddHostToCluster ####---- CLUSTER ID: " + clusterid);
			logger.info("----#### INFINIDATFunctions:AddHostToCluster ####---- HOST ID: " + hostid);
			
			String result = "";
			String tmp = "{";
			tmp +=       "\"id\": " + hostid;
			tmp +=       "}";
			
			logger.info("----#### INFINIDATFunctions:AddHostToCluster ####---- SEND DATA: " + tmp);
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:AddHostToCluster ####---- GOT CREDENTIALS");
			String response = getResponse(baseURL, "api/rest/clusters/"+clusterid+"/hosts", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			logger.info("----#### INFINIDATFunctions:AddHostToCluster ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				logger.info("----#### INFINIDATFunctions:AddHostToCluster ####---- " + response );

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
					
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				logger.info("----#### INFINIDATFunctions:AddHostToCluster ####---- " + result );
			} else {
				logger.info("----#### INFINIDATFunctions:AddHostToCluster ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:AddHostToCluster ####---- Error: " + ex);
			return null;
		}
	}
	public String getAllClusterHosts(String id) {
		logger.info("----#### INFINIDATFunctions:getAllClusterHosts ####---- CLUSTER ID: " + id);
		String result = "";
		try {
			String payload = "";
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:getAllClusterHosts ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/clusters/" + id + "/hosts", "GET", this.credential.getLogin(), this.credential.getPassword(), payload);
			if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
			logger.info("----#### INFINIDATFunctions:getAllClusterHosts ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				logger.info("----#### INFINIDATFunctions:getAllClusterHosts ####---- " + response );
				
				return response;
				
			} else {
				logger.info("----#### INFINIDATFunctions:getAllClusterHosts ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:getAllClusterHosts ####---- Error: " + ex);
			return null;
		}
	}
	public String deleteHostToCluster(String id, String hostid) {
		try {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- " + this.credential.getLogin() );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- HOSTID: " + id );}
			
			String result = "";
			String tmp = null;

			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/clusters/" + id + "/hosts/" + hostid + "?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "LUNS:" + myJson.getElementCountAsInt(response,"result.luns") + " | ";
					result += "HOSTS:" + myJson.getElementCountAsInt(response,"result.hosts") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- " + result );}
			}
			return result;
		} catch (Exception ex) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- Error: " + ex.getMessage());}
			return null;
		}
	}
	
	// LUN TO CLUSTER
	public String addLunToCluster(String clusterid, String volumeid, int lunid) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addLunToCluster ####----");}
		try {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- CLUSTER ID: " + clusterid);}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- VOLUME ID: " + volumeid);}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- LUB ID: " + lunid);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"lun\": " + lunid + ",";
			tmp += "\"volume_id\": " + volumeid + "}";
			
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- SEND DATA: " + tmp);}		
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/clusters/"+clusterid+"/luns", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "HOSTCLUSTERID:" + myJson.getExactSingleItemAsString(response, "result", "host_cluster_id") + " | ";
					result += "VOLUMEID:" + myJson.getExactSingleItemAsString(response, "result", "volume_id") + " | ";
					result += "CLUSTERED:" + myJson.getExactSingleItemAsString(response, "result", "clustered") + " | ";
					result += "HOSTID:" + myJson.getExactSingleItemAsString(response, "result", "host_id") + " | ";
					result += "LUNID:" + myJson.getExactSingleItemAsString(response, "result", "lun") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}

				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:addLunToCluster ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String getClusterLuns(String clusterid) {
		logger.info("----#### INFINIDATFunctions:getClusterLuns ####----");
		try {
			logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- CLUSTER ID: " + clusterid);
			
			String result = "";
			String tmp = "";
			
			logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- SEND DATA: " + tmp);
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/clusters/"+clusterid+"/luns", "GET", this.credential.getLogin(),this.credential.getPassword(),tmp);
			logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
	
				logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- RESPONSE: " + response );
				
				return response;
			} else {
				logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- Error: " + ex);
			return null;
		}
	}
	public String deleteLunToCluster(String clusterid, String volumeid) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteLunToCluster ####----");}
		try {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteLunToCluster ####---- CLUSTER ID: " + clusterid);}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteLunToCluster ####---- HOST ID: " + volumeid);}
			
			String result = "";
			String tmp = "";
			
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteLunToCluster ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteLunToCluster ####---- URL: " + baseURL );}
			String response = getResponse(baseURL, "api/rest/clusters/" + clusterid + "/luns/volume_id/" + volumeid +"?approved=yes", "DELETE", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteLunToCluster ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteHostToCluster ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "HOSTCLUSTERID:" + myJson.getExactSingleItemAsString(response, "result", "host_cluster_id") + " | ";
					result += "VOLUMEID:" + myJson.getExactSingleItemAsString(response, "result", "volume_id") + " | ";
					result += "CLUSTERED:" + myJson.getExactSingleItemAsString(response, "result", "clustered") + " | ";
					result += "HOSTID:" + myJson.getExactSingleItemAsString(response, "result", "host_id") + " | ";
					result += "LUNID:" + myJson.getExactSingleItemAsString(response, "result", "lun") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:deleteLunToCluster ####---- " + result );}
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:deleteLunToCluster ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public String getVolumeNameFromVolumeID(String volumeid) {
		logger.info("----#### INFINIDATFunctions:getVolumeNameFromVolumeID ####----");
		try {
			logger.info("----#### INFINIDATFunctions:getVolumeNameFromVolumeID ####---- VOLUME ID: " + volumeid);
			
			String result = "";
			String tmp = "";
			
			logger.info("----#### INFINIDATFunctions:getVolumeNameFromVolumeID ####---- SEND DATA: " + tmp);
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:getVolumeNameFromVolumeID ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/volumes/"+volumeid, "GET", this.credential.getLogin(),this.credential.getPassword(),tmp);
			logger.info("----#### INFINIDATFunctions:getVolumeNameFromVolumeID ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
	
				logger.info("----#### INFINIDATFunctions:getVolumeNameFromVolumeID ####---- RESPONSE: " + response );
				
				return myJson.getExactSingleItemAsString(response, "result", "name");
			} else {
				logger.info("----#### INFINIDATFunctions:getVolumeNameFromVolumeID ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:getVolumeNameFromVolumeID ####---- Error: " + ex);
			return null;
		}
	}
	public String getAllClusterLuns(){
		logger.info("----#### INFINIDATFunctions:getClusterLuns ####----");
		try {
			
			String result = "";
			String tmp = "";
			
			logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- SEND DATA: " + tmp);
			
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- GOT CREDENTIALS");
			
			String response = getResponse(baseURL, "api/rest/clusters", "GET", this.credential.getLogin(),this.credential.getPassword(),tmp);
			logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- RESPONSE: " + response);
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
	
				logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- RESPONSE: " + response );
				
				return response;
			} else {
				logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- RESPONSE IS NULL!!" );
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception ex) {
			logger.info("----#### INFINIDATFunctions:getClusterLuns ####---- Error: " + ex);
			return null;
		}
	}
	
	// VOLUME SNAPSHOTS
	public String createVolumeSnapshot(String vid, String name) {
		try {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createVolumeSnapshot ####---- VOLUME ID: " + vid);}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createVolumeSnapshot ####---- VOLUME NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"parent_id\": " + vid + ",";
			tmp += "\"name\": \"" + name + "\"";
			tmp += "}";
			
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createVolumeSnapshot ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createVolumeSnapshot ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/volumes", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createVolumeSnapshot ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createVolumeSnapshot ####---- " + response );}
				
				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | "; 
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SERIAL:" + myJson.getExactSingleItemAsString(response, "result", "serial") + " | ";
					result += "BLOCKS:" + myJson.getExactSingleItemAsString(response, "result", "num_blocks") + " | ";
					result += "ALLOCATED:" + myJson.getExactSingleItemAsString(response, "result", "allocated") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "PARENTID:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "DATASET:" + myJson.getExactSingleItemAsString(response, "result", "dataset_type") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "result", "data") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}
				
				if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:createVolumeSnapshot ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createVolumeSnapshot ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:createVolumeSnapshot ####---- Error: " + e.getMessage());}
			return null;
		}
	}
	public void refreshSnapshots() {
		logger.info("----#### INFINIDATFunctions:refreshSnapshots ####---- ");
		try {
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			this.getVolumesSummary(baseURL);
		} catch (Exception e) {
			logger.info("----#### INFINIDATFunctions:refreshSnapshots ####---- ERROR: " + e.getMessage());
		}	
	}
	
	// CLONE VOLUME
	/**
	 * No return given from this function. It is simply a shortcut
	 * to the getVolumeSummary function.
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * image exists. When this applet attempts to draw the image on
	 * the screen, the data will be loaded. The graphics primitives 
	 * that draw the image will incrementally paint on the screen. 
	 *
	 * @param  		none
	 * @return 		none
	 * @see    		n/a
	 * @since		0.7	
	 */
	public void refreshClones() {
		logger.info("----#### INFINIDATFunctions:refreshClones ####---- ");
		try {
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			this.getVolumesSummary(baseURL);
		} catch (Exception e) {
			logger.info("----#### INFINIDATFunctions:refreshClones ####---- ERROR: " + e.getMessage());
		}	
	}
	/**
	 * Returns a String that can be used to determine if the HTTP request
	 * and the action was completed successfully or not. 
	 * <p>
	 * This method returns either a null result due to a failure or
	 * it returns the code from the last HTTP request in JSON format. 
	 *
	 * @param  vid  	an ID that represents a volume in the UCSD DB
	 * @param  name 	the name given to the volume
	 * @return String	a String containing the processed result of the API query     
	 * @see   
	 * @since			0.7
	 */
	public String cloneVolumeSnapshot(String vid, String name) {
		if(INFINIDATConstants.DEBUG_SNAPSHOTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:addLunToCluster ####----");}
		try {
			if(INFINIDATConstants.DEBUG_SNAPSHOTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeSnapshot ####---- VOLUME ID: " + vid);}
			if(INFINIDATConstants.DEBUG_SNAPSHOTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeSnapshot ####---- VOLUME NAME: " + name);}
			
			String result = "";
			String tmp = "{";
			tmp += "\"parent_id\": " + vid + ",";
			tmp += "\"name\": \"" + name + "\"";
			tmp += "}";
			
			if(INFINIDATConstants.DEBUG_SNAPSHOTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeSnapshot ####---- SEND DATA: " + tmp);}
			String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			if(INFINIDATConstants.DEBUG_SNAPSHOTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeSnapshot ####---- URL: " + baseURL);}
			String response = getResponse(baseURL, "api/rest/volumes", "POST", this.credential.getLogin(),this.credential.getPassword(),tmp);
			if(INFINIDATConstants.DEBUG_SNAPSHOTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeSnapshot ####---- RESPONSE: " + response);}
			
			if( response != null) {
				if( response.substring(0, 4).equals("null")) {response = response.substring(4, response.length());}
				if(INFINIDATConstants.DEBUG_SNAPSHOTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeSnapshot ####---- " + response );}

				if(myJson.getElementCountAsInt(response, "result") == 0) {
					result = "STATUS: ERROR" + " | ";
					result += "MESSAGE:" + myJson.getExactSingleItemAsString(response, "error", "code") + " | ";
					result += "DESCRIPTION:" + myJson.getExactSingleItemAsString(response, "error", "message") + " | ";
					result += "SEVERITY:" + myJson.getExactSingleItemAsString(response, "error", "severity") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "error", "data") + " | ";
					result += "REMOTE:" + myJson.getExactSingleItemAsString(response, "error", "is_remote") + " | ";
					result += "CODE:" + this.lastResponseCode;
				} else {
					result = "STATUS: SUCCESS" + " | ";
					result += "ID:" + myJson.getExactSingleItemAsString(response, "result", "id") + " | ";
					result += "NAME:" + myJson.getExactSingleItemAsString(response, "result", "name") + " | ";
					result += "POOLID:" + myJson.getExactSingleItemAsString(response, "result", "pool_id") + " | ";
					result += "SERIAL:" + myJson.getExactSingleItemAsString(response, "result", "serial") + " | ";
					result += "BLOCKS:" + myJson.getExactSingleItemAsString(response, "result", "num_blocks") + " | ";
					result += "ALLOCATED:" + myJson.getExactSingleItemAsString(response, "result", "allocated") + " | ";
					result += "SIZE:" + myJson.getExactSingleItemAsString(response, "result", "size") + " | ";
					result += "PARENTID:" + myJson.getExactSingleItemAsString(response, "result", "parent_id") + " | ";
					result += "SSD:" + myJson.getExactSingleItemAsString(response, "result", "ssd_enabled") + " | ";
					result += "TYPE:" + myJson.getExactSingleItemAsString(response, "result", "type") + " | ";
					result += "DATASET:" + myJson.getExactSingleItemAsString(response, "result", "dataset_type") + " | ";
					result += "DATA:" + myJson.getExactSingleItemAsString(response, "result", "data") + " | ";
					result += "PROVTYPE:" + myJson.getExactSingleItemAsString(response, "result", "provtype") + " | ";
					result += "PROTECTED:" + myJson.getExactSingleItemAsString(response, "result", "write_protected") + " | ";
					result += "MAPPED:" + myJson.getExactSingleItemAsString(response, "result", "mapped") + " | ";
					result += "CODE:" + this.lastResponseCode;
				}

				if(INFINIDATConstants.DEBUG_SNAPSHOTS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATFunctions:cloneVolumeSnapshot ####---- " + result );}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:cloneVolumeSnapshot ####---- RESPONSE IS NULL!!" );}
				result = "CODE:" + this.lastResponseCode;
			}
			return result;
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATFunctions:cloneVolumeSnapshot ####---- Error: " + e.getMessage());}
			return null;
		}
	}

	// HELPER FUNCTIONS
	/**
	 * Returns the current date and time formatted with a preconfigured
	 * format. dd/MM/yyyy hh:mm:ss 
	 * <p>
	 * This method always returns the system based time and date. 
	 *
	 * @param  		none
	 * @return      A string containing a formatted date and time.
	 * @see         SimpleDateFormat
	 * @since		0.8
	 */
	public static String getReportTitleCurrentDateTime() {
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat(INFINIDATConstants.DEFAULT_DATE_TIME_FORMAT);
	    return ft.format(dNow);
	}

	// WORK IN PROGRESS
}