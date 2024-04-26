package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.internal.util.atomic.MpscLinkedAtomicQueue;
import rx.internal.util.unsafe.MpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes.dex */
public final class CompletableOnSubscribeMergeDelayErrorIterable implements Completable.OnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeMergeDelayErrorIterable(Iterable<? extends Completable> iterable) {
        this.sources = iterable;
    }

    @Override // rx.functions.Action1
    public void call(final CompletableSubscriber completableSubscriber) {
        Queue mpscLinkedAtomicQueue;
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        completableSubscriber.onSubscribe(compositeSubscription);
        try {
            Iterator<? extends Completable> it = this.sources.iterator();
            if (it == null) {
                completableSubscriber.onError(new NullPointerException("The source iterator returned is null"));
                return;
            }
            final AtomicInteger atomicInteger = new AtomicInteger(1);
            if (UnsafeAccess.isUnsafeAvailable()) {
                mpscLinkedAtomicQueue = new MpscLinkedQueue();
            } else {
                mpscLinkedAtomicQueue = new MpscLinkedAtomicQueue();
            }
            final Queue queue = mpscLinkedAtomicQueue;
            while (!compositeSubscription.isUnsubscribed()) {
                try {
                    if (it.hasNext()) {
                        if (compositeSubscription.isUnsubscribed()) {
                            return;
                        }
                        try {
                            Completable next = it.next();
                            if (compositeSubscription.isUnsubscribed()) {
                                return;
                            }
                            if (next == null) {
                                queue.offer(new NullPointerException("A completable source is null"));
                                if (atomicInteger.decrementAndGet() == 0) {
                                    if (queue.isEmpty()) {
                                        completableSubscriber.onCompleted();
                                        return;
                                    } else {
                                        completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                        return;
                                    }
                                }
                                return;
                            }
                            atomicInteger.getAndIncrement();
                            next.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeDelayErrorIterable.1
                                @Override // rx.CompletableSubscriber
                                public void onSubscribe(Subscription subscription) {
                                    compositeSubscription.add(subscription);
                                }

                                @Override // rx.CompletableSubscriber
                                public void onError(Throwable th) {
                                    queue.offer(th);
                                    tryTerminate();
                                }

                                @Override // rx.CompletableSubscriber
                                public void onCompleted() {
                                    tryTerminate();
                                }

                                void tryTerminate() {
                                    if (atomicInteger.decrementAndGet() == 0) {
                                        if (queue.isEmpty()) {
                                            completableSubscriber.onCompleted();
                                        } else {
                                            completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                        }
                                    }
                                }
                            });
                        } catch (Throwable th) {
                            queue.offer(th);
                            if (atomicInteger.decrementAndGet() == 0) {
                                if (queue.isEmpty()) {
                                    completableSubscriber.onCompleted();
                                    return;
                                } else {
                                    completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                    return;
                                }
                            }
                            return;
                        }
                    } else if (atomicInteger.decrementAndGet() == 0) {
                        if (queue.isEmpty()) {
                            completableSubscriber.onCompleted();
                            return;
                        } else {
                            completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    queue.offer(th2);
                    if (atomicInteger.decrementAndGet() == 0) {
                        if (queue.isEmpty()) {
                            completableSubscriber.onCompleted();
                            return;
                        } else {
                            completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                            return;
                        }
                    }
                    return;
                }
            }
        } catch (Throwable th3) {
            completableSubscriber.onError(th3);
        }
    }
}