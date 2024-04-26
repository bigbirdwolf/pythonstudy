package com.yltx.oil.partner.data.network.adapter;

import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.Call;
import retrofit2.Response;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaPlugins;

/* loaded from: classes.dex */
final class CallArbiter<T> extends AtomicInteger implements Subscription, Producer {
    private static final int STATE_HAS_RESPONSE = 2;
    private static final int STATE_REQUESTED = 1;
    private static final int STATE_TERMINATED = 3;
    private static final int STATE_WAITING = 0;
    private final Call<T> call;
    private volatile Response<T> response;
    private final Subscriber<? super Response<T>> subscriber;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallArbiter(Call<T> call, Subscriber<? super Response<T>> subscriber) {
        super(0);
        this.call = call;
        this.subscriber = subscriber;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.call.cancel();
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.call.isCanceled();
    }

    @Override // rx.Producer
    public void request(long j) {
        if (j == 0) {
            return;
        }
        while (true) {
            int i = get();
            switch (i) {
                case 0:
                    if (!compareAndSet(0, 1)) {
                        break;
                    } else {
                        return;
                    }
                case 1:
                case 3:
                    return;
                case 2:
                    if (!compareAndSet(2, 3)) {
                        break;
                    } else {
                        deliverResponse(this.response);
                        return;
                    }
                default:
                    throw new IllegalStateException("Unknown state: " + i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void emitResponse(Response<T> response) {
        while (true) {
            int i = get();
            switch (i) {
                case 0:
                    this.response = response;
                    if (!compareAndSet(0, 2)) {
                        break;
                    } else {
                        return;
                    }
                case 1:
                    if (!compareAndSet(1, 3)) {
                        break;
                    } else {
                        deliverResponse(response);
                        return;
                    }
                case 2:
                case 3:
                    throw new AssertionError();
                default:
                    throw new IllegalStateException("Unknown state: " + i);
            }
        }
    }

    private void deliverResponse(Response<T> response) {
        try {
            if (!isUnsubscribed()) {
                this.subscriber.onNext(response);
            }
            try {
                if (isUnsubscribed()) {
                    return;
                }
                this.subscriber.onCompleted();
            } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e2) {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e2);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            try {
                this.subscriber.onError(th2);
            } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e3) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e3);
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(th2, th3));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void emitError(Throwable th) {
        set(3);
        if (isUnsubscribed()) {
            return;
        }
        try {
            this.subscriber.onError(th);
        } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e) {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(th, th2));
        }
    }
}