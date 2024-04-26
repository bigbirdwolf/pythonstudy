package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* loaded from: classes.dex */
public final class OperatorTake<T> implements Observable.Operator<T, T> {
    final int limit;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTake(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("limit >= 0 required but it was " + i);
        }
        this.limit = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorTake$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends Subscriber<T> {
        boolean completed;
        int count;
        final /* synthetic */ Subscriber val$child;

        AnonymousClass1(Subscriber subscriber) {
            this.val$child = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.completed) {
                return;
            }
            this.completed = true;
            this.val$child.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.completed) {
                return;
            }
            this.completed = true;
            try {
                this.val$child.onError(th);
            } finally {
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed()) {
                return;
            }
            int i = this.count;
            this.count = i + 1;
            if (i < OperatorTake.this.limit) {
                boolean z = this.count == OperatorTake.this.limit;
                this.val$child.onNext(t);
                if (!z || this.completed) {
                    return;
                }
                this.completed = true;
                try {
                    this.val$child.onCompleted();
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(final Producer producer) {
            this.val$child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTake.1.1
                final AtomicLong requested = new AtomicLong(0);

                @Override // rx.Producer
                public void request(long j) {
                    long j2;
                    long min;
                    if (j <= 0 || AnonymousClass1.this.completed) {
                        return;
                    }
                    do {
                        j2 = this.requested.get();
                        min = Math.min(j, OperatorTake.this.limit - j2);
                        if (min == 0) {
                            return;
                        }
                    } while (!this.requested.compareAndSet(j2, j2 + min));
                    producer.request(min);
                }
            });
        }
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(subscriber);
        if (this.limit == 0) {
            subscriber.onCompleted();
            anonymousClass1.unsubscribe();
        }
        subscriber.add(anonymousClass1);
        return anonymousClass1;
    }
}