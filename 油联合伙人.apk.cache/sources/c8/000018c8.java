package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MineinfoUpdatePhoneUseCase_Factory implements Factory<MineinfoUpdatePhoneUseCase> {
    private final Provider<Repository> repositoryProvider;

    public MineinfoUpdatePhoneUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public MineinfoUpdatePhoneUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static MineinfoUpdatePhoneUseCase provideInstance(Provider<Repository> provider) {
        return new MineinfoUpdatePhoneUseCase(provider.get());
    }

    public static MineinfoUpdatePhoneUseCase_Factory create(Provider<Repository> provider) {
        return new MineinfoUpdatePhoneUseCase_Factory(provider);
    }

    public static MineinfoUpdatePhoneUseCase newMineinfoUpdatePhoneUseCase(Repository repository) {
        return new MineinfoUpdatePhoneUseCase(repository);
    }
}