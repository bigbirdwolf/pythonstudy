package com.yltx.oil.partner.modules;

import android.app.Fragment;
import com.yltx.oil.partner.modules.login.presenter.LoginPresenter;
import com.yltx.oil.partner.modules.main.presenter.SplashPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SplashActivity_MembersInjector implements MembersInjector<SplashActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<LoginPresenter> mPresenterProvider;
    private final Provider<SplashPresenter> presenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public SplashActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<LoginPresenter> provider3, Provider<SplashPresenter> provider4) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.mPresenterProvider = provider3;
        this.presenterProvider = provider4;
    }

    public static MembersInjector<SplashActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<LoginPresenter> provider3, Provider<SplashPresenter> provider4) {
        return new SplashActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SplashActivity splashActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(splashActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(splashActivity, this.frameworkFragmentInjectorProvider.get());
        injectMPresenter(splashActivity, this.mPresenterProvider.get());
        injectPresenter(splashActivity, this.presenterProvider.get());
    }

    public static void injectMPresenter(SplashActivity splashActivity, LoginPresenter loginPresenter) {
        splashActivity.mPresenter = loginPresenter;
    }

    public static void injectPresenter(SplashActivity splashActivity, SplashPresenter splashPresenter) {
        splashActivity.presenter = splashPresenter;
    }
}