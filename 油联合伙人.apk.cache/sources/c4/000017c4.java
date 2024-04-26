package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.SCUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SCPresenter_Factory implements Factory<SCPresenter> {
    private final Provider<SCUseCase> scUseCaseProvider;

    public SCPresenter_Factory(Provider<SCUseCase> provider) {
        this.scUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public SCPresenter get() {
        return provideInstance(this.scUseCaseProvider);
    }

    public static SCPresenter provideInstance(Provider<SCUseCase> provider) {
        return new SCPresenter(provider.get());
    }

    public static SCPresenter_Factory create(Provider<SCUseCase> provider) {
        return new SCPresenter_Factory(provider);
    }

    public static SCPresenter newSCPresenter(SCUseCase sCUseCase) {
        return new SCPresenter(sCUseCase);
    }
}