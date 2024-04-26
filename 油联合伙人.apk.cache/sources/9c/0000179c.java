package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SCUseCase_Factory implements Factory<SCUseCase> {
    private final Provider<Repository> repositoryProvider;

    public SCUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public SCUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static SCUseCase provideInstance(Provider<Repository> provider) {
        return new SCUseCase(provider.get());
    }

    public static SCUseCase_Factory create(Provider<Repository> provider) {
        return new SCUseCase_Factory(provider);
    }

    public static SCUseCase newSCUseCase(Repository repository) {
        return new SCUseCase(repository);
    }
}