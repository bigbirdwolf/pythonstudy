package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.oiltrade.presenter.FuelCardListPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RefuelingCardFragment_MembersInjector implements MembersInjector<RefuelingCardFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<FuelCardListPresenter> mPresenterProvider;

    public RefuelingCardFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<FuelCardListPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.mPresenterProvider = provider2;
    }

    public static MembersInjector<RefuelingCardFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<FuelCardListPresenter> provider2) {
        return new RefuelingCardFragment_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RefuelingCardFragment refuelingCardFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(refuelingCardFragment, this.childFragmentInjectorProvider.get());
        injectMPresenter(refuelingCardFragment, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(RefuelingCardFragment refuelingCardFragment, FuelCardListPresenter fuelCardListPresenter) {
        refuelingCardFragment.mPresenter = fuelCardListPresenter;
    }
}