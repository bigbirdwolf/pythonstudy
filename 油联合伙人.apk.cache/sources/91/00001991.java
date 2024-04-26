package com.yltx.oil.partner.modules.profit.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.SettlementRecordsPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class CollectionRecordActivity_MembersInjector implements MembersInjector<CollectionRecordActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<SettlementRecordsPresenter> settlementRecordsPresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public CollectionRecordActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<SettlementRecordsPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.settlementRecordsPresenterProvider = provider3;
    }

    public static MembersInjector<CollectionRecordActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<SettlementRecordsPresenter> provider3) {
        return new CollectionRecordActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CollectionRecordActivity collectionRecordActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(collectionRecordActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(collectionRecordActivity, this.frameworkFragmentInjectorProvider.get());
        injectSettlementRecordsPresenter(collectionRecordActivity, this.settlementRecordsPresenterProvider.get());
    }

    public static void injectSettlementRecordsPresenter(CollectionRecordActivity collectionRecordActivity, SettlementRecordsPresenter settlementRecordsPresenter) {
        collectionRecordActivity.settlementRecordsPresenter = settlementRecordsPresenter;
    }
}