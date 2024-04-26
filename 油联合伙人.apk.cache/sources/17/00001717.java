package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.RechargeActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {RechargeActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_RechargeActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface RechargeActivitySubcomponent extends AndroidInjector<RechargeActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<RechargeActivity> {
        }
    }

    @ActivityKey(RechargeActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(RechargeActivitySubcomponent.Builder builder);

    private BuildersModule_RechargeActivity() {
    }
}