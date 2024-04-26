package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.StoredValueCardResp;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class StoredValueCardUseCase extends PageLimitUseCase<List<StoredValueCardResp>> {
    private Repository mRepository;

    @Inject
    public StoredValueCardUseCase(Repository repository) {
        this.mRepository = repository;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    @Inject
    public Observable<List<StoredValueCardResp>> buildObservable() {
        return this.mRepository.getSotredCardList(pageOffset());
    }
}