package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action2;
import rx.functions.Func0;

/* loaded from: classes.dex */
public final class OnSubscribeCollect<T, R> implements Observable.OnSubscribe<R> {
    final Func0<R> collectionFactory;
    final Action2<R, ? super T> collector;
    final Observable<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeCollect(Observable<T> observable, Func0<R> func0, Action2<R, ? super T> action2) {
        this.source = observable;
        this.collectionFactory = func0;
        this.collector = action2;
    }

    public void call(Subscriber<? super R> subscriber) {
        try {
            new CollectSubscriber(subscriber, this.collectionFactory.call(), this.collector).subscribeTo(this.source);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class CollectSubscriber<T, R> extends DeferredScalarSubscriberSafe<T, R> {
        final Action2<R, ? super T> collector;

        public CollectSubscriber(Subscriber<? super R> subscriber, R r, Action2<R, ? super T> action2) {
            super(subscriber);
            this.value = r;
            this.hasValue = true;
            this.collector = action2;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                this.collector.call(this.value, t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }
    }
}