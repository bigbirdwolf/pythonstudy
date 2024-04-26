package com.jakewharton.rxbinding.internal;

/* loaded from: classes.dex */
public final class Preconditions {
    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}