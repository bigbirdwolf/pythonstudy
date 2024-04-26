package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RegisterUseCase_Factory implements Factory<RegisterUseCase> {
    private final Provider<Repository> repositoryProvider;

    public RegisterUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public RegisterUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static RegisterUseCase provideInstance(Provider<Repository> provider) {
        return new RegisterUseCase(provider.get());
    }

    public static RegisterUseCase_Factory create(Provider<Repository> provider) {
        return new RegisterUseCase_Factory(provider);
    }

    public static RegisterUseCase newRegisterUseCase(Repository repository) {
        return new RegisterUseCase(repository);
    }
}