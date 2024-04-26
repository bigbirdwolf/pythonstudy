package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class LoginSmUseCase_Factory implements Factory<LoginSmUseCase> {
    private final Provider<Repository> repositoryProvider;

    public LoginSmUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public LoginSmUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static LoginSmUseCase provideInstance(Provider<Repository> provider) {
        return new LoginSmUseCase(provider.get());
    }

    public static LoginSmUseCase_Factory create(Provider<Repository> provider) {
        return new LoginSmUseCase_Factory(provider);
    }

    public static LoginSmUseCase newLoginSmUseCase(Repository repository) {
        return new LoginSmUseCase(repository);
    }
}