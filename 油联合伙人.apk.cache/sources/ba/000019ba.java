package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FragmentProfit_yjjsjl_UseCase_Factory implements Factory<FragmentProfit_yjjsjl_UseCase> {
    private final Provider<Repository> repositoryProvider;

    public FragmentProfit_yjjsjl_UseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public FragmentProfit_yjjsjl_UseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static FragmentProfit_yjjsjl_UseCase provideInstance(Provider<Repository> provider) {
        return new FragmentProfit_yjjsjl_UseCase(provider.get());
    }

    public static FragmentProfit_yjjsjl_UseCase_Factory create(Provider<Repository> provider) {
        return new FragmentProfit_yjjsjl_UseCase_Factory(provider);
    }

    public static FragmentProfit_yjjsjl_UseCase newFragmentProfit_yjjsjl_UseCase(Repository repository) {
        return new FragmentProfit_yjjsjl_UseCase(repository);
    }
}