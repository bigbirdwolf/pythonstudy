package com.jakewharton.rxbinding.support.design.widget;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class SwipeDismissBehaviorOnSubscribe implements Observable.OnSubscribe<View> {
    private final View view;

    public SwipeDismissBehaviorOnSubscribe(View view) {
        this.view = view;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super View> subscriber) {
        MainThreadSubscription.verifyMainThread();
        SwipeDismissBehavior.OnDismissListener onDismissListener = new SwipeDismissBehavior.OnDismissListener() { // from class: com.jakewharton.rxbinding.support.design.widget.SwipeDismissBehaviorOnSubscribe.1
            @Override // android.support.design.widget.SwipeDismissBehavior.OnDismissListener
            public void onDragStateChanged(int i) {
            }

            @Override // android.support.design.widget.SwipeDismissBehavior.OnDismissListener
            public void onDismiss(View view) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(view);
            }
        };
        if (!(this.view.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not in a Coordinator Layout.");
        }
        final SwipeDismissBehavior swipeDismissBehavior = (SwipeDismissBehavior) ((CoordinatorLayout.LayoutParams) this.view.getLayoutParams()).getBehavior();
        if (swipeDismissBehavior == null) {
            throw new IllegalStateException("There's no behavior set on this view.");
        }
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.design.widget.SwipeDismissBehaviorOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                swipeDismissBehavior.setListener(null);
            }
        });
        swipeDismissBehavior.setListener(onDismissListener);
    }
}