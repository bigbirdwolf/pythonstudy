package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.oiltrade.fragment.ShopFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {ShopFragmentSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_ShopFragment {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface ShopFragmentSubcomponent extends AndroidInjector<ShopFragment> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<ShopFragment> {
        }
    }

    @FragmentKey(ShopFragment.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(ShopFragmentSubcomponent.Builder builder);

    private BuildersModule_ShopFragment() {
    }
}