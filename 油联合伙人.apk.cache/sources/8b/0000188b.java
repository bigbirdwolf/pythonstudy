package com.yltx.oil.partner.modules.mine.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.mine.presenter.ComplaintOrderPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ComplaintActivity_MembersInjector implements MembersInjector<ComplaintActivity> {
    private final Provider<ComplaintOrderPresenter> complaintPresenterProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public ComplaintActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ComplaintOrderPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.complaintPresenterProvider = provider3;
    }

    public static MembersInjector<ComplaintActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ComplaintOrderPresenter> provider3) {
        return new ComplaintActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ComplaintActivity complaintActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(complaintActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(complaintActivity, this.frameworkFragmentInjectorProvider.get());
        injectComplaintPresenter(complaintActivity, this.complaintPresenterProvider.get());
    }

    public static void injectComplaintPresenter(ComplaintActivity complaintActivity, ComplaintOrderPresenter complaintOrderPresenter) {
        complaintActivity.complaintPresenter = complaintOrderPresenter;
    }
}