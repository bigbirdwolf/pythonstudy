package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class ComplaintUseCase extends UseCase<HttpResult<String>> {
    private String context;
    private Repository mRepository;
    private String name;
    private String phone;
    private String reason;
    private String url;
    private String voucherCode;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String str) {
        this.context = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getVoucherCode() {
        return this.voucherCode;
    }

    public void setVoucherCode(String str) {
        this.voucherCode = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    @Inject
    public ComplaintUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<String>> buildObservable() {
        return this.mRepository.complaint(this.phone, this.context, this.reason, this.url, this.voucherCode, this.name);
    }
}