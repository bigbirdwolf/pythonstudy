package com.yltx.oil.partner.modules.oiltrade.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.FinanceCardlResp;
import com.yltx.oil.partner.data.response.GiftCardResp;
import com.yltx.oil.partner.data.response.SfResp;
import com.yltx.oil.partner.data.response.ShopDetailsResp;
import com.yltx.oil.partner.data.response.StoredValueCardResp;
import com.yltx.oil.partner.modules.oiltrade.domain.FinanceCardDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.FuelCardDetailsUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.GiftCardDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.SFDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.ShopDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.StoredValueCardDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.response.FuelCardDetailsResponse;
import com.yltx.oil.partner.modules.oiltrade.view.FinanCarddetailView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class FinanceCardetailPresenter implements Presenter {
    private FinanceCardDetailUseCase financeCardDetailUseCase;
    private FuelCardDetailsUseCase fuelCardDetailsUseCase;
    private GiftCardDetailUseCase mGiftCardDetailUseCase;
    private StoredValueCardDetailUseCase mStoredValueCardDetaUseCase;
    private SFDetailUseCase sFDetailUseCase;
    private ShopDetailUseCase shopDetailUseCase;
    private FinanCarddetailView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public FinanceCardetailPresenter(FuelCardDetailsUseCase fuelCardDetailsUseCase, FinanceCardDetailUseCase financeCardDetailUseCase, StoredValueCardDetailUseCase storedValueCardDetailUseCase, ShopDetailUseCase shopDetailUseCase, SFDetailUseCase sFDetailUseCase, GiftCardDetailUseCase giftCardDetailUseCase) {
        this.fuelCardDetailsUseCase = fuelCardDetailsUseCase;
        this.financeCardDetailUseCase = financeCardDetailUseCase;
        this.mStoredValueCardDetaUseCase = storedValueCardDetailUseCase;
        this.shopDetailUseCase = shopDetailUseCase;
        this.sFDetailUseCase = sFDetailUseCase;
        this.mGiftCardDetailUseCase = giftCardDetailUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.fuelCardDetailsUseCase.unSubscribe();
        this.financeCardDetailUseCase.unSubscribe();
        this.mStoredValueCardDetaUseCase.unSubscribe();
        this.shopDetailUseCase.unSubscribe();
        this.sFDetailUseCase.unSubscribe();
        this.mGiftCardDetailUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (FinanCarddetailView) interfaceView;
    }

    public void getShareNum(String str, String str2, String str3) {
        this.sFDetailUseCase.setRecommenderId(str);
        this.sFDetailUseCase.setGoodsId(str2);
        this.sFDetailUseCase.setType(str3);
        this.sFDetailUseCase.execute(new ProgressSubscriber<SfResp>(this.view) { // from class: com.yltx.oil.partner.modules.oiltrade.presenter.FinanceCardetailPresenter.1
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                FinanceCardetailPresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(SfResp sfResp) {
                super.onNext((AnonymousClass1) sfResp);
                FinanceCardetailPresenter.this.view.onDetails(sfResp);
            }
        });
    }

    public void getStoredValueCardDetailUseCase(String str) {
        this.mStoredValueCardDetaUseCase.setRowId(str);
        this.mStoredValueCardDetaUseCase.execute(new ProgressSubscriber<HttpResult<StoredValueCardResp>>(this.view) { // from class: com.yltx.oil.partner.modules.oiltrade.presenter.FinanceCardetailPresenter.2
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                FinanceCardetailPresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<StoredValueCardResp> httpResult) {
                super.onNext((AnonymousClass2) httpResult);
                FinanceCardetailPresenter.this.view.onStoredValueDetail(httpResult);
            }
        });
    }

    public void getFinanceCardetail(String str) {
        this.financeCardDetailUseCase.setRowId(str);
        this.financeCardDetailUseCase.execute(new FinanceCardetailSubscriber(this.view));
    }

    public void getshopdetail(String str) {
        this.shopDetailUseCase.setRowId(str);
        this.shopDetailUseCase.execute(new shopSubscriber(this.view));
    }

    public void getRechargeById(String str) {
        this.mGiftCardDetailUseCase.setRowId(str);
        this.mGiftCardDetailUseCase.execute(new ProgressSubscriber<HttpResult<GiftCardResp>>(this.view) { // from class: com.yltx.oil.partner.modules.oiltrade.presenter.FinanceCardetailPresenter.3
            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                FinanceCardetailPresenter.this.view.onError(th);
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(HttpResult<GiftCardResp> httpResult) {
                super.onNext((AnonymousClass3) httpResult);
                FinanceCardetailPresenter.this.view.onGiftCardDetails(httpResult);
            }
        });
    }

    public void getFueldetail(String str) {
        this.fuelCardDetailsUseCase.setRowId(str);
        this.fuelCardDetailsUseCase.execute(new FuelDetailsSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class FinanceCardetailSubscriber extends ProgressSubscriber<HttpResult<FinanceCardlResp>> {
        public FinanceCardetailSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            FinanceCardetailPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<FinanceCardlResp> httpResult) {
            super.onNext((FinanceCardetailSubscriber) httpResult);
            FinanceCardetailPresenter.this.view.onfinanceCarddetail(httpResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class shopSubscriber extends ProgressSubscriber<HttpResult<ShopDetailsResp>> {
        public shopSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            FinanceCardetailPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<ShopDetailsResp> httpResult) {
            super.onNext((shopSubscriber) httpResult);
            FinanceCardetailPresenter.this.view.onshopDetails(httpResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class FuelDetailsSubscriber extends ProgressSubscriber<HttpResult<FuelCardDetailsResponse>> {
        public FuelDetailsSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            FinanceCardetailPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<FuelCardDetailsResponse> httpResult) {
            super.onNext((FuelDetailsSubscriber) httpResult);
            FinanceCardetailPresenter.this.view.onfuelDetails(httpResult);
        }
    }
}