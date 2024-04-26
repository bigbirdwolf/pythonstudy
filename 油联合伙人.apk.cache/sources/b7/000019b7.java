package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.profit.response.CommissionResponse;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class CommissionUseCase extends UseCase<HttpResult<CommissionResponse>> {
    private String button;
    private Repository mRepository;

    public String getButton() {
        return this.button;
    }

    public void setButton(String str) {
        this.button = str;
    }

    @Inject
    public CommissionUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<CommissionResponse>> buildObservable() {
        return this.mRepository.CommissionList(this.button);
    }
}