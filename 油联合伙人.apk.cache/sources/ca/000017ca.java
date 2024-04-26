package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.SeekUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SeekPresenter_Factory implements Factory<SeekPresenter> {
    private final Provider<SeekUseCase> seekUseCaseProvider;

    public SeekPresenter_Factory(Provider<SeekUseCase> provider) {
        this.seekUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public SeekPresenter get() {
        return provideInstance(this.seekUseCaseProvider);
    }

    public static SeekPresenter provideInstance(Provider<SeekUseCase> provider) {
        return new SeekPresenter(provider.get());
    }

    public static SeekPresenter_Factory create(Provider<SeekUseCase> provider) {
        return new SeekPresenter_Factory(provider);
    }

    public static SeekPresenter newSeekPresenter(SeekUseCase seekUseCase) {
        return new SeekPresenter(seekUseCase);
    }
}