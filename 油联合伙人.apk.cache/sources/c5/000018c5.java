package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class MineinfoOldPhoneUseCase extends UseCase<HttpResult<String>> {
    private Repository mRepository;
    String phone;
    String validCode;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getValidCode() {
        return this.validCode;
    }

    public void setValidCode(String str) {
        this.validCode = str;
    }

    @Inject
    public MineinfoOldPhoneUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<String>> buildObservable() {
        return this.mRepository.checkValidCode(this.phone, this.validCode);
    }
}