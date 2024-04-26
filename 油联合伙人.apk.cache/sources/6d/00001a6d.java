package com.yltx.oil.partner.navigation;

import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class Navigator_Factory implements Factory<Navigator> {
    private static final Navigator_Factory INSTANCE = new Navigator_Factory();

    @Override // javax.inject.Provider
    public Navigator get() {
        return provideInstance();
    }

    public static Navigator provideInstance() {
        return new Navigator();
    }

    public static Navigator_Factory create() {
        return INSTANCE;
    }

    public static Navigator newNavigator() {
        return new Navigator();
    }
}