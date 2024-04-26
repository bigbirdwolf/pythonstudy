package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.activity.OptiondateActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {OptiondateActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_OptiondateActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface OptiondateActivitySubcomponent extends AndroidInjector<OptiondateActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<OptiondateActivity> {
        }
    }

    @ActivityKey(OptiondateActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(OptiondateActivitySubcomponent.Builder builder);

    private BuildersModule_OptiondateActivity() {
    }
}