package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class BindingbankcardsUseCase extends UseCase<HttpResult<String>> {
    private String bankNo;
    private String bankPhone;
    private String idcard;
    private Repository mRepository;
    private String realname;
    private String validCode;

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String str) {
        this.realname = str;
    }

    public String getIdcard() {
        return this.idcard;
    }

    public void setIdcard(String str) {
        this.idcard = str;
    }

    public String getBankNo() {
        return this.bankNo;
    }

    public void setBankNo(String str) {
        this.bankNo = str;
    }

    public String getBankPhone() {
        return this.bankPhone;
    }

    public void setBankPhone(String str) {
        this.bankPhone = str;
    }

    public String getValidCode() {
        return this.validCode;
    }

    public void setValidCode(String str) {
        this.validCode = str;
    }

    @Inject
    public BindingbankcardsUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<String>> buildObservable() {
        return this.mRepository.bindBankCard(this.realname, this.idcard, this.bankNo, this.bankPhone, this.validCode);
    }
}