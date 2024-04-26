package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* loaded from: classes.dex */
public final class SeekBarStartChangeEvent extends SeekBarChangeEvent {
    @CheckResult
    @NonNull
    public static SeekBarStartChangeEvent create(@NonNull SeekBar seekBar) {
        return new SeekBarStartChangeEvent(seekBar);
    }

    private SeekBarStartChangeEvent(@NonNull SeekBar seekBar) {
        super(seekBar);
    }

    public boolean equals(Object obj) {
        return (obj instanceof SeekBarStartChangeEvent) && ((SeekBarStartChangeEvent) obj).view() == view();
    }

    public int hashCode() {
        return view().hashCode();
    }

    public String toString() {
        return "SeekBarStartChangeEvent{view=" + view() + '}';
    }
}