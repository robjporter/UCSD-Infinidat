package com.cloupia.feature.infinidat.inventory;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.accounts.inventory.INFINIDATConvergedStackBuilder;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATServices;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATNodes;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATVolume;
import com.cloupia.model.cIM.InfraAccount;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.model.cIM.stackView.StackViewItemProviderIf;
import com.cloupia.service.cIM.inframgr.GenericInfraAccountConnectionTestHandler;
import com.cloupia.service.cIM.inframgr.InfraAccountConnectionTestHandlerIf;
import com.cloupia.service.cIM.inframgr.collector.controller.CollectorFactory;
import com.cloupia.service.cIM.inframgr.collector.controller.InventoryCollector;
import com.cloupia.service.cIM.inframgr.collector.impl.GenericNodeID;
import com.cloupia.service.cIM.inframgr.reports.contextresolve.ConvergedStackComponentBuilderIf;

@SuppressWarnings("unused")
public class INFINIDATCollectorFactory extends CollectorFactory {
	private static Logger logger = Logger.getLogger( INFINIDATCollectorFactory.class );

	public INFINIDATCollectorFactory( int accountType ) {
		super(accountType);		
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCollectorFactory:INFINIDATCollectorFactory ####----" );}
	}
	@Override
	public InventoryCollector createCollector( String accountName ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCollectorFactory:createCollector ####----" );}
		GenericNodeID nodeID = new GenericNodeID( accountName, InfraAccountTypes.CAT_STORAGE );
		INFINIDATInventoryCollector collector = new INFINIDATInventoryCollector( nodeID );
		INFINIDATInventoryItem capabilities = new INFINIDATInventoryItem( INFINIDATConstants.CAPABILITIES, INFINIDATServices.class );
		INFINIDATInventoryItem controllers = new INFINIDATInventoryItem( INFINIDATConstants.CONTROLLERS, INFINIDATNodes.class );
		INFINIDATInventoryItem luns = new INFINIDATInventoryItem( INFINIDATConstants.LUNS, INFINIDATVolume.class );
		collector.addItem( capabilities );
		collector.addItem( controllers );
		collector.addItem( luns );
		return collector;
	}
	@Override
	public int getAccountCategory() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCollectorFactory:getAccountCategory ####----" );}
		return CollectorFactory.STORAGE_CATEGORY;
	}
	@Override
	public ConvergedStackComponentBuilderIf getStackComponentBuilder() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCollectorFactory:getStackComponentBuilder ####----" );}
		return new INFINIDATConvergedStackBuilder();
	}
	@Override
	public StackViewItemProviderIf getStackViewProvider() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCollectorFactory:getStackViewProvider ####----" );}
		return new INFINIDATStackViewProvider();
	}
	@Override
	public InfraAccountConnectionTestHandlerIf getTestConnectionHandler() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCollectorFactory:getTestConnectionHandler ####----" );}
		return new GenericInfraAccountConnectionTestHandler() {
			@Override
			public boolean testConnectionTo( InfraAccount arg0, StringBuffer arg1 ) {
				if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCollectorFactory:testConnectionTo ####----" );}
				return true;
			}
		};
	}
}
