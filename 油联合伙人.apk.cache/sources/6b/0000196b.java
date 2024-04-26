package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.StoredValueCardUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class StoredValueCardPresenter_Factory implements Factory<StoredValueCardPresenter> {
    private final Provider<StoredValueCardUseCase> mStoredValueCardUseCaseProvider;

    public StoredValueCardPresenter_Factory(Provider<StoredValueCardUseCase> provider) {
        this.mStoredValueCardUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public StoredValueCardPresenter get() {
        return provideInstance(this.mStoredValueCardUseCaseProvider);
    }

    public static StoredValueCardPresenter provideInstance(Provider<StoredValueCardUseCase> provider) {
        return new StoredValueCardPresenter(provider.get());
    }

    public static StoredValueCardPresenter_Factory create(Provider<StoredValueCardUseCase> provider) {
        return new StoredValueCardPresenter_Factory(provider);
    }

    public static StoredValueCardPresenter newStoredValueCardPresenter(StoredValueCardUseCase storedValueCardUseCase) {
        return new StoredValueCardPresenter(storedValueCardUseCase);
    }
}