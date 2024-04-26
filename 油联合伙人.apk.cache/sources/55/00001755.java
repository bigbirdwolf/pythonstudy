package com.yltx.oil.partner.injections.modules;

import com.yltx.oil.partner.injections.instrumentation.HttpLoggingInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.StethoInstrumentation;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

@Module
/* loaded from: classes.dex */
public final class NetworkModule {
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(StethoInstrumentation stethoInstrumentation, HttpLoggingInstrumentation httpLoggingInstrumentation) {
        return httpLoggingInstrumentation.decorateNetwork(stethoInstrumentation.decorateNetwork(new OkHttpClient.Builder().writeTimeout(10000L, TimeUnit.MILLISECONDS).readTimeout(10000L, TimeUnit.MILLISECONDS).connectTimeout(15000L, TimeUnit.MILLISECONDS).build()));
    }
}