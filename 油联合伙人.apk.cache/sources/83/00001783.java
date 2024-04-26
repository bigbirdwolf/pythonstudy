package com.yltx.oil.partner.modules.home.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.home.presenter.SeekPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SearchResultActivity_MembersInjector implements MembersInjector<SearchResultActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<SeekPresenter> seekPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public SearchResultActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<SeekPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.seekPresenterProvider = provider3;
    }

    public static MembersInjector<SearchResultActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<SeekPresenter> provider3) {
        return new SearchResultActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SearchResultActivity searchResultActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(searchResultActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(searchResultActivity, this.frameworkFragmentInjectorProvider.get());
        injectSeekPresenter(searchResultActivity, this.seekPresenterProvider.get());
    }

    public static void injectSeekPresenter(SearchResultActivity searchResultActivity, SeekPresenter seekPresenter) {
        searchResultActivity.seekPresenter = seekPresenter;
    }
}