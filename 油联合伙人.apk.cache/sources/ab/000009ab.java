package com.alipay.sdk.tid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alipay.sdk.util.c;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
final class a extends SQLiteOpenHelper {
    private static final String a = "msp.db";
    private static final int b = 1;
    private WeakReference<Context> c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context) {
        super(context, a, (SQLiteDatabase.CursorFactory) null, 1);
        this.c = new WeakReference<>(context);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        Exception e;
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            try {
                sQLiteDatabase = getWritableDatabase();
            } catch (Exception e2) {
                sQLiteDatabase = null;
                e = e2;
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                    sQLiteDatabase2.close();
                }
                throw th;
            }
            try {
                sQLiteDatabase.execSQL("drop table if exists tb_tid");
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
            } catch (Exception e3) {
                e = e3;
                c.a(e);
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabase.close();
            }
            sQLiteDatabase.close();
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0 && sQLiteDatabase2.isOpen()) {
                sQLiteDatabase2.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002b, code lost:
        if (r2.isOpen() != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0058, code lost:
        if (r2.isOpen() != false) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4b
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            java.lang.String r5 = r4.c(r5, r6)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            boolean r0 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L4d
            if (r0 == 0) goto L20
            java.lang.String r6 = r5.getString(r6)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L4d
            r1 = r6
        L20:
            if (r5 == 0) goto L25
            r5.close()
        L25:
            if (r2 == 0) goto L5b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L5b
        L2d:
            r2.close()
            goto L5b
        L31:
            r6 = move-exception
            r1 = r5
            goto L3a
        L34:
            r6 = move-exception
            goto L3a
        L36:
            r5 = r1
            goto L4d
        L38:
            r6 = move-exception
            r2 = r1
        L3a:
            if (r1 == 0) goto L3f
            r1.close()
        L3f:
            if (r2 == 0) goto L4a
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L4a
            r2.close()
        L4a:
            throw r6
        L4b:
            r5 = r1
            r2 = r5
        L4d:
            if (r5 == 0) goto L52
            r5.close()
        L52:
            if (r2 == 0) goto L5b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L5b
            goto L2d
        L5b:
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 != 0) goto L71
            java.lang.ref.WeakReference<android.content.Context> r5 = r4.c
            java.lang.Object r5 = r5.get()
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r5 = com.alipay.sdk.util.a.c(r5)
            java.lang.String r1 = com.alipay.sdk.encrypt.b.b(r1, r5)
        L71:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002b, code lost:
        if (r2.isOpen() != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0058, code lost:
        if (r2.isOpen() != false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String b(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select key_tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4b
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            java.lang.String r5 = r4.c(r5, r6)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            boolean r0 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L4d
            if (r0 == 0) goto L20
            java.lang.String r6 = r5.getString(r6)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L4d
            r1 = r6
        L20:
            if (r5 == 0) goto L25
            r5.close()
        L25:
            if (r2 == 0) goto L5b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L5b
        L2d:
            r2.close()
            goto L5b
        L31:
            r6 = move-exception
            r1 = r5
            goto L3a
        L34:
            r6 = move-exception
            goto L3a
        L36:
            r5 = r1
            goto L4d
        L38:
            r6 = move-exception
            r2 = r1
        L3a:
            if (r1 == 0) goto L3f
            r1.close()
        L3f:
            if (r2 == 0) goto L4a
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L4a
            r2.close()
        L4a:
            throw r6
        L4b:
            r5 = r1
            r2 = r5
        L4d:
            if (r5 == 0) goto L52
            r5.close()
        L52:
            if (r2 == 0) goto L5b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L5b
            goto L2d
        L5b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.b(java.lang.String, java.lang.String):java.lang.String");
    }

    private String c(String str, String str2) {
        return str + str2;
    }
}