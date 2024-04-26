package com.yltx.oil.partner.injections.modules;

import android.content.Context;
import com.yltx.oil.partner.data.datasource.RestDataSource;
import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GlobalModule_ProvideDataRepositoryFactory implements Factory<Repository> {
    private final Provider<Context> ctxProvider;
    private final GlobalModule module;
    private final Provider<RestDataSource> restDataSourceProvider;

    public GlobalModule_ProvideDataRepositoryFactory(GlobalModule globalModule, Provider<Context> provider, Provider<RestDataSource> provider2) {
        this.module = globalModule;
        this.ctxProvider = provider;
        this.restDataSourceProvider = provider2;
    }

    @Override // javax.inject.Provider
    public Repository get() {
        return provideInstance(this.module, this.ctxProvider, this.restDataSourceProvider);
    }

    public static Repository provideInstance(GlobalModule globalModule, Provider<Context> provider, Provider<RestDataSource> provider2) {
        return proxyProvideDataRepository(globalModule, provider.get(), provider2.get());
    }

    public static GlobalModule_ProvideDataRepositoryFactory create(GlobalModule globalModule, Provider<Context> provider, Provider<RestDataSource> provider2) {
        return new GlobalModule_ProvideDataRepositoryFactory(globalModule, provider, provider2);
    }

    public static Repository proxyProvideDataRepository(GlobalModule globalModule, Context context, RestDataSource restDataSource) {
        return (Repository) Preconditions.checkNotNull(globalModule.provideDataRepository(context, restDataSource), "Cannot return null from a non-@Nullable @Provides method");
    }
}