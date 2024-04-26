package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.response.GiftCardResp;
import dagger.MembersInjector;
import java.util.List;
import rx.Observable;

/* loaded from: classes.dex */
public final class GiftCardCardUseCase_MembersInjector implements MembersInjector<GiftCardCardUseCase> {
    public static MembersInjector<GiftCardCardUseCase> create() {
        return new GiftCardCardUseCase_MembersInjector();
    }

    @Override // dagger.MembersInjector
    public void injectMembers(GiftCardCardUseCase giftCardCardUseCase) {
        injectBuildObservable(giftCardCardUseCase);
    }

    public static Observable<List<GiftCardResp>> injectBuildObservable(GiftCardCardUseCase giftCardCardUseCase) {
        return giftCardCardUseCase.buildObservable();
    }
}