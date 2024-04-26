package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.SettlementRecordsUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SettlementRecordsPresenter_Factory implements Factory<SettlementRecordsPresenter> {
    private final Provider<SettlementRecordsUseCase> useCaseProvider;

    public SettlementRecordsPresenter_Factory(Provider<SettlementRecordsUseCase> provider) {
        this.useCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public SettlementRecordsPresenter get() {
        return provideInstance(this.useCaseProvider);
    }

    public static SettlementRecordsPresenter provideInstance(Provider<SettlementRecordsUseCase> provider) {
        return new SettlementRecordsPresenter(provider.get());
    }

    public static SettlementRecordsPresenter_Factory create(Provider<SettlementRecordsUseCase> provider) {
        return new SettlementRecordsPresenter_Factory(provider);
    }

    public static SettlementRecordsPresenter newSettlementRecordsPresenter(SettlementRecordsUseCase settlementRecordsUseCase) {
        return new SettlementRecordsPresenter(settlementRecordsUseCase);
    }
}