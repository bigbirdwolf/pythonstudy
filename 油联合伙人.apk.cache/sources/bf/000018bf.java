package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.InviteDetailResp;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class DetailUseCase extends UseCase<HttpResult<InviteDetailResp>> {
    private Repository mRepository;
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    @Inject
    public DetailUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<InviteDetailResp>> buildObservable() {
        return this.mRepository.getDetail(this.userId);
    }
}