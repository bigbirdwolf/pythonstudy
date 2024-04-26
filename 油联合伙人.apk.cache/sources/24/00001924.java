package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FinanceCardUseCase_Factory implements Factory<FinanceCardUseCase> {
    private final Provider<Repository> repositoryProvider;

    public FinanceCardUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public FinanceCardUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static FinanceCardUseCase provideInstance(Provider<Repository> provider) {
        return new FinanceCardUseCase(provider.get());
    }

    public static FinanceCardUseCase_Factory create(Provider<Repository> provider) {
        return new FinanceCardUseCase_Factory(provider);
    }

    public static FinanceCardUseCase newFinanceCardUseCase(Repository repository) {
        return new FinanceCardUseCase(repository);
    }
}