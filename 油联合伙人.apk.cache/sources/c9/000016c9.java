package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.FeedbackActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {FeedbackActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_FeedbackActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface FeedbackActivitySubcomponent extends AndroidInjector<FeedbackActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<FeedbackActivity> {
        }
    }

    @ActivityKey(FeedbackActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(FeedbackActivitySubcomponent.Builder builder);

    private BuildersModule_FeedbackActivity() {
    }
}