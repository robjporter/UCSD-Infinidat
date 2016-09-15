package com.cloupia.feature.infinidat.accounts.api;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import com.cloupia.feature.infinidat.accounts.INFINIDATAccount;
import com.cloupia.feature.infinidat.accounts.api.INFINIDATAccountAPI;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;

@SuppressWarnings({"deprecation","unused"})
public class INFINIDATAccountAPI {
	private static Logger logger = Logger.getLogger( INFINIDATAccountAPI.class );
	private static final HashMap<String, INFINIDATAccountAPI> instances = new HashMap<String, INFINIDATAccountAPI>();
	private String ipAddress;
	private String username;
	private String password;
	private String protocol;
	private int port;
	private int apiVersion = 1;
	private String token = null;

	private INFINIDATAccountAPI() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountAPI:getDeviceId ####----" );}
	}
	private INFINIDATAccountAPI( String ipAddress, String username, String password, int port, String protocol ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountAPI:getDeviceId ####----" );}
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		this.port = port;
		this.protocol = protocol;
	}
	public static INFINIDATAccountAPI getINFINIDATAccountAPI(INFINIDATAccount account) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountAPI:getINFINIDATAccountAPI ####----" );}
		try {
			return getInstanceFor( account.getServerAddress(), account.getLogin(), account.getPassword(), Integer.parseInt( account.getPort()), account.getProtocol());
		} catch (NumberFormatException e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAccountAPI:getINFINIDATAccountAPI ####---- ERROR: " + e.getMessage());}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAccountAPI:getINFINIDATAccountAPI ####---- ERROR: " + e.getMessage());}
		}
		return null;
	}
	public static INFINIDATAccountAPI getXIOAccountAPI( INFINIDATAccount account ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountAPI:getDeviceId ####----" );}
		return getInstanceFor( account.getServerAddress(), account.getLogin(), account.getPassword(), Integer.parseInt( account.getPort()), account.getProtocol());
	}
	public static INFINIDATAccountAPI getInstanceFor( String ipAddress, String username, String password, int port, String protocol ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountAPI:getDeviceId ####----" );}
		INFINIDATAccountAPI api = instances.get( ipAddress + username + password + port );
		if( api == null ) {
			api = new INFINIDATAccountAPI( ipAddress, username, password, port, protocol );
			instances.put(ipAddress + username + password + port, api);
		} else {
		}
		return api;
	}
	private HttpClient trustEveryoneSSLHttpClient() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountAPI:trustEveryoneSSLHttpClient ####----" );}
		try {
			SchemeRegistry registry = new SchemeRegistry();
			SSLSocketFactory socketFactory = new SSLSocketFactory( new TrustStrategy() {
						public boolean isTrusted( final X509Certificate[] chain, String authType ) throws CertificateException {
							return true;
						}
					},
					org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			registry.register( new Scheme( this.protocol, port, socketFactory ));
			ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager( registry );
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout( params, 30000 );
			HttpConnectionParams.setSoTimeout( params, 30000 );
			DefaultHttpClient client = new DefaultHttpClient( mgr );
			client.setParams(params);
			return client;
		} catch (GeneralSecurityException e) {
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountAPI:trustEveryoneSSLHttpClient ####----" );}
			throw new RuntimeException(e);
		}
	}
	public String getInventoryData( String url ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountAPI:getInventoryData ####----" );}
		return url;
	}
	private String getCompleteUrl(String url) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountAPI:getCompleteUrl ####----" );}
		return this.protocol + "://" + ipAddress + ":" + port + "/" + url;
	}
}
