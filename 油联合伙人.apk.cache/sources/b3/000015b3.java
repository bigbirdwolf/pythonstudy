package com.yltx.oil.partner.data.repository;

import android.content.Context;
import com.yltx.oil.partner.data.datasource.DataSourceFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DataRepository_Factory implements Factory<DataRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<DataSourceFactory> dataSourceFactoryProvider;

    public DataRepository_Factory(Provider<Context> provider, Provider<DataSourceFactory> provider2) {
        this.contextProvider = provider;
        this.dataSourceFactoryProvider = provider2;
    }

    @Override // javax.inject.Provider
    public DataRepository get() {
        return provideInstance(this.contextProvider, this.dataSourceFactoryProvider);
    }

    public static DataRepository provideInstance(Provider<Context> provider, Provider<DataSourceFactory> provider2) {
        return new DataRepository(provider.get(), provider2.get());
    }

    public static DataRepository_Factory create(Provider<Context> provider, Provider<DataSourceFactory> provider2) {
        return new DataRepository_Factory(provider, provider2);
    }

    public static DataRepository newDataRepository(Context context, DataSourceFactory dataSourceFactory) {
        return new DataRepository(context, dataSourceFactory);
    }
}