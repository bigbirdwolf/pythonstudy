package com.yltx.oil.partner.injections.modules;

import android.content.Context;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.yltx.oil.partner.injections.instrumentation.StethoInstrumentation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DebugInstrumentationModule_ProvidesStethoInstrumentationFactory implements Factory<StethoInstrumentation> {
    private final Provider<Context> contextProvider;
    private final Provider<StethoInterceptor> interceptorProvider;
    private final DebugInstrumentationModule module;

    public DebugInstrumentationModule_ProvidesStethoInstrumentationFactory(DebugInstrumentationModule debugInstrumentationModule, Provider<Context> provider, Provider<StethoInterceptor> provider2) {
        this.module = debugInstrumentationModule;
        this.contextProvider = provider;
        this.interceptorProvider = provider2;
    }

    @Override // javax.inject.Provider
    public StethoInstrumentation get() {
        return provideInstance(this.module, this.contextProvider, this.interceptorProvider);
    }

    public static StethoInstrumentation provideInstance(DebugInstrumentationModule debugInstrumentationModule, Provider<Context> provider, Provider<StethoInterceptor> provider2) {
        return proxyProvidesStethoInstrumentation(debugInstrumentationModule, provider.get(), provider2.get());
    }

    public static DebugInstrumentationModule_ProvidesStethoInstrumentationFactory create(DebugInstrumentationModule debugInstrumentationModule, Provider<Context> provider, Provider<StethoInterceptor> provider2) {
        return new DebugInstrumentationModule_ProvidesStethoInstrumentationFactory(debugInstrumentationModule, provider, provider2);
    }

    public static StethoInstrumentation proxyProvidesStethoInstrumentation(DebugInstrumentationModule debugInstrumentationModule, Context context, StethoInterceptor stethoInterceptor) {
        return (StethoInstrumentation) Preconditions.checkNotNull(debugInstrumentationModule.providesStethoInstrumentation(context, stethoInterceptor), "Cannot return null from a non-@Nullable @Provides method");
    }
}