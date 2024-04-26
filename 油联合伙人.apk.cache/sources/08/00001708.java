package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.NoviceGuideActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {NoviceGuideActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_NoviceGuideActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface NoviceGuideActivitySubcomponent extends AndroidInjector<NoviceGuideActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<NoviceGuideActivity> {
        }
    }

    @ActivityKey(NoviceGuideActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(NoviceGuideActivitySubcomponent.Builder builder);

    private BuildersModule_NoviceGuideActivity() {
    }
}