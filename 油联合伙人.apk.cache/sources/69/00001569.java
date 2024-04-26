package com.yltx.oil.partner.data.datasource;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DataSourceFactory_Factory implements Factory<DataSourceFactory> {
    private final Provider<Context> contextProvider;
    private final Provider<RestDataSource> restDataSourceProvider;

    public DataSourceFactory_Factory(Provider<Context> provider, Provider<RestDataSource> provider2) {
        this.contextProvider = provider;
        this.restDataSourceProvider = provider2;
    }

    @Override // javax.inject.Provider
    public DataSourceFactory get() {
        return provideInstance(this.contextProvider, this.restDataSourceProvider);
    }

    public static DataSourceFactory provideInstance(Provider<Context> provider, Provider<RestDataSource> provider2) {
        return new DataSourceFactory(provider.get(), provider2.get());
    }

    public static DataSourceFactory_Factory create(Provider<Context> provider, Provider<RestDataSource> provider2) {
        return new DataSourceFactory_Factory(provider, provider2);
    }

    public static DataSourceFactory newDataSourceFactory(Context context, RestDataSource restDataSource) {
        return new DataSourceFactory(context, restDataSource);
    }
}