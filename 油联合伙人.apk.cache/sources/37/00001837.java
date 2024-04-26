package com.yltx.oil.partner.modules.main;

import android.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public MainActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
    }

    public static MembersInjector<MainActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        return new MainActivity_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MainActivity mainActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(mainActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(mainActivity, this.frameworkFragmentInjectorProvider.get());
    }
}