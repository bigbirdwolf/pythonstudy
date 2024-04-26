package rx.internal.operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.FuncN;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaHooks;

/* loaded from: classes.dex */
public final class OnSubscribeCombineLatest<T, R> implements Observable.OnSubscribe<R> {
    final int bufferSize;
    final FuncN<? extends R> combiner;
    final boolean delayError;
    final Observable<? extends T>[] sources;
    final Iterable<? extends Observable<? extends T>> sourcesIterable;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeCombineLatest(Iterable<? extends Observable<? extends T>> iterable, FuncN<? extends R> funcN) {
        this(null, iterable, funcN, RxRingBuffer.SIZE, false);
    }

    public OnSubscribeCombineLatest(Observable<? extends T>[] observableArr, Iterable<? extends Observable<? extends T>> iterable, FuncN<? extends R> funcN, int i, boolean z) {
        this.sources = observableArr;
        this.sourcesIterable = iterable;
        this.combiner = funcN;
        this.bufferSize = i;
        this.delayError = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void call(rx.Subscriber<? super R> r9) {
        /*
            r8 = this;
            rx.Observable<? extends T>[] r0 = r8.sources
            if (r0 != 0) goto L4a
            java.lang.Iterable<? extends rx.Observable<? extends T>> r0 = r8.sourcesIterable
            boolean r0 = r0 instanceof java.util.List
            if (r0 == 0) goto L1c
            java.lang.Iterable<? extends rx.Observable<? extends T>> r0 = r8.sourcesIterable
            java.util.List r0 = (java.util.List) r0
            int r1 = r0.size()
            rx.Observable[] r1 = new rx.Observable[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            rx.Observable[] r0 = (rx.Observable[]) r0
            int r1 = r0.length
            goto L4b
        L1c:
            r0 = 8
            rx.Observable[] r0 = new rx.Observable[r0]
            java.lang.Iterable<? extends rx.Observable<? extends T>> r1 = r8.sourcesIterable
            java.util.Iterator r1 = r1.iterator()
            r2 = 0
            r3 = r0
            r0 = 0
        L29:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L47
            java.lang.Object r4 = r1.next()
            rx.Observable r4 = (rx.Observable) r4
            int r5 = r3.length
            if (r0 != r5) goto L41
            int r5 = r0 >> 2
            int r5 = r5 + r0
            rx.Observable[] r5 = new rx.Observable[r5]
            java.lang.System.arraycopy(r3, r2, r5, r2, r0)
            r3 = r5
        L41:
            int r5 = r0 + 1
            r3[r0] = r4
            r0 = r5
            goto L29
        L47:
            r5 = r0
            r0 = r3
            goto L4c
        L4a:
            int r1 = r0.length
        L4b:
            r5 = r1
        L4c:
            if (r5 != 0) goto L52
            r9.onCompleted()
            return
        L52:
            rx.internal.operators.OnSubscribeCombineLatest$LatestCoordinator r1 = new rx.internal.operators.OnSubscribeCombineLatest$LatestCoordinator
            rx.functions.FuncN<? extends R> r4 = r8.combiner
            int r6 = r8.bufferSize
            boolean r7 = r8.delayError
            r2 = r1
            r3 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            r1.subscribe(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeCombineLatest.call(rx.Subscriber):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class LatestCoordinator<T, R> extends AtomicInteger implements Producer, Subscription {
        static final Object MISSING = new Object();
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final FuncN<? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested;
        final CombinerSubscriber<T, R>[] subscribers;

        public LatestCoordinator(Subscriber<? super R> subscriber, FuncN<? extends R> funcN, int i, int i2, boolean z) {
            this.actual = subscriber;
            this.combiner = funcN;
            this.bufferSize = i2;
            this.delayError = z;
            this.latest = new Object[i];
            Arrays.fill(this.latest, MISSING);
            this.subscribers = new CombinerSubscriber[i];
            this.queue = new SpscLinkedArrayQueue<>(i2);
            this.requested = new AtomicLong();
            this.error = new AtomicReference<>();
        }

        public void subscribe(Observable<? extends T>[] observableArr) {
            CombinerSubscriber<T, R>[] combinerSubscriberArr = this.subscribers;
            int length = combinerSubscriberArr.length;
            for (int i = 0; i < length; i++) {
                combinerSubscriberArr[i] = new CombinerSubscriber<>(this, i);
            }
            lazySet(0);
            this.actual.add(this);
            this.actual.setProducer(this);
            for (int i2 = 0; i2 < length && !this.cancelled; i2++) {
                observableArr[i2].subscribe((Subscriber<? super Object>) combinerSubscriberArr[i2]);
            }
        }

        @Override // rx.Producer
        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (j != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                drain();
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            if (getAndIncrement() == 0) {
                cancel(this.queue);
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancelled;
        }

        void cancel(Queue<?> queue) {
            queue.clear();
            for (CombinerSubscriber<T, R> combinerSubscriber : this.subscribers) {
                combinerSubscriber.unsubscribe();
            }
        }

        void combine(Object obj, int i) {
            boolean z;
            CombinerSubscriber<T, R> combinerSubscriber = this.subscribers[i];
            synchronized (this) {
                int length = this.latest.length;
                Object obj2 = this.latest[i];
                int i2 = this.active;
                if (obj2 == MISSING) {
                    i2++;
                    this.active = i2;
                }
                int i3 = this.complete;
                if (obj == null) {
                    i3++;
                    this.complete = i3;
                } else {
                    this.latest[i] = NotificationLite.getValue(obj);
                }
                boolean z2 = false;
                z = i2 == length;
                if (i3 == length || (obj == null && obj2 == MISSING)) {
                    z2 = true;
                }
                if (z2) {
                    this.done = true;
                } else if (obj != null && z) {
                    this.queue.offer(combinerSubscriber, this.latest.clone());
                } else if (obj == null && this.error.get() != null && (obj2 == MISSING || !this.delayError)) {
                    this.done = true;
                }
            }
            if (!z && obj != null) {
                combinerSubscriber.requestMore(1L);
            } else {
                drain();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0093, code lost:
            if (r3 == 0) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x009c, code lost:
            if (r13 == Long.MAX_VALUE) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x009e, code lost:
            rx.internal.operators.BackpressureUtils.produced(r10, r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00a1, code lost:
            r12 = addAndGet(-r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00a6, code lost:
            if (r12 != 0) goto L6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00a8, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                r19 = this;
                r7 = r19
                int r0 = r19.getAndIncrement()
                if (r0 == 0) goto L9
                return
            L9:
                rx.internal.util.atomic.SpscLinkedArrayQueue<java.lang.Object> r8 = r7.queue
                rx.Subscriber<? super R> r9 = r7.actual
                boolean r0 = r7.delayError
                java.util.concurrent.atomic.AtomicLong r10 = r7.requested
                r11 = 1
                r12 = 1
            L13:
                boolean r2 = r7.done
                boolean r3 = r8.isEmpty()
                r1 = r19
                r4 = r9
                r5 = r8
                r6 = r0
                boolean r1 = r1.checkTerminated(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L25
                return
            L25:
                long r13 = r10.get()
                r5 = 0
            L2b:
                int r1 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
                if (r1 == 0) goto L8e
                boolean r2 = r7.done
                java.lang.Object r1 = r8.peek()
                r4 = r1
                rx.internal.operators.OnSubscribeCombineLatest$CombinerSubscriber r4 = (rx.internal.operators.OnSubscribeCombineLatest.CombinerSubscriber) r4
                if (r4 != 0) goto L3d
                r16 = 1
                goto L40
            L3d:
                r1 = 0
                r16 = 0
            L40:
                r1 = r19
                r3 = r16
                r15 = r4
                r4 = r9
                r17 = r5
                r5 = r8
                r6 = r0
                boolean r1 = r1.checkTerminated(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L51
                return
            L51:
                if (r16 == 0) goto L56
                r3 = r17
                goto L8f
            L56:
                r8.poll()
                java.lang.Object r1 = r8.poll()
                java.lang.Object[] r1 = (java.lang.Object[]) r1
                if (r1 != 0) goto L71
                r7.cancelled = r11
                r7.cancel(r8)
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "Broken queue?! Sender received but not the array."
                r0.<init>(r1)
                r9.onError(r0)
                return
            L71:
                rx.functions.FuncN<? extends R> r2 = r7.combiner     // Catch: java.lang.Throwable -> L84
                java.lang.Object r1 = r2.call(r1)     // Catch: java.lang.Throwable -> L84
                r9.onNext(r1)
                r1 = 1
                r15.requestMore(r1)
                r3 = r17
                long r5 = r3 + r1
                goto L2b
            L84:
                r0 = move-exception
                r7.cancelled = r11
                r7.cancel(r8)
                r9.onError(r0)
                return
            L8e:
                r3 = r5
            L8f:
                r1 = 0
                int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r5 == 0) goto La1
                r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r5 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
                if (r5 == 0) goto La1
                rx.internal.operators.BackpressureUtils.produced(r10, r3)
            La1:
                int r1 = -r12
                int r12 = r7.addAndGet(r1)
                if (r12 != 0) goto L13
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeCombineLatest.LatestCoordinator.drain():void");
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, Queue<?> queue, boolean z3) {
            if (this.cancelled) {
                cancel(queue);
                return true;
            } else if (z) {
                if (z3) {
                    if (z2) {
                        Throwable th = this.error.get();
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onCompleted();
                        }
                        return true;
                    }
                    return false;
                }
                Throwable th2 = this.error.get();
                if (th2 != null) {
                    cancel(queue);
                    subscriber.onError(th2);
                    return true;
                } else if (z2) {
                    subscriber.onCompleted();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        void onError(Throwable th) {
            Throwable th2;
            Throwable th3;
            AtomicReference<Throwable> atomicReference = this.error;
            do {
                th2 = atomicReference.get();
                if (th2 == null) {
                    th3 = th;
                } else if (th2 instanceof CompositeException) {
                    ArrayList arrayList = new ArrayList(((CompositeException) th2).getExceptions());
                    arrayList.add(th);
                    th3 = new CompositeException(arrayList);
                } else {
                    th3 = new CompositeException(Arrays.asList(th2, th));
                }
            } while (!atomicReference.compareAndSet(th2, th3));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class CombinerSubscriber<T, R> extends Subscriber<T> {
        boolean done;
        final int index;
        final LatestCoordinator<T, R> parent;

        public CombinerSubscriber(LatestCoordinator<T, R> latestCoordinator, int i) {
            this.parent = latestCoordinator;
            this.index = i;
            request(latestCoordinator.bufferSize);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            this.parent.combine(NotificationLite.next(t), this.index);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.parent.onError(th);
            this.done = true;
            this.parent.combine(null, this.index);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.combine(null, this.index);
        }

        public void requestMore(long j) {
            request(j);
        }
    }
}