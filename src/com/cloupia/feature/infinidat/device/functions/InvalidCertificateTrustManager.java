package com.cloupia.feature.infinidat.device.functions;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class InvalidCertificateTrustManager implements X509TrustManager{
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    @Override
    public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {

    }

    @Override
    public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
    }
}