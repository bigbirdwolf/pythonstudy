package com.yltx.oil.partner.injections.modules;

import android.content.Context;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.yltx.oil.partner.data.network.interceptors.HttpLoggingInterceptor;
import com.yltx.oil.partner.injections.instrumentation.ApplicationInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.DebugApplicationInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.HttpLoggingInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.PosInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.StethoInstrumentation;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
/* loaded from: classes.dex */
public class DebugInstrumentationModule {
    @Provides
    @Singleton
    public ApplicationInstrumentation providesInstrumentation(StethoInstrumentation stethoInstrumentation, HttpLoggingInstrumentation httpLoggingInstrumentation) {
        return new DebugApplicationInstrumentation(stethoInstrumentation, httpLoggingInstrumentation);
    }

    @Provides
    @Singleton
    public StethoInstrumentation providesStethoInstrumentation(Context context, StethoInterceptor stethoInterceptor) {
        return new StethoInstrumentation(context, stethoInterceptor);
    }

    @Provides
    @Singleton
    public HttpLoggingInstrumentation providesHttpLoggingInstrumentation(Context context, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new HttpLoggingInstrumentation(context, httpLoggingInterceptor);
    }

    @Provides
    @Singleton
    public PosInstrumentation providesPosInstrumentation(Context context) {
        return new PosInstrumentation(context);
    }

    @Provides
    @Singleton
    public StethoInterceptor providesStethoIntercetor() {
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor providesHttpLoggingIntercetor() {
        return new HttpLoggingInterceptor(HttpLoggingInterceptor.Level.BODY);
    }
}