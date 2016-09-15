package com.cloupia.feature.infinidat.device;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_device_info" )
public class INFINIDATDeviceInfo {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceInfo.class );
	@Persistent
	private String accountName;
	@Persistent
	private String id;
	@Persistent
	private String ip;
	@Persistent
	private String name;
	@Persistent
	private String uptime;
	@Persistent
	private String globalid;
	@Persistent
	private String serialnumber;
	@Persistent
	private String model;
	@Persistent
	private String fwversion;
	@Persistent
	private String free;
	@Persistent
	private String capacity;
	@Persistent
	private String vendor;
	@Persistent
	private String status;
	
	public INFINIDATDeviceInfo() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:INFINIDATDeviceInfo ####----" );}
		this.id = "Unknown";
		this.globalid = "Unknown";
		this.serialnumber = "Unknown";
		this.name = "Unknown";
		this.model = "Unknown";
		this.uptime = "Unknown";
		this.fwversion = "Unknown";
		this.capacity = "Unknown";
		this.free = "Uknown";
		this.vendor = "Unknown";
		this.status = "Unknown";
	}
	public INFINIDATDeviceInfo( String accountname, String id, String ip, String name, String uptime, String globalid, String serialnumber, String model, String fwversion, String capacity, String free, String vendor, String status ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:INFINIDATDeviceInfo ####----" );}
		this.accountName = accountname;
		this.id = id;
		this.ip = ip;
		this.name = name;
		this.uptime = uptime;
		this.globalid = globalid;
		this.serialnumber = serialnumber;
		this.model = model;
		this.fwversion = fwversion;
		this.capacity = capacity;
		this.free = free;
		this.vendor = vendor;
		this.status = status;
	}
	public String getAccountName(){
		return this.accountName;		
	}
	public void setAccountName( String accountName ){
		this.accountName = accountName;		
	}
	public String getID() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getID ####----" );}
		return this.id;
	}
	public void setID( String id ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setID ####----" );}
		this.id = id;
	}
	public String getIP() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getIP ####----" );}
		return this.ip;
	}
	public void setIP( String ip ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setIP ####----" );}
		this.ip = ip;
	}
	public String getName() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getID ####----" );}
		return this.name;
	}
	public void setName( String name ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setID ####----" );}
		this.name = name;
	}
	public String getUptime() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getID ####----" );}
		return this.uptime;
	}
	public void setUptime( String uptime ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setID ####----" );}
		this.uptime = uptime;
	}
	public String getGlobalID() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getGlobalID ####----" );}
		return this.globalid;
	}
	public void setglobalID( String globalid ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setGlobalID ####----" );}
		this.globalid = globalid;
	}
	public String getSerialNumber() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getSerialNumber ####----" );}
		return this.serialnumber;
	}
	public void setSerialNumber( String serialnumber ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setSerialNumber ####----" );}
		this.serialnumber = serialnumber;
	}
	public String getModel() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getModel ####----" );}
		return this.model;
	}
	public void setModel( String model ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setModel ####----" );}
		this.model = model;
	}
	public String getFWVersion() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getFWVersion ####----" );}
		return this.fwversion;
	}
	public void setFWVersion( String fwversion ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setFWVersion ####----" );}
		this.fwversion = fwversion;
	}
	public String getCapacity() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getID ####----" );}
		return this.capacity;
	}
	public void setCapacity( String capacity ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setID ####----" );}
		this.capacity = capacity;
	}
	public String getFree() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getID ####----" );}
		return this.free;
	}
	public void setFree( String free ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setID ####----" );}
		this.free = free;
	}
	public String getVendor() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getVendor ####----" );}
		return this.vendor;
	}
	public void setVendor( String vendor ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setVendor ####----" );}
		this.vendor = vendor;
	}
	public String getStatus() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:getStatus ####----" );}
		return this.status;
	}
	public void setStatus( String status ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceInfo:setStatus ####----" );}
		this.status = status;
	}
}