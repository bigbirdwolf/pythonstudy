package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.login.activity.YLSPLoginActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {YLSPLoginActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_YlspLoginActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface YLSPLoginActivitySubcomponent extends AndroidInjector<YLSPLoginActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<YLSPLoginActivity> {
        }
    }

    @ActivityKey(YLSPLoginActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(YLSPLoginActivitySubcomponent.Builder builder);

    private BuildersModule_YlspLoginActivity() {
    }
}