package com.yltx.oil.partner.modules.profit.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.FragmentProfit_yjjsjl_Presenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SettlementrecordsActivity_MembersInjector implements MembersInjector<SettlementrecordsActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<FragmentProfit_yjjsjl_Presenter> profit_yjjsjl_presenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public SettlementrecordsActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<FragmentProfit_yjjsjl_Presenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.profit_yjjsjl_presenterProvider = provider3;
    }

    public static MembersInjector<SettlementrecordsActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<FragmentProfit_yjjsjl_Presenter> provider3) {
        return new SettlementrecordsActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SettlementrecordsActivity settlementrecordsActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(settlementrecordsActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(settlementrecordsActivity, this.frameworkFragmentInjectorProvider.get());
        injectProfit_yjjsjl_presenter(settlementrecordsActivity, this.profit_yjjsjl_presenterProvider.get());
    }

    public static void injectProfit_yjjsjl_presenter(SettlementrecordsActivity settlementrecordsActivity, FragmentProfit_yjjsjl_Presenter fragmentProfit_yjjsjl_Presenter) {
        settlementrecordsActivity.profit_yjjsjl_presenter = fragmentProfit_yjjsjl_Presenter;
    }
}