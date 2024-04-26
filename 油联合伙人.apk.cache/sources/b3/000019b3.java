package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.profit.response.AllordersResponse;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class AllordersUseCase extends PageLimitUseCase<AllordersResponse> {
    private Repository mRepository;
    private String status;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    @Inject
    public AllordersUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<AllordersResponse> buildObservable() {
        return this.mRepository.AllordersList(this.type, this.status, pageOffset());
    }
}