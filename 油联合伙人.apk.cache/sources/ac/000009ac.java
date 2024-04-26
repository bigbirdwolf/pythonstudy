package com.alipay.sdk.tid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.app.AlipayResultActivity;
import com.alipay.sdk.util.c;
import java.util.Random;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {
    public static final String a = "alipay_tid_storage";
    public static final String b = "tidinfo";
    public static final String c = "upgraded_from_db";
    public static final String d = "tid";
    public static final String e = "client_key";
    public static final String f = "timestamp";
    public static final String g = "vimei";
    public static final String h = "vimsi";
    private static Context i;
    private static b o;
    private String j;
    private String k;
    private long l;
    private String m;
    private String n;
    private boolean p = false;

    private void o() {
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (o == null) {
                c.b("TidStorage", "getInstance");
                o = new b();
            }
            if (i == null) {
                o.b(context);
            }
            bVar = o;
        }
        return bVar;
    }

    private void b(Context context) {
        if (context != null) {
            c.b("TidStorage", "TidStorage.initialize context != null");
            i = context.getApplicationContext();
        }
        if (this.p) {
            return;
        }
        this.p = true;
        k();
        l();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private void k() {
        /*
            r8 = this;
            android.content.Context r0 = com.alipay.sdk.tid.b.i
            if (r0 != 0) goto L5
            return
        L5:
            java.lang.String r1 = "alipay_tid_storage"
            java.lang.String r2 = "upgraded_from_db"
            boolean r1 = com.alipay.sdk.tid.b.a.d(r1, r2)
            if (r1 == 0) goto L17
            java.lang.String r0 = "TidStorage"
            java.lang.String r1 = "transferTidFromOldDb: already migrated. returning"
            com.alipay.sdk.util.c.b(r0, r1)
            return
        L17:
            r1 = 0
            java.lang.String r2 = "TidStorage"
            java.lang.String r3 = "transferTidFromOldDb: tid from db: "
            com.alipay.sdk.util.c.b(r2, r3)     // Catch: java.lang.Throwable -> L72
            com.alipay.sdk.tid.a r2 = new com.alipay.sdk.tid.a     // Catch: java.lang.Throwable -> L72
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L72
            com.alipay.sdk.util.a r1 = com.alipay.sdk.util.a.a(r0)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r1 = r1.b()     // Catch: java.lang.Throwable -> L6d
            com.alipay.sdk.util.a r3 = com.alipay.sdk.util.a.a(r0)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = r3.a()     // Catch: java.lang.Throwable -> L6d
            java.lang.String r4 = r2.a(r1, r3)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r1 = r2.b(r1, r3)     // Catch: java.lang.Throwable -> L6d
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L6d
            if (r3 != 0) goto L69
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L6d
            if (r3 != 0) goto L69
            java.lang.String r3 = "TidStorage"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6d
            r5.<init>()     // Catch: java.lang.Throwable -> L6d
            java.lang.String r6 = "transferTidFromOldDb: tid from db is "
            r5.append(r6)     // Catch: java.lang.Throwable -> L6d
            r5.append(r4)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r6 = ", "
            r5.append(r6)     // Catch: java.lang.Throwable -> L6d
            r5.append(r1)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L6d
            com.alipay.sdk.util.c.b(r3, r5)     // Catch: java.lang.Throwable -> L6d
            r8.a(r4, r1)     // Catch: java.lang.Throwable -> L6d
        L69:
            r2.close()
            goto L7c
        L6d:
            r1 = move-exception
            goto L76
        L6f:
            r0 = move-exception
            r2 = r1
            goto Lb2
        L72:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
        L76:
            com.alipay.sdk.util.c.a(r1)     // Catch: java.lang.Throwable -> Lb1
            if (r2 == 0) goto L7c
            goto L69
        L7c:
            java.lang.String r1 = "TidStorage"
            java.lang.String r3 = "transferTidFromOldDb: removing database table"
            com.alipay.sdk.util.c.b(r1, r3)     // Catch: java.lang.Throwable -> L97
            com.alipay.sdk.tid.a r1 = new com.alipay.sdk.tid.a     // Catch: java.lang.Throwable -> L97
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L97
            r1.a()     // Catch: java.lang.Throwable -> L92
            r1.close()
            goto La0
        L8f:
            r0 = move-exception
            r2 = r1
            goto Lab
        L92:
            r0 = move-exception
            r2 = r1
            goto L98
        L95:
            r0 = move-exception
            goto Lab
        L97:
            r0 = move-exception
        L98:
            com.alipay.sdk.util.c.a(r0)     // Catch: java.lang.Throwable -> L95
            if (r2 == 0) goto La0
            r2.close()
        La0:
            java.lang.String r0 = "alipay_tid_storage"
            java.lang.String r1 = "upgraded_from_db"
            java.lang.String r2 = "updated"
            r3 = 0
            com.alipay.sdk.tid.b.a.a(r0, r1, r2, r3)
            return
        Lab:
            if (r2 == 0) goto Lb0
            r2.close()
        Lb0:
            throw r0
        Lb1:
            r0 = move-exception
        Lb2:
            if (r2 == 0) goto Lb7
            r2.close()
        Lb7:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.b.k():void");
    }

    public String a() {
        c.b("TidStorage", "TidStorage.getTid " + this.j);
        return this.j;
    }

    public String b() {
        c.b("TidStorage", "TidStorage.getClientKey " + this.k);
        return this.k;
    }

    public String c() {
        c.b("TidStorage", "TidStorage.getVirtualImei " + this.m);
        return this.m;
    }

    public String d() {
        c.b("TidStorage", "TidStorage.getVirtualImsi " + this.n);
        return this.n;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void l() {
        /*
            r9 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r1 = 0
            java.lang.String r2 = "alipay_tid_storage"
            java.lang.String r3 = "tidinfo"
            r4 = 1
            java.lang.String r2 = com.alipay.sdk.tid.b.a.a(r2, r3, r4)     // Catch: java.lang.Exception -> L61
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L61
            if (r3 != 0) goto L5d
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L61
            r3.<init>(r2)     // Catch: java.lang.Exception -> L61
            java.lang.String r2 = "tid"
            java.lang.String r4 = ""
            java.lang.String r2 = r3.optString(r2, r4)     // Catch: java.lang.Exception -> L61
            java.lang.String r4 = "client_key"
            java.lang.String r5 = ""
            java.lang.String r4 = r3.optString(r4, r5)     // Catch: java.lang.Exception -> L5a
            java.lang.String r5 = "timestamp"
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L57
            long r5 = r3.optLong(r5, r6)     // Catch: java.lang.Exception -> L57
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> L57
            java.lang.String r0 = "vimei"
            java.lang.String r6 = ""
            java.lang.String r0 = r3.optString(r0, r6)     // Catch: java.lang.Exception -> L54
            java.lang.String r6 = "vimsi"
            java.lang.String r7 = ""
            java.lang.String r3 = r3.optString(r6, r7)     // Catch: java.lang.Exception -> L4f
            r1 = r2
            r2 = r0
            r0 = r5
            goto L6b
        L4f:
            r3 = move-exception
            r8 = r5
            r5 = r0
            r0 = r8
            goto L65
        L54:
            r3 = move-exception
            r0 = r5
            goto L58
        L57:
            r3 = move-exception
        L58:
            r5 = r1
            goto L65
        L5a:
            r3 = move-exception
            r4 = r1
            goto L64
        L5d:
            r2 = r1
            r3 = r2
            r4 = r3
            goto L6b
        L61:
            r3 = move-exception
            r2 = r1
            r4 = r2
        L64:
            r5 = r4
        L65:
            com.alipay.sdk.util.c.a(r3)
            r3 = r1
            r1 = r2
            r2 = r5
        L6b:
            java.lang.String r5 = "TidStorage"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "TidStorage.load "
            r6.append(r7)
            r6.append(r1)
            java.lang.String r7 = " "
            r6.append(r7)
            r6.append(r4)
            java.lang.String r7 = " "
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = " "
            r6.append(r7)
            r6.append(r2)
            java.lang.String r7 = " "
            r6.append(r7)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            com.alipay.sdk.util.c.b(r5, r6)
            boolean r5 = r9.a(r1, r4, r2, r3)
            if (r5 == 0) goto Lab
            r9.m()
            goto Lb9
        Lab:
            r9.j = r1
            r9.k = r4
            long r0 = r0.longValue()
            r9.l = r0
            r9.m = r2
            r9.n = r3
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.b.l():void");
    }

    private boolean a(String str, String str2, String str3, String str4) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4);
    }

    public boolean e() {
        return TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.k) || TextUtils.isEmpty(this.m) || TextUtils.isEmpty(this.n);
    }

    private void m() {
        this.j = "";
        this.k = f();
        this.l = System.currentTimeMillis();
        this.m = n();
        this.n = n();
        a.b(a, b);
    }

    private String n() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        Random random = new Random();
        return hexString + (random.nextInt(AlipayResultActivity.a) + 1000);
    }

    public String f() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        return hexString.length() > 10 ? hexString.substring(hexString.length() - 10) : hexString;
    }

    public void g() {
        String format = String.format("TidStorage::delete > %s，%s，%s，%s，%s", this.j, this.k, Long.valueOf(this.l), this.m, this.n);
        c.b("TidStorage", "TidStorage.delete " + format);
        m();
    }

    public boolean h() {
        return e();
    }

    public Long i() {
        return Long.valueOf(this.l);
    }

    public void a(String str, String str2) {
        c.b("TidStorage", "TidStorage.save " + ("tid=" + str + ",clientKey=" + str2));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.j = str;
        this.k = str2;
        this.l = System.currentTimeMillis();
        p();
        o();
    }

    private void a(String str, String str2, String str3, String str4, Long l) {
        if (a(str, str2, str3, str4)) {
            return;
        }
        this.j = str;
        this.k = str2;
        this.m = str3;
        this.n = str4;
        if (l == null) {
            this.l = System.currentTimeMillis();
        } else {
            this.l = l.longValue();
        }
        p();
    }

    private void p() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", this.j);
            jSONObject.put(e, this.k);
            jSONObject.put(f, this.l);
            jSONObject.put(g, this.m);
            jSONObject.put(h, this.n);
            a.a(a, b, jSONObject.toString(), true);
        } catch (Exception e2) {
            c.a(e2);
        }
    }

    /* loaded from: classes.dex */
    public static class a {
        private static String a() {
            return "!@#23457";
        }

        public static boolean a(String str, String str2) {
            if (b.i == null) {
                return false;
            }
            return b.i.getSharedPreferences(str, 0).contains(str2);
        }

        public static void b(String str, String str2) {
            if (b.i == null) {
                return;
            }
            b.i.getSharedPreferences(str, 0).edit().remove(str2).apply();
        }

        public static String c(String str, String str2) {
            return a(str, str2, true);
        }

        public static boolean d(String str, String str2) {
            if (b.i == null) {
                return false;
            }
            return b.i.getSharedPreferences(str, 0).contains(str2);
        }

        public static String a(String str, String str2, boolean z) {
            String str3;
            if (b.i == null) {
                return null;
            }
            String string = b.i.getSharedPreferences(str, 0).getString(str2, null);
            if (TextUtils.isEmpty(string) || !z) {
                str3 = string;
            } else {
                String b = b();
                str3 = com.alipay.sdk.encrypt.b.b(string, b);
                if (TextUtils.isEmpty(str3)) {
                    str3 = com.alipay.sdk.encrypt.b.b(string, a());
                    if (!TextUtils.isEmpty(str3)) {
                        a(str, str2, str3, true);
                    }
                }
                if (TextUtils.isEmpty(str3)) {
                    String.format("LocalPreference::getLocalPreferences failed %s，%s", string, b);
                    c.b("TidStorage", "TidStorage.save LocalPreference::getLocalPreferences failed");
                }
            }
            c.b("TidStorage", "TidStorage.save LocalPreference::getLocalPreferences value " + string);
            return str3;
        }

        public static void a(String str, String str2, String str3) {
            a(str, str2, str3, true);
        }

        public static void a(String str, String str2, String str3, boolean z) {
            if (b.i == null) {
                return;
            }
            SharedPreferences sharedPreferences = b.i.getSharedPreferences(str, 0);
            if (z) {
                String b = b();
                String a = com.alipay.sdk.encrypt.b.a(str3, b);
                if (TextUtils.isEmpty(a)) {
                    String.format("LocalPreference::putLocalPreferences failed %s，%s", str3, b);
                }
                str3 = a;
            }
            sharedPreferences.edit().putString(str2, str3).apply();
        }

        private static String b() {
            String str = "";
            try {
                str = b.i.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                c.a(th);
            }
            if (TextUtils.isEmpty(str)) {
                str = "unknow";
            }
            return (str + "00000000").substring(0, 8);
        }
    }
}