package com.facebook.stetho.inspector.database;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import com.umeng.socialize.net.dplus.CommonNetImpl;

/* loaded from: classes.dex */
public abstract class SQLiteDatabaseCompat {
    public static final int ENABLE_FOREIGN_KEY_CONSTRAINTS = 2;
    public static final int ENABLE_WRITE_AHEAD_LOGGING = 1;
    private static final SQLiteDatabaseCompat sInstance;

    /* loaded from: classes.dex */
    public @interface SQLiteOpenOptions {
    }

    public abstract void enableFeatures(@SQLiteOpenOptions int i, SQLiteDatabase sQLiteDatabase);

    public abstract int provideOpenFlags(@SQLiteOpenOptions int i);

    static {
        if (Build.VERSION.SDK_INT >= 16) {
            sInstance = new JellyBeanAndBeyondImpl();
        } else if (Build.VERSION.SDK_INT >= 11) {
            sInstance = new HoneycombImpl();
        } else {
            sInstance = new NoopImpl();
        }
    }

    public static SQLiteDatabaseCompat getInstance() {
        return sInstance;
    }

    @TargetApi(16)
    /* loaded from: classes.dex */
    private static class JellyBeanAndBeyondImpl extends SQLiteDatabaseCompat {
        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public int provideOpenFlags(@SQLiteOpenOptions int i) {
            if ((i & 1) != 0) {
                return CommonNetImpl.FLAG_SHARE;
            }
            return 0;
        }

        private JellyBeanAndBeyondImpl() {
        }

        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public void enableFeatures(@SQLiteOpenOptions int i, SQLiteDatabase sQLiteDatabase) {
            if ((i & 2) != 0) {
                sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
            }
        }
    }

    @TargetApi(11)
    /* loaded from: classes.dex */
    private static class HoneycombImpl extends SQLiteDatabaseCompat {
        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public int provideOpenFlags(@SQLiteOpenOptions int i) {
            return 0;
        }

        private HoneycombImpl() {
        }

        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public void enableFeatures(@SQLiteOpenOptions int i, SQLiteDatabase sQLiteDatabase) {
            if ((i & 1) != 0) {
                sQLiteDatabase.enableWriteAheadLogging();
            }
            if ((i & 2) != 0) {
                sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
            }
        }
    }

    /* loaded from: classes.dex */
    private static class NoopImpl extends SQLiteDatabaseCompat {
        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public void enableFeatures(@SQLiteOpenOptions int i, SQLiteDatabase sQLiteDatabase) {
        }

        @Override // com.facebook.stetho.inspector.database.SQLiteDatabaseCompat
        public int provideOpenFlags(@SQLiteOpenOptions int i) {
            return 0;
        }

        private NoopImpl() {
        }
    }
}