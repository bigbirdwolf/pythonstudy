package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.os.Environment;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.e.b;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.security.mobile.module.http.model.c;
import com.alipay.security.mobile.module.http.model.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/* loaded from: classes.dex */
public final class a {
    private Context a;
    private com.alipay.apmobilesecuritysdk.b.a b = com.alipay.apmobilesecuritysdk.b.a.a();
    private int c = 4;

    public a(Context context) {
        this.a = context;
    }

    public static String a(Context context) {
        String b = b(context);
        return com.alipay.security.mobile.module.a.a.a(b) ? h.f(context) : b;
    }

    public static String a(Context context, String str) {
        try {
            b();
            String a = i.a(str);
            if (com.alipay.security.mobile.module.a.a.a(a)) {
                String a2 = g.a(context, str);
                i.a(str, a2);
                return !com.alipay.security.mobile.module.a.a.a(a2) ? a2 : "";
            }
            return a;
        } catch (Throwable unused) {
            return "";
        }
    }

    private static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int random = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        for (int i = 0; i < 3; i++) {
            try {
                String[] split = strArr[i].split(" ");
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(parse2);
                    calendar.add(13, random);
                    Date time = calendar.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private c b(Map<String, String> map) {
        b b;
        b c;
        try {
            Context context = this.a;
            d dVar = new d();
            String a = com.alipay.security.mobile.module.a.a.a(map, "appName", "");
            String a2 = com.alipay.security.mobile.module.a.a.a(map, "sessionId", "");
            String a3 = com.alipay.security.mobile.module.a.a.a(map, "rpcVersion", "");
            String a4 = a(context, a);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String d = h.d(context);
            if (com.alipay.security.mobile.module.a.a.b(a2)) {
                dVar.d(a2);
            } else {
                dVar.d(a4);
            }
            dVar.e(securityToken);
            dVar.f(d);
            dVar.b("android");
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            com.alipay.apmobilesecuritysdk.e.c c2 = com.alipay.apmobilesecuritysdk.e.d.c(context);
            if (c2 != null) {
                str2 = c2.a();
                str3 = c2.c();
            }
            if (com.alipay.security.mobile.module.a.a.a(str2) && (c = com.alipay.apmobilesecuritysdk.e.a.c(context)) != null) {
                str2 = c.a();
                str3 = c.c();
            }
            com.alipay.apmobilesecuritysdk.e.c b2 = com.alipay.apmobilesecuritysdk.e.d.b();
            if (b2 != null) {
                str = b2.a();
                str4 = b2.c();
            }
            if (com.alipay.security.mobile.module.a.a.a(str) && (b = com.alipay.apmobilesecuritysdk.e.a.b()) != null) {
                str = b.a();
                str4 = b.c();
            }
            dVar.h(str2);
            dVar.g(str);
            dVar.a(a3);
            if (com.alipay.security.mobile.module.a.a.a(str2)) {
                dVar.c(str);
                dVar.i(str4);
            } else {
                dVar.c(str2);
                dVar.i(str3);
            }
            dVar.a(e.a(context, map));
            return com.alipay.security.mobile.module.http.d.b(this.a, this.b.c()).a(dVar);
        } catch (Throwable th) {
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }

    private static String b(Context context) {
        try {
            String b = i.b();
            if (com.alipay.security.mobile.module.a.a.a(b)) {
                com.alipay.apmobilesecuritysdk.e.c b2 = com.alipay.apmobilesecuritysdk.e.d.b(context);
                if (b2 != null) {
                    i.a(b2);
                    String a = b2.a();
                    if (com.alipay.security.mobile.module.a.a.b(a)) {
                        return a;
                    }
                }
                b b3 = com.alipay.apmobilesecuritysdk.e.a.b(context);
                if (b3 != null) {
                    i.a(b3);
                    String a2 = b3.a();
                    return com.alipay.security.mobile.module.a.a.b(a2) ? a2 : "";
                }
                return "";
            }
            return b;
        } catch (Throwable unused) {
            return "";
        }
    }

    private static void b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                File file = new File(externalStorageDirectory, ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00e0 A[Catch: Exception -> 0x0269, TryCatch #0 {Exception -> 0x0269, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:36:0x00cb, B:67:0x0211, B:69:0x022c, B:71:0x0232, B:73:0x0238, B:77:0x0241, B:79:0x0247, B:39:0x00e0, B:41:0x00fa, B:47:0x0107, B:48:0x0119, B:50:0x0120, B:54:0x0132, B:56:0x0194, B:58:0x019e, B:60:0x01a6, B:62:0x01b7, B:64:0x01c1, B:66:0x01c9, B:65:0x01c5, B:59:0x01a2, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd), top: B:85:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x022c A[Catch: Exception -> 0x0269, TryCatch #0 {Exception -> 0x0269, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:36:0x00cb, B:67:0x0211, B:69:0x022c, B:71:0x0232, B:73:0x0238, B:77:0x0241, B:79:0x0247, B:39:0x00e0, B:41:0x00fa, B:47:0x0107, B:48:0x0119, B:50:0x0120, B:54:0x0132, B:56:0x0194, B:58:0x019e, B:60:0x01a6, B:62:0x01b7, B:64:0x01c1, B:66:0x01c9, B:65:0x01c5, B:59:0x01a2, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd), top: B:85:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0232 A[Catch: Exception -> 0x0269, TryCatch #0 {Exception -> 0x0269, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:36:0x00cb, B:67:0x0211, B:69:0x022c, B:71:0x0232, B:73:0x0238, B:77:0x0241, B:79:0x0247, B:39:0x00e0, B:41:0x00fa, B:47:0x0107, B:48:0x0119, B:50:0x0120, B:54:0x0132, B:56:0x0194, B:58:0x019e, B:60:0x01a6, B:62:0x01b7, B:64:0x01c1, B:66:0x01c9, B:65:0x01c5, B:59:0x01a2, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd), top: B:85:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0241 A[Catch: Exception -> 0x0269, TryCatch #0 {Exception -> 0x0269, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:36:0x00cb, B:67:0x0211, B:69:0x022c, B:71:0x0232, B:73:0x0238, B:77:0x0241, B:79:0x0247, B:39:0x00e0, B:41:0x00fa, B:47:0x0107, B:48:0x0119, B:50:0x0120, B:54:0x0132, B:56:0x0194, B:58:0x019e, B:60:0x01a6, B:62:0x01b7, B:64:0x01c1, B:66:0x01c9, B:65:0x01c5, B:59:0x01a2, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd), top: B:85:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.a.a.a(java.util.Map):int");
    }
}