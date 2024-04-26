package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.MinePhonePresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class PhoneActivity_MembersInjector implements MembersInjector<PhoneActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<MinePhonePresenter> mPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public PhoneActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MinePhonePresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.mPresenterProvider = provider3;
    }

    public static MembersInjector<PhoneActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MinePhonePresenter> provider3) {
        return new PhoneActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PhoneActivity phoneActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(phoneActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(phoneActivity, this.frameworkFragmentInjectorProvider.get());
        injectMPresenter(phoneActivity, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(PhoneActivity phoneActivity, MinePhonePresenter minePhonePresenter) {
        phoneActivity.mPresenter = minePhonePresenter;
    }
}