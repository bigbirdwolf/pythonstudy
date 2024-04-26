package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.oiltrade.presenter.GiftCardCardPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GiftCardFragment_MembersInjector implements MembersInjector<GiftCardFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<GiftCardCardPresenter> mPresenterProvider;

    public GiftCardFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<GiftCardCardPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.mPresenterProvider = provider2;
    }

    public static MembersInjector<GiftCardFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<GiftCardCardPresenter> provider2) {
        return new GiftCardFragment_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(GiftCardFragment giftCardFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(giftCardFragment, this.childFragmentInjectorProvider.get());
        injectMPresenter(giftCardFragment, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(GiftCardFragment giftCardFragment, GiftCardCardPresenter giftCardCardPresenter) {
        giftCardFragment.mPresenter = giftCardCardPresenter;
    }
}