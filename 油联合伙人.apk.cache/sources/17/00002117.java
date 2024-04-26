package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.SingleFromObservable;
import rx.internal.producers.SingleProducer;
import rx.plugins.RxJavaHooks;

/* loaded from: classes.dex */
public final class SingleLiftObservableOperator<T, R> implements Single.OnSubscribe<R> {
    final Observable.Operator<? extends R, ? super T> lift;
    final Single.OnSubscribe<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleLiftObservableOperator(Single.OnSubscribe<T> onSubscribe, Observable.Operator<? extends R, ? super T> operator) {
        this.source = onSubscribe;
        this.lift = operator;
    }

    public void call(SingleSubscriber<? super R> singleSubscriber) {
        SingleFromObservable.WrapSingleIntoSubscriber wrapSingleIntoSubscriber = new SingleFromObservable.WrapSingleIntoSubscriber(singleSubscriber);
        singleSubscriber.add(wrapSingleIntoSubscriber);
        try {
            Subscriber<? super T> call = RxJavaHooks.onSingleLift(this.lift).call(wrapSingleIntoSubscriber);
            SingleSubscriber wrap = wrap(call);
            call.onStart();
            this.source.call(wrap);
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, singleSubscriber);
        }
    }

    public static <T> SingleSubscriber<T> wrap(Subscriber<T> subscriber) {
        WrapSubscriberIntoSingle wrapSubscriberIntoSingle = new WrapSubscriberIntoSingle(subscriber);
        subscriber.add(wrapSubscriberIntoSingle);
        return wrapSubscriberIntoSingle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class WrapSubscriberIntoSingle<T> extends SingleSubscriber<T> {
        final Subscriber<? super T> actual;

        /* JADX INFO: Access modifiers changed from: package-private */
        public WrapSubscriberIntoSingle(Subscriber<? super T> subscriber) {
            this.actual = subscriber;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.actual.setProducer(new SingleProducer(this.actual, t));
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }
    }
}