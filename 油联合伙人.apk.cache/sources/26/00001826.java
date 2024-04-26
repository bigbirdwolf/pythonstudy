package com.yltx.oil.partner.modules.login.presenter;

import com.yltx.oil.partner.modules.login.domain.FindpwUseCase;
import com.yltx.oil.partner.modules.login.domain.GetvalidCodeUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ForgetPwdPresenter_Factory implements Factory<ForgetPwdPresenter> {
    private final Provider<FindpwUseCase> mFindpwUseCaseProvider;
    private final Provider<GetvalidCodeUseCase> mGetvalidCodeUseCaseProvider;

    public ForgetPwdPresenter_Factory(Provider<FindpwUseCase> provider, Provider<GetvalidCodeUseCase> provider2) {
        this.mFindpwUseCaseProvider = provider;
        this.mGetvalidCodeUseCaseProvider = provider2;
    }

    @Override // javax.inject.Provider
    public ForgetPwdPresenter get() {
        return provideInstance(this.mFindpwUseCaseProvider, this.mGetvalidCodeUseCaseProvider);
    }

    public static ForgetPwdPresenter provideInstance(Provider<FindpwUseCase> provider, Provider<GetvalidCodeUseCase> provider2) {
        return new ForgetPwdPresenter(provider.get(), provider2.get());
    }

    public static ForgetPwdPresenter_Factory create(Provider<FindpwUseCase> provider, Provider<GetvalidCodeUseCase> provider2) {
        return new ForgetPwdPresenter_Factory(provider, provider2);
    }

    public static ForgetPwdPresenter newForgetPwdPresenter(FindpwUseCase findpwUseCase, GetvalidCodeUseCase getvalidCodeUseCase) {
        return new ForgetPwdPresenter(findpwUseCase, getvalidCodeUseCase);
    }
}