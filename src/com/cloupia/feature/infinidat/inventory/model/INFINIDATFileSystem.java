package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_filesystem" )
public class INFINIDATFileSystem implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATFileSystem.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Filesystem ID" )
	@Persistent
	private int id = 0;
	@ReportField( label = "NFS ID" )
	@Persistent
	private int nfsid = 0;
	@ReportField( label = "Pool ID" )
	@Persistent
	private int poolid = 0;
	@ReportField( label = "Pool Name" )
	@Persistent
	private String poolname = "";
	@ReportField( label = "Name" )
	@Persistent
	private String name = "";
	@ReportField( label = "Number Blocks" )
	@Persistent
	private long numblocks;
	@ReportField( label = "Size" )
	@Persistent
	private long size;
	@ReportField( label = "ssd" )
	@Persistent
	private boolean ssd;
	@ReportField( label = "Parent ID" )
	@Persistent
	private int parentid;
	@ReportField( label = "Type" )
	@Persistent
	private String type;
	@ReportField( label = "Used" )
	@Persistent
	private long used;
	@ReportField( label = "Dataset Type" )
	@Persistent
	private String datasettype;
	@ReportField( label = "Data" )
	@Persistent
	private int data;
	@ReportField( label = "Provision Type" )
	@Persistent
	private String provtype;
	@ReportField( label = "Security" )
	@Persistent
	private String security;
	@ReportField( label = "Protected" )
	@Persistent
	private boolean protect;
	@ReportField( label = "Mapped" )
	@Persistent
	private boolean mapped;
	@ReportField( label = "Created At" )
	@Persistent
	private long cdate;
	@ReportField( label = "Updated At" )
	@Persistent
	private long udate;
	@ReportField( label = "Comment" )
	@Persistent
	private String comment;
	

	public INFINIDATFileSystem() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:INFINIDATFileSystem ####----" );}
	}
	public INFINIDATFileSystem( String accountName, int id, int nfsid, int poolid, String poolname, String name, long numblocks, long size, boolean ssd, int parentid, String type, long used, String datasettype, int data, String provtype, String security, boolean protect, boolean mapped, long cdate, long udate, String comment) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:INFINIDATFileSystem ####----" );}
		this.accountName = accountName;
		this.id = id;
		this.nfsid = nfsid;
		this.poolid = poolid;
		this.poolname = poolname;
		this.name = name;
		this.numblocks = numblocks;
		this.size = size;
		this.ssd = ssd;
		this.parentid = parentid;
		this.type = type;
		this.used = used;
		this.datasettype = datasettype;
		this.data = data;
		this.provtype = provtype;
		this.security = security;
		this.protect = protect;
		this.mapped = mapped;
		this.cdate = cdate;
		this.udate = udate;
		this.comment = comment;
	}
	
	public int getID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getID ####----" );}
		return this.id;
	}
	public void setID(int id) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setID ####----" );}
		this.id = id;
	}
	public int getNFSID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getID ####----" );}
		return this.nfsid;
	}
	public void setNFSID(int id) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setID ####----" );}
		this.nfsid = id;
	}
	public int getPoolID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getID ####----" );}
		return this.poolid;
	}
	public void setPoolID(int id) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setID ####----" );}
		this.poolid = id;
	}
	public String getPoolName() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getPoolName ####----" );}
		return this.poolname;
	}
	public void setPoolName(String name) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setPoolName ####----" );}
		this.poolname = name;
	}
	public String getName() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getName ####----" );}
		return this.name;
	}
	public void setName(String name) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setName ####----" );}
		this.name = name;
	}
	public long getNumBlocks() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getNumBlocks ####----" );}
		return this.numblocks;
	}
	public void setNumBlocks(long blocks) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setNumBlocks ####----" );}
		this.numblocks = blocks;
	}
	public long getSize() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getSize ####----" );}
		return this.size;
	}
	public void setSize(long size) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setSize ####----" );}
		this.size = size;
	}
	public boolean getSSD() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getSSD ####----" );}
		return this.ssd;
	}
	public void setSSD(boolean ssd) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setSSD ####----" );}
		this.ssd = ssd;
	}
	public int getParentID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getParentID ####----" );}
		return this.parentid;
	}
	public void setParentID(int id) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setParentID ####----" );}
		this.parentid = id;
	}
	public String getType() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getType ####----" );}
		return this.type;
	}
	public void setType(String type) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setType ####----" );}
		this.type = type;
	}
	public long getUsed() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getUsed ####----" );}
		return this.used;
	}
	public void setUsed(long used) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setUsed ####----" );}
		this.used = used;
	}
	public String getDatasetType() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getDatasetType ####----" );}
		return this.datasettype;
	}
	public void setDatasetType(String type) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setDatasetType ####----" );}
		this.datasettype = type;
	}
	public int getData() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getData ####----" );}
		return this.data;
	}
	public void setData(int data) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setData ####----" );}
		this.data = data;
	}
	public String getProvType() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getProvType ####----" );}
		return this.provtype;
	}
	public void setProvType(String type) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setProvType ####----" );}
		this.provtype = type;
	}
	public String getSecurity() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getSecurity ####----" );}
		return this.security;
	}
	public void setSecurity(String security) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setSecurity ####----" );}
		this.security = security;
	}
	public boolean getProtected() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getProtected ####----" );}
		return this.protect;
	}
	public void setProtected(boolean protect) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setProtected ####----" );}
		this.protect = protect;
	}
	public boolean getMapped() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getMapped ####----" );}
		return this.mapped;
	}
	public void setMapped(boolean mapped) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setMapped ####----" );}
		this.mapped = mapped;
	}
	public long getCreateDate() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getCreateDate ####----" );}
		return this.cdate;
	}
	public void setCreateDate(long date) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setCreateDate ####----" );}
		this.cdate = date;
	}
	public long getUpdateDate() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getUpdateDate ####----" );}
		return this.udate;
	}
	public void setUpdateDate(long date) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setUpdateDate ####----" );}
		this.udate = date;
	}
	public String getComment() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:getComment ####----" );}
		return this.comment;
	}
	public void setComment(String comment) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATFileSystem:setComment ####----" );}
		this.comment = comment;
	}
	@Override
	public String getAccountName() {
		logger.info( "----#### INFINIDATFileSystem:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		logger.info( "----#### INFINIDATFileSystem:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		logger.info( "----#### INFINIDATFileSystem:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}