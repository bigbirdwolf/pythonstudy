package com.google.zxing.aztec.decoder;

import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.umeng.commonsdk.proguard.e;
import com.yltx.oil.partner.utils.OfflineResource;
import java.util.Arrays;
import org.apache.commons.cli.HelpFormatter;

/* loaded from: classes.dex */
public final class Decoder {
    private AztecDetectorResult ddata;
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", "A", "B", "C", "D", "E", OfflineResource.VOICE_FEMALE, "G", "H", "I", "J", "K", "L", OfflineResource.VOICE_MALE, "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", OfflineResource.VOICE_DUXY, OfflineResource.VOICE_DUYY, "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", e.al, "b", "c", e.am, "e", "f", "g", "h", e.aq, "j", "k", "l", "m", "n", "o", e.ao, "q", "r", e.ap, e.ar, "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", a.b, "'", "(", ")", "*", "+", ",", HelpFormatter.DEFAULT_OPT_PREFIX, ".", "/", ":", i.b, "<", "=", ">", "?", "[", "]", "{", i.d, "CTRL_UL"};
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        boolean[] correctBits = correctBits(extractBits(aztecDetectorResult.getBits()));
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(correctBits), getEncodedData(correctBits), null, null);
        decoderResult.setNumBits(correctBits.length);
        return decoderResult;
    }

    public static String highLevelDecode(boolean[] zArr) {
        return getEncodedData(zArr);
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        Table table3 = table;
        int i = 0;
        while (i < length) {
            if (table2 != Table.BINARY) {
                int i2 = table2 == Table.DIGIT ? 4 : 5;
                if (length - i < i2) {
                    break;
                }
                int readCode = readCode(zArr, i, i2);
                i += i2;
                String character = getCharacter(table2, readCode);
                if (character.startsWith("CTRL_")) {
                    table3 = getTable(character.charAt(5));
                    if (character.charAt(6) != 'L') {
                        table3 = table2;
                        table2 = table3;
                    }
                } else {
                    sb.append(character);
                }
                table2 = table3;
            } else if (length - i < 5) {
                break;
            } else {
                int readCode2 = readCode(zArr, i, 5);
                int i3 = i + 5;
                if (readCode2 == 0) {
                    if (length - i3 < 11) {
                        break;
                    }
                    readCode2 = readCode(zArr, i3, 11) + 31;
                    i3 += 11;
                }
                int i4 = i3;
                int i5 = 0;
                while (true) {
                    if (i5 >= readCode2) {
                        i = i4;
                        break;
                    } else if (length - i4 < 8) {
                        i = length;
                        break;
                    } else {
                        sb.append((char) readCode(zArr, i4, 8));
                        i4 += 8;
                        i5++;
                    }
                }
                table2 = table3;
            }
        }
        return sb.toString();
    }

    private static Table getTable(char c) {
        if (c != 'B') {
            if (c != 'D') {
                if (c != 'P') {
                    switch (c) {
                        case 'L':
                            return Table.LOWER;
                        case 'M':
                            return Table.MIXED;
                        default:
                            return Table.UPPER;
                    }
                }
                return Table.PUNCT;
            }
            return Table.DIGIT;
        }
        return Table.BINARY;
    }

    private static String getCharacter(Table table, int i) {
        switch (table) {
            case UPPER:
                return UPPER_TABLE[i];
            case LOWER:
                return LOWER_TABLE[i];
            case MIXED:
                return MIXED_TABLE[i];
            case PUNCT:
                return PUNCT_TABLE[i];
            case DIGIT:
                return DIGIT_TABLE[i];
            default:
                throw new IllegalStateException("Bad table");
        }
    }

    private boolean[] correctBits(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int i = 8;
        if (this.ddata.getNbLayers() <= 2) {
            i = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.ddata.getNbLayers() <= 8) {
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.ddata.getNbLayers() <= 22) {
            i = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            i = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr.length / i;
        if (length < nbDatablocks) {
            throw FormatException.getFormatInstance();
        }
        int[] iArr = new int[length];
        int length2 = zArr.length % i;
        int i2 = 0;
        while (i2 < length) {
            iArr[i2] = readCode(zArr, length2, i);
            i2++;
            length2 += i;
        }
        try {
            new ReedSolomonDecoder(genericGF).decode(iArr, length - nbDatablocks);
            int i3 = (1 << i) - 1;
            int i4 = 0;
            for (int i5 = 0; i5 < nbDatablocks; i5++) {
                int i6 = iArr[i5];
                if (i6 == 0 || i6 == i3) {
                    throw FormatException.getFormatInstance();
                }
                if (i6 == 1 || i6 == i3 - 1) {
                    i4++;
                }
            }
            boolean[] zArr2 = new boolean[(nbDatablocks * i) - i4];
            int i7 = 0;
            for (int i8 = 0; i8 < nbDatablocks; i8++) {
                int i9 = iArr[i8];
                if (i9 == 1 || i9 == i3 - 1) {
                    Arrays.fill(zArr2, i7, (i7 + i) - 1, i9 > 1);
                    i7 += i - 1;
                } else {
                    int i10 = i - 1;
                    while (i10 >= 0) {
                        int i11 = i7 + 1;
                        zArr2[i7] = ((1 << i10) & i9) != 0;
                        i10--;
                        i7 = i11;
                    }
                }
            }
            return zArr2;
        } catch (ReedSolomonException e) {
            throw FormatException.getFormatInstance(e);
        }
    }

    private boolean[] extractBits(BitMatrix bitMatrix) {
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i = (isCompact ? 11 : 14) + (nbLayers << 2);
        int[] iArr = new int[i];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        int i2 = 2;
        if (isCompact) {
            for (int i3 = 0; i3 < iArr.length; i3++) {
                iArr[i3] = i3;
            }
        } else {
            int i4 = i / 2;
            int i5 = ((i + 1) + (((i4 - 1) / 15) * 2)) / 2;
            for (int i6 = 0; i6 < i4; i6++) {
                int i7 = (i6 / 15) + i6;
                iArr[(i4 - i6) - 1] = (i5 - i7) - 1;
                iArr[i4 + i6] = i7 + i5 + 1;
            }
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < nbLayers) {
            int i10 = ((nbLayers - i8) << i2) + (isCompact ? 9 : 12);
            int i11 = i8 << 1;
            int i12 = (i - 1) - i11;
            int i13 = 0;
            while (i13 < i10) {
                int i14 = i13 << 1;
                int i15 = 0;
                while (i15 < i2) {
                    int i16 = i11 + i15;
                    int i17 = i11 + i13;
                    zArr[i9 + i14 + i15] = bitMatrix.get(iArr[i16], iArr[i17]);
                    int i18 = i12 - i15;
                    zArr[(i10 * 2) + i9 + i14 + i15] = bitMatrix.get(iArr[i17], iArr[i18]);
                    int i19 = i12 - i13;
                    zArr[(i10 * 4) + i9 + i14 + i15] = bitMatrix.get(iArr[i18], iArr[i19]);
                    zArr[(i10 * 6) + i9 + i14 + i15] = bitMatrix.get(iArr[i19], iArr[i16]);
                    i15++;
                    isCompact = isCompact;
                    nbLayers = nbLayers;
                    i2 = 2;
                }
                i13++;
                i2 = 2;
            }
            i9 += i10 << 3;
            i8++;
            i2 = 2;
        }
        return zArr;
    }

    private static int readCode(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }

    private static byte readByte(boolean[] zArr, int i) {
        int length = zArr.length - i;
        if (length >= 8) {
            return (byte) readCode(zArr, i, 8);
        }
        return (byte) (readCode(zArr, i, length) << (8 - length));
    }

    static byte[] convertBoolArrayToByteArray(boolean[] zArr) {
        byte[] bArr = new byte[(zArr.length + 7) / 8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = readByte(zArr, i << 3);
        }
        return bArr;
    }
}