package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ModifUseCase_Factory implements Factory<ModifUseCase> {
    private final Provider<Repository> repositoryProvider;

    public ModifUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public ModifUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static ModifUseCase provideInstance(Provider<Repository> provider) {
        return new ModifUseCase(provider.get());
    }

    public static ModifUseCase_Factory create(Provider<Repository> provider) {
        return new ModifUseCase_Factory(provider);
    }

    public static ModifUseCase newModifUseCase(Repository repository) {
        return new ModifUseCase(repository);
    }
}