package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.internal.operators.SingleLiftObservableOperator;

/* loaded from: classes.dex */
public final class SingleToObservable<T> implements Observable.OnSubscribe<T> {
    final Single.OnSubscribe<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public SingleToObservable(Single.OnSubscribe<T> onSubscribe) {
        this.source = onSubscribe;
    }

    public void call(Subscriber<? super T> subscriber) {
        SingleLiftObservableOperator.WrapSubscriberIntoSingle wrapSubscriberIntoSingle = new SingleLiftObservableOperator.WrapSubscriberIntoSingle(subscriber);
        subscriber.add(wrapSubscriberIntoSingle);
        this.source.call(wrapSubscriberIntoSingle);
    }
}