package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class CommissionUseCase_Factory implements Factory<CommissionUseCase> {
    private final Provider<Repository> repositoryProvider;

    public CommissionUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public CommissionUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static CommissionUseCase provideInstance(Provider<Repository> provider) {
        return new CommissionUseCase(provider.get());
    }

    public static CommissionUseCase_Factory create(Provider<Repository> provider) {
        return new CommissionUseCase_Factory(provider);
    }

    public static CommissionUseCase newCommissionUseCase(Repository repository) {
        return new CommissionUseCase(repository);
    }
}