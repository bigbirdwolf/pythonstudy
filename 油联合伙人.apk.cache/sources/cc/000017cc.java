package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.ShortUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ShareDetailPresenter_Factory implements Factory<ShareDetailPresenter> {
    private final Provider<ShortUseCase> bannerUseCaseProvider;

    public ShareDetailPresenter_Factory(Provider<ShortUseCase> provider) {
        this.bannerUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public ShareDetailPresenter get() {
        return provideInstance(this.bannerUseCaseProvider);
    }

    public static ShareDetailPresenter provideInstance(Provider<ShortUseCase> provider) {
        return new ShareDetailPresenter(provider.get());
    }

    public static ShareDetailPresenter_Factory create(Provider<ShortUseCase> provider) {
        return new ShareDetailPresenter_Factory(provider);
    }

    public static ShareDetailPresenter newShareDetailPresenter(ShortUseCase shortUseCase) {
        return new ShareDetailPresenter(shortUseCase);
    }
}