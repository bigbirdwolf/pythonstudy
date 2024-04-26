package com.yltx.oil.partner.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import com.yltx.oil.partner.injections.instrumentation.ApplicationInstrumentation;
import com.yltx.oil.partner.navigation.Navigator;
import dagger.MembersInjector;
import dagger.android.DaggerApplication_MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class PartnerApplication_MembersInjector implements MembersInjector<PartnerApplication> {
    private final Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider;
    private final Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider;
    private final Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider;
    private final Provider<ApplicationInstrumentation> mInstrumentationProvider;
    private final Provider<Navigator> mNavigatorProvider;
    private final Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public PartnerApplication_MembersInjector(Provider<DispatchingAndroidInjector<Activity>> provider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> provider2, Provider<DispatchingAndroidInjector<Fragment>> provider3, Provider<DispatchingAndroidInjector<Service>> provider4, Provider<DispatchingAndroidInjector<ContentProvider>> provider5, Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider6, Provider<ApplicationInstrumentation> provider7, Provider<Navigator> provider8) {
        this.activityInjectorProvider = provider;
        this.broadcastReceiverInjectorProvider = provider2;
        this.fragmentInjectorProvider = provider3;
        this.serviceInjectorProvider = provider4;
        this.contentProviderInjectorProvider = provider5;
        this.supportFragmentInjectorProvider = provider6;
        this.mInstrumentationProvider = provider7;
        this.mNavigatorProvider = provider8;
    }

    public static MembersInjector<PartnerApplication> create(Provider<DispatchingAndroidInjector<Activity>> provider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> provider2, Provider<DispatchingAndroidInjector<Fragment>> provider3, Provider<DispatchingAndroidInjector<Service>> provider4, Provider<DispatchingAndroidInjector<ContentProvider>> provider5, Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider6, Provider<ApplicationInstrumentation> provider7, Provider<Navigator> provider8) {
        return new PartnerApplication_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PartnerApplication partnerApplication) {
        DaggerApplication_MembersInjector.injectActivityInjector(partnerApplication, this.activityInjectorProvider.get());
        DaggerApplication_MembersInjector.injectBroadcastReceiverInjector(partnerApplication, this.broadcastReceiverInjectorProvider.get());
        DaggerApplication_MembersInjector.injectFragmentInjector(partnerApplication, this.fragmentInjectorProvider.get());
        DaggerApplication_MembersInjector.injectServiceInjector(partnerApplication, this.serviceInjectorProvider.get());
        DaggerApplication_MembersInjector.injectContentProviderInjector(partnerApplication, this.contentProviderInjectorProvider.get());
        DaggerApplication_MembersInjector.injectSetInjected(partnerApplication);
        dagger.android.support.DaggerApplication_MembersInjector.injectSupportFragmentInjector(partnerApplication, this.supportFragmentInjectorProvider.get());
        injectMInstrumentation(partnerApplication, this.mInstrumentationProvider.get());
        injectMNavigator(partnerApplication, this.mNavigatorProvider.get());
    }

    public static void injectMInstrumentation(PartnerApplication partnerApplication, ApplicationInstrumentation applicationInstrumentation) {
        partnerApplication.mInstrumentation = applicationInstrumentation;
    }

    public static void injectMNavigator(PartnerApplication partnerApplication, Navigator navigator) {
        partnerApplication.mNavigator = navigator;
    }
}