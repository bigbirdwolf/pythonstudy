package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.GiftCardCardUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GiftCardCardPresenter_Factory implements Factory<GiftCardCardPresenter> {
    private final Provider<GiftCardCardUseCase> mGiftCardCardUseCaseProvider;

    public GiftCardCardPresenter_Factory(Provider<GiftCardCardUseCase> provider) {
        this.mGiftCardCardUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public GiftCardCardPresenter get() {
        return provideInstance(this.mGiftCardCardUseCaseProvider);
    }

    public static GiftCardCardPresenter provideInstance(Provider<GiftCardCardUseCase> provider) {
        return new GiftCardCardPresenter(provider.get());
    }

    public static GiftCardCardPresenter_Factory create(Provider<GiftCardCardUseCase> provider) {
        return new GiftCardCardPresenter_Factory(provider);
    }

    public static GiftCardCardPresenter newGiftCardCardPresenter(GiftCardCardUseCase giftCardCardUseCase) {
        return new GiftCardCardPresenter(giftCardCardUseCase);
    }
}