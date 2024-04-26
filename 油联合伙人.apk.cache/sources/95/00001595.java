package com.yltx.oil.partner.data.datasource;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public final class RestDataSource_Factory implements Factory<RestDataSource> {
    private final Provider<OkHttpClient> clientProvider;
    private final Provider<Context> contextProvider;

    public RestDataSource_Factory(Provider<Context> provider, Provider<OkHttpClient> provider2) {
        this.contextProvider = provider;
        this.clientProvider = provider2;
    }

    @Override // javax.inject.Provider
    public RestDataSource get() {
        return provideInstance(this.contextProvider, this.clientProvider);
    }

    public static RestDataSource provideInstance(Provider<Context> provider, Provider<OkHttpClient> provider2) {
        return new RestDataSource(provider.get(), provider2.get());
    }

    public static RestDataSource_Factory create(Provider<Context> provider, Provider<OkHttpClient> provider2) {
        return new RestDataSource_Factory(provider, provider2);
    }

    public static RestDataSource newRestDataSource(Context context, OkHttpClient okHttpClient) {
        return new RestDataSource(context, okHttpClient);
    }
}