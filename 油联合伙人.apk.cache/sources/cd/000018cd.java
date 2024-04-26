package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.mine.response.MyfeedbackResponse;
import com.yltx.oil.partner.mvp.domain.UseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class MyfeedbackUseCase extends UseCase<HttpResult<List<MyfeedbackResponse>>> {
    private Repository mRepository;

    @Inject
    public MyfeedbackUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<List<MyfeedbackResponse>>> buildObservable() {
        return this.mRepository.myfeedbackCode();
    }
}