package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class BindingbankcardsUseCase_Factory implements Factory<BindingbankcardsUseCase> {
    private final Provider<Repository> repositoryProvider;

    public BindingbankcardsUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public BindingbankcardsUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static BindingbankcardsUseCase provideInstance(Provider<Repository> provider) {
        return new BindingbankcardsUseCase(provider.get());
    }

    public static BindingbankcardsUseCase_Factory create(Provider<Repository> provider) {
        return new BindingbankcardsUseCase_Factory(provider);
    }

    public static BindingbankcardsUseCase newBindingbankcardsUseCase(Repository repository) {
        return new BindingbankcardsUseCase(repository);
    }
}