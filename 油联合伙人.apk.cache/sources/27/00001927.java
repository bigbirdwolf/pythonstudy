package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.oiltrade.response.FuelCardListResponse;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class FuelCardListUseCase extends PageLimitUseCase<List<FuelCardListResponse>> {
    private Repository mRepository;

    @Inject
    public FuelCardListUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<List<FuelCardListResponse>> buildObservable() {
        return this.mRepository.FuelList(pageOffset());
    }
}