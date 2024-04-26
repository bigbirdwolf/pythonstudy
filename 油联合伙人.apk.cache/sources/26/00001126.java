package com.jakewharton.rxbinding.view;

import android.support.annotation.NonNull;
import android.view.View;
import com.jakewharton.rxbinding.view.ViewAttachEvent;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ViewAttachEventOnSubscribe implements Observable.OnSubscribe<ViewAttachEvent> {
    final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewAttachEventOnSubscribe(View view) {
        this.view = view;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super ViewAttachEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final View.OnAttachStateChangeListener onAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: com.jakewharton.rxbinding.view.ViewAttachEventOnSubscribe.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(@NonNull View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(ViewAttachEvent.create(ViewAttachEventOnSubscribe.this.view, ViewAttachEvent.Kind.ATTACH));
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(@NonNull View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(ViewAttachEvent.create(ViewAttachEventOnSubscribe.this.view, ViewAttachEvent.Kind.DETACH));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewAttachEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewAttachEventOnSubscribe.this.view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
            }
        });
        this.view.addOnAttachStateChangeListener(onAttachStateChangeListener);
    }
}