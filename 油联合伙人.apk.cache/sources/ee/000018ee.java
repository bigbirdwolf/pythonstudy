package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.ComplaintUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ComplaintPresenter_Factory implements Factory<ComplaintPresenter> {
    private final Provider<ComplaintUseCase> complaintUseCaseProvider;

    public ComplaintPresenter_Factory(Provider<ComplaintUseCase> provider) {
        this.complaintUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public ComplaintPresenter get() {
        return provideInstance(this.complaintUseCaseProvider);
    }

    public static ComplaintPresenter provideInstance(Provider<ComplaintUseCase> provider) {
        return new ComplaintPresenter(provider.get());
    }

    public static ComplaintPresenter_Factory create(Provider<ComplaintUseCase> provider) {
        return new ComplaintPresenter_Factory(provider);
    }

    public static ComplaintPresenter newComplaintPresenter(ComplaintUseCase complaintUseCase) {
        return new ComplaintPresenter(complaintUseCase);
    }
}