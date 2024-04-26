package com.yltx.oil.partner.base;

import android.support.v4.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class BaseFragment_MembersInjector implements MembersInjector<BaseFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public BaseFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        this.childFragmentInjectorProvider = provider;
    }

    public static MembersInjector<BaseFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        return new BaseFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BaseFragment baseFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(baseFragment, this.childFragmentInjectorProvider.get());
    }
}