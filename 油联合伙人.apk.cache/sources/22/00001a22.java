package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.UserAccountUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class UserAccountPresenter_Factory implements Factory<UserAccountPresenter> {
    private final Provider<UserAccountUseCase> mStoredValueCardUseCaseProvider;

    public UserAccountPresenter_Factory(Provider<UserAccountUseCase> provider) {
        this.mStoredValueCardUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public UserAccountPresenter get() {
        return provideInstance(this.mStoredValueCardUseCaseProvider);
    }

    public static UserAccountPresenter provideInstance(Provider<UserAccountUseCase> provider) {
        return new UserAccountPresenter(provider.get());
    }

    public static UserAccountPresenter_Factory create(Provider<UserAccountUseCase> provider) {
        return new UserAccountPresenter_Factory(provider);
    }

    public static UserAccountPresenter newUserAccountPresenter(UserAccountUseCase userAccountUseCase) {
        return new UserAccountPresenter(userAccountUseCase);
    }
}