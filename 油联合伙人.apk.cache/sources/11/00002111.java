package rx.internal.operators;

import java.util.concurrent.Callable;
import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;

/* loaded from: classes.dex */
public final class SingleFromCallable<T> implements Single.OnSubscribe<T> {
    final Callable<? extends T> callable;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleFromCallable(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        try {
            singleSubscriber.onSuccess((T) this.callable.call());
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            singleSubscriber.onError(th);
        }
    }
}