package com.jakewharton.rxbinding.widget;

import android.view.KeyEvent;
import android.widget.TextView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

/* loaded from: classes.dex */
final class TextViewEditorActionEventOnSubscribe implements Observable.OnSubscribe<TextViewEditorActionEvent> {
    final Func1<? super TextViewEditorActionEvent, Boolean> handled;
    final TextView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewEditorActionEventOnSubscribe(TextView textView, Func1<? super TextViewEditorActionEvent, Boolean> func1) {
        this.view = textView;
        this.handled = func1;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super TextViewEditorActionEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() { // from class: com.jakewharton.rxbinding.widget.TextViewEditorActionEventOnSubscribe.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                TextViewEditorActionEvent create = TextViewEditorActionEvent.create(textView, i, keyEvent);
                if (TextViewEditorActionEventOnSubscribe.this.handled.call(create).booleanValue()) {
                    if (subscriber.isUnsubscribed()) {
                        return true;
                    }
                    subscriber.onNext(create);
                    return true;
                }
                return false;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.TextViewEditorActionEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                TextViewEditorActionEventOnSubscribe.this.view.setOnEditorActionListener(null);
            }
        });
        this.view.setOnEditorActionListener(onEditorActionListener);
    }
}