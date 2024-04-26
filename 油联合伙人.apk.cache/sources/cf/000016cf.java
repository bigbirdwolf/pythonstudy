package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.home.fragment.FragmentHome;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {FragmentHomeSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_FragmentHome {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface FragmentHomeSubcomponent extends AndroidInjector<FragmentHome> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<FragmentHome> {
        }
    }

    @FragmentKey(FragmentHome.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(FragmentHomeSubcomponent.Builder builder);

    private BuildersModule_FragmentHome() {
    }
}