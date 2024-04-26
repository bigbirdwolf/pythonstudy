package com.yltx.oil.partner.modules.home.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.home.presenter.BannerPresenter;
import com.yltx.oil.partner.modules.home.presenter.HomeButtonconfigurationPresenter;
import com.yltx.oil.partner.modules.home.presenter.ShopRecommendPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FragmentHome_MembersInjector implements MembersInjector<FragmentHome> {
    private final Provider<BannerPresenter> bannerPresenterProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<HomeButtonconfigurationPresenter> homeButtonconfigurationPresenterProvider;
    private final Provider<ShopRecommendPresenter> mPresenterProvider;

    public FragmentHome_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<ShopRecommendPresenter> provider2, Provider<BannerPresenter> provider3, Provider<HomeButtonconfigurationPresenter> provider4) {
        this.childFragmentInjectorProvider = provider;
        this.mPresenterProvider = provider2;
        this.bannerPresenterProvider = provider3;
        this.homeButtonconfigurationPresenterProvider = provider4;
    }

    public static MembersInjector<FragmentHome> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<ShopRecommendPresenter> provider2, Provider<BannerPresenter> provider3, Provider<HomeButtonconfigurationPresenter> provider4) {
        return new FragmentHome_MembersInjector(provider, provider2, provider3, provider4);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FragmentHome fragmentHome) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentHome, this.childFragmentInjectorProvider.get());
        injectMPresenter(fragmentHome, this.mPresenterProvider.get());
        injectBannerPresenter(fragmentHome, this.bannerPresenterProvider.get());
        injectHomeButtonconfigurationPresenter(fragmentHome, this.homeButtonconfigurationPresenterProvider.get());
    }

    public static void injectMPresenter(FragmentHome fragmentHome, ShopRecommendPresenter shopRecommendPresenter) {
        fragmentHome.mPresenter = shopRecommendPresenter;
    }

    public static void injectBannerPresenter(FragmentHome fragmentHome, BannerPresenter bannerPresenter) {
        fragmentHome.bannerPresenter = bannerPresenter;
    }

    public static void injectHomeButtonconfigurationPresenter(FragmentHome fragmentHome, HomeButtonconfigurationPresenter homeButtonconfigurationPresenter) {
        fragmentHome.homeButtonconfigurationPresenter = homeButtonconfigurationPresenter;
    }
}