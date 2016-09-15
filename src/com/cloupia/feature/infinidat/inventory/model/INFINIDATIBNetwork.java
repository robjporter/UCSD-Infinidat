package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_ib_network" )
public class INFINIDATIBNetwork implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATIBNetwork.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Node ID" )
	@Persistent
	private int nodeid;
	@ReportField( label = "ID" )
	@Persistent
	private int id;
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
	@ReportField( label = "State Description" )
	@Persistent
	private String statedescription;
	@ReportField( label = "Comment" )
	@Persistent
	private String comment;
	
	public INFINIDATIBNetwork() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:INFINIDATIBNetwork ####----" );}
	}
	public INFINIDATIBNetwork(String accountName, int nodeid, int id, String model, String vendor, String firmware, String state, String stateDescription, String comment) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:INFINIDATIBNetwork ####----" );}
		this.accountName = accountName;
		this.nodeid = nodeid;
		this.id = id;
		this.model = model;
		this.vendor = vendor;
		this.firmware = firmware;
		this.state = state;
		this.statedescription = stateDescription;
		this.comment = comment;
	}
	public String debug() {
		String tmp = "";
		return tmp;
	}
	public int getNodeID() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getNodeID ####----" );}
		return this.nodeid;
	}
	public void setNodeID(int nodeid) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:setNodeID ####----" );}
		this.nodeid = nodeid;
	}
	public int getID() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getID ####----" );}
		return this.id;
	}
	public void setID(int id) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:setID ####----" );}
		this.id = id;
	}
	public String getModel() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getModel ####----" );}
		return this.model;
	}
	public void setModel(String model) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:setModel ####----" );}
		this.model = model;
	}
	public String getVendor() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getVendor ####----" );}
		return this.vendor;
	}
	public void setVendor(String vendor) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:setVendor ####----" );}
		this.vendor = vendor;
	}
	public String getFirmware() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getFirmware ####----" );}
		return this.firmware;
	}
	public void setFirmware(String firmware) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:setFirmware ####----" );}
		this.firmware = firmware;
	}
	public String getState() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getState ####----" );}
		return this.state;
	}
	public void setState(String state) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:setState ####----" );}
		this.state = state;
	}
	public String getStateDescription() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getStateDescription ####----" );}
		return this.statedescription;
	}
	public void setStateDescription(String state) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:setStateDescription ####----" );}
		this.statedescription = state;
	}
	public String getComment() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getComment ####----" );}
		return this.comment;
	}
	public void setComment(String comment) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:setComment ####----" );}
		this.comment = comment;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_IBNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATIBNetwork:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}