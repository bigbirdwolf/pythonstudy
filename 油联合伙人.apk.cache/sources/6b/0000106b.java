package com.jakewharton.rxbinding.internal;

import rx.functions.Func0;
import rx.functions.Func1;

/* loaded from: classes.dex */
public final class Functions {
    private static final Always<Boolean> ALWAYS_TRUE = new Always<>(true);
    public static final Func0<Boolean> FUNC0_ALWAYS_TRUE = ALWAYS_TRUE;
    public static final Func1<Object, Boolean> FUNC1_ALWAYS_TRUE = ALWAYS_TRUE;

    /* loaded from: classes.dex */
    private static final class Always<T> implements Func1<Object, T>, Func0<T> {
        private final T value;

        Always(T t) {
            this.value = t;
        }

        @Override // rx.functions.Func1
        public T call(Object obj) {
            return this.value;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public T call() {
            return this.value;
        }
    }

    private Functions() {
        throw new AssertionError("No instances.");
    }
}