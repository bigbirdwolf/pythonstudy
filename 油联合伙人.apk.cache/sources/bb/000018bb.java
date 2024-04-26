package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.mine.response.ComplaintResponse;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class ComplaintOrderUseCase extends PageLimitUseCase<List<ComplaintResponse>> {
    private Repository mRepository;
    private String status;
    private String type;
    private String voucherCode;

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
    public ComplaintOrderUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<List<ComplaintResponse>> buildObservable() {
        return this.mRepository.complaintOrder(this.type, this.status, this.voucherCode, pageOffset());
    }
}