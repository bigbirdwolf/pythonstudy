package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class TextViewTextChangeEvent extends ViewEvent<TextView> {
    private final int before;
    private final int count;
    private final int start;
    private final CharSequence text;

    @CheckResult
    @NonNull
    public static TextViewTextChangeEvent create(@NonNull TextView textView, @NonNull CharSequence charSequence, int i, int i2, int i3) {
        return new TextViewTextChangeEvent(textView, charSequence, i, i2, i3);
    }

    private TextViewTextChangeEvent(@NonNull TextView textView, @NonNull CharSequence charSequence, int i, int i2, int i3) {
        super(textView);
        this.text = charSequence;
        this.start = i;
        this.before = i2;
        this.count = i3;
    }

    @NonNull
    public CharSequence text() {
        return this.text;
    }

    public int start() {
        return this.start;
    }

    public int before() {
        return this.before;
    }

    public int count() {
        return this.count;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextViewTextChangeEvent) {
            TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) obj;
            return textViewTextChangeEvent.view() == view() && this.text.equals(textViewTextChangeEvent.text) && this.start == textViewTextChangeEvent.start && this.before == textViewTextChangeEvent.before && this.count == textViewTextChangeEvent.count;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((629 + view().hashCode()) * 37) + this.text.hashCode()) * 37) + this.start) * 37) + this.before) * 37) + this.count;
    }

    public String toString() {
        return "TextViewTextChangeEvent{text=" + ((Object) this.text) + ", start=" + this.start + ", before=" + this.before + ", count=" + this.count + ", view=" + view() + '}';
    }
}