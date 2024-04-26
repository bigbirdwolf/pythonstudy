package com.jakewharton.rxbinding.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class TextViewTextChangeEventOnSubscribe implements Observable.OnSubscribe<TextViewTextChangeEvent> {
    final TextView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewTextChangeEventOnSubscribe(TextView textView) {
        this.view = textView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super TextViewTextChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final TextWatcher textWatcher = new TextWatcher() { // from class: com.jakewharton.rxbinding.widget.TextViewTextChangeEventOnSubscribe.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(TextViewTextChangeEvent.create(TextViewTextChangeEventOnSubscribe.this.view, charSequence, i, i2, i3));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.TextViewTextChangeEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                TextViewTextChangeEventOnSubscribe.this.view.removeTextChangedListener(textWatcher);
            }
        });
        this.view.addTextChangedListener(textWatcher);
        subscriber.onNext(TextViewTextChangeEvent.create(this.view, this.view.getText(), 0, 0, 0));
    }
}