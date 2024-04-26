package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class UpBankcardsUseCase_Factory implements Factory<UpBankcardsUseCase> {
    private final Provider<Repository> repositoryProvider;

    public UpBankcardsUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public UpBankcardsUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static UpBankcardsUseCase provideInstance(Provider<Repository> provider) {
        return new UpBankcardsUseCase(provider.get());
    }

    public static UpBankcardsUseCase_Factory create(Provider<Repository> provider) {
        return new UpBankcardsUseCase_Factory(provider);
    }

    public static UpBankcardsUseCase newUpBankcardsUseCase(Repository repository) {
        return new UpBankcardsUseCase(repository);
    }
}