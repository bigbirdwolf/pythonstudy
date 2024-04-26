package com.yltx.oil.partner.injections.modules;

import com.yltx.oil.partner.injections.instrumentation.ApplicationInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.HttpLoggingInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.StethoInstrumentation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DebugInstrumentationModule_ProvidesInstrumentationFactory implements Factory<ApplicationInstrumentation> {
    private final Provider<HttpLoggingInstrumentation> instrumentation1Provider;
    private final Provider<StethoInstrumentation> instrumentationProvider;
    private final DebugInstrumentationModule module;

    public DebugInstrumentationModule_ProvidesInstrumentationFactory(DebugInstrumentationModule debugInstrumentationModule, Provider<StethoInstrumentation> provider, Provider<HttpLoggingInstrumentation> provider2) {
        this.module = debugInstrumentationModule;
        this.instrumentationProvider = provider;
        this.instrumentation1Provider = provider2;
    }

    @Override // javax.inject.Provider
    public ApplicationInstrumentation get() {
        return provideInstance(this.module, this.instrumentationProvider, this.instrumentation1Provider);
    }

    public static ApplicationInstrumentation provideInstance(DebugInstrumentationModule debugInstrumentationModule, Provider<StethoInstrumentation> provider, Provider<HttpLoggingInstrumentation> provider2) {
        return proxyProvidesInstrumentation(debugInstrumentationModule, provider.get(), provider2.get());
    }

    public static DebugInstrumentationModule_ProvidesInstrumentationFactory create(DebugInstrumentationModule debugInstrumentationModule, Provider<StethoInstrumentation> provider, Provider<HttpLoggingInstrumentation> provider2) {
        return new DebugInstrumentationModule_ProvidesInstrumentationFactory(debugInstrumentationModule, provider, provider2);
    }

    public static ApplicationInstrumentation proxyProvidesInstrumentation(DebugInstrumentationModule debugInstrumentationModule, StethoInstrumentation stethoInstrumentation, HttpLoggingInstrumentation httpLoggingInstrumentation) {
        return (ApplicationInstrumentation) Preconditions.checkNotNull(debugInstrumentationModule.providesInstrumentation(stethoInstrumentation, httpLoggingInstrumentation), "Cannot return null from a non-@Nullable @Provides method");
    }
}