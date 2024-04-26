package com.yltx.oil.partner.base;

import android.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class StateActivity_MembersInjector implements MembersInjector<StateActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public StateActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
    }

    public static MembersInjector<StateActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2) {
        return new StateActivity_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(StateActivity stateActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(stateActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(stateActivity, this.frameworkFragmentInjectorProvider.get());
    }
}