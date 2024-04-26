package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class TxUseCase_Factory implements Factory<TxUseCase> {
    private final Provider<Repository> repositoryProvider;

    public TxUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public TxUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static TxUseCase provideInstance(Provider<Repository> provider) {
        return new TxUseCase(provider.get());
    }

    public static TxUseCase_Factory create(Provider<Repository> provider) {
        return new TxUseCase_Factory(provider);
    }

    public static TxUseCase newTxUseCase(Repository repository) {
        return new TxUseCase(repository);
    }
}