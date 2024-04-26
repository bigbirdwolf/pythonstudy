package com.jakewharton.rxbinding.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

/* loaded from: classes.dex */
public final class ViewLayoutChangeEvent extends ViewEvent<View> {
    private final int bottom;
    private final int left;
    private final int oldBottom;
    private final int oldLeft;
    private final int oldRight;
    private final int oldTop;
    private final int right;
    private final int top;

    @CheckResult
    @NonNull
    public static ViewLayoutChangeEvent create(@NonNull View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return new ViewLayoutChangeEvent(view, i, i2, i3, i4, i5, i6, i7, i8);
    }

    private ViewLayoutChangeEvent(@NonNull View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        super(view);
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
        this.oldLeft = i5;
        this.oldTop = i6;
        this.oldRight = i7;
        this.oldBottom = i8;
    }

    public int left() {
        return this.left;
    }

    public int top() {
        return this.top;
    }

    public int right() {
        return this.right;
    }

    public int bottom() {
        return this.bottom;
    }

    public int oldLeft() {
        return this.oldLeft;
    }

    public int oldTop() {
        return this.oldTop;
    }

    public int oldRight() {
        return this.oldRight;
    }

    public int oldBottom() {
        return this.oldBottom;
    }

    public int hashCode() {
        return ((((((((((((((((629 + view().hashCode()) * 37) + this.left) * 37) + this.top) * 37) + this.right) * 37) + this.bottom) * 37) + this.oldLeft) * 37) + this.oldTop) * 37) + this.oldRight) * 37) + this.oldBottom;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewLayoutChangeEvent) {
            ViewLayoutChangeEvent viewLayoutChangeEvent = (ViewLayoutChangeEvent) obj;
            return viewLayoutChangeEvent.view() == view() && viewLayoutChangeEvent.left == this.left && viewLayoutChangeEvent.top == this.top && viewLayoutChangeEvent.right == this.right && viewLayoutChangeEvent.bottom == this.bottom && viewLayoutChangeEvent.oldLeft == this.oldLeft && viewLayoutChangeEvent.oldTop == this.oldTop && viewLayoutChangeEvent.oldRight == this.oldRight && viewLayoutChangeEvent.oldBottom == this.oldBottom;
        }
        return false;
    }

    public String toString() {
        return "ViewLayoutChangeEvent{left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ", oldLeft=" + this.oldLeft + ", oldTop=" + this.oldTop + ", oldRight=" + this.oldRight + ", oldBottom=" + this.oldBottom + '}';
    }
}