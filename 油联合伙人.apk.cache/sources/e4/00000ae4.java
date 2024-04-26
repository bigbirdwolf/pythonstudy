package com.bumptech.glide.gifdecoder;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
public class GifDecoder {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int DISPOSAL_BACKGROUND = 2;
    private static final int DISPOSAL_NONE = 1;
    private static final int DISPOSAL_PREVIOUS = 3;
    private static final int DISPOSAL_UNSPECIFIED = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARTIAL_DECODE = 3;
    private static final String TAG = "GifDecoder";
    private int[] act;
    private BitmapProvider bitmapProvider;
    private byte[] data;
    private int framePointer;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;
    private final byte[] block = new byte[256];
    private GifHeader header = new GifHeader();

    /* loaded from: classes.dex */
    public interface BitmapProvider {
        Bitmap obtain(int i, int i2, Bitmap.Config config);

        void release(Bitmap bitmap);
    }

    public GifDecoder(BitmapProvider bitmapProvider) {
        this.bitmapProvider = bitmapProvider;
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int i) {
        if (i < 0 || i >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i).delay;
    }

    public int getNextDelay() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            return -1;
        }
        return getDelay(this.framePointer);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    public int getLoopCount() {
        return this.header.loopCount;
    }

    public synchronized Bitmap getNextFrame() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            if (Log.isLoggable(TAG, 3)) {
                String str = TAG;
                Log.d(str, "unable to decode frame, frameCount=" + this.header.frameCount + " framePointer=" + this.framePointer);
            }
            this.status = 1;
        }
        if (this.status != 1 && this.status != 2) {
            int i = 0;
            this.status = 0;
            GifFrame gifFrame = this.header.frames.get(this.framePointer);
            int i2 = this.framePointer - 1;
            GifFrame gifFrame2 = i2 >= 0 ? this.header.frames.get(i2) : null;
            if (gifFrame.lct == null) {
                this.act = this.header.gct;
            } else {
                this.act = gifFrame.lct;
                if (this.header.bgIndex == gifFrame.transIndex) {
                    this.header.bgColor = 0;
                }
            }
            if (gifFrame.transparency) {
                int i3 = this.act[gifFrame.transIndex];
                this.act[gifFrame.transIndex] = 0;
                i = i3;
            }
            if (this.act == null) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "No Valid Color Table");
                }
                this.status = 1;
                return null;
            }
            Bitmap pixels = setPixels(gifFrame, gifFrame2);
            if (gifFrame.transparency) {
                this.act[gifFrame.transIndex] = i;
            }
            return pixels;
        }
        if (Log.isLoggable(TAG, 3)) {
            String str2 = TAG;
            Log.d(str2, "Unable to decode frame, status=" + this.status);
        }
        return null;
    }

    public int read(InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        this.data = null;
        this.mainPixels = null;
        this.mainScratch = null;
        if (this.previousImage != null) {
            this.bitmapProvider.release(this.previousImage);
        }
        this.previousImage = null;
        this.rawData = null;
    }

    public void setData(GifHeader gifHeader, byte[] bArr) {
        this.header = gifHeader;
        this.data = bArr;
        this.status = 0;
        this.framePointer = -1;
        this.rawData = ByteBuffer.wrap(bArr);
        this.rawData.rewind();
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = gifHeader.frames.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().dispose == 3) {
                this.savePrevious = true;
                break;
            }
        }
        this.mainPixels = new byte[gifHeader.width * gifHeader.height];
        this.mainScratch = new int[gifHeader.width * gifHeader.height];
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    public int read(byte[] bArr) {
        this.data = bArr;
        this.header = getHeaderParser().setData(bArr).parseHeader();
        if (bArr != null) {
            this.rawData = ByteBuffer.wrap(bArr);
            this.rawData.rewind();
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.mainPixels = new byte[this.header.width * this.header.height];
            this.mainScratch = new int[this.header.width * this.header.height];
            this.savePrevious = false;
            Iterator<GifFrame> it = this.header.frames.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            }
        }
        return this.status;
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        int i;
        int i2 = this.header.width;
        int i3 = this.header.height;
        int[] iArr = this.mainScratch;
        int i4 = 0;
        if (gifFrame2 != null && gifFrame2.dispose > 0) {
            if (gifFrame2.dispose == 2) {
                Arrays.fill(iArr, !gifFrame.transparency ? this.header.bgColor : 0);
            } else if (gifFrame2.dispose == 3 && this.previousImage != null) {
                this.previousImage.getPixels(iArr, 0, i2, 0, 0, i2, i3);
            }
        }
        decodeBitmapData(gifFrame);
        int i5 = 0;
        int i6 = 1;
        int i7 = 8;
        while (i4 < gifFrame.ih) {
            if (gifFrame.interlace) {
                if (i5 >= gifFrame.ih) {
                    i6++;
                    switch (i6) {
                        case 2:
                            i5 = 4;
                            break;
                        case 3:
                            i5 = 2;
                            i7 = 4;
                            break;
                        case 4:
                            i5 = 1;
                            i7 = 2;
                            break;
                    }
                }
                i = i5 + i7;
            } else {
                i = i5;
                i5 = i4;
            }
            int i8 = i5 + gifFrame.iy;
            if (i8 < this.header.height) {
                int i9 = i8 * this.header.width;
                int i10 = gifFrame.ix + i9;
                int i11 = gifFrame.iw + i10;
                if (this.header.width + i9 < i11) {
                    i11 = this.header.width + i9;
                }
                int i12 = gifFrame.iw * i4;
                while (i10 < i11) {
                    int i13 = i12 + 1;
                    int i14 = this.act[this.mainPixels[i12] & 255];
                    if (i14 != 0) {
                        iArr[i10] = i14;
                    }
                    i10++;
                    i12 = i13;
                }
            }
            i4++;
            i5 = i;
        }
        if (this.savePrevious && (gifFrame.dispose == 0 || gifFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            this.previousImage.setPixels(iArr, 0, i2, 0, 0, i2, i3);
        }
        Bitmap nextBitmap = getNextBitmap();
        nextBitmap.setPixels(iArr, 0, i2, 0, 0, i2, i3);
        return nextBitmap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void decodeBitmapData(GifFrame gifFrame) {
        int i;
        short s;
        if (gifFrame != null) {
            this.rawData.position(gifFrame.bufferFrameStart);
        }
        int i2 = gifFrame == null ? this.header.width * this.header.height : gifFrame.ih * gifFrame.iw;
        if (this.mainPixels == null || this.mainPixels.length < i2) {
            this.mainPixels = new byte[i2];
        }
        if (this.prefix == null) {
            this.prefix = new short[4096];
        }
        if (this.suffix == null) {
            this.suffix = new byte[4096];
        }
        if (this.pixelStack == null) {
            this.pixelStack = new byte[4097];
        }
        int read = read();
        int i3 = 1;
        int i4 = 1 << read;
        int i5 = i4 + 1;
        int i6 = i4 + 2;
        int i7 = read + 1;
        int i8 = (1 << i7) - 1;
        for (int i9 = 0; i9 < i4; i9++) {
            this.prefix[i9] = 0;
            this.suffix[i9] = (byte) i9;
        }
        int i10 = -1;
        int i11 = i7;
        int i12 = i6;
        int i13 = i8;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = -1;
        while (true) {
            if (i14 >= i2) {
                break;
            }
            int i23 = 3;
            if (i15 == 0) {
                i15 = readBlock();
                if (i15 <= 0) {
                    this.status = 3;
                    break;
                }
                i18 = 0;
            }
            i17 += (this.block[i18] & 255) << i19;
            i18 += i3;
            i15 += i10;
            int i24 = i19 + 8;
            int i25 = i20;
            int i26 = i22;
            int i27 = i14;
            int i28 = i16;
            int i29 = i12;
            int i30 = i11;
            while (i24 >= i30) {
                int i31 = i17 & i13;
                i17 >>= i30;
                i24 -= i30;
                if (i31 != i4) {
                    if (i31 > i29) {
                        this.status = i23;
                    } else if (i31 != i5) {
                        if (i26 == -1) {
                            this.pixelStack[i21] = this.suffix[i31];
                            i26 = i31;
                            i25 = i26;
                            i21++;
                        } else {
                            if (i31 >= i29) {
                                i = i7;
                                this.pixelStack[i21] = (byte) i25;
                                s = i26;
                                i21++;
                            } else {
                                i = i7;
                                s = i31;
                            }
                            while (s >= i4) {
                                this.pixelStack[i21] = this.suffix[s];
                                s = this.prefix[s];
                                i21++;
                                i24 = i24;
                            }
                            int i32 = i24;
                            int i33 = this.suffix[s] & 255;
                            int i34 = i21 + 1;
                            int i35 = i4;
                            byte b = (byte) i33;
                            this.pixelStack[i21] = b;
                            if (i29 < 4096) {
                                this.prefix[i29] = (short) i26;
                                this.suffix[i29] = b;
                                i29++;
                                if ((i29 & i13) == 0 && i29 < 4096) {
                                    i30++;
                                    i13 += i29;
                                }
                            }
                            i21 = i34;
                            while (i21 > 0) {
                                i21--;
                                this.mainPixels[i28] = this.pixelStack[i21];
                                i27++;
                                i28++;
                            }
                            i25 = i33;
                            i26 = i31;
                            i7 = i;
                            i24 = i32;
                            i4 = i35;
                        }
                        i23 = 3;
                    }
                    i22 = i26;
                    i11 = i30;
                    i12 = i29;
                    i14 = i27;
                    i16 = i28;
                    i20 = i25;
                    i3 = 1;
                    i10 = -1;
                    i19 = i24;
                    break;
                }
                i30 = i7;
                i29 = i6;
                i13 = i8;
                i26 = -1;
                i10 = -1;
            }
            i22 = i26;
            i11 = i30;
            i12 = i29;
            i14 = i27;
            i16 = i28;
            i3 = 1;
            i20 = i25;
            i19 = i24;
            i7 = i7;
        }
        while (i16 < i2) {
            this.mainPixels[i16] = 0;
            i16++;
        }
    }

    private int read() {
        try {
            return this.rawData.get() & 255;
        } catch (Exception unused) {
            this.status = 1;
            return 0;
        }
    }

    private int readBlock() {
        int read = read();
        int i = 0;
        if (read > 0) {
            while (i < read) {
                int i2 = read - i;
                try {
                    this.rawData.get(this.block, i, i2);
                    i += i2;
                } catch (Exception e) {
                    Log.w(TAG, "Error Reading Block", e);
                    this.status = 1;
                }
            }
        }
        return i;
    }

    private Bitmap getNextBitmap() {
        Bitmap obtain = this.bitmapProvider.obtain(this.header.width, this.header.height, BITMAP_CONFIG);
        if (obtain == null) {
            obtain = Bitmap.createBitmap(this.header.width, this.header.height, BITMAP_CONFIG);
        }
        setAlpha(obtain);
        return obtain;
    }

    @TargetApi(12)
    private static void setAlpha(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}