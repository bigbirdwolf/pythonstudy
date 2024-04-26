package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class SettlementRecordsUseCase extends PageLimitUseCase<ManageResponse> {
    private Repository mRepository;

    @Inject
    public SettlementRecordsUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<ManageResponse> buildObservable() {
        return this.mRepository.yzskjlList(pageOffset());
    }
}