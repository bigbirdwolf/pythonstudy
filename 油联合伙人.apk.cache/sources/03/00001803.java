package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AppLoginStatusUseCase_Factory implements Factory<AppLoginStatusUseCase> {
    private final Provider<Repository> repositoryProvider;

    public AppLoginStatusUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public AppLoginStatusUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static AppLoginStatusUseCase provideInstance(Provider<Repository> provider) {
        return new AppLoginStatusUseCase(provider.get());
    }

    public static AppLoginStatusUseCase_Factory create(Provider<Repository> provider) {
        return new AppLoginStatusUseCase_Factory(provider);
    }

    public static AppLoginStatusUseCase newAppLoginStatusUseCase(Repository repository) {
        return new AppLoginStatusUseCase(repository);
    }
}