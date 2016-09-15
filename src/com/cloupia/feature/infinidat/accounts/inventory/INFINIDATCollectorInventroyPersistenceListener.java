package com.cloupia.feature.infinidat.accounts.inventory;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.service.cIM.inframgr.collector.controller.PersistenceListener;
import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;

@SuppressWarnings("unused")
public class INFINIDATCollectorInventroyPersistenceListener extends PersistenceListener {
	private static Logger logger = Logger.getLogger( INFINIDATCollectorInventroyPersistenceListener.class );

	@Override
	public void persistItem( ItemResponse arg0 ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCollectorInventroyPersistenceListener:persistItem ####----" );}
	}
}
