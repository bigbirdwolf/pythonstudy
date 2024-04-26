package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.ComplaintActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ComplaintActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_ComplaintActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface ComplaintActivitySubcomponent extends AndroidInjector<ComplaintActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<ComplaintActivity> {
        }
    }

    @ActivityKey(ComplaintActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(ComplaintActivitySubcomponent.Builder builder);

    private BuildersModule_ComplaintActivity() {
    }
}