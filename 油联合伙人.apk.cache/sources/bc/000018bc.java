package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ComplaintOrderUseCase_Factory implements Factory<ComplaintOrderUseCase> {
    private final Provider<Repository> repositoryProvider;

    public ComplaintOrderUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public ComplaintOrderUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static ComplaintOrderUseCase provideInstance(Provider<Repository> provider) {
        return new ComplaintOrderUseCase(provider.get());
    }

    public static ComplaintOrderUseCase_Factory create(Provider<Repository> provider) {
        return new ComplaintOrderUseCase_Factory(provider);
    }

    public static ComplaintOrderUseCase newComplaintOrderUseCase(Repository repository) {
        return new ComplaintOrderUseCase(repository);
    }
}