package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GeneralizeUseCase_Factory implements Factory<GeneralizeUseCase> {
    private final Provider<Repository> repositoryProvider;

    public GeneralizeUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public GeneralizeUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static GeneralizeUseCase provideInstance(Provider<Repository> provider) {
        return new GeneralizeUseCase(provider.get());
    }

    public static GeneralizeUseCase_Factory create(Provider<Repository> provider) {
        return new GeneralizeUseCase_Factory(provider);
    }

    public static GeneralizeUseCase newGeneralizeUseCase(Repository repository) {
        return new GeneralizeUseCase(repository);
    }
}