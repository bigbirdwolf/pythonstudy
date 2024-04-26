package com.yltx.oil.partner.injections;

/* loaded from: classes.dex */
public final class Preconditions {
    private Preconditions() {
        throw new AssertionError("No instances");
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }
}