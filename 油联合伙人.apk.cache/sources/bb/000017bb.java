package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.HomeButtonconfigurationUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class HomeButtonconfigurationPresenter_Factory implements Factory<HomeButtonconfigurationPresenter> {
    private final Provider<HomeButtonconfigurationUseCase> homeButtonconfigurationUseCaseProvider;

    public HomeButtonconfigurationPresenter_Factory(Provider<HomeButtonconfigurationUseCase> provider) {
        this.homeButtonconfigurationUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public HomeButtonconfigurationPresenter get() {
        return provideInstance(this.homeButtonconfigurationUseCaseProvider);
    }

    public static HomeButtonconfigurationPresenter provideInstance(Provider<HomeButtonconfigurationUseCase> provider) {
        return new HomeButtonconfigurationPresenter(provider.get());
    }

    public static HomeButtonconfigurationPresenter_Factory create(Provider<HomeButtonconfigurationUseCase> provider) {
        return new HomeButtonconfigurationPresenter_Factory(provider);
    }

    public static HomeButtonconfigurationPresenter newHomeButtonconfigurationPresenter(HomeButtonconfigurationUseCase homeButtonconfigurationUseCase) {
        return new HomeButtonconfigurationPresenter(homeButtonconfigurationUseCase);
    }
}