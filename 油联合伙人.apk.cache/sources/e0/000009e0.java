package com.alipay.security.mobile.module.a;

/* loaded from: classes.dex */
public final class b {
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
        if (r4 == null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3b
            r2.<init>(r4, r5)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3b
            boolean r4 = r2.exists()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3b
            if (r4 != 0) goto L12
            return r1
        L12:
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3b
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3b
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3b
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3b
            java.lang.String r2 = "UTF-8"
            r5.<init>(r3, r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3b
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3b
        L23:
            java.lang.String r5 = r4.readLine()     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L3c
            if (r5 == 0) goto L2d
            r0.append(r5)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L3c
            goto L23
        L2d:
            r4.close()     // Catch: java.lang.Throwable -> L3f
            goto L3f
        L31:
            r5 = move-exception
            r1 = r4
            goto L35
        L34:
            r5 = move-exception
        L35:
            if (r1 == 0) goto L3a
            r1.close()     // Catch: java.lang.Throwable -> L3a
        L3a:
            throw r5
        L3b:
            r4 = r1
        L3c:
            if (r4 == 0) goto L3f
            goto L2d
        L3f:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.a.b.a(java.lang.String, java.lang.String):java.lang.String");
    }
}