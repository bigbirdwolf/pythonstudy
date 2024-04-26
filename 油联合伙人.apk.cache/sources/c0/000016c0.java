package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.activity.DataanalysisActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {DataanalysisActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_DataanalysisActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface DataanalysisActivitySubcomponent extends AndroidInjector<DataanalysisActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<DataanalysisActivity> {
        }
    }

    @ActivityKey(DataanalysisActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(DataanalysisActivitySubcomponent.Builder builder);

    private BuildersModule_DataanalysisActivity() {
    }
}