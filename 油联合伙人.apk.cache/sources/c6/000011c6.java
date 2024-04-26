package com.jakewharton.rxbinding.widget;

import android.support.annotation.NonNull;
import android.widget.SeekBar;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public abstract class SeekBarChangeEvent extends ViewEvent<SeekBar> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SeekBarChangeEvent(@NonNull SeekBar seekBar) {
        super(seekBar);
    }
}