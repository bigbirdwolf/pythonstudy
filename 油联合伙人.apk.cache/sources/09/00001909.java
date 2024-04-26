package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.data.response.LnvoicePayResp;
import com.yltx.oil.partner.data.response.RechargePayTypeResp;
import com.yltx.oil.partner.modules.mine.domain.RechargePayOrderCae;
import com.yltx.oil.partner.modules.mine.domain.RechargePayTypeOrderCae;
import com.yltx.oil.partner.modules.mine.view.RechargeView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import javax.inject.Inject;
import rx.Subscriber;

/* loaded from: classes.dex */
public class RechargePresenter implements Presenter {
    private RechargeView mPageView;
    private RechargePayOrderCae mrechargePayOrderCae;
    private RechargePayTypeOrderCae rechargePayTypeOrderCae;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public RechargePresenter(RechargePayTypeOrderCae rechargePayTypeOrderCae, RechargePayOrderCae rechargePayOrderCae) {
        this.rechargePayTypeOrderCae = rechargePayTypeOrderCae;
        this.mrechargePayOrderCae = rechargePayOrderCae;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.rechargePayTypeOrderCae.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.mPageView = (RechargeView) interfaceView;
    }

    public void generateRecord(String str, String str2, String str3, String str4, String str5) {
        this.mrechargePayOrderCae.setOrderAmount(str);
        this.mrechargePayOrderCae.setCaleTotalAmount(str2);
        this.mrechargePayOrderCae.setOutUsePay(str3);
        this.mrechargePayOrderCae.setBankCode(str4);
        this.mrechargePayOrderCae.setOutPayAmount(str5);
        this.mrechargePayOrderCae.execute(new Subscriber<LnvoicePayResp>() { // from class: com.yltx.oil.partner.modules.mine.presenter.RechargePresenter.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                RechargePresenter.this.mPageView.onLoadingComplete();
                RechargePresenter.this.mPageView.onPayTypeError(th);
            }

            @Override // rx.Observer
            public void onNext(LnvoicePayResp lnvoicePayResp) {
                RechargePresenter.this.mPageView.onLoadingComplete();
                RechargePresenter.this.mPageView.onPaySuccess(lnvoicePayResp);
            }
        });
    }

    public void getOutPayTypeList() {
        this.rechargePayTypeOrderCae.execute(new Subscriber<RechargePayTypeResp>() { // from class: com.yltx.oil.partner.modules.mine.presenter.RechargePresenter.2
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                RechargePresenter.this.mPageView.onLoadingComplete();
                RechargePresenter.this.mPageView.onPayTypeError(th);
            }

            @Override // rx.Observer
            public void onNext(RechargePayTypeResp rechargePayTypeResp) {
                RechargePresenter.this.mPageView.onLoadingComplete();
                RechargePresenter.this.mPageView.onPayTypeSuccess(rechargePayTypeResp);
            }
        });
    }
}