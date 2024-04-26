package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;

/* loaded from: classes.dex */
public final class SingleTimeout<T> implements Single.OnSubscribe<T> {
    final Single.OnSubscribe<? extends T> other;
    final Scheduler scheduler;
    final Single.OnSubscribe<T> source;
    final long timeout;
    final TimeUnit unit;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleTimeout(Single.OnSubscribe<T> onSubscribe, long j, TimeUnit timeUnit, Scheduler scheduler, Single.OnSubscribe<? extends T> onSubscribe2) {
        this.source = onSubscribe;
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.other = onSubscribe2;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        TimeoutSingleSubscriber timeoutSingleSubscriber = new TimeoutSingleSubscriber(singleSubscriber, this.other);
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        timeoutSingleSubscriber.add(createWorker);
        singleSubscriber.add(timeoutSingleSubscriber);
        createWorker.schedule(timeoutSingleSubscriber, this.timeout, this.unit);
        this.source.call(timeoutSingleSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class TimeoutSingleSubscriber<T> extends SingleSubscriber<T> implements Action0 {
        final SingleSubscriber<? super T> actual;
        final AtomicBoolean once = new AtomicBoolean();
        final Single.OnSubscribe<? extends T> other;

        TimeoutSingleSubscriber(SingleSubscriber<? super T> singleSubscriber, Single.OnSubscribe<? extends T> onSubscribe) {
            this.actual = singleSubscriber;
            this.other = onSubscribe;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            if (this.once.compareAndSet(false, true)) {
                try {
                    this.actual.onSuccess(t);
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                try {
                    this.actual.onError(th);
                    return;
                } finally {
                    unsubscribe();
                }
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.functions.Action0
        public void call() {
            if (this.once.compareAndSet(false, true)) {
                try {
                    Single.OnSubscribe<? extends T> onSubscribe = this.other;
                    if (onSubscribe == null) {
                        this.actual.onError(new TimeoutException());
                    } else {
                        OtherSubscriber otherSubscriber = new OtherSubscriber(this.actual);
                        this.actual.add(otherSubscriber);
                        onSubscribe.call(otherSubscriber);
                    }
                } finally {
                    unsubscribe();
                }
            }
        }

        /* loaded from: classes.dex */
        static final class OtherSubscriber<T> extends SingleSubscriber<T> {
            final SingleSubscriber<? super T> actual;

            OtherSubscriber(SingleSubscriber<? super T> singleSubscriber) {
                this.actual = singleSubscriber;
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(T t) {
                this.actual.onSuccess(t);
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                this.actual.onError(th);
            }
        }
    }
}