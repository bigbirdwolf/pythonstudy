package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.fragment.AllordersFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {AllordersFragmentSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_AllordersFragment {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface AllordersFragmentSubcomponent extends AndroidInjector<AllordersFragment> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<AllordersFragment> {
        }
    }

    @FragmentKey(AllordersFragment.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(AllordersFragmentSubcomponent.Builder builder);

    private BuildersModule_AllordersFragment() {
    }
}