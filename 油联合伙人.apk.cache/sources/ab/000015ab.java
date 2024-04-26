package com.yltx.oil.partner.data.network.interceptors;

import android.content.Context;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public class CommonParamsInterceptor implements Interceptor {
    private Context mContext;

    public CommonParamsInterceptor(Context context) {
        this.mContext = context;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        if (method.equalsIgnoreCase("get")) {
            request = request.newBuilder().headers(new RequestParamsWrapper(this.mContext, request.url().query()).getRequestHeaders(request.headers())).build();
        } else if (method.equalsIgnoreCase("post")) {
            request = request.newBuilder().headers(new RequestParamsWrapper(this.mContext, request.body()).getRequestHeaders(request.headers())).build();
        }
        return chain.proceed(request);
    }
}