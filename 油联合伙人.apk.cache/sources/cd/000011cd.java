package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* loaded from: classes.dex */
public final class SeekBarProgressChangeEvent extends SeekBarChangeEvent {
    private final boolean fromUser;
    private final int progress;

    @CheckResult
    @NonNull
    public static SeekBarProgressChangeEvent create(@NonNull SeekBar seekBar, int i, boolean z) {
        return new SeekBarProgressChangeEvent(seekBar, i, z);
    }

    private SeekBarProgressChangeEvent(@NonNull SeekBar seekBar, int i, boolean z) {
        super(seekBar);
        this.progress = i;
        this.fromUser = z;
    }

    public int progress() {
        return this.progress;
    }

    public boolean fromUser() {
        return this.fromUser;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SeekBarProgressChangeEvent) {
            SeekBarProgressChangeEvent seekBarProgressChangeEvent = (SeekBarProgressChangeEvent) obj;
            return seekBarProgressChangeEvent.view() == view() && seekBarProgressChangeEvent.progress == this.progress && seekBarProgressChangeEvent.fromUser == this.fromUser;
        }
        return false;
    }

    public int hashCode() {
        return ((((629 + view().hashCode()) * 37) + this.progress) * 37) + (this.fromUser ? 1 : 0);
    }

    public String toString() {
        return "SeekBarProgressChangeEvent{view=" + view() + ", progress=" + this.progress + ", fromUser=" + this.fromUser + '}';
    }
}