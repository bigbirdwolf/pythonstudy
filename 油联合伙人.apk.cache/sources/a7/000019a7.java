package com.yltx.oil.partner.modules.profit.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.TxHistoryPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class TxHistoryActivity_MembersInjector implements MembersInjector<TxHistoryActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<TxHistoryPresenter> mPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public TxHistoryActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<TxHistoryPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.mPresenterProvider = provider3;
    }

    public static MembersInjector<TxHistoryActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<TxHistoryPresenter> provider3) {
        return new TxHistoryActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TxHistoryActivity txHistoryActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(txHistoryActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(txHistoryActivity, this.frameworkFragmentInjectorProvider.get());
        injectMPresenter(txHistoryActivity, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(TxHistoryActivity txHistoryActivity, TxHistoryPresenter txHistoryPresenter) {
        txHistoryActivity.mPresenter = txHistoryPresenter;
    }
}