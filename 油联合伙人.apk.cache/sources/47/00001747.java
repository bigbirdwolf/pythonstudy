package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.activity.YLTXLoginActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {YLTXLoginActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_YltxLoginActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface YLTXLoginActivitySubcomponent extends AndroidInjector<YLTXLoginActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<YLTXLoginActivity> {
        }
    }

    @ActivityKey(YLTXLoginActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(YLTXLoginActivitySubcomponent.Builder builder);

    private BuildersModule_YltxLoginActivity() {
    }
}