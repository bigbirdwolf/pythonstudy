package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* loaded from: classes.dex */
public final class SeekBarStopChangeEvent extends SeekBarChangeEvent {
    @CheckResult
    @NonNull
    public static SeekBarStopChangeEvent create(@NonNull SeekBar seekBar) {
        return new SeekBarStopChangeEvent(seekBar);
    }

    private SeekBarStopChangeEvent(@NonNull SeekBar seekBar) {
        super(seekBar);
    }

    public boolean equals(Object obj) {
        return (obj instanceof SeekBarStopChangeEvent) && ((SeekBarStopChangeEvent) obj).view() == view();
    }

    public int hashCode() {
        return view().hashCode();
    }

    public String toString() {
        return "SeekBarStopChangeEvent{view=" + view() + '}';
    }
}