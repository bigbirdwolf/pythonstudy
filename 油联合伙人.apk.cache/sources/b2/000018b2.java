package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.login.presenter.ForgetPwdPresenter;
import com.yltx.oil.partner.modules.mine.presenter.MinePhonePresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class UpdatePwdActivity_MembersInjector implements MembersInjector<UpdatePwdActivity> {
    private final Provider<ForgetPwdPresenter> forgetPwdPresenterProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<MinePhonePresenter> mPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public UpdatePwdActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ForgetPwdPresenter> provider3, Provider<MinePhonePresenter> provider4) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.forgetPwdPresenterProvider = provider3;
        this.mPresenterProvider = provider4;
    }

    public static MembersInjector<UpdatePwdActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ForgetPwdPresenter> provider3, Provider<MinePhonePresenter> provider4) {
        return new UpdatePwdActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UpdatePwdActivity updatePwdActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(updatePwdActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(updatePwdActivity, this.frameworkFragmentInjectorProvider.get());
        injectForgetPwdPresenter(updatePwdActivity, this.forgetPwdPresenterProvider.get());
        injectMPresenter(updatePwdActivity, this.mPresenterProvider.get());
    }

    public static void injectForgetPwdPresenter(UpdatePwdActivity updatePwdActivity, ForgetPwdPresenter forgetPwdPresenter) {
        updatePwdActivity.forgetPwdPresenter = forgetPwdPresenter;
    }

    public static void injectMPresenter(UpdatePwdActivity updatePwdActivity, MinePhonePresenter minePhonePresenter) {
        updatePwdActivity.mPresenter = minePhonePresenter;
    }
}