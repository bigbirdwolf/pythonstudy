package com.yltx.oil.partner.injections.modules;

import android.content.Context;
import com.yltx.oil.partner.injections.instrumentation.PosInstrumentation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DebugInstrumentationModule_ProvidesPosInstrumentationFactory implements Factory<PosInstrumentation> {
    private final Provider<Context> contextProvider;
    private final DebugInstrumentationModule module;

    public DebugInstrumentationModule_ProvidesPosInstrumentationFactory(DebugInstrumentationModule debugInstrumentationModule, Provider<Context> provider) {
        this.module = debugInstrumentationModule;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public PosInstrumentation get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static PosInstrumentation provideInstance(DebugInstrumentationModule debugInstrumentationModule, Provider<Context> provider) {
        return proxyProvidesPosInstrumentation(debugInstrumentationModule, provider.get());
    }

    public static DebugInstrumentationModule_ProvidesPosInstrumentationFactory create(DebugInstrumentationModule debugInstrumentationModule, Provider<Context> provider) {
        return new DebugInstrumentationModule_ProvidesPosInstrumentationFactory(debugInstrumentationModule, provider);
    }

    public static PosInstrumentation proxyProvidesPosInstrumentation(DebugInstrumentationModule debugInstrumentationModule, Context context) {
        return (PosInstrumentation) Preconditions.checkNotNull(debugInstrumentationModule.providesPosInstrumentation(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}