package com.yltx.oil.partner.modules.home.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.home.presenter.MessageForDetailsPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MessageDetailsActivity_MembersInjector implements MembersInjector<MessageDetailsActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<MessageForDetailsPresenter> messageForDetailsPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public MessageDetailsActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MessageForDetailsPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.messageForDetailsPresenterProvider = provider3;
    }

    public static MembersInjector<MessageDetailsActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MessageForDetailsPresenter> provider3) {
        return new MessageDetailsActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MessageDetailsActivity messageDetailsActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(messageDetailsActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(messageDetailsActivity, this.frameworkFragmentInjectorProvider.get());
        injectMessageForDetailsPresenter(messageDetailsActivity, this.messageForDetailsPresenterProvider.get());
    }

    public static void injectMessageForDetailsPresenter(MessageDetailsActivity messageDetailsActivity, MessageForDetailsPresenter messageForDetailsPresenter) {
        messageDetailsActivity.messageForDetailsPresenter = messageForDetailsPresenter;
    }
}