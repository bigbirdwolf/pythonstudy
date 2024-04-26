package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* compiled from: UUIDTracker.java */
/* loaded from: classes.dex */
public class s extends a {
    private static final String a = "uuid";
    private static final String e = "yosuid";
    private static final String f = "23346339";
    private Context b;
    private String c;
    private String d;

    public s(Context context) {
        super(a);
        this.b = null;
        this.c = null;
        this.d = null;
        this.b = context;
        this.c = null;
        this.d = null;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor edit;
        try {
            if (TextUtils.isEmpty(a("ro.yunos.version", "")) || this.b == null || (sharedPreferences = PreferenceWrapper.getDefault(this.b)) == null) {
                return null;
            }
            String string = sharedPreferences.getString(e, "");
            if (TextUtils.isEmpty(string)) {
                this.d = b(f);
                if (!TextUtils.isEmpty(this.d) && this.b != null && sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                    edit.putString(e, this.d).commit();
                }
                return this.d;
            }
            return string;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:17|18|19|(13:38|39|41|42|43|44|(2:45|(1:47)(1:48))|49|50|22|23|(2:32|33)|(2:28|29))|21|22|23|(0)|(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00f2, code lost:
        if (r0 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x011f, code lost:
        if (r0 == null) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0121, code lost:
        r0.disconnect();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0110 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.s.b(java.lang.String):java.lang.String");
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }
}