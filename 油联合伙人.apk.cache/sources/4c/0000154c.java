package com.yltx.oil.partner.base;

import android.view.View;
import android.widget.TextView;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/* loaded from: classes.dex */
public final class Rx {
    public static void click(View view, Action1<Void> action1) {
        click(view, 150L, action1);
    }

    public static void click(View view, long j, Action1<Void> action1) {
        RxView.clicks(view).throttleFirst(j, TimeUnit.MILLISECONDS).subscribe(action1);
    }

    public static void click(View view, Func1<Void, Boolean> func1, Action1<Void> action1) {
        click(view, 150L, func1, action1);
    }

    public static void click(View view, long j, Func1<Void, Boolean> func1, Action1<Void> action1) {
        RxView.clicks(view).throttleFirst(j, TimeUnit.MILLISECONDS).filter(func1).subscribe(action1);
    }

    public static void click(View view, Func1<Void, Boolean> func1, Func1<Void, Boolean> func12, Action1<Void> action1) {
        click(view, 150L, func1, func12, action1);
    }

    public static void click(View view, long j, Func1<Void, Boolean> func1, Func1<Void, Boolean> func12, Action1<Void> action1) {
        RxView.clicks(view).throttleFirst(j, TimeUnit.MILLISECONDS).filter(func1).filter(func12).subscribe(action1);
    }

    public static void click(View view, Func1<Void, Boolean> func1, Action1<Boolean> action1, Observable.Transformer<Object, Boolean> transformer) {
        click(view, 150L, func1, action1, transformer);
    }

    public static void click(View view, long j, Func1<Void, Boolean> func1, Action1<Boolean> action1, Observable.Transformer<Object, Boolean> transformer) {
        RxView.clicks(view).throttleFirst(j, TimeUnit.MILLISECONDS).filter(func1).compose(transformer).subscribe(action1);
    }

    public static void click(View view, Action1<Boolean> action1, Observable.Transformer<Object, Boolean> transformer) {
        click(view, 150L, action1, transformer);
    }

    public static void click(View view, long j, Action1<Boolean> action1, Observable.Transformer<Object, Boolean> transformer) {
        RxView.clicks(view).throttleFirst(j, TimeUnit.MILLISECONDS).compose(transformer).subscribe(action1);
    }

    public static void afterTextChange(TextView textView, Action1<TextViewAfterTextChangeEvent> action1) {
        afterTextChange(textView, 150L, action1);
    }

    public static void afterTextChange(TextView textView, long j, Action1<TextViewAfterTextChangeEvent> action1) {
        RxTextView.afterTextChangeEvents(textView).debounce(j, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(action1);
    }

    public static void longClick(View view, Action1<Void> action1) {
        longClick(view, 150L, action1);
    }

    public static void longClick(View view, long j, Action1<Void> action1) {
        RxView.longClicks(view).throttleFirst(j, TimeUnit.MILLISECONDS).subscribe(action1);
    }

    public static void longClick(View view, Func1<Void, Boolean> func1, Action1<Void> action1) {
        longClick(view, 150L, func1, action1);
    }

    public static void longClick(View view, long j, Func1<Void, Boolean> func1, Action1<Void> action1) {
        RxView.longClicks(view).throttleFirst(j, TimeUnit.MILLISECONDS).filter(func1).subscribe(action1);
    }

    public static void longClick(View view, Func1<Void, Boolean> func1, Func1<Void, Boolean> func12, Action1<Void> action1) {
        longClick(view, 150L, func1, func12, action1);
    }

    public static void longClick(View view, long j, Func1<Void, Boolean> func1, Func1<Void, Boolean> func12, Action1<Void> action1) {
        RxView.longClicks(view).throttleFirst(j, TimeUnit.MILLISECONDS).filter(func1).filter(func12).subscribe(action1);
    }
}