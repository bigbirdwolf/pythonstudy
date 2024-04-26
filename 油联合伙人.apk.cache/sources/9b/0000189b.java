package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.InvitePresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class InviteCourtesyActivity_MembersInjector implements MembersInjector<InviteCourtesyActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<InvitePresenter> presenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public InviteCourtesyActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<InvitePresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.presenterProvider = provider3;
    }

    public static MembersInjector<InviteCourtesyActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<InvitePresenter> provider3) {
        return new InviteCourtesyActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(InviteCourtesyActivity inviteCourtesyActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(inviteCourtesyActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(inviteCourtesyActivity, this.frameworkFragmentInjectorProvider.get());
        injectPresenter(inviteCourtesyActivity, this.presenterProvider.get());
    }

    public static void injectPresenter(InviteCourtesyActivity inviteCourtesyActivity, InvitePresenter invitePresenter) {
        inviteCourtesyActivity.presenter = invitePresenter;
    }
}