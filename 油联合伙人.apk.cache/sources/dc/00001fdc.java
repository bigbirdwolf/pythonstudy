package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;

/* loaded from: classes.dex */
public final class OnSubscribeTakeLastOne<T> implements Observable.OnSubscribe<T> {
    final Observable<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeTakeLastOne(Observable<T> observable) {
        this.source = observable;
    }

    public void call(Subscriber<? super T> subscriber) {
        new TakeLastOneSubscriber(subscriber).subscribeTo(this.source);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class TakeLastOneSubscriber<T> extends DeferredScalarSubscriber<T, T> {
        static final Object EMPTY = new Object();

        /* JADX WARN: Type inference failed for: r1v1, types: [R, java.lang.Object] */
        public TakeLastOneSubscriber(Subscriber<? super T> subscriber) {
            super(subscriber);
            this.value = EMPTY;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        public void onNext(T t) {
            this.value = t;
        }

        @Override // rx.internal.operators.DeferredScalarSubscriber, rx.Observer
        public void onCompleted() {
            Object obj = this.value;
            if (obj == EMPTY) {
                complete();
            } else {
                complete(obj);
            }
        }
    }
}