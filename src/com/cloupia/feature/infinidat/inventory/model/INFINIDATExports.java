package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.apache.log4j.Logger;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true", table = "infinidat_filesystem_exports" )
public class INFINIDATExports implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATExports.class );
	@Persistent
	private String accountName;
	@ReportField( label = "ID" )
	@Persistent
	private int id;
	@ReportField( label = "Filesystem ID" )
	@Persistent
	private int fid;
	@ReportField( label = "Filesystem Name" )
	@Persistent
	private String fName;
	@ReportField( label = "Enabled" )
	@Persistent
	private boolean enabled;
	@ReportField( label = "Export Path" )
	@Persistent
	private String exportPath;
	@ReportField( label = "Inner Path" )
	@Persistent
	private String innerPath;
	@ReportField( label = "Anonymous UID" )
	@Persistent
	private int anonymousUID;
	@ReportField( label = "Anonymous GID" )
	@Persistent
	private int anonymousGID;
	@ReportField( label = "Transport Protocol" )
	@Persistent
	private String transportProtocol;
	@ReportField( label = "Max Read" )
	@Persistent
	private int maxRead;
	@ReportField( label = "Max Write" )
	@Persistent
	private int maxWrite;
	@ReportField( label = "Perf Read" )
	@Persistent
	private int perfRead;
	@ReportField( label = "Perf Write" )
	@Persistent
	private int perfWrite;
	@ReportField( label = "Perf ReadDir" )
	@Persistent
	private int perfReadDir;
	@ReportField( label = "Privilege Port" )
	@Persistent
	private boolean privilegedPort;
	@ReportField( label = "All Users Anonymous" )
	@Persistent
	private boolean allUsersAnonymous;
	@ReportField( label = "32bit File ID" )
	@Persistent
	private boolean bitFileID;
	@ReportField( label = "Created At" )
	@Persistent
	private long cdate;
	@ReportField( label = "Updated At" )
	@Persistent
	private long udate;
	@ReportField( label = "Comment" )
	@Persistent
	private String comment;
	
	public INFINIDATExports() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:INFINIDATExports ####----" );}
	}
	public INFINIDATExports(String accountName, int id, int fileid, String fname, boolean enabled, String exportPath, String innerPath, int anonymousUID, int anonymousGID, String protocol, int maxread, int maxwrite, int perfread, int perfwrite, int perfreaddir, boolean port, boolean anonymous, boolean bitfile, long cdate, long udate, String comment) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:INFINIDATExports ####----" );}
		this.accountName = accountName;
		this.id = id;
		this.fid = fileid;
		this.fName = fname;
		this.enabled = enabled;
		this.exportPath = exportPath;
		this.innerPath = innerPath;
		this.anonymousUID = anonymousUID;
		this.anonymousGID = anonymousGID;
		this.transportProtocol = protocol;
		this.maxRead = maxread;
		this.maxWrite = maxwrite;
		this.perfRead = perfread;
		this.perfWrite = perfwrite;
		this.perfReadDir = perfreaddir;
		this.privilegedPort = port;
		this.allUsersAnonymous = anonymous;
		this.bitFileID = bitfile;
		this.cdate = cdate;
		this.udate = udate;
		this.comment = comment;
	}
	public int getID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getID ####----" );}
		return this.id;
	}
	public void setID(int id) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setID ####----" );}
		this.id = id;
	}
	public int getFileSystemID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getFileSystemID ####----" );}
		return this.fid;
	}
	public void setFileSystemID(int id) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setFileSystemID ####----" );}
		this.fid = id;
	}
	public String getFileSystemName() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getType ####----" );}
		return this.fName;
	}
	public void setFileSystemName(String filesystem) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setType ####----" );}
		this.fName = filesystem;
	}
	public boolean getEnabled() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getSSD ####----" );}
		return this.enabled;
	}
	public void setEnabled(boolean enabled) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setSSD ####----" );}
		this.enabled = enabled;
	}
	public String getExportPath() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getType ####----" );}
		return this.exportPath;
	}
	public void setExportPath(String exportPath) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setType ####----" );}
		this.exportPath = exportPath;
	}
	public String getInnerPath() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getType ####----" );}
		return this.innerPath;
	}
	public void setInnerPath(String innerPath) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setType ####----" );}
		this.innerPath = innerPath;
	}
	public int getAnonymousUID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getParentID ####----" );}
		return this.anonymousUID;
	}
	public void setAnonymousUID(int anonymousUID) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setParentID ####----" );}
		this.anonymousUID = anonymousUID;
	}
	public int getAnonymousGID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getParentID ####----" );}
		return this.anonymousGID;
	}
	public void setAnonymousGID(int anonymousGID) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setParentID ####----" );}
		this.anonymousGID = anonymousGID;
	}
	public String getTransportProtocol() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getType ####----" );}
		return this.transportProtocol;
	}
	public void setTransportProtocol(String transportProtocol) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setType ####----" );}
		this.transportProtocol = transportProtocol;
	}
	public int getMaxRead() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getParentID ####----" );}
		return this.maxRead;
	}
	public void setMaxRead(int maxRead) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setParentID ####----" );}
		this.maxRead = maxRead;
	}
	public int getMaxWrite() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getParentID ####----" );}
		return this.maxWrite;
	}
	public void setMaxWrite(int maxWrite) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setParentID ####----" );}
		this.maxWrite = maxWrite;
	}
	public int getPerfRead() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getParentID ####----" );}
		return this.perfRead;
	}
	public void setPerfRead(int prefRead) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setParentID ####----" );}
		this.perfRead = prefRead;
	}
	public int getPerfWrite() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getParentID ####----" );}
		return this.perfWrite;
	}
	public void setPerfWrite(int perfWrite) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setParentID ####----" );}
		this.perfWrite = perfWrite;
	}
	public int getPerfReadDir() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getParentID ####----" );}
		return this.perfReadDir;
	}
	public void setPerfReadDir(int perfReadDir) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setParentID ####----" );}
		this.perfReadDir = perfReadDir;
	}
	public boolean getPrivilegedPort() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getSSD ####----" );}
		return this.privilegedPort;
	}
	public void setPrivilegedPort(boolean privilegedPort) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setSSD ####----" );}
		this.privilegedPort = privilegedPort;
	}
	public boolean getAllUsersAnonymous() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getSSD ####----" );}
		return this.allUsersAnonymous;
	}
	public void setAllUsersAnonymous(boolean allUsersAnonymous) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setSSD ####----" );}
		this.allUsersAnonymous = allUsersAnonymous;
	}
	public boolean getBitFieldID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getSSD ####----" );}
		return this.bitFileID;
	}
	public void setBitFieldID(boolean bitFileID) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setSSD ####----" );}
		this.bitFileID = bitFileID;
	}
	public long getCreateDate() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getCreateDate ####----" );}
		return this.cdate;
	}
	public void setCreateDate(long date) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setCreateDate ####----" );}
		this.cdate = date;
	}
	public long getUpdateDate() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getUpdateDate ####----" );}
		return this.udate;
	}
	public void setUpdateDate(long date) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setUpdateDate ####----" );}
		this.udate = date;
	}
	public String getComment() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getComment ####----" );}
		return this.comment;
	}
	public void setComment(String comment) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setComment ####----" );}
		this.comment = comment;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATExports:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
