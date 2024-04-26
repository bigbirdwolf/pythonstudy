package com.yltx.oil.partner.modules.login.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class GetImgCodeUseCase extends UseCase<String> {
    private Repository mRepository;

    @Inject
    public GetImgCodeUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<String> buildObservable() {
        return this.mRepository.getimgCode();
    }
}