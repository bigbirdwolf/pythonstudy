package com.yltx.oil.partner.data.network.interceptors;

import android.content.Context;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.yltx.oil.partner.utils.AndroidUtil;
import java.io.IOException;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public class RewriteCacheControlInterceptor implements Interceptor {
    private Context context;

    public RewriteCacheControlInterceptor(Context context) {
        this.context = context;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (!AndroidUtil.isNetWorkAvailable(this.context)) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
        }
        Response proceed = chain.proceed(request);
        if (AndroidUtil.isNetWorkAvailable(this.context)) {
            return proceed.newBuilder().header(HttpHeaders.CACHE_CONTROL, request.cacheControl().toString()).removeHeader("Pragma").build();
        }
        return proceed.newBuilder().header(HttpHeaders.CACHE_CONTROL, "public, only-if-cached, max-stale=2419200").removeHeader("Pragma").build();
    }
}