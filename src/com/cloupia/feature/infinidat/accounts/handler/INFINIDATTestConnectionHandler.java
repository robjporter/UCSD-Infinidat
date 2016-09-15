package com.cloupia.feature.infinidat.accounts.handler;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.JSON;
import com.cloupia.feature.infinidat.accounts.INFINIDATAccount;
import com.cloupia.feature.infinidat.accounts.api.INFINIDATAccountAPI;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalConnectivityStatus;
import com.cloupia.lib.connector.account.PhysicalConnectivityTestHandler;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.model.cIM.InfraAccountTypes;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_test_connection_handler")
public class INFINIDATTestConnectionHandler extends PhysicalConnectivityTestHandler {
	static Logger logger = Logger.getLogger(INFINIDATTestConnectionHandler.class);

	@Override
	public PhysicalConnectivityStatus testConnection( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATTestConnectionHandler:PhysicalConnectivityStatus ####----" );}
		INFINIDATAccount acc = getINFINIDATCredential( accountName );
		PhysicalInfraAccount infraAccount = AccountUtil.getAccountByName( accountName );
		PhysicalConnectivityStatus status = new PhysicalConnectivityStatus( infraAccount );
		status.setConnectionOK( false );
		if( infraAccount != null ) {
			if( infraAccount.getAccountType() != null ) {
				if( infraAccount.getAccountType().equals( INFINIDATConstants.INFRA_ACCOUNT_LABEL )) {
					try {
						INFINIDATAccountAPI tmp = INFINIDATAccountAPI.getINFINIDATAccountAPI(acc);
						if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATTestConnectionHandler:PhysicalConnectivityStatus ####---- CONNECTION SUCCESSFUL" );}
						if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATTestConnectionHandler:PhysicalConnectivityStatus ####---- SETTING CONNECTION AS TRUE!");}
						status.setConnectionOK( true );
					} catch(Exception e) {
						if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATTestConnectionHandler:PhysicalConnectivityStatus ####---- ERROR: " + e.getMessage());}
						status.setErrorMsg("Failed to establish connection with the Device.");
					}
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATTestConnectionHandler:PhysicalConnectivityStatus ####---- ERROR: Incorrect Account Type" );}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATTestConnectionHandler:PhysicalConnectivityStatus ####---- ERROR: Account Type = null" );}			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATTestConnectionHandler:PhysicalConnectivityStatus ####---- ERROR: InfraAccount = null" );}
		}
		return status;
	}
	private static INFINIDATAccount getINFINIDATCredential( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATTestConnectionHandler:getINFINIDATCredential ####----" );}
		PhysicalInfraAccount acc = AccountUtil.getAccountByName(accountName);
		String json = acc.getCredential();
		AbstractInfraAccount specificAcc = (AbstractInfraAccount) JSON.jsonToJavaObject(json, INFINIDATAccount.class);
		specificAcc.setAccount(acc);
		return (INFINIDATAccount) specificAcc;
	}
}
