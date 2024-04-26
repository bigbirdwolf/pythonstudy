package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.fragment.RefundFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {RefundFragmentSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_RefundFragment {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface RefundFragmentSubcomponent extends AndroidInjector<RefundFragment> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<RefundFragment> {
        }
    }

    @FragmentKey(RefundFragment.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(RefundFragmentSubcomponent.Builder builder);

    private BuildersModule_RefundFragment() {
    }
}