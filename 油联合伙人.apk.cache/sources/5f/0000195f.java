package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.modules.oiltrade.domain.FinanceCardDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.FuelCardDetailsUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.GiftCardDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.SFDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.ShopDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.StoredValueCardDetailUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FinanceCardetailPresenter_Factory implements Factory<FinanceCardetailPresenter> {
    private final Provider<FinanceCardDetailUseCase> financeCardDetailUseCaseProvider;
    private final Provider<FuelCardDetailsUseCase> fuelCardDetailsUseCaseProvider;
    private final Provider<GiftCardDetailUseCase> mGiftCardDetailUseCaseProvider;
    private final Provider<StoredValueCardDetailUseCase> mStoredValueCardDetaUseCaseProvider;
    private final Provider<SFDetailUseCase> sFDetailUseCaseProvider;
    private final Provider<ShopDetailUseCase> shopDetailUseCaseProvider;

    public FinanceCardetailPresenter_Factory(Provider<FuelCardDetailsUseCase> provider, Provider<FinanceCardDetailUseCase> provider2, Provider<StoredValueCardDetailUseCase> provider3, Provider<ShopDetailUseCase> provider4, Provider<SFDetailUseCase> provider5, Provider<GiftCardDetailUseCase> provider6) {
        this.fuelCardDetailsUseCaseProvider = provider;
        this.financeCardDetailUseCaseProvider = provider2;
        this.mStoredValueCardDetaUseCaseProvider = provider3;
        this.shopDetailUseCaseProvider = provider4;
        this.sFDetailUseCaseProvider = provider5;
        this.mGiftCardDetailUseCaseProvider = provider6;
    }

    @Override // javax.inject.Provider
    public FinanceCardetailPresenter get() {
        return provideInstance(this.fuelCardDetailsUseCaseProvider, this.financeCardDetailUseCaseProvider, this.mStoredValueCardDetaUseCaseProvider, this.shopDetailUseCaseProvider, this.sFDetailUseCaseProvider, this.mGiftCardDetailUseCaseProvider);
    }

    public static FinanceCardetailPresenter provideInstance(Provider<FuelCardDetailsUseCase> provider, Provider<FinanceCardDetailUseCase> provider2, Provider<StoredValueCardDetailUseCase> provider3, Provider<ShopDetailUseCase> provider4, Provider<SFDetailUseCase> provider5, Provider<GiftCardDetailUseCase> provider6) {
        return new FinanceCardetailPresenter(provider.get(), provider2.get(), provider3.get(), provider4.get(), provider5.get(), provider6.get());
    }

    public static FinanceCardetailPresenter_Factory create(Provider<FuelCardDetailsUseCase> provider, Provider<FinanceCardDetailUseCase> provider2, Provider<StoredValueCardDetailUseCase> provider3, Provider<ShopDetailUseCase> provider4, Provider<SFDetailUseCase> provider5, Provider<GiftCardDetailUseCase> provider6) {
        return new FinanceCardetailPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static FinanceCardetailPresenter newFinanceCardetailPresenter(FuelCardDetailsUseCase fuelCardDetailsUseCase, FinanceCardDetailUseCase financeCardDetailUseCase, StoredValueCardDetailUseCase storedValueCardDetailUseCase, ShopDetailUseCase shopDetailUseCase, SFDetailUseCase sFDetailUseCase, GiftCardDetailUseCase giftCardDetailUseCase) {
        return new FinanceCardetailPresenter(fuelCardDetailsUseCase, financeCardDetailUseCase, storedValueCardDetailUseCase, shopDetailUseCase, sFDetailUseCase, giftCardDetailUseCase);
    }
}