package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GiftCardDetailUseCase_Factory implements Factory<GiftCardDetailUseCase> {
    private final Provider<Repository> repositoryProvider;

    public GiftCardDetailUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public GiftCardDetailUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static GiftCardDetailUseCase provideInstance(Provider<Repository> provider) {
        return new GiftCardDetailUseCase(provider.get());
    }

    public static GiftCardDetailUseCase_Factory create(Provider<Repository> provider) {
        return new GiftCardDetailUseCase_Factory(provider);
    }

    public static GiftCardDetailUseCase newGiftCardDetailUseCase(Repository repository) {
        return new GiftCardDetailUseCase(repository);
    }
}