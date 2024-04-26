package com.yltx.oil.partner.oss;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class OSSFileHelper_Factory implements Factory<OSSFileHelper> {
    private final Provider<Context> contextProvider;

    public OSSFileHelper_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public OSSFileHelper get() {
        return provideInstance(this.contextProvider);
    }

    public static OSSFileHelper provideInstance(Provider<Context> provider) {
        return new OSSFileHelper(provider.get());
    }

    public static OSSFileHelper_Factory create(Provider<Context> provider) {
        return new OSSFileHelper_Factory(provider);
    }

    public static OSSFileHelper newOSSFileHelper(Context context) {
        return new OSSFileHelper(context);
    }
}