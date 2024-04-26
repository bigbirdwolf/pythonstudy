package com.yltx.oil.partner.modules.login.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.login.presenter.GetValidCodePresenter;
import com.yltx.oil.partner.modules.login.presenter.LoginPresenter;
import com.yltx.oil.partner.modules.login.presenter.RegisterPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RegisterActivity_MembersInjector implements MembersInjector<RegisterActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<GetValidCodePresenter> getValidCodePresenterProvider;
    private final Provider<LoginPresenter> mPresenterProvider;
    private final Provider<RegisterPresenter> registerPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public RegisterActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<GetValidCodePresenter> provider3, Provider<RegisterPresenter> provider4, Provider<LoginPresenter> provider5) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.getValidCodePresenterProvider = provider3;
        this.registerPresenterProvider = provider4;
        this.mPresenterProvider = provider5;
    }

    public static MembersInjector<RegisterActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<GetValidCodePresenter> provider3, Provider<RegisterPresenter> provider4, Provider<LoginPresenter> provider5) {
        return new RegisterActivity_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RegisterActivity registerActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(registerActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(registerActivity, this.frameworkFragmentInjectorProvider.get());
        injectGetValidCodePresenter(registerActivity, this.getValidCodePresenterProvider.get());
        injectRegisterPresenter(registerActivity, this.registerPresenterProvider.get());
        injectMPresenter(registerActivity, this.mPresenterProvider.get());
    }

    public static void injectGetValidCodePresenter(RegisterActivity registerActivity, GetValidCodePresenter getValidCodePresenter) {
        registerActivity.getValidCodePresenter = getValidCodePresenter;
    }

    public static void injectRegisterPresenter(RegisterActivity registerActivity, RegisterPresenter registerPresenter) {
        registerActivity.registerPresenter = registerPresenter;
    }

    public static void injectMPresenter(RegisterActivity registerActivity, LoginPresenter loginPresenter) {
        registerActivity.mPresenter = loginPresenter;
    }
}