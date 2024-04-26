package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FuelCardListUseCase_Factory implements Factory<FuelCardListUseCase> {
    private final Provider<Repository> repositoryProvider;

    public FuelCardListUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public FuelCardListUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static FuelCardListUseCase provideInstance(Provider<Repository> provider) {
        return new FuelCardListUseCase(provider.get());
    }

    public static FuelCardListUseCase_Factory create(Provider<Repository> provider) {
        return new FuelCardListUseCase_Factory(provider);
    }

    public static FuelCardListUseCase newFuelCardListUseCase(Repository repository) {
        return new FuelCardListUseCase(repository);
    }
}