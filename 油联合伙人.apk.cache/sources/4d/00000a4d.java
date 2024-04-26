package com.allenliu.versionchecklib.v2.net;

import android.os.Handler;
import android.os.Looper;
import com.allenliu.versionchecklib.callback.DownloadListener;
import com.allenliu.versionchecklib.core.http.AllenHttp;
import com.allenliu.versionchecklib.core.http.FileCallBack;
import java.io.File;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public class DownloadMangerV2 {
    public static void download(String str, String str2, String str3, final DownloadListener downloadListener) {
        if (str != null && !str.isEmpty()) {
            Request build = new Request.Builder().addHeader("Accept-Encoding", "identity").url(str).build();
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.allenliu.versionchecklib.v2.net.DownloadMangerV2.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DownloadListener.this != null) {
                        DownloadListener.this.onCheckerStartDownload();
                    }
                }
            });
            AllenHttp.getHttpClient().newCall(build).enqueue(new FileCallBack(str2, str3) { // from class: com.allenliu.versionchecklib.v2.net.DownloadMangerV2.2
                @Override // com.allenliu.versionchecklib.core.http.FileCallBack
                public void onSuccess(final File file, Call call, Response response) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.allenliu.versionchecklib.v2.net.DownloadMangerV2.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (downloadListener != null) {
                                downloadListener.onCheckerDownloadSuccess(file);
                            }
                        }
                    });
                }

                @Override // com.allenliu.versionchecklib.core.http.FileCallBack
                public void onDownloading(final int i) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.allenliu.versionchecklib.v2.net.DownloadMangerV2.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (downloadListener != null) {
                                downloadListener.onCheckerDownloading(i);
                            }
                        }
                    });
                }

                @Override // com.allenliu.versionchecklib.core.http.FileCallBack
                public void onDownloadFailed() {
                    DownloadMangerV2.handleFailed(downloadListener);
                }
            });
            return;
        }
        throw new RuntimeException("you must set download url for download function using");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00b1 A[Catch: IOException -> 0x00b5, TRY_LEAVE, TryCatch #3 {IOException -> 0x00b5, blocks: (B:43:0x00ac, B:45:0x00b1), top: B:52:0x00ac }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void response(okhttp3.Response r11, java.lang.String r12, java.lang.String r13, final com.allenliu.versionchecklib.callback.DownloadListener r14) {
        /*
            boolean r0 = r11.isSuccessful()
            if (r0 == 0) goto Lb9
            r0 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r0]
            java.io.File r1 = new java.io.File
            r1.<init>(r12)
            boolean r2 = r1.exists()
            if (r2 != 0) goto L18
            r1.mkdirs()
        L18:
            r1 = 0
            okhttp3.ResponseBody r2 = r11.body()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L95
            java.io.InputStream r2 = r2.byteStream()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L95
            okhttp3.ResponseBody r11 = r11.body()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8e
            long r3 = r11.contentLength()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8e
            java.io.File r11 = new java.io.File     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8e
            r11.<init>(r12, r13)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8e
            boolean r12 = r11.exists()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8e
            if (r12 == 0) goto L38
            r11.delete()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8e
            goto L3b
        L38:
            r11.createNewFile()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8e
        L3b:
            java.io.FileOutputStream r12 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8e
            r12.<init>(r11)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8e
            r5 = 0
        L42:
            int r13 = r2.read(r0)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            r1 = -1
            if (r13 == r1) goto L6f
            r1 = 0
            r12.write(r0, r1, r13)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            long r7 = (long) r13
            long r5 = r5 + r7
            double r7 = (double) r5
            double r9 = (double) r3
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r9)
            double r7 = r7 / r9
            r9 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r7 = r7 * r9
            int r13 = (int) r7
            android.os.Handler r1 = new android.os.Handler     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            android.os.Looper r7 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            r1.<init>(r7)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            com.allenliu.versionchecklib.v2.net.DownloadMangerV2$3 r7 = new com.allenliu.versionchecklib.v2.net.DownloadMangerV2$3     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            r7.<init>()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            r1.post(r7)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            goto L42
        L6f:
            r12.flush()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            android.os.Handler r13 = new android.os.Handler     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            android.os.Looper r0 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            r13.<init>(r0)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            com.allenliu.versionchecklib.v2.net.DownloadMangerV2$4 r0 = new com.allenliu.versionchecklib.v2.net.DownloadMangerV2$4     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            r0.<init>()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            r13.post(r0)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8f
            if (r2 == 0) goto La0
            r2.close()     // Catch: java.io.IOException -> La4
            goto La0
        L89:
            r11 = move-exception
            goto Laa
        L8b:
            r11 = move-exception
            r12 = r1
            goto Laa
        L8e:
            r12 = r1
        L8f:
            r1 = r2
            goto L96
        L91:
            r11 = move-exception
            r12 = r1
            r2 = r12
            goto Laa
        L95:
            r12 = r1
        L96:
            handleFailed(r14)     // Catch: java.lang.Throwable -> La8
            if (r1 == 0) goto L9e
            r1.close()     // Catch: java.io.IOException -> La4
        L9e:
            if (r12 == 0) goto Lbc
        La0:
            r12.close()     // Catch: java.io.IOException -> La4
            goto Lbc
        La4:
            handleFailed(r14)
            goto Lbc
        La8:
            r11 = move-exception
            r2 = r1
        Laa:
            if (r2 == 0) goto Laf
            r2.close()     // Catch: java.io.IOException -> Lb5
        Laf:
            if (r12 == 0) goto Lb8
            r12.close()     // Catch: java.io.IOException -> Lb5
            goto Lb8
        Lb5:
            handleFailed(r14)
        Lb8:
            throw r11
        Lb9:
            handleFailed(r14)
        Lbc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.allenliu.versionchecklib.v2.net.DownloadMangerV2.response(okhttp3.Response, java.lang.String, java.lang.String, com.allenliu.versionchecklib.callback.DownloadListener):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleFailed(final DownloadListener downloadListener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.allenliu.versionchecklib.v2.net.DownloadMangerV2.5
            @Override // java.lang.Runnable
            public void run() {
                if (DownloadListener.this != null) {
                    DownloadListener.this.onCheckerDownloadFail();
                }
            }
        });
    }
}