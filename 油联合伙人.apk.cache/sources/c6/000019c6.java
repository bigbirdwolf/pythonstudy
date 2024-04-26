package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SettlementRecordsUseCase_Factory implements Factory<SettlementRecordsUseCase> {
    private final Provider<Repository> repositoryProvider;

    public SettlementRecordsUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public SettlementRecordsUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static SettlementRecordsUseCase provideInstance(Provider<Repository> provider) {
        return new SettlementRecordsUseCase(provider.get());
    }

    public static SettlementRecordsUseCase_Factory create(Provider<Repository> provider) {
        return new SettlementRecordsUseCase_Factory(provider);
    }

    public static SettlementRecordsUseCase newSettlementRecordsUseCase(Repository repository) {
        return new SettlementRecordsUseCase(repository);
    }
}