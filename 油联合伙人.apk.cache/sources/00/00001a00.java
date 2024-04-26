package com.yltx.oil.partner.modules.profit.fragment;

import android.support.v4.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RefundFragment_MembersInjector implements MembersInjector<RefundFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public RefundFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        this.childFragmentInjectorProvider = provider;
    }

    public static MembersInjector<RefundFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider) {
        return new RefundFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RefundFragment refundFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(refundFragment, this.childFragmentInjectorProvider.get());
    }
}