package rx.internal.operators;

import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.exceptions.AssemblyStackTraceException;

/* loaded from: classes.dex */
public final class OnSubscribeOnAssemblyCompletable<T> implements Completable.OnSubscribe {
    public static volatile boolean fullStackTrace;
    final Completable.OnSubscribe source;
    final String stacktrace = OnSubscribeOnAssembly.createStacktrace();

    public OnSubscribeOnAssemblyCompletable(Completable.OnSubscribe onSubscribe) {
        this.source = onSubscribe;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        this.source.call(new OnAssemblyCompletableSubscriber(completableSubscriber, this.stacktrace));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class OnAssemblyCompletableSubscriber implements CompletableSubscriber {
        final CompletableSubscriber actual;
        final String stacktrace;

        public OnAssemblyCompletableSubscriber(CompletableSubscriber completableSubscriber, String str) {
            this.actual = completableSubscriber;
            this.stacktrace = str;
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.actual.onSubscribe(subscription);
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.actual.onCompleted();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            new AssemblyStackTraceException(this.stacktrace).attachTo(th);
            this.actual.onError(th);
        }
    }
}