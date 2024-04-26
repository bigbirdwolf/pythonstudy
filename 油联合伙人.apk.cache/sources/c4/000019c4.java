package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ManagerYZJYFXUseCase_Factory implements Factory<ManagerYZJYFXUseCase> {
    private final Provider<Repository> repositoryProvider;

    public ManagerYZJYFXUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public ManagerYZJYFXUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static ManagerYZJYFXUseCase provideInstance(Provider<Repository> provider) {
        return new ManagerYZJYFXUseCase(provider.get());
    }

    public static ManagerYZJYFXUseCase_Factory create(Provider<Repository> provider) {
        return new ManagerYZJYFXUseCase_Factory(provider);
    }

    public static ManagerYZJYFXUseCase newManagerYZJYFXUseCase(Repository repository) {
        return new ManagerYZJYFXUseCase(repository);
    }
}