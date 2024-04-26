package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class TxApplyUseCase_Factory implements Factory<TxApplyUseCase> {
    private final Provider<Repository> repositoryProvider;

    public TxApplyUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public TxApplyUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static TxApplyUseCase provideInstance(Provider<Repository> provider) {
        return new TxApplyUseCase(provider.get());
    }

    public static TxApplyUseCase_Factory create(Provider<Repository> provider) {
        return new TxApplyUseCase_Factory(provider);
    }

    public static TxApplyUseCase newTxApplyUseCase(Repository repository) {
        return new TxApplyUseCase(repository);
    }
}