package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.modules.profit.response.UserAccountConsumeResponse;
import dagger.MembersInjector;
import rx.Observable;

/* loaded from: classes.dex */
public final class UserAccountUseCase_MembersInjector implements MembersInjector<UserAccountUseCase> {
    public static MembersInjector<UserAccountUseCase> create() {
        return new UserAccountUseCase_MembersInjector();
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UserAccountUseCase userAccountUseCase) {
        injectBuildObservable(userAccountUseCase);
    }

    public static Observable<UserAccountConsumeResponse> injectBuildObservable(UserAccountUseCase userAccountUseCase) {
        return userAccountUseCase.buildObservable();
    }
}