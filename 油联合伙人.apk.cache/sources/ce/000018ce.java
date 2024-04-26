package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MyfeedbackUseCase_Factory implements Factory<MyfeedbackUseCase> {
    private final Provider<Repository> repositoryProvider;

    public MyfeedbackUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public MyfeedbackUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static MyfeedbackUseCase provideInstance(Provider<Repository> provider) {
        return new MyfeedbackUseCase(provider.get());
    }

    public static MyfeedbackUseCase_Factory create(Provider<Repository> provider) {
        return new MyfeedbackUseCase_Factory(provider);
    }

    public static MyfeedbackUseCase newMyfeedbackUseCase(Repository repository) {
        return new MyfeedbackUseCase(repository);
    }
}