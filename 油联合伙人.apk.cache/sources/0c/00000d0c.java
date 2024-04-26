package com.facebook.stetho.inspector.database;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.protocol.module.Database;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* loaded from: classes.dex */
public class SqliteDatabaseDriver extends Database.DatabaseDriver {
    private static final String[] UNINTERESTING_FILENAME_SUFFIXES = {"-journal", "-shm", "-uid", "-wal"};
    private final DatabaseConnectionProvider mDatabaseConnectionProvider;
    private final DatabaseFilesProvider mDatabaseFilesProvider;
    private List<String> mDatabases;

    @Deprecated
    public SqliteDatabaseDriver(Context context) {
        this(context, new DefaultDatabaseFilesProvider(context), new DefaultDatabaseConnectionProvider());
    }

    public SqliteDatabaseDriver(Context context, DatabaseFilesProvider databaseFilesProvider, DatabaseConnectionProvider databaseConnectionProvider) {
        super(context);
        this.mDatabaseFilesProvider = databaseFilesProvider;
        this.mDatabaseConnectionProvider = databaseConnectionProvider;
    }

    @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver
    public List<String> getDatabaseNames() {
        if (this.mDatabases == null) {
            this.mDatabases = new ArrayList();
            List<File> databaseFiles = this.mDatabaseFilesProvider.getDatabaseFiles();
            Collections.sort(databaseFiles);
            for (File file : tidyDatabaseList(databaseFiles)) {
                this.mDatabases.add(file.getName());
            }
        }
        return this.mDatabases;
    }

    static List<File> tidyDatabaseList(List<File> list) {
        HashSet hashSet = new HashSet(list);
        ArrayList arrayList = new ArrayList();
        for (File file : list) {
            String path = file.getPath();
            String removeSuffix = removeSuffix(path, UNINTERESTING_FILENAME_SUFFIXES);
            if (removeSuffix.equals(path) || !hashSet.contains(new File(removeSuffix))) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    private static String removeSuffix(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str.endsWith(str2)) {
                return str.substring(0, str.length() - str2.length());
            }
        }
        return str;
    }

    @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver
    public List<String> getTableNames(String str) throws SQLiteException {
        SQLiteDatabase openDatabase = openDatabase(str);
        try {
            Cursor rawQuery = openDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type IN (?, ?)", new String[]{"table", "view"});
            ArrayList arrayList = new ArrayList();
            while (rawQuery.moveToNext()) {
                arrayList.add(rawQuery.getString(0));
            }
            rawQuery.close();
            return arrayList;
        } finally {
            openDatabase.close();
        }
    }

    @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver
    public Database.ExecuteSQLResponse executeSQL(String str, String str2, Database.DatabaseDriver.ExecuteResultHandler<Database.ExecuteSQLResponse> executeResultHandler) throws SQLiteException {
        Util.throwIfNull(str2);
        Util.throwIfNull(executeResultHandler);
        SQLiteDatabase openDatabase = openDatabase(str);
        try {
            String upperCase = getFirstWord(str2).toUpperCase();
            char c = 65535;
            switch (upperCase.hashCode()) {
                case -2130463047:
                    if (upperCase.equals("INSERT")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1926899396:
                    if (upperCase.equals("PRAGMA")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1852692228:
                    if (upperCase.equals("SELECT")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1785516855:
                    if (upperCase.equals("UPDATE")) {
                        c = 0;
                        break;
                    }
                    break;
                case -591179561:
                    if (upperCase.equals("EXPLAIN")) {
                        c = 5;
                        break;
                    }
                    break;
                case 2012838315:
                    if (upperCase.equals("DELETE")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                    return (Database.ExecuteSQLResponse) executeUpdateDelete(openDatabase, str2, executeResultHandler);
                case 2:
                    return (Database.ExecuteSQLResponse) executeInsert(openDatabase, str2, executeResultHandler);
                case 3:
                case 4:
                case 5:
                    return (Database.ExecuteSQLResponse) executeSelect(openDatabase, str2, executeResultHandler);
                default:
                    return (Database.ExecuteSQLResponse) executeRawQuery(openDatabase, str2, executeResultHandler);
            }
        } finally {
            openDatabase.close();
        }
    }

    private static String getFirstWord(String str) {
        String trim = str.trim();
        int indexOf = trim.indexOf(32);
        return indexOf >= 0 ? trim.substring(0, indexOf) : trim;
    }

    @TargetApi(11)
    private <T> T executeUpdateDelete(SQLiteDatabase sQLiteDatabase, String str, Database.DatabaseDriver.ExecuteResultHandler<T> executeResultHandler) {
        return executeResultHandler.handleUpdateDelete(sQLiteDatabase.compileStatement(str).executeUpdateDelete());
    }

    private <T> T executeInsert(SQLiteDatabase sQLiteDatabase, String str, Database.DatabaseDriver.ExecuteResultHandler<T> executeResultHandler) {
        return executeResultHandler.handleInsert(sQLiteDatabase.compileStatement(str).executeInsert());
    }

    private <T> T executeSelect(SQLiteDatabase sQLiteDatabase, String str, Database.DatabaseDriver.ExecuteResultHandler<T> executeResultHandler) {
        Cursor rawQuery = sQLiteDatabase.rawQuery(str, null);
        try {
            return executeResultHandler.handleSelect(rawQuery);
        } finally {
            rawQuery.close();
        }
    }

    private <T> T executeRawQuery(SQLiteDatabase sQLiteDatabase, String str, Database.DatabaseDriver.ExecuteResultHandler<T> executeResultHandler) {
        sQLiteDatabase.execSQL(str);
        return executeResultHandler.handleRawQuery();
    }

    private SQLiteDatabase openDatabase(String str) throws SQLiteException {
        Util.throwIfNull(str);
        return this.mDatabaseConnectionProvider.openDatabase(findDatabaseFile(str));
    }

    private File findDatabaseFile(String str) {
        for (File file : this.mDatabaseFilesProvider.getDatabaseFiles()) {
            if (file.getName().equals(str)) {
                return file;
            }
        }
        return this.mContext.getDatabasePath(str);
    }
}