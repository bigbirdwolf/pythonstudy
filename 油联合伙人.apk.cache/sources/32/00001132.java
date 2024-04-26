package com.jakewharton.rxbinding.view;

import android.support.annotation.NonNull;
import android.view.View;
import com.jakewharton.rxbinding.internal.Preconditions;

/* loaded from: classes.dex */
public abstract class ViewEvent<T extends View> {
    private final T view;

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewEvent(@NonNull T t) {
        this.view = (T) Preconditions.checkNotNull(t, "view == null");
    }

    @NonNull
    public T view() {
        return this.view;
    }
}