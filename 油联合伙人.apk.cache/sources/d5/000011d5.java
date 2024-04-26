package com.jakewharton.rxbinding.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class TextViewBeforeTextChangeEventOnSubscribe implements Observable.OnSubscribe<TextViewBeforeTextChangeEvent> {
    final TextView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewBeforeTextChangeEventOnSubscribe(TextView textView) {
        this.view = textView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super TextViewBeforeTextChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final TextWatcher textWatcher = new TextWatcher() { // from class: com.jakewharton.rxbinding.widget.TextViewBeforeTextChangeEventOnSubscribe.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(TextViewBeforeTextChangeEvent.create(TextViewBeforeTextChangeEventOnSubscribe.this.view, charSequence, i, i2, i3));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.TextViewBeforeTextChangeEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                TextViewBeforeTextChangeEventOnSubscribe.this.view.removeTextChangedListener(textWatcher);
            }
        });
        this.view.addTextChangedListener(textWatcher);
        subscriber.onNext(TextViewBeforeTextChangeEvent.create(this.view, this.view.getText(), 0, 0, 0));
    }
}