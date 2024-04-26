package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.oiltrade.presenter.FinanceCardPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class OilCardFragment_MembersInjector implements MembersInjector<OilCardFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<FinanceCardPresenter> mPresenterProvider;

    public OilCardFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<FinanceCardPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.mPresenterProvider = provider2;
    }

    public static MembersInjector<OilCardFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<FinanceCardPresenter> provider2) {
        return new OilCardFragment_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OilCardFragment oilCardFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(oilCardFragment, this.childFragmentInjectorProvider.get());
        injectMPresenter(oilCardFragment, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(OilCardFragment oilCardFragment, FinanceCardPresenter financeCardPresenter) {
        oilCardFragment.mPresenter = financeCardPresenter;
    }
}