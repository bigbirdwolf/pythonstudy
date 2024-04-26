package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.c;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UMStoreManager.java */
/* loaded from: classes.dex */
public class g {
    public static final int a = 2049;
    public static final int b = 2050;
    private static final int c = 1000;
    private static Context d = null;
    private static String e = null;
    private static final String f = "umeng+";
    private static final String g = "ek__id";
    private static final String h = "ek_key";
    private List<String> i;
    private List<Integer> j;
    private String k;
    private List<String> l;

    /* compiled from: UMStoreManager.java */
    /* loaded from: classes.dex */
    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION,
        INSTANTSESSIONBEGIN
    }

    /* compiled from: UMStoreManager.java */
    /* loaded from: classes.dex */
    private static class b {
        private static final g a = new g();

        private b() {
        }
    }

    private g() {
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = null;
        this.l = new ArrayList();
    }

    public static g a(Context context) {
        g gVar = b.a;
        if (d == null && context != null) {
            d = context.getApplicationContext();
            gVar.k();
        }
        return gVar;
    }

    private void k() {
        synchronized (this) {
            l();
            this.i.clear();
            this.l.clear();
            this.j.clear();
        }
    }

    public void a() {
        this.i.clear();
    }

    public void b() {
        this.l.clear();
    }

    public boolean c() {
        return this.l.isEmpty();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x009a, code lost:
        if (r1 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a5, code lost:
        if (r1 == null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a7, code lost:
        r1.endTransaction();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(org.json.JSONArray r8) {
        /*
            r7 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> La4 android.database.sqlite.SQLiteDatabaseCorruptException -> Lb4
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> La4 android.database.sqlite.SQLiteDatabaseCorruptException -> Lb4
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> La4 android.database.sqlite.SQLiteDatabaseCorruptException -> Lb4
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L9d android.database.sqlite.SQLiteDatabaseCorruptException -> L9f java.lang.Throwable -> La5
            r2 = 0
        Lf:
            int r3 = r8.length()     // Catch: java.lang.Throwable -> L9d android.database.sqlite.SQLiteDatabaseCorruptException -> L9f java.lang.Throwable -> La5
            if (r2 >= r3) goto L97
            org.json.JSONObject r3 = r8.getJSONObject(r2)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            r4.<init>()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r5 = "__i"
            java.lang.String r5 = r3.optString(r5)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            if (r6 != 0) goto L32
            java.lang.String r6 = "-1"
            boolean r6 = r6.equals(r5)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            if (r6 == 0) goto L42
        L32:
            com.umeng.analytics.pro.q r5 = com.umeng.analytics.pro.q.a()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r5 = r5.b()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            if (r6 == 0) goto L42
            java.lang.String r5 = "-1"
        L42:
            java.lang.String r6 = "__i"
            r4.put(r6, r5)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r5 = "__e"
            java.lang.String r6 = "id"
            java.lang.String r6 = r3.optString(r6)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            r4.put(r5, r6)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r5 = "__t"
            java.lang.String r6 = "__t"
            int r6 = r3.optInt(r6)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            r4.put(r5, r6)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r5 = "__av"
            android.content.Context r6 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r6)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            r4.put(r5, r6)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r5 = "__vc"
            android.content.Context r6 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r6)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            r4.put(r5, r6)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r5 = "__i"
            r3.remove(r5)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r5 = "__t"
            r3.remove(r5)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r5 = "__s"
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r3 = r7.c(r3)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            r4.put(r5, r3)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
            java.lang.String r3 = "__et"
            r1.insert(r3, r0, r4)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9d java.lang.Throwable -> La5
        L93:
            int r2 = r2 + 1
            goto Lf
        L97:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L9d android.database.sqlite.SQLiteDatabaseCorruptException -> L9f java.lang.Throwable -> La5
            if (r1 == 0) goto Laa
            goto La7
        L9d:
            r8 = move-exception
            goto Lc0
        L9f:
            r0 = r1
            goto Lb4
        La1:
            r8 = move-exception
            r1 = r0
            goto Lc0
        La4:
            r1 = r0
        La5:
            if (r1 == 0) goto Laa
        La7:
            r1.endTransaction()     // Catch: java.lang.Throwable -> Laa
        Laa:
            android.content.Context r8 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r8 = com.umeng.analytics.pro.e.a(r8)
            r8.b()
            goto Lbf
        Lb4:
            android.content.Context r8 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> La1
            com.umeng.analytics.pro.f.a(r8)     // Catch: java.lang.Throwable -> La1
            if (r0 == 0) goto Laa
            r0.endTransaction()     // Catch: java.lang.Throwable -> Laa
            goto Laa
        Lbf:
            return
        Lc0:
            if (r1 == 0) goto Lc5
            r1.endTransaction()     // Catch: java.lang.Throwable -> Lc5
        Lc5:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(org.json.JSONArray):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0059, code lost:
        if (r1 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005b, code lost:
        r1.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x004e, code lost:
        if (r1 != null) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(java.lang.String r5, java.lang.String r6, int r7) {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L55 java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch: java.lang.Throwable -> L55 java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L55 java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            r2.<init>()     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            java.lang.String r3 = "__i"
            r2.put(r3, r5)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            java.lang.String r5 = r4.c(r6)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            if (r6 != 0) goto L4b
            java.lang.String r6 = "__a"
            r2.put(r6, r5)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            java.lang.String r5 = "__t"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            r2.put(r5, r6)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            java.lang.String r5 = "__av"
            android.content.Context r6 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r6)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            r2.put(r5, r6)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            java.lang.String r5 = "__vc"
            android.content.Context r6 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r6)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            r2.put(r5, r6)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            java.lang.String r5 = "__er"
            r1.insert(r5, r0, r2)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
        L4b:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteDatabaseCorruptException -> L53 java.lang.Throwable -> L59
            if (r1 == 0) goto L5e
            goto L5b
        L51:
            r5 = move-exception
            goto L75
        L53:
            r0 = r1
            goto L68
        L55:
            r5 = move-exception
            r1 = r0
            goto L75
        L58:
            r1 = r0
        L59:
            if (r1 == 0) goto L5e
        L5b:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L5e
        L5e:
            android.content.Context r5 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r5 = com.umeng.analytics.pro.e.a(r5)
            r5.b()
            goto L73
        L68:
            android.content.Context r5 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L55
            com.umeng.analytics.pro.f.a(r5)     // Catch: java.lang.Throwable -> L55
            if (r0 == 0) goto L5e
            r0.endTransaction()     // Catch: java.lang.Throwable -> L5e
            goto L5e
        L73:
            r5 = 0
            return r5
        L75:
            if (r1 == 0) goto L7a
            r1.endTransaction()     // Catch: java.lang.Throwable -> L7a
        L7a:
            android.content.Context r6 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r6 = com.umeng.analytics.pro.e.a(r6)
            r6.b()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(java.lang.String, java.lang.String, int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006e, code lost:
        if (r1 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007b, code lost:
        if (r1 == null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007d, code lost:
        r1.endTransaction();
        r0 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d() {
        /*
            r7 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L8a
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L8a
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L7a android.database.sqlite.SQLiteDatabaseCorruptException -> L8a
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            com.umeng.analytics.pro.q r0 = com.umeng.analytics.pro.q.a()     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            java.lang.String r0 = r0.c()     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            if (r2 == 0) goto L2b
            if (r1 == 0) goto L21
            r1.endTransaction()     // Catch: java.lang.Throwable -> L21
        L21:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            return
        L2b:
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            java.lang.String r3 = ""
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            java.lang.String r3 = "-1"
            r5 = 1
            r2[r5] = r3     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
        L38:
            int r3 = r2.length     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            if (r4 >= r3) goto L6b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            r3.<init>()     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            java.lang.String r5 = "update __et set __i=\""
            r3.append(r5)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            r3.append(r0)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            java.lang.String r5 = "\" where "
            r3.append(r5)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            java.lang.String r5 = "__i"
            r3.append(r5)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            java.lang.String r5 = "=\""
            r3.append(r5)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            r5 = r2[r4]     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            r3.append(r5)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            java.lang.String r5 = "\""
            r3.append(r5)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            r1.execSQL(r3)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            int r4 = r4 + 1
            goto L38
        L6b:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteDatabaseCorruptException -> L73 java.lang.Throwable -> L7b
            if (r1 == 0) goto L80
            goto L7d
        L71:
            r0 = move-exception
            goto L96
        L73:
            r0 = r1
            goto L8a
        L75:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L96
        L7a:
            r1 = r0
        L7b:
            if (r1 == 0) goto L80
        L7d:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L80
        L80:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L95
        L8a:
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L75
            com.umeng.analytics.pro.f.a(r1)     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L80
            r0.endTransaction()     // Catch: java.lang.Throwable -> L80
            goto L80
        L95:
            return
        L96:
            if (r1 == 0) goto L9b
            r1.endTransaction()     // Catch: java.lang.Throwable -> L9b
        L9b:
            android.content.Context r1 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)
            r1.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.d():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x007f, code lost:
        if (r2 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008a, code lost:
        if (r2 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008c, code lost:
        r2.endTransaction();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(java.lang.String r6, org.json.JSONObject r7, com.umeng.analytics.pro.g.a r8) {
        /*
            r5 = this;
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            r1 = 0
            android.content.Context r2 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> L89 android.database.sqlite.SQLiteDatabaseCorruptException -> L99
            com.umeng.analytics.pro.e r2 = com.umeng.analytics.pro.e.a(r2)     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> L89 android.database.sqlite.SQLiteDatabaseCorruptException -> L99
            android.database.sqlite.SQLiteDatabase r2 = r2.a()     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> L89 android.database.sqlite.SQLiteDatabaseCorruptException -> L99
            r2.beginTransaction()     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            com.umeng.analytics.pro.g$a r3 = com.umeng.analytics.pro.g.a.BEGIN     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            if (r8 != r3) goto L51
            java.lang.String r8 = "__e"
            java.lang.Object r7 = r7.opt(r8)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            java.lang.Long r7 = (java.lang.Long) r7     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            long r7 = r7.longValue()     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            r3.<init>()     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            java.lang.String r4 = "__ii"
            r3.put(r4, r6)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            java.lang.String r6 = "__e"
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            r3.put(r6, r7)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            java.lang.String r6 = "__av"
            android.content.Context r7 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            java.lang.String r7 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r7)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            r3.put(r6, r7)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            java.lang.String r6 = "__vc"
            android.content.Context r7 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            java.lang.String r7 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r7)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            r3.put(r6, r7)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            java.lang.String r6 = "__sd"
            r2.insert(r6, r1, r3)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            goto L7c
        L51:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.INSTANTSESSIONBEGIN     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            if (r8 != r1) goto L59
            r5.b(r6, r7, r2)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            goto L7c
        L59:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.END     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            if (r8 != r1) goto L61
            r5.a(r6, r7, r2)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            goto L7c
        L61:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.PAGE     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            if (r8 != r1) goto L6b
            java.lang.String r8 = "__a"
            r5.a(r6, r7, r2, r8)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            goto L7c
        L6b:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.AUTOPAGE     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            if (r8 != r1) goto L75
            java.lang.String r8 = "__b"
            r5.a(r6, r7, r2, r8)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            goto L7c
        L75:
            com.umeng.analytics.pro.g$a r1 = com.umeng.analytics.pro.g.a.NEWSESSION     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            if (r8 != r1) goto L7c
            r5.c(r6, r7, r2)     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
        L7c:
            r2.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L82 android.database.sqlite.SQLiteDatabaseCorruptException -> L84 java.lang.Throwable -> L8a
            if (r2 == 0) goto L8f
            goto L8c
        L82:
            r6 = move-exception
            goto La5
        L84:
            r1 = r2
            goto L99
        L86:
            r6 = move-exception
            r2 = r1
            goto La5
        L89:
            r2 = r1
        L8a:
            if (r2 == 0) goto L8f
        L8c:
            r2.endTransaction()     // Catch: java.lang.Throwable -> L8f
        L8f:
            android.content.Context r6 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r6 = com.umeng.analytics.pro.e.a(r6)
            r6.b()
            goto La4
        L99:
            android.content.Context r6 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L86
            com.umeng.analytics.pro.f.a(r6)     // Catch: java.lang.Throwable -> L86
            if (r1 == 0) goto L8f
            r1.endTransaction()     // Catch: java.lang.Throwable -> L8f
            goto L8f
        La4:
            return r0
        La5:
            if (r2 == 0) goto Laa
            r2.endTransaction()     // Catch: java.lang.Throwable -> Laa
        Laa:
            android.content.Context r7 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r7 = com.umeng.analytics.pro.e.a(r7)
            r7.b()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(java.lang.String, org.json.JSONObject, com.umeng.analytics.pro.g$a):boolean");
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        try {
            long longValue = ((Long) jSONObject.opt(c.d.a.g)).longValue();
            JSONObject optJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("__pp");
            String str2 = "";
            String str3 = "";
            if (optJSONObject != null && optJSONObject.length() > 0) {
                str2 = c(optJSONObject.toString());
            }
            if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                str3 = c(optJSONObject2.toString());
            }
            sQLiteDatabase.execSQL("update __sd set __f=\"" + longValue + "\", __sp=\"" + str2 + "\", __pp=\"" + str3 + "\" where __ii=\"" + str + "\"");
        } catch (Throwable unused) {
        }
    }

    private void b(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        try {
            long longValue = ((Long) jSONObject.get("__e")).longValue();
            JSONObject optJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("__pp");
            String str2 = "";
            String str3 = "";
            if (optJSONObject != null && optJSONObject.length() > 0) {
                str2 = c(optJSONObject.toString());
            }
            if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                str3 = c(optJSONObject2.toString());
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("__ii", str);
            contentValues.put("__e", String.valueOf(longValue));
            contentValues.put("__sp", str2);
            contentValues.put("__pp", str3);
            contentValues.put("__av", UMGlobalContext.getInstance().getAppVersion());
            contentValues.put("__vc", UMUtils.getAppVersionCode(d));
            sQLiteDatabase.insert(c.C0020c.a, null, contentValues);
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0047, code lost:
        if (r3 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0068, code lost:
        if (r3 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006a, code lost:
        r3.endTransaction();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long a(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "select __f from __sd where __ii=\""
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = "\""
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r0 = 0
            r1 = 0
            android.content.Context r3 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L62
            com.umeng.analytics.pro.e r3 = com.umeng.analytics.pro.e.a(r3)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L62
            android.database.sqlite.SQLiteDatabase r3 = r3.a()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L62
            r3.beginTransaction()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L63
            android.database.Cursor r8 = r3.rawQuery(r8, r0)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L63
            if (r8 == 0) goto L42
            r8.moveToFirst()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L40
            java.lang.String r0 = "__f"
            int r0 = r8.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L40
            long r4 = r8.getLong(r0)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L40
            r1 = r4
            goto L42
        L3b:
            r0 = move-exception
            r6 = r0
            r0 = r8
            r8 = r6
            goto L4e
        L40:
            r0 = r8
            goto L63
        L42:
            if (r8 == 0) goto L47
            r8.close()     // Catch: java.lang.Exception -> L6d
        L47:
            if (r3 == 0) goto L6d
            goto L6a
        L4a:
            r8 = move-exception
            goto L4e
        L4c:
            r8 = move-exception
            r3 = r0
        L4e:
            if (r0 == 0) goto L53
            r0.close()     // Catch: java.lang.Exception -> L58
        L53:
            if (r3 == 0) goto L58
            r3.endTransaction()     // Catch: java.lang.Exception -> L58
        L58:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r8
        L62:
            r3 = r0
        L63:
            if (r0 == 0) goto L68
            r0.close()     // Catch: java.lang.Exception -> L6d
        L68:
            if (r3 == 0) goto L6d
        L6a:
            r3.endTransaction()     // Catch: java.lang.Exception -> L6d
        L6d:
            android.content.Context r8 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r8 = com.umeng.analytics.pro.e.a(r8)
            r8.b()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(java.lang.String):long");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private void c(java.lang.String r6, org.json.JSONObject r7, android.database.sqlite.SQLiteDatabase r8) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.c(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase):void");
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase, String str2) throws JSONException {
        Cursor cursor;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        String str3 = null;
        try {
            if ("__a".equals(str2)) {
                jSONArray = jSONObject.optJSONArray("__a");
                if (jSONArray == null || jSONArray.length() <= 0) {
                    return;
                }
            } else if (c.d.a.c.equals(str2)) {
                jSONArray = jSONObject.optJSONArray(c.d.a.c);
                if (jSONArray == null || jSONArray.length() <= 0) {
                    return;
                }
            } else {
                jSONArray = null;
            }
            cursor = sQLiteDatabase.rawQuery("select " + str2 + " from " + c.d.a + " where __ii=\"" + str + "\"", null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    try {
                        str3 = d(cursor.getString(cursor.getColumnIndex(str2)));
                    } catch (Throwable unused) {
                        if (cursor == null) {
                            return;
                        }
                        cursor.close();
                    }
                }
            }
            jSONArray2 = new JSONArray();
            if (!TextUtils.isEmpty(str3)) {
                jSONArray2 = new JSONArray(str3);
            }
        } catch (Throwable unused2) {
            cursor = null;
        }
        if (jSONArray2.length() > 1000) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2 != null) {
                    jSONArray2.put(jSONObject2);
                }
            } catch (JSONException unused3) {
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        String c2 = c(jSONArray2.toString());
        if (!TextUtils.isEmpty(c2)) {
            sQLiteDatabase.execSQL("update __sd set " + str2 + "=\"" + c2 + "\" where __ii=\"" + str + "\"");
        }
        if (cursor == null) {
            return;
        }
        cursor.close();
    }

    public boolean e() {
        return this.i.isEmpty();
    }

    public JSONObject a(boolean z) {
        a();
        this.j.clear();
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            a(jSONObject, z);
            b(jSONObject, (String) null);
            a(jSONObject, (String) null);
        } else {
            String a2 = a(jSONObject, z);
            if (!TextUtils.isEmpty(a2)) {
                b(jSONObject, a2);
                a(jSONObject, a2);
            }
        }
        return jSONObject;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x009a, code lost:
        if (r0 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009c, code lost:
        r0.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b5, code lost:
        if (r0 != null) goto L41;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v16, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject f() {
        /*
            r7 = this;
            java.util.List<java.lang.String> r0 = r7.l
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 == 0) goto La
            return r1
        La:
            android.content.Context r0 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L8f java.lang.Throwable -> L93 android.database.sqlite.SQLiteDatabaseCorruptException -> La9
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)     // Catch: java.lang.Throwable -> L8f java.lang.Throwable -> L93 android.database.sqlite.SQLiteDatabaseCorruptException -> La9
            android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch: java.lang.Throwable -> L8f java.lang.Throwable -> L93 android.database.sqlite.SQLiteDatabaseCorruptException -> La9
            r0.beginTransaction()     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            r2.<init>()     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.String r3 = "select *  from __is where __ii=\""
            r2.append(r3)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.util.List<java.lang.String> r3 = r7.l     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            r2.append(r3)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.String r3 = "\""
            r2.append(r3)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            android.database.Cursor r2 = r0.rawQuery(r2, r1)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            if (r2 == 0) goto L73
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6d android.database.sqlite.SQLiteDatabaseCorruptException -> L70
            if (r3 == 0) goto L73
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6d android.database.sqlite.SQLiteDatabaseCorruptException -> L70
            r3.<init>()     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6d android.database.sqlite.SQLiteDatabaseCorruptException -> L70
            java.lang.String r1 = "__av"
            int r1 = r2.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r1 = r2.getString(r1)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r4 = "__vc"
            int r4 = r2.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r4 = r2.getString(r4)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r5 = "__av"
            r3.put(r5, r1)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r1 = "__vc"
            r3.put(r1, r4)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            r1 = r3
            goto L73
        L67:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto Lba
        L6d:
            r3 = r1
        L6e:
            r1 = r2
            goto L95
        L70:
            r3 = r1
        L71:
            r1 = r2
            goto Lab
        L73:
            r0.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6d android.database.sqlite.SQLiteDatabaseCorruptException -> L70
            if (r2 == 0) goto L7b
            r2.close()
        L7b:
            if (r0 == 0) goto L80
            r0.endTransaction()     // Catch: java.lang.Throwable -> L80
        L80:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            r3 = r1
            goto Lb8
        L8b:
            r3 = r1
            goto L95
        L8d:
            r3 = r1
            goto Lab
        L8f:
            r0 = move-exception
            r2 = r0
            r0 = r1
            goto Lba
        L93:
            r0 = r1
            r3 = r0
        L95:
            if (r1 == 0) goto L9a
            r1.close()
        L9a:
            if (r0 == 0) goto L9f
        L9c:
            r0.endTransaction()     // Catch: java.lang.Throwable -> L9f
        L9f:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto Lb8
        La9:
            r0 = r1
            r3 = r0
        Lab:
            android.content.Context r2 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> Lb9
            com.umeng.analytics.pro.f.a(r2)     // Catch: java.lang.Throwable -> Lb9
            if (r1 == 0) goto Lb5
            r1.close()
        Lb5:
            if (r0 == 0) goto L9f
            goto L9c
        Lb8:
            return r3
        Lb9:
            r2 = move-exception
        Lba:
            if (r1 == 0) goto Lbf
            r1.close()
        Lbf:
            if (r0 == 0) goto Lc4
            r0.endTransaction()     // Catch: java.lang.Throwable -> Lc4
        Lc4:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.f():org.json.JSONObject");
    }

    public JSONObject b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        b(jSONObject, z);
        return jSONObject;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x016b, code lost:
        if (r1 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x017b, code lost:
        if (r1 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x017d, code lost:
        r1.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0195, code lost:
        if (r1 != null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(org.json.JSONObject r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0073, code lost:
        if (r1 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0082, code lost:
        if (r1 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0084, code lost:
        r1.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x009c, code lost:
        if (r1 != null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(org.json.JSONObject r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L91
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L91
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b android.database.sqlite.SQLiteDatabaseCorruptException -> L91
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteDatabaseCorruptException -> L92 java.lang.Throwable -> La0
            java.lang.String r2 = "select *  from __er"
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteDatabaseCorruptException -> L92 java.lang.Throwable -> La0
            if (r3 != 0) goto L2c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteDatabaseCorruptException -> L92 java.lang.Throwable -> La0
            r2.<init>()     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteDatabaseCorruptException -> L92 java.lang.Throwable -> La0
            java.lang.String r3 = "select *  from __er where __i=\""
            r2.append(r3)     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteDatabaseCorruptException -> L92 java.lang.Throwable -> La0
            r2.append(r6)     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteDatabaseCorruptException -> L92 java.lang.Throwable -> La0
            java.lang.String r6 = "\""
            r2.append(r6)     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteDatabaseCorruptException -> L92 java.lang.Throwable -> La0
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteDatabaseCorruptException -> L92 java.lang.Throwable -> La0
        L2c:
            android.database.Cursor r6 = r1.rawQuery(r2, r0)     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteDatabaseCorruptException -> L92 java.lang.Throwable -> La0
            if (r6 == 0) goto L6b
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            r0.<init>()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
        L37:
            boolean r2 = r6.moveToNext()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            if (r2 == 0) goto L5a
            java.lang.String r2 = "__a"
            int r2 = r6.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            java.lang.String r2 = r6.getString(r2)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            if (r3 != 0) goto L37
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            java.lang.String r2 = r4.d(r2)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            r0.put(r3)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            goto L37
        L5a:
            int r2 = r0.length()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            if (r2 <= 0) goto L6b
            java.lang.String r2 = "error"
            r5.put(r2, r0)     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            goto L6b
        L66:
            r5 = move-exception
            r0 = r6
            goto La1
        L69:
            r0 = r6
            goto L92
        L6b:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L66 android.database.sqlite.SQLiteDatabaseCorruptException -> L69 java.lang.Throwable -> L7d
            if (r6 == 0) goto L73
            r6.close()
        L73:
            if (r1 == 0) goto L87
            goto L84
        L76:
            r6 = r0
            goto L7d
        L78:
            r5 = move-exception
            r1 = r0
            goto La1
        L7b:
            r6 = r0
            r1 = r6
        L7d:
            if (r6 == 0) goto L82
            r6.close()
        L82:
            if (r1 == 0) goto L87
        L84:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L87
        L87:
            android.content.Context r5 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r5 = com.umeng.analytics.pro.e.a(r5)
            r5.b()
            goto L9f
        L91:
            r1 = r0
        L92:
            android.content.Context r5 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> La0
            com.umeng.analytics.pro.f.a(r5)     // Catch: java.lang.Throwable -> La0
            if (r0 == 0) goto L9c
            r0.close()
        L9c:
            if (r1 == 0) goto L87
            goto L84
        L9f:
            return
        La0:
            r5 = move-exception
        La1:
            if (r0 == 0) goto La6
            r0.close()
        La6:
            if (r1 == 0) goto Lab
            r1.endTransaction()     // Catch: java.lang.Throwable -> Lab
        Lab:
            android.content.Context r6 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r6 = com.umeng.analytics.pro.e.a(r6)
            r6.b()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x009a, code lost:
        if (r0 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009c, code lost:
        r0.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b5, code lost:
        if (r0 != null) goto L41;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v16, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject g() {
        /*
            r7 = this;
            java.util.List<java.lang.String> r0 = r7.i
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 == 0) goto La
            return r1
        La:
            android.content.Context r0 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L8f java.lang.Throwable -> L93 android.database.sqlite.SQLiteDatabaseCorruptException -> La9
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)     // Catch: java.lang.Throwable -> L8f java.lang.Throwable -> L93 android.database.sqlite.SQLiteDatabaseCorruptException -> La9
            android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch: java.lang.Throwable -> L8f java.lang.Throwable -> L93 android.database.sqlite.SQLiteDatabaseCorruptException -> La9
            r0.beginTransaction()     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            r2.<init>()     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.String r3 = "select *  from __sd where __ii=\""
            r2.append(r3)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.util.List<java.lang.String> r3 = r7.i     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            r2.append(r3)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.String r3 = "\""
            r2.append(r3)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            android.database.Cursor r2 = r0.rawQuery(r2, r1)     // Catch: java.lang.Throwable -> L8b android.database.sqlite.SQLiteDatabaseCorruptException -> L8d java.lang.Throwable -> Lb9
            if (r2 == 0) goto L73
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6d android.database.sqlite.SQLiteDatabaseCorruptException -> L70
            if (r3 == 0) goto L73
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6d android.database.sqlite.SQLiteDatabaseCorruptException -> L70
            r3.<init>()     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6d android.database.sqlite.SQLiteDatabaseCorruptException -> L70
            java.lang.String r1 = "__av"
            int r1 = r2.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r1 = r2.getString(r1)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r4 = "__vc"
            int r4 = r2.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r4 = r2.getString(r4)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r5 = "__av"
            r3.put(r5, r1)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            java.lang.String r1 = "__vc"
            r3.put(r1, r4)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6e android.database.sqlite.SQLiteDatabaseCorruptException -> L71
            r1 = r3
            goto L73
        L67:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto Lba
        L6d:
            r3 = r1
        L6e:
            r1 = r2
            goto L95
        L70:
            r3 = r1
        L71:
            r1 = r2
            goto Lab
        L73:
            r0.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L6d android.database.sqlite.SQLiteDatabaseCorruptException -> L70
            if (r2 == 0) goto L7b
            r2.close()
        L7b:
            if (r0 == 0) goto L80
            r0.endTransaction()     // Catch: java.lang.Throwable -> L80
        L80:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            r3 = r1
            goto Lb8
        L8b:
            r3 = r1
            goto L95
        L8d:
            r3 = r1
            goto Lab
        L8f:
            r0 = move-exception
            r2 = r0
            r0 = r1
            goto Lba
        L93:
            r0 = r1
            r3 = r0
        L95:
            if (r1 == 0) goto L9a
            r1.close()
        L9a:
            if (r0 == 0) goto L9f
        L9c:
            r0.endTransaction()     // Catch: java.lang.Throwable -> L9f
        L9f:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto Lb8
        La9:
            r0 = r1
            r3 = r0
        Lab:
            android.content.Context r2 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> Lb9
            com.umeng.analytics.pro.f.a(r2)     // Catch: java.lang.Throwable -> Lb9
            if (r1 == 0) goto Lb5
            r1.close()
        Lb5:
            if (r0 == 0) goto L9f
            goto L9c
        Lb8:
            return r3
        Lb9:
            r2 = move-exception
        Lba:
            if (r1 == 0) goto Lbf
            r1.close()
        Lbf:
            if (r0 == 0) goto Lc4
            r0.endTransaction()     // Catch: java.lang.Throwable -> Lc4
        Lc4:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.g():org.json.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x018b, code lost:
        if (r3 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x019d, code lost:
        if (r3 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x019f, code lost:
        r3.endTransaction();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(org.json.JSONObject r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(org.json.JSONObject, boolean):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b5, code lost:
        if (r1 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c7, code lost:
        if (r1 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c9, code lost:
        r1.endTransaction();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(org.json.JSONObject r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(org.json.JSONObject, boolean):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x004a, code lost:
        if (r1 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0055, code lost:
        if (r1 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0057, code lost:
        r1.endTransaction();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r4, boolean r5) {
        /*
            r3 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L64
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L64
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L54 android.database.sqlite.SQLiteDatabaseCorruptException -> L64
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            if (r5 == 0) goto L18
            if (r4 == 0) goto L47
            java.lang.String r4 = "delete from __is"
            r1.execSQL(r4)     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            goto L47
        L18:
            java.util.List<java.lang.String> r4 = r3.l     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            int r4 = r4.size()     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            if (r4 <= 0) goto L47
            r5 = 0
        L21:
            if (r5 >= r4) goto L47
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            r0.<init>()     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            java.lang.String r2 = "delete from __is where __ii=\""
            r0.append(r2)     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            java.util.List<java.lang.String> r2 = r3.l     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            java.lang.Object r2 = r2.get(r5)     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            r0.append(r2)     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            java.lang.String r2 = "\""
            r0.append(r2)     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            r1.execSQL(r0)     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            int r5 = r5 + 1
            goto L21
        L47:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L4d android.database.sqlite.SQLiteDatabaseCorruptException -> L4f java.lang.Throwable -> L55
            if (r1 == 0) goto L5a
            goto L57
        L4d:
            r4 = move-exception
            goto L70
        L4f:
            r0 = r1
            goto L64
        L51:
            r4 = move-exception
            r1 = r0
            goto L70
        L54:
            r1 = r0
        L55:
            if (r1 == 0) goto L5a
        L57:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L5a
        L5a:
            android.content.Context r4 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r4 = com.umeng.analytics.pro.e.a(r4)
            r4.b()
            goto L6f
        L64:
            android.content.Context r4 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L51
            com.umeng.analytics.pro.f.a(r4)     // Catch: java.lang.Throwable -> L51
            if (r0 == 0) goto L5a
            r0.endTransaction()     // Catch: java.lang.Throwable -> L5a
            goto L5a
        L6f:
            return
        L70:
            if (r1 == 0) goto L75
            r1.endTransaction()     // Catch: java.lang.Throwable -> L75
        L75:
            android.content.Context r5 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r5 = com.umeng.analytics.pro.e.a(r5)
            r5.b()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(boolean, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0050, code lost:
        if (r1 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
        if (r1 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005d, code lost:
        r1.endTransaction();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(boolean r3, boolean r4) {
        /*
            r2 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L57 java.lang.Throwable -> L5a android.database.sqlite.SQLiteDatabaseCorruptException -> L6a
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Throwable -> L5a android.database.sqlite.SQLiteDatabaseCorruptException -> L6a
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L57 java.lang.Throwable -> L5a android.database.sqlite.SQLiteDatabaseCorruptException -> L6a
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            if (r4 == 0) goto L18
            if (r3 == 0) goto L4d
            java.lang.String r3 = "delete from __sd"
            r1.execSQL(r3)     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            goto L4d
        L18:
            java.util.List<java.lang.String> r3 = r2.i     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            int r3 = r3.size()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            if (r3 <= 0) goto L4d
            r3 = 0
        L21:
            java.util.List<java.lang.String> r4 = r2.i     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            int r4 = r4.size()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            if (r3 >= r4) goto L4d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            r4.<init>()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            java.lang.String r0 = "delete from __sd where __ii=\""
            r4.append(r0)     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            java.util.List<java.lang.String> r0 = r2.i     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            java.lang.Object r0 = r0.get(r3)     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            r4.append(r0)     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            java.lang.String r0 = "\""
            r4.append(r0)     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            r1.execSQL(r4)     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            int r3 = r3 + 1
            goto L21
        L4d:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteDatabaseCorruptException -> L55 java.lang.Throwable -> L5b
            if (r1 == 0) goto L60
            goto L5d
        L53:
            r3 = move-exception
            goto L76
        L55:
            r0 = r1
            goto L6a
        L57:
            r3 = move-exception
            r1 = r0
            goto L76
        L5a:
            r1 = r0
        L5b:
            if (r1 == 0) goto L60
        L5d:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L60
        L60:
            android.content.Context r3 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r3 = com.umeng.analytics.pro.e.a(r3)
            r3.b()
            goto L75
        L6a:
            android.content.Context r3 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L57
            com.umeng.analytics.pro.f.a(r3)     // Catch: java.lang.Throwable -> L57
            if (r0 == 0) goto L60
            r0.endTransaction()     // Catch: java.lang.Throwable -> L60
            goto L60
        L75:
            return
        L76:
            if (r1 == 0) goto L7b
            r1.endTransaction()     // Catch: java.lang.Throwable -> L7b
        L7b:
            android.content.Context r4 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r4 = com.umeng.analytics.pro.e.a(r4)
            r4.b()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(boolean, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0044, code lost:
        if (r1 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0051, code lost:
        if (r1 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0053, code lost:
        r1.endTransaction();
        r0 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.util.List, java.util.List<java.lang.Integer>] */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h() {
        /*
            r5 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L50 android.database.sqlite.SQLiteDatabaseCorruptException -> L60
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L50 android.database.sqlite.SQLiteDatabaseCorruptException -> L60
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L50 android.database.sqlite.SQLiteDatabaseCorruptException -> L60
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            java.util.List<java.lang.Integer> r0 = r5.j     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            if (r0 <= 0) goto L3c
            r0 = 0
        L17:
            java.util.List<java.lang.Integer> r2 = r5.j     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            int r2 = r2.size()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            if (r0 >= r2) goto L3c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            r2.<init>()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            java.lang.String r3 = "delete from __et where rowid="
            r2.append(r3)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            java.util.List<java.lang.Integer> r3 = r5.j     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            java.lang.Object r3 = r3.get(r0)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            r2.append(r3)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            r1.execSQL(r2)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            int r0 = r0 + 1
            goto L17
        L3c:
            java.util.List<java.lang.Integer> r0 = r5.j     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            r0.clear()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteDatabaseCorruptException -> L49 java.lang.Throwable -> L51
            if (r1 == 0) goto L56
            goto L53
        L47:
            r0 = move-exception
            goto L6c
        L49:
            r0 = r1
            goto L60
        L4b:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L6c
        L50:
            r1 = r0
        L51:
            if (r1 == 0) goto L56
        L53:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L56
        L56:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L6b
        L60:
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L4b
            com.umeng.analytics.pro.f.a(r1)     // Catch: java.lang.Throwable -> L4b
            if (r0 == 0) goto L56
            r0.endTransaction()     // Catch: java.lang.Throwable -> L56
            goto L56
        L6b:
            return
        L6c:
            if (r1 == 0) goto L71
            r1.endTransaction()     // Catch: java.lang.Throwable -> L71
        L71:
            android.content.Context r1 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)
            r1.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.h():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
        if (r1 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
        r1.endTransaction();
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0016, code lost:
        if (r1 != null) goto L9;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void i() {
        /*
            r3 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L20 java.lang.Throwable -> L22 android.database.sqlite.SQLiteDatabaseCorruptException -> L32
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch: java.lang.Throwable -> L20 java.lang.Throwable -> L22 android.database.sqlite.SQLiteDatabaseCorruptException -> L32
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L20 java.lang.Throwable -> L22 android.database.sqlite.SQLiteDatabaseCorruptException -> L32
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L19 android.database.sqlite.SQLiteDatabaseCorruptException -> L1e java.lang.Throwable -> L23
            java.lang.String r0 = "delete from __er"
            r1.execSQL(r0)     // Catch: java.lang.Throwable -> L19 android.database.sqlite.SQLiteDatabaseCorruptException -> L1e java.lang.Throwable -> L23
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L19 android.database.sqlite.SQLiteDatabaseCorruptException -> L1e java.lang.Throwable -> L23
            if (r1 == 0) goto L28
            goto L25
        L19:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L3e
        L1e:
            r0 = r1
            goto L32
        L20:
            r1 = move-exception
            goto L3e
        L22:
            r1 = r0
        L23:
            if (r1 == 0) goto L28
        L25:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L28
        L28:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L3d
        L32:
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L20
            com.umeng.analytics.pro.f.a(r1)     // Catch: java.lang.Throwable -> L20
            if (r0 == 0) goto L28
            r0.endTransaction()     // Catch: java.lang.Throwable -> L28
            goto L28
        L3d:
            return
        L3e:
            if (r0 == 0) goto L43
            r0.endTransaction()     // Catch: java.lang.Throwable -> L43
        L43:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.i():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0058, code lost:
        if (r0 == null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x005a, code lost:
        r0.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006d, code lost:
        if (r0 == null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x004f, code lost:
        if (r0 != null) goto L13;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j() {
        /*
            r5 = this;
            java.lang.String r0 = r5.k
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 != 0) goto L80
            android.content.Context r0 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L57 android.database.sqlite.SQLiteDatabaseCorruptException -> L67
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L57 android.database.sqlite.SQLiteDatabaseCorruptException -> L67
            android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L57 android.database.sqlite.SQLiteDatabaseCorruptException -> L67
            r0.beginTransaction()     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            r2.<init>()     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.String r3 = "delete from __er where __i=\""
            r2.append(r3)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.String r3 = r5.k     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            r2.append(r3)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.String r3 = "\""
            r2.append(r3)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            r0.execSQL(r2)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            r2.<init>()     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.String r3 = "delete from __et where __i=\""
            r2.append(r3)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.String r3 = r5.k     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            r2.append(r3)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.String r3 = "\""
            r2.append(r3)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            r0.execSQL(r2)     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            r0.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L58 android.database.sqlite.SQLiteDatabaseCorruptException -> L68 java.lang.Throwable -> L70
            if (r0 == 0) goto L5d
            goto L5a
        L52:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L71
        L57:
            r0 = r1
        L58:
            if (r0 == 0) goto L5d
        L5a:
            r0.endTransaction()     // Catch: java.lang.Throwable -> L5d
        L5d:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            goto L80
        L67:
            r0 = r1
        L68:
            android.content.Context r2 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L70
            com.umeng.analytics.pro.f.a(r2)     // Catch: java.lang.Throwable -> L70
            if (r0 == 0) goto L5d
            goto L5a
        L70:
            r1 = move-exception
        L71:
            if (r0 == 0) goto L76
            r0.endTransaction()     // Catch: java.lang.Throwable -> L76
        L76:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r1
        L80:
            r5.k = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.j():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0073, code lost:
        if (r0 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0075, code lost:
        r0.endTransaction();
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0067, code lost:
        if (r0 != null) goto L12;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r3, java.lang.String r4) {
        /*
            r2 = this;
            r3 = 0
            android.content.Context r0 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L6e java.lang.Throwable -> L72 android.database.sqlite.SQLiteDatabaseCorruptException -> L82
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)     // Catch: java.lang.Throwable -> L6e java.lang.Throwable -> L72 android.database.sqlite.SQLiteDatabaseCorruptException -> L82
            android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch: java.lang.Throwable -> L6e java.lang.Throwable -> L72 android.database.sqlite.SQLiteDatabaseCorruptException -> L82
            r0.beginTransaction()     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            if (r3 != 0) goto L64
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r3.<init>()     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.String r1 = "delete from __er where __i=\""
            r3.append(r1)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r3.append(r4)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.String r1 = "\""
            r3.append(r1)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r0.execSQL(r3)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r3.<init>()     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.String r1 = "delete from __et where __i=\""
            r3.append(r1)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r3.append(r4)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.String r1 = "\""
            r3.append(r1)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r0.execSQL(r3)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.util.List<java.lang.Integer> r3 = r2.j     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r3.clear()     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r3.<init>()     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.String r1 = "delete from __sd where __ii=\""
            r3.append(r1)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r3.append(r4)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.String r4 = "\""
            r3.append(r4)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            r0.execSQL(r3)     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
        L64:
            r0.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L6a android.database.sqlite.SQLiteDatabaseCorruptException -> L6c java.lang.Throwable -> L73
            if (r0 == 0) goto L78
            goto L75
        L6a:
            r3 = move-exception
            goto L8e
        L6c:
            r3 = r0
            goto L82
        L6e:
            r4 = move-exception
            r0 = r3
            r3 = r4
            goto L8e
        L72:
            r0 = r3
        L73:
            if (r0 == 0) goto L78
        L75:
            r0.endTransaction()     // Catch: java.lang.Throwable -> L78
        L78:
            android.content.Context r3 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r3 = com.umeng.analytics.pro.e.a(r3)
            r3.b()
            goto L8d
        L82:
            android.content.Context r4 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L6e
            com.umeng.analytics.pro.f.a(r4)     // Catch: java.lang.Throwable -> L6e
            if (r3 == 0) goto L78
            r3.endTransaction()     // Catch: java.lang.Throwable -> L78
            goto L78
        L8d:
            return
        L8e:
            if (r0 == 0) goto L93
            r0.endTransaction()     // Catch: java.lang.Throwable -> L93
        L93:
            android.content.Context r4 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r4 = com.umeng.analytics.pro.e.a(r4)
            r4.b()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(boolean, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        if (r1 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
        r1.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0030, code lost:
        if (r1 != null) goto L12;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L37 java.lang.Throwable -> L3a android.database.sqlite.SQLiteDatabaseCorruptException -> L4a
            com.umeng.analytics.pro.e r1 = com.umeng.analytics.pro.e.a(r1)     // Catch: java.lang.Throwable -> L37 java.lang.Throwable -> L3a android.database.sqlite.SQLiteDatabaseCorruptException -> L4a
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L37 java.lang.Throwable -> L3a android.database.sqlite.SQLiteDatabaseCorruptException -> L4a
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
            if (r0 != 0) goto L2d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
            r0.<init>()     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
            java.lang.String r2 = "delete from __is where __ii=\""
            r0.append(r2)     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
            r0.append(r4)     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
            java.lang.String r4 = "\""
            r0.append(r4)     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
            java.lang.String r4 = r0.toString()     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
            r1.execSQL(r4)     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
        L2d:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L33 android.database.sqlite.SQLiteDatabaseCorruptException -> L35 java.lang.Throwable -> L3b
            if (r1 == 0) goto L40
            goto L3d
        L33:
            r4 = move-exception
            goto L56
        L35:
            r0 = r1
            goto L4a
        L37:
            r4 = move-exception
            r1 = r0
            goto L56
        L3a:
            r1 = r0
        L3b:
            if (r1 == 0) goto L40
        L3d:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L40
        L40:
            android.content.Context r4 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r4 = com.umeng.analytics.pro.e.a(r4)
            r4.b()
            goto L55
        L4a:
            android.content.Context r4 = com.umeng.analytics.pro.g.d     // Catch: java.lang.Throwable -> L37
            com.umeng.analytics.pro.f.a(r4)     // Catch: java.lang.Throwable -> L37
            if (r0 == 0) goto L40
            r0.endTransaction()     // Catch: java.lang.Throwable -> L40
            goto L40
        L55:
            return
        L56:
            if (r1 == 0) goto L5b
            r1.endTransaction()     // Catch: java.lang.Throwable -> L5b
        L5b:
            android.content.Context r0 = com.umeng.analytics.pro.g.d
            com.umeng.analytics.pro.e r0 = com.umeng.analytics.pro.e.a(r0)
            r0.b()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(java.lang.String):void");
    }

    private void l() {
        try {
            if (TextUtils.isEmpty(e)) {
                String multiProcessSP = UMUtils.getMultiProcessSP(d, g);
                if (TextUtils.isEmpty(multiProcessSP)) {
                    multiProcessSP = DeviceConfig.getDBencryptID(d);
                    if (!TextUtils.isEmpty(multiProcessSP)) {
                        UMUtils.setMultiProcessSP(d, g, multiProcessSP);
                    }
                }
                if (!TextUtils.isEmpty(multiProcessSP)) {
                    String substring = multiProcessSP.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < substring.length(); i++) {
                        char charAt = substring.charAt(i);
                        if (Character.isDigit(charAt)) {
                            if (Integer.parseInt(Character.toString(charAt)) == 0) {
                                sb.append(0);
                            } else {
                                sb.append(10 - Integer.parseInt(Character.toString(charAt)));
                            }
                        } else {
                            sb.append(charAt);
                        }
                    }
                    e = sb.toString();
                }
                if (TextUtils.isEmpty(e)) {
                    return;
                }
                e += new StringBuilder(e).reverse().toString();
                String multiProcessSP2 = UMUtils.getMultiProcessSP(d, h);
                if (TextUtils.isEmpty(multiProcessSP2)) {
                    UMUtils.setMultiProcessSP(d, h, c(f));
                } else if (!f.equals(d(multiProcessSP2))) {
                    b(true, false);
                    a(true, false);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public String c(String str) {
        try {
            return TextUtils.isEmpty(e) ? str : Base64.encodeToString(DataHelper.encrypt(str.getBytes(), e.getBytes()), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String d(String str) {
        try {
            return TextUtils.isEmpty(e) ? str : new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), e.getBytes()));
        } catch (Exception unused) {
            return null;
        }
    }
}