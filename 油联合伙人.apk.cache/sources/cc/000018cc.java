package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MyfeedbackSubmintUseCase_Factory implements Factory<MyfeedbackSubmintUseCase> {
    private final Provider<Repository> repositoryProvider;

    public MyfeedbackSubmintUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public MyfeedbackSubmintUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static MyfeedbackSubmintUseCase provideInstance(Provider<Repository> provider) {
        return new MyfeedbackSubmintUseCase(provider.get());
    }

    public static MyfeedbackSubmintUseCase_Factory create(Provider<Repository> provider) {
        return new MyfeedbackSubmintUseCase_Factory(provider);
    }

    public static MyfeedbackSubmintUseCase newMyfeedbackSubmintUseCase(Repository repository) {
        return new MyfeedbackSubmintUseCase(repository);
    }
}