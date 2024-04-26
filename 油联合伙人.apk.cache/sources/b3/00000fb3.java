package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int BLOCK_SIZE = 8;
    private static final int BLOCK_SIZE_MASK = 7;
    private static final int BLOCK_SIZE_POWER = 3;
    private static final int MINIMUM_DIMENSION = 40;
    private static final int MIN_DYNAMIC_RANGE = 24;
    private BitMatrix matrix;

    private static int cap(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public BitMatrix getBlackMatrix() throws NotFoundException {
        if (this.matrix != null) {
            return this.matrix;
        }
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        if (width >= 40 && height >= 40) {
            byte[] matrix = luminanceSource.getMatrix();
            int i = width >> 3;
            if ((width & 7) != 0) {
                i++;
            }
            int i2 = i;
            int i3 = height >> 3;
            if ((height & 7) != 0) {
                i3++;
            }
            int i4 = i3;
            int[][] calculateBlackPoints = calculateBlackPoints(matrix, i2, i4, width, height);
            BitMatrix bitMatrix = new BitMatrix(width, height);
            calculateThresholdForBlock(matrix, i2, i4, width, height, calculateBlackPoints, bitMatrix);
            this.matrix = bitMatrix;
        } else {
            this.matrix = super.getBlackMatrix();
        }
        return this.matrix;
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    private static void calculateThresholdForBlock(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, BitMatrix bitMatrix) {
        int i5 = i4 - 8;
        int i6 = i3 - 8;
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = i7 << 3;
            int i9 = i8 > i5 ? i5 : i8;
            int cap = cap(i7, 2, i2 - 3);
            for (int i10 = 0; i10 < i; i10++) {
                int i11 = i10 << 3;
                int i12 = i11 > i6 ? i6 : i11;
                int cap2 = cap(i10, 2, i - 3);
                int i13 = 0;
                for (int i14 = -2; i14 <= 2; i14++) {
                    int[] iArr2 = iArr[cap + i14];
                    i13 += iArr2[cap2 - 2] + iArr2[cap2 - 1] + iArr2[cap2] + iArr2[cap2 + 1] + iArr2[cap2 + 2];
                }
                thresholdBlock(bArr, i12, i9, i13 / 25, i3, bitMatrix);
            }
        }
    }

    private static void thresholdBlock(byte[] bArr, int i, int i2, int i3, int i4, BitMatrix bitMatrix) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    bitMatrix.set(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    private static int[][] calculateBlackPoints(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = 8;
        int i7 = i4 - 8;
        int i8 = i3 - 8;
        int[][] iArr = (int[][]) Array.newInstance(int.class, i2, i5);
        int i9 = 0;
        while (i9 < i2) {
            int i10 = i9 << 3;
            if (i10 > i7) {
                i10 = i7;
            }
            int i11 = 0;
            while (i11 < i5) {
                int i12 = i11 << 3;
                if (i12 > i8) {
                    i12 = i8;
                }
                int i13 = (i10 * i3) + i12;
                int i14 = 255;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                while (i15 < i6) {
                    int i18 = i17;
                    int i19 = i14;
                    int i20 = 0;
                    while (i20 < i6) {
                        int i21 = bArr[i13 + i20] & 255;
                        i16 += i21;
                        if (i21 < i19) {
                            i19 = i21;
                        }
                        if (i21 > i18) {
                            i18 = i21;
                        }
                        i20++;
                        i6 = 8;
                    }
                    if (i18 - i19 > 24) {
                        while (true) {
                            i15++;
                            i13 += i3;
                            if (i15 < 8) {
                                int i22 = 0;
                                for (int i23 = 8; i22 < i23; i23 = 8) {
                                    i16 += bArr[i13 + i22] & 255;
                                    i22++;
                                }
                            }
                        }
                    }
                    i15++;
                    i13 += i3;
                    i14 = i19;
                    i6 = 8;
                    i17 = i18;
                }
                int i24 = i16 >> 6;
                if (i17 - i14 <= 24) {
                    i24 = i14 / 2;
                    if (i9 > 0 && i11 > 0) {
                        int i25 = i9 - 1;
                        int i26 = i11 - 1;
                        int i27 = ((iArr[i25][i11] + (iArr[i9][i26] * 2)) + iArr[i25][i26]) / 4;
                        if (i14 < i27) {
                            i24 = i27;
                        }
                    }
                }
                iArr[i9][i11] = i24;
                i11++;
                i5 = i;
                i6 = 8;
            }
            i9++;
            i5 = i;
            i6 = 8;
        }
        return iArr;
    }
}