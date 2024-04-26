package com.yltx.oil.partner.injections.modules;

import android.content.Context;
import com.yltx.oil.partner.base.PartnerApplication;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AppModule_ProvideContextFactory implements Factory<Context> {
    private final Provider<PartnerApplication> applicationProvider;
    private final AppModule module;

    public AppModule_ProvideContextFactory(AppModule appModule, Provider<PartnerApplication> provider) {
        this.module = appModule;
        this.applicationProvider = provider;
    }

    @Override // javax.inject.Provider
    public Context get() {
        return provideInstance(this.module, this.applicationProvider);
    }

    public static Context provideInstance(AppModule appModule, Provider<PartnerApplication> provider) {
        return proxyProvideContext(appModule, provider.get());
    }

    public static AppModule_ProvideContextFactory create(AppModule appModule, Provider<PartnerApplication> provider) {
        return new AppModule_ProvideContextFactory(appModule, provider);
    }

    public static Context proxyProvideContext(AppModule appModule, PartnerApplication partnerApplication) {
        return (Context) Preconditions.checkNotNull(appModule.provideContext(partnerApplication), "Cannot return null from a non-@Nullable @Provides method");
    }
}