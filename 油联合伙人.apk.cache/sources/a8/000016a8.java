package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.activity.ApplyingPartnerActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ApplyingPartnerActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_ApplyingPartnerActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface ApplyingPartnerActivitySubcomponent extends AndroidInjector<ApplyingPartnerActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<ApplyingPartnerActivity> {
        }
    }

    @ActivityKey(ApplyingPartnerActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(ApplyingPartnerActivitySubcomponent.Builder builder);

    private BuildersModule_ApplyingPartnerActivity() {
    }
}