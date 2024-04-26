package com.allenliu.versionchecklib.core.http;

import android.os.Handler;
import android.os.Looper;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* loaded from: classes.dex */
public abstract class FileCallBack implements Callback {
    private Handler handler = new Handler(Looper.getMainLooper());
    private String name;
    private String path;

    public abstract void onDownloadFailed();

    public abstract void onDownloading(int i);

    public abstract void onSuccess(File file, Call call, Response response);

    public FileCallBack(String str, String str2) {
        this.path = str;
        this.name = str2;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException iOException) {
        this.handler.post(new Runnable() { // from class: com.allenliu.versionchecklib.core.http.FileCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                FileCallBack.this.onDownloadFailed();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00c1 A[Catch: IOException -> 0x00bd, TRY_LEAVE, TryCatch #7 {IOException -> 0x00bd, blocks: (B:49:0x00b9, B:53:0x00c1), top: B:60:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // okhttp3.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onResponse(final okhttp3.Call r12, final okhttp3.Response r13) throws java.io.IOException {
        /*
            r11 = this;
            r0 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r0]
            java.io.File r1 = new java.io.File
            java.lang.String r2 = r11.path
            r1.<init>(r2)
            boolean r2 = r1.exists()
            if (r2 != 0) goto L14
            r1.mkdirs()
        L14:
            r1 = 0
            okhttp3.ResponseBody r2 = r13.body()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L94
            java.io.InputStream r2 = r2.byteStream()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L94
            okhttp3.ResponseBody r3 = r13.body()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            r3.contentLength()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            java.lang.String r4 = r11.path     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            java.lang.String r5 = r11.name     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            boolean r4 = r3.exists()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            if (r4 == 0) goto L37
            r3.delete()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            goto L3a
        L37:
            r3.createNewFile()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
        L3a:
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8c
            r5 = 0
        L41:
            int r1 = r2.read(r0)     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            r7 = -1
            if (r1 == r7) goto L6f
            okhttp3.ResponseBody r7 = r13.body()     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            long r7 = r7.contentLength()     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            r9 = 0
            r4.write(r0, r9, r1)     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            long r9 = (long) r1
            long r5 = r5 + r9
            double r9 = (double) r5
            double r7 = (double) r7
            java.lang.Double.isNaN(r9)
            java.lang.Double.isNaN(r7)
            double r9 = r9 / r7
            r7 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r9 = r9 * r7
            int r1 = (int) r9
            android.os.Handler r7 = r11.handler     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            com.allenliu.versionchecklib.core.http.FileCallBack$2 r8 = new com.allenliu.versionchecklib.core.http.FileCallBack$2     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            r8.<init>()     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            r7.post(r8)     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            goto L41
        L6f:
            r4.flush()     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            android.os.Handler r0 = r11.handler     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            com.allenliu.versionchecklib.core.http.FileCallBack$3 r1 = new com.allenliu.versionchecklib.core.http.FileCallBack$3     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            r1.<init>()     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            r0.post(r1)     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L87
            if (r2 == 0) goto L81
            r2.close()     // Catch: java.io.IOException -> La9
        L81:
            r4.close()     // Catch: java.io.IOException -> La9
            goto Lb4
        L85:
            r12 = move-exception
            goto Lb7
        L87:
            r12 = move-exception
            goto L8e
        L89:
            r12 = move-exception
            r4 = r1
            goto Lb7
        L8c:
            r12 = move-exception
            r4 = r1
        L8e:
            r1 = r2
            goto L96
        L90:
            r12 = move-exception
            r2 = r1
            r4 = r2
            goto Lb7
        L94:
            r12 = move-exception
            r4 = r1
        L96:
            r12.printStackTrace()     // Catch: java.lang.Throwable -> Lb5
            android.os.Handler r12 = r11.handler     // Catch: java.lang.Throwable -> Lb5
            com.allenliu.versionchecklib.core.http.FileCallBack$4 r13 = new com.allenliu.versionchecklib.core.http.FileCallBack$4     // Catch: java.lang.Throwable -> Lb5
            r13.<init>()     // Catch: java.lang.Throwable -> Lb5
            r12.post(r13)     // Catch: java.lang.Throwable -> Lb5
            if (r1 == 0) goto Lab
            r1.close()     // Catch: java.io.IOException -> La9
            goto Lab
        La9:
            r12 = move-exception
            goto Lb1
        Lab:
            if (r4 == 0) goto Lb4
            r4.close()     // Catch: java.io.IOException -> La9
            goto Lb4
        Lb1:
            r12.printStackTrace()
        Lb4:
            return
        Lb5:
            r12 = move-exception
            r2 = r1
        Lb7:
            if (r2 == 0) goto Lbf
            r2.close()     // Catch: java.io.IOException -> Lbd
            goto Lbf
        Lbd:
            r13 = move-exception
            goto Lc5
        Lbf:
            if (r4 == 0) goto Lc8
            r4.close()     // Catch: java.io.IOException -> Lbd
            goto Lc8
        Lc5:
            r13.printStackTrace()
        Lc8:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.allenliu.versionchecklib.core.http.FileCallBack.onResponse(okhttp3.Call, okhttp3.Response):void");
    }
}