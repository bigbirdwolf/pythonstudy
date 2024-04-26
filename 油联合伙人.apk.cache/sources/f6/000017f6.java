package com.yltx.oil.partner.modules.login.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.login.presenter.AppLoginStatusPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class NonoilLoginActivity_MembersInjector implements MembersInjector<NonoilLoginActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<AppLoginStatusPresenter> presenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public NonoilLoginActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<AppLoginStatusPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.presenterProvider = provider3;
    }

    public static MembersInjector<NonoilLoginActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<AppLoginStatusPresenter> provider3) {
        return new NonoilLoginActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NonoilLoginActivity nonoilLoginActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(nonoilLoginActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(nonoilLoginActivity, this.frameworkFragmentInjectorProvider.get());
        injectPresenter(nonoilLoginActivity, this.presenterProvider.get());
    }

    public static void injectPresenter(NonoilLoginActivity nonoilLoginActivity, AppLoginStatusPresenter appLoginStatusPresenter) {
        nonoilLoginActivity.presenter = appLoginStatusPresenter;
    }
}