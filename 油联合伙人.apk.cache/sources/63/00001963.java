package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.FuelCardListUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FuelCardListPresenter_Factory implements Factory<FuelCardListPresenter> {
    private final Provider<FuelCardListUseCase> fuelCardListUseCaseProvider;

    public FuelCardListPresenter_Factory(Provider<FuelCardListUseCase> provider) {
        this.fuelCardListUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public FuelCardListPresenter get() {
        return provideInstance(this.fuelCardListUseCaseProvider);
    }

    public static FuelCardListPresenter provideInstance(Provider<FuelCardListUseCase> provider) {
        return new FuelCardListPresenter(provider.get());
    }

    public static FuelCardListPresenter_Factory create(Provider<FuelCardListUseCase> provider) {
        return new FuelCardListPresenter_Factory(provider);
    }

    public static FuelCardListPresenter newFuelCardListPresenter(FuelCardListUseCase fuelCardListUseCase) {
        return new FuelCardListPresenter(fuelCardListUseCase);
    }
}