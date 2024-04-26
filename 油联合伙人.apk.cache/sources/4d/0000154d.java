package com.yltx.oil.partner.base;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/* loaded from: classes.dex */
public class RxBus {
    private static volatile RxBus defaultInstance;
    private final Subject bus = new SerializedSubject(PublishSubject.create());

    public static RxBus getDefault() {
        RxBus rxBus = defaultInstance;
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                rxBus = defaultInstance;
                if (defaultInstance == null) {
                    rxBus = new RxBus();
                    defaultInstance = rxBus;
                }
            }
        }
        return rxBus;
    }

    public void post(Object obj) {
        this.bus.onNext(obj);
    }

    public <T> Observable<T> toObserverable(Class<T> cls) {
        return (Observable<T>) this.bus.ofType(cls);
    }
}