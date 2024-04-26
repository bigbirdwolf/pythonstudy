package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.TxUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ModificationPresenter_Factory implements Factory<ModificationPresenter> {
    private final Provider<TxUseCase> mUseCaseProvider;

    public ModificationPresenter_Factory(Provider<TxUseCase> provider) {
        this.mUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public ModificationPresenter get() {
        return provideInstance(this.mUseCaseProvider);
    }

    public static ModificationPresenter provideInstance(Provider<TxUseCase> provider) {
        return new ModificationPresenter(provider.get());
    }

    public static ModificationPresenter_Factory create(Provider<TxUseCase> provider) {
        return new ModificationPresenter_Factory(provider);
    }

    public static ModificationPresenter newModificationPresenter(TxUseCase txUseCase) {
        return new ModificationPresenter(txUseCase);
    }
}