package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class PhoneUseCase_Factory implements Factory<PhoneUseCase> {
    private final Provider<Repository> repositoryProvider;

    public PhoneUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public PhoneUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static PhoneUseCase provideInstance(Provider<Repository> provider) {
        return new PhoneUseCase(provider.get());
    }

    public static PhoneUseCase_Factory create(Provider<Repository> provider) {
        return new PhoneUseCase_Factory(provider);
    }

    public static PhoneUseCase newPhoneUseCase(Repository repository) {
        return new PhoneUseCase(repository);
    }
}