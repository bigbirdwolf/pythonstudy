package rx;

import rx.functions.Cancellable;

/* loaded from: classes.dex */
public interface Emitter<T> extends Observer<T> {

    /* loaded from: classes.dex */
    public enum BackpressureMode {
        NONE,
        ERROR,
        BUFFER,
        DROP,
        LATEST
    }

    long requested();

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}