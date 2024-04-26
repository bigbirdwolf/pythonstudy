package com.yltx.oil.partner.injections.modules;

import android.app.Activity;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.activity.MineInfoActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MineInfoActivitySubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_MineInfoActivity {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface MineInfoActivitySubcomponent extends AndroidInjector<MineInfoActivity> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<MineInfoActivity> {
        }
    }

    @ActivityKey(MineInfoActivity.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(MineInfoActivitySubcomponent.Builder builder);

    private BuildersModule_MineInfoActivity() {
    }
}