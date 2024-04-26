package rx.internal.operators;

import rx.Completable;
import rx.CompletableSubscriber;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* loaded from: classes.dex */
public final class CompletableFlatMapSingleToCompletable<T> implements Completable.OnSubscribe {
    final Func1<? super T, ? extends Completable> mapper;
    final Single<T> source;

    public CompletableFlatMapSingleToCompletable(Single<T> single, Func1<? super T, ? extends Completable> func1) {
        this.source = single;
        this.mapper = func1;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        SourceSubscriber sourceSubscriber = new SourceSubscriber(completableSubscriber, this.mapper);
        completableSubscriber.onSubscribe(sourceSubscriber);
        this.source.subscribe(sourceSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class SourceSubscriber<T> extends SingleSubscriber<T> implements CompletableSubscriber {
        final CompletableSubscriber actual;
        final Func1<? super T, ? extends Completable> mapper;

        public SourceSubscriber(CompletableSubscriber completableSubscriber, Func1<? super T, ? extends Completable> func1) {
            this.actual = completableSubscriber;
            this.mapper = func1;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            try {
                Completable call = this.mapper.call(t);
                if (call == null) {
                    onError(new NullPointerException("The mapper returned a null Completable"));
                } else {
                    call.subscribe(this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                onError(th);
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.actual.onCompleted();
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            add(subscription);
        }
    }
}