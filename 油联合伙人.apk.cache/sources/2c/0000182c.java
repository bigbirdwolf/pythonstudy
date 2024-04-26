package com.yltx.oil.partner.modules.login.presenter;

import com.yltx.oil.partner.modules.login.domain.AutoUseCase;
import com.yltx.oil.partner.modules.login.domain.LoginSmUseCase;
import com.yltx.oil.partner.modules.login.domain.LoginUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class LoginPresenter_Factory implements Factory<LoginPresenter> {
    private final Provider<AutoUseCase> autoUseCaseProvider;
    private final Provider<LoginSmUseCase> loginSmUseCaseProvider;
    private final Provider<LoginUseCase> loginUseCaseProvider;

    public LoginPresenter_Factory(Provider<LoginUseCase> provider, Provider<LoginSmUseCase> provider2, Provider<AutoUseCase> provider3) {
        this.loginUseCaseProvider = provider;
        this.loginSmUseCaseProvider = provider2;
        this.autoUseCaseProvider = provider3;
    }

    @Override // javax.inject.Provider
    public LoginPresenter get() {
        return provideInstance(this.loginUseCaseProvider, this.loginSmUseCaseProvider, this.autoUseCaseProvider);
    }

    public static LoginPresenter provideInstance(Provider<LoginUseCase> provider, Provider<LoginSmUseCase> provider2, Provider<AutoUseCase> provider3) {
        return new LoginPresenter(provider.get(), provider2.get(), provider3.get());
    }

    public static LoginPresenter_Factory create(Provider<LoginUseCase> provider, Provider<LoginSmUseCase> provider2, Provider<AutoUseCase> provider3) {
        return new LoginPresenter_Factory(provider, provider2, provider3);
    }

    public static LoginPresenter newLoginPresenter(LoginUseCase loginUseCase, LoginSmUseCase loginSmUseCase, AutoUseCase autoUseCase) {
        return new LoginPresenter(loginUseCase, loginSmUseCase, autoUseCase);
    }
}