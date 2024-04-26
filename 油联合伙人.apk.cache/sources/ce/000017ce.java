package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.ShopRecommendUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ShopRecommendPresenter_Factory implements Factory<ShopRecommendPresenter> {
    private final Provider<ShopRecommendUseCase> mStoredValueCardUseCaseProvider;

    public ShopRecommendPresenter_Factory(Provider<ShopRecommendUseCase> provider) {
        this.mStoredValueCardUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public ShopRecommendPresenter get() {
        return provideInstance(this.mStoredValueCardUseCaseProvider);
    }

    public static ShopRecommendPresenter provideInstance(Provider<ShopRecommendUseCase> provider) {
        return new ShopRecommendPresenter(provider.get());
    }

    public static ShopRecommendPresenter_Factory create(Provider<ShopRecommendUseCase> provider) {
        return new ShopRecommendPresenter_Factory(provider);
    }

    public static ShopRecommendPresenter newShopRecommendPresenter(ShopRecommendUseCase shopRecommendUseCase) {
        return new ShopRecommendPresenter(shopRecommendUseCase);
    }
}