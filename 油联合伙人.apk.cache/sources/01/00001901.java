package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.login.domain.GetvalidCodeUseCase;
import com.yltx.oil.partner.modules.mine.domain.MineinfoOldPhoneUseCase;
import com.yltx.oil.partner.modules.mine.domain.MineinfoUpdatePhoneUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MinePhonePresenter_Factory implements Factory<MinePhonePresenter> {
    private final Provider<GetvalidCodeUseCase> mGetvalidCodeUseCaseProvider;
    private final Provider<MineinfoOldPhoneUseCase> mMineinfoOldPhoneUseCaseProvider;
    private final Provider<MineinfoUpdatePhoneUseCase> mMineinfoUpdatePhoneUseCaseProvider;

    public MinePhonePresenter_Factory(Provider<MineinfoOldPhoneUseCase> provider, Provider<MineinfoUpdatePhoneUseCase> provider2, Provider<GetvalidCodeUseCase> provider3) {
        this.mMineinfoOldPhoneUseCaseProvider = provider;
        this.mMineinfoUpdatePhoneUseCaseProvider = provider2;
        this.mGetvalidCodeUseCaseProvider = provider3;
    }

    @Override // javax.inject.Provider
    public MinePhonePresenter get() {
        return provideInstance(this.mMineinfoOldPhoneUseCaseProvider, this.mMineinfoUpdatePhoneUseCaseProvider, this.mGetvalidCodeUseCaseProvider);
    }

    public static MinePhonePresenter provideInstance(Provider<MineinfoOldPhoneUseCase> provider, Provider<MineinfoUpdatePhoneUseCase> provider2, Provider<GetvalidCodeUseCase> provider3) {
        return new MinePhonePresenter(provider.get(), provider2.get(), provider3.get());
    }

    public static MinePhonePresenter_Factory create(Provider<MineinfoOldPhoneUseCase> provider, Provider<MineinfoUpdatePhoneUseCase> provider2, Provider<GetvalidCodeUseCase> provider3) {
        return new MinePhonePresenter_Factory(provider, provider2, provider3);
    }

    public static MinePhonePresenter newMinePhonePresenter(MineinfoOldPhoneUseCase mineinfoOldPhoneUseCase, MineinfoUpdatePhoneUseCase mineinfoUpdatePhoneUseCase, GetvalidCodeUseCase getvalidCodeUseCase) {
        return new MinePhonePresenter(mineinfoOldPhoneUseCase, mineinfoUpdatePhoneUseCase, getvalidCodeUseCase);
    }
}