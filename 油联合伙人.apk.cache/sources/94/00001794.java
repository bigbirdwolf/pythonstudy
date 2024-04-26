package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class BannerUseCase_Factory implements Factory<BannerUseCase> {
    private final Provider<Repository> repositoryProvider;

    public BannerUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public BannerUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static BannerUseCase provideInstance(Provider<Repository> provider) {
        return new BannerUseCase(provider.get());
    }

    public static BannerUseCase_Factory create(Provider<Repository> provider) {
        return new BannerUseCase_Factory(provider);
    }

    public static BannerUseCase newBannerUseCase(Repository repository) {
        return new BannerUseCase(repository);
    }
}