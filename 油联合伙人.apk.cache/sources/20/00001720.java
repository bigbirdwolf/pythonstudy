package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.activity.RegisterActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {RegisterActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_RegisterActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface RegisterActivitySubcomponent extends AndroidInjector<RegisterActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<RegisterActivity> {
        }
    }

    @ActivityKey(RegisterActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(RegisterActivitySubcomponent.Builder builder);

    private BuildersModule_RegisterActivity() {
    }
}