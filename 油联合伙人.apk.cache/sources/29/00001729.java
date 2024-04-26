package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.activity.SettlementrecordsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {SettlementrecordsActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_SettlementrecordsActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface SettlementrecordsActivitySubcomponent extends AndroidInjector<SettlementrecordsActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<SettlementrecordsActivity> {
        }
    }

    @ActivityKey(SettlementrecordsActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(SettlementrecordsActivitySubcomponent.Builder builder);

    private BuildersModule_SettlementrecordsActivity() {
    }
}