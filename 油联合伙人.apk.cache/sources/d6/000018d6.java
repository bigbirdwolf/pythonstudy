package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RechargePayTypeOrderCae_Factory implements Factory<RechargePayTypeOrderCae> {
    private final Provider<Repository> repositoryProvider;

    public RechargePayTypeOrderCae_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public RechargePayTypeOrderCae get() {
        return provideInstance(this.repositoryProvider);
    }

    public static RechargePayTypeOrderCae provideInstance(Provider<Repository> provider) {
        return new RechargePayTypeOrderCae(provider.get());
    }

    public static RechargePayTypeOrderCae_Factory create(Provider<Repository> provider) {
        return new RechargePayTypeOrderCae_Factory(provider);
    }

    public static RechargePayTypeOrderCae newRechargePayTypeOrderCae(Repository repository) {
        return new RechargePayTypeOrderCae(repository);
    }
}