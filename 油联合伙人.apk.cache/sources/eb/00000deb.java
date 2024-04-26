package com.facebook.stetho.inspector.protocol.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@TargetApi(11)
/* loaded from: classes.dex */
public class Database implements ChromeDevtoolsDomain {
    private static final int MAX_BLOB_LENGTH = 512;
    private static final int MAX_EXECUTE_RESULTS = 250;
    private static final String UNKNOWN_BLOB_LABEL = "{blob}";
    private final ObjectMapper mObjectMapper;
    private List<DatabaseDriver> mDatabaseDrivers = new ArrayList();
    private final ChromePeerManager mChromePeerManager = new ChromePeerManager();
    private final DatabasePeerRegistrationListener mPeerListener = new DatabasePeerRegistrationListener(this.mDatabaseDrivers);

    /* loaded from: classes.dex */
    public static class AddDatabaseEvent {
        @JsonProperty(required = true)
        public DatabaseObject database;
    }

    /* loaded from: classes.dex */
    public static class DatabaseObject {
        @JsonProperty(required = true)
        public String domain;
        @JsonProperty(required = true)
        public String id;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public String version;
    }

    /* loaded from: classes.dex */
    public static class Error {
        @JsonProperty(required = true)
        public int code;
        @JsonProperty(required = true)
        public String message;
    }

    /* loaded from: classes.dex */
    public static class ExecuteSQLRequest {
        @JsonProperty(required = true)
        public String databaseId;
        @JsonProperty(required = true)
        public String query;
    }

    /* loaded from: classes.dex */
    public static class ExecuteSQLResponse implements JsonRpcResult {
        @JsonProperty
        public List<String> columnNames;
        @JsonProperty
        public Error sqlError;
        @JsonProperty
        public List<String> values;
    }

    public Database() {
        this.mChromePeerManager.setListener(this.mPeerListener);
        this.mObjectMapper = new ObjectMapper();
    }

