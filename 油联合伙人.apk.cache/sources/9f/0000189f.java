package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.MineInfoPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MineInfoActivity_MembersInjector implements MembersInjector<MineInfoActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<MineInfoPresenter> mPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public MineInfoActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MineInfoPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.mPresenterProvider = provider3;
    }

    public static MembersInjector<MineInfoActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MineInfoPresenter> provider3) {
        return new MineInfoActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MineInfoActivity mineInfoActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(mineInfoActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(mineInfoActivity, this.frameworkFragmentInjectorProvider.get());
        injectMPresenter(mineInfoActivity, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(MineInfoActivity mineInfoActivity, MineInfoPresenter mineInfoPresenter) {
        mineInfoActivity.mPresenter = mineInfoPresenter;
    }
}