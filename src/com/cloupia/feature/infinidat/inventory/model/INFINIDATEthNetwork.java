package com.cloupia.feature.infinidat.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_eth_network" )
public class INFINIDATEthNetwork  implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATEthNetwork.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Node ID" )
	@Persistent
	private int nodeid;
	@ReportField( label = "ID" )
	@Persistent
	private int id;
	@ReportField( label = "Port Number" )
	@Persistent
	private int port;
	@ReportField( label = "Name" )
	@Persistent
	private String name;
	@ReportField( label = "Role" )
	@Persistent
	private String role;
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
	@ReportField( label = "HW Address" )
	@Persistent
	private String hwaddr;
	@ReportField( label = "IPv4 Address" )
	@Persistent
	private String ipv4;
	@Persistent
	private String broadcast;
	@Persistent
	private String netmask;
	@ReportField( label = "Speed" )
	@Persistent
	private long speed;
	@ReportField( label = "Link State" )
	@Persistent
	private String lstate;
	
	public INFINIDATEthNetwork() {
		 if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:INFINIDATEthNetwork ####----" );}
	}
	public INFINIDATEthNetwork( String accountName, int nodeid, int id, int port, String name, String role, String model, String vendor, String firmware, String state, String hwaddr, String ipv4, String broadcast, String netmask, long speed, String linkstate ) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:INFINIDATEthNetwork ####----" );}
		this.accountName = accountName;
		this.nodeid = nodeid;
		this.id = id;
		this.port = port;
		this.name = name;
		this.role = role;
		this.model = model;
		this.vendor = vendor;
		this.firmware = firmware;
		this.state = state;
		this.hwaddr = hwaddr;
		this.ipv4 = ipv4;
		this.broadcast = broadcast;
		this.netmask = netmask;
		this.speed = speed;
		this.lstate = linkstate;
	}
	public String debug() { 
		String tmp = "";
		tmp += "AccountName: " + this.accountName + " | Node ID: " + this.nodeid + " | ID: " + this.id + " | Port: " + this.port + " | ";
		tmp += "Name: " + this.name + " | Role: " + this.role + " | Model: " + this.model + " | Vendor: " + this.vendor + " | Firmware: " + this.firmware + " | ";
		tmp += "State: " + this.state + " | HW Address: " + this.hwaddr + " | IPv4: " + this.ipv4 + " | Broadcast: " + this.broadcast + " | NetMask: " + this.netmask + " | ";
		tmp += "Speed: " + this.speed + " | Link State: " + this.lstate;
		return tmp;
	}
	public int getNodeID() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getNodeID ####----" );}
		return this.nodeid;
	}
	public void setNodeID(int nodeid) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setNodeID ####----" );}
		this.nodeid = nodeid;
	}
	public int getID() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getID ####----" );}
		return this.id;
	}
	public void setID(int id) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setID ####----" );}
		this.id = id;
	}
	public int getPortID() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getPortID ####----" );}
		return this.port;
	}
	public void setPortID(int port) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setPortID ####----" );}
		this.port = port;
	}
	public String getName() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getName ####----" );}
		return this.name;
	}
	public void setName(String name) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setName ####----" );}
		this.name = name;
	}
	public String getRole() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getRole ####----" );}
		return this.role;
	}
	public void setRole(String role) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setRole ####----" );}
		this.role = role;
	}
	public String getModel() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getModel ####----" );}
		return this.model;
	}
	public void setModel(String model) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setModel ####----" );}
		this.model = model;
	}
	public String getVendor() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getVendor ####----" );}
		return this.vendor;
	}
	public void setVendor(String vendor) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setVendor ####----" );}
		this.vendor = vendor;
	}
	public String getFirmware() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getFirmware ####----" );}
		return this.firmware;
	}
	public void setFirmware(String firmware) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setFirmware ####----" );}
		this.firmware = firmware;
	}
	public String getState() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getState ####----" );}
		return this.state;
	}
	public void setState(String state) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setState ####----" );}
		this.state = state;
	}
	public String getHWAddr() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getHWAddr ####----" );}
		return this.hwaddr;
	}
	public void setHWAddr(String hwaddr) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setHWAddr ####----" );}
		this.hwaddr = hwaddr;
	}
	public String getIPv4() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getIPv4 ####----" );}
		return this.ipv4;
	}
	public void setIPv4(String ipv4) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setIPv4 ####----" );}
		this.ipv4 = ipv4;
	}
	public String getBroadcast() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getBroadcast ####----" );}
		return this.broadcast;
	}
	public void setBroadcast(String broadcast) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setBroadcast ####----" );}
		this.broadcast = broadcast;
	}
	public String getNetmask() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getNetmask ####----" );}
		return this.netmask;
	}
	public void setNetmask(String netmask) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setNetmask ####----" );}
		this.netmask = netmask;
	}
	public long getSpeed() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getSpeed ####----" );}
		return this.speed;
	}
	public void setSpeed(long speed) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setSpeed ####----" );}
		this.speed = speed;
	}
	public String getLinkState() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getLinkState ####----" );}
		return this.lstate;
	}
	public void setLinkState(String lstate) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setLinkState ####----" );}
		this.lstate = lstate;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_ENETWORK && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEthNetwork:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
