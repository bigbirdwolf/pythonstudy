package com.yltx.oil.partner.modules.login.presenter;

import com.yltx.oil.partner.modules.login.domain.AppLoginStatusUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AppLoginStatusPresenter_Factory implements Factory<AppLoginStatusPresenter> {
    private final Provider<AppLoginStatusUseCase> loginUseCaseProvider;

    public AppLoginStatusPresenter_Factory(Provider<AppLoginStatusUseCase> provider) {
        this.loginUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public AppLoginStatusPresenter get() {
        return provideInstance(this.loginUseCaseProvider);
    }

    public static AppLoginStatusPresenter provideInstance(Provider<AppLoginStatusUseCase> provider) {
        return new AppLoginStatusPresenter(provider.get());
    }

    public static AppLoginStatusPresenter_Factory create(Provider<AppLoginStatusUseCase> provider) {
        return new AppLoginStatusPresenter_Factory(provider);
    }

    public static AppLoginStatusPresenter newAppLoginStatusPresenter(AppLoginStatusUseCase appLoginStatusUseCase) {
        return new AppLoginStatusPresenter(appLoginStatusUseCase);
    }
}