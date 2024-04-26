package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.home.response.SCResponse;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class SCUseCase extends UseCase<HttpResult<SCResponse>> {
    private Repository mRepository;

    @Inject
    public SCUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<SCResponse>> buildObservable() {
        return this.mRepository.SCList();
    }
}