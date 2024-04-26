package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class GetvalidCodeUseCase extends UseCase<HttpResult<String>> {
    private String image;
    private String loginOrRegister;
    private Repository mRepository;
    private String name;

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getLoginOrRegister() {
        return this.loginOrRegister;
    }

    public void setLoginOrRegister(String str) {
        this.loginOrRegister = str;
    }

    @Inject
    public GetvalidCodeUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<String>> buildObservable() {
        return this.mRepository.validCode(this.name, this.loginOrRegister, this.image);
    }
}