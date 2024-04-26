package com.umeng.socialize.net.dplus.db;

import android.content.Context;
import com.umeng.socialize.utils.ContextUtil;

/* loaded from: classes.dex */
public class DBManager {
    private static DBManager a;
    private static StandardDBHelper b;

    public static synchronized DBManager get(Context context) {
        DBManager dBManager;
        synchronized (DBManager.class) {
            if (a == null) {
                a = new DBManager();
            }
            dBManager = a;
        }
        return dBManager;
    }

    private DBManager() {
        b = new StandardDBHelper(ContextUtil.getContext());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public synchronized void insertS_E(org.json.JSONObject r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L5
            monitor-exit(r4)
            return
        L5:
            r0 = 0
            com.umeng.socialize.net.dplus.db.StandardDBHelper r1 = com.umeng.socialize.net.dplus.db.DBManager.b     // Catch: java.lang.Throwable -> L32
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L32
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L33
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L33
            r2.<init>()     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = "_json"
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L33
            r2.put(r3, r5)     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = "s_e"
            r1.insert(r5, r0, r2)     // Catch: java.lang.Throwable -> L33
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L3c
            goto L35
        L28:
            r5 = move-exception
            goto L2c
        L2a:
            r5 = move-exception
            r1 = r0
        L2c:
            if (r1 == 0) goto L31
            r1.endTransaction()     // Catch: java.lang.Throwable -> L31
        L31:
            throw r5     // Catch: java.lang.Throwable -> L39
        L32:
            r1 = r0
        L33:
            if (r1 == 0) goto L3c
        L35:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L3c
            goto L3c
        L39:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L3c:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.db.DBManager.insertS_E(org.json.JSONObject):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public synchronized void insertAuth(org.json.JSONObject r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L5
            monitor-exit(r4)
            return
        L5:
            r0 = 0
            com.umeng.socialize.net.dplus.db.StandardDBHelper r1 = com.umeng.socialize.net.dplus.db.DBManager.b     // Catch: java.lang.Throwable -> L32
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L32
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L33
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L33
            r2.<init>()     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = "_json"
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L33
            r2.put(r3, r5)     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = "auth"
            r1.insert(r5, r0, r2)     // Catch: java.lang.Throwable -> L33
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L3c
            goto L35
        L28:
            r5 = move-exception
            goto L2c
        L2a:
            r5 = move-exception
            r1 = r0
        L2c:
            if (r1 == 0) goto L31
            r1.endTransaction()     // Catch: java.lang.Throwable -> L31
        L31:
            throw r5     // Catch: java.lang.Throwable -> L39
        L32:
            r1 = r0
        L33:
            if (r1 == 0) goto L3c
        L35:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L3c
            goto L3c
        L39:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L3c:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.db.DBManager.insertAuth(org.json.JSONObject):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public synchronized void insertUserInfo(org.json.JSONObject r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L5
            monitor-exit(r4)
            return
        L5:
            r0 = 0
            com.umeng.socialize.net.dplus.db.StandardDBHelper r1 = com.umeng.socialize.net.dplus.db.DBManager.b     // Catch: java.lang.Throwable -> L32
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L32
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L33
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L33
            r2.<init>()     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = "_json"
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L33
            r2.put(r3, r5)     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = "userinfo"
            r1.insert(r5, r0, r2)     // Catch: java.lang.Throwable -> L33
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L3c
            goto L35
        L28:
            r5 = move-exception
            goto L2c
        L2a:
            r5 = move-exception
            r1 = r0
        L2c:
            if (r1 == 0) goto L31
            r1.endTransaction()     // Catch: java.lang.Throwable -> L31
        L31:
            throw r5     // Catch: java.lang.Throwable -> L39
        L32:
            r1 = r0
        L33:
            if (r1 == 0) goto L3c
        L35:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L3c
            goto L3c
        L39:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L3c:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.db.DBManager.insertUserInfo(org.json.JSONObject):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public synchronized void insertDau(org.json.JSONObject r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L5
            monitor-exit(r4)
            return
        L5:
            r0 = 0
            com.umeng.socialize.net.dplus.db.StandardDBHelper r1 = com.umeng.socialize.net.dplus.db.DBManager.b     // Catch: java.lang.Throwable -> L32
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L32
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L33
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L33
            r2.<init>()     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = "_json"
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L33
            r2.put(r3, r5)     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = "dau"
            r1.insert(r5, r0, r2)     // Catch: java.lang.Throwable -> L33
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L3c
            goto L35
        L28:
            r5 = move-exception
            goto L2c
        L2a:
            r5 = move-exception
            r1 = r0
        L2c:
            if (r1 == 0) goto L31
            r1.endTransaction()     // Catch: java.lang.Throwable -> L31
        L31:
            throw r5     // Catch: java.lang.Throwable -> L39
        L32:
            r1 = r0
        L33:
            if (r1 == 0) goto L3c
        L35:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L3c
            goto L3c
        L39:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L3c:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.db.DBManager.insertDau(org.json.JSONObject):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public synchronized void insertStats(org.json.JSONObject r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L5
            monitor-exit(r4)
            return
        L5:
            r0 = 0
            com.umeng.socialize.net.dplus.db.StandardDBHelper r1 = com.umeng.socialize.net.dplus.db.DBManager.b     // Catch: java.lang.Throwable -> L32
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L32
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L33
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L33
            r2.<init>()     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = "_json"
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L33
            r2.put(r3, r5)     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = "stats"
            r1.insert(r5, r0, r2)     // Catch: java.lang.Throwable -> L33
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L3c
            goto L35
        L28:
            r5 = move-exception
            goto L2c
        L2a:
            r5 = move-exception
            r1 = r0
        L2c:
            if (r1 == 0) goto L31
            r1.endTransaction()     // Catch: java.lang.Throwable -> L31
        L31:
            throw r5     // Catch: java.lang.Throwable -> L39
        L32:
            r1 = r0
        L33:
            if (r1 == 0) goto L3c
        L35:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L3c
            goto L3c
        L39:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L3c:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.db.DBManager.insertStats(org.json.JSONObject):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public synchronized void deleteTable(java.lang.String r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            com.umeng.socialize.net.dplus.db.StandardDBHelper r1 = com.umeng.socialize.net.dplus.db.DBManager.b     // Catch: java.lang.Throwable -> L34
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L34
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L35
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L35
            r0.<init>()     // Catch: java.lang.Throwable -> L35
            java.lang.String r2 = "DELETE FROM "
            r0.append(r2)     // Catch: java.lang.Throwable -> L35
            r0.append(r4)     // Catch: java.lang.Throwable -> L35
            java.lang.String r4 = ";"
            r0.append(r4)     // Catch: java.lang.Throwable -> L35
            java.lang.String r4 = r0.toString()     // Catch: java.lang.Throwable -> L35
            r1.execSQL(r4)     // Catch: java.lang.Throwable -> L35
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L3e
            goto L37
        L2a:
            r4 = move-exception
            goto L2e
        L2c:
            r4 = move-exception
            r1 = r0
        L2e:
            if (r1 == 0) goto L33
            r1.endTransaction()     // Catch: java.lang.Throwable -> L33
        L33:
            throw r4     // Catch: java.lang.Throwable -> L3b
        L34:
            r1 = r0
        L35:
            if (r1 == 0) goto L3e
        L37:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L3e
            goto L3e
        L3b:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L3e:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.db.DBManager.deleteTable(java.lang.String):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public synchronized void delete(java.util.ArrayList<java.lang.Integer> r5, java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            com.umeng.socialize.net.dplus.db.StandardDBHelper r1 = com.umeng.socialize.net.dplus.db.DBManager.b     // Catch: java.lang.Throwable -> L4a
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> L4a
            r1.beginTransaction()     // Catch: java.lang.Throwable -> L4b
            r0 = 0
        Lc:
            int r2 = r5.size()     // Catch: java.lang.Throwable -> L4b
            if (r0 >= r2) goto L3a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4b
            r2.<init>()     // Catch: java.lang.Throwable -> L4b
            java.lang.String r3 = "delete from "
            r2.append(r3)     // Catch: java.lang.Throwable -> L4b
            r2.append(r6)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r3 = " where Id='"
            r2.append(r3)     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r3 = r5.get(r0)     // Catch: java.lang.Throwable -> L4b
            r2.append(r3)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r3 = "' "
            r2.append(r3)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L4b
            r1.execSQL(r2)     // Catch: java.lang.Throwable -> L4b
            int r0 = r0 + 1
            goto Lc
        L3a:
            r1.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L4b
            if (r1 == 0) goto L54
            goto L4d
        L40:
            r5 = move-exception
            goto L44
        L42:
            r5 = move-exception
            r1 = r0
        L44:
            if (r1 == 0) goto L49
            r1.endTransaction()     // Catch: java.lang.Throwable -> L49
        L49:
            throw r5     // Catch: java.lang.Throwable -> L51
        L4a:
            r1 = r0
        L4b:
            if (r1 == 0) goto L54
        L4d:
            r1.endTransaction()     // Catch: java.lang.Throwable -> L54
            goto L54
        L51:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L54:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.db.DBManager.delete(java.util.ArrayList, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006e, code lost:
        if (r2 != null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0070, code lost:
        r2.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008c, code lost:
        if (r2 != null) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x009d A[Catch: Throwable -> 0x00a0, TRY_LEAVE, TryCatch #3 {Throwable -> 0x00a0, blocks: (B:47:0x0098, B:49:0x009d), top: B:59:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized org.json.JSONArray select(java.lang.String r8, java.util.ArrayList<java.lang.Integer> r9, double r10, boolean r12) throws org.json.JSONException {
        /*
            r7 = this;
            monitor-enter(r7)
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch: java.lang.Throwable -> La1
            r0.<init>()     // Catch: java.lang.Throwable -> La1
            r1 = 0
            com.umeng.socialize.net.dplus.db.StandardDBHelper r2 = com.umeng.socialize.net.dplus.db.DBManager.b     // Catch: java.lang.Throwable -> L81 java.lang.Throwable -> L85 org.json.JSONException -> L91
            android.database.sqlite.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch: java.lang.Throwable -> L81 java.lang.Throwable -> L85 org.json.JSONException -> L91
            r2.beginTransaction()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b org.json.JSONException -> L7d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b org.json.JSONException -> L7d
            r3.<init>()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b org.json.JSONException -> L7d
            java.lang.String r4 = "select * from "
            r3.append(r4)     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b org.json.JSONException -> L7d
            r3.append(r8)     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b org.json.JSONException -> L7d
            java.lang.String r8 = r3.toString()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b org.json.JSONException -> L7d
            android.database.Cursor r8 = r2.rawQuery(r8, r1)     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L7b org.json.JSONException -> L7d
        L25:
            boolean r1 = r8.moveToNext()     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            if (r1 == 0) goto L66
            r1 = 0
            int r1 = r8.getInt(r1)     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            r3 = 1
            java.lang.String r3 = r8.getString(r3)     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            if (r12 == 0) goto L4c
            java.lang.String r4 = r0.toString()     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            byte[] r4 = r4.getBytes()     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            int r4 = r4.length     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            byte[] r5 = r3.getBytes()     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            int r5 = r5.length     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            int r4 = r4 + r5
            double r4 = (double) r4     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            int r6 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r6 <= 0) goto L4c
            goto L66
        L4c:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            r0.put(r4)     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            boolean r3 = r9.contains(r3)     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            if (r3 != 0) goto L25
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            r9.add(r1)     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            goto L25
        L66:
            r2.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L74 org.json.JSONException -> L76 java.lang.Throwable -> L87
            if (r8 == 0) goto L6e
            r8.close()     // Catch: java.lang.Throwable -> L8f
        L6e:
            if (r2 == 0) goto L8f
        L70:
            r2.endTransaction()     // Catch: java.lang.Throwable -> L8f
            goto L8f
        L74:
            r9 = move-exception
            goto L96
        L76:
            r9 = move-exception
            goto L7f
        L78:
            r9 = move-exception
            r8 = r1
            goto L96
        L7b:
            r8 = r1
            goto L87
        L7d:
            r9 = move-exception
            r8 = r1
        L7f:
            r1 = r2
            goto L93
        L81:
            r9 = move-exception
            r8 = r1
            r2 = r8
            goto L96
        L85:
            r8 = r1
            r2 = r8
        L87:
            if (r8 == 0) goto L8c
            r8.close()     // Catch: java.lang.Throwable -> L8f
        L8c:
            if (r2 == 0) goto L8f
            goto L70
        L8f:
            monitor-exit(r7)
            return r0
        L91:
            r9 = move-exception
            r8 = r1
        L93:
            throw r9     // Catch: java.lang.Throwable -> L94
        L94:
            r9 = move-exception
            r2 = r1
        L96:
            if (r8 == 0) goto L9b
            r8.close()     // Catch: java.lang.Throwable -> La0
        L9b:
            if (r2 == 0) goto La0
            r2.endTransaction()     // Catch: java.lang.Throwable -> La0
        La0:
            throw r9     // Catch: java.lang.Throwable -> La1
        La1:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.db.DBManager.select(java.lang.String, java.util.ArrayList, double, boolean):org.json.JSONArray");
    }

    public synchronized void closeDatabase() {
        b.close();
    }
}