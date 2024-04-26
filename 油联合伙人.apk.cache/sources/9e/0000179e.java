package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SSLSUseCase_Factory implements Factory<SSLSUseCase> {
    private final Provider<Repository> repositoryProvider;

    public SSLSUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public SSLSUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static SSLSUseCase provideInstance(Provider<Repository> provider) {
        return new SSLSUseCase(provider.get());
    }

    public static SSLSUseCase_Factory create(Provider<Repository> provider) {
        return new SSLSUseCase_Factory(provider);
    }

    public static SSLSUseCase newSSLSUseCase(Repository repository) {
        return new SSLSUseCase(repository);
    }
}