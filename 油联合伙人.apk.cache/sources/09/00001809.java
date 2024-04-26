package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GetImgCodeUseCase_Factory implements Factory<GetImgCodeUseCase> {
    private final Provider<Repository> repositoryProvider;

    public GetImgCodeUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public GetImgCodeUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static GetImgCodeUseCase provideInstance(Provider<Repository> provider) {
        return new GetImgCodeUseCase(provider.get());
    }

    public static GetImgCodeUseCase_Factory create(Provider<Repository> provider) {
        return new GetImgCodeUseCase_Factory(provider);
    }

    public static GetImgCodeUseCase newGetImgCodeUseCase(Repository repository) {
        return new GetImgCodeUseCase(repository);
    }
}