package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.home.response.SeekResponse;
import com.yltx.oil.partner.mvp.domain.UseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class SeekUseCase extends UseCase<List<SeekResponse>> {
    private String goodsName;
    private Repository mRepository;

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String str) {
        this.goodsName = str;
    }

    @Inject
    public SeekUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<List<SeekResponse>> buildObservable() {
        return this.mRepository.SSList(this.goodsName);
    }
}