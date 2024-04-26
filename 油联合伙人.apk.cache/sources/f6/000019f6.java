package com.yltx.oil.partner.modules.profit.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.AllordersPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FailureoftheorderFragment_MembersInjector implements MembersInjector<FailureoftheorderFragment> {
    private final Provider<AllordersPresenter> allordersPresenterProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public FailureoftheorderFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<AllordersPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.allordersPresenterProvider = provider2;
    }

    public static MembersInjector<FailureoftheorderFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<AllordersPresenter> provider2) {
        return new FailureoftheorderFragment_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FailureoftheorderFragment failureoftheorderFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(failureoftheorderFragment, this.childFragmentInjectorProvider.get());
        injectAllordersPresenter(failureoftheorderFragment, this.allordersPresenterProvider.get());
    }

    public static void injectAllordersPresenter(FailureoftheorderFragment failureoftheorderFragment, AllordersPresenter allordersPresenter) {
        failureoftheorderFragment.allordersPresenter = allordersPresenter;
    }
}