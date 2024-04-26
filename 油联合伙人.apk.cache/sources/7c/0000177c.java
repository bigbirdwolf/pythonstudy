package com.yltx.oil.partner.modules.home.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.home.presenter.MessageNotificationPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MessageHomeActivity_MembersInjector implements MembersInjector<MessageHomeActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<MessageNotificationPresenter> messageNotificationPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public MessageHomeActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MessageNotificationPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.messageNotificationPresenterProvider = provider3;
    }

    public static MembersInjector<MessageHomeActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MessageNotificationPresenter> provider3) {
        return new MessageHomeActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MessageHomeActivity messageHomeActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(messageHomeActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(messageHomeActivity, this.frameworkFragmentInjectorProvider.get());
        injectMessageNotificationPresenter(messageHomeActivity, this.messageNotificationPresenterProvider.get());
    }

    public static void injectMessageNotificationPresenter(MessageHomeActivity messageHomeActivity, MessageNotificationPresenter messageNotificationPresenter) {
        messageHomeActivity.messageNotificationPresenter = messageNotificationPresenter;
    }
}