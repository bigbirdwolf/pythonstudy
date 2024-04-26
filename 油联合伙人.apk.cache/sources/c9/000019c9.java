package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.BankInfoResp;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class TxUseCase extends UseCase<HttpResult<BankInfoResp>> {
    private Repository repository;

    @Inject
    public TxUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<BankInfoResp>> buildObservable() {
        return this.repository.getBankCard();
    }
}