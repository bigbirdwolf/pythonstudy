package com.yltx.oil.partner.data.network.interceptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.yltx.oil.partner.data.network.UserToken;
import com.yltx.oil.partner.utils.DataCache;
import com.yltx.oil.partner.utils.Md5;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* loaded from: classes.dex */
final class RequestParamsWrapper {
    private static final String PARAM_APP = "X-App";
    private static final String PARAM_NONCESTR = "X-Noncestr";
    private static final String PARAM_OS = "X-OS";
    private static final String PARAM_REQ_TIME = "X-Req-Time";
    private static final String PARAM_SIGN = "X-Sign";
    private static final String PARAM_TOKEN = "X-Token";
    private static final String PARAM_USER_ID = "X-UserID";
    private static final String tf = "yyyyMMddHHmmssSSS";
    private String appType;
    private String clientType;
    private String noncestr;
    private String reqTime;
    private String sign;
    private String token;
    private String userId;

    RequestParamsWrapper(Context context) {
        this.reqTime = getRequestTime();
        this.clientType = "partnerApp_android";
        this.appType = "native";
        this.noncestr = "123456";
        UserToken userToken = UserToken.getInstance();
        if (TextUtils.isEmpty(userToken.getToken())) {
            Log.d(">>Token>>>", ">>>>>Token>>>>");
            this.token = DataCache.getToken(context);
            this.userId = DataCache.getUserid(context);
            return;
        }
        this.token = TextUtils.isEmpty(userToken.getToken()) ? "" : userToken.getToken();
        this.userId = TextUtils.isEmpty(userToken.getUserID()) ? "" : userToken.getUserID();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestParamsWrapper(Context context, String str) {
        this(context);
        this.sign = sign((str == null || str.length() == 0) ? "" : "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestParamsWrapper(Context context, RequestBody requestBody) {
        this(context);
        StringBuilder sb = new StringBuilder();
        if (requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;
            int size = formBody.size();
            for (int i = 0; i < size; i++) {
                sb.append(formBody.name(i));
                sb.append("=");
                sb.append(formBody.value(i));
                if (i < size - 1) {
                    sb.append(a.b);
                }
            }
            this.sign = sign(sb.toString());
        } else if (requestBody instanceof MultipartBody) {
            this.sign = "";
        } else {
            this.sign = "";
        }
    }

    public Headers getRequestHeaders(Headers headers) {
        Headers.Builder newBuilder = headers.newBuilder();
        newBuilder.add(PARAM_APP, this.appType);
        newBuilder.add(PARAM_NONCESTR, this.noncestr);
        newBuilder.add(PARAM_OS, this.clientType);
        newBuilder.add(PARAM_REQ_TIME, this.reqTime);
        newBuilder.add(PARAM_SIGN, this.sign);
        newBuilder.add(PARAM_TOKEN, this.token);
        newBuilder.add(PARAM_USER_ID, this.userId);
        return newBuilder.build();
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String getRequestTime() {
        Date time = new GregorianCalendar().getTime();
        new SimpleDateFormat(tf);
        return String.valueOf(time.getTime());
    }

    private String sign(String str) {
        return Md5.md5(this.token + this.reqTime + this.noncestr.substring(2) + str).toLowerCase();
    }
}