package com.facebook.stetho.inspector.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.protocol.module.Database;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* loaded from: classes.dex */
public class ContentProviderDatabaseDriver extends Database.DatabaseDriver {
    private static final String sDatabaseName = "content-providers";
    private final ContentProviderSchema[] mContentProviderSchemas;
    private List<String> mDatabaseNames;
    private List<String> mTableNames;

    public ContentProviderDatabaseDriver(Context context, ContentProviderSchema... contentProviderSchemaArr) {
        super(context);
        this.mContentProviderSchemas = contentProviderSchemaArr;
    }

    @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver
    public List<String> getDatabaseNames() {
        if (this.mDatabaseNames == null && this.mContentProviderSchemas != null) {
            this.mDatabaseNames = new ArrayList();
            this.mDatabaseNames.add(sDatabaseName);
        }
        return this.mDatabaseNames;
    }

    @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver
    public List<String> getTableNames(String str) {
        if (this.mTableNames == null) {
            this.mTableNames = new ArrayList();
            for (ContentProviderSchema contentProviderSchema : this.mContentProviderSchemas) {
                this.mTableNames.add(contentProviderSchema.getTableName());
            }
        }
        return this.mTableNames;
    }

    @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver
    public Database.ExecuteSQLResponse executeSQL(String str, String str2, Database.DatabaseDriver.ExecuteResultHandler<Database.ExecuteSQLResponse> executeResultHandler) throws SQLiteException {
        ContentProviderSchema contentProviderSchema = this.mContentProviderSchemas[this.mTableNames.indexOf(fetchTableName(str2))];
        Cursor query = this.mContext.getContentResolver().query(contentProviderSchema.getUri(), contentProviderSchema.getProjection(), null, null, null);
        try {
            return executeResultHandler.handleSelect(query);
        } finally {
            query.close();
        }
    }

    private String fetchTableName(String str) {
        for (String str2 : this.mTableNames) {
            if (str.contains(str2)) {
                return str2;
            }
        }
        return "";
    }
}