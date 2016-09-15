package com.cloupia.feature.infinidat.accounts.session;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.connector.pooling.ConnectorSession;
import com.cloupia.lib.connector.pooling.ConnectorSessionFactory;

@SuppressWarnings("unused")
public class INFINIDATSessionFactory implements ConnectorSessionFactory {
	private static Logger logger = Logger.getLogger( INFINIDATSessionFactory.class );
	
	@Override
	public ConnectorSession createSession( String arg0 ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATSessionFactory:createSession ####----" );}
		return null;
	}

}
