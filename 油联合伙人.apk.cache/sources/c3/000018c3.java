package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.mvp.domain.UseCase;
import com.yltx.oil.partner.oss.OSSFileHelper;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func1;

/* loaded from: classes.dex */
public class MineinfoHeadPicUseCase extends UseCase<HttpResult<String>> {
    private Repository mRepository;
    private OSSFileHelper ossFileHelper;
    String picUrl;

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String str) {
        this.picUrl = str;
    }

    @Inject
    public MineinfoHeadPicUseCase(Repository repository, OSSFileHelper oSSFileHelper) {
        this.mRepository = repository;
        this.ossFileHelper = oSSFileHelper;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<String>> buildObservable() {
        return this.ossFileHelper.asyncUpload(0, this.picUrl).flatMap(new Func1() { // from class: com.yltx.oil.partner.modules.mine.domain.-$$Lambda$MineinfoHeadPicUseCase$-Ep5bqGgVrFqgaJLtyRCDgTnThE
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable changeHeadPic;
                changeHeadPic = MineinfoHeadPicUseCase.this.mRepository.changeHeadPic((String) obj);
                return changeHeadPic;
            }
        });
    }
}