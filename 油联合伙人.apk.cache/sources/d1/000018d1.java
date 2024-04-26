package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.PhoneResp;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class PhoneUseCase extends UseCase<HttpResult<PhoneResp>> {
    private Repository mRepository;

    @Inject
    public PhoneUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<PhoneResp>> buildObservable() {
        return this.mRepository.getPhone();
    }
}