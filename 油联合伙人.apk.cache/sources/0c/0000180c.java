package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class LoginSmUseCase extends UseCase<HttpResult<LoginInfo>> {
    private Repository mRepository;
    private String name;
    private String validCode;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getValidCode() {
        return this.validCode;
    }

    public void setValidCode(String str) {
        this.validCode = str;
    }

    @Inject
    public LoginSmUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<LoginInfo>> buildObservable() {
        return this.mRepository.loginValidcode(this.name, this.validCode);
    }
}