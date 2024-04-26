package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FinanceCardDetailUseCase_Factory implements Factory<FinanceCardDetailUseCase> {
    private final Provider<Repository> repositoryProvider;

    public FinanceCardDetailUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public FinanceCardDetailUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static FinanceCardDetailUseCase provideInstance(Provider<Repository> provider) {
        return new FinanceCardDetailUseCase(provider.get());
    }

    public static FinanceCardDetailUseCase_Factory create(Provider<Repository> provider) {
        return new FinanceCardDetailUseCase_Factory(provider);
    }

    public static FinanceCardDetailUseCase newFinanceCardDetailUseCase(Repository repository) {
        return new FinanceCardDetailUseCase(repository);
    }
}