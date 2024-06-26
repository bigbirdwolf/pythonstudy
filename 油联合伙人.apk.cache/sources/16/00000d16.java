package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.Descriptor;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public final class DescriptorMap {
    private Descriptor.Host mHost;
    private boolean mIsInitializing;
    private final Map<Class<?>, Descriptor> mMap = new IdentityHashMap();

    public DescriptorMap beginInit() {
        Util.throwIf(this.mIsInitializing);
        this.mIsInitializing = true;
        return this;
    }

    public DescriptorMap register(Class<?> cls, Descriptor descriptor) {
        Util.throwIfNull(cls);
        Util.throwIfNull(descriptor);
        Util.throwIf(descriptor.isInitialized());
        Util.throwIfNot(this.mIsInitializing);
        if (this.mMap.containsKey(cls)) {
            throw new UnsupportedOperationException();
        }
        if (this.mMap.containsValue(descriptor)) {
            throw new UnsupportedOperationException();
        }
        this.mMap.put(cls, descriptor);
        return this;
    }

    public DescriptorMap setHost(Descriptor.Host host) {
        Util.throwIfNull(host);
        Util.throwIfNot(this.mIsInitializing);
        Util.throwIfNotNull(this.mHost);
        this.mHost = host;
        return this;
    }

    public DescriptorMap endInit() {
        Util.throwIfNot(this.mIsInitializing);
        Util.throwIfNull(this.mHost);
        this.mIsInitializing = false;
        for (Class<?> cls : this.mMap.keySet()) {
            Descriptor descriptor = this.mMap.get(cls);
            if (descriptor instanceof ChainedDescriptor) {
                ((ChainedDescriptor) descriptor).setSuper(getImpl(cls.getSuperclass()));
            }
            descriptor.initialize(this.mHost);
        }
        return this;
    }

    @Nullable
    public Descriptor get(Class<?> cls) {
        Util.throwIfNull(cls);
        Util.throwIf(this.mIsInitializing);
        return getImpl(cls);
    }

    @Nullable
    private Descriptor getImpl(Class<?> cls) {
        while (cls != null) {
            Descriptor descriptor = this.mMap.get(cls);
            if (descriptor != null) {
                return descriptor;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }
}