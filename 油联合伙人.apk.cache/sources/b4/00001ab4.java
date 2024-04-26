package com.yltx.oil.partner.utils;

import android.content.Context;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.mvp.exception.BizException;
import com.yltx.oil.partner.mvp.exception.ServerError;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import retrofit2.HttpException;

/* loaded from: classes.dex */
public final class ErrorFormatter {
    private static final String ERROR1 = "HTTP 504 Unsatisfiable Request (only-if-cached)";
    private static Context appContext = PartnerApplication.getInstance();
    private static final String HTTP_CONNECT_AUTH_FAILURE_ERROR_MSG = appContext.getString(R.string.http_connect_auth_failure_error_msg);
    private static final String HTTP_CONNECT_NETWORK_ERROR_MSG = appContext.getString(R.string.http_connect_network_error_msg);
    private static final String HTTP_CONNECT_NO_CONNECTION_ERROR_MSG = appContext.getString(R.string.http_connect_no_connection_error_msg);
    private static final String HTTP_CONNECT_UNKNOWN_HOST_ERROR_MSG = appContext.getString(R.string.http_connect_unknown_host_error_msg);
    private static final String HTTP_CONNECT_SERVER_ERROR_MSG = appContext.getString(R.string.http_connect_server_error_msg);
    private static final String HTTP_CONNECT_TIMEOUT_ERROR_MSG = appContext.getString(R.string.http_connect_timeout_error_msg);
    private static final String HTTP_CONNECT_UNKNOWN_ERROR_MSG = appContext.getString(R.string.http_connect_unknown_error_msg);

    public static String format(Throwable th) {
        if (th != null && (th instanceof BizException)) {
            return ((BizException) th).message();
        }
        if (th != null && (th instanceof UnknownHostException)) {
            return HTTP_CONNECT_UNKNOWN_HOST_ERROR_MSG;
        }
        if (th != null && (th instanceof ConnectException)) {
            return HTTP_CONNECT_NO_CONNECTION_ERROR_MSG;
        }
        if (th != null && (th instanceof SocketTimeoutException)) {
            return HTTP_CONNECT_TIMEOUT_ERROR_MSG;
        }
        if (th != null && (th instanceof HttpException)) {
            return th.getMessage().equals(ERROR1) ? "请检查网络连接" : th.getMessage();
        } else if (th.getMessage() == null || !th.getMessage().equals("该号码未注册")) {
            if (th != null && (th instanceof ServerError)) {
                return HTTP_CONNECT_SERVER_ERROR_MSG;
            }
            return HTTP_CONNECT_UNKNOWN_ERROR_MSG;
        } else {
            return "该号码未注册";
        }
    }
}