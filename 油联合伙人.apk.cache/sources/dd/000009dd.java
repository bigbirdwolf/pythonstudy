package com.alipay.security.mobile.module.a.a;

import com.facebook.stetho.dumpapp.Framer;
import com.umeng.commonsdk.proguard.ap;

/* loaded from: classes.dex */
public final class a {
    private static char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, ap.k, ap.l, ap.m, ap.n, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, Framer.STDIN_FRAME_PREFIX, 46, 47, 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, -1, -1, -1, -1, -1};

    /* JADX WARN: Code restructure failed: missing block: B:36:0x007c, code lost:
        if (r2 == (-1)) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007e, code lost:
        r0.append((char) (r2 | ((r5 & 3) << 6)));
        r2 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(java.lang.String r8) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "US-ASCII"
            byte[] r8 = r8.getBytes(r1)
            int r1 = r8.length
            r2 = 0
        Ld:
            if (r2 >= r1) goto L45
        Lf:
            byte[] r3 = com.alipay.security.mobile.module.a.a.a.b
            int r4 = r2 + 1
            r2 = r8[r2]
            r2 = r3[r2]
            r3 = -1
            if (r4 >= r1) goto L1f
            if (r2 == r3) goto L1d
            goto L1f
        L1d:
            r2 = r4
            goto Lf
        L1f:
            if (r2 == r3) goto L45
        L21:
            byte[] r5 = com.alipay.security.mobile.module.a.a.a.b
            int r6 = r4 + 1
            r4 = r8[r4]
            r4 = r5[r4]
            if (r6 >= r1) goto L30
            if (r4 == r3) goto L2e
            goto L30
        L2e:
            r4 = r6
            goto L21
        L30:
            if (r4 == r3) goto L45
            int r2 = r2 << 2
            r5 = r4 & 48
            int r5 = r5 >>> 4
            r2 = r2 | r5
            char r2 = (char) r2
            r0.append(r2)
        L3d:
            int r2 = r6 + 1
            r5 = r8[r6]
            r6 = 61
            if (r5 != r6) goto L50
        L45:
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "iso8859-1"
            byte[] r8 = r8.getBytes(r0)
            return r8
        L50:
            byte[] r7 = com.alipay.security.mobile.module.a.a.a.b
            r5 = r7[r5]
            if (r2 >= r1) goto L5b
            if (r5 == r3) goto L59
            goto L5b
        L59:
            r6 = r2
            goto L3d
        L5b:
            if (r5 == r3) goto L45
            r4 = r4 & 15
            int r4 = r4 << 4
            r7 = r5 & 60
            int r7 = r7 >>> 2
            r4 = r4 | r7
            char r4 = (char) r4
            r0.append(r4)
        L6a:
            int r4 = r2 + 1
            r2 = r8[r2]
            if (r2 != r6) goto L71
            goto L45
        L71:
            byte[] r7 = com.alipay.security.mobile.module.a.a.a.b
            r2 = r7[r2]
            if (r4 >= r1) goto L7c
            if (r2 == r3) goto L7a
            goto L7c
        L7a:
            r2 = r4
            goto L6a
        L7c:
            if (r2 == r3) goto L45
            r3 = r5 & 3
            int r3 = r3 << 6
            r2 = r2 | r3
            char r2 = (char) r2
            r0.append(r2)
            r2 = r4
            goto Ld
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.a.a.a.a(java.lang.String):byte[]");
    }
}