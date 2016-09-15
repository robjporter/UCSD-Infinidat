package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true", table = "infinidat_services" )
public class INFINIDATServices  implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATServices.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Node ID" )
	@Persistent
	private int node;
	@ReportField( label = "Role" )
	@Persistent
	private String role;
	@ReportField( label = "Feature" )
	@Persistent
	private String feature;
	@ReportField( label = "Status" )
	@Persistent
	private Boolean status;

	public INFINIDATServices() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:INFINIDATServices ####----" );}
	}	
	
	public INFINIDATServices( String accountName, int node, String role, String feature, Boolean status ) {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:INFINIDATServices ####----" );}
		this.accountName = accountName;
		this.node = node;
		this.role = role;
		this.feature = feature;
		this.status = status;
	}
	public int getNodeID() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:getNodeID ####----" );}
		return this.node;
	}
	public void setNodeID(int id) {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:getNodeID ####----" );}
		this.node = id;
	}
	public String getRole() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:getRole ####----" );}
		return this.role;
	}
	public void setRole( String role ) {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:setRole ####----" );}
		this.role = role;
	}
	public String getFeature() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:getFeature ####----" );}
		return this.feature;
	}
	public void setFeature( String feature ) {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:setFeature ####----" );}
		this.feature = feature;
	}
	public Boolean getStatus() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:getStatus ####----" );}
		return this.status;
	}
	public void setStatus( Boolean status ) {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:setStatus ####----" );}
		this.status = status;
	}
	
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATServices:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}