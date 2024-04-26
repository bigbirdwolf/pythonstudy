package com.yltx.oil.partner.injections.modules;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.oiltrade.fragment.GiftCardFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {GiftCardFragmentSubcomponent.class})
/* loaded from: classes.dex */
public abstract class BuildersModule_GiftcardFragment {

    @ActivityScope
    @Subcomponent
    /* loaded from: classes.dex */
    public interface GiftCardFragmentSubcomponent extends AndroidInjector<GiftCardFragment> {

        @Subcomponent.Builder
        /* loaded from: classes.dex */
        public static abstract class Builder extends AndroidInjector.Builder<GiftCardFragment> {
        }
    }

    @FragmentKey(GiftCardFragment.class)
    @Binds
    @IntoMap
    abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(GiftCardFragmentSubcomponent.Builder builder);

    private BuildersModule_GiftcardFragment() {
    }
}