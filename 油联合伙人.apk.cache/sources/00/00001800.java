package com.yltx.oil.partner.modules.login.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.login.presenter.AppLoginStatusPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class YLTXLoginActivity_MembersInjector implements MembersInjector<YLTXLoginActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<AppLoginStatusPresenter> presenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public YLTXLoginActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<AppLoginStatusPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.presenterProvider = provider3;
    }

    public static MembersInjector<YLTXLoginActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<AppLoginStatusPresenter> provider3) {
        return new YLTXLoginActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(YLTXLoginActivity yLTXLoginActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(yLTXLoginActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(yLTXLoginActivity, this.frameworkFragmentInjectorProvider.get());
        injectPresenter(yLTXLoginActivity, this.presenterProvider.get());
    }

    public static void injectPresenter(YLTXLoginActivity yLTXLoginActivity, AppLoginStatusPresenter appLoginStatusPresenter) {
        yLTXLoginActivity.presenter = appLoginStatusPresenter;
    }
}