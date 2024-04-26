package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.MessageForDetailsUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MessageForDetailsPresenter_Factory implements Factory<MessageForDetailsPresenter> {
    private final Provider<MessageForDetailsUseCase> messageForDetailsUseCaseProvider;

    public MessageForDetailsPresenter_Factory(Provider<MessageForDetailsUseCase> provider) {
        this.messageForDetailsUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public MessageForDetailsPresenter get() {
        return provideInstance(this.messageForDetailsUseCaseProvider);
    }

    public static MessageForDetailsPresenter provideInstance(Provider<MessageForDetailsUseCase> provider) {
        return new MessageForDetailsPresenter(provider.get());
    }

    public static MessageForDetailsPresenter_Factory create(Provider<MessageForDetailsUseCase> provider) {
        return new MessageForDetailsPresenter_Factory(provider);
    }

    public static MessageForDetailsPresenter newMessageForDetailsPresenter(MessageForDetailsUseCase messageForDetailsUseCase) {
        return new MessageForDetailsPresenter(messageForDetailsUseCase);
    }
}