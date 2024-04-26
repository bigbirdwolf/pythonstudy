package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.home.response.MessageForDetailsResponse;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class MessageForDetailsUseCase extends UseCase<HttpResult<MessageForDetailsResponse>> {
    private Repository mRepository;
    private int rowid;

    public int getRowid() {
        return this.rowid;
    }

    public void setRowid(int i) {
        this.rowid = i;
    }

    @Inject
    public MessageForDetailsUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<MessageForDetailsResponse>> buildObservable() {
        return this.mRepository.DetailsList(this.rowid);
    }
}