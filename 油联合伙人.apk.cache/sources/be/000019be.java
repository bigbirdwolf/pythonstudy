package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GetTxHistoryListUseCase_Factory implements Factory<GetTxHistoryListUseCase> {
    private final Provider<Repository> repositoryProvider;

    public GetTxHistoryListUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public GetTxHistoryListUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static GetTxHistoryListUseCase provideInstance(Provider<Repository> provider) {
        return new GetTxHistoryListUseCase(provider.get());
    }

    public static GetTxHistoryListUseCase_Factory create(Provider<Repository> provider) {
        return new GetTxHistoryListUseCase_Factory(provider);
    }

    public static GetTxHistoryListUseCase newGetTxHistoryListUseCase(Repository repository) {
        return new GetTxHistoryListUseCase(repository);
    }
}