package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.SSLSUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SSLSPresenter_Factory implements Factory<SSLSPresenter> {
    private final Provider<SSLSUseCase> sslsUseCaseProvider;

    public SSLSPresenter_Factory(Provider<SSLSUseCase> provider) {
        this.sslsUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public SSLSPresenter get() {
        return provideInstance(this.sslsUseCaseProvider);
    }

    public static SSLSPresenter provideInstance(Provider<SSLSUseCase> provider) {
        return new SSLSPresenter(provider.get());
    }

    public static SSLSPresenter_Factory create(Provider<SSLSUseCase> provider) {
        return new SSLSPresenter_Factory(provider);
    }

    public static SSLSPresenter newSSLSPresenter(SSLSUseCase sSLSUseCase) {
        return new SSLSPresenter(sSLSUseCase);
    }
}