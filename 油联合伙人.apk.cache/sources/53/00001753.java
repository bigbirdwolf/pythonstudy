package com.yltx.oil.partner.injections.modules;

import com.yltx.oil.partner.navigation.Navigator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class GlobalModule_ProvideNavigatorFactory implements Factory<Navigator> {
    private final GlobalModule module;

    public GlobalModule_ProvideNavigatorFactory(GlobalModule globalModule) {
        this.module = globalModule;
    }

    @Override // javax.inject.Provider
    public Navigator get() {
        return provideInstance(this.module);
    }

    public static Navigator provideInstance(GlobalModule globalModule) {
        return proxyProvideNavigator(globalModule);
    }

    public static GlobalModule_ProvideNavigatorFactory create(GlobalModule globalModule) {
        return new GlobalModule_ProvideNavigatorFactory(globalModule);
    }

    public static Navigator proxyProvideNavigator(GlobalModule globalModule) {
        return (Navigator) Preconditions.checkNotNull(globalModule.provideNavigator(), "Cannot return null from a non-@Nullable @Provides method");
    }
}