package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

/* compiled from: SpmcArrayQueue.java */
@SuppressAnimalSniffer
/* loaded from: classes.dex */
abstract class SpmcArrayQueueProducerIndexCacheField<E> extends SpmcArrayQueueMidPad<E> {
    private volatile long producerIndexCache;

    public SpmcArrayQueueProducerIndexCacheField(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long lvProducerIndexCache() {
        return this.producerIndexCache;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void svProducerIndexCache(long j) {
        this.producerIndexCache = j;
    }
}