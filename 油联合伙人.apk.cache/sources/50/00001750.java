package com.yltx.oil.partner.injections.modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class DebugInstrumentationModule_ProvidesStethoIntercetorFactory implements Factory<StethoInterceptor> {
    private final DebugInstrumentationModule module;

    public DebugInstrumentationModule_ProvidesStethoIntercetorFactory(DebugInstrumentationModule debugInstrumentationModule) {
        this.module = debugInstrumentationModule;
    }

    @Override // javax.inject.Provider
    public StethoInterceptor get() {
        return provideInstance(this.module);
    }

    public static StethoInterceptor provideInstance(DebugInstrumentationModule debugInstrumentationModule) {
        return proxyProvidesStethoIntercetor(debugInstrumentationModule);
    }

    public static DebugInstrumentationModule_ProvidesStethoIntercetorFactory create(DebugInstrumentationModule debugInstrumentationModule) {
        return new DebugInstrumentationModule_ProvidesStethoIntercetorFactory(debugInstrumentationModule);
    }

    public static StethoInterceptor proxyProvidesStethoIntercetor(DebugInstrumentationModule debugInstrumentationModule) {
        return (StethoInterceptor) Preconditions.checkNotNull(debugInstrumentationModule.providesStethoIntercetor(), "Cannot return null from a non-@Nullable @Provides method");
    }
}