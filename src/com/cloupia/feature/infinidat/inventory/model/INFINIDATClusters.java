package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_clusters" )
public class INFINIDATClusters implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATData.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Cluster ID" )
	@Persistent
	private int id = 0;
	@ReportField( label = "Lun Count" )
	@Persistent
	private int lunCount = 0;
	@ReportField( label = "Host Count" )
	@Persistent
	private int hostCount = 0;
	@ReportField( label = "Name" )
	@Persistent
	private String name = "";
	@ReportField( label = "Created At" )
	@Persistent
	private long cdate;
	@ReportField( label = "Updated At" )
	@Persistent
	private long udate;
	@ReportField( label = "Comment" )
	@Persistent
	private String comment;

	public INFINIDATClusters() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:INFINIDATClusters ####----" );}
	}
	public INFINIDATClusters(String accountName,int id, String name, int lcount, int hcount, long cdate, long udate, String comment) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:INFINIDATClusters ####----" );}
		this.accountName = accountName;
		this.id = id;
		this.name = name;
		this.lunCount = lcount;
		this.hostCount = hcount;
		this.cdate = cdate;
		this.udate = udate;
		this.comment = comment;
	}
	public int getID() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getID ####----" );}
		return this.id;
	}
	public void setID(int id) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:setID ####----" );}
		this.id = id;
	}
	public String getName() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getName ####----" );}
		return this.name;
	}
	public void setName(String name) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:setName ####----" );}
		this.name = name;
	}
	public int getLunCount() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getLunCount ####----" );}
		return this.lunCount;
	}
	public void setLunCount(int count) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:setLunCount ####----" );}
		this.lunCount = count;
	}
	public int getHostCount() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getHostCount ####----" );}
		return this.hostCount;
	}
	public void setHostCount(int count) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:setHostCount ####----" );}
		this.hostCount = count;
	}
	public long getCreateDate() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getCreateDate ####----" );}
		return this.cdate;
	}
	public void setCreateDate(long date) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:setCreateDate ####----" );}
		this.cdate = date;
	}
	public long getUpdateDate() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getUpdateDate ####----" );}
		return this.udate;
	}
	public void setUpdateDate(long date) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:setUpdateDate ####----" );}
		this.udate = date;
	}
	public String getComment() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getComment ####----" );}
		return this.comment;
	}
	public void setComment(String comment) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:setComment ####----" );}
		this.comment = comment;
	}
	
	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getAccountName ####---- AccountName: " + this.accountName );}
		return this.accountName;
	}
	@Override
	public void setAccountName(String accountName) {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getAccountName ####---- AccountName: " + accountName );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATClusters:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
