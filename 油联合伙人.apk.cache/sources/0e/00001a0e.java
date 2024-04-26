package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.CommissionUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class CommissionPresenter_Factory implements Factory<CommissionPresenter> {
    private final Provider<CommissionUseCase> commissionUseCaseProvider;

    public CommissionPresenter_Factory(Provider<CommissionUseCase> provider) {
        this.commissionUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public CommissionPresenter get() {
        return provideInstance(this.commissionUseCaseProvider);
    }

    public static CommissionPresenter provideInstance(Provider<CommissionUseCase> provider) {
        return new CommissionPresenter(provider.get());
    }

    public static CommissionPresenter_Factory create(Provider<CommissionUseCase> provider) {
        return new CommissionPresenter_Factory(provider);
    }

    public static CommissionPresenter newCommissionPresenter(CommissionUseCase commissionUseCase) {
        return new CommissionPresenter(commissionUseCase);
    }
}