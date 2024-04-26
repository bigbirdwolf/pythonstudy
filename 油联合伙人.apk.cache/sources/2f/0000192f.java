package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SFDetailUseCase_Factory implements Factory<SFDetailUseCase> {
    private final Provider<Repository> repositoryProvider;

    public SFDetailUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public SFDetailUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static SFDetailUseCase provideInstance(Provider<Repository> provider) {
        return new SFDetailUseCase(provider.get());
    }

    public static SFDetailUseCase_Factory create(Provider<Repository> provider) {
        return new SFDetailUseCase_Factory(provider);
    }

    public static SFDetailUseCase newSFDetailUseCase(Repository repository) {
        return new SFDetailUseCase(repository);
    }
}