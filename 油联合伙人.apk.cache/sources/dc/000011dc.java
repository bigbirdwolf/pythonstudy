package com.jakewharton.rxbinding.widget;

import android.view.KeyEvent;
import android.widget.TextView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

/* loaded from: classes.dex */
final class TextViewEditorActionOnSubscribe implements Observable.OnSubscribe<Integer> {
    final Func1<? super Integer, Boolean> handled;
    final TextView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewEditorActionOnSubscribe(TextView textView, Func1<? super Integer, Boolean> func1) {
        this.view = textView;
        this.handled = func1;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() { // from class: com.jakewharton.rxbinding.widget.TextViewEditorActionOnSubscribe.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (TextViewEditorActionOnSubscribe.this.handled.call(Integer.valueOf(i)).booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(Integer.valueOf(i));
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.TextViewEditorActionOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                TextViewEditorActionOnSubscribe.this.view.setOnEditorActionListener(null);
            }
        });
        this.view.setOnEditorActionListener(onEditorActionListener);
    }
}