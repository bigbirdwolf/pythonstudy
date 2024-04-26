package com.alipay.sdk.encrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/* loaded from: classes.dex */
public class c {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(byte[] r6) throws java.io.IOException {
        /*
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L3e
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L3e
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3b
            r6.<init>()     // Catch: java.lang.Throwable -> L3b
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L36
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L36
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L34
        L14:
            int r3 = r1.read(r0)     // Catch: java.lang.Throwable -> L34
            r4 = -1
            if (r3 == r4) goto L20
            r4 = 0
            r2.write(r0, r4, r3)     // Catch: java.lang.Throwable -> L34
            goto L14
        L20:
            r2.flush()     // Catch: java.lang.Throwable -> L34
            r2.finish()     // Catch: java.lang.Throwable -> L34
            byte[] r0 = r6.toByteArray()     // Catch: java.lang.Throwable -> L34
            r1.close()     // Catch: java.lang.Exception -> L2d
        L2d:
            r6.close()     // Catch: java.lang.Exception -> L30
        L30:
            r2.close()     // Catch: java.lang.Exception -> L33
        L33:
            return r0
        L34:
            r0 = move-exception
            goto L43
        L36:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L43
        L3b:
            r6 = move-exception
            r2 = r0
            goto L41
        L3e:
            r6 = move-exception
            r1 = r0
            r2 = r1
        L41:
            r0 = r6
            r6 = r2
        L43:
            if (r1 == 0) goto L48
            r1.close()     // Catch: java.lang.Exception -> L48
        L48:
            if (r6 == 0) goto L4d
            r6.close()     // Catch: java.lang.Exception -> L4d
        L4d:
            if (r2 == 0) goto L52
            r2.close()     // Catch: java.lang.Exception -> L52
        L52:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.encrypt.c.a(byte[]):byte[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] b(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        GZIPInputStream gZIPInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream = null;
            byteArrayOutputStream = null;
        }
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                byte[] bArr2 = new byte[4096];
                byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = gZIPInputStream.read(bArr2, 0, bArr2.length);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused) {
                        }
                        try {
                            gZIPInputStream.close();
                        } catch (Exception unused2) {
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception unused3) {
                        }
                        throw th;
                    }
                }
                byteArrayOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (Exception unused4) {
                }
                try {
                    gZIPInputStream.close();
                } catch (Exception unused5) {
                }
                try {
                    byteArrayInputStream.close();
                } catch (Exception unused6) {
                }
                return byteArray;
            } catch (Throwable th4) {
                byteArrayOutputStream = null;
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            byteArrayOutputStream = null;
            th = th;
            gZIPInputStream = byteArrayOutputStream;
            byteArrayOutputStream.close();
            gZIPInputStream.close();
            byteArrayInputStream.close();
            throw th;
        }
    }
}