package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.GiftCardResp;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class GiftCardCardUseCase extends PageLimitUseCase<List<GiftCardResp>> {
    private Repository mRepository;

    @Inject
    public GiftCardCardUseCase(Repository repository) {
        this.mRepository = repository;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    @Inject
    public Observable<List<GiftCardResp>> buildObservable() {
        return this.mRepository.getRechargeList(pageOffset());
    }
}