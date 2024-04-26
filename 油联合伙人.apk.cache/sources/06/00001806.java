package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class FindpwUseCase extends UseCase<HttpResult<String>> {
    private Repository mRepository;
    private String name;
    private String pwd;
    private String validCode;

    public String getValidCode() {
        return this.validCode;
    }

    public void setValidCode(String str) {
        this.validCode = str;
    }

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
    public FindpwUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<String>> buildObservable() {
        return this.mRepository.findpwdUpdate(this.name, this.validCode, this.pwd);
    }
}