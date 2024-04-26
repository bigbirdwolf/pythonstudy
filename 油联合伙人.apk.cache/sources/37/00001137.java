package com.jakewharton.rxbinding.view;

import android.view.View;
import android.view.ViewGroup;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class ViewGroupHierarchyChangeEventOnSubscribe implements Observable.OnSubscribe<ViewGroupHierarchyChangeEvent> {
    final ViewGroup viewGroup;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewGroupHierarchyChangeEventOnSubscribe(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super ViewGroupHierarchyChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = new ViewGroup.OnHierarchyChangeListener() { // from class: com.jakewharton.rxbinding.view.ViewGroupHierarchyChangeEventOnSubscribe.1
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View view, View view2) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(ViewGroupHierarchyChildViewAddEvent.create((ViewGroup) view, view2));
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View view, View view2) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(ViewGroupHierarchyChildViewRemoveEvent.create((ViewGroup) view, view2));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.view.ViewGroupHierarchyChangeEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                ViewGroupHierarchyChangeEventOnSubscribe.this.viewGroup.setOnHierarchyChangeListener(null);
            }
        });
        this.viewGroup.setOnHierarchyChangeListener(onHierarchyChangeListener);
    }
}