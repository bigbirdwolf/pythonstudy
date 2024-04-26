package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.main.MainActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MainActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_MainActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<MainActivity> {
        }
    }

    @ActivityKey(MainActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(MainActivitySubcomponent.Builder builder);

    private BuildersModule_MainActivity() {
    }
}