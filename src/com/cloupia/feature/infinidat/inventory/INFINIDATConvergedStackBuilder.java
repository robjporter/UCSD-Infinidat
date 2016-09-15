package com.cloupia.feature.infinidat.inventory;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.service.cIM.inframgr.reports.contextresolve.AbstractConvergedStackComponentBuilder;

@SuppressWarnings("unused")
public class INFINIDATConvergedStackBuilder extends AbstractConvergedStackComponentBuilder {
	private static Logger logger = Logger.getLogger( INFINIDATConvergedStackBuilder.class );

	public INFINIDATConvergedStackBuilder() {
		super( INFINIDATConstants.INFINIDAT_ACCOUNT_TYPE, InfraAccountTypes.CAT_STORAGE );
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackBuilder:INFINIDATConvergedStackBuilder ####----" );}
	}
	public String getSerialNumber() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackBuilder:getSerialNumber ####----" );}
		return "SERIAL NUMBER";
	}
	@Override
	public String getModel() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackBuilder:getModel ####----" );}
		String ret = INFINIDATConstants.INFINIDAT_DEFAULT_MODEL;
		String accountName = this.getIdentifier();
		try {
			if (accountName != null) {
				INFINIDATFunctions funcs = new INFINIDATFunctions();
				ret = funcs.getDeviceModel( accountName );
			} 
		} catch( Exception e ) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATConvergedStackBuilder:getModel ####---- ERROR - ", e );}
		}
		return ret;
	}
	@Override
	public String getOSVersion() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackBuilder:getOSVersion ####----" );}
		String ret = INFINIDATConstants.INFINIDAT_DEFAULT_VERSION;
		String accountName = this.getIdentifier();
		try {
			if (accountName != null) {
				INFINIDATFunctions funcs = new INFINIDATFunctions();
				ret = funcs.getDeviceVersion( accountName );
			} 
		} catch( Exception e ) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATConvergedStackBuilder:getOSVersion ####---- ERROR - ", e );}
		}
		return ret;
	}
	@Override
	public String getVendor() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackBuilder:getVendor ####----" );}
		return INFINIDATConstants.INFINIDAT_VENDOR_NAME;
	}
	@Override
	public String getVendorImageName() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackBuilder:getVendorImageName ####----" );}
		return INFINIDATConstants.INFINIDAT_VENDOR_IMAGE;
	}
}
