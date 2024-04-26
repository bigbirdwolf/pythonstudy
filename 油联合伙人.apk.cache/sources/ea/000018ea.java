package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.ComplaintOrderUseCase;
import com.yltx.oil.partner.mvp.controller.PageLimitPresenter;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class ComplaintOrderPresenter extends PageLimitPresenter {
    private ComplaintOrderUseCase complaintOrderUseCase;
    private String status;
    private String type;
    private String voucherCode;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    public String getVoucherCode() {
        return this.voucherCode;
    }

    public void setVoucherCode(String str) {
        this.voucherCode = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    @Inject
    public ComplaintOrderPresenter(ComplaintOrderUseCase complaintOrderUseCase) {
        this.complaintOrderUseCase = complaintOrderUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.PageLimitPresenter
    protected UseCase buildPageUseCase(int i, int i2) {
        this.complaintOrderUseCase.setPageOffset(i);
        this.complaintOrderUseCase.setType(this.type);
        this.complaintOrderUseCase.setStatus(this.status);
        this.complaintOrderUseCase.setVoucherCode(this.voucherCode);
        return this.complaintOrderUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.complaintOrderUseCase.unSubscribe();
    }
}