package com.yltx.oil.partner.modules.profit.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.AllordersPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class EffectiveorderFragment_MembersInjector implements MembersInjector<EffectiveorderFragment> {
    private final Provider<AllordersPresenter> allordersPresenterProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public EffectiveorderFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<AllordersPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.allordersPresenterProvider = provider2;
    }

    public static MembersInjector<EffectiveorderFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<AllordersPresenter> provider2) {
        return new EffectiveorderFragment_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EffectiveorderFragment effectiveorderFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(effectiveorderFragment, this.childFragmentInjectorProvider.get());
        injectAllordersPresenter(effectiveorderFragment, this.allordersPresenterProvider.get());
    }

    public static void injectAllordersPresenter(EffectiveorderFragment effectiveorderFragment, AllordersPresenter allordersPresenter) {
        effectiveorderFragment.allordersPresenter = allordersPresenter;
    }
}