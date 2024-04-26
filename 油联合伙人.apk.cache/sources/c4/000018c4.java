package com.yltx.oil.partner.modules.mine.domain;

import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.oss.OSSFileHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MineinfoHeadPicUseCase_Factory implements Factory<MineinfoHeadPicUseCase> {
    private final Provider<OSSFileHelper> ossFileHelperProvider;
    private final Provider<Repository> repositoryProvider;

    public MineinfoHeadPicUseCase_Factory(Provider<Repository> provider, Provider<OSSFileHelper> provider2) {
        this.repositoryProvider = provider;
        this.ossFileHelperProvider = provider2;
    }

    @Override // javax.inject.Provider
    public MineinfoHeadPicUseCase get() {
        return provideInstance(this.repositoryProvider, this.ossFileHelperProvider);
    }

    public static MineinfoHeadPicUseCase provideInstance(Provider<Repository> provider, Provider<OSSFileHelper> provider2) {
        return new MineinfoHeadPicUseCase(provider.get(), provider2.get());
    }

    public static MineinfoHeadPicUseCase_Factory create(Provider<Repository> provider, Provider<OSSFileHelper> provider2) {
        return new MineinfoHeadPicUseCase_Factory(provider, provider2);
    }

    public static MineinfoHeadPicUseCase newMineinfoHeadPicUseCase(Repository repository, OSSFileHelper oSSFileHelper) {
        return new MineinfoHeadPicUseCase(repository, oSSFileHelper);
    }
}