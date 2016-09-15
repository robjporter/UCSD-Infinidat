package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_event_logs" )
public class INFINIDATEventLog  implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATEventLog.class );
	@Persistent
	private String accountName;	
	@ReportField( label = "" )
	@Persistent
	private String reporter;
	@ReportField( label = "ID" )
	@Persistent
	private int id;
	@ReportField( label = "Date/Time" )
	@Persistent
	private long datetime;
	@ReportField( label = "Level" )
	@Persistent
	private String level;
	@ReportField( label = "Visibility" )
	@Persistent
	private String visibility;
	@ReportField( label = "Source node" )
	@Persistent
	private int node;
	@ReportField( label = "System Version" )
	@Persistent
	private String version;
	@ReportField( label = "Description" )
	@Persistent
	private String description;
	
	public INFINIDATEventLog() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:INFINIDATEventLog ####----" );}
	}
	public INFINIDATEventLog( String accountname, String reporter, int id, long datetime, String level, String visibility, int node, String version, String description ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:INFINIDATEventLog ####----" );}
		this.accountName = accountname;
		this.reporter = reporter;
		this.id = id;
		this.datetime = datetime;
		this.level = level;
		this.visibility = visibility;
		this.node = node;
		this.version = version;
		this.description = description;
	}
	public String getReporter() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getReporter ####----" );}
		return this.reporter;
	}
	public void setReporter( String reporter ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:setReporter ####----" );}
		this.reporter = reporter;
	}
	public int getID() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getID ####----" );}
		return this.id;
	}
	public void setID( int id ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:setID ####----" );}
		this.id = id;
	}
	public long getDateTime() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getDateTime ####----" );}
		return this.datetime;
	}
	public void setDateTime( long datetime ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:setDateTime ####----" );}
		this.datetime = datetime;
	}
	public String getLevel() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getLevel ####----" );}
		return this.level;
	}
	public void setLevel( String level ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:setLevel ####----" );}
		this.level = level;
	}
	public String getVisibility() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getLevel ####----" );}
		return this.visibility;
	}
	public void setVisibility( String visibility ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:setLevel ####----" );}
		this.visibility = visibility;
	}
	public int getNode() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getNode ####----" );}
		return this.node;
	}
	public void setNode( int node ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:setNode ####----" );}
		this.node = node;
	}
	public String getVersion() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getLevel ####----" );}
		return this.version;
	}
	public void setVersion( String version ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:setLevel ####----" );}
		this.version = version;
	}
	public String getDescription() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getDescription ####----" );}
		return this.description;
	}
	public void setDescription( String description ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:setDescription ####----" );}
		this.description = description;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEventLog:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
