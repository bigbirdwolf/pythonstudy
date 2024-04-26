package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.BindingbankcardsUseCase;
import com.yltx.oil.partner.modules.profit.domain.UpBankcardsUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class BindingBankPresenter_Factory implements Factory<BindingBankPresenter> {
    private final Provider<BindingbankcardsUseCase> bindingbankcardsUseCaseProvider;
    private final Provider<UpBankcardsUseCase> mUpBankcardsUseCaseProvider;

    public BindingBankPresenter_Factory(Provider<BindingbankcardsUseCase> provider, Provider<UpBankcardsUseCase> provider2) {
        this.bindingbankcardsUseCaseProvider = provider;
        this.mUpBankcardsUseCaseProvider = provider2;
    }

    @Override // javax.inject.Provider
    public BindingBankPresenter get() {
        return provideInstance(this.bindingbankcardsUseCaseProvider, this.mUpBankcardsUseCaseProvider);
    }

    public static BindingBankPresenter provideInstance(Provider<BindingbankcardsUseCase> provider, Provider<UpBankcardsUseCase> provider2) {
        return new BindingBankPresenter(provider.get(), provider2.get());
    }

    public static BindingBankPresenter_Factory create(Provider<BindingbankcardsUseCase> provider, Provider<UpBankcardsUseCase> provider2) {
        return new BindingBankPresenter_Factory(provider, provider2);
    }

    public static BindingBankPresenter newBindingBankPresenter(BindingbankcardsUseCase bindingbankcardsUseCase, UpBankcardsUseCase upBankcardsUseCase) {
        return new BindingBankPresenter(bindingbankcardsUseCase, upBankcardsUseCase);
    }
}