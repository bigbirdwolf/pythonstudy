package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class ManagerYZJYFXUseCase extends UseCase<HttpResult<ManageResponse>> {
    private Repository mRepository;
    private String pageNo;

    public String getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(String str) {
        this.pageNo = str;
    }

    @Inject
    public ManagerYZJYFXUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<ManageResponse>> buildObservable() {
        return this.mRepository.yzjyfx(this.pageNo);
    }
}