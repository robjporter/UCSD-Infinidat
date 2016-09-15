package com.cloupia.feature.infinidat.inventory;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.cIaaS.network.model.DeviceIdentity;
import com.cloupia.service.cIM.inframgr.stackView.AbstractOANetworkStackViewProvider;

@SuppressWarnings("unused")
public class INFINIDATStackViewProvider  extends AbstractOANetworkStackViewProvider {
	private static Logger logger = Logger.getLogger( INFINIDATStackViewProvider.class );
	private String bottomLabel = "some other info";
	private String topLabel = "dummydevice";
	
	@Override
	public String getBottomLabel( DeviceIdentity identity ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATStackViewProvider:getBottomLabel ####----" );}
		return this.bottomLabel;
	}
	@Override
	public String getTopLabel( DeviceIdentity identity ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATStackViewProvider:getTopLabel ####----" );}
		return this.topLabel;
	}
	@Override
	public boolean isApplicable( DeviceIdentity identity ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATStackViewProvider:isApplicable ####----" );}
		if (identity != null) {
			//if (identity.getDeviceIp().equals("172.25.168.60")) {
			return true;
			//}
		}
		return false;
	}

}
