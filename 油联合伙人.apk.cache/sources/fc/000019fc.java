package com.yltx.oil.partner.modules.profit.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.AccountBalancePresenter;
import com.yltx.oil.partner.modules.profit.presenter.CommissionPresenter;
import com.yltx.oil.partner.modules.profit.presenter.FragmentProfit_yjjsjl_Presenter;
import com.yltx.oil.partner.modules.profit.presenter.JoinPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FragmentProfit_MembersInjector implements MembersInjector<FragmentProfit> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<CommissionPresenter> commissionPresenterProvider;
    private final Provider<JoinPresenter> joinPresenterProvider;
    private final Provider<AccountBalancePresenter> mPresenterProvider;
    private final Provider<FragmentProfit_yjjsjl_Presenter> profit_yjjsjl_presenterProvider;

    public FragmentProfit_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<AccountBalancePresenter> provider2, Provider<FragmentProfit_yjjsjl_Presenter> provider3, Provider<CommissionPresenter> provider4, Provider<JoinPresenter> provider5) {
        this.childFragmentInjectorProvider = provider;
        this.mPresenterProvider = provider2;
        this.profit_yjjsjl_presenterProvider = provider3;
        this.commissionPresenterProvider = provider4;
        this.joinPresenterProvider = provider5;
    }

    public static MembersInjector<FragmentProfit> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<AccountBalancePresenter> provider2, Provider<FragmentProfit_yjjsjl_Presenter> provider3, Provider<CommissionPresenter> provider4, Provider<JoinPresenter> provider5) {
        return new FragmentProfit_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FragmentProfit fragmentProfit) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentProfit, this.childFragmentInjectorProvider.get());
        injectMPresenter(fragmentProfit, this.mPresenterProvider.get());
        injectProfit_yjjsjl_presenter(fragmentProfit, this.profit_yjjsjl_presenterProvider.get());
        injectCommissionPresenter(fragmentProfit, this.commissionPresenterProvider.get());
        injectJoinPresenter(fragmentProfit, this.joinPresenterProvider.get());
    }

    public static void injectMPresenter(FragmentProfit fragmentProfit, AccountBalancePresenter accountBalancePresenter) {
        fragmentProfit.mPresenter = accountBalancePresenter;
    }

    public static void injectProfit_yjjsjl_presenter(FragmentProfit fragmentProfit, FragmentProfit_yjjsjl_Presenter fragmentProfit_yjjsjl_Presenter) {
        fragmentProfit.profit_yjjsjl_presenter = fragmentProfit_yjjsjl_Presenter;
    }

    public static void injectCommissionPresenter(FragmentProfit fragmentProfit, CommissionPresenter commissionPresenter) {
        fragmentProfit.commissionPresenter = commissionPresenter;
    }

    public static void injectJoinPresenter(FragmentProfit fragmentProfit, JoinPresenter joinPresenter) {
        fragmentProfit.joinPresenter = joinPresenter;
    }
}