package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.profit.response.UserAccountConsumeResponse;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class UserAccountUseCase extends PageLimitUseCase<UserAccountConsumeResponse> {
    private Repository mRepository;

    @Inject
    public UserAccountUseCase(Repository repository) {
        this.mRepository = repository;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    @Inject
    public Observable<UserAccountConsumeResponse> buildObservable() {
        return this.mRepository.userAccountConsumeList(pageOffset());
    }
}