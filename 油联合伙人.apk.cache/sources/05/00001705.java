package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.activity.NonoilLoginActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {NonoilLoginActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_NonoilLoginActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface NonoilLoginActivitySubcomponent extends AndroidInjector<NonoilLoginActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<NonoilLoginActivity> {
        }
    }

    @ActivityKey(NonoilLoginActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(NonoilLoginActivitySubcomponent.Builder builder);

    private BuildersModule_NonoilLoginActivity() {
    }
}