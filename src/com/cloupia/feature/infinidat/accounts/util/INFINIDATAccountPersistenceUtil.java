package com.cloupia.feature.infinidat.accounts.util;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.JSON;
import com.cloupia.feature.infinidat.accounts.INFINIDATAccount;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;

@SuppressWarnings("unused")
public class INFINIDATAccountPersistenceUtil {
	private static Logger logger = Logger.getLogger( INFINIDATAccountPersistenceUtil.class );

	public static void persistCollectedInventory( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountPersistenceUtil:persistCollectedInventory ####----" );}
		INFINIDATAccount acc = getINFINIDATCredential( accountName );
		if (acc != null) {
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAccountPersistenceUtil:persistCollectedInventory ####---- Account gained successfully - IP: " + acc.getServerAddress());}
		} else {
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAccountPersistenceUtil:persistCollectedInventory ####---- ERROR: Account lookup failed" );}
		}
	}
	public static INFINIDATAccount getINFINIDATCredential( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### XIOAccountPersistenceUtil:getINFINIDATCredential ####----" );}
		PhysicalInfraAccount acc = AccountUtil.getAccountByName(accountName);
		String json = acc.getCredential();
		AbstractInfraAccount specificAcc = (AbstractInfraAccount) JSON.jsonToJavaObject(json, INFINIDATAccount.class);
		specificAcc.setAccount(acc);
		return (INFINIDATAccount) specificAcc;
	}
}
