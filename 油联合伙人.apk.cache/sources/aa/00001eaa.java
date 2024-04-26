package rx;

import rx.functions.Cancellable;

/* loaded from: classes.dex */
public interface CompletableEmitter {
    void onCompleted();

    void onError(Throwable th);

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}