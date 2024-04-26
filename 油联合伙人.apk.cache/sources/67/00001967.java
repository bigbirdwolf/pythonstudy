package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.ShopUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ShopDetailsPresenter_Factory implements Factory<ShopDetailsPresenter> {
    private final Provider<ShopUseCase> fuelCardListUseCaseProvider;

    public ShopDetailsPresenter_Factory(Provider<ShopUseCase> provider) {
        this.fuelCardListUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public ShopDetailsPresenter get() {
        return provideInstance(this.fuelCardListUseCaseProvider);
    }

    public static ShopDetailsPresenter provideInstance(Provider<ShopUseCase> provider) {
        return new ShopDetailsPresenter(provider.get());
    }

    public static ShopDetailsPresenter_Factory create(Provider<ShopUseCase> provider) {
        return new ShopDetailsPresenter_Factory(provider);
    }

    public static ShopDetailsPresenter newShopDetailsPresenter(ShopUseCase shopUseCase) {
        return new ShopDetailsPresenter(shopUseCase);
    }
}