package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.ModifUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ModifPresenter_Factory implements Factory<ModifPresenter> {
    private final Provider<ModifUseCase> modifUseCaseProvider;

    public ModifPresenter_Factory(Provider<ModifUseCase> provider) {
        this.modifUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public ModifPresenter get() {
        return provideInstance(this.modifUseCaseProvider);
    }

    public static ModifPresenter provideInstance(Provider<ModifUseCase> provider) {
        return new ModifPresenter(provider.get());
    }

    public static ModifPresenter_Factory create(Provider<ModifUseCase> provider) {
        return new ModifPresenter_Factory(provider);
    }

    public static ModifPresenter newModifPresenter(ModifUseCase modifUseCase) {
        return new ModifPresenter(modifUseCase);
    }
}