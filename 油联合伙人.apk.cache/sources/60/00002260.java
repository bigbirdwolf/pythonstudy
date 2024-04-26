package rx.subscriptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import rx.Subscription;
import rx.exceptions.Exceptions;

/* loaded from: classes.dex */
public final class CompositeSubscription implements Subscription {
    private Set<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public CompositeSubscription() {
    }

    public CompositeSubscription(Subscription... subscriptionArr) {
        this.subscriptions = new HashSet(Arrays.asList(subscriptionArr));
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void add(Subscription subscription) {
        if (subscription.isUnsubscribed()) {
            return;
        }
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    if (this.subscriptions == null) {
                        this.subscriptions = new HashSet(4);
                    }
                    this.subscriptions.add(subscription);
                    return;
                }
            }
        }
        subscription.unsubscribe();
    }

    public void addAll(Subscription... subscriptionArr) {
        int i = 0;
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    if (this.subscriptions == null) {
                        this.subscriptions = new HashSet(subscriptionArr.length);
                    }
                    int length = subscriptionArr.length;
                    while (i < length) {
                        Subscription subscription = subscriptionArr[i];
                        if (!subscription.isUnsubscribed()) {
                            this.subscriptions.add(subscription);
                        }
                        i++;
                    }
                    return;
                }
            }
        }
        int length2 = subscriptionArr.length;
        while (i < length2) {
            subscriptionArr[i].unsubscribe();
            i++;
        }
    }

    public void remove(Subscription subscription) {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            if (!this.unsubscribed && this.subscriptions != null) {
                boolean remove = this.subscriptions.remove(subscription);
                if (remove) {
                    subscription.unsubscribe();
                }
            }
        }
    }

    public void clear() {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            if (!this.unsubscribed && this.subscriptions != null) {
                Set<Subscription> set = this.subscriptions;
                this.subscriptions = null;
                unsubscribeFromAll(set);
            }
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            if (this.unsubscribed) {
                return;
            }
            this.unsubscribed = true;
            Set<Subscription> set = this.subscriptions;
            this.subscriptions = null;
            unsubscribeFromAll(set);
        }
    }

    private static void unsubscribeFromAll(Collection<Subscription> collection) {
        if (collection == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Subscription subscription : collection) {
            try {
                subscription.unsubscribe();
            } catch (Throwable th) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        Exceptions.throwIfAny(arrayList);
    }

    public boolean hasSubscriptions() {
        boolean z = false;
        if (this.unsubscribed) {
            return false;
        }
        synchronized (this) {
            if (!this.unsubscribed && this.subscriptions != null && !this.subscriptions.isEmpty()) {
                z = true;
            }
        }
        return z;
    }
}