package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.home.response.SSLSResponse;
import com.yltx.oil.partner.mvp.domain.UseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class SSLSUseCase extends UseCase<HttpResult<List<SSLSResponse>>> {
    private Repository mRepository;

    @Inject
    public SSLSUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<List<SSLSResponse>>> buildObservable() {
        return this.mRepository.SSLSList();
    }
}