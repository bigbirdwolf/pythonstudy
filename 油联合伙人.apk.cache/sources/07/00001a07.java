package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.AllordersUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AllordersPresenter_Factory implements Factory<AllordersPresenter> {
    private final Provider<AllordersUseCase> allordersUseCaseProvider;

    public AllordersPresenter_Factory(Provider<AllordersUseCase> provider) {
        this.allordersUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public AllordersPresenter get() {
        return provideInstance(this.allordersUseCaseProvider);
    }

    public static AllordersPresenter provideInstance(Provider<AllordersUseCase> provider) {
        return new AllordersPresenter(provider.get());
    }

    public static AllordersPresenter_Factory create(Provider<AllordersUseCase> provider) {
        return new AllordersPresenter_Factory(provider);
    }

    public static AllordersPresenter newAllordersPresenter(AllordersUseCase allordersUseCase) {
        return new AllordersPresenter(allordersUseCase);
    }
}