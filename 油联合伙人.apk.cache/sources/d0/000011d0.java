package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.TextView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class TextViewAfterTextChangeEvent extends ViewEvent<TextView> {
    private final Editable editable;

    @CheckResult
    @NonNull
    public static TextViewAfterTextChangeEvent create(@NonNull TextView textView, @Nullable Editable editable) {
        return new TextViewAfterTextChangeEvent(textView, editable);
    }

    private TextViewAfterTextChangeEvent(@NonNull TextView textView, @Nullable Editable editable) {
        super(textView);
        this.editable = editable;
    }

    @Nullable
    public Editable editable() {
        return this.editable;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextViewAfterTextChangeEvent) {
            TextViewAfterTextChangeEvent textViewAfterTextChangeEvent = (TextViewAfterTextChangeEvent) obj;
            return textViewAfterTextChangeEvent.view() == view() && this.editable.equals(textViewAfterTextChangeEvent.editable);
        }
        return false;
    }

    public int hashCode() {
        return ((629 + view().hashCode()) * 37) + this.editable.hashCode();
    }

    public String toString() {
        return "TextViewAfterTextChangeEvent{editable=" + ((Object) this.editable) + ", view=" + view() + '}';
    }
}