package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.oiltrade.fragment.RefuelingCardFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {RefuelingCardFragmentSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_RefuelingCardFragment {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface RefuelingCardFragmentSubcomponent extends AndroidInjector<RefuelingCardFragment> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<RefuelingCardFragment> {
        }
    }

    @FragmentKey(RefuelingCardFragment.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(RefuelingCardFragmentSubcomponent.Builder builder);

    private BuildersModule_RefuelingCardFragment() {
    }
}