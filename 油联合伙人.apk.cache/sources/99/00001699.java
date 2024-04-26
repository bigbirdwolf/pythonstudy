package com.yltx.oil.partner.injections.instrumentation;

import android.support.annotation.NonNull;

/* loaded from: classes.dex */
public interface NetworkInstrumentation<T> extends Instrumentation {
    @NonNull
    T decorateNetwork(@NonNull T t);
}