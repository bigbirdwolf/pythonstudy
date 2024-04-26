package com.yltx.oil.partner.injections.modules;

import android.content.Context;
import com.yltx.oil.partner.base.PartnerApplication;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
/* loaded from: classes.dex */
public class AppModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Context provideContext(PartnerApplication partnerApplication) {
        return partnerApplication;
    }
}