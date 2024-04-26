package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.Homebuttonconfiguration_Bean;
import com.yltx.oil.partner.mvp.domain.UseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class HomeButtonconfigurationUseCase extends UseCase<HttpResult<List<Homebuttonconfiguration_Bean>>> {
    private Repository mRepository;

    @Inject
    public HomeButtonconfigurationUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<List<Homebuttonconfiguration_Bean>>> buildObservable() {
        return this.mRepository.getInfoList();
    }
}