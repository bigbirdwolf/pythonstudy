package com.yltx.oil.partner.modules.main.domian;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.data.response.VersionResponse;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class CheckVersionUseCase extends UseCase<VersionResponse> {
    private Repository mRepository;
    private String platform;

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    @Inject
    public CheckVersionUseCase(Repository repository) {
        this.mRepository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<VersionResponse> buildObservable() {
        return this.mRepository.versionCheck(this.platform);
    }
}