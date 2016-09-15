package com.cloupia.feature.infinidat.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_volume")
public class INFINIDATVolume implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATVolume.class );
	
	@Persistent
	private String accountName;
	@ReportField( label = "Name" )
	@Persistent
	private String name;
	@ReportField( label = "Size" )
	@Persistent
	private long size;
	@ReportField( label = "ID" )
	@Persistent
	private int id;
	@ReportField( label = "Pool ID" )
	@Persistent
	private int poolid;
	@ReportField( label = "Pool Name" )
	@Persistent
	private String poolName;
	@ReportField( label = "Creation Date" )
	@Persistent
	private long created_at;
	@ReportField( label = "Updated Date" )
	@Persistent
	private long updated_at;
	@ReportField( label = "Provision Type" )
	@Persistent
	private String provtype;
	@Persistent
	private String type;
	@ReportField( label = "Used" )
	@Persistent
	private long used;
	@ReportField( label = "Mapped" )
	@Persistent
	private Boolean mapped;
	@Persistent
	private String serial;
	@ReportField( label = "Protected" )
	@Persistent
	private Boolean write_protected;
	@ReportField( label = "SSD" )
	@Persistent
	private Boolean ssd_enabled;
	@ReportField( label = "Comment" )
	@Persistent
	private String comment;

	public INFINIDATVolume() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:INFINIDATVolume ####----" );}
	}
	public INFINIDATVolume( String accountName, String name, long size, int id, int poolid, String poolname, long cdate, long udate, String provtype, String type, long used, Boolean mapped, String serial, Boolean wprotect, Boolean ssd, String comment ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:INFINIDATVolume ####----" );}
		this.accountName = accountName;
		this.name = name;
		this.size = size;
		this.id = id;
		this.poolid = poolid;
		this.poolName = poolname;
		this.created_at = cdate;
		this.updated_at = udate;
		this.provtype = provtype;
		this.type = type;
		this.used = used;
		this.mapped = mapped;
		this.serial = serial;
		this.write_protected = wprotect;
		this.ssd_enabled = ssd;
		this.comment = comment;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setAccountName ####----" );}
		this.accountName = accountName;
	}
	public String getName() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getName ####----" );}
		return this.name;
	}
	public void setName( String name ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setName ####----" );}
		this.name = name;
	}
	public long getSize() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getSize ####----" );}
		return this.size;
	}
	public void setSize( long size ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setSize ####----" );}
		this.size = size;
	}
	public int getID() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getID ####----" );}
		return this.id;
	}
	public void setID( int id ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setID ####----" );}
		this.id = id;
	}
	public int getPoolID() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getID ####----" );}
		return this.poolid;
	}
	public void setPoolID( int poolid ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setID ####----" );}
		this.poolid = poolid;
	}
	public String getPoolName() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getPoolName ####----" );}
		return this.poolName;
	}
	public void setPoolName( String name ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setPoolName ####----" );}
		this.poolName = name;
	}
	public long getCreationDate() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getCreationDate ####----" );}
		return this.created_at;
	}
	public void setCreationDate( long creationDate ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setCreationDate ####----" );}
		this.created_at = creationDate;
	}
	public long getUpdatedDate() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getUpdatedDate ####----" );}
		return this.updated_at;
	}
	public void setUpdatedDate( int creationDate ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setUpdatedDate ####----" );}
		this.updated_at = creationDate;
	}
	public String getVolumeProvType() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getVolumeProvType ####----" );}
		return this.provtype;
	}
	public void setVolumeProvType(String provtype) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setVolumeProvType ####----" );}
		this.provtype = provtype;
	}
	public String getVolumeType() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getVolumeType ####----" );}
		return this.type;
	}
	public void setVolumeType( String type ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setVolumeType ####----" );}
		this.type = type;
	}
	public long getUsed() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getUsed ####----" );}
		return this.used;
	}
	public void setUsed( long used ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setUsed ####----" );}
		this.used = used;
	}
	public Boolean getMapped() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getMapped ####----" );}
		return this.mapped;
	}
	public void setMapped( Boolean mapped ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setMapped ####----" );}
		this.mapped = mapped;
	}
	public String getSerial() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getSerial ####----" );}
		return this.serial;
	}
	public void setSerial( String serial ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setSerial ####----" );}
		this.serial = serial;
	}
	public Boolean getWriteProtected() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getWriteProtected ####----" );}
		return this.write_protected;
	}
	public void setWriteProtected( Boolean wprotect ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setWriteProtected ####----" );}
		this.write_protected = wprotect;
	}
	public Boolean getSSDEnabled() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getSSDEnabled ####----" );}
		return this.ssd_enabled;
	}
	public void setSSDEnabled( Boolean ssd ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setSSDEnabled ####----" );}
		this.ssd_enabled = ssd;
	}
	public String getComment() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getSerial ####----" );}
		return this.comment;
	}
	public void setComment( String comment ) {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:setSerial ####----" );}
		this.comment = comment;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATVolume:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
