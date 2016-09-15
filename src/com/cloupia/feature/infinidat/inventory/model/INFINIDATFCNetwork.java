package com.cloupia.feature.infinidat.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_fc_network" )
public class INFINIDATFCNetwork implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATFCNetwork.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Node id" )
	@Persistent
	private int nid;
	@ReportField( label = "ID" )
	@Persistent
	private int id;
	@ReportField( label = "HBA id" )
	@Persistent
	private int hbaid;
	@ReportField( label = "Link State" )
	@Persistent
	private String lstate;
	@ReportField( label = "Status" )
	@Persistent
	private String status;
	@ReportField( label = "HW Revision" )
	@Persistent
	private String hwrevision;
	@ReportField( label = "Firmware" )
	@Persistent
	private String firmware;
	@ReportField( label = "Role" )
	@Persistent
	private String role;
	@ReportField( label = "Switch Vendor" )
	@Persistent
	private String svendor;
	@ReportField( label = "Vendor" )
	@Persistent
	private String vendor;
	@ReportField( label = "Model" )
	@Persistent
	private String model;
	@ReportField( label = "Enabled" )
	@Persistent
	private boolean enabled;
	@ReportField( label = "WWPN" )
	@Persistent
	private String wwpn;
	@ReportField( label = "WWNN" )
	@Persistent
	private String wwnn;
	@ReportField( label = "Speed" )
	@Persistent
	private long speed;
	
	public INFINIDATFCNetwork() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:INFINIDATFCNetwork ####----" );}
	}
	public INFINIDATFCNetwork(String accountName, int nodeid, int id, int hbaid, String lstate, String status, String hwrev, String firmware, String role, String svendor, String vendor, String model, boolean enabled, String wwpn, String wwnn, long speed) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:INFINIDATFCNetwork ####----" );}
		this.accountName = accountName;
		this.nid = nodeid;
		this.id = id;
		this.hbaid = hbaid;
		this.lstate = lstate;
		this.status = status;
		this.hwrevision = hwrev;
		this.firmware = firmware;
		this.role = role;
		this.svendor = svendor;
		this.vendor = vendor;
		this.model = model;
		this.enabled = enabled;
		this.wwpn = wwpn;
		this.wwnn = wwnn;
		this.speed = speed;
	}
	public String debug() {
		String tmp = "";
		tmp += "AccountName: " + this.accountName + " | Node ID: " + this.nid + " | ID: " + this.id + " | HBA ID: " + this.hbaid + " | STATE: " + this.lstate + " | ";
		tmp += "HW Revision: " + this.hwrevision + " | Firmware: " + this.firmware + " | Role: " + this.role + " | S Vendor: " + this.svendor + " | ";
		tmp += "Vendor: " + this.vendor + " | Model: " + this.model + " | Enabled: " + this.enabled + " | WWPN: " + this.wwpn + " | WWNN: " + this.wwnn + " | Speed: " + this.speed;
		return tmp;
	}
	public int getID() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getID ####----" );}
		return this.id;
	}
	public void setID( int id ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setID ####----" );}
		this.id = id;
	}
	public int getHBAID() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getHBAID ####----" );}
		return this.hbaid;
	}
	public void setHBAID( int id ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setHBAID ####----" );}
		this.hbaid = id;
	}
	public int getNodeID() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getNodeID ####----" );}
		return this.nid;
	}
	public void setNodeID( int id ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setNodeID ####----" );}
		this.nid = id;
	}
	public String getLinkState() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getLinkState ####----" );}
		return this.lstate;
	}
	public void setLinkState( String state ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setLinkState ####----" );}
		this.lstate = state;
	}
	public String getStatus() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getStatus ####----" );}
		return this.status;
	}
	public void setStatus( String status ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setStatus ####----" );}
		this.status = status;
	}
	public String getHWRevision() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getHWRevision ####----" );}
		return this.hwrevision;
	}
	public void setHWRevision( String hw ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setHWRevision ####----" );}
		this.hwrevision = hw;
	}
	public String getFirmware() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getFirmware ####----" );}
		return this.firmware;
	}
	public void setFirmware( String firmware ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setFirmware ####----" );}
		this.firmware = firmware;
	}
	public String getRole() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getRole ####----" );}
		return this.role;
	}
	public void setRole( String role ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setRole ####----" );}
		this.role = role;
	}
	public String getSwitchVendor() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getSwitchVendor ####----" );}
		return this.svendor;
	}
	public void setSwitchVendor( String svendor ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setSwitchVendor ####----" );}
		this.svendor = svendor;
	}
	public String getVendor() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getVendor ####----" );}
		return this.vendor;
	}
	public void setVendor( String vendor ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setVendor ####----" );}
		this.vendor = vendor;
	}
	public String getModel() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getModel ####----" );}
		return this.model;
	}
	public void setModel( String model ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setModel ####----" );}
		this.model = model;
	}
	public boolean getEnabled() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getEnabled ####----" );}
		return this.enabled;
	}
	public void setEnabled( boolean enabled ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setEnabled ####----" );}
		this.enabled = enabled;
	}
	public String getWWPN() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getWWPN ####----" );}
		return this.wwpn;
	}
	public void setWWPN( String wwpn ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setWWPN ####----" );}
		this.wwpn = wwpn;
	}
	public String getWWNN() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getWWNN ####----" );}
		return this.wwnn;
	}
	public void setWWNN( String wwnn ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setWWNN ####----" );}
		this.wwnn = wwnn;
	}
	public long getSpeed() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getSpeed ####----" );}
		return this.speed;
	}
	public void setSpeed( long speed ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setSpeed ####----" );}
		this.speed = speed;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_FCNETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFCNetwork:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
