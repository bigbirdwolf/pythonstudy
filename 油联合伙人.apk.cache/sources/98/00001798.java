package com.yltx.oil.partner.modules.home.domain;

import com.yltx.oil.partner.data.repository.Repository;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MessageForDetailsUseCase_Factory implements Factory<MessageForDetailsUseCase> {
    private final Provider<Repository> repositoryProvider;

    public MessageForDetailsUseCase_Factory(Provider<Repository> provider) {
        this.repositoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public MessageForDetailsUseCase get() {
        return provideInstance(this.repositoryProvider);
    }

    public static MessageForDetailsUseCase provideInstance(Provider<Repository> provider) {
        return new MessageForDetailsUseCase(provider.get());
    }

    public static MessageForDetailsUseCase_Factory create(Provider<Repository> provider) {
        return new MessageForDetailsUseCase_Factory(provider);
    }

    public static MessageForDetailsUseCase newMessageForDetailsUseCase(Repository repository) {
        return new MessageForDetailsUseCase(repository);
    }
}