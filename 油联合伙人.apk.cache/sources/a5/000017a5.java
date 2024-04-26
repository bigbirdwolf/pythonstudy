package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ShortUseCase_Factory implements Factory<ShortUseCase> {
    private final Provider<Repository> repositoryProvider;

    public ShortUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public ShortUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static ShortUseCase provideInstance(Provider<Repository> provider) {
        return new ShortUseCase(provider.get());
    }

    public static ShortUseCase_Factory create(Provider<Repository> provider) {
        return new ShortUseCase_Factory(provider);
    }

    public static ShortUseCase newShortUseCase(Repository repository) {
        return new ShortUseCase(repository);
    }
}