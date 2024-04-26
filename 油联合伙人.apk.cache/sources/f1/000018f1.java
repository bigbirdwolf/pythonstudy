package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.DetailUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class InviteDetailPresenter_Factory implements Factory<InviteDetailPresenter> {
    private final Provider<DetailUseCase> mDetailUseCaseProvider;

    public InviteDetailPresenter_Factory(Provider<DetailUseCase> provider) {
        this.mDetailUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public InviteDetailPresenter get() {
        return provideInstance(this.mDetailUseCaseProvider);
    }

    public static InviteDetailPresenter provideInstance(Provider<DetailUseCase> provider) {
        return new InviteDetailPresenter(provider.get());
    }

    public static InviteDetailPresenter_Factory create(Provider<DetailUseCase> provider) {
        return new InviteDetailPresenter_Factory(provider);
    }

    public static InviteDetailPresenter newInviteDetailPresenter(DetailUseCase detailUseCase) {
        return new InviteDetailPresenter(detailUseCase);
    }
}