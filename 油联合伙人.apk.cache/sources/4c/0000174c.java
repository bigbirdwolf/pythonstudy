package com.yltx.oil.partner.injections.modules;

import com.yltx.oil.partner.data.network.interceptors.HttpLoggingInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class DebugInstrumentationModule_ProvidesHttpLoggingIntercetorFactory implements Factory<HttpLoggingInterceptor> {
    private final DebugInstrumentationModule module;

    public DebugInstrumentationModule_ProvidesHttpLoggingIntercetorFactory(DebugInstrumentationModule debugInstrumentationModule) {
        this.module = debugInstrumentationModule;
    }

    @Override // javax.inject.Provider
    public HttpLoggingInterceptor get() {
        return provideInstance(this.module);
    }

    public static HttpLoggingInterceptor provideInstance(DebugInstrumentationModule debugInstrumentationModule) {
        return proxyProvidesHttpLoggingIntercetor(debugInstrumentationModule);
    }

    public static DebugInstrumentationModule_ProvidesHttpLoggingIntercetorFactory create(DebugInstrumentationModule debugInstrumentationModule) {
        return new DebugInstrumentationModule_ProvidesHttpLoggingIntercetorFactory(debugInstrumentationModule);
    }

    public static HttpLoggingInterceptor proxyProvidesHttpLoggingIntercetor(DebugInstrumentationModule debugInstrumentationModule) {
        return (HttpLoggingInterceptor) Preconditions.checkNotNull(debugInstrumentationModule.providesHttpLoggingIntercetor(), "Cannot return null from a non-@Nullable @Provides method");
    }
}