package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DetailUseCase_Factory implements Factory<DetailUseCase> {
    private final Provider<Repository> repositoryProvider;

    public DetailUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public DetailUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static DetailUseCase provideInstance(Provider<Repository> provider) {
        return new DetailUseCase(provider.get());
    }

    public static DetailUseCase_Factory create(Provider<Repository> provider) {
        return new DetailUseCase_Factory(provider);
    }

    public static DetailUseCase newDetailUseCase(Repository repository) {
        return new DetailUseCase(repository);
    }
}