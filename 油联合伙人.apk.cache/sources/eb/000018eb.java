package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.ComplaintOrderUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ComplaintOrderPresenter_Factory implements Factory<ComplaintOrderPresenter> {
    private final Provider<ComplaintOrderUseCase> mfProvider;

    public ComplaintOrderPresenter_Factory(Provider<ComplaintOrderUseCase> provider) {
        this.mfProvider = provider;
    }

    @Override // javax.inject.Provider
    public ComplaintOrderPresenter get() {
        return provideInstance(this.mfProvider);
    }

    public static ComplaintOrderPresenter provideInstance(Provider<ComplaintOrderUseCase> provider) {
        return new ComplaintOrderPresenter(provider.get());
    }

    public static ComplaintOrderPresenter_Factory create(Provider<ComplaintOrderUseCase> provider) {
        return new ComplaintOrderPresenter_Factory(provider);
    }

    public static ComplaintOrderPresenter newComplaintOrderPresenter(ComplaintOrderUseCase complaintOrderUseCase) {
        return new ComplaintOrderPresenter(complaintOrderUseCase);
    }
}