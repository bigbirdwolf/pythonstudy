package com.jakewharton.rxbinding.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class RecyclerViewChildAttachStateChangeEventOnSubscribe implements Observable.OnSubscribe<RecyclerViewChildAttachStateChangeEvent> {
    final RecyclerView recyclerView;

    public RecyclerViewChildAttachStateChangeEventOnSubscribe(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super RecyclerViewChildAttachStateChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() { // from class: com.jakewharton.rxbinding.support.v7.widget.RecyclerViewChildAttachStateChangeEventOnSubscribe.1
            @Override // android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(RecyclerViewChildAttachEvent.create(RecyclerViewChildAttachStateChangeEventOnSubscribe.this.recyclerView, view));
            }

            @Override // android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(RecyclerViewChildDetachEvent.create(RecyclerViewChildAttachStateChangeEventOnSubscribe.this.recyclerView, view));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v7.widget.RecyclerViewChildAttachStateChangeEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                RecyclerViewChildAttachStateChangeEventOnSubscribe.this.recyclerView.removeOnChildAttachStateChangeListener(onChildAttachStateChangeListener);
            }
        });
        this.recyclerView.addOnChildAttachStateChangeListener(onChildAttachStateChangeListener);
    }
}