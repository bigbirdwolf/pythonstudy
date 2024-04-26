package com.yltx.oil.partner.modules.login.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.login.presenter.ForgetPwdPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ForgetPasswordActivity_MembersInjector implements MembersInjector<ForgetPasswordActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<ForgetPwdPresenter> mPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public ForgetPasswordActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ForgetPwdPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.mPresenterProvider = provider3;
    }

    public static MembersInjector<ForgetPasswordActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ForgetPwdPresenter> provider3) {
        return new ForgetPasswordActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ForgetPasswordActivity forgetPasswordActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(forgetPasswordActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(forgetPasswordActivity, this.frameworkFragmentInjectorProvider.get());
        injectMPresenter(forgetPasswordActivity, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(ForgetPasswordActivity forgetPasswordActivity, ForgetPwdPresenter forgetPwdPresenter) {
        forgetPasswordActivity.mPresenter = forgetPwdPresenter;
    }
}