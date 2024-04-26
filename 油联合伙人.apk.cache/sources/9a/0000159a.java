package com.yltx.oil.partner.data.network;

import android.annotation.SuppressLint;
import com.yltx.oil.partner.utils.ContextHolder;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public class SSLContextUtil {
    private static TrustManager trustManagers = new X509TrustManager() { // from class: com.yltx.oil.partner.data.network.SSLContextUtil.1
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };
    public static final HostnameVerifier HOSTNAME_VERIFIER = new HostnameVerifier() { // from class: com.yltx.oil.partner.data.network.SSLContextUtil.2
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };

    @SuppressLint({"TrulyRandom"})
    public static SSLContext getSSLContext() {
        SSLContext sSLContext;
        Exception e;
        try {
            sSLContext = SSLContext.getInstance("TLS");
            try {
                Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(ContextHolder.getContext().getAssets().open("server.cer"));
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(null, null);
                keyStore.setCertificateEntry("trust", generateCertificate);
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(keyStore, null);
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                sSLContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return sSLContext;
            }
        } catch (Exception e3) {
            sSLContext = null;
            e = e3;
        }
        return sSLContext;
    }

    public static SSLContext getDefaultSLLContext() {
        SSLContext sSLContext;
        Exception e;
        try {
            sSLContext = SSLContext.getInstance("TLS");
            try {
                sSLContext.init(null, new TrustManager[]{trustManagers}, new SecureRandom());
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return sSLContext;
            }
        } catch (Exception e3) {
            sSLContext = null;
            e = e3;
        }
        return sSLContext;
    }
}