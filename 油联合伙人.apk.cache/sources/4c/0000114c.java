package com.jakewharton.rxbinding.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

/* loaded from: classes.dex */
public final class ViewScrollChangeEvent extends ViewEvent<View> {
    private final int oldScrollX;
    private final int oldScrollY;
    private final int scrollX;
    private final int scrollY;

    @CheckResult
    @NonNull
    public static ViewScrollChangeEvent create(@NonNull View view, int i, int i2, int i3, int i4) {
        return new ViewScrollChangeEvent(view, i, i2, i3, i4);
    }

    protected ViewScrollChangeEvent(@NonNull View view, int i, int i2, int i3, int i4) {
        super(view);
        this.scrollX = i;
        this.scrollY = i2;
        this.oldScrollX = i3;
        this.oldScrollY = i4;
    }

    public int scrollX() {
        return this.scrollX;
    }

    public int scrollY() {
        return this.scrollY;
    }

    public int oldScrollX() {
        return this.oldScrollX;
    }

    public int oldScrollY() {
        return this.oldScrollY;
    }

    public int hashCode() {
        return ((((((((629 + view().hashCode()) * 37) + this.scrollX) * 37) + this.scrollY) * 37) + this.oldScrollX) * 37) + this.oldScrollY;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewScrollChangeEvent) {
            ViewScrollChangeEvent viewScrollChangeEvent = (ViewScrollChangeEvent) obj;
            return viewScrollChangeEvent.view() == view() && viewScrollChangeEvent.scrollX == this.scrollX && viewScrollChangeEvent.scrollY == this.scrollY && viewScrollChangeEvent.oldScrollX == this.oldScrollX && viewScrollChangeEvent.oldScrollY == this.oldScrollY;
        }
        return false;
    }

    public String toString() {
        return "ViewScrollChangeEvent{scrollX=" + this.scrollX + ", scrollY=" + this.scrollY + ", oldScrollX=" + this.oldScrollX + ", oldScrollY=" + this.oldScrollY + '}';
    }
}