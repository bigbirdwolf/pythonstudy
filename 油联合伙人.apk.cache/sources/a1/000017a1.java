package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.ShopResp;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class ShopRecommendUseCase extends PageLimitUseCase<List<ShopResp>> {
    private Repository mRepository;

    @Inject
    public ShopRecommendUseCase(Repository repository) {
        this.mRepository = repository;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    @Inject
    public Observable<List<ShopResp>> buildObservable() {
        return this.mRepository.getShopRecommend(pageOffset());
    }
}