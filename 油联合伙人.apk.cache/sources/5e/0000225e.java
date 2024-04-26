package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.functions.Action0;

/* loaded from: classes.dex */
public final class BooleanSubscription implements Subscription {
    static final Action0 EMPTY_ACTION = new Action0() { // from class: rx.subscriptions.BooleanSubscription.1
        @Override // rx.functions.Action0
        public void call() {
        }
    };
    final AtomicReference<Action0> actionRef;

    public BooleanSubscription() {
        this.actionRef = new AtomicReference<>();
    }

    private BooleanSubscription(Action0 action0) {
        this.actionRef = new AtomicReference<>(action0);
    }

    public static BooleanSubscription create() {
        return new BooleanSubscription();
    }

    public static BooleanSubscription create(Action0 action0) {
        return new BooleanSubscription(action0);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.actionRef.get() == EMPTY_ACTION;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        Action0 andSet;
        if (this.actionRef.get() == EMPTY_ACTION || (andSet = this.actionRef.getAndSet(EMPTY_ACTION)) == null || andSet == EMPTY_ACTION) {
            return;
        }
        andSet.call();
    }
}