package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.mine.fragment.FragmentMine;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {FragmentMineSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_FragmentMine {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface FragmentMineSubcomponent extends AndroidInjector<FragmentMine> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<FragmentMine> {
        }
    }

    @FragmentKey(FragmentMine.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(FragmentMineSubcomponent.Builder builder);

    private BuildersModule_FragmentMine() {
    }
}