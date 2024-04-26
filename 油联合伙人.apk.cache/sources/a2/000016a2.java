package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.fragment.AllFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {AllFragmentSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_AllFragment {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface AllFragmentSubcomponent extends AndroidInjector<AllFragment> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<AllFragment> {
        }
    }

    @FragmentKey(AllFragment.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(AllFragmentSubcomponent.Builder builder);

    private BuildersModule_AllFragment() {
    }
}