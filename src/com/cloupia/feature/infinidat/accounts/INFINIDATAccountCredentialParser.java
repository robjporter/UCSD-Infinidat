package com.cloupia.feature.infinidat.accounts;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.accounts.INFINIDATAccountCredentialParser;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.connector.account.credential.CredentialParserIf;

@SuppressWarnings("unused")
public class INFINIDATAccountCredentialParser implements CredentialParserIf {
	private static Logger logger = Logger.getLogger( INFINIDATAccountCredentialParser.class );
	@Override
	public Object getCredentialsFromPolicy( String arg0, Object arg1 ) throws Exception {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountCredentialParser:getCredentialsFromPolicy ####----" );}
		return null;
	}
}