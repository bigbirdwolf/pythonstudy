package rx;

import rx.internal.util.SubscriptionList;

/* loaded from: classes.dex */
public abstract class Subscriber<T> implements Observer<T>, Subscription {
    private static final long NOT_SET = Long.MIN_VALUE;
    private Producer producer;
    private long requested;
    private final Subscriber<?> subscriber;
    private final SubscriptionList subscriptions;

    public void onStart() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Subscriber() {
        this(null, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Subscriber(Subscriber<?> subscriber) {
        this(subscriber, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Subscriber(Subscriber<?> subscriber, boolean z) {
        this.requested = NOT_SET;
        this.subscriber = subscriber;
        this.subscriptions = (!z || subscriber == null) ? new SubscriptionList() : subscriber.subscriptions;
    }

    public final void add(Subscription subscription) {
        this.subscriptions.add(subscription);
    }

    @Override // rx.Subscription
    public final void unsubscribe() {
        this.subscriptions.unsubscribe();
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.subscriptions.isUnsubscribed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + j);
        }
        synchronized (this) {
            if (this.producer != null) {
                this.producer.request(j);
                return;
            }
            addToRequested(j);
        }
    }

    private void addToRequested(long j) {
        if (this.requested == NOT_SET) {
            this.requested = j;
            return;
        }
        long j2 = this.requested + j;
        if (j2 < 0) {
            this.requested = Long.MAX_VALUE;
        } else {
            this.requested = j2;
        }
    }

    public void setProducer(Producer producer) {
        long j;
        boolean z;
        synchronized (this) {
            j = this.requested;
            this.producer = producer;
            z = this.subscriber != null && j == NOT_SET;
        }
        if (z) {
            this.subscriber.setProducer(this.producer);
        } else if (j == NOT_SET) {
            this.producer.request(Long.MAX_VALUE);
        } else {
            this.producer.request(j);
        }
    }
}