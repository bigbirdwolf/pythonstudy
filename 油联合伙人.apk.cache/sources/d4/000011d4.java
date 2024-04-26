package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class TextViewBeforeTextChangeEvent extends ViewEvent<TextView> {
    private final int after;
    private final int count;
    private final int start;
    private final CharSequence text;

    @CheckResult
    @NonNull
    public static TextViewBeforeTextChangeEvent create(@NonNull TextView textView, @NonNull CharSequence charSequence, int i, int i2, int i3) {
        return new TextViewBeforeTextChangeEvent(textView, charSequence, i, i2, i3);
    }

    private TextViewBeforeTextChangeEvent(@NonNull TextView textView, @NonNull CharSequence charSequence, int i, int i2, int i3) {
        super(textView);
        this.text = charSequence;
        this.start = i;
        this.count = i2;
        this.after = i3;
    }

    @NonNull
    public CharSequence text() {
        return this.text;
    }

    public int start() {
        return this.start;
    }

    public int count() {
        return this.count;
    }

    public int after() {
        return this.after;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextViewAfterTextChangeEvent) {
            TextViewBeforeTextChangeEvent textViewBeforeTextChangeEvent = (TextViewBeforeTextChangeEvent) obj;
            return textViewBeforeTextChangeEvent.view() == view() && this.text.equals(textViewBeforeTextChangeEvent.text) && this.start == textViewBeforeTextChangeEvent.start && this.count == textViewBeforeTextChangeEvent.count && this.after == textViewBeforeTextChangeEvent.after;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((629 + view().hashCode()) * 37) + this.text.hashCode()) * 37) + this.start) * 37) + this.count) * 37) + this.after;
    }

    public String toString() {
        return "TextViewBeforeTextChangeEvent{text=" + ((Object) this.text) + ", start=" + this.start + ", count=" + this.count + ", after=" + this.after + ", view=" + view() + '}';
    }
}