package com.yltx.oil.partner.modules.home.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.oiltrade.presenter.FinanceCardetailPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class CommoditySharingInforActivity_MembersInjector implements MembersInjector<CommoditySharingInforActivity> {
    private final Provider<FinanceCardetailPresenter> financeCardetailPresenterProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public CommoditySharingInforActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<FinanceCardetailPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.financeCardetailPresenterProvider = provider3;
    }

    public static MembersInjector<CommoditySharingInforActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<FinanceCardetailPresenter> provider3) {
        return new CommoditySharingInforActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommoditySharingInforActivity commoditySharingInforActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(commoditySharingInforActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(commoditySharingInforActivity, this.frameworkFragmentInjectorProvider.get());
        injectFinanceCardetailPresenter(commoditySharingInforActivity, this.financeCardetailPresenterProvider.get());
    }

    public static void injectFinanceCardetailPresenter(CommoditySharingInforActivity commoditySharingInforActivity, FinanceCardetailPresenter financeCardetailPresenter) {
        commoditySharingInforActivity.financeCardetailPresenter = financeCardetailPresenter;
    }
}