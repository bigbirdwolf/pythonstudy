package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;

/* loaded from: classes.dex */
public final class SingleTakeUntilObservable<T, U> implements Single.OnSubscribe<T> {
    final Observable<? extends U> other;
    final Single.OnSubscribe<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleTakeUntilObservable(Single.OnSubscribe<T> onSubscribe, Observable<? extends U> observable) {
        this.source = onSubscribe;
        this.other = observable;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        TakeUntilSourceSubscriber takeUntilSourceSubscriber = new TakeUntilSourceSubscriber(singleSubscriber);
        singleSubscriber.add(takeUntilSourceSubscriber);
        this.other.subscribe((Subscriber<? super Object>) takeUntilSourceSubscriber.other);
        this.source.call(takeUntilSourceSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class TakeUntilSourceSubscriber<T, U> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final AtomicBoolean once = new AtomicBoolean();
        final Subscriber<U> other = new OtherSubscriber();

        TakeUntilSourceSubscriber(SingleSubscriber<? super T> singleSubscriber) {
            this.actual = singleSubscriber;
            add(this.other);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            if (this.once.compareAndSet(false, true)) {
                unsubscribe();
                this.actual.onSuccess(t);
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                unsubscribe();
                this.actual.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        /* loaded from: classes.dex */
        final class OtherSubscriber extends Subscriber<U> {
            OtherSubscriber() {
            }

            @Override // rx.Observer
            public void onNext(U u) {
                onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                TakeUntilSourceSubscriber.this.onError(th);
            }

            @Override // rx.Observer
            public void onCompleted() {
                onError(new CancellationException("Single::takeUntil(Observable) - Stream was canceled before emitting a terminal event."));
            }
        }
    }
}