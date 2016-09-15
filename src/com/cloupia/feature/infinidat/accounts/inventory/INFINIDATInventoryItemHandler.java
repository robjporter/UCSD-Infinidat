package com.cloupia.feature.infinidat.accounts.inventory;

import java.util.Map;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.JSON;
import com.cloupia.feature.infinidat.accounts.INFINIDATAccount;
import com.cloupia.feature.infinidat.accounts.api.INFINIDATAccountAPI;
import com.cloupia.feature.infinidat.accounts.api.INFINIDATAccountJSONBinder;
import com.cloupia.feature.infinidat.accounts.api.INFINIDATJSONBinder;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.cIaaS.network.model.DeviceCredential;
import com.cloupia.lib.connector.AbstractInventoryItemHandler;
import com.cloupia.lib.connector.InventoryContext;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.service.cIM.inframgr.collector.controller.PersistenceListener;
import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_inventory_handler") 
public class INFINIDATInventoryItemHandler extends AbstractInventoryItemHandler {
	private static Logger logger = Logger.getLogger( INFINIDATInventoryItemHandler.class );

	@Override
	public void cleanup( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:cleanup ####----" );}
	}
	@Override
	public void doInventory( String accountName, InventoryContext inventoryCtxt ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:doInventory ####----" );}
		doInventory( accountName );
	}
	@Override
	public void doInventory( String accountName, Object obj ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:doInventory ####----" );}
	}
	private void doInventory( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:doInventory ####----" );}
		PhysicalInfraAccount infraAccount = AccountUtil.getAccountByName( accountName );
		DeviceCredential cred = infraAccount.toDeviceCredential();
		INFINIDATFunctions INFINIDATFunctions = new INFINIDATFunctions( cred );
		try {
			INFINIDATFunctions.getSummaryInventory( infraAccount.getAccountName());
			infraAccount.setReachable( true );
		} catch( Exception e ) {
			infraAccount.setReachable( false );
			throw( e );	
		}
		String jsonData = null; //api.getInventoryData( "http:10.52.208.227/query/arrays" );
		ItemResponse bindableResponse = new ItemResponse();
		bindableResponse.setContext( getContext( accountName ));
		bindableResponse.setCollectedData( jsonData );
		ItemResponse bindedResponse = null;
		INFINIDATJSONBinder binder = getBinder();
		
		if (binder != null) {
			bindedResponse = binder.bind( bindableResponse );
		} else {}
		
		//TODO: NOT PERFECT
		PersistenceListener listener = getListener();
		
		if (listener != null) {
			listener.persistItem( bindedResponse );
			//NEW
			//INFINIDATCollectorInventroyPersistenceListener.persistItem2( cred );
		} else {}
	}
	private void doInventoryOld( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:doInventoryOld ####----" );}
		INFINIDATAccount acc = getINFINIDATCredential( accountName );
		String jsonData = null;
		ItemResponse bindableResponse = new ItemResponse();
		bindableResponse.setContext( getContext( accountName ));
		bindableResponse.setCollectedData( jsonData );
		ItemResponse bindedResponse = null;
		
		INFINIDATJSONBinder binder = getBinder();
		
		if (binder != null) {
			bindedResponse = binder.bind( bindableResponse );
		} else {}
		
		PersistenceListener listener = getListener();
		
		if( listener != null ) {
			listener.persistItem( bindedResponse );
		} else {}
	}
	public String getUrl() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:getUrl ####----" );}
		return "platform/1/protocols/smb/shares";
	}
	public INFINIDATAccountJSONBinder getBinder() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:getBinder ####----" );}
		return new INFINIDATAccountJSONBinder();
	}
	private Map<String, Object> getContext( String accountName ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:getContext ####----" );}
		return null;
	}
	private PersistenceListener getListener() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:getListener ####----" );}
		return new INFINIDATCollectorInventroyPersistenceListener();
	}
	private static INFINIDATAccount getINFINIDATCredential( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItemHandler:getINFINIDATCredential ####----" );}
		PhysicalInfraAccount acc = AccountUtil.getAccountByName( accountName );
		String json = acc.getCredential();
		AbstractInfraAccount specificAcc = (AbstractInfraAccount) JSON.jsonToJavaObject( json, INFINIDATAccount.class );
		specificAcc.setAccount( acc );
		return (INFINIDATAccount) specificAcc;
	}
}
