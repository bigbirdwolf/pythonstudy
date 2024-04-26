package com.yltx.oil.partner.modules.login.presenter;

import com.yltx.oil.partner.modules.login.domain.GetImgCodeUseCase;
import com.yltx.oil.partner.modules.login.domain.RegisterUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RegisterPresenter_Factory implements Factory<RegisterPresenter> {
    private final Provider<GetImgCodeUseCase> getImgCodeUseCaseProvider;
    private final Provider<RegisterUseCase> registerUseCaseProvider;

    public RegisterPresenter_Factory(Provider<RegisterUseCase> provider, Provider<GetImgCodeUseCase> provider2) {
        this.registerUseCaseProvider = provider;
        this.getImgCodeUseCaseProvider = provider2;
    }

    @Override // javax.inject.Provider
    public RegisterPresenter get() {
        return provideInstance(this.registerUseCaseProvider, this.getImgCodeUseCaseProvider);
    }

    public static RegisterPresenter provideInstance(Provider<RegisterUseCase> provider, Provider<GetImgCodeUseCase> provider2) {
        return new RegisterPresenter(provider.get(), provider2.get());
    }

    public static RegisterPresenter_Factory create(Provider<RegisterUseCase> provider, Provider<GetImgCodeUseCase> provider2) {
        return new RegisterPresenter_Factory(provider, provider2);
    }

    public static RegisterPresenter newRegisterPresenter(RegisterUseCase registerUseCase, GetImgCodeUseCase getImgCodeUseCase) {
        return new RegisterPresenter(registerUseCase, getImgCodeUseCase);
    }
}