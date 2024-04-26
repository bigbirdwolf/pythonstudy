package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.AppLoginStatusResp;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class AppLoginStatusUseCase extends UseCase<HttpResult<AppLoginStatusResp>> {
    private Repository mRepository;

    @Inject
    public AppLoginStatusUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<AppLoginStatusResp>> buildObservable() {
        return this.mRepository.AppLoginStatus();
    }
}