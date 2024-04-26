package com.umeng.analytics.pro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: UMDBUtils.java */
/* loaded from: classes.dex */
public class f {
    public static boolean a(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        boolean z = false;
        if (str == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", null);
        } catch (Exception unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (rawQuery.moveToNext()) {
                if (rawQuery.getInt(0) > 0) {
                    z = true;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception unused2) {
            cursor = rawQuery;
            if (cursor != null) {
                cursor.close();
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
            cursor = rawQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return z;
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            File databasePath = context.getDatabasePath(c.b);
            if (databasePath != null && databasePath.exists()) {
                databasePath.delete();
            }
            d.a(context).a();
        } catch (Throwable unused) {
        }
    }

    public static String b(Context context) {
        File databasePath = context.getDatabasePath(c.b);
        return databasePath.getParent() + File.separator;
    }

    public static String c(Context context) {
        return b(context) + "subprocess/";
    }

    public static String a(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static List<String> a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    public static List<String> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : list) {
                if (Collections.frequency(arrayList, str) < 1) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor cursor = null;
        boolean z = false;
        try {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getColumnIndex(str2) != -1) {
                        z = true;
                    }
                } catch (Exception unused) {
                    cursor = rawQuery;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return z;
                } catch (Throwable th) {
                    th = th;
                    cursor = rawQuery;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (rawQuery != null && !rawQuery.isClosed()) {
                rawQuery.close();
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        return z;
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        sQLiteDatabase.execSQL("alter table " + str + " add " + str2 + " " + str3);
    }
}