package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.oiltrade.fragment.OilCardFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {OilCardFragmentSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_OilCardFragment {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface OilCardFragmentSubcomponent extends AndroidInjector<OilCardFragment> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<OilCardFragment> {
        }
    }

    @FragmentKey(OilCardFragment.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(OilCardFragmentSubcomponent.Builder builder);

    private BuildersModule_OilCardFragment() {
    }
}