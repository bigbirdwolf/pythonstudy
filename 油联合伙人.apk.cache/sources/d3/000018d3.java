package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.LnvoicePayResp;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class RechargePayOrderCae extends UseCase<LnvoicePayResp> {
    private String bankCode;
    private String caleTotalAmount;
    private String orderAmount;
    private String outPayAmount;
    private String outUsePay;
    private Repository repository;

    public String getOrderAmount() {
        return this.orderAmount;
    }

    public void setOrderAmount(String str) {
        this.orderAmount = str;
    }

    public String getCaleTotalAmount() {
        return this.caleTotalAmount;
    }

    public void setCaleTotalAmount(String str) {
        this.caleTotalAmount = str;
    }

    public String getOutUsePay() {
        return this.outUsePay;
    }

    public void setOutUsePay(String str) {
        this.outUsePay = str;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public void setBankCode(String str) {
        this.bankCode = str;
    }

    public String getOutPayAmount() {
        return this.outPayAmount;
    }

    public void setOutPayAmount(String str) {
        this.outPayAmount = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public RechargePayOrderCae(Repository repository) {
        this.repository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<LnvoicePayResp> buildObservable() {
        return this.repository.generateRecord(this.orderAmount, this.caleTotalAmount, this.outUsePay, this.bankCode, this.outPayAmount);
    }
}