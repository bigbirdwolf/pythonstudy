package com.allenliu.versionchecklib.core.http;

import com.alipay.sdk.sys.a;
import com.allenliu.versionchecklib.core.VersionParams;
import com.allenliu.versionchecklib.utils.ALog;
import com.allenliu.versionchecklib.v2.builder.RequestVersionBuilder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AllenHttp {
    private static OkHttpClient client;

    public static OkHttpClient getHttpClient() {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(createSSLSocketFactory());
            builder.connectTimeout(15L, TimeUnit.SECONDS);
            builder.hostnameVerifier(new TrustAllHostnameVerifier());
            client = builder.build();
        }
        return client;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }

        private TrustAllHostnameVerifier() {
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class TrustAllCerts implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        private TrustAllCerts() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static String assembleUrl(String str, HttpParams httpParams) {
        StringBuffer stringBuffer = new StringBuffer(str);
        if (httpParams != null) {
            stringBuffer.append("?");
            for (Map.Entry<String, Object> entry : httpParams.entrySet()) {
                stringBuffer.append(entry.getKey());
                stringBuffer.append("=");
                stringBuffer.append(entry.getValue() + "");
                stringBuffer.append(a.b);
            }
            str = stringBuffer.substring(0, stringBuffer.length() - 1);
        }
        ALog.e("url:" + str);
        return str;
    }

    private static String getRequestParamsJson(HttpParams httpParams) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, Object> entry : httpParams.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String jSONObject2 = jSONObject.toString();
        ALog.e("json:" + jSONObject2);
        return jSONObject2;
    }

    private static <T extends Request.Builder> T assembleHeader(T t, VersionParams versionParams) {
        HttpHeaders httpHeaders = versionParams.getHttpHeaders();
        if (httpHeaders != null) {
            ALog.e("header:");
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                ALog.e(key + "=" + value + "\n");
                t.addHeader(key, value);
            }
        }
        return t;
    }

    public static Request.Builder get(VersionParams versionParams) {
        Request.Builder assembleHeader = assembleHeader(new Request.Builder(), versionParams);
        assembleHeader.url(assembleUrl(versionParams.getRequestUrl(), versionParams.getRequestParams()));
        return assembleHeader;
    }

    public static Request.Builder post(VersionParams versionParams) {
        FormBody requestParams = getRequestParams(versionParams);
        Request.Builder assembleHeader = assembleHeader(new Request.Builder(), versionParams);
        assembleHeader.post(requestParams).url(versionParams.getRequestUrl());
        return assembleHeader;
    }

    public static Request.Builder postJson(VersionParams versionParams) {
        RequestBody create = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), getRequestParamsJson(versionParams.getRequestParams()));
        Request.Builder assembleHeader = assembleHeader(new Request.Builder(), versionParams);
        assembleHeader.post(create).url(versionParams.getRequestUrl());
        return assembleHeader;
    }

    private static FormBody getRequestParams(VersionParams versionParams) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : versionParams.getRequestParams().entrySet()) {
            builder.add(entry.getKey(), entry.getValue() + "");
            ALog.e("params key:" + entry.getKey() + "-----value:" + entry.getValue());
        }
        return builder.build();
    }

    private static <T extends Request.Builder> T assembleHeader(T t, RequestVersionBuilder requestVersionBuilder) {
        HttpHeaders httpHeaders = requestVersionBuilder.getHttpHeaders();
        if (httpHeaders != null) {
            ALog.e("header:");
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                ALog.e(key + "=" + value + "\n");
                t.addHeader(key, value);
            }
        }
        return t;
    }

    public static Request.Builder get(RequestVersionBuilder requestVersionBuilder) {
        Request.Builder assembleHeader = assembleHeader(new Request.Builder(), requestVersionBuilder);
        assembleHeader.url(assembleUrl(requestVersionBuilder.getRequestUrl(), requestVersionBuilder.getRequestParams()));
        return assembleHeader;
    }

    public static Request.Builder post(RequestVersionBuilder requestVersionBuilder) {
        FormBody requestParams = getRequestParams(requestVersionBuilder);
        Request.Builder assembleHeader = assembleHeader(new Request.Builder(), requestVersionBuilder);
        assembleHeader.post(requestParams).url(requestVersionBuilder.getRequestUrl());
        return assembleHeader;
    }

    public static Request.Builder postJson(RequestVersionBuilder requestVersionBuilder) {
        RequestBody create = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), getRequestParamsJson(requestVersionBuilder.getRequestParams()));
        Request.Builder assembleHeader = assembleHeader(new Request.Builder(), requestVersionBuilder);
        assembleHeader.post(create).url(requestVersionBuilder.getRequestUrl());
        return assembleHeader;
    }

    private static FormBody getRequestParams(RequestVersionBuilder requestVersionBuilder) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : requestVersionBuilder.getRequestParams().entrySet()) {
            builder.add(entry.getKey(), entry.getValue() + "");
            ALog.e("params key:" + entry.getKey() + "-----value:" + entry.getValue());
        }
        return builder.build();
    }
}