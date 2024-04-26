package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.home.response.MessageNotificationResponse;
import com.yltx.oil.partner.mvp.domain.UseCase;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class MessageNotificationUseCase extends UseCase<HttpResult<List<MessageNotificationResponse>>> {
    private Repository mRepository;
    private int pageNo;

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int i) {
        this.pageNo = i;
    }

    @Inject
    public MessageNotificationUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<List<MessageNotificationResponse>>> buildObservable() {
        return this.mRepository.MessageList(this.pageNo);
    }
}