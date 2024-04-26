package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.TxHistoryResp;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class GetTxHistoryListUseCase extends PageLimitUseCase<TxHistoryResp> {
    private Repository repository;

    @Inject
    public GetTxHistoryListUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<TxHistoryResp> buildObservable() {
        return this.repository.getTxList(pageOffset());
    }
}