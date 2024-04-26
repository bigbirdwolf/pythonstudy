package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FindpwUseCase_Factory implements Factory<FindpwUseCase> {
    private final Provider<Repository> repositoryProvider;

    public FindpwUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public FindpwUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static FindpwUseCase provideInstance(Provider<Repository> provider) {
        return new FindpwUseCase(provider.get());
    }

    public static FindpwUseCase_Factory create(Provider<Repository> provider) {
        return new FindpwUseCase_Factory(provider);
    }

    public static FindpwUseCase newFindpwUseCase(Repository repository) {
        return new FindpwUseCase(repository);
    }
}