package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.ComplaintPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ComplainValetActivity_MembersInjector implements MembersInjector<ComplainValetActivity> {
    private final Provider<ComplaintPresenter> complaintPresenterProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public ComplainValetActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ComplaintPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.complaintPresenterProvider = provider3;
    }

    public static MembersInjector<ComplainValetActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ComplaintPresenter> provider3) {
        return new ComplainValetActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ComplainValetActivity complainValetActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(complainValetActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(complainValetActivity, this.frameworkFragmentInjectorProvider.get());
        injectComplaintPresenter(complainValetActivity, this.complaintPresenterProvider.get());
    }

    public static void injectComplaintPresenter(ComplainValetActivity complainValetActivity, ComplaintPresenter complaintPresenter) {
        complainValetActivity.complaintPresenter = complaintPresenter;
    }
}