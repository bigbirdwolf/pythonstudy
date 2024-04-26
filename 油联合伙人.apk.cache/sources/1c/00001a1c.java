package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.GetTxHistoryListUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class TxHistoryPresenter_Factory implements Factory<TxHistoryPresenter> {
    private final Provider<GetTxHistoryListUseCase> mUseCaseProvider;

    public TxHistoryPresenter_Factory(Provider<GetTxHistoryListUseCase> provider) {
        this.mUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public TxHistoryPresenter get() {
        return provideInstance(this.mUseCaseProvider);
    }

    public static TxHistoryPresenter provideInstance(Provider<GetTxHistoryListUseCase> provider) {
        return new TxHistoryPresenter(provider.get());
    }

    public static TxHistoryPresenter_Factory create(Provider<GetTxHistoryListUseCase> provider) {
        return new TxHistoryPresenter_Factory(provider);
    }

    public static TxHistoryPresenter newTxHistoryPresenter(GetTxHistoryListUseCase getTxHistoryListUseCase) {
        return new TxHistoryPresenter(getTxHistoryListUseCase);
    }
}