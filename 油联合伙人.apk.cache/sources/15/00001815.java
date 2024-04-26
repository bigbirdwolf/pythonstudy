package com.yltx.oil.partner.modules.login.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.login.presenter.GetValidCodePresenter;
import com.yltx.oil.partner.modules.login.presenter.LoginPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FragmentPwdLogin_MembersInjector implements MembersInjector<FragmentPwdLogin> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<GetValidCodePresenter> getValidCodePresenterProvider;
    private final Provider<LoginPresenter> mPresenterProvider;

    public FragmentPwdLogin_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<LoginPresenter> provider2, Provider<GetValidCodePresenter> provider3) {
        this.childFragmentInjectorProvider = provider;
        this.mPresenterProvider = provider2;
        this.getValidCodePresenterProvider = provider3;
    }

    public static MembersInjector<FragmentPwdLogin> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<LoginPresenter> provider2, Provider<GetValidCodePresenter> provider3) {
        return new FragmentPwdLogin_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FragmentPwdLogin fragmentPwdLogin) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentPwdLogin, this.childFragmentInjectorProvider.get());
        injectMPresenter(fragmentPwdLogin, this.mPresenterProvider.get());
        injectGetValidCodePresenter(fragmentPwdLogin, this.getValidCodePresenterProvider.get());
    }

    public static void injectMPresenter(FragmentPwdLogin fragmentPwdLogin, LoginPresenter loginPresenter) {
        fragmentPwdLogin.mPresenter = loginPresenter;
    }

    public static void injectGetValidCodePresenter(FragmentPwdLogin fragmentPwdLogin, GetValidCodePresenter getValidCodePresenter) {
        fragmentPwdLogin.getValidCodePresenter = getValidCodePresenter;
    }
}