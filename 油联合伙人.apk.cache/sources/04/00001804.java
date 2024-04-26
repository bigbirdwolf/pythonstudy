package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class AutoUseCase extends UseCase<HttpResult<LoginInfo>> {
    private Repository mRepository;
    private String token;
    private String userid;

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String str) {
        this.userid = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    @Inject
    public AutoUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<LoginInfo>> buildObservable() {
        return this.mRepository.auto(this.token, this.userid);
    }
}