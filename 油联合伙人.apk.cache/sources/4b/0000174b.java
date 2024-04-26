package com.yltx.oil.partner.injections.modules;

import android.content.Context;
import com.yltx.oil.partner.data.network.interceptors.HttpLoggingInterceptor;
import com.yltx.oil.partner.injections.instrumentation.HttpLoggingInstrumentation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DebugInstrumentationModule_ProvidesHttpLoggingInstrumentationFactory implements Factory<HttpLoggingInstrumentation> {
    private final Provider<Context> contextProvider;
    private final Provider<HttpLoggingInterceptor> interceptorProvider;
    private final DebugInstrumentationModule module;

    public DebugInstrumentationModule_ProvidesHttpLoggingInstrumentationFactory(DebugInstrumentationModule debugInstrumentationModule, Provider<Context> provider, Provider<HttpLoggingInterceptor> provider2) {
        this.module = debugInstrumentationModule;
        this.contextProvider = provider;
        this.interceptorProvider = provider2;
    }

    @Override // javax.inject.Provider
    public HttpLoggingInstrumentation get() {
        return provideInstance(this.module, this.contextProvider, this.interceptorProvider);
    }

    public static HttpLoggingInstrumentation provideInstance(DebugInstrumentationModule debugInstrumentationModule, Provider<Context> provider, Provider<HttpLoggingInterceptor> provider2) {
        return proxyProvidesHttpLoggingInstrumentation(debugInstrumentationModule, provider.get(), provider2.get());
    }

    public static DebugInstrumentationModule_ProvidesHttpLoggingInstrumentationFactory create(DebugInstrumentationModule debugInstrumentationModule, Provider<Context> provider, Provider<HttpLoggingInterceptor> provider2) {
        return new DebugInstrumentationModule_ProvidesHttpLoggingInstrumentationFactory(debugInstrumentationModule, provider, provider2);
    }

    public static HttpLoggingInstrumentation proxyProvidesHttpLoggingInstrumentation(DebugInstrumentationModule debugInstrumentationModule, Context context, HttpLoggingInterceptor httpLoggingInterceptor) {
        return (HttpLoggingInstrumentation) Preconditions.checkNotNull(debugInstrumentationModule.providesHttpLoggingInstrumentation(context, httpLoggingInterceptor), "Cannot return null from a non-@Nullable @Provides method");
    }
}