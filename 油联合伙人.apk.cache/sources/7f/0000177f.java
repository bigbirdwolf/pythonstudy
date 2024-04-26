package com.yltx.oil.partner.modules.home.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.home.presenter.SCPresenter;
import com.yltx.oil.partner.modules.home.presenter.SSLSPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SearchHomeActivity_MembersInjector implements MembersInjector<SearchHomeActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<SCPresenter> scPresenterProvider;
    private final Provider<SSLSPresenter> sslsPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public SearchHomeActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<SSLSPresenter> provider3, Provider<SCPresenter> provider4) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.sslsPresenterProvider = provider3;
        this.scPresenterProvider = provider4;
    }

    public static MembersInjector<SearchHomeActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<SSLSPresenter> provider3, Provider<SCPresenter> provider4) {
        return new SearchHomeActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SearchHomeActivity searchHomeActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(searchHomeActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(searchHomeActivity, this.frameworkFragmentInjectorProvider.get());
        injectSslsPresenter(searchHomeActivity, this.sslsPresenterProvider.get());
        injectScPresenter(searchHomeActivity, this.scPresenterProvider.get());
    }

    public static void injectSslsPresenter(SearchHomeActivity searchHomeActivity, SSLSPresenter sSLSPresenter) {
        searchHomeActivity.sslsPresenter = sSLSPresenter;
    }

    public static void injectScPresenter(SearchHomeActivity searchHomeActivity, SCPresenter sCPresenter) {
        searchHomeActivity.scPresenter = sCPresenter;
    }
}