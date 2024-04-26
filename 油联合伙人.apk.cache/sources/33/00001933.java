package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ShopUseCase_Factory implements Factory<ShopUseCase> {
    private final Provider<Repository> repositoryProvider;

    public ShopUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public ShopUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static ShopUseCase provideInstance(Provider<Repository> provider) {
        ShopUseCase shopUseCase = new ShopUseCase(provider.get());
        ShopUseCase_MembersInjector.injectBuildObservable(shopUseCase);
        return shopUseCase;
    }

    public static ShopUseCase_Factory create(Provider<Repository> provider) {
        return new ShopUseCase_Factory(provider);
    }

    public static ShopUseCase newShopUseCase(Repository repository) {
        return new ShopUseCase(repository);
    }
}