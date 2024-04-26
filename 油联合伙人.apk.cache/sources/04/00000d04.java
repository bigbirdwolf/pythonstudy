package com.facebook.stetho.inspector.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.database.SQLiteDatabaseCompat;
import java.io.File;

/* loaded from: classes.dex */
public class DefaultDatabaseConnectionProvider implements DatabaseConnectionProvider {
    @Override // com.facebook.stetho.inspector.database.DatabaseConnectionProvider
    public SQLiteDatabase openDatabase(File file) throws SQLiteException {
        return performOpen(file, determineOpenOptions(file));
    }

    @SQLiteDatabaseCompat.SQLiteOpenOptions
    protected int determineOpenOptions(File file) {
        String parent = file.getParent();
        StringBuilder sb = new StringBuilder();
        sb.append(file.getName());
        sb.append("-wal");
        return new File(parent, sb.toString()).exists() ? 1 : 0;
    }

    protected SQLiteDatabase performOpen(File file, @SQLiteDatabaseCompat.SQLiteOpenOptions int i) {
        SQLiteDatabaseCompat sQLiteDatabaseCompat = SQLiteDatabaseCompat.getInstance();
        SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, sQLiteDatabaseCompat.provideOpenFlags(i) | 0);
        sQLiteDatabaseCompat.enableFeatures(i, openDatabase);
        return openDatabase;
    }
}