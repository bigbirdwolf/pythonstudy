package com.yltx.oil.partner.modules.main.domian;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class CheckVersionUseCase_Factory implements Factory<CheckVersionUseCase> {
    private final Provider<Repository> repositoryProvider;

    public CheckVersionUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public CheckVersionUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static CheckVersionUseCase provideInstance(Provider<Repository> provider) {
        return new CheckVersionUseCase(provider.get());
    }

    public static CheckVersionUseCase_Factory create(Provider<Repository> provider) {
        return new CheckVersionUseCase_Factory(provider);
    }

    public static CheckVersionUseCase newCheckVersionUseCase(Repository repository) {
        return new CheckVersionUseCase(repository);
    }
}