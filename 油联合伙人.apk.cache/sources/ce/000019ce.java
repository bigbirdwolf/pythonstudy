package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class UserAccountUseCase_Factory implements Factory<UserAccountUseCase> {
    private final Provider<Repository> repositoryProvider;

    public UserAccountUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public UserAccountUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static UserAccountUseCase provideInstance(Provider<Repository> provider) {
        UserAccountUseCase userAccountUseCase = new UserAccountUseCase(provider.get());
        UserAccountUseCase_MembersInjector.injectBuildObservable(userAccountUseCase);
        return userAccountUseCase;
    }

    public static UserAccountUseCase_Factory create(Provider<Repository> provider) {
        return new UserAccountUseCase_Factory(provider);
    }

    public static UserAccountUseCase newUserAccountUseCase(Repository repository) {
        return new UserAccountUseCase(repository);
    }
}