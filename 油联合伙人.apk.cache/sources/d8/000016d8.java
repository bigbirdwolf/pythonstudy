package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.profit.fragment.FragmentProfit;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {FragmentProfitSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_FragmentProfit {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface FragmentProfitSubcomponent extends AndroidInjector<FragmentProfit> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<FragmentProfit> {
        }
    }

    @FragmentKey(FragmentProfit.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(FragmentProfitSubcomponent.Builder builder);

    private BuildersModule_FragmentProfit() {
    }
}