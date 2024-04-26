package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.activity.ClassificationActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ClassificationActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_ClassificationActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface ClassificationActivitySubcomponent extends AndroidInjector<ClassificationActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<ClassificationActivity> {
        }
    }

    @ActivityKey(ClassificationActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(ClassificationActivitySubcomponent.Builder builder);

    private BuildersModule_ClassificationActivity() {
    }
}