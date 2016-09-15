package com.cloupia.feature.infinidat.device.functions;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.tasks.INFINIDATdeletePoolByIDTask;

@SuppressWarnings("unused")
public class InvalidCertificateHostVerifier implements HostnameVerifier{
	static Logger logger = Logger.getLogger(INFINIDATdeletePoolByIDTask.class);
    @Override
    public boolean verify(String paramString, SSLSession paramSSLSession) {
    	if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info("----#### InvalidCertificateHostVerifier:verify ####----");}
        return true;
    }
}