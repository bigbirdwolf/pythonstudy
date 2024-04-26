package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GetvalidCodeUseCase_Factory implements Factory<GetvalidCodeUseCase> {
    private final Provider<Repository> repositoryProvider;

    public GetvalidCodeUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public GetvalidCodeUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static GetvalidCodeUseCase provideInstance(Provider<Repository> provider) {
        return new GetvalidCodeUseCase(provider.get());
    }

    public static GetvalidCodeUseCase_Factory create(Provider<Repository> provider) {
        return new GetvalidCodeUseCase_Factory(provider);
    }

    public static GetvalidCodeUseCase newGetvalidCodeUseCase(Repository repository) {
        return new GetvalidCodeUseCase(repository);
    }
}