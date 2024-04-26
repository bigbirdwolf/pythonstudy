package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class StoredValueCardDetailUseCase_Factory implements Factory<StoredValueCardDetailUseCase> {
    private final Provider<Repository> repositoryProvider;

    public StoredValueCardDetailUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public StoredValueCardDetailUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static StoredValueCardDetailUseCase provideInstance(Provider<Repository> provider) {
        return new StoredValueCardDetailUseCase(provider.get());
    }

    public static StoredValueCardDetailUseCase_Factory create(Provider<Repository> provider) {
        return new StoredValueCardDetailUseCase_Factory(provider);
    }

    public static StoredValueCardDetailUseCase newStoredValueCardDetailUseCase(Repository repository) {
        return new StoredValueCardDetailUseCase(repository);
    }
}