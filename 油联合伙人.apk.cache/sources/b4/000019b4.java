package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AllordersUseCase_Factory implements Factory<AllordersUseCase> {
    private final Provider<Repository> repositoryProvider;

    public AllordersUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public AllordersUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static AllordersUseCase provideInstance(Provider<Repository> provider) {
        return new AllordersUseCase(provider.get());
    }

    public static AllordersUseCase_Factory create(Provider<Repository> provider) {
        return new AllordersUseCase_Factory(provider);
    }

    public static AllordersUseCase newAllordersUseCase(Repository repository) {
        return new AllordersUseCase(repository);
    }
}