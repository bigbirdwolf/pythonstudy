package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.ClipImageActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ClipImageActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_ClipImageActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface ClipImageActivitySubcomponent extends AndroidInjector<ClipImageActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<ClipImageActivity> {
        }
    }

    @ActivityKey(ClipImageActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(ClipImageActivitySubcomponent.Builder builder);

    private BuildersModule_ClipImageActivity() {
    }
}