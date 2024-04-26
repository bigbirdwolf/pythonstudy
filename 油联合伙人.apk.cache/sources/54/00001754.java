package com.yltx.oil.partner.injections.modules;

import android.content.Context;
import com.yltx.oil.partner.data.datasource.RestDataSource;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public final class GlobalModule_ProvideRestDataSourceFactory implements Factory<RestDataSource> {
    private final Provider<Context> ctxProvider;
    private final GlobalModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public GlobalModule_ProvideRestDataSourceFactory(GlobalModule globalModule, Provider<Context> provider, Provider<OkHttpClient> provider2) {
        this.module = globalModule;
        this.ctxProvider = provider;
        this.okHttpClientProvider = provider2;
    }

    @Override // javax.inject.Provider
    public RestDataSource get() {
        return provideInstance(this.module, this.ctxProvider, this.okHttpClientProvider);
    }

    public static RestDataSource provideInstance(GlobalModule globalModule, Provider<Context> provider, Provider<OkHttpClient> provider2) {
        return proxyProvideRestDataSource(globalModule, provider.get(), provider2.get());
    }

    public static GlobalModule_ProvideRestDataSourceFactory create(GlobalModule globalModule, Provider<Context> provider, Provider<OkHttpClient> provider2) {
        return new GlobalModule_ProvideRestDataSourceFactory(globalModule, provider, provider2);
    }

    public static RestDataSource proxyProvideRestDataSource(GlobalModule globalModule, Context context, OkHttpClient okHttpClient) {
        return (RestDataSource) Preconditions.checkNotNull(globalModule.provideRestDataSource(context, okHttpClient), "Cannot return null from a non-@Nullable @Provides method");
    }
}