package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.FinanceCardUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FinanceCardPresenter_Factory implements Factory<FinanceCardPresenter> {
    private final Provider<FinanceCardUseCase> mfProvider;

    public FinanceCardPresenter_Factory(Provider<FinanceCardUseCase> provider) {
        this.mfProvider = provider;
    }

    @Override // javax.inject.Provider
    public FinanceCardPresenter get() {
        return provideInstance(this.mfProvider);
    }

    public static FinanceCardPresenter provideInstance(Provider<FinanceCardUseCase> provider) {
        return new FinanceCardPresenter(provider.get());
    }

    public static FinanceCardPresenter_Factory create(Provider<FinanceCardUseCase> provider) {
        return new FinanceCardPresenter_Factory(provider);
    }

    public static FinanceCardPresenter newFinanceCardPresenter(FinanceCardUseCase financeCardUseCase) {
        return new FinanceCardPresenter(financeCardUseCase);
    }
}