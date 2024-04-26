package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.oiltrade.presenter.ShopPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ShopFragment_MembersInjector implements MembersInjector<ShopFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<ShopPresenter> mPresenterProvider;

    public ShopFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<ShopPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.mPresenterProvider = provider2;
    }

    public static MembersInjector<ShopFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<ShopPresenter> provider2) {
        return new ShopFragment_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ShopFragment shopFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(shopFragment, this.childFragmentInjectorProvider.get());
        injectMPresenter(shopFragment, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(ShopFragment shopFragment, ShopPresenter shopPresenter) {
        shopFragment.mPresenter = shopPresenter;
    }
}