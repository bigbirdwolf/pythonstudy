package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.TotalUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class InvitePresenter_Factory implements Factory<InvitePresenter> {
    private final Provider<TotalUseCase> totalUseCaseProvider;

    public InvitePresenter_Factory(Provider<TotalUseCase> provider) {
        this.totalUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public InvitePresenter get() {
        return provideInstance(this.totalUseCaseProvider);
    }

    public static InvitePresenter provideInstance(Provider<TotalUseCase> provider) {
        return new InvitePresenter(provider.get());
    }

    public static InvitePresenter_Factory create(Provider<TotalUseCase> provider) {
        return new InvitePresenter_Factory(provider);
    }

    public static InvitePresenter newInvitePresenter(TotalUseCase totalUseCase) {
        return new InvitePresenter(totalUseCase);
    }
}