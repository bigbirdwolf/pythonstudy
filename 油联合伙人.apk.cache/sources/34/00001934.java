package com.yltx.oil.partner.modules.oiltrade.domain;

import com.yltx.oil.partner.data.response.ShopResp;
import dagger.MembersInjector;
import java.util.List;
import rx.Observable;

/* loaded from: classes.dex */
public final class ShopUseCase_MembersInjector implements MembersInjector<ShopUseCase> {
    public static MembersInjector<ShopUseCase> create() {
        return new ShopUseCase_MembersInjector();
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ShopUseCase shopUseCase) {
        injectBuildObservable(shopUseCase);
    }

    public static Observable<List<ShopResp>> injectBuildObservable(ShopUseCase shopUseCase) {
        return shopUseCase.buildObservable();
    }
}