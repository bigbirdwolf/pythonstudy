package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class LoginUseCase_Factory implements Factory<LoginUseCase> {
    private final Provider<Repository> repositoryProvider;

    public LoginUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public LoginUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static LoginUseCase provideInstance(Provider<Repository> provider) {
        return new LoginUseCase(provider.get());
    }

    public static LoginUseCase_Factory create(Provider<Repository> provider) {
        return new LoginUseCase_Factory(provider);
    }

    public static LoginUseCase newLoginUseCase(Repository repository) {
        return new LoginUseCase(repository);
    }
}