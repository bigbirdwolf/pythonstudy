package com.yltx.oil.partner.modules.home.presenter;

import com.yltx.oil.partner.modules.home.domain.MessageNotificationUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MessageNotificationPresenter_Factory implements Factory<MessageNotificationPresenter> {
    private final Provider<MessageNotificationUseCase> messageNotificationUseCaseProvider;

    public MessageNotificationPresenter_Factory(Provider<MessageNotificationUseCase> provider) {
        this.messageNotificationUseCaseProvider = provider;
    }

    @Override // javax.inject.Provider
    public MessageNotificationPresenter get() {
        return provideInstance(this.messageNotificationUseCaseProvider);
    }

    public static MessageNotificationPresenter provideInstance(Provider<MessageNotificationUseCase> provider) {
        return new MessageNotificationPresenter(provider.get());
    }

    public static MessageNotificationPresenter_Factory create(Provider<MessageNotificationUseCase> provider) {
        return new MessageNotificationPresenter_Factory(provider);
    }

    public static MessageNotificationPresenter newMessageNotificationPresenter(MessageNotificationUseCase messageNotificationUseCase) {
        return new MessageNotificationPresenter(messageNotificationUseCase);
    }
}