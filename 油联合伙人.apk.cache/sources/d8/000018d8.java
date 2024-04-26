package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class TotalUseCase_Factory implements Factory<TotalUseCase> {
    private final Provider<Repository> repositoryProvider;

    public TotalUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public TotalUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static TotalUseCase provideInstance(Provider<Repository> provider) {
        return new TotalUseCase(provider.get());
    }

    public static TotalUseCase_Factory create(Provider<Repository> provider) {
        return new TotalUseCase_Factory(provider);
    }

    public static TotalUseCase newTotalUseCase(Repository repository) {
        return new TotalUseCase(repository);
    }
}