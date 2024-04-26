package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MineinfoOldPhoneUseCase_Factory implements Factory<MineinfoOldPhoneUseCase> {
    private final Provider<Repository> repositoryProvider;

    public MineinfoOldPhoneUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public MineinfoOldPhoneUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static MineinfoOldPhoneUseCase provideInstance(Provider<Repository> provider) {
        return new MineinfoOldPhoneUseCase(provider.get());
    }

    public static MineinfoOldPhoneUseCase_Factory create(Provider<Repository> provider) {
        return new MineinfoOldPhoneUseCase_Factory(provider);
    }

    public static MineinfoOldPhoneUseCase newMineinfoOldPhoneUseCase(Repository repository) {
        return new MineinfoOldPhoneUseCase(repository);
    }
}