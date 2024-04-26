package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AutoUseCase_Factory implements Factory<AutoUseCase> {
    private final Provider<Repository> repositoryProvider;

    public AutoUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public AutoUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static AutoUseCase provideInstance(Provider<Repository> provider) {
        return new AutoUseCase(provider.get());
    }

    public static AutoUseCase_Factory create(Provider<Repository> provider) {
        return new AutoUseCase_Factory(provider);
    }

    public static AutoUseCase newAutoUseCase(Repository repository) {
        return new AutoUseCase(repository);
    }
}