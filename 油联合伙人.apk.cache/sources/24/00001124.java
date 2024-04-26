package com.jakewharton.rxbinding.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

/* loaded from: classes.dex */
public final class ViewAttachEvent extends ViewEvent<View> {
    private final Kind kind;

    /* loaded from: classes.dex */
    public enum Kind {
        ATTACH,
        DETACH
    }

    @CheckResult
    @NonNull
    public static ViewAttachEvent create(@NonNull View view, @NonNull Kind kind) {
        return new ViewAttachEvent(view, kind);
    }

    private ViewAttachEvent(@NonNull View view, @NonNull Kind kind) {
        super(view);
        this.kind = kind;
    }

    @NonNull
    public Kind kind() {
        return this.kind;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewAttachEvent) {
            ViewAttachEvent viewAttachEvent = (ViewAttachEvent) obj;
            return viewAttachEvent.view() == view() && viewAttachEvent.kind() == kind();
        }
        return false;
    }

    public int hashCode() {
        return ((629 + view().hashCode()) * 37) + kind().hashCode();
    }

    public String toString() {
        return "ViewAttachEvent{view=" + view() + ", kind=" + kind() + '}';
    }
}