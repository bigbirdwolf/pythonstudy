package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.SfResp;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class SFDetailUseCase extends UseCase<SfResp> {
    String goodsId;
    private Repository mRepository;
    String recommenderId;
    String type;

    public String getRecommenderId() {
        return this.recommenderId;
    }

    public void setRecommenderId(String str) {
        this.recommenderId = str;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String str) {
        this.goodsId = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    @Inject
    public SFDetailUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<SfResp> buildObservable() {
        return this.mRepository.getShareNum(this.recommenderId, this.goodsId, this.type);
    }
}