package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SeekUseCase_Factory implements Factory<SeekUseCase> {
    private final Provider<Repository> repositoryProvider;

    public SeekUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public SeekUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static SeekUseCase provideInstance(Provider<Repository> provider) {
        return new SeekUseCase(provider.get());
    }

    public static SeekUseCase_Factory create(Provider<Repository> provider) {
        return new SeekUseCase_Factory(provider);
    }

    public static SeekUseCase newSeekUseCase(Repository repository) {
        return new SeekUseCase(repository);
    }
}