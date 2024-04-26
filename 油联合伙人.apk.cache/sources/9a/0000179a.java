package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MessageNotificationUseCase_Factory implements Factory<MessageNotificationUseCase> {
    private final Provider<Repository> repositoryProvider;

    public MessageNotificationUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public MessageNotificationUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static MessageNotificationUseCase provideInstance(Provider<Repository> provider) {
        return new MessageNotificationUseCase(provider.get());
    }

    public static MessageNotificationUseCase_Factory create(Provider<Repository> provider) {
        return new MessageNotificationUseCase_Factory(provider);
    }

    public static MessageNotificationUseCase newMessageNotificationUseCase(Repository repository) {
        return new MessageNotificationUseCase(repository);
    }
}