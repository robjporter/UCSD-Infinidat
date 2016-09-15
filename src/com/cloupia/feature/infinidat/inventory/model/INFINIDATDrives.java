package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_drives" )
public class INFINIDATDrives implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATDrives.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Node ID" )
	@Persistent
	private int node;
	@ReportField( label = "Drive ID" )
	@Persistent
	private int driveID;
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
	@ReportField( label = "Type" )
	@Persistent
	private String type;
	@ReportField( label = "Serial Number" )
	@Persistent
	private String serialnumber;
	@ReportField( label = "Created At" )
	@Persistent
	private long cdate;
	@ReportField( label = "Updated At" )
	@Persistent
	private long udate;
	@ReportField( label = "Comment" )
	@Persistent
	private String comment;
	
	public INFINIDATDrives() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:INFINIDATDrives ####----" );}
	}
	public INFINIDATDrives( String accountName, int nodeid, int driveid, String model, String vendor, String firmware, String state, String type, String serialnumber ) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:INFINIDATDrives ####----" );}
		this.accountName = accountName;
		this.node = nodeid;
		this.driveID = driveid;
		this.model = model;
		this.vendor = vendor;
		this.firmware = firmware;
		this.state = state;
		this.type = type;
		this.serialnumber = serialnumber;
	}

	public int getNodeID() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getNodeID ####----" );}
		return this.node;
	}
	public void setNodeID(int id) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:setNodeID ####----" );}
		this.node = id;
	}
	public int getDriveID() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getDriveID ####----" );}
		return this.driveID;
	}
	public void setDriveID(int id) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:setDriveID ####----" );}
		this.driveID = id;
	}
	public String getModel() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getDriveID ####----" );}
		return this.model;
	}
	public void setModel(String model) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:setDriveID ####----" );}
		this.model = model;
	}
	public String getVendor() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getVendor ####----" );}
		return this.vendor;
	}
	public void setVendor(String vendor) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:setVendor ####----" );}
		this.vendor = vendor;
	}
	public String getFirmware() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getFirmware ####----" );}
		return this.firmware;
	}
	public void setFirmware(String firmware) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:setFirmware ####----" );}
		this.firmware = firmware;
	}
	public String getState() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getState ####----" );}
		return this.state;
	}
	public void setState(String state) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:setState ####----" );}
		this.state = state;
	}
	public String getType() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getType ####----" );}
		return this.type;
	}
	public void setType(String type) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:setType ####----" );}
		this.type = type;
	}
	public String getSerialNumber() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getSerialNumber ####----" );}
		return this.serialnumber;
	}
	public void setSerialNumber(String serialnumber) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:setSerialNumber ####----" );}
		this.serialnumber = serialnumber;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_DRIVES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDrives:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}