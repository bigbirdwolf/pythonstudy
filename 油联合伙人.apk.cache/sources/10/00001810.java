package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class RegisterUseCase extends UseCase<HttpResult<LoginInfo>> {
    private Repository mRepository;
    private String password;
    private String phone;
    private String validCode;

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    @Inject
    public RegisterUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<LoginInfo>> buildObservable() {
        return this.mRepository.regist(this.phone, this.validCode, this.password);
    }
}