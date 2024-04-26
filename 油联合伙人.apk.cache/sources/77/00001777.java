package com.yltx.oil.partner.modules.home.activity;

import android.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MessageBulletinActivity_MembersInjector implements MembersInjector<MessageBulletinActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public MessageBulletinActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
    }

    public static MembersInjector<MessageBulletinActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        return new MessageBulletinActivity_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MessageBulletinActivity messageBulletinActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(messageBulletinActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(messageBulletinActivity, this.frameworkFragmentInjectorProvider.get());
    }
}