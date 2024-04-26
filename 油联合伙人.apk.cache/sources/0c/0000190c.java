package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.RechargePayOrderCae;
import com.yltx.oil.partner.modules.mine.domain.RechargePayTypeOrderCae;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RechargePresenter_Factory implements Factory<RechargePresenter> {
    private final Provider<RechargePayOrderCae> mrechargePayOrderCaeProvider;
    private final Provider<RechargePayTypeOrderCae> rechargePayTypeOrderCaeProvider;

    public RechargePresenter_Factory(Provider<RechargePayTypeOrderCae> provider, Provider<RechargePayOrderCae> provider2) {
        this.rechargePayTypeOrderCaeProvider = provider;
        this.mrechargePayOrderCaeProvider = provider2;
    }

    @Override // javax.inject.Provider
    public RechargePresenter get() {
        return provideInstance(this.rechargePayTypeOrderCaeProvider, this.mrechargePayOrderCaeProvider);
    }

    public static RechargePresenter provideInstance(Provider<RechargePayTypeOrderCae> provider, Provider<RechargePayOrderCae> provider2) {
        return new RechargePresenter(provider.get(), provider2.get());
    }

    public static RechargePresenter_Factory create(Provider<RechargePayTypeOrderCae> provider, Provider<RechargePayOrderCae> provider2) {
        return new RechargePresenter_Factory(provider, provider2);
    }

    public static RechargePresenter newRechargePresenter(RechargePayTypeOrderCae rechargePayTypeOrderCae, RechargePayOrderCae rechargePayOrderCae) {
        return new RechargePresenter(rechargePayTypeOrderCae, rechargePayOrderCae);
    }
}