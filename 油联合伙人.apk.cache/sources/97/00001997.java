package com.yltx.oil.partner.modules.profit.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.profit.presenter.ModificationPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ModificationBankCardsActivity_MembersInjector implements MembersInjector<ModificationBankCardsActivity> {
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<ModificationPresenter> presenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public ModificationBankCardsActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ModificationPresenter> provider3) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.presenterProvider = provider3;
    }

    public static MembersInjector<ModificationBankCardsActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<ModificationPresenter> provider3) {
        return new ModificationBankCardsActivity_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ModificationBankCardsActivity modificationBankCardsActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(modificationBankCardsActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(modificationBankCardsActivity, this.frameworkFragmentInjectorProvider.get());
        injectPresenter(modificationBankCardsActivity, this.presenterProvider.get());
    }

    public static void injectPresenter(ModificationBankCardsActivity modificationBankCardsActivity, ModificationPresenter modificationPresenter) {
        modificationBankCardsActivity.presenter = modificationPresenter;
    }
}