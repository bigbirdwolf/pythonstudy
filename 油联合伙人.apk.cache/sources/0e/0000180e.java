package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class LoginUseCase extends UseCase<HttpResult<LoginInfo>> {
    private Repository mRepository;
    private String name;
    private String pwd;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String str) {
        this.pwd = str;
    }

    @Inject
    public LoginUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<LoginInfo>> buildObservable() {
        return this.mRepository.loginWithToken(this.name, this.pwd);
    }
}