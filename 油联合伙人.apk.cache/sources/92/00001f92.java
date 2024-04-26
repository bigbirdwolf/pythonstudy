package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes.dex */
public final class OnSubscribeFlatMapCompletable<T> implements Observable.OnSubscribe<T> {
    final boolean delayErrors;
    final Func1<? super T, ? extends Completable> mapper;
    final int maxConcurrency;
    final Observable<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeFlatMapCompletable(Observable<T> observable, Func1<? super T, ? extends Completable> func1, boolean z, int i) {
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

    public void call(Subscriber<? super T> subscriber) {
        FlatMapCompletableSubscriber flatMapCompletableSubscriber = new FlatMapCompletableSubscriber(subscriber, this.mapper, this.delayErrors, this.maxConcurrency);
        subscriber.add(flatMapCompletableSubscriber);
        subscriber.add(flatMapCompletableSubscriber.set);
        this.source.unsafeSubscribe(flatMapCompletableSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class FlatMapCompletableSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        final boolean delayErrors;
        final Func1<? super T, ? extends Completable> mapper;
        final int maxConcurrency;
        final AtomicInteger wip = new AtomicInteger(1);
        final AtomicReference<Throwable> errors = new AtomicReference<>();
        final CompositeSubscription set = new CompositeSubscription();

        FlatMapCompletableSubscriber(Subscriber<? super T> subscriber, Func1<? super T, ? extends Completable> func1, boolean z, int i) {
            this.actual = subscriber;
            this.mapper = func1;
            this.delayErrors = z;
            this.maxConcurrency = i;
            request(i != Integer.MAX_VALUE ? i : Long.MAX_VALUE);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Completable call = this.mapper.call(t);
                if (call == null) {
                    throw new NullPointerException("The mapper returned a null Completable");
                }
                InnerSubscriber innerSubscriber = new InnerSubscriber();
                this.set.add(innerSubscriber);
                this.wip.getAndIncrement();
                call.unsafeSubscribe(innerSubscriber);
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
                onCompleted();
                return;
            }
            this.set.unsubscribe();
            if (this.errors.compareAndSet(null, th)) {
                this.actual.onError(ExceptionsUtils.terminate(this.errors));
            } else {
                RxJavaHooks.onError(th);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            done();
        }

        boolean done() {
            if (this.wip.decrementAndGet() == 0) {
                Throwable terminate = ExceptionsUtils.terminate(this.errors);
                if (terminate != null) {
                    this.actual.onError(terminate);
                    return true;
                }
                this.actual.onCompleted();
                return true;
            }
            return false;
        }

        public void innerError(FlatMapCompletableSubscriber<T>.InnerSubscriber innerSubscriber, Throwable th) {
            this.set.remove(innerSubscriber);
            if (this.delayErrors) {
                ExceptionsUtils.addThrowable(this.errors, th);
                if (done() || this.maxConcurrency == Integer.MAX_VALUE) {
                    return;
                }
                request(1L);
                return;
            }
            this.set.unsubscribe();
            unsubscribe();
            if (this.errors.compareAndSet(null, th)) {
                this.actual.onError(ExceptionsUtils.terminate(this.errors));
            } else {
                RxJavaHooks.onError(th);
            }
        }

        public void innerComplete(FlatMapCompletableSubscriber<T>.InnerSubscriber innerSubscriber) {
            this.set.remove(innerSubscriber);
            if (done() || this.maxConcurrency == Integer.MAX_VALUE) {
                return;
            }
            request(1L);
        }

        /* loaded from: classes.dex */
        final class InnerSubscriber extends AtomicReference<Subscription> implements CompletableSubscriber, Subscription {
            private static final long serialVersionUID = -8588259593722659900L;

            InnerSubscriber() {
            }

            @Override // rx.Subscription
            public void unsubscribe() {
                Subscription andSet = getAndSet(this);
                if (andSet == null || andSet == this) {
                    return;
                }
                andSet.unsubscribe();
            }

            @Override // rx.Subscription
            public boolean isUnsubscribed() {
                return get() == this;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                FlatMapCompletableSubscriber.this.innerComplete(this);
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                FlatMapCompletableSubscriber.this.innerError(this, th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                if (compareAndSet(null, subscription)) {
                    return;
                }
                subscription.unsubscribe();
                if (get() != this) {
                    RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
                }
            }
        }
    }
}