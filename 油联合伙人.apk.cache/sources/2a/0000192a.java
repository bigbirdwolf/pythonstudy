package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GiftCardCardUseCase_Factory implements Factory<GiftCardCardUseCase> {
    private final Provider<Repository> repositoryProvider;

    public GiftCardCardUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public GiftCardCardUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static GiftCardCardUseCase provideInstance(Provider<Repository> provider) {
        GiftCardCardUseCase giftCardCardUseCase = new GiftCardCardUseCase(provider.get());
        GiftCardCardUseCase_MembersInjector.injectBuildObservable(giftCardCardUseCase);
        return giftCardCardUseCase;
    }

    public static GiftCardCardUseCase_Factory create(Provider<Repository> provider) {
        return new GiftCardCardUseCase_Factory(provider);
    }

    public static GiftCardCardUseCase newGiftCardCardUseCase(Repository repository) {
        return new GiftCardCardUseCase(repository);
    }
}