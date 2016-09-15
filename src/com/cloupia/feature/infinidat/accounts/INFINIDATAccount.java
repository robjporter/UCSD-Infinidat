package com.cloupia.feature.infinidat.accounts;

import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.cIaaS.network.model.DeviceCredential;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.model.cIM.InfraAccount;
import com.cloupia.service.cIM.inframgr.collector.view2.ConnectorCredential;
import com.cloupia.service.cIM.inframgr.forms.wizard.FieldValidation;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;
import com.cloupia.service.cIM.inframgr.forms.wizard.HideFieldOnCondition;

@SuppressWarnings("unused")
@PersistenceCapable(detachable = "true", table = "infinidat_account")
public class INFINIDATAccount extends AbstractInfraAccount implements ConnectorCredential {
	static Logger logger = Logger.getLogger( DeviceCredential.class );

	@Persistent( sequence = "deviceidseq", valueStrategy = IdGeneratorStrategy.INCREMENT )
	private long deviceId;
	@Persistent
	@FormField( label = "Device IP", help = "Device IP", mandatory = true )
	private String deviceIp;
	@Persistent
	@FormField( label = "Use Credential Policy", validate = true, help = "Select if you want to use policy to give the credentials.", type = FormFieldDefinition.FIELD_TYPE_BOOLEAN )
	private boolean isCredentialPolicy = false;
	@Persistent
	@FormField( label = "Protocol", help = "Protocol", type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, validate = true, lov = { "http", "https" })
	@HideFieldOnCondition(field = "isCredentialPolicy", op = FieldValidation.OP_EQUALS, value = "true" )
	private String protocol = "http";
	@FormField( label = "Port", help = "Port Number", type = FormFieldDefinition.FIELD_TYPE_TEXT )
	@Persistent
	@HideFieldOnCondition( field = "isCredentialPolicy", op = FieldValidation.OP_EQUALS, value = "true" )
	private String port = "80";
	@Persistent
	@FormField( label = "Login", help = "Login" )
	@HideFieldOnCondition( field = "isCredentialPolicy", op = FieldValidation.OP_EQUALS, value = "true" )
	private String login;
	@Persistent
	@FormField( label = "Password", help = "Password", type = FormFieldDefinition.FIELD_TYPE_PASSWORD )
	@HideFieldOnCondition( field = "isCredentialPolicy", op = FieldValidation.OP_EQUALS, value = "true" )
	private String password;

	public long getDeviceId() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:getDeviceId ####----" );}
		return this.deviceId;
	}
	public void setDeviceId( long deviceId ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setDeviceId ####----" );}
		this.deviceId = deviceId;
	}
	public String getDeviceIp() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:getDeviceIp ####----" );}
		return deviceIp;
	}
	public void setDeviceIp( String deviceIp ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setDeviceIp ####----" );}
		this.deviceIp = deviceIp;
	}
	public boolean isCredentialPolicy() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:isCredentialPolicy ####----" );}
		return this.isCredentialPolicy;
	}
	public void setCredentialPolicy( boolean isCredentialPolicy ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setCredentialPolicy ####----" );}
		this.isCredentialPolicy = isCredentialPolicy;
	}
	public String getProtocol() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:getProtocol ####----" );}
		return this.protocol;
	}
	public void setProtocol( String protocol ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setProtocol ####----" );}
		this.protocol = protocol;
	}
	public String getPort() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:getPort ####----" );}
		return this.port;
	}
	public void setPort( String port ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setPort ####----" );}
		this.port = port;
	}
	public String getLogin() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:getLogin ####----" );}
		return this.login;
	}
	public void setLogin(String login) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setLogin ####----" );}
		this.login = login;
	}
	public String getPassword() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:getPassword ####----" );}
		return this.password;
	}
	public void setPassword(String password) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setPassword ####----" );}
		this.password = password;
	}
	@Override
	public InfraAccount toInfraAccount() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:toInfraAccount ####----" );}
		try {
			ObjStore<InfraAccount> store = ObjStoreHelper.getStore(InfraAccount.class);
			String cquery = "server == '" + deviceIp + "' && userID == '" + login + "' && transport == '" + protocol + "' && port == " + Integer.parseInt(port);
			List<InfraAccount> accList = store.query(cquery);
			
			if( accList != null && accList.size() > 0 ) {
				return accList.get(0);
			} else {
				if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:toInfraAccount ####----" );}
				return null;
			}
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAccount:toInfraAccount::ERROR:::: ####----", e );}
		}
		return null;
	}
	@Override
	public String getServerAddress() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:getServerAddress ####----" );}
		return this.getDeviceIp();
	}
	@Override
	public String getPolicy() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:getPolicy ####----" );}
		return null;
	}
	@Override
	public void setPolicy( String policy ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setPolicy ####----" );}
	}
	@Override
	public void setPort( int port ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setPort ####----" );}
	}
	@Override
	public void setUsername( String username ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccount:setUsername ####----" );}
	}
}