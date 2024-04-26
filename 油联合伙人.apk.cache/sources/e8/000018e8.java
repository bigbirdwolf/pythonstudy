package com.yltx.oil.partner.modules.mine.fragment;

import android.support.v4.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.MemberPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FragmentMine_MembersInjector implements MembersInjector<FragmentMine> {
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;
    private final Provider<MemberPresenter> memberPresenterProvider;

    public FragmentMine_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<MemberPresenter> provider2) {
        this.childFragmentInjectorProvider = provider;
        this.memberPresenterProvider = provider2;
    }

    public static MembersInjector<FragmentMine> create(Provider<DispatchingAndroidInjector<Fragment>> provider, Provider<MemberPresenter> provider2) {
        return new FragmentMine_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FragmentMine fragmentMine) {
        DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentMine, this.childFragmentInjectorProvider.get());
        injectMemberPresenter(fragmentMine, this.memberPresenterProvider.get());
    }

    public static void injectMemberPresenter(FragmentMine fragmentMine, MemberPresenter memberPresenter) {
        fragmentMine.memberPresenter = memberPresenter;
    }
}