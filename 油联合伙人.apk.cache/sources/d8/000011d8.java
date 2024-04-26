package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class TextViewEditorActionEvent extends ViewEvent<TextView> {
    private final int actionId;
    @Nullable
    private final KeyEvent keyEvent;

    @CheckResult
    @NonNull
    public static TextViewEditorActionEvent create(@NonNull TextView textView, int i, @Nullable KeyEvent keyEvent) {
        return new TextViewEditorActionEvent(textView, i, keyEvent);
    }

    private TextViewEditorActionEvent(@NonNull TextView textView, int i, @Nullable KeyEvent keyEvent) {
        super(textView);
        this.actionId = i;
        this.keyEvent = keyEvent;
    }

    public int actionId() {
        return this.actionId;
    }

    @Nullable
    public KeyEvent keyEvent() {
        return this.keyEvent;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextViewEditorActionEvent) {
            TextViewEditorActionEvent textViewEditorActionEvent = (TextViewEditorActionEvent) obj;
            if (textViewEditorActionEvent.view() == view() && textViewEditorActionEvent.actionId == this.actionId) {
                if (textViewEditorActionEvent.keyEvent != null) {
                    if (textViewEditorActionEvent.keyEvent.equals(this.keyEvent)) {
                        return true;
                    }
                } else if (this.keyEvent == null) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return ((((629 + view().hashCode()) * 37) + this.actionId) * 37) + (this.keyEvent != null ? this.keyEvent.hashCode() : 0);
    }

    public String toString() {
        return "TextViewEditorActionEvent{view=" + view() + ", actionId=" + this.actionId + ", keyEvent=" + this.keyEvent + '}';
    }
}