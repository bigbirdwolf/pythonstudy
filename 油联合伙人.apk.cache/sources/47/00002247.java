package rx.subjects;

import android.support.v7.widget.ActivityChooserView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

/* loaded from: classes.dex */
public final class ReplaySubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    final ReplayState<T> state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ReplayBuffer<T> {
        void complete();

        void drain(ReplayProducer<T> replayProducer);

        Throwable error();

        void error(Throwable th);

        boolean isComplete();

        boolean isEmpty();

        T last();

        void next(T t);

        int size();

        T[] toArray(T[] tArr);
    }

    public static <T> ReplaySubject<T> create() {
        return create(16);
    }

    public static <T> ReplaySubject<T> create(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("capacity > 0 required but it was " + i);
        }
        return new ReplaySubject<>(new ReplayState(new ReplayUnboundedBuffer(i)));
    }

    static <T> ReplaySubject<T> createUnbounded() {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeBoundBuffer(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)));
    }

    static <T> ReplaySubject<T> createUnboundedTime() {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeAndTimeBoundBuffer(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Long.MAX_VALUE, Schedulers.immediate())));
    }

    public static <T> ReplaySubject<T> createWithSize(int i) {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeBoundBuffer(i)));
    }

    public static <T> ReplaySubject<T> createWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return createWithTimeAndSize(j, timeUnit, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, scheduler);
    }

    public static <T> ReplaySubject<T> createWithTimeAndSize(long j, TimeUnit timeUnit, int i, Scheduler scheduler) {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeAndTimeBoundBuffer(i, timeUnit.toMillis(j), scheduler)));
    }

    ReplaySubject(ReplayState<T> replayState) {
        super(replayState);
        this.state = replayState;
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.state.onNext(t);
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.state.onError(th);
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.state.onCompleted();
    }

    int subscriberCount() {
        return this.state.get().length;
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.get().length != 0;
    }

    public boolean hasThrowable() {
        return this.state.isTerminated() && this.state.buffer.error() != null;
    }

    public boolean hasCompleted() {
        return this.state.isTerminated() && this.state.buffer.error() == null;
    }

    public Throwable getThrowable() {
        if (this.state.isTerminated()) {
            return this.state.buffer.error();
        }
        return null;
    }

    public int size() {
        return this.state.buffer.size();
    }

    public boolean hasAnyValue() {
        return !this.state.buffer.isEmpty();
    }

    public boolean hasValue() {
        return hasAnyValue();
    }

    public T[] getValues(T[] tArr) {
        return this.state.buffer.toArray(tArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] values = getValues(EMPTY_ARRAY);
        return values == EMPTY_ARRAY ? new Object[0] : values;
    }

    public T getValue() {
        return this.state.buffer.last();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ReplayState<T> extends AtomicReference<ReplayProducer<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
        static final ReplayProducer[] EMPTY = new ReplayProducer[0];
        static final ReplayProducer[] TERMINATED = new ReplayProducer[0];
        private static final long serialVersionUID = 5952362471246910544L;
        final ReplayBuffer<T> buffer;

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public ReplayState(ReplayBuffer<T> replayBuffer) {
            this.buffer = replayBuffer;
            lazySet(EMPTY);
        }

        public void call(Subscriber<? super T> subscriber) {
            ReplayProducer<T> replayProducer = new ReplayProducer<>(subscriber, this);
            subscriber.add(replayProducer);
            subscriber.setProducer(replayProducer);
            if (add(replayProducer) && replayProducer.isUnsubscribed()) {
                remove(replayProducer);
            } else {
                this.buffer.drain(replayProducer);
            }
        }

        boolean add(ReplayProducer<T> replayProducer) {
            ReplayProducer<T>[] replayProducerArr;
            ReplayProducer[] replayProducerArr2;
            do {
                replayProducerArr = get();
                if (replayProducerArr == TERMINATED) {
                    return false;
                }
                int length = replayProducerArr.length;
                replayProducerArr2 = new ReplayProducer[length + 1];
                System.arraycopy(replayProducerArr, 0, replayProducerArr2, 0, length);
                replayProducerArr2[length] = replayProducer;
            } while (!compareAndSet(replayProducerArr, replayProducerArr2));
            return true;
        }

        void remove(ReplayProducer<T> replayProducer) {
            ReplayProducer<T>[] replayProducerArr;
            ReplayProducer[] replayProducerArr2;
            do {
                replayProducerArr = get();
                if (replayProducerArr == TERMINATED || replayProducerArr == EMPTY) {
                    return;
                }
                int length = replayProducerArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (replayProducerArr[i2] == replayProducer) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    replayProducerArr2 = EMPTY;
                } else {
                    ReplayProducer[] replayProducerArr3 = new ReplayProducer[length - 1];
                    System.arraycopy(replayProducerArr, 0, replayProducerArr3, 0, i);
                    System.arraycopy(replayProducerArr, i + 1, replayProducerArr3, i, (length - i) - 1);
                    replayProducerArr2 = replayProducerArr3;
                }
            } while (!compareAndSet(replayProducerArr, replayProducerArr2));
        }

        @Override // rx.Observer
        public void onNext(T t) {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.next(t);
            for (ReplayProducer<T> replayProducer : get()) {
                replayBuffer.drain(replayProducer);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.error(th);
            ArrayList arrayList = null;
            for (ReplayProducer<T> replayProducer : getAndSet(TERMINATED)) {
                try {
                    replayBuffer.drain(replayProducer);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }

        @Override // rx.Observer
        public void onCompleted() {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.complete();
            for (ReplayProducer<T> replayProducer : getAndSet(TERMINATED)) {
                replayBuffer.drain(replayProducer);
            }
        }

        boolean isTerminated() {
            return get() == TERMINATED;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ReplayUnboundedBuffer<T> implements ReplayBuffer<T> {
        final int capacity;
        volatile boolean done;
        Throwable error;
        final Object[] head;
        volatile int size;
        Object[] tail;
        int tailIndex;

        public ReplayUnboundedBuffer(int i) {
            this.capacity = i;
            Object[] objArr = new Object[i + 1];
            this.head = objArr;
            this.tail = objArr;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            if (this.done) {
                return;
            }
            int i = this.tailIndex;
            Object[] objArr = this.tail;
            if (i == objArr.length - 1) {
                Object[] objArr2 = new Object[objArr.length];
                objArr2[0] = t;
                this.tailIndex = 1;
                objArr[i] = objArr2;
                this.tail = objArr2;
            } else {
                objArr[i] = t;
                this.tailIndex = i + 1;
            }
            this.size++;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            this.done = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x006e, code lost:
            if (r8 != r6) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0074, code lost:
            if (r2.isUnsubscribed() == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0076, code lost:
            r18.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0078, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
            r12 = r17.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x007d, code lost:
            if (r10 != r17.size) goto L41;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007f, code lost:
            r16 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0081, code lost:
            if (r12 == false) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0083, code lost:
            if (r16 == 0) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0085, code lost:
            r18.node = null;
            r1 = r17.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0089, code lost:
            if (r1 == null) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x008b, code lost:
            r2.onError(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x008f, code lost:
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0092, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0097, code lost:
            if (r8 == 0) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00a0, code lost:
            if (r6 == Long.MAX_VALUE) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00a2, code lost:
            rx.internal.operators.BackpressureUtils.produced(r18.requested, r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00a7, code lost:
            r18.index = r10;
            r18.tailIndex = r13;
            r18.node = r14;
            r5 = r18.addAndGet(-r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:?, code lost:
            return;
         */
        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drain(rx.subjects.ReplaySubject.ReplayProducer<T> r18) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                int r2 = r18.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                int r3 = r0.capacity
                r4 = 1
                r5 = 1
            L11:
                java.util.concurrent.atomic.AtomicLong r6 = r1.requested
                long r6 = r6.get()
                java.lang.Object r8 = r1.node
                java.lang.Object[] r8 = (java.lang.Object[]) r8
                if (r8 != 0) goto L1f
                java.lang.Object[] r8 = r0.head
            L1f:
                int r9 = r1.tailIndex
                int r10 = r1.index
                r14 = r8
                r13 = r9
                r8 = 0
            L27:
                int r15 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                r16 = 0
                r11 = 0
                if (r15 == 0) goto L6c
                boolean r12 = r2.isUnsubscribed()
                if (r12 == 0) goto L37
                r1.node = r11
                return
            L37:
                boolean r12 = r0.done
                int r15 = r0.size
                if (r10 != r15) goto L3f
                r15 = 1
                goto L40
            L3f:
                r15 = 0
            L40:
                if (r12 == 0) goto L52
                if (r15 == 0) goto L52
                r1.node = r11
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L4e
                r2.onError(r1)
                goto L51
            L4e:
                r2.onCompleted()
            L51:
                return
            L52:
                if (r15 == 0) goto L55
                goto L6c
            L55:
                if (r13 != r3) goto L5d
                r11 = r14[r13]
                java.lang.Object[] r11 = (java.lang.Object[]) r11
                r14 = r11
                goto L5f
            L5d:
                r16 = r13
            L5f:
                r11 = r14[r16]
                r2.onNext(r11)
                r11 = 1
                long r8 = r8 + r11
                int r13 = r16 + 1
                int r10 = r10 + 1
                goto L27
            L6c:
                int r12 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r12 != 0) goto L93
                boolean r12 = r2.isUnsubscribed()
                if (r12 == 0) goto L79
                r1.node = r11
                return
            L79:
                boolean r12 = r0.done
                int r15 = r0.size
                if (r10 != r15) goto L81
                r16 = 1
            L81:
                if (r12 == 0) goto L93
                if (r16 == 0) goto L93
                r1.node = r11
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L8f
                r2.onError(r1)
                goto L92
            L8f:
                r2.onCompleted()
            L92:
                return
            L93:
                r11 = 0
                int r15 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
                if (r15 == 0) goto La7
                r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r15 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                if (r15 == 0) goto La7
                java.util.concurrent.atomic.AtomicLong r6 = r1.requested
                rx.internal.operators.BackpressureUtils.produced(r6, r8)
            La7:
                r1.index = r10
                r1.tailIndex = r13
                r1.node = r14
                int r5 = -r5
                int r5 = r1.addAndGet(r5)
                if (r5 != 0) goto L11
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.ReplaySubject.ReplayUnboundedBuffer.drain(rx.subjects.ReplaySubject$ReplayProducer):void");
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            int i = this.size;
            if (i == 0) {
                return null;
            }
            Object[] objArr = this.head;
            int i2 = this.capacity;
            while (i >= i2) {
                objArr = objArr[i2];
                i -= i2;
            }
            return (T) objArr[i - 1];
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            return this.size;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return this.size == 0;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            int i = this.size;
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            Object[] objArr = this.head;
            int i2 = this.capacity;
            Object[] objArr2 = objArr;
            int i3 = 0;
            while (true) {
                int i4 = i3 + i2;
                if (i4 >= i) {
                    break;
                }
                System.arraycopy(objArr2, 0, tArr, i3, i2);
                objArr2 = objArr2[i2];
                i3 = i4;
            }
            System.arraycopy(objArr2, 0, tArr, i3, i - i3);
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }
    }

    /* loaded from: classes.dex */
    static final class ReplaySizeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile Node<T> head;
        final int limit;
        int size;
        Node<T> tail;

        public ReplaySizeBoundBuffer(int i) {
            this.limit = i;
            Node<T> node = new Node<>(null);
            this.tail = node;
            this.head = node;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            Node<T> node = new Node<>(t);
            this.tail.set(node);
            this.tail = node;
            int i = this.size;
            if (i == this.limit) {
                this.head = this.head.get();
            } else {
                this.size = i + 1;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            this.error = th;
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            this.done = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:31:0x005f, code lost:
            if (r10 != r5) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0065, code lost:
            if (r2.isUnsubscribed() == false) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0067, code lost:
            r18.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0069, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x006a, code lost:
            r12 = r17.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0070, code lost:
            if (r7.get() != null) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0072, code lost:
            r13 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0073, code lost:
            if (r12 == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0075, code lost:
            if (r13 == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0077, code lost:
            r18.node = null;
            r1 = r17.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x007b, code lost:
            if (r1 == null) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x007d, code lost:
            r2.onError(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0081, code lost:
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0084, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0087, code lost:
            if (r10 == 0) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0090, code lost:
            if (r5 == Long.MAX_VALUE) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0092, code lost:
            rx.internal.operators.BackpressureUtils.produced(r18.requested, r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x0097, code lost:
            r18.node = r7;
            r4 = r18.addAndGet(-r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:?, code lost:
            return;
         */
        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drain(rx.subjects.ReplaySubject.ReplayProducer<T> r18) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                int r2 = r18.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                r3 = 1
                r4 = 1
            Lf:
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r5 = r5.get()
                java.lang.Object r7 = r1.node
                rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node r7 = (rx.subjects.ReplaySubject.ReplaySizeBoundBuffer.Node) r7
                r8 = 0
                if (r7 != 0) goto L1f
                rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node<T> r7 = r0.head
            L1f:
                r10 = r8
            L20:
                int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                r13 = 0
                r14 = 0
                if (r12 == 0) goto L5d
                boolean r12 = r2.isUnsubscribed()
                if (r12 == 0) goto L2f
                r1.node = r14
                return
            L2f:
                boolean r12 = r0.done
                java.lang.Object r15 = r7.get()
                rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node r15 = (rx.subjects.ReplaySubject.ReplaySizeBoundBuffer.Node) r15
                if (r15 != 0) goto L3c
                r16 = 1
                goto L3e
            L3c:
                r16 = 0
            L3e:
                if (r12 == 0) goto L50
                if (r16 == 0) goto L50
                r1.node = r14
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L4c
                r2.onError(r1)
                goto L4f
            L4c:
                r2.onCompleted()
            L4f:
                return
            L50:
                if (r16 == 0) goto L53
                goto L5d
            L53:
                T r7 = r15.value
                r2.onNext(r7)
                r12 = 1
                long r10 = r10 + r12
                r7 = r15
                goto L20
            L5d:
                int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                if (r12 != 0) goto L85
                boolean r12 = r2.isUnsubscribed()
                if (r12 == 0) goto L6a
                r1.node = r14
                return
            L6a:
                boolean r12 = r0.done
                java.lang.Object r15 = r7.get()
                if (r15 != 0) goto L73
                r13 = 1
            L73:
                if (r12 == 0) goto L85
                if (r13 == 0) goto L85
                r1.node = r14
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L81
                r2.onError(r1)
                goto L84
            L81:
                r2.onCompleted()
            L84:
                return
            L85:
                int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r12 == 0) goto L97
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r12 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r12 == 0) goto L97
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                rx.internal.operators.BackpressureUtils.produced(r5, r10)
            L97:
                r1.node = r7
                int r4 = -r4
                int r4 = r1.addAndGet(r4)
                if (r4 != 0) goto Lf
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.ReplaySubject.ReplaySizeBoundBuffer.drain(rx.subjects.ReplaySubject$ReplayProducer):void");
        }

        /* loaded from: classes.dex */
        static final class Node<T> extends AtomicReference<Node<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final T value;

            public Node(T t) {
                this.value = t;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            Node<T> node = this.head;
            while (true) {
                Node<T> node2 = node.get();
                if (node2 == null) {
                    return node.value;
                }
                node = node2;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            Node<T> node = this.head.get();
            int i = 0;
            while (node != null && i != Integer.MAX_VALUE) {
                node = node.get();
                i++;
            }
            return i;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return this.head.get() == null;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (Node<T> node = this.head.get(); node != null; node = node.get()) {
                arrayList.add(node.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ReplaySizeAndTimeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile TimedNode<T> head;
        final int limit;
        final long maxAgeMillis;
        final Scheduler scheduler;
        int size;
        TimedNode<T> tail;

        public ReplaySizeAndTimeBoundBuffer(int i, long j, Scheduler scheduler) {
            this.limit = i;
            TimedNode<T> timedNode = new TimedNode<>(null, 0L);
            this.tail = timedNode;
            this.head = timedNode;
            this.maxAgeMillis = j;
            this.scheduler = scheduler;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            TimedNode<T> timedNode;
            long now = this.scheduler.now();
            TimedNode<T> timedNode2 = new TimedNode<>(t, now);
            this.tail.set(timedNode2);
            this.tail = timedNode2;
            long j = now - this.maxAgeMillis;
            int i = this.size;
            TimedNode<T> timedNode3 = this.head;
            if (i == this.limit) {
                timedNode = timedNode3.get();
            } else {
                i++;
                timedNode = timedNode3;
            }
            while (true) {
                TimedNode<T> timedNode4 = timedNode.get();
                if (timedNode4 == null || timedNode4.timestamp > j) {
                    break;
                }
                i--;
                timedNode = timedNode4;
            }
            this.size = i;
            if (timedNode != timedNode3) {
                this.head = timedNode;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            evictFinal();
            this.error = th;
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            evictFinal();
            this.done = true;
        }

        void evictFinal() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> timedNode = this.head;
            TimedNode<T> timedNode2 = timedNode;
            while (true) {
                TimedNode<T> timedNode3 = timedNode2.get();
                if (timedNode3 == null || timedNode3.timestamp > now) {
                    break;
                }
                timedNode2 = timedNode3;
            }
            if (timedNode != timedNode2) {
                this.head = timedNode2;
            }
        }

        TimedNode<T> latestHead() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> timedNode = this.head;
            while (true) {
                TimedNode<T> timedNode2 = timedNode.get();
                if (timedNode2 == null || timedNode2.timestamp > now) {
                    break;
                }
                timedNode = timedNode2;
            }
            return timedNode;
        }

        /* JADX WARN: Code restructure failed: missing block: B:31:0x0061, code lost:
            if (r10 != r5) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
            if (r2.isUnsubscribed() == false) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0069, code lost:
            r18.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006b, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x006c, code lost:
            r12 = r17.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0072, code lost:
            if (r7.get() != null) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
            r13 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0075, code lost:
            if (r12 == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0077, code lost:
            if (r13 == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0079, code lost:
            r18.node = null;
            r1 = r17.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x007d, code lost:
            if (r1 == null) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x007f, code lost:
            r2.onError(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0083, code lost:
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0086, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0089, code lost:
            if (r10 == 0) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0092, code lost:
            if (r5 == Long.MAX_VALUE) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0094, code lost:
            rx.internal.operators.BackpressureUtils.produced(r18.requested, r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x0099, code lost:
            r18.node = r7;
            r4 = r18.addAndGet(-r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:?, code lost:
            return;
         */
        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drain(rx.subjects.ReplaySubject.ReplayProducer<T> r18) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                int r2 = r18.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                r3 = 1
                r4 = 1
            Lf:
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r5 = r5.get()
                java.lang.Object r7 = r1.node
                rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode r7 = (rx.subjects.ReplaySubject.ReplaySizeAndTimeBoundBuffer.TimedNode) r7
                r8 = 0
                if (r7 != 0) goto L21
                rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode r7 = r17.latestHead()
            L21:
                r10 = r8
            L22:
                int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                r13 = 0
                r14 = 0
                if (r12 == 0) goto L5f
                boolean r12 = r2.isUnsubscribed()
                if (r12 == 0) goto L31
                r1.node = r14
                return
            L31:
                boolean r12 = r0.done
                java.lang.Object r15 = r7.get()
                rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode r15 = (rx.subjects.ReplaySubject.ReplaySizeAndTimeBoundBuffer.TimedNode) r15
                if (r15 != 0) goto L3e
                r16 = 1
                goto L40
            L3e:
                r16 = 0
            L40:
                if (r12 == 0) goto L52
                if (r16 == 0) goto L52
                r1.node = r14
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L4e
                r2.onError(r1)
                goto L51
            L4e:
                r2.onCompleted()
            L51:
                return
            L52:
                if (r16 == 0) goto L55
                goto L5f
            L55:
                T r7 = r15.value
                r2.onNext(r7)
                r12 = 1
                long r10 = r10 + r12
                r7 = r15
                goto L22
            L5f:
                int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                if (r12 != 0) goto L87
                boolean r12 = r2.isUnsubscribed()
                if (r12 == 0) goto L6c
                r1.node = r14
                return
            L6c:
                boolean r12 = r0.done
                java.lang.Object r15 = r7.get()
                if (r15 != 0) goto L75
                r13 = 1
            L75:
                if (r12 == 0) goto L87
                if (r13 == 0) goto L87
                r1.node = r14
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L83
                r2.onError(r1)
                goto L86
            L83:
                r2.onCompleted()
            L86:
                return
            L87:
                int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r12 == 0) goto L99
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r12 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r12 == 0) goto L99
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                rx.internal.operators.BackpressureUtils.produced(r5, r10)
            L99:
                r1.node = r7
                int r4 = -r4
                int r4 = r1.addAndGet(r4)
                if (r4 != 0) goto Lf
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.ReplaySubject.ReplaySizeAndTimeBoundBuffer.drain(rx.subjects.ReplaySubject$ReplayProducer):void");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public static final class TimedNode<T> extends AtomicReference<TimedNode<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final long timestamp;
            final T value;

            public TimedNode(T t, long j) {
                this.value = t;
                this.timestamp = j;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            TimedNode<T> latestHead = latestHead();
            while (true) {
                TimedNode<T> timedNode = latestHead.get();
                if (timedNode == null) {
                    return latestHead.value;
                }
                latestHead = timedNode;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            TimedNode<T> timedNode = latestHead().get();
            int i = 0;
            while (timedNode != null && i != Integer.MAX_VALUE) {
                timedNode = timedNode.get();
                i++;
            }
            return i;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return latestHead().get() == null;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (TimedNode<T> timedNode = latestHead().get(); timedNode != null; timedNode = timedNode.get()) {
                arrayList.add(timedNode.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ReplayProducer<T> extends AtomicInteger implements Producer, Subscription {
        private static final long serialVersionUID = -5006209596735204567L;
        final Subscriber<? super T> actual;
        int index;
        Object node;
        final AtomicLong requested = new AtomicLong();
        final ReplayState<T> state;
        int tailIndex;

        public ReplayProducer(Subscriber<? super T> subscriber, ReplayState<T> replayState) {
            this.actual = subscriber;
            this.state = replayState;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.state.remove(this);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.actual.isUnsubscribed();
        }

        @Override // rx.Producer
        public void request(long j) {
            if (j > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                this.state.buffer.drain(this);
            } else if (j >= 0) {
            } else {
                throw new IllegalArgumentException("n >= required but it was " + j);
            }
        }
    }
}