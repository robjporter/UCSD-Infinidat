package com.cloupia.feature.infinidat.accounts.inventory;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.JSON;
import com.cloupia.feature.infinidat.accounts.INFINIDATAccount;
import com.cloupia.feature.infinidat.accounts.util.INFINIDATAccountPersistenceUtil;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.connector.InventoryContext;
import com.cloupia.lib.connector.InventoryEventListener;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.lib.connector.account.AccountTypeEntry;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalAccountManager;
import com.cloupia.lib.connector.account.PhysicalConnectivityStatus;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;

@SuppressWarnings("unused")
public class INFINIDATInventoryListener implements InventoryEventListener {
	private static Logger logger = Logger.getLogger( INFINIDATInventoryListener.class );

	@Override
	public void beforeInventoryStart( String accountName, InventoryContext arg1 ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryListener:beforeInventoryStart ####----" );}
	}
	@Override
	public void afterInventoryDone( String accountName, InventoryContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryListener:afterInventoryDone ####----" );}
		INFINIDATAccountPersistenceUtil.persistCollectedInventory( accountName );
		AccountTypeEntry entry = PhysicalAccountManager.getInstance().getAccountTypeEntryByName( accountName );
		PhysicalConnectivityStatus connectivityStatus = null;
		if( entry != null ) {
			connectivityStatus = entry.getTestConnectionHandler().testConnection( accountName );
		} else {}
		INFINIDATAccount acc = getINFINIDATCredential( accountName );
		if (acc != null && connectivityStatus != null) {
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryListener:afterInventoryDone ####---- Inventory collected successfully" );}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATInventoryListener:afterInventoryDone ####---- ERROR : Inventory failed collected" );}
		}
	}
	private static INFINIDATAccount getINFINIDATCredential( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryListener:getINFINIDATCredential ####----" );}
		PhysicalInfraAccount acc = AccountUtil.getAccountByName(accountName);
		String json = acc.getCredential();
		AbstractInfraAccount specificAcc = (AbstractInfraAccount) JSON.jsonToJavaObject(json, INFINIDATAccount.class);
		specificAcc.setAccount(acc);
		return (INFINIDATAccount) specificAcc;
	}
}
