package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.RechargePayTypeResp;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class RechargePayTypeOrderCae extends UseCase<RechargePayTypeResp> {
    private Repository repository;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public RechargePayTypeOrderCae(Repository repository) {
        this.repository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<RechargePayTypeResp> buildObservable() {
        return this.repository.getOutPayTypeList("app");
    }
}