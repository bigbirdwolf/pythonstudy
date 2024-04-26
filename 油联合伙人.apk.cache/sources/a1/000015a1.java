package com.yltx.oil.partner.data.network.adapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/* loaded from: classes.dex */
final class CallEnqueueOnSubscribe<T> implements Observable.OnSubscribe<Response<T>> {
    private final Call<T> originalCall;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallEnqueueOnSubscribe(Call<T> call) {
        this.originalCall = call;
    }

    public void call(Subscriber<? super Response<T>> subscriber) {
        Call<T> mo24clone = this.originalCall.mo24clone();
        final CallArbiter callArbiter = new CallArbiter(mo24clone, subscriber);
        subscriber.add(callArbiter);
        subscriber.setProducer(callArbiter);
        mo24clone.enqueue(new Callback<T>() { // from class: com.yltx.oil.partner.data.network.adapter.CallEnqueueOnSubscribe.1
            @Override // retrofit2.Callback
            public void onResponse(Call<T> call, Response<T> response) {
                callArbiter.emitResponse(response);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<T> call, Throwable th) {
                Exceptions.throwIfFatal(th);
                callArbiter.emitError(th);
            }
        });
    }
}