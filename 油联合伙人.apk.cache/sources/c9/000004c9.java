package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
/* loaded from: classes.dex */
class TreeDocumentFile extends DocumentFile {
    private Context mContext;
    private Uri mUri;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TreeDocumentFile(DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.mContext = context;
        this.mUri = uri;
    }

    @Override // android.support.v4.provider.DocumentFile
    public DocumentFile createFile(String str, String str2) {
        Uri createFile = createFile(this.mContext, this.mUri, str, str2);
        if (createFile != null) {
            return new TreeDocumentFile(this, this.mContext, createFile);
        }
        return null;
    }

    private static Uri createFile(Context context, Uri uri, String str, String str2) {
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), uri, str, str2);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.support.v4.provider.DocumentFile
    public DocumentFile createDirectory(String str) {
        Uri createFile = createFile(this.mContext, this.mUri, "vnd.android.document/directory", str);
        if (createFile != null) {
            return new TreeDocumentFile(this, this.mContext, createFile);
        }
        return null;
    }

    @Override // android.support.v4.provider.DocumentFile
    public Uri getUri() {
        return this.mUri;
    }

    @Override // android.support.v4.provider.DocumentFile
    public String getName() {
        return DocumentsContractApi19.getName(this.mContext, this.mUri);
    }

    @Override // android.support.v4.provider.DocumentFile
    public String getType() {
        return DocumentsContractApi19.getType(this.mContext, this.mUri);
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean isFile() {
        return DocumentsContractApi19.isFile(this.mContext, this.mUri);
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean isVirtual() {
        return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
    }

    @Override // android.support.v4.provider.DocumentFile
    public long lastModified() {
        return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
    }

    @Override // android.support.v4.provider.DocumentFile
    public long length() {
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean canRead() {
        return DocumentsContractApi19.canRead(this.mContext, this.mUri);
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean canWrite() {
        return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean delete() {
        try {
            return DocumentsContract.deleteDocument(this.mContext.getContentResolver(), this.mUri);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean exists() {
        return DocumentsContractApi19.exists(this.mContext, this.mUri);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0073 A[LOOP:1: B:19:0x0070->B:21:0x0073, LOOP_END] */
    @Override // android.support.v4.provider.DocumentFile
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.support.v4.provider.DocumentFile[] listFiles() {
        /*
            r9 = this;
            android.content.Context r0 = r9.mContext
            android.content.ContentResolver r1 = r0.getContentResolver()
            android.net.Uri r0 = r9.mUri
            android.net.Uri r2 = r9.mUri
            java.lang.String r2 = android.provider.DocumentsContract.getDocumentId(r2)
            android.net.Uri r2 = android.provider.DocumentsContract.buildChildDocumentsUriUsingTree(r0, r2)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r3 = 1
            r7 = 0
            r8 = 0
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L47
            java.lang.String r4 = "document_id"
            r3[r7] = r4     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L47
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L47
        L27:
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L42
            if (r2 == 0) goto L3b
            java.lang.String r2 = r1.getString(r7)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L42
            android.net.Uri r3 = r9.mUri     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L42
            android.net.Uri r2 = android.provider.DocumentsContract.buildDocumentUriUsingTree(r3, r2)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L42
            r0.add(r2)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L42
            goto L27
        L3b:
            closeQuietly(r1)
            goto L61
        L3f:
            r0 = move-exception
            r8 = r1
            goto L82
        L42:
            r2 = move-exception
            r8 = r1
            goto L48
        L45:
            r0 = move-exception
            goto L82
        L47:
            r2 = move-exception
        L48:
            java.lang.String r1 = "DocumentFile"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L45
            r3.<init>()     // Catch: java.lang.Throwable -> L45
            java.lang.String r4 = "Failed query: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L45
            r3.append(r2)     // Catch: java.lang.Throwable -> L45
            java.lang.String r2 = r3.toString()     // Catch: java.lang.Throwable -> L45
            android.util.Log.w(r1, r2)     // Catch: java.lang.Throwable -> L45
            closeQuietly(r8)
        L61:
            int r1 = r0.size()
            android.net.Uri[] r1 = new android.net.Uri[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            android.net.Uri[] r0 = (android.net.Uri[]) r0
            int r1 = r0.length
            android.support.v4.provider.DocumentFile[] r1 = new android.support.v4.provider.DocumentFile[r1]
        L70:
            int r2 = r0.length
            if (r7 >= r2) goto L81
            android.support.v4.provider.TreeDocumentFile r2 = new android.support.v4.provider.TreeDocumentFile
            android.content.Context r3 = r9.mContext
            r4 = r0[r7]
            r2.<init>(r9, r3, r4)
            r1[r7] = r2
            int r7 = r7 + 1
            goto L70
        L81:
            return r1
        L82:
            closeQuietly(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.TreeDocumentFile.listFiles():android.support.v4.provider.DocumentFile[]");
    }

    private static void closeQuietly(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean renameTo(String str) {
        try {
            Uri renameDocument = DocumentsContract.renameDocument(this.mContext.getContentResolver(), this.mUri, str);
            if (renameDocument != null) {
                this.mUri = renameDocument;
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}