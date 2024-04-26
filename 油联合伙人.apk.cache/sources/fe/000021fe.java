package rx.observers;

import java.util.concurrent.atomic.AtomicReference;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;

/* loaded from: classes.dex */
public abstract class AsyncCompletableSubscriber implements CompletableSubscriber, Subscription {
    static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();
    private final AtomicReference<Subscription> upstream = new AtomicReference<>();

    protected void onStart() {
    }

    @Override // rx.CompletableSubscriber
    public final void onSubscribe(Subscription subscription) {
        if (!this.upstream.compareAndSet(null, subscription)) {
            subscription.unsubscribe();
            if (this.upstream.get() != UNSUBSCRIBED) {
                RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
                return;
            }
            return;
        }
        onStart();
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.upstream.get() == UNSUBSCRIBED;
    }

    protected final void clear() {
        this.upstream.set(UNSUBSCRIBED);
    }

    @Override // rx.Subscription
    public final void unsubscribe() {
        Subscription andSet;
        if (this.upstream.get() == UNSUBSCRIBED || (andSet = this.upstream.getAndSet(UNSUBSCRIBED)) == null || andSet == UNSUBSCRIBED) {
            return;
        }
        andSet.unsubscribe();
    }

    /* loaded from: classes.dex */
    static final class Unsubscribed implements Subscription {
        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return true;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
        }

        Unsubscribed() {
        }
    }
}