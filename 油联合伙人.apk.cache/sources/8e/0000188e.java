package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.MyfeedbackPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FeedbackActivity_MembersInjector implements MembersInjector<FeedbackActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<MyfeedbackPresenter> myfeedbackPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public FeedbackActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MyfeedbackPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.myfeedbackPresenterProvider = provider3;
    }

    public static MembersInjector<FeedbackActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<MyfeedbackPresenter> provider3) {
        return new FeedbackActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FeedbackActivity feedbackActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(feedbackActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(feedbackActivity, this.frameworkFragmentInjectorProvider.get());
        injectMyfeedbackPresenter(feedbackActivity, this.myfeedbackPresenterProvider.get());
    }

    public static void injectMyfeedbackPresenter(FeedbackActivity feedbackActivity, MyfeedbackPresenter myfeedbackPresenter) {
        feedbackActivity.myfeedbackPresenter = myfeedbackPresenter;
    }
}