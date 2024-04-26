package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AccountBalanceUseCase_Factory implements Factory<AccountBalanceUseCase> {
    private final Provider<Repository> repositoryProvider;

    public AccountBalanceUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public AccountBalanceUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static AccountBalanceUseCase provideInstance(Provider<Repository> provider) {
        return new AccountBalanceUseCase(provider.get());
    }

    public static AccountBalanceUseCase_Factory create(Provider<Repository> provider) {
        return new AccountBalanceUseCase_Factory(provider);
    }

    public static AccountBalanceUseCase newAccountBalanceUseCase(Repository repository) {
        return new AccountBalanceUseCase(repository);
    }
}