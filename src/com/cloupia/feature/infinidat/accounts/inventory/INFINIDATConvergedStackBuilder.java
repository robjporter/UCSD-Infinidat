package com.cloupia.feature.infinidat.accounts.inventory;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.INFINIDATDeviceInfo;
import com.cloupia.feature.infinidat.inventory.custom.INFINIDATConvergedStackComponentDetail;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.model.cIM.ConvergedStackComponentDetail;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reports.contextresolve.ConvergedStackComponentBuilderIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_stackbuilder")
public class INFINIDATConvergedStackBuilder implements ConvergedStackComponentBuilderIf {
	private static Logger logger = Logger.getLogger( INFINIDATConvergedStackBuilder.class );
	
	@Override
	public ConvergedStackComponentDetail buildConvergedStackComponent( String accountIdentity ) throws Exception {
		INFINIDATConvergedStackComponentDetail detail = new INFINIDATConvergedStackComponentDetail();
		ObjStore<INFINIDATDeviceInfo> store = ObjStoreHelper.getStore( INFINIDATDeviceInfo.class );
		String accountName = accountIdentity.split( ";" )[0];
		String query = "accountName == '" + accountName + "'";
		
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackBuilder:ConvergedStackComponentDetail ####---- ACCOUNT: " + accountName );}
		PhysicalInfraAccount account = AccountUtil.getAccountByName(accountName);
		logger.info("AccountName:" + account.getAccountName());
		
		
		List<INFINIDATDeviceInfo> summaryList = store.query( query );
		if( summaryList.isEmpty() == false ) {
			INFINIDATDeviceInfo infinidatDeviceInfo = summaryList.get( 0 );
			detail.setModel( infinidatDeviceInfo.getModel() );
			detail.setOsVersion( infinidatDeviceInfo.getFWVersion() );
			detail.setVendorLogoUrl( "/app/uploads/openauto/infinidat_logo.png" );
	        detail.setIconUrl("/app/uploads/openauto/infinidat_array.png");
			detail.setMgmtIPAddr( infinidatDeviceInfo.getIP() );
			detail.setStatus( infinidatDeviceInfo.getStatus() );
			detail.setSerialNumber( infinidatDeviceInfo.getSerialNumber());
			detail.setVendorName( infinidatDeviceInfo.getVendor() );
			detail.setBuildVersion( INFINIDATConstants.BUILD );
			detail.setFree(infinidatDeviceInfo.getFree());
			detail.setCapacity(infinidatDeviceInfo.getCapacity());
			detail.setContextType( ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE ).getType());
			detail.setLayerType( com.cloupia.model.cIM.ConvergedStackComponentDetail.LAYER_TYPE_PHY_STORAGE );
			detail.setContextValue( accountIdentity );
			detail.setStatus( "OK" );
		} else {
			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackBuilder:ConvergedStackComponentDetail ####---- No inventory summary found for ACCOUNT: " + accountName );}
			detail.setStatus( "FAILED" );
		}
		return detail;
	}
}
