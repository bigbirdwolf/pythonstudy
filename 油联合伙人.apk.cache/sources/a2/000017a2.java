package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ShopRecommendUseCase_Factory implements Factory<ShopRecommendUseCase> {
    private final Provider<Repository> repositoryProvider;

    public ShopRecommendUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public ShopRecommendUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static ShopRecommendUseCase provideInstance(Provider<Repository> provider) {
        ShopRecommendUseCase shopRecommendUseCase = new ShopRecommendUseCase(provider.get());
        ShopRecommendUseCase_MembersInjector.injectBuildObservable(shopRecommendUseCase);
        return shopRecommendUseCase;
    }

    public static ShopRecommendUseCase_Factory create(Provider<Repository> provider) {
        return new ShopRecommendUseCase_Factory(provider);
    }

    public static ShopRecommendUseCase newShopRecommendUseCase(Repository repository) {
        return new ShopRecommendUseCase(repository);
    }
}