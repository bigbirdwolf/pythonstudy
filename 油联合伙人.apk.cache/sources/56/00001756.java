package com.yltx.oil.partner.injections.modules;

import com.yltx.oil.partner.injections.instrumentation.HttpLoggingInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.StethoInstrumentation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
    private final Provider<StethoInstrumentation> i1Provider;
    private final Provider<HttpLoggingInstrumentation> i2Provider;
    private final NetworkModule module;

    public NetworkModule_ProvideOkHttpClientFactory(NetworkModule networkModule, Provider<StethoInstrumentation> provider, Provider<HttpLoggingInstrumentation> provider2) {
        this.module = networkModule;
        this.i1Provider = provider;
        this.i2Provider = provider2;
    }

    @Override // javax.inject.Provider
    public OkHttpClient get() {
        return provideInstance(this.module, this.i1Provider, this.i2Provider);
    }

    public static OkHttpClient provideInstance(NetworkModule networkModule, Provider<StethoInstrumentation> provider, Provider<HttpLoggingInstrumentation> provider2) {
        return proxyProvideOkHttpClient(networkModule, provider.get(), provider2.get());
    }

    public static NetworkModule_ProvideOkHttpClientFactory create(NetworkModule networkModule, Provider<StethoInstrumentation> provider, Provider<HttpLoggingInstrumentation> provider2) {
        return new NetworkModule_ProvideOkHttpClientFactory(networkModule, provider, provider2);
    }

    public static OkHttpClient proxyProvideOkHttpClient(NetworkModule networkModule, StethoInstrumentation stethoInstrumentation, HttpLoggingInstrumentation httpLoggingInstrumentation) {
        return (OkHttpClient) Preconditions.checkNotNull(networkModule.provideOkHttpClient(stethoInstrumentation, httpLoggingInstrumentation), "Cannot return null from a non-@Nullable @Provides method");
    }
}