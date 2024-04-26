package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.response.ShopResp;
import dagger.MembersInjector;
import java.util.List;
import rx.Observable;

/* loaded from: classes.dex */
public final class ShopRecommendUseCase_MembersInjector implements MembersInjector<ShopRecommendUseCase> {
    public static MembersInjector<ShopRecommendUseCase> create() {
        return new ShopRecommendUseCase_MembersInjector();
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ShopRecommendUseCase shopRecommendUseCase) {
        injectBuildObservable(shopRecommendUseCase);
    }

    public static Observable<List<ShopResp>> injectBuildObservable(ShopRecommendUseCase shopRecommendUseCase) {
        return shopRecommendUseCase.buildObservable();
    }
}