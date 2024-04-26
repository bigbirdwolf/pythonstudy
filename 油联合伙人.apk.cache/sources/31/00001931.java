package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ShopDetailUseCase_Factory implements Factory<ShopDetailUseCase> {
    private final Provider<Repository> repositoryProvider;

    public ShopDetailUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public ShopDetailUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static ShopDetailUseCase provideInstance(Provider<Repository> provider) {
        return new ShopDetailUseCase(provider.get());
    }

    public static ShopDetailUseCase_Factory create(Provider<Repository> provider) {
        return new ShopDetailUseCase_Factory(provider);
    }

    public static ShopDetailUseCase newShopDetailUseCase(Repository repository) {
        return new ShopDetailUseCase(repository);
    }
}