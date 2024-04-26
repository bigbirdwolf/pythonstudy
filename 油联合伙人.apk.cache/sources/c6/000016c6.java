package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.fragment.FailureoftheorderFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {FailureoftheorderFragmentSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_FailureoftheorderFragment {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface FailureoftheorderFragmentSubcomponent extends AndroidInjector<FailureoftheorderFragment> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<FailureoftheorderFragment> {
        }
    }

    @FragmentKey(FailureoftheorderFragment.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(FailureoftheorderFragmentSubcomponent.Builder builder);

    private BuildersModule_FailureoftheorderFragment() {
    }
}