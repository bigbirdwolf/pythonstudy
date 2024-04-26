package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.support.v4.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FragmentOilTrade_MembersInjector implements MembersInjector<FragmentOilTrade> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public FragmentOilTrade_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        this.childFragmentInjectorProvider = provider;
    }

    public static MembersInjector<FragmentOilTrade> create(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        return new FragmentOilTrade_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FragmentOilTrade fragmentOilTrade) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentOilTrade, this.childFragmentInjectorProvider.get());
    }
}