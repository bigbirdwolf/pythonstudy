package com.yltx.oil.partner.modules.login.presenter;

import com.yltx.oil.partner.modules.login.domain.GetvalidCodeUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GetValidCodePresenter_Factory implements Factory<GetValidCodePresenter> {
    private final Provider<GetvalidCodeUseCase> getvalidCodeUseCaseProvider;

    public GetValidCodePresenter_Factory(Provider<GetvalidCodeUseCase> provider) {
        this.getvalidCodeUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public GetValidCodePresenter get() {
        return provideInstance(this.getvalidCodeUseCaseProvider);
    }

    public static GetValidCodePresenter provideInstance(Provider<GetvalidCodeUseCase> provider) {
        return new GetValidCodePresenter(provider.get());
    }

    public static GetValidCodePresenter_Factory create(Provider<GetvalidCodeUseCase> provider) {
        return new GetValidCodePresenter_Factory(provider);
    }

    public static GetValidCodePresenter newGetValidCodePresenter(GetvalidCodeUseCase getvalidCodeUseCase) {
        return new GetValidCodePresenter(getvalidCodeUseCase);
    }
}