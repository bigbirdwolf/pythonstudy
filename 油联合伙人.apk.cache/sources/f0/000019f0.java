package com.yltx.oil.partner.modules.profit.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.AllordersPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AllordersFragment_MembersInjector implements MembersInjector<AllordersFragment> {
    private final Provider<AllordersPresenter> allordersPresenterProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public AllordersFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<AllordersPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.allordersPresenterProvider = provider2;
    }

    public static MembersInjector<AllordersFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<AllordersPresenter> provider2) {
        return new AllordersFragment_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AllordersFragment allordersFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(allordersFragment, this.childFragmentInjectorProvider.get());
        injectAllordersPresenter(allordersFragment, this.allordersPresenterProvider.get());
    }

    public static void injectAllordersPresenter(AllordersFragment allordersFragment, AllordersPresenter allordersPresenter) {
        allordersFragment.allordersPresenter = allordersPresenter;
    }
}