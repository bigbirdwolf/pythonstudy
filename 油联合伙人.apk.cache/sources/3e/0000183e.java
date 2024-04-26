package com.yltx.oil.partner.modules.main.presenter;

import com.yltx.oil.partner.modules.main.domian.CheckVersionUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SplashPresenter_Factory implements Factory<SplashPresenter> {
    private final Provider<CheckVersionUseCase> mCheckVersionUseCaseProvider;

    public SplashPresenter_Factory(Provider<CheckVersionUseCase> provider) {
        this.mCheckVersionUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public SplashPresenter get() {
        return provideInstance(this.mCheckVersionUseCaseProvider);
    }

    public static SplashPresenter provideInstance(Provider<CheckVersionUseCase> provider) {
        return new SplashPresenter(provider.get());
    }

    public static SplashPresenter_Factory create(Provider<CheckVersionUseCase> provider) {
        return new SplashPresenter_Factory(provider);
    }

    public static SplashPresenter newSplashPresenter(CheckVersionUseCase checkVersionUseCase) {
        return new SplashPresenter(checkVersionUseCase);
    }
}