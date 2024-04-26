package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {LoginActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_LoginActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface LoginActivitySubcomponent extends AndroidInjector<LoginActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<LoginActivity> {
        }
    }

    @ActivityKey(LoginActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(LoginActivitySubcomponent.Builder builder);

    private BuildersModule_LoginActivity() {
    }
}