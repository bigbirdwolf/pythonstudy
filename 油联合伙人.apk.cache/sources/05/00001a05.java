package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.AccountBalanceUseCase;
import com.yltx.oil.partner.modules.profit.domain.IsAuthUseCase;
import com.yltx.oil.partner.modules.profit.domain.ManagerYZJYFXUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AccountBalancePresenter_Factory implements Factory<AccountBalancePresenter> {
    private final Provider<AccountBalanceUseCase> accountBalanceUseCaseProvider;
    private final Provider<IsAuthUseCase> isAuthUseCaseProvider;
    private final Provider<ManagerYZJYFXUseCase> managerYZJYFXUseCaseProvider;

    public AccountBalancePresenter_Factory(Provider<AccountBalanceUseCase> provider, Provider<IsAuthUseCase> provider2, Provider<ManagerYZJYFXUseCase> provider3) {
        this.accountBalanceUseCaseProvider = provider;
        this.isAuthUseCaseProvider = provider2;
        this.managerYZJYFXUseCaseProvider = provider3;
    }

    @Override // javax.inject.Provider
    public AccountBalancePresenter get() {
        return provideInstance(this.accountBalanceUseCaseProvider, this.isAuthUseCaseProvider, this.managerYZJYFXUseCaseProvider);
    }

    public static AccountBalancePresenter provideInstance(Provider<AccountBalanceUseCase> provider, Provider<IsAuthUseCase> provider2, Provider<ManagerYZJYFXUseCase> provider3) {
        return new AccountBalancePresenter(provider.get(), provider2.get(), provider3.get());
    }

    public static AccountBalancePresenter_Factory create(Provider<AccountBalanceUseCase> provider, Provider<IsAuthUseCase> provider2, Provider<ManagerYZJYFXUseCase> provider3) {
        return new AccountBalancePresenter_Factory(provider, provider2, provider3);
    }

    public static AccountBalancePresenter newAccountBalancePresenter(AccountBalanceUseCase accountBalanceUseCase, IsAuthUseCase isAuthUseCase, ManagerYZJYFXUseCase managerYZJYFXUseCase) {
        return new AccountBalancePresenter(accountBalanceUseCase, isAuthUseCase, managerYZJYFXUseCase);
    }
}