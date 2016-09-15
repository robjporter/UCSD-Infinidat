package com.cloupia.feature.infinidat.inventory;

import org.apache.log4j.Logger;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.collector.controller.InventoryCollector;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemDataObjectBinderIf;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemParserIf;
import com.cloupia.service.cIM.inframgr.collector.controller.JDOPersistenceListener;
import com.cloupia.service.cIM.inframgr.collector.controller.NodeConnectorIf;
import com.cloupia.service.cIM.inframgr.collector.controller.PersistenceListener;
import com.cloupia.service.cIM.inframgr.collector.model.NodeID;

@SuppressWarnings("unused")
public class INFINIDATInventoryCollector extends InventoryCollector {
	private static Logger logger = Logger.getLogger( INFINIDATInventoryCollector.class );
	private NodeID node;
	private INFINIDATConnector connector;
	private INFINIDATBinder binder;
	private JDOPersistenceListener listener;
	
	public INFINIDATInventoryCollector( NodeID nodeId ) {
		super( nodeId );
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryCollector:DummyInventoryCollector ####----" );}
		this.node = nodeId;
		this.connector = new INFINIDATConnector();
		this.binder = new INFINIDATBinder();
		this.listener = new JDOPersistenceListener();	
	}
	@Override
	public NodeConnectorIf getConnector() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryCollector:getConnector ####----" );}
		return this.connector;
	}
	@Override
	public PersistenceListener getItemListener() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryCollector:getItemListener ####----" );}
		return this.listener;
	}
	@Override
	public ItemParserIf getItemParser() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryCollector:getItemParser ####----" );}
		return null;
	}
	@Override
	public ItemDataObjectBinderIf getObjectBinder() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryCollector:getObjectBinder ####----" );}
		return this.binder;
	}
	@Override
	public FormLOVPair[] getFrequencyHoursLov() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryCollector:getFrequencyHoursLov ####----" );}
		return null;
	}
	@Override
	public FormLOVPair[] getFrequencyMinsLov() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryCollector:getFrequencyMinsLov ####----" );}
		return null;
	}
	@Override
	public String getTaskName() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryCollector:getTaskName ####----" );}
		return INFINIDATConstants.INFINIDAT_INVENTORY_COLLECTOR_NAME + "_" + node.getConnectorId();
	}
	@Override
	public long getFrequenceInMinutes() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryCollector:getFrequenceInMinutes ####----" );}
		return INFINIDATConstants.DEFAULT_INVENTORY_COLLECTION_TIMEOUT;
	}
}
