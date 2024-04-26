package com.alipay.sdk.packet;

import com.alipay.sdk.util.n;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Locale;

/* loaded from: classes.dex */
public final class c {
    private boolean a;
    private String b = n.a(24);

    public c(boolean z) {
        this.a = z;
    }

    public d a(b bVar, boolean z) {
        if (bVar == null) {
            return null;
        }
        byte[] bytes = bVar.a().getBytes();
        byte[] bytes2 = bVar.b().getBytes();
        if (z) {
            try {
                bytes2 = com.alipay.sdk.encrypt.c.a(bytes2);
            } catch (Exception unused) {
                z = false;
            }
        }
        return new d(z, this.a ? a(bytes, a(this.b, com.alipay.sdk.cons.a.c), a(this.b, bytes2)) : a(bytes, bytes2));
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.alipay.sdk.packet.b a(com.alipay.sdk.packet.d r6) {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            byte[] r2 = r6.b()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            r2 = 5
            byte[] r3 = new byte[r2]     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r1.read(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r4.<init>(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            int r3 = a(r4)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r1.read(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r4.<init>(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r1.read(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r3.<init>(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            int r2 = a(r3)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            if (r2 <= 0) goto L52
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r1.read(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            boolean r3 = r5.a     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            if (r3 == 0) goto L42
            java.lang.String r3 = r5.b     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            byte[] r2 = b(r3, r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
        L42:
            boolean r6 = r6.a()     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            if (r6 == 0) goto L4c
            byte[] r2 = com.alipay.sdk.encrypt.c.b(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
        L4c:
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r6.<init>(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            goto L53
        L52:
            r6 = r0
        L53:
            r1.close()     // Catch: java.lang.Exception -> L6b
            goto L6b
        L57:
            r6 = move-exception
            goto L62
        L59:
            r6 = move-exception
            r4 = r0
            goto L62
        L5c:
            r6 = move-exception
            r1 = r0
            goto L77
        L5f:
            r6 = move-exception
            r1 = r0
            r4 = r1
        L62:
            com.alipay.sdk.util.c.a(r6)     // Catch: java.lang.Throwable -> L76
            if (r1 == 0) goto L6a
            r1.close()     // Catch: java.lang.Exception -> L6a
        L6a:
            r6 = r0
        L6b:
            if (r4 != 0) goto L70
            if (r6 != 0) goto L70
            return r0
        L70:
            com.alipay.sdk.packet.b r0 = new com.alipay.sdk.packet.b
            r0.<init>(r4, r6)
            return r0
        L76:
            r6 = move-exception
        L77:
            if (r1 == 0) goto L7c
            r1.close()     // Catch: java.lang.Exception -> L7c
        L7c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.packet.c.a(com.alipay.sdk.packet.d):com.alipay.sdk.packet.b");
    }

    private static byte[] a(String str, String str2) {
        return com.alipay.sdk.encrypt.d.a(str, str2);
    }

    private static byte[] a(String str, byte[] bArr) {
        return com.alipay.sdk.encrypt.e.a(str, bArr);
    }

    private static byte[] b(String str, byte[] bArr) {
        return com.alipay.sdk.encrypt.e.b(str, bArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    private static byte[] a(byte[]... bArr) {
        DataOutputStream dataOutputStream;
        DataOutputStream dataOutputStream2;
        if (bArr != null) {
            ?? length = bArr.length;
            try {
                if (length != 0) {
                    try {
                        length = new ByteArrayOutputStream();
                    } catch (Exception e) {
                        e = e;
                        length = 0;
                        dataOutputStream2 = null;
                    } catch (Throwable th) {
                        th = th;
                        length = 0;
                        dataOutputStream = null;
                    }
                    try {
                        dataOutputStream2 = new DataOutputStream(length);
                    } catch (Exception e2) {
                        e = e2;
                        dataOutputStream2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        dataOutputStream = null;
                        if (length != 0) {
                            try {
                                length.close();
                            } catch (Exception unused) {
                            }
                        }
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (Exception unused2) {
                            }
                        }
                        throw th;
                    }
                    try {
                        for (byte[] bArr2 : bArr) {
                            dataOutputStream2.write(a(bArr2.length).getBytes());
                            dataOutputStream2.write(bArr2);
                        }
                        dataOutputStream2.flush();
                        byte[] byteArray = length.toByteArray();
                        try {
                            length.close();
                        } catch (Exception unused3) {
                        }
                        try {
                            dataOutputStream2.close();
                        } catch (Exception unused4) {
                            return byteArray;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        com.alipay.sdk.util.c.a(e);
                        if (length != 0) {
                            try {
                                length.close();
                            } catch (Exception unused5) {
                            }
                        }
                        if (dataOutputStream2 != null) {
                            try {
                                dataOutputStream2.close();
                            } catch (Exception unused6) {
                            }
                        }
                        return null;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return null;
    }

    private static String a(int i) {
        return String.format(Locale.getDefault(), "%05d", Integer.valueOf(i));
    }

    private static int a(String str) {
        return Integer.parseInt(str);
    }
}