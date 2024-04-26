package com.yltx.oil.partner.modules.profit.activity;

import android.app.Fragment;
import com.yltx.oil.partner.modules.login.presenter.GetValidCodePresenter;
import com.yltx.oil.partner.modules.profit.presenter.BindingBankPresenter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class BindingbankcardsActivity_MembersInjector implements MembersInjector<BindingbankcardsActivity> {
    private final Provider<BindingBankPresenter> bindingBankPresenterProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<GetValidCodePresenter> getValidCodePresenterProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public BindingbankcardsActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<GetValidCodePresenter> provider3, Provider<BindingBankPresenter> provider4) {
        this.supportFragmentInjectorProvider = provider;
        this.frameworkFragmentInjectorProvider = provider2;
        this.getValidCodePresenterProvider = provider3;
        this.bindingBankPresenterProvider = provider4;
    }

    public static MembersInjector<BindingbankcardsActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> provider, Provider<DispatchingAndroidInjector<Fragment>> provider2, Provider<GetValidCodePresenter> provider3, Provider<BindingBankPresenter> provider4) {
        return new BindingbankcardsActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BindingbankcardsActivity bindingbankcardsActivity) {
        DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(bindingbankcardsActivity, this.supportFragmentInjectorProvider.get());
        DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(bindingbankcardsActivity, this.frameworkFragmentInjectorProvider.get());
        injectGetValidCodePresenter(bindingbankcardsActivity, this.getValidCodePresenterProvider.get());
        injectBindingBankPresenter(bindingbankcardsActivity, this.bindingBankPresenterProvider.get());
    }

    public static void injectGetValidCodePresenter(BindingbankcardsActivity bindingbankcardsActivity, GetValidCodePresenter getValidCodePresenter) {
        bindingbankcardsActivity.getValidCodePresenter = getValidCodePresenter;
    }

    public static void injectBindingBankPresenter(BindingbankcardsActivity bindingbankcardsActivity, BindingBankPresenter bindingBankPresenter) {
        bindingbankcardsActivity.bindingBankPresenter = bindingBankPresenter;
    }
}