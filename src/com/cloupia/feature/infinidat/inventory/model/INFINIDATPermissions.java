package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.apache.log4j.Logger;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_filesystem_exports_permissions" )
public class INFINIDATPermissions implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATPermissions.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Filesystem ID" )
	@Persistent
	private int fid;
	@ReportField( label = "Access" )
	@Persistent
	private String access;
	@ReportField( label = "Client" )
	@Persistent
	private String client;
	@ReportField( label = "Root Squash" )
	@Persistent
	private boolean rootSquash;


	public INFINIDATPermissions() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:INFINIDATExports ####----" );}
	}
	public INFINIDATPermissions(String accountName, int fileSystemID, String access, String client, boolean root) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:INFINIDATExports ####----" );}
		this.accountName = accountName;
		this.fid = fileSystemID;
		this.access = access;
		this.client = client;
		this.rootSquash = root;
	}
	public int getFileSystemID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:getFileSystemID ####----" );}
		return this.fid;
	}
	public void setFileSystemID(int id) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:setFileSystemID ####----" );}
		this.fid = id;
	}
	public String getAccess() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:getType ####----" );}
		return this.access;
	}
	public void setAccess(String access) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:setType ####----" );}
		this.access = access;
	}
	public String getClient() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:getType ####----" );}
		return this.client;
	}
	public void setClient(String client) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:setType ####----" );}
		this.client = client;
	}
	public boolean getRootSquash() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:getSSD ####----" );}
		return this.rootSquash;
	}
	public void setRootSquash(boolean rootSquash) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:setSSD ####----" );}
		this.rootSquash = rootSquash;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPermissions:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