    public void add(DatabaseDriver databaseDriver) {
        this.mDatabaseDrivers.add(databaseDriver);
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        this.mChromePeerManager.addPeer(jsonRpcPeer);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        this.mChromePeerManager.removePeer(jsonRpcPeer);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getDatabaseTableNames(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JsonRpcException {
        GetDatabaseTableNamesRequest getDatabaseTableNamesRequest = (GetDatabaseTableNamesRequest) this.mObjectMapper.convertValue(jSONObject, GetDatabaseTableNamesRequest.class);
        DatabaseDriver databasePeer = getDatabasePeer(getDatabaseTableNamesRequest.databaseId);
        try {
            GetDatabaseTableNamesResponse getDatabaseTableNamesResponse = new GetDatabaseTableNamesResponse();
            getDatabaseTableNamesResponse.tableNames = databasePeer.getTableNames(getDatabaseTableNamesRequest.databaseId);
            return getDatabaseTableNamesResponse;
        } catch (SQLiteException e) {
            throw new JsonRpcException(new JsonRpcError(JsonRpcError.ErrorCode.INVALID_REQUEST, e.toString(), null));
        }
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult executeSQL(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        ExecuteSQLRequest executeSQLRequest = (ExecuteSQLRequest) this.mObjectMapper.convertValue(jSONObject, ExecuteSQLRequest.class);
        String str = executeSQLRequest.databaseId;
        String str2 = executeSQLRequest.query;
        try {
            return getDatabasePeer(str).executeSQL(executeSQLRequest.databaseId, executeSQLRequest.query, new DatabaseDriver.ExecuteResultHandler<ExecuteSQLResponse>() { // from class: com.facebook.stetho.inspector.protocol.module.Database.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver.ExecuteResultHandler
                public ExecuteSQLResponse handleRawQuery() throws SQLiteException {
                    ExecuteSQLResponse executeSQLResponse = new ExecuteSQLResponse();
                    executeSQLResponse.columnNames = Collections.singletonList(CommonNetImpl.SUCCESS);
                    executeSQLResponse.values = Collections.singletonList("true");
                    return executeSQLResponse;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver.ExecuteResultHandler
                public ExecuteSQLResponse handleSelect(Cursor cursor) throws SQLiteException {
                    ExecuteSQLResponse executeSQLResponse = new ExecuteSQLResponse();
                    executeSQLResponse.columnNames = Arrays.asList(cursor.getColumnNames());
                    executeSQLResponse.values = Database.flattenRows(cursor, 250);
                    return executeSQLResponse;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver.ExecuteResultHandler
                public ExecuteSQLResponse handleInsert(long j) throws SQLiteException {
                    ExecuteSQLResponse executeSQLResponse = new ExecuteSQLResponse();
                    executeSQLResponse.columnNames = Collections.singletonList("ID of last inserted row");
                    executeSQLResponse.values = Collections.singletonList(String.valueOf(j));
                    return executeSQLResponse;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver.ExecuteResultHandler
                public ExecuteSQLResponse handleUpdateDelete(int i) throws SQLiteException {
                    ExecuteSQLResponse executeSQLResponse = new ExecuteSQLResponse();
                    executeSQLResponse.columnNames = Collections.singletonList("Modified rows");
                    executeSQLResponse.values = Collections.singletonList(String.valueOf(i));
                    return executeSQLResponse;
                }
            });
        } catch (SQLiteException e) {
            Error error = new Error();
            error.code = 0;
            error.message = e.getMessage();
            ExecuteSQLResponse executeSQLResponse = new ExecuteSQLResponse();
            executeSQLResponse.sqlError = error;
            return executeSQLResponse;
        }
    }

    private DatabaseDriver getDatabasePeer(String str) {
        for (DatabaseDriver databaseDriver : this.mDatabaseDrivers) {
            List<String> databaseNames = databaseDriver.getDatabaseNames();
            if (databaseNames != null && databaseNames.contains(str)) {
                return databaseDriver;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<String> flattenRows(Cursor cursor, int i) {
        Util.throwIfNot(i >= 0);
        ArrayList<String> arrayList = new ArrayList<>();
        int columnCount = cursor.getColumnCount();
        for (int i2 = 0; i2 < i && cursor.moveToNext(); i2++) {
            for (int i3 = 0; i3 < columnCount; i3++) {
                int type = cursor.getType(i3);
                if (type != 4) {
                    switch (type) {
                        case 0:
                            arrayList.add(null);
                            break;
                        case 1:
                            arrayList.add(String.valueOf(cursor.getLong(i3)));
                            break;
                        case 2:
                            arrayList.add(String.valueOf(cursor.getDouble(i3)));
                            break;
                        default:
                            arrayList.add(cursor.getString(i3));
                            break;
                    }
                } else {
                    arrayList.add(blobToString(cursor.getBlob(i3)));
                }
            }
        }
        if (!cursor.isAfterLast()) {
            for (int i4 = 0; i4 < columnCount; i4++) {
                arrayList.add("{truncated}");
            }
        }
        return arrayList;
    }

    private static String blobToString(byte[] bArr) {
        if (bArr.length > 512 || !fastIsAscii(bArr)) {
            return UNKNOWN_BLOB_LABEL;
        }
        try {
            return new String(bArr, "US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            return UNKNOWN_BLOB_LABEL;
        }
    }

    private static boolean fastIsAscii(byte[] bArr) {
        for (byte b : bArr) {
            if ((b & Byte.MIN_VALUE) != 0) {
                return false;
            }
        }
        return true;
    }

    /* loaded from: classes.dex */
    private static class DatabasePeerRegistrationListener implements PeerRegistrationListener {
        private final List<DatabaseDriver> mDatabaseDrivers;

        private DatabasePeerRegistrationListener(List<DatabaseDriver> list) {
            this.mDatabaseDrivers = list;
        }

        @Override // com.facebook.stetho.inspector.helper.PeerRegistrationListener
        public void onPeerRegistered(JsonRpcPeer jsonRpcPeer) {
            for (DatabaseDriver databaseDriver : this.mDatabaseDrivers) {
                databaseDriver.onRegistered(jsonRpcPeer);
            }
        }

        @Override // com.facebook.stetho.inspector.helper.PeerRegistrationListener
        public void onPeerUnregistered(JsonRpcPeer jsonRpcPeer) {
            for (DatabaseDriver databaseDriver : this.mDatabaseDrivers) {
                databaseDriver.onUnregistered(jsonRpcPeer);
            }
        }
    }

    /* loaded from: classes.dex */
    private static class GetDatabaseTableNamesRequest {
        @JsonProperty(required = true)
        public String databaseId;

        private GetDatabaseTableNamesRequest() {
        }
    }

    /* loaded from: classes.dex */
    private static class GetDatabaseTableNamesResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<String> tableNames;

        private GetDatabaseTableNamesResponse() {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class DatabaseDriver {
        protected Context mContext;

        /* loaded from: classes.dex */
        public interface ExecuteResultHandler<T> {
            T handleInsert(long j) throws SQLiteException;

            T handleRawQuery() throws SQLiteException;

            T handleSelect(Cursor cursor) throws SQLiteException;

            T handleUpdateDelete(int i) throws SQLiteException;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void onUnregistered(JsonRpcPeer jsonRpcPeer) {
        }

        public abstract ExecuteSQLResponse executeSQL(String str, String str2, ExecuteResultHandler<ExecuteSQLResponse> executeResultHandler) throws SQLiteException;

        public abstract List<String> getDatabaseNames();

        public abstract List<String> getTableNames(String str);

        public DatabaseDriver(Context context) {
            this.mContext = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void onRegistered(JsonRpcPeer jsonRpcPeer) {
            for (String str : getDatabaseNames()) {
                DatabaseObject databaseObject = new DatabaseObject();
                databaseObject.id = str;
                databaseObject.name = str;
                databaseObject.domain = this.mContext.getPackageName();
                databaseObject.version = "N/A";
                AddDatabaseEvent addDatabaseEvent = new AddDatabaseEvent();
                addDatabaseEvent.database = databaseObject;
                jsonRpcPeer.invokeMethod("Database.addDatabase", addDatabaseEvent, null);
            }
        }
    }
}