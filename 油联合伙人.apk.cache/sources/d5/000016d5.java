package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.oiltrade.fragment.FragmentOilTrade;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {FragmentOilTradeSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_FragmentOilTrade {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface FragmentOilTradeSubcomponent extends AndroidInjector<FragmentOilTrade> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<FragmentOilTrade> {
        }
    }

    @FragmentKey(FragmentOilTrade.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(FragmentOilTradeSubcomponent.Builder builder);

    private BuildersModule_FragmentOilTrade() {
    }
}