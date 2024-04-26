package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class PersonalCenterUseCase_Factory implements Factory<PersonalCenterUseCase> {
    private final Provider<Repository> repositoryProvider;

    public PersonalCenterUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public PersonalCenterUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static PersonalCenterUseCase provideInstance(Provider<Repository> provider) {
        return new PersonalCenterUseCase(provider.get());
    }

    public static PersonalCenterUseCase_Factory create(Provider<Repository> provider) {
        return new PersonalCenterUseCase_Factory(provider);
    }

    public static PersonalCenterUseCase newPersonalCenterUseCase(Repository repository) {
        return new PersonalCenterUseCase(repository);
    }
}