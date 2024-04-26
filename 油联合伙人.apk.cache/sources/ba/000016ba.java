package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ComplainValetActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_ComplainValetActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface ComplainValetActivitySubcomponent extends AndroidInjector<ComplainValetActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<ComplainValetActivity> {
        }
    }

    @ActivityKey(ComplainValetActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(ComplainValetActivitySubcomponent.Builder builder);

    private BuildersModule_ComplainValetActivity() {
    }
}