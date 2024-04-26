package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class PersonalCenterUseCase extends UseCase<HttpResult<LoginInfo>> {
    private Repository mRepository;

    @Inject
    public PersonalCenterUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<LoginInfo>> buildObservable() {
        return this.mRepository.partnerLogin();
    }
}