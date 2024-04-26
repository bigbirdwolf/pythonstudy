package com.yltx.oil.partner.modules.profit.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.AccountBalancePresenter;
import com.yltx.oil.partner.modules.profit.presenter.UserAccountPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AccountdetailsActivity_MembersInjector implements MembersInjector<AccountdetailsActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<AccountBalancePresenter> mPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;
    private final Provider<UserAccountPresenter> userAccountPresenterProvider;

    public AccountdetailsActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<UserAccountPresenter> provider3, Provider<AccountBalancePresenter> provider4) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.userAccountPresenterProvider = provider3;
        this.mPresenterProvider = provider4;
    }

    public static MembersInjector<AccountdetailsActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<UserAccountPresenter> provider3, Provider<AccountBalancePresenter> provider4) {
        return new AccountdetailsActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AccountdetailsActivity accountdetailsActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(accountdetailsActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(accountdetailsActivity, this.frameworkFragmentInjectorProvider.get());
        injectUserAccountPresenter(accountdetailsActivity, this.userAccountPresenterProvider.get());
        injectMPresenter(accountdetailsActivity, this.mPresenterProvider.get());
    }

    public static void injectUserAccountPresenter(AccountdetailsActivity accountdetailsActivity, UserAccountPresenter userAccountPresenter) {
        accountdetailsActivity.userAccountPresenter = userAccountPresenter;
    }

    public static void injectMPresenter(AccountdetailsActivity accountdetailsActivity, AccountBalancePresenter accountBalancePresenter) {
        accountdetailsActivity.mPresenter = accountBalancePresenter;
    }
}