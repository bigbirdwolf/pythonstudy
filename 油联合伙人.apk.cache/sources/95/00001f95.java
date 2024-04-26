package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.atomic.MpscLinkedAtomicQueue;
import rx.internal.util.unsafe.MpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes.dex */
public final class OnSubscribeFlatMapSingle<T, R> implements Observable.OnSubscribe<R> {
    final boolean delayErrors;
    final Func1<? super T, ? extends Single<? extends R>> mapper;
    final int maxConcurrency;
    final Observable<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeFlatMapSingle(Observable<T> observable, Func1<? super T, ? extends Single<? extends R>> func1, boolean z, int i) {
        if (func1 == null) {
            throw new NullPointerException("mapper is null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + i);
        }
        this.source = observable;
        this.mapper = func1;
        this.delayErrors = z;
        this.maxConcurrency = i;
    }

    public void call(Subscriber<? super R> subscriber) {
        FlatMapSingleSubscriber flatMapSingleSubscriber = new FlatMapSingleSubscriber(subscriber, this.mapper, this.delayErrors, this.maxConcurrency);
        subscriber.add(flatMapSingleSubscriber.set);
        subscriber.add(flatMapSingleSubscriber.requested);
        subscriber.setProducer(flatMapSingleSubscriber.requested);
        this.source.unsafeSubscribe(flatMapSingleSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class FlatMapSingleSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Func1<? super T, ? extends Single<? extends R>> mapper;
        final int maxConcurrency;
        final Queue<Object> queue;
        final AtomicInteger wip = new AtomicInteger();
        final AtomicReference<Throwable> errors = new AtomicReference<>();
        final FlatMapSingleSubscriber<T, R>.Requested requested = new Requested();
        final CompositeSubscription set = new CompositeSubscription();
        final AtomicInteger active = new AtomicInteger();

        FlatMapSingleSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends Single<? extends R>> func1, boolean z, int i) {
            this.actual = subscriber;
            this.mapper = func1;
            this.delayErrors = z;
            this.maxConcurrency = i;
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new MpscLinkedQueue();
            } else {
                this.queue = new MpscLinkedAtomicQueue();
            }
            request(i != Integer.MAX_VALUE ? i : Long.MAX_VALUE);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Single<? extends R> call = this.mapper.call(t);
                if (call == null) {
                    throw new NullPointerException("The mapper returned a null Single");
                }
                InnerSubscriber innerSubscriber = new InnerSubscriber();
                this.set.add(innerSubscriber);
                this.active.incrementAndGet();
                call.subscribe(innerSubscriber);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.delayErrors) {
                ExceptionsUtils.addThrowable(this.errors, th);
            } else {
                this.set.unsubscribe();
                if (!this.errors.compareAndSet(null, th)) {
                    RxJavaHooks.onError(th);
                    return;
                }
            }
            this.done = true;
            drain();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        void innerSuccess(FlatMapSingleSubscriber<T, R>.InnerSubscriber innerSubscriber, R r) {
            this.queue.offer(NotificationLite.next(r));
            this.set.remove(innerSubscriber);
            this.active.decrementAndGet();
            drain();
        }

        void innerError(FlatMapSingleSubscriber<T, R>.InnerSubscriber innerSubscriber, Throwable th) {
            if (this.delayErrors) {
                ExceptionsUtils.addThrowable(this.errors, th);
                this.set.remove(innerSubscriber);
                if (!this.done && this.maxConcurrency != Integer.MAX_VALUE) {
                    request(1L);
                }
            } else {
                this.set.unsubscribe();
                unsubscribe();
                if (!this.errors.compareAndSet(null, th)) {
                    RxJavaHooks.onError(th);
                    return;
                }
                this.done = true;
            }
            this.active.decrementAndGet();
            drain();
        }

        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super R> subscriber = this.actual;
            Queue<Object> queue = this.queue;
            boolean z = this.delayErrors;
            AtomicInteger atomicInteger = this.active;
            int i = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    if (this.cancelled) {
                        queue.clear();
                        return;
                    }
                    boolean z2 = this.done;
                    if (!z && z2 && this.errors.get() != null) {
                        queue.clear();
                        subscriber.onError(ExceptionsUtils.terminate(this.errors));
                        return;
                    }
                    Object poll = queue.poll();
                    boolean z3 = poll == null;
                    if (z2 && atomicInteger.get() == 0 && z3) {
                        if (this.errors.get() != null) {
                            subscriber.onError(ExceptionsUtils.terminate(this.errors));
                            return;
                        } else {
                            subscriber.onCompleted();
                            return;
                        }
                    } else if (z3) {
                        break;
                    } else {
                        subscriber.onNext((Object) NotificationLite.getValue(poll));
                        j2++;
                    }
                }
                if (j2 == j) {
                    if (this.cancelled) {
                        queue.clear();
                        return;
                    } else if (this.done) {
                        if (z) {
                            if (atomicInteger.get() == 0 && queue.isEmpty()) {
                                if (this.errors.get() != null) {
                                    subscriber.onError(ExceptionsUtils.terminate(this.errors));
                                    return;
                                } else {
                                    subscriber.onCompleted();
                                    return;
                                }
                            }
                        } else if (this.errors.get() != null) {
                            queue.clear();
                            subscriber.onError(ExceptionsUtils.terminate(this.errors));
                            return;
                        } else if (atomicInteger.get() == 0 && queue.isEmpty()) {
                            subscriber.onCompleted();
                            return;
                        }
                    }
                }
                if (j2 != 0) {
                    this.requested.produced(j2);
                    if (!this.done && this.maxConcurrency != Integer.MAX_VALUE) {
                        request(j2);
                    }
                }
                i = this.wip.addAndGet(-i);
            } while (i != 0);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public final class Requested extends AtomicLong implements Producer, Subscription {
            private static final long serialVersionUID = -887187595446742742L;

            Requested() {
            }

            @Override // rx.Producer
            public void request(long j) {
                if (j > 0) {
                    BackpressureUtils.getAndAddRequest(this, j);
                    FlatMapSingleSubscriber.this.drain();
                }
            }

            void produced(long j) {
                BackpressureUtils.produced(this, j);
            }

            @Override // rx.Subscription
            public void unsubscribe() {
                FlatMapSingleSubscriber.this.cancelled = true;
                FlatMapSingleSubscriber.this.unsubscribe();
                if (FlatMapSingleSubscriber.this.wip.getAndIncrement() == 0) {
                    FlatMapSingleSubscriber.this.queue.clear();
                }
            }

            @Override // rx.Subscription
            public boolean isUnsubscribed() {
                return FlatMapSingleSubscriber.this.cancelled;
            }
        }

        /* loaded from: classes.dex */
        final class InnerSubscriber extends SingleSubscriber<R> {
            InnerSubscriber() {
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(R r) {
                FlatMapSingleSubscriber.this.innerSuccess(this, r);
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                FlatMapSingleSubscriber.this.innerError(this, th);
            }
        }
    }
}