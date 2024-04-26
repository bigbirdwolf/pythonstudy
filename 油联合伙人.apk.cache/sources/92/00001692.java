package com.yltx.oil.partner.injections.components;

import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.injections.modules.AppModule;
import com.yltx.oil.partner.injections.modules.BuildersModule;
import com.yltx.oil.partner.injections.modules.DebugInstrumentationModule;
import com.yltx.oil.partner.injections.modules.GlobalModule;
import com.yltx.oil.partner.injections.modules.NetworkModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Component(modules = {AppModule.class, GlobalModule.class, NetworkModule.class, BuildersModule.class, DebugInstrumentationModule.class, AndroidSupportInjectionModule.class})
@Singleton
/* loaded from: classes.dex */
interface GlobalComponent extends AndroidInjector<PartnerApplication> {

    @Component.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder extends AndroidInjector.Builder<PartnerApplication> {
    }
}