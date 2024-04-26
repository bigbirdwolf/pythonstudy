package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.fragment.EffectiveorderFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {EffectiveorderFragmentSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_EffectiveorderFragment {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface EffectiveorderFragmentSubcomponent extends AndroidInjector<EffectiveorderFragment> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<EffectiveorderFragment> {
        }
    }

    @FragmentKey(EffectiveorderFragment.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(EffectiveorderFragmentSubcomponent.Builder builder);

    private BuildersModule_EffectiveorderFragment() {
    }
}