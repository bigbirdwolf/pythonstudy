package com.yltx.oil.partner.modules.profit.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.GeneralizePresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DataanalysisActivity_MembersInjector implements MembersInjector<DataanalysisActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<GeneralizePresenter> generalizePresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public DataanalysisActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<GeneralizePresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.generalizePresenterProvider = provider3;
    }

    public static MembersInjector<DataanalysisActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<GeneralizePresenter> provider3) {
        return new DataanalysisActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DataanalysisActivity dataanalysisActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(dataanalysisActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(dataanalysisActivity, this.frameworkFragmentInjectorProvider.get());
        injectGeneralizePresenter(dataanalysisActivity, this.generalizePresenterProvider.get());
    }

    public static void injectGeneralizePresenter(DataanalysisActivity dataanalysisActivity, GeneralizePresenter generalizePresenter) {
        dataanalysisActivity.generalizePresenter = generalizePresenter;
    }
}