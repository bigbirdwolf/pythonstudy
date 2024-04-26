package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class JoinUseCase_Factory implements Factory<JoinUseCase> {
    private final Provider<Repository> repositoryProvider;

    public JoinUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public JoinUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static JoinUseCase provideInstance(Provider<Repository> provider) {
        return new JoinUseCase(provider.get());
    }

    public static JoinUseCase_Factory create(Provider<Repository> provider) {
        return new JoinUseCase_Factory(provider);
    }

    public static JoinUseCase newJoinUseCase(Repository repository) {
        return new JoinUseCase(repository);
    }
}