package com.jakewharton.rxbinding.view;

import android.view.DragEvent;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

/* loaded from: classes.dex */
final class ViewDragOnSubscribe implements Observable.OnSubscribe<DragEvent> {
    final Func1<? super DragEvent, Boolean> handled;
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewDragOnSubscribe(View view, Func1<? super DragEvent, Boolean> func1) {
        this.view = view;
        this.handled = func1;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super DragEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        View.OnDragListener onDragListener = new View.OnDragListener() { // from class: com.jakewharton.rxbinding.view.ViewDragOnSubscribe.1
            @Override // android.view.View.OnDragListener
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (ViewDragOnSubscribe.this.handled.call(dragEvent).booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(dragEvent);
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewDragOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewDragOnSubscribe.this.view.setOnDragListener(null);
            }
        });
        this.view.setOnDragListener(onDragListener);
    }
}