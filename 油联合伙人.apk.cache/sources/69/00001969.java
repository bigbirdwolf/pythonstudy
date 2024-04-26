package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.ShopUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ShopPresenter_Factory implements Factory<ShopPresenter> {
    private final Provider<ShopUseCase> mStoredValueCardUseCaseProvider;

    public ShopPresenter_Factory(Provider<ShopUseCase> provider) {
        this.mStoredValueCardUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public ShopPresenter get() {
        return provideInstance(this.mStoredValueCardUseCaseProvider);
    }

    public static ShopPresenter provideInstance(Provider<ShopUseCase> provider) {
        return new ShopPresenter(provider.get());
    }

    public static ShopPresenter_Factory create(Provider<ShopUseCase> provider) {
        return new ShopPresenter_Factory(provider);
    }

    public static ShopPresenter newShopPresenter(ShopUseCase shopUseCase) {
        return new ShopPresenter(shopUseCase);
    }
}