package com.alipay.android.phone.mrpc.core;

import java.io.IOException;
import java.net.SocketException;
import javax.net.ssl.SSLException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes.dex */
public class ad implements HttpRequestRetryHandler {
    static final String a = "ad";

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        if (i >= 3) {
            return false;
        }
        if (iOException instanceof NoHttpResponseException) {
            String str = a;
            return true;
        } else if (((iOException instanceof SocketException) || (iOException instanceof SSLException)) && iOException.getMessage() != null && iOException.getMessage().contains("Broken pipe")) {
            String str2 = a;
            return true;
        } else {
            return false;
        }
    }
}