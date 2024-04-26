package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class IsAuthUseCase_Factory implements Factory<IsAuthUseCase> {
    private final Provider<Repository> repositoryProvider;

    public IsAuthUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public IsAuthUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static IsAuthUseCase provideInstance(Provider<Repository> provider) {
        return new IsAuthUseCase(provider.get());
    }

    public static IsAuthUseCase_Factory create(Provider<Repository> provider) {
        return new IsAuthUseCase_Factory(provider);
    }

    public static IsAuthUseCase newIsAuthUseCase(Repository repository) {
        return new IsAuthUseCase(repository);
    }
}