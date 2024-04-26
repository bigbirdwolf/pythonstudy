package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.PhoneActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {PhoneActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_PhoneActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface PhoneActivitySubcomponent extends AndroidInjector<PhoneActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<PhoneActivity> {
        }
    }

    @ActivityKey(PhoneActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(PhoneActivitySubcomponent.Builder builder);

    private BuildersModule_PhoneActivity() {
    }
}