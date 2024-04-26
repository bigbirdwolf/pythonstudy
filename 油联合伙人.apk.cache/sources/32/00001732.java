package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.SplashActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {SplashActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_SplashActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface SplashActivitySubcomponent extends AndroidInjector<SplashActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<SplashActivity> {
        }
    }

    @ActivityKey(SplashActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(SplashActivitySubcomponent.Builder builder);

    private BuildersModule_SplashActivity() {
    }
}