package com.yltx.oil.partner.injections.instrumentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.yltx.oil.partner.injections.Preconditions;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public class HttpLoggingInstrumentation implements NetworkInstrumentation<OkHttpClient> {
    @NonNull
    private final Context context;
    @NonNull
    private final Interceptor interceptor;

    @Override // com.yltx.oil.partner.injections.instrumentation.Instrumentation
    public void init() {
    }

    public HttpLoggingInstrumentation(@NonNull Context context, @NonNull Interceptor interceptor) {
        Preconditions.checkNotNull(context, "Context cannot be null.");
        Preconditions.checkNotNull(interceptor, "Interceptor cannot be null.");
        this.context = context;
        this.interceptor = interceptor;
    }

    @Override // com.yltx.oil.partner.injections.instrumentation.NetworkInstrumentation
    @NonNull
    public OkHttpClient decorateNetwork(@NonNull OkHttpClient okHttpClient) {
        Preconditions.checkNotNull(okHttpClient, "Http Client cannot be null.");
        return addInterceptor(okHttpClient, this.interceptor);
    }

    @VisibleForTesting
    OkHttpClient addInterceptor(@NonNull OkHttpClient okHttpClient, @NonNull Interceptor interceptor) {
        Preconditions.checkNotNull(okHttpClient, "Http Client cannot be null.");
        Preconditions.checkNotNull(interceptor, "Interceptor cannot be null.");
        return okHttpClient.newBuilder().addInterceptor(interceptor).build();
    }
}