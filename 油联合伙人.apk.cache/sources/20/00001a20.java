package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.TxApplyUseCase;
import com.yltx.oil.partner.modules.profit.domain.TxUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class TxPresenter_Factory implements Factory<TxPresenter> {
    private final Provider<TxApplyUseCase> mApplyUseCaseProvider;
    private final Provider<TxUseCase> mUseCaseProvider;

    public TxPresenter_Factory(Provider<TxUseCase> provider, Provider<TxApplyUseCase> provider2) {
        this.mUseCaseProvider = provider;
        this.mApplyUseCaseProvider = provider2;
    }

    @Override // javax.inject.Provider
    public TxPresenter get() {
        return provideInstance(this.mUseCaseProvider, this.mApplyUseCaseProvider);
    }

    public static TxPresenter provideInstance(Provider<TxUseCase> provider, Provider<TxApplyUseCase> provider2) {
        return new TxPresenter(provider.get(), provider2.get());
    }

    public static TxPresenter_Factory create(Provider<TxUseCase> provider, Provider<TxApplyUseCase> provider2) {
        return new TxPresenter_Factory(provider, provider2);
    }

    public static TxPresenter newTxPresenter(TxUseCase txUseCase, TxApplyUseCase txApplyUseCase) {
        return new TxPresenter(txUseCase, txApplyUseCase);
    }
}