package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_nodes" )
public class INFINIDATNodes implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATNodes.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Node ID" )
	@Persistent
	private int id;
	@ReportField( label = "Name" )
	@Persistent
	private String name;
	@ReportField( label = "Model" )
	@Persistent
	private String model;
	@ReportField( label = "Vendor" )
	@Persistent
	private String vendor;
	@ReportField( label = "Firmware" )
	@Persistent
	private String firmware;
	@ReportField( label = "State" )
	@Persistent
	private String state;

	public INFINIDATNodes() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:INFINIDATNodes ####----" );}
	}
	public INFINIDATNodes( String accountName, int id, String name, String model, String vendor, String firmware, String state ) {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:INFINIDATNodes ####----" );}
		this.accountName = accountName;
		this.id = id;
		this.name = name;
		this.model = model;
		this.vendor = vendor;
		this.firmware = firmware;
		this.state = state;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:setAccountName ####----" );}
		this.accountName = accountName;
	}
	public int getNodeID() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:getNodeID ####----" );}
		return this.id;
	}
	public void setNodeID( int id ) {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:setNodeID ####----" );}
		this.id = id;
	}
	public String getName() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:getName ####----" );}
		return this.name;
	}
	public void setName( String name ) {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:setName ####----" );}
		this.name = name;
	}
	public String getModel() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:getModel ####----" );}
		return this.model;
	}
	public void setModel( String model ) {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:setModel ####----" );}
		this.model = model;
	}
	public String getVendor() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:getVendor ####----" );}
		return this.vendor;
	}
	public void setVendor( String vendor ) {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:setVendor ####----" );}
		this.vendor = vendor;
	}
	public String getFirmware() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:getFirmware ####----" );}
		return this.firmware;
	}
	public void setFirmware( String firmware ) {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:setFirmware ####----" );}
		this.firmware = firmware;
	}
	public String getState() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:getState ####----" );}
		return this.state;
	}
	public void setState( String state ) {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:setState ####----" );}
		this.state = state;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATNodes:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
