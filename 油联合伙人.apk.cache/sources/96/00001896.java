package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.InviteDetailPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class InvitationDetailsActivity_MembersInjector implements MembersInjector<InvitationDetailsActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<InviteDetailPresenter> presenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public InvitationDetailsActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<InviteDetailPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.presenterProvider = provider3;
    }

    public static MembersInjector<InvitationDetailsActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<InviteDetailPresenter> provider3) {
        return new InvitationDetailsActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(InvitationDetailsActivity invitationDetailsActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(invitationDetailsActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(invitationDetailsActivity, this.frameworkFragmentInjectorProvider.get());
        injectPresenter(invitationDetailsActivity, this.presenterProvider.get());
    }

    public static void injectPresenter(InvitationDetailsActivity invitationDetailsActivity, InviteDetailPresenter inviteDetailPresenter) {
        invitationDetailsActivity.presenter = inviteDetailPresenter;
    }
}