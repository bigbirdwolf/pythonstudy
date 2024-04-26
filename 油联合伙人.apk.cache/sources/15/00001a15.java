package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.JoinUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class JoinPresenter_Factory implements Factory<JoinPresenter> {
    private final Provider<JoinUseCase> joinUseCaseProvider;

    public JoinPresenter_Factory(Provider<JoinUseCase> provider) {
        this.joinUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public JoinPresenter get() {
        return provideInstance(this.joinUseCaseProvider);
    }

    public static JoinPresenter provideInstance(Provider<JoinUseCase> provider) {
        return new JoinPresenter(provider.get());
    }

    public static JoinPresenter_Factory create(Provider<JoinUseCase> provider) {
        return new JoinPresenter_Factory(provider);
    }

    public static JoinPresenter newJoinPresenter(JoinUseCase joinUseCase) {
        return new JoinPresenter(joinUseCase);
    }
}