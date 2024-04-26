package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.FuelCardDetailsUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FuelCardDetailsPresenter_Factory implements Factory<FuelCardDetailsPresenter> {
    private final Provider<FuelCardDetailsUseCase> fuelCardListUseCaseProvider;

    public FuelCardDetailsPresenter_Factory(Provider<FuelCardDetailsUseCase> provider) {
        this.fuelCardListUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public FuelCardDetailsPresenter get() {
        return provideInstance(this.fuelCardListUseCaseProvider);
    }

    public static FuelCardDetailsPresenter provideInstance(Provider<FuelCardDetailsUseCase> provider) {
        return new FuelCardDetailsPresenter(provider.get());
    }

    public static FuelCardDetailsPresenter_Factory create(Provider<FuelCardDetailsUseCase> provider) {
        return new FuelCardDetailsPresenter_Factory(provider);
    }

    public static FuelCardDetailsPresenter newFuelCardDetailsPresenter(FuelCardDetailsUseCase fuelCardDetailsUseCase) {
        return new FuelCardDetailsPresenter(fuelCardDetailsUseCase);
    }
}