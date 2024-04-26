package com.yltx.oil.partner.injections.modules;

import android.content.Context;
import com.yltx.oil.partner.data.datasource.DataSourceFactory;
import com.yltx.oil.partner.data.datasource.RestDataSource;
import com.yltx.oil.partner.data.repository.DataRepository;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.navigation.Navigator;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

@Module
/* loaded from: classes.dex */
public class GlobalModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Repository provideDataRepository(Context context, RestDataSource restDataSource) {
        return new DataRepository(context, new DataSourceFactory(context, restDataSource));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Navigator provideNavigator() {
        return new Navigator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public RestDataSource provideRestDataSource(Context context, OkHttpClient okHttpClient) {
        return new RestDataSource(context, okHttpClient);
    }
}