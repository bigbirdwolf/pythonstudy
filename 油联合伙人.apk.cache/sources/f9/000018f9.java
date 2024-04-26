package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.modules.mine.domain.MemberUseCase;
import com.yltx.oil.partner.modules.mine.domain.PersonalCenterUseCase;
import com.yltx.oil.partner.modules.mine.domain.PhoneUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MemberPresenter_Factory implements Factory<MemberPresenter> {
    private final Provider<MemberUseCase> memberUseCaseProvider;
    private final Provider<PersonalCenterUseCase> personalCenterUseCaseProvider;
    private final Provider<PhoneUseCase> phoneUseCaseProvider;

    public MemberPresenter_Factory(Provider<MemberUseCase> provider, Provider<PhoneUseCase> provider2, Provider<PersonalCenterUseCase> provider3) {
        this.memberUseCaseProvider = provider;
        this.phoneUseCaseProvider = provider2;
        this.personalCenterUseCaseProvider = provider3;
    }

    @Override // javax.inject.Provider
    public MemberPresenter get() {
        return provideInstance(this.memberUseCaseProvider, this.phoneUseCaseProvider, this.personalCenterUseCaseProvider);
    }

    public static MemberPresenter provideInstance(Provider<MemberUseCase> provider, Provider<PhoneUseCase> provider2, Provider<PersonalCenterUseCase> provider3) {
        return new MemberPresenter(provider.get(), provider2.get(), provider3.get());
    }

    public static MemberPresenter_Factory create(Provider<MemberUseCase> provider, Provider<PhoneUseCase> provider2, Provider<PersonalCenterUseCase> provider3) {
        return new MemberPresenter_Factory(provider, provider2, provider3);
    }

    public static MemberPresenter newMemberPresenter(MemberUseCase memberUseCase, PhoneUseCase phoneUseCase, PersonalCenterUseCase personalCenterUseCase) {
        return new MemberPresenter(memberUseCase, phoneUseCase, personalCenterUseCase);
    }
}