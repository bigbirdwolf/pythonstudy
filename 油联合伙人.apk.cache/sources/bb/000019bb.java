package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.modules.profit.response.GeneralizeResponse;
import com.yltx.oil.partner.mvp.domain.PageLimitUseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class GeneralizeUseCase extends PageLimitUseCase<GeneralizeResponse> {
    private String beginTime;
    private String endTime;
    private Repository mRepository;

    public String getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(String str) {
        this.beginTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    @Inject
    public GeneralizeUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<GeneralizeResponse> buildObservable() {
        return this.mRepository.GeneralizeList(this.beginTime, this.endTime, pageOffset());
    }
}