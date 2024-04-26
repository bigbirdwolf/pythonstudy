package com.yltx.oil.partner.modules.profit.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.SettlementRecordsPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AllFragment_MembersInjector implements MembersInjector<AllFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<SettlementRecordsPresenter> settlementRecordsPresenterProvider;

    public AllFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<SettlementRecordsPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.settlementRecordsPresenterProvider = provider2;
    }

    public static MembersInjector<AllFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<SettlementRecordsPresenter> provider2) {
        return new AllFragment_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AllFragment allFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(allFragment, this.childFragmentInjectorProvider.get());
        injectSettlementRecordsPresenter(allFragment, this.settlementRecordsPresenterProvider.get());
    }

    public static void injectSettlementRecordsPresenter(AllFragment allFragment, SettlementRecordsPresenter settlementRecordsPresenter) {
        allFragment.settlementRecordsPresenter = settlementRecordsPresenter;
    }
}