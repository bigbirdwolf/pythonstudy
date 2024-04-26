package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.RechargePresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RechargeActivity_MembersInjector implements MembersInjector<RechargeActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<RechargePresenter> mPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public RechargeActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<RechargePresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.mPresenterProvider = provider3;
    }

    public static MembersInjector<RechargeActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<RechargePresenter> provider3) {
        return new RechargeActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RechargeActivity rechargeActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(rechargeActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(rechargeActivity, this.frameworkFragmentInjectorProvider.get());
        injectMPresenter(rechargeActivity, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(RechargeActivity rechargeActivity, RechargePresenter rechargePresenter) {
        rechargeActivity.mPresenter = rechargePresenter;
    }
}