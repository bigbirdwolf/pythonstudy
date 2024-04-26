package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.BannerUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class BannerPresenter_Factory implements Factory<BannerPresenter> {
    private final Provider<BannerUseCase> bannerUseCaseProvider;

    public BannerPresenter_Factory(Provider<BannerUseCase> provider) {
        this.bannerUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public BannerPresenter get() {
        return provideInstance(this.bannerUseCaseProvider);
    }

    public static BannerPresenter provideInstance(Provider<BannerUseCase> provider) {
        return new BannerPresenter(provider.get());
    }

    public static BannerPresenter_Factory create(Provider<BannerUseCase> provider) {
        return new BannerPresenter_Factory(provider);
    }

    public static BannerPresenter newBannerPresenter(BannerUseCase bannerUseCase) {
        return new BannerPresenter(bannerUseCase);
    }
}