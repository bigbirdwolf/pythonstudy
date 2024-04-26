package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.oiltrade.presenter.StoredValueCardPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class StoredValueCardFragment_MembersInjector implements MembersInjector<StoredValueCardFragment> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<StoredValueCardPresenter> mPresenterProvider;

    public StoredValueCardFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<StoredValueCardPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.mPresenterProvider = provider2;
    }

    public static MembersInjector<StoredValueCardFragment> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<StoredValueCardPresenter> provider2) {
        return new StoredValueCardFragment_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(StoredValueCardFragment storedValueCardFragment) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(storedValueCardFragment, this.childFragmentInjectorProvider.get());
        injectMPresenter(storedValueCardFragment, this.mPresenterProvider.get());
    }

    public static void injectMPresenter(StoredValueCardFragment storedValueCardFragment, StoredValueCardPresenter storedValueCardPresenter) {
        storedValueCardFragment.mPresenter = storedValueCardPresenter;
    }
}