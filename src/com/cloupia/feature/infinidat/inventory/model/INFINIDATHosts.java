package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_hosts" )
public class INFINIDATHosts  implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATHosts.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Global ID" )
	@Persistent
	private int id;
	@ReportField( label = "Host Protocol" )
	@Persistent
	private String type;
	@ReportField( label = "Host Name" )
	@Persistent
	private String name;
	@ReportField( label = "Cluster ID" )
	@Persistent
	private int hid;
	@ReportField( label = "Cluster NAme" )
	@Persistent
	private String cname;
	@ReportField( label = "Ports Count" )
	@Persistent
	private int pcount;
	@ReportField( label = "Luns Count" )
	@Persistent
	private int lcount;
	@ReportField( label = "Creation Date" )
	@Persistent
	private long cdate;
	@ReportField( label = "Updated Date" )
	@Persistent
	private long udate;
	@ReportField( label = "Comment" )
	@Persistent
	private String comment;
	
	public INFINIDATHosts() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:INFINIDATHost ####----" );}
	}
	public INFINIDATHosts( String accountname, int id, String type, String name, int hid, String cname, int pcount, int lcount, long cdate, long udate, String comment ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:INFINIDATHost ####----" );}
		this.accountName = accountname;
		this.id = id;
		this.type = type;
		this.name = name;
		this.hid = hid;
		this.cname = cname;
		this.pcount = pcount;
		this.lcount = lcount;
		this.cdate = cdate;
		this.udate = udate;
		this.comment = comment;
	}
	public int getID() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getID ####----" );}
		return this.id;
	}
	public void setID( int id ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setID ####----" );}
		this.id = id;
	}
	public String getType() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getType ####----" );}
		return this.type;
	}
	public String getProtocol() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getProtocol ####----" );}
		return this.type;
	}
	public void setProtocol( String type ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setProtocol ####----" );}
		this.type = type;
	}
	public String getName() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getName ####----" );}
		return this.name;
	}
	public void setName( String name ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setName ####----" );}
		this.name = name;
	}
	public int getHostID() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getHostID ####----" );}
		return this.hid;
	}
	public void setHostID( int hid ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setHostID ####----" );}
		this.hid = hid;
	}
	public String getClusterName() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getClusterName ####----" );}
		return this.cname;
	}
	public void setClusterName( String name ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setClusterName ####----" );}
		this.cname = name;
	}
	public int getPortCount() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getPortCount ####----" );}
		return this.pcount;
	}
	public void setPortCount( int count ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setPortCount ####----" );}
		this.pcount = count;
	}
	public long getCreatedDate() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getCreatedDate ####----" );}
		return this.cdate;
	}
	public void setCreatedDate( long cdate ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setCreatedDate ####----" );}
		this.cdate = cdate;
	}
	public long getUpdatedDate() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getUpdatedDate ####----" );}
		return this.udate;
	}
	public void setUpdatedDate( long udate ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setUpdatedDate ####----" );}
		this.udate = udate;
	}
	public int getLunCount() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getLunCount ####----" );}
		return this.lcount;
	}
	public void setLunCount( int count ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setLunCount ####----" );}
		this.lcount = count;
	}
	public String getComment() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getComment ####----" );}
		return this.comment;
	}
	public void setComment( String comment ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setComment ####----" );}
		this.comment = comment;
	}
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATHost:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}