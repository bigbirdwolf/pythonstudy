package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ComplaintUseCase_Factory implements Factory<ComplaintUseCase> {
    private final Provider<Repository> repositoryProvider;

    public ComplaintUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public ComplaintUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static ComplaintUseCase provideInstance(Provider<Repository> provider) {
        return new ComplaintUseCase(provider.get());
    }

    public static ComplaintUseCase_Factory create(Provider<Repository> provider) {
        return new ComplaintUseCase_Factory(provider);
    }

    public static ComplaintUseCase newComplaintUseCase(Repository repository) {
        return new ComplaintUseCase(repository);
    }
}