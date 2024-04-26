package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.MineinfoHeadPicUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MineInfoPresenter_Factory implements Factory<MineInfoPresenter> {
    private final Provider<MineinfoHeadPicUseCase> mMineinfoHeadPicUseCaseProvider;

    public MineInfoPresenter_Factory(Provider<MineinfoHeadPicUseCase> provider) {
        this.mMineinfoHeadPicUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public MineInfoPresenter get() {
        return provideInstance(this.mMineinfoHeadPicUseCaseProvider);
    }

    public static MineInfoPresenter provideInstance(Provider<MineinfoHeadPicUseCase> provider) {
        return new MineInfoPresenter(provider.get());
    }

    public static MineInfoPresenter_Factory create(Provider<MineinfoHeadPicUseCase> provider) {
        return new MineInfoPresenter_Factory(provider);
    }

    public static MineInfoPresenter newMineInfoPresenter(MineinfoHeadPicUseCase mineinfoHeadPicUseCase) {
        return new MineInfoPresenter(mineinfoHeadPicUseCase);
    }
}