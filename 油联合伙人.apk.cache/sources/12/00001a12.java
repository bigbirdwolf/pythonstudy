package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.GeneralizeUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GeneralizePresenter_Factory implements Factory<GeneralizePresenter> {
    private final Provider<GeneralizeUseCase> generalizeUseCaseProvider;

    public GeneralizePresenter_Factory(Provider<GeneralizeUseCase> provider) {
        this.generalizeUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public GeneralizePresenter get() {
        return provideInstance(this.generalizeUseCaseProvider);
    }

    public static GeneralizePresenter provideInstance(Provider<GeneralizeUseCase> provider) {
        return new GeneralizePresenter(provider.get());
    }

    public static GeneralizePresenter_Factory create(Provider<GeneralizeUseCase> provider) {
        return new GeneralizePresenter_Factory(provider);
    }

    public static GeneralizePresenter newGeneralizePresenter(GeneralizeUseCase generalizeUseCase) {
        return new GeneralizePresenter(generalizeUseCase);
    }
}