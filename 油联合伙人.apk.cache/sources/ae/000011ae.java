package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SeekBar;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;

/* loaded from: classes.dex */
public final class RxSeekBar {
    @CheckResult
    @NonNull
    public static Observable<Integer> changes(@NonNull SeekBar seekBar) {
        Preconditions.checkNotNull(seekBar, "view == null");
        return Observable.create(new SeekBarChangeOnSubscribe(seekBar, null));
    }

    @CheckResult
    @NonNull
    public static Observable<Integer> userChanges(@NonNull SeekBar seekBar) {
        Preconditions.checkNotNull(seekBar, "view == null");
        return Observable.create(new SeekBarChangeOnSubscribe(seekBar, true));
    }

    @CheckResult
    @NonNull
    public static Observable<Integer> systemChanges(@NonNull SeekBar seekBar) {
        Preconditions.checkNotNull(seekBar, "view == null");
        return Observable.create(new SeekBarChangeOnSubscribe(seekBar, false));
    }

    @CheckResult
    @NonNull
    public static Observable<SeekBarChangeEvent> changeEvents(@NonNull SeekBar seekBar) {
        Preconditions.checkNotNull(seekBar, "view == null");
        return Observable.create(new SeekBarChangeEventOnSubscribe(seekBar));
    }

    private RxSeekBar() {
        throw new AssertionError("No instances.");
    }
}