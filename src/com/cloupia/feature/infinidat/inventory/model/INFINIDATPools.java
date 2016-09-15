package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_pools" )
public class INFINIDATPools implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATPools.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Pool ID" )
	@Persistent
	private int id;
	@ReportField( label = "Name" )
	@Persistent
	private String name;
	@ReportField( label = "State" )
	@Persistent
	private String state;
	@ReportField( label = "Created At" )
	@Persistent
	private long cdate;
	@Persistent
	private long udate;
	@ReportField( label = "Physical Capacity" )
	@Persistent
	private long pcapacity;
	@ReportField( label = "Virtual Capacity" )
	@Persistent
	private long vcapacity; 
	@ReportField( label = "Capacity Warning" )
	@Persistent
	private int cwarning;
	@Persistent
	private int ccritical;
	@ReportField( label = "Reserved Capacity" )
	@Persistent
	private long rcapacity;
	@ReportField( label = "Entity Count" )
	@Persistent
	private int ecount;
	@ReportField( label = "Volume Count" )
	@Persistent
	private int vcount;
	@ReportField( label = "Filesystem Count" )
	@Persistent
	private int fcount;
	@ReportField( label = "Snapshot Count" )
	@Persistent
	private int scount;
	@ReportField( label = "Clone Count" )
	@Persistent
	private int ccount;
	@Persistent
	private long apspace;
	@Persistent
	private long fpspace;
	@Persistent
	private long fvspace;
	@Persistent
	private String comment;
	
	public INFINIDATPools() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:INFINIDATPools ####----" );}
	}
	public INFINIDATPools( String accountName, int poolid, String name, String state, long cdate, long udate, long pcapacity, long vcapacity, int cwarning, int ccritical, long rcapacity, int ecount, int vcount, int fcount, int scount, int ccount, long apspace, long fpspace, long fvspace, String comment) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:INFINIDATPools ####----" );}
		this.accountName = accountName;
		this.id = poolid;
		this.name = name;
		this.state = state;
		this.cdate = cdate;
		this.udate = udate;
		this.pcapacity = pcapacity;
		this.vcapacity = vcapacity;
		this.cwarning = cwarning;
		this.ccritical = ccritical;
		this.rcapacity = rcapacity;
		this.ecount = ecount;
		this.vcount = vcount;
		this.fcount = fcount;
		this.scount = scount;
		this.ccount = ccount;
		this.apspace = apspace;
		this.fpspace = fpspace;
		this.fvspace = fvspace;
		this.comment = comment;
	}
	public int getPoolID() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getPoolID ####----" );}
		return this.id;
	}
	public void setPoolID(int id) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setPoolID ####----" );}
		this.id = id;
	}
	public String getName() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getName ####----" );}
		return this.name;
	}
	public void setName(String name) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setName ####----" );}
		this.name = name;
	}
	public String getState() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getState ####----" );}
		return this.state;
	}
	public void setState(String state) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setState ####----" );}
		this.state = state;
	}
	public long getCreateDate() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getCreateDate ####----" );}
		return this.cdate;
	}
	public void setCreateDate(long date) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setCreateDate ####----" );}
		this.cdate = date;
	}
	public long getUpdateDate() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getUpdateDate ####----" );}
		return this.udate;
	}
	public void setUpdateDate(long date) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setUpdateDate ####----" );}
		this.udate = date;
	}
	public long getPhysicalCapacity() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getPhysicalCapacity ####----" );}
		return this.pcapacity;
	}
	public void setPhysicalCapacity(long pcapacity) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setPhysicalCapacity ####----" );}
		this.pcapacity = pcapacity;
	}
	public long getVirtualCapacity() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getVirtualCapacity ####----" );}
		return this.vcapacity;
	}
	public void setVirtualCapacity(long vcapacity) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setVirtualCapacity ####----" );}
		this.vcapacity = vcapacity;
	}
	public int getCapacityWarning() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getCriticalWarning ####----" );}
		return this.cwarning;
	}
	public void setCapacityWarning(int cwarning) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setCriticalWarning ####----" );}
		this.cwarning = cwarning;
	}
	public int getCapacityCritical() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getCapacityCritical ####----" );}
		return this.ccritical;
	}
	public void setCapacityCritical(int ccritical) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setCapacityCritical ####----" );}
		this.ccritical = ccritical;
	}
	public long getReservedCapacity() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getReservedCapacity ####----" );}
		return this.rcapacity;
	}
	public void setReservedCapacity(long rcapacity) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setReservedCapacity ####----" );}
		this.rcapacity = rcapacity;
	}
	public int getEntityCount() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getEntityCount ####----" );}
		return this.ecount;
	}
	public void setEntityCount(int ecount) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setEntityCount ####----" );}
		this.ecount = ecount;
	}
	public int getVolumeCount() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getVolumeCount ####----" );}
		return this.vcount;
	}
	public void setVolumeCount(int vcount) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setVolumeCount ####----" );}
		this.vcount = vcount;
	}
	public int getFilesystemCount() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getFilesystemCount ####----" );}
		return this.fcount;
	}
	public void setFilesystemCount(int fcount) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setFilesystemCount ####----" );}
		this.fcount = fcount;
	}
	public int getSnapshotCount() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getSnapshotCount ####----" );}
		return this.scount;
	}
	public void setSnapshotCount(int scount) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setSnapshotCount ####----" );}
		this.scount = scount;
	}
	public int getClonesCount() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getClonesCount ####----" );}
		return this.ccount;
	}
	public void setClonesCount(int ccount) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setClonesCount ####----" );}
		this.ccount = ccount;
	}
	public long getAllocatedPhysicalSpace() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getAllocatedPhysicalSpace ####----" );}
		return this.apspace;
	}
	public void setAllocatedPhysicalSpace(long apspace) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setAllocatedPhysicalSpace ####----" );}
		this.apspace = apspace;
	}
	public long getFreePhysicalSpace() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getFreePhysicalSpace ####----" );}
		return this.fpspace;
	}
	public void setFreePhysicalSpace(long fpspace) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getFreePhysicalSpace ####----" );}
		this.fpspace = fpspace;
	}
	public long getFreeVirtualSpace() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getFreeVirtualSpace ####----" );}
		return this.fvspace;
	}
	public void setFreeVirtualSpace(long fvspace) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setFreeVirtualSpace ####----" );}
		this.fvspace = fvspace;
	}
	public String getComment() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getComment ####----" );}
		return this.comment;
	}
	public void setComment(String comment) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setComment ####----" );}
		this.comment = comment;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPools:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
