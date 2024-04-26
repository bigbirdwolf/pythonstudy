package com.yltx.oil.partner.modules.login.activity;

import android.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class YLSPLoginActivity_MembersInjector implements MembersInjector<YLSPLoginActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public YLSPLoginActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
    }

    public static MembersInjector<YLSPLoginActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        return new YLSPLoginActivity_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(YLSPLoginActivity yLSPLoginActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(yLSPLoginActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(yLSPLoginActivity, this.frameworkFragmentInjectorProvider.get());
    }
}