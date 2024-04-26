package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.profit.response.FragmentProfit_yjjsjl_Response;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class FragmentProfit_yjjsjl_UseCase extends PageLimitUseCase<FragmentProfit_yjjsjl_Response> {
    private Repository mRepository;

    @Inject
    public FragmentProfit_yjjsjl_UseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<FragmentProfit_yjjsjl_Response> buildObservable() {
        return this.mRepository.yjjsjl(pageOffset());
    }
}