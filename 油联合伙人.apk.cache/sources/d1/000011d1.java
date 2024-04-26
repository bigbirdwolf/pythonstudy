package com.jakewharton.rxbinding.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class TextViewAfterTextChangeEventOnSubscribe implements Observable.OnSubscribe<TextViewAfterTextChangeEvent> {
    final TextView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextViewAfterTextChangeEventOnSubscribe(TextView textView) {
        this.view = textView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super TextViewAfterTextChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final TextWatcher textWatcher = new TextWatcher() { // from class: com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEventOnSubscribe.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(TextViewAfterTextChangeEvent.create(TextViewAfterTextChangeEventOnSubscribe.this.view, editable));
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEventOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                TextViewAfterTextChangeEventOnSubscribe.this.view.removeTextChangedListener(textWatcher);
            }
        });
        this.view.addTextChangedListener(textWatcher);
        subscriber.onNext(TextViewAfterTextChangeEvent.create(this.view, this.view.getEditableText()));
    }
}