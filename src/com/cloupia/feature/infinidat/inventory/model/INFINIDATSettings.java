package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_settings" )
public class INFINIDATSettings implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATData.class );
	@Persistent
	private String accountName;
	
	
	public INFINIDATSettings() {
		if(INFINIDATConstants.DEBUG_SETTINGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDASettings:INFINIDATSettings ####----" );}
	}
	public INFINIDATSettings(String accountName) {
		if(INFINIDATConstants.DEBUG_SETTINGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDASettings:INFINIDATSettings ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_SETTINGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDASettings:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_SETTINGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDASettings:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName(String accName) {
		if(INFINIDATConstants.DEBUG_SETTINGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDASettings:setAccountName ####----" );}
		this.accountName = accName;
	}
}
