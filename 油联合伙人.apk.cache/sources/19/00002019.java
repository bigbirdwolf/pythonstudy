package rx.internal.operators;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;

/* loaded from: classes.dex */
public final class OperatorDematerialize<T> implements Observable.Operator<T, Notification<T>> {
    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Holder {
        static final OperatorDematerialize<Object> INSTANCE = new OperatorDematerialize<>();

        Holder() {
        }
    }

    public static OperatorDematerialize instance() {
        return Holder.INSTANCE;
    }

    OperatorDematerialize() {
    }

    public Subscriber<? super Notification<T>> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<Notification<T>>(subscriber) { // from class: rx.internal.operators.OperatorDematerialize.1
            boolean terminated;

            @Override // rx.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((Notification) ((Notification) obj));
            }

            public void onNext(Notification<T> notification) {
                switch (AnonymousClass2.$SwitchMap$rx$Notification$Kind[notification.getKind().ordinal()]) {
                    case 1:
                        if (this.terminated) {
                            return;
                        }
                        subscriber.onNext(notification.getValue());
                        return;
                    case 2:
                        onError(notification.getThrowable());
                        return;
                    case 3:
                        onCompleted();
                        return;
                    default:
                        onError(new IllegalArgumentException("Unsupported notification type: " + notification));
                        return;
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                subscriber.onError(th);
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                subscriber.onCompleted();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorDematerialize$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$rx$Notification$Kind = new int[Notification.Kind.values().length];

        static {
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnNext.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnError.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnCompleted.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}