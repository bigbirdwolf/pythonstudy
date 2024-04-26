package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.FinanceCardlResp;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class FinanceCardUseCase extends PageLimitUseCase<List<FinanceCardlResp>> {
    private Repository mRepository;

    @Inject
    public FinanceCardUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<List<FinanceCardlResp>> buildObservable() {
        return this.mRepository.financecardList(pageOffset());
    }
}