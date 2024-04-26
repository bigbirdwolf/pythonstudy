package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class StoredValueCardUseCase_Factory implements Factory<StoredValueCardUseCase> {
    private final Provider<Repository> repositoryProvider;

    public StoredValueCardUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public StoredValueCardUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static StoredValueCardUseCase provideInstance(Provider<Repository> provider) {
        StoredValueCardUseCase storedValueCardUseCase = new StoredValueCardUseCase(provider.get());
        StoredValueCardUseCase_MembersInjector.injectBuildObservable(storedValueCardUseCase);
        return storedValueCardUseCase;
    }

    public static StoredValueCardUseCase_Factory create(Provider<Repository> provider) {
        return new StoredValueCardUseCase_Factory(provider);
    }

    public static StoredValueCardUseCase newStoredValueCardUseCase(Repository repository) {
        return new StoredValueCardUseCase(repository);
    }
}