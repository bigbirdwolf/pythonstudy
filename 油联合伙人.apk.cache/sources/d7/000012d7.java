package com.umeng.analytics.process;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.umeng.analytics.pro.w;
import com.umeng.analytics.process.DBFileTraversalUtil;
import com.umeng.analytics.process.a;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UMProcessDBHelper {
    private static UMProcessDBHelper mInstance;
    private Context mContext;
    private FileLockUtil mFileLock = new FileLockUtil();
    private InsertEventCallback ekvCallBack = new InsertEventCallback();

    private int getDataSource() {
        return 0;
    }

    /* loaded from: classes.dex */
    private class InsertEventCallback implements FileLockCallback {
        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) {
            return false;
        }

        private InsertEventCallback() {
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(com.umeng.analytics.process.a.c)) {
                str = str.replaceFirst(com.umeng.analytics.process.a.c, "");
            }
            UMProcessDBHelper.this.insertEvents(str.replace(com.umeng.analytics.process.a.d, ""), (JSONArray) obj);
            return true;
        }
    }

    private UMProcessDBHelper() {
    }

    private UMProcessDBHelper(Context context) {
        w.a().a(context);
    }

    public static UMProcessDBHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UMProcessDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new UMProcessDBHelper(context);
                }
            }
        }
        mInstance.mContext = context;
        return mInstance;
    }

    public void createDBByProcess(String str) {
        try {
            c.a(this.mContext).a(str);
            c.a(this.mContext).b(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertEventsInSubProcess(String str, JSONArray jSONArray) {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            File file = new File(b.b(this.mContext, str));
            if (file.exists()) {
                this.mFileLock.doFileOperateion(file, this.ekvCallBack, jSONArray);
            } else {
                insertEvents(str, jSONArray);
            }
        }
    }

    public void insertEvents(String str, JSONArray jSONArray) {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            insertEvents_(str, datasAdapter(str, jSONArray));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processToMain(String str) {
        if (dbIsExists(str)) {
            List<a> readEventByProcess = readEventByProcess(str);
            if (!readEventByProcess.isEmpty() && insertEvents_(com.umeng.analytics.process.a.h, readEventByProcess)) {
                deleteEventDatas(str, null, readEventByProcess);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x014a, code lost:
        if (r4 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x015d, code lost:
        if (r4 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x015f, code lost:
        r4.endTransaction();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject readMainEvents(long r17, java.util.List<java.lang.Integer> r19) {
        /*
            Method dump skipped, instructions count: 390
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readMainEvents(long, java.util.List):org.json.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004e, code lost:
        if (r1 == null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0050, code lost:
        r1.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0053, code lost:
        com.umeng.analytics.process.c.a(r6.mContext).b(com.umeng.analytics.process.a.h);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005e, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0035, code lost:
        if (r1 != null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void deleteMainProcessEventDatasByIds(java.util.List<java.lang.Integer> r7) {
        /*
            r6 = this;
            r0 = 0
            android.content.Context r1 = r6.mContext     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L4d
            com.umeng.analytics.process.c r1 = com.umeng.analytics.process.c.a(r1)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L4d
            java.lang.String r2 = "_main_"
            android.database.sqlite.SQLiteDatabase r1 = r1.a(r2)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L4d
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
        L14:
            boolean r0 = r7.hasNext()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
            if (r0 == 0) goto L32
            java.lang.Object r0 = r7.next()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
            java.lang.String r2 = "__et_p"
            java.lang.String r3 = "id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
            r5 = 0
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
            r4[r5] = r0     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
            r1.delete(r2, r3, r4)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
            goto L14
        L32:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L4e
            if (r1 == 0) goto L53
            goto L50
        L38:
            r7 = move-exception
            goto L3c
        L3a:
            r7 = move-exception
            r1 = r0
        L3c:
            if (r1 == 0) goto L41
            r1.endTransaction()
        L41:
            android.content.Context r0 = r6.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a(r0)
            java.lang.String r1 = "_main_"
            r0.b(r1)
            throw r7
        L4d:
            r1 = r0
        L4e:
            if (r1 == 0) goto L53
        L50:
            r1.endTransaction()
        L53:
            android.content.Context r7 = r6.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a(r7)
            java.lang.String r0 = "_main_"
            r7.b(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.deleteMainProcessEventDatasByIds(java.util.List):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
        if (r0 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005f, code lost:
        if (r0 == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0061, code lost:
        r0.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
        com.umeng.analytics.process.c.a(r4.mContext).b(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006d, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void deleteEventDatas(java.lang.String r5, java.lang.String r6, java.util.List<com.umeng.analytics.process.UMProcessDBHelper.a> r7) {
        /*
            r4 = this;
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 == 0) goto L7
            return
        L7:
            r6 = 0
            android.content.Context r0 = r4.mContext     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L5e
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a(r0)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L5e
            android.database.sqlite.SQLiteDatabase r0 = r0.a(r5)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L5e
            r0.beginTransaction()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            int r1 = r7.size()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            if (r7 == 0) goto L3f
            if (r1 <= 0) goto L3f
            r6 = 0
        L1e:
            if (r6 >= r1) goto L44
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            r2.<init>()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            java.lang.String r3 = "delete from __et_p where rowid="
            r2.append(r3)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            java.lang.Object r3 = r7.get(r6)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            com.umeng.analytics.process.UMProcessDBHelper$a r3 = (com.umeng.analytics.process.UMProcessDBHelper.a) r3     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            int r3 = r3.a     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            r2.append(r3)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            r0.execSQL(r2)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            int r6 = r6 + 1
            goto L1e
        L3f:
            java.lang.String r7 = "__et_p"
            r0.delete(r7, r6, r6)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
        L44:
            r0.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5f
            if (r0 == 0) goto L64
            goto L61
        L4a:
            r6 = move-exception
            goto L4f
        L4c:
            r7 = move-exception
            r0 = r6
            r6 = r7
        L4f:
            if (r0 == 0) goto L54
            r0.endTransaction()
        L54:
            android.content.Context r7 = r4.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a(r7)
            r7.b(r5)
            throw r6
        L5e:
            r0 = r6
        L5f:
            if (r0 == 0) goto L64
        L61:
            r0.endTransaction()
        L64:
            android.content.Context r6 = r4.mContext
            com.umeng.analytics.process.c r6 = com.umeng.analytics.process.c.a(r6)
            r6.b(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.deleteEventDatas(java.lang.String, java.lang.String, java.util.List):void");
    }

    private boolean insertEvents_(String str, List<a> list) {
        SQLiteDatabase sQLiteDatabase;
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            return true;
        }
        try {
            sQLiteDatabase = c.a(this.mContext).a(str);
        } catch (Exception unused) {
            sQLiteDatabase = null;
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            try {
                sQLiteDatabase.beginTransaction();
                for (a aVar : list) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("__i", aVar.b);
                        contentValues.put("__e", aVar.c);
                        contentValues.put("__t", Integer.valueOf(aVar.e));
                        contentValues.put(a.InterfaceC0021a.f, aVar.f);
                        contentValues.put("__av", aVar.g);
                        contentValues.put("__vc", aVar.h);
                        contentValues.put("__s", aVar.d);
                        sQLiteDatabase.insert(a.InterfaceC0021a.a, null, contentValues);
                    } catch (Exception unused2) {
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable unused3) {
                    }
                }
                c.a(this.mContext).b(str);
                return true;
            } catch (Exception unused4) {
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable unused5) {
                    }
                }
                c.a(this.mContext).b(str);
                return false;
            }
        } catch (Throwable th2) {
            th = th2;
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable unused6) {
                }
            }
            c.a(this.mContext).b(str);
            throw th;
        }
    }

    private List<a> datasAdapter(String str, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                a aVar = new a();
                aVar.c = jSONObject.optString("id");
                aVar.g = UMUtils.getAppVersionName(this.mContext);
                aVar.h = UMUtils.getAppVersionCode(this.mContext);
                aVar.b = jSONObject.optString("__i");
                aVar.e = jSONObject.optInt("__t");
                aVar.f = str;
                if (jSONObject.has(com.umeng.analytics.pro.b.ac)) {
                    jSONObject.remove(com.umeng.analytics.pro.b.ac);
                }
                jSONObject.put(com.umeng.analytics.pro.b.ac, getDataSource());
                jSONObject.remove("__i");
                jSONObject.remove("__t");
                aVar.d = w.a().a(jSONObject.toString());
                jSONObject.remove(com.umeng.analytics.pro.b.ac);
                arrayList.add(aVar);
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0097 A[Catch: Exception -> 0x009a, TRY_LEAVE, TryCatch #5 {Exception -> 0x009a, blocks: (B:36:0x0092, B:38:0x0097), top: B:57:0x0092 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00af A[Catch: Exception -> 0x00b2, TRY_LEAVE, TryCatch #9 {Exception -> 0x00b2, blocks: (B:43:0x00aa, B:45:0x00af), top: B:60:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject readVersionInfoFromColumId(java.lang.Integer r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "select *  from __et_p where rowid="
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r0 = 0
            android.content.Context r1 = r6.mContext     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L88
            com.umeng.analytics.process.c r1 = com.umeng.analytics.process.c.a(r1)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L88
            java.lang.String r2 = "_main_"
            android.database.sqlite.SQLiteDatabase r1 = r1.a(r2)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L88
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L80
            android.database.Cursor r7 = r1.rawQuery(r7, r0)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L80
            if (r7 == 0) goto L65
            boolean r2 = r7.moveToNext()     // Catch: java.lang.Exception -> L60 java.lang.Throwable -> La7
            if (r2 == 0) goto L65
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Exception -> L60 java.lang.Throwable -> La7
            r2.<init>()     // Catch: java.lang.Exception -> L60 java.lang.Throwable -> La7
            java.lang.String r0 = "__av"
            int r0 = r7.getColumnIndex(r0)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> La7
            java.lang.String r0 = r7.getString(r0)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> La7
            java.lang.String r3 = "__vc"
            int r3 = r7.getColumnIndex(r3)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> La7
            java.lang.String r3 = r7.getString(r3)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> La7
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> La7
            if (r4 != 0) goto L51
            java.lang.String r4 = "__av"
            r2.put(r4, r0)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> La7
        L51:
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> La7
            if (r0 != 0) goto L5c
            java.lang.String r0 = "__vc"
            r2.put(r0, r3)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> La7
        L5c:
            r0 = r2
            goto L65
        L5e:
            r0 = move-exception
            goto L8d
        L60:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L8d
        L65:
            if (r7 == 0) goto L6a
            r7.close()     // Catch: java.lang.Exception -> L6f
        L6a:
            if (r1 == 0) goto L6f
            r1.endTransaction()     // Catch: java.lang.Exception -> L6f
        L6f:
            android.content.Context r7 = r6.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a(r7)
            java.lang.String r1 = "_main_"
            r7.b(r1)
            goto La6
        L7b:
            r7 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto La8
        L80:
            r7 = move-exception
            r2 = r0
            goto L8b
        L83:
            r7 = move-exception
            r1 = r0
            r0 = r7
            r7 = r1
            goto La8
        L88:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L8b:
            r0 = r7
            r7 = r2
        L8d:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> La7
            if (r7 == 0) goto L95
            r7.close()     // Catch: java.lang.Exception -> L9a
        L95:
            if (r1 == 0) goto L9a
            r1.endTransaction()     // Catch: java.lang.Exception -> L9a
        L9a:
            android.content.Context r7 = r6.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a(r7)
            java.lang.String r0 = "_main_"
            r7.b(r0)
            r0 = r2
        La6:
            return r0
        La7:
            r0 = move-exception
        La8:
            if (r7 == 0) goto Lad
            r7.close()     // Catch: java.lang.Exception -> Lb2
        Lad:
            if (r1 == 0) goto Lb2
            r1.endTransaction()     // Catch: java.lang.Exception -> Lb2
        Lb2:
            android.content.Context r7 = r6.mContext
            com.umeng.analytics.process.c r7 = com.umeng.analytics.process.c.a(r7)
            java.lang.String r1 = "_main_"
            r7.b(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readVersionInfoFromColumId(java.lang.Integer):org.json.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x008c, code lost:
        if (r3 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a7, code lost:
        if (r3 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a9, code lost:
        r3.endTransaction();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.umeng.analytics.process.UMProcessDBHelper.a> readEventByProcess(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "select *  from __et_p"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.content.Context r3 = r7.mContext     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L9b
            com.umeng.analytics.process.c r3 = com.umeng.analytics.process.c.a(r3)     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L9b
            android.database.sqlite.SQLiteDatabase r3 = r3.a(r8)     // Catch: java.lang.Throwable -> L97 java.lang.Exception -> L9b
            r3.beginTransaction()     // Catch: java.lang.Throwable -> L8f java.lang.Exception -> L92
            android.database.Cursor r0 = r3.rawQuery(r0, r2)     // Catch: java.lang.Throwable -> L8f java.lang.Exception -> L92
            if (r0 == 0) goto L87
        L1b:
            boolean r4 = r0.moveToNext()     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            if (r4 == 0) goto L87
            com.umeng.analytics.process.UMProcessDBHelper$a r4 = new com.umeng.analytics.process.UMProcessDBHelper$a     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r4.<init>()     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r5 = 0
            int r5 = r0.getInt(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r4.a = r5     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = "__i"
            int r5 = r0.getColumnIndex(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = r0.getString(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r4.b = r5     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = "__e"
            int r5 = r0.getColumnIndex(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = r0.getString(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r4.c = r5     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = "__s"
            int r5 = r0.getColumnIndex(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = r0.getString(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r4.d = r5     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = "__t"
            int r5 = r0.getColumnIndex(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            int r5 = r0.getInt(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r4.e = r5     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = "__pn"
            int r5 = r0.getColumnIndex(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = r0.getString(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r4.f = r5     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = "__av"
            int r5 = r0.getColumnIndex(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = r0.getString(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r4.g = r5     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = "__vc"
            int r5 = r0.getColumnIndex(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            java.lang.String r5 = r0.getString(r5)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r4.h = r5     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            r1.add(r4)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> Lb6
            goto L1b
        L85:
            r2 = move-exception
            goto L9f
        L87:
            if (r0 == 0) goto L8c
            r0.close()     // Catch: java.lang.Exception -> Lac
        L8c:
            if (r3 == 0) goto Lac
            goto La9
        L8f:
            r1 = move-exception
            r0 = r2
            goto Lb7
        L92:
            r0 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L9f
        L97:
            r1 = move-exception
            r0 = r2
            r3 = r0
            goto Lb7
        L9b:
            r0 = move-exception
            r3 = r2
            r2 = r0
            r0 = r3
        L9f:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> Lb6
            if (r0 == 0) goto La7
            r0.close()     // Catch: java.lang.Exception -> Lac
        La7:
            if (r3 == 0) goto Lac
        La9:
            r3.endTransaction()     // Catch: java.lang.Exception -> Lac
        Lac:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a(r0)
            r0.b(r8)
            return r1
        Lb6:
            r1 = move-exception
        Lb7:
            if (r0 == 0) goto Lbc
            r0.close()     // Catch: java.lang.Exception -> Lc1
        Lbc:
            if (r3 == 0) goto Lc1
            r3.endTransaction()     // Catch: java.lang.Exception -> Lc1
        Lc1:
            android.content.Context r0 = r7.mContext
            com.umeng.analytics.process.c r0 = com.umeng.analytics.process.c.a(r0)
            r0.b(r8)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readEventByProcess(java.lang.String):java.util.List");
    }

    private boolean dbIsExists(String str) {
        try {
            return new File(b.b(this.mContext, str)).exists();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a implements Serializable {
        int a;
        String b;
        String c;
        String d;
        int e;
        String f;
        String g;
        String h;

        private a() {
        }
    }

    /* loaded from: classes.dex */
    private class ProcessToMainCallback implements FileLockCallback {
        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) {
            return false;
        }

        private ProcessToMainCallback() {
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(com.umeng.analytics.process.a.c)) {
                str = str.replaceFirst(com.umeng.analytics.process.a.c, "");
            }
            UMProcessDBHelper.this.processToMain(str.replace(com.umeng.analytics.process.a.d, ""));
            return true;
        }
    }

    public void processDBToMain() {
        try {
            DBFileTraversalUtil.traverseDBFiles(b.a(this.mContext), new ProcessToMainCallback(), new DBFileTraversalUtil.a() { // from class: com.umeng.analytics.process.UMProcessDBHelper.1
                @Override // com.umeng.analytics.process.DBFileTraversalUtil.a
                public void a() {
                    if (AnalyticsConstants.SUB_PROCESS_EVENT) {
                        UMWorkDispatch.sendEvent(UMProcessDBHelper.this.mContext, UMProcessDBDatasSender.UM_PROCESS_CONSTRUCTMESSAGE, UMProcessDBDatasSender.getInstance(UMProcessDBHelper.this.mContext), null);
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    private boolean processIsService(Context context) {
        return context.getPackageManager().getServiceInfo(new ComponentName(context, this.mContext.getClass()), 0) != null;
    }
}