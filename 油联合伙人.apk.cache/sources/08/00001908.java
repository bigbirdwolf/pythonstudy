package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.MyfeedbackSubmintUseCase;
import com.yltx.oil.partner.modules.mine.domain.MyfeedbackUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MyfeedbackPresenter_Factory implements Factory<MyfeedbackPresenter> {
    private final Provider<MyfeedbackSubmintUseCase> myfeedbackSubmintUseCaseProvider;
    private final Provider<MyfeedbackUseCase> myfeedbackUseCaseProvider;

    public MyfeedbackPresenter_Factory(Provider<MyfeedbackUseCase> provider, Provider<MyfeedbackSubmintUseCase> provider2) {
        this.myfeedbackUseCaseProvider = provider;
        this.myfeedbackSubmintUseCaseProvider = provider2;
    }

    @Override // javax.inject.Provider
    public MyfeedbackPresenter get() {
        return provideInstance(this.myfeedbackUseCaseProvider, this.myfeedbackSubmintUseCaseProvider);
    }

    public static MyfeedbackPresenter provideInstance(Provider<MyfeedbackUseCase> provider, Provider<MyfeedbackSubmintUseCase> provider2) {
        return new MyfeedbackPresenter(provider.get(), provider2.get());
    }

    public static MyfeedbackPresenter_Factory create(Provider<MyfeedbackUseCase> provider, Provider<MyfeedbackSubmintUseCase> provider2) {
        return new MyfeedbackPresenter_Factory(provider, provider2);
    }

    public static MyfeedbackPresenter newMyfeedbackPresenter(MyfeedbackUseCase myfeedbackUseCase, MyfeedbackSubmintUseCase myfeedbackSubmintUseCase) {
        return new MyfeedbackPresenter(myfeedbackUseCase, myfeedbackSubmintUseCase);
    }
}