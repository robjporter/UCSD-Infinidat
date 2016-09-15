package com.cloupia.feature.infinidat.inventory;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.INFINIDATDevice;
import com.cloupia.model.cIM.InfraAccount;
import com.cloupia.service.cIM.inframgr.collector.controller.NodeConnectorIf;
import com.cloupia.service.cIM.inframgr.collector.model.ConfigItemIf;
import com.cloupia.service.cIM.inframgr.collector.model.ConfigResponse;
import com.cloupia.service.cIM.inframgr.collector.model.ConnectionProperties;
import com.cloupia.service.cIM.inframgr.collector.model.ConnectionStatus;
import com.cloupia.service.cIM.inframgr.collector.model.ItemIf;
import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;
import com.cloupia.service.cIM.inframgr.collector.model.NodeID;
import com.cloupia.service.cIM.inframgr.collector.view2.ConnectorCredential;

@SuppressWarnings("unused")
public class INFINIDATConnector implements NodeConnectorIf {
	private static Logger logger = Logger.getLogger( INFINIDATConnector.class );
	private INFINIDATDevice device = INFINIDATDevice.getInstance();
	
	@Override
	public ConnectionStatus connect( ConnectorCredential credential, ConnectionProperties props ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConnector:connect ####----" );}
		InfraAccount account = credential.toInfraAccount();
		String serverIP = account.getServer(); // deviceCreds.getDeviceIp();
		String login = account.getUserID(); // deviceCreds.getLogin();
		String password = account.getPassword();
		boolean result = device.connect( serverIP, login, password );
		ConnectionStatus status = new ConnectionStatus();
		status.setConnected( result );
		return status;
	}
	@Override
	public void disconnect() throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConnector:disconnect ####----" );}
	}
	@Override
	public ConfigResponse executeItems( NodeID arg0, ConfigItemIf arg1 ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConnector:executeItems ####----" );}
		return null;
	}
	@Override
	public ItemResponse getItem( NodeID nodeId, ItemIf item ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConnector:getItem ####----" );}
		INFINIDATInventoryItem invItem = (INFINIDATInventoryItem) item;
		String dataToCollect = invItem.getName();
		String data = device.getData( dataToCollect );
		ItemResponse itemResponse = new ItemResponse();
		itemResponse.setItem( item );
		itemResponse.setCollectedData( data );
		itemResponse.setNodeId( nodeId );
		return itemResponse;
	}
}
