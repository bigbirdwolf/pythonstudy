package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.response.StoredValueCardResp;
import dagger.MembersInjector;
import java.util.List;
import rx.Observable;

/* loaded from: classes.dex */
public final class StoredValueCardUseCase_MembersInjector implements MembersInjector<StoredValueCardUseCase> {
    public static MembersInjector<StoredValueCardUseCase> create() {
        return new StoredValueCardUseCase_MembersInjector();
    }

    @Override // dagger.MembersInjector
    public void injectMembers(StoredValueCardUseCase storedValueCardUseCase) {
        injectBuildObservable(storedValueCardUseCase);
    }

    public static Observable<List<StoredValueCardResp>> injectBuildObservable(StoredValueCardUseCase storedValueCardUseCase) {
        return storedValueCardUseCase.buildObservable();
    }
}