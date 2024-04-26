package com.yltx.oil.partner.modules.profit.presenter;

import com.yltx.oil.partner.modules.profit.domain.FragmentProfit_yjjsjl_UseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FragmentProfit_yjjsjl_Presenter_Factory implements Factory<FragmentProfit_yjjsjl_Presenter> {
    private final Provider<FragmentProfit_yjjsjl_UseCase> useCaseProvider;

    public FragmentProfit_yjjsjl_Presenter_Factory(Provider<FragmentProfit_yjjsjl_UseCase> provider) {
        this.useCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public FragmentProfit_yjjsjl_Presenter get() {
        return provideInstance(this.useCaseProvider);
    }

    public static FragmentProfit_yjjsjl_Presenter provideInstance(Provider<FragmentProfit_yjjsjl_UseCase> provider) {
        return new FragmentProfit_yjjsjl_Presenter(provider.get());
    }

    public static FragmentProfit_yjjsjl_Presenter_Factory create(Provider<FragmentProfit_yjjsjl_UseCase> provider) {
        return new FragmentProfit_yjjsjl_Presenter_Factory(provider);
    }

    public static FragmentProfit_yjjsjl_Presenter newFragmentProfit_yjjsjl_Presenter(FragmentProfit_yjjsjl_UseCase fragmentProfit_yjjsjl_UseCase) {
        return new FragmentProfit_yjjsjl_Presenter(fragmentProfit_yjjsjl_UseCase);
    }
}