package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.apache.log4j.Logger;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_users")
public class INFINIDATUsers implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATVolume.class );
	
	@Persistent
	private String accountName;
	@ReportField( label = "Name" )
	@Persistent
	private String name;
	@ReportField( label = "Roles" )
	@Persistent
	private String roles;
	@ReportField( label = "ID" )
	@Persistent
	private int id;
	@ReportField( label = "Role" )
	@Persistent
	private String role;
	@ReportField( label = "Type" )
	@Persistent
	private String type;
	@ReportField( label = "Email" )
	@Persistent
	private String email;
	@ReportField( label = "Comment" )
	@Persistent
	private String comment;

	public INFINIDATUsers() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:INFINIDATUsers ####----" );}
	}
	public INFINIDATUsers(String accountName, int id, String name, String roles, String role, String type, String email, String comment ) {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:INFINIDATUsers ####----" );}
		this.accountName = accountName;
		this.id = id;
		this.name = name;
		this.roles = roles;
		this.role = role;
		this.type = type;
		this.email = email;
		this.comment = comment;
	}

	public int getID() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:getID ####----" );}
		return this.id;
	}
	public void setID( int id ) {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:setID ####----" );}
		this.id = id;
	}
	public String getName() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:getName ####----" );}
		return this.name;
	}
	public void setName( String name ) {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:setName ####----" );}
		this.name = name;
	}
	public String getRoles() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:getRoles ####----" );}
		return this.roles;
	}
	public void setRoles( String roles ) {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:setRoles ####----" );}
		this.roles = roles;
	}
	public String getRole() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:getRole ####----" );}
		return this.role;
	}
	public void setRole( String role ) {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:setRole ####----" );}
		this.role = role;
	}
	public String getType() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:getType ####----" );}
		return this.type;
	}
	public void setType( String type ) {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:setType ####----" );}
		this.type = type;
	}
	public String getEmail() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:getMail ####----" );}
		return this.email;
	}
	public void setEmail( String email ) {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:setEmail ####----" );}
		this.email = email;
	}
	public String getComment() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:getComments ####----" );}
		return this.comment;
	}
	public void setComment( String comment ) {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:setComments ####----" );}
		this.comment = comment;
	}

	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:getAccountName ####----" );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:setAccountName ####----" );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_USERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATUsers:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
