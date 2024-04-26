package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class HomeButtonconfigurationUseCase_Factory implements Factory<HomeButtonconfigurationUseCase> {
    private final Provider<Repository> repositoryProvider;

    public HomeButtonconfigurationUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public HomeButtonconfigurationUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static HomeButtonconfigurationUseCase provideInstance(Provider<Repository> provider) {
        return new HomeButtonconfigurationUseCase(provider.get());
    }

    public static HomeButtonconfigurationUseCase_Factory create(Provider<Repository> provider) {
        return new HomeButtonconfigurationUseCase_Factory(provider);
    }

    public static HomeButtonconfigurationUseCase newHomeButtonconfigurationUseCase(Repository repository) {
        return new HomeButtonconfigurationUseCase(repository);
    }
}