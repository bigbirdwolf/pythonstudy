package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
/* loaded from: classes.dex */
public abstract class ConcurrentSequencedCircularArrayQueue<E> extends ConcurrentCircularArrayQueue<E> {
    private static final long ARRAY_BASE;
    private static final int ELEMENT_SHIFT;
    protected final long[] sequenceBuffer;

    static {
        if (8 == UnsafeAccess.UNSAFE.arrayIndexScale(long[].class)) {
            ELEMENT_SHIFT = SPARSE_SHIFT + 3;
            ARRAY_BASE = UnsafeAccess.UNSAFE.arrayBaseOffset(long[].class) + (32 << (ELEMENT_SHIFT - SPARSE_SHIFT));
            return;
        }
        throw new IllegalStateException("Unexpected long[] element size");
    }

    public ConcurrentSequencedCircularArrayQueue(int i) {
        super(i);
        int i2 = (int) (this.mask + 1);
        this.sequenceBuffer = new long[(i2 << SPARSE_SHIFT) + 64];
        for (long j = 0; j < i2; j++) {
            soSequence(this.sequenceBuffer, calcSequenceOffset(j), j);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long calcSequenceOffset(long j) {
        return ARRAY_BASE + ((j & this.mask) << ELEMENT_SHIFT);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void soSequence(long[] jArr, long j, long j2) {
        UnsafeAccess.UNSAFE.putOrderedLong(jArr, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long lvSequence(long[] jArr, long j) {
        return UnsafeAccess.UNSAFE.getLongVolatile(jArr, j);
    }
}