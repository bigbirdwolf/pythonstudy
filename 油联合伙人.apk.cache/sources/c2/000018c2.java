package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MemberUseCase_Factory implements Factory<MemberUseCase> {
    private final Provider<Repository> repositoryProvider;

    public MemberUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public MemberUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static MemberUseCase provideInstance(Provider<Repository> provider) {
        return new MemberUseCase(provider.get());
    }

    public static MemberUseCase_Factory create(Provider<Repository> provider) {
        return new MemberUseCase_Factory(provider);
    }

    public static MemberUseCase newMemberUseCase(Repository repository) {
        return new MemberUseCase(repository);
    }
}