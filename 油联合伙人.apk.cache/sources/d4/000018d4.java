package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RechargePayOrderCae_Factory implements Factory<RechargePayOrderCae> {
    private final Provider<Repository> repositoryProvider;

    public RechargePayOrderCae_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public RechargePayOrderCae get() {
        return provideInstance(this.repositoryProvider);
    }

    public static RechargePayOrderCae provideInstance(Provider<Repository> provider) {
        return new RechargePayOrderCae(provider.get());
    }

    public static RechargePayOrderCae_Factory create(Provider<Repository> provider) {
        return new RechargePayOrderCae_Factory(provider);
    }

    public static RechargePayOrderCae newRechargePayOrderCae(Repository repository) {
        return new RechargePayOrderCae(repository);
    }
}