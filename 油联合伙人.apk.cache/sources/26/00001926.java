package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FuelCardDetailsUseCase_Factory implements Factory<FuelCardDetailsUseCase> {
    private final Provider<Repository> repositoryProvider;

    public FuelCardDetailsUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public FuelCardDetailsUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static FuelCardDetailsUseCase provideInstance(Provider<Repository> provider) {
        return new FuelCardDetailsUseCase(provider.get());
    }

    public static FuelCardDetailsUseCase_Factory create(Provider<Repository> provider) {
        return new FuelCardDetailsUseCase_Factory(provider);
    }

    public static FuelCardDetailsUseCase newFuelCardDetailsUseCase(Repository repository) {
        return new FuelCardDetailsUseCase(repository);
    }
}