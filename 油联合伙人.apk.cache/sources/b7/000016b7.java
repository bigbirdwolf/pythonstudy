package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.activity.CommoditySharingInforActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {CommoditySharingInforActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_CommoditySharingInforActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface CommoditySharingInforActivitySubcomponent extends AndroidInjector<CommoditySharingInforActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<CommoditySharingInforActivity> {
        }
    }

    @ActivityKey(CommoditySharingInforActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(CommoditySharingInforActivitySubcomponent.Builder builder);

    private BuildersModule_CommoditySharingInforActivity() {
    }
}