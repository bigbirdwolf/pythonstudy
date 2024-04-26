package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: UMSLNetWorkSenderHelper.java */
/* loaded from: classes.dex */
public class e {
    private String a = "10.0.0.172";
    private int b = 80;
    private Context c;

    public e(Context context) {
        this.c = context;
    }

    private void a() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "sl_domain_p", "");
        if (TextUtils.isEmpty(imprintProperty)) {
            return;
        }
        a.g = DataHelper.assembleStatelessURL(imprintProperty);
    }

    private void b() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "sl_domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_sl_domain_p", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            a.f = DataHelper.assembleStatelessURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            a.h = DataHelper.assembleStatelessURL(imprintProperty2);
        }
        a.g = a.h;
        if (TextUtils.isEmpty(com.umeng.commonsdk.statistics.b.b)) {
            return;
        }
        if (com.umeng.commonsdk.statistics.b.b.startsWith("460") || com.umeng.commonsdk.statistics.b.b.startsWith("461")) {
            a.g = a.f;
        }
    }

    private boolean c() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (this.c == null || this.c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.c.getSystemService("connectivity");
            if (DeviceConfig.checkPermission(this.c, "android.permission.ACCESS_NETWORK_STATE") && connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.getType() != 1 && (extraInfo = activeNetworkInfo.getExtraInfo()) != null) {
                if (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap")) {
                    if (extraInfo.equals("uniwap")) {
                    }
                }
                return true;
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.c, th);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v14 */
    public boolean a(byte[] bArr, String str) {
        HttpsURLConnection httpsURLConnection;
        InputStream inputStream;
        ?? r2;
        boolean z = false;
        if (bArr == null || str == null) {
            ULog.i("walle", "[stateless] sendMessage, envelopeByte == null || path == null ");
            return false;
        }
        if (SdkVersion.SDK_TYPE == 0) {
            a();
        } else {
            a.f = a.h;
            b();
        }
        OutputStream outputStream = null;
        try {
            try {
                try {
                    if (c()) {
                        httpsURLConnection = (HttpsURLConnection) new URL(a.g + "/" + str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
                    } else {
                        httpsURLConnection = (HttpsURLConnection) new URL(a.g + "/" + str).openConnection();
                    }
                    try {
                        httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                        SSLContext sSLContext = SSLContext.getInstance("TLS");
                        sSLContext.init(null, null, new SecureRandom());
                        httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                        httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                        httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                        httpsURLConnection.setConnectTimeout(30000);
                        httpsURLConnection.setReadTimeout(30000);
                        httpsURLConnection.setRequestMethod("POST");
                        httpsURLConnection.setDoOutput(true);
                        httpsURLConnection.setDoInput(true);
                        httpsURLConnection.setUseCaches(false);
                        OutputStream outputStream2 = httpsURLConnection.getOutputStream();
                        try {
                            outputStream2.write(bArr);
                            outputStream2.flush();
                            httpsURLConnection.connect();
                            r2 = 200;
                            if (httpsURLConnection.getResponseCode() == 200) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("--->>> send stateless message success : ");
                                sb.append(a.g);
                                sb.append("/");
                                sb.append(str);
                                UMRTLog.i(UMRTLog.RTLOG_TAG, sb.toString());
                                z = true;
                                r2 = sb;
                            }
                            if (outputStream2 != null) {
                                try {
                                    outputStream2.close();
                                } catch (Exception unused) {
                                }
                            }
                        } catch (SSLHandshakeException e) {
                            e = e;
                            outputStream = outputStream2;
                            MLog.e("SSLHandshakeException, Failed to send message.", e);
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception unused2) {
                                }
                            }
                            if (httpsURLConnection != null) {
                                inputStream = httpsURLConnection.getInputStream();
                                outputStream = outputStream;
                                inputStream.close();
                                httpsURLConnection.disconnect();
                            }
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            outputStream = outputStream2;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception unused3) {
                                }
                            }
                            if (httpsURLConnection != null) {
                                try {
                                    httpsURLConnection.getInputStream().close();
                                } catch (IOException unused4) {
                                }
                                httpsURLConnection.disconnect();
                            }
                            throw th;
                        }
                    } catch (SSLHandshakeException e2) {
                        e = e2;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (SSLHandshakeException e3) {
                    e = e3;
                    httpsURLConnection = null;
                } catch (Throwable th3) {
                    th = th3;
                    httpsURLConnection = null;
                }
            } catch (IOException unused5) {
            }
            if (httpsURLConnection != null) {
                inputStream = httpsURLConnection.getInputStream();
                outputStream = r2;
                inputStream.close();
                httpsURLConnection.disconnect();
            }
            return z;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public boolean b(byte[] r8, java.lang.String r9) {
        /*
            r7 = this;
            r0 = 0
            if (r8 == 0) goto Ld4
            if (r9 != 0) goto L7
            goto Ld4
        L7:
            r1 = 0
            boolean r2 = r7.c()     // Catch: java.lang.Throwable -> Lb8
            if (r2 == 0) goto L40
            java.net.Proxy r2 = new java.net.Proxy     // Catch: java.lang.Throwable -> Lb8
            java.net.Proxy$Type r3 = java.net.Proxy.Type.HTTP     // Catch: java.lang.Throwable -> Lb8
            java.net.InetSocketAddress r4 = new java.net.InetSocketAddress     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r5 = r7.a     // Catch: java.lang.Throwable -> Lb8
            int r6 = r7.b     // Catch: java.lang.Throwable -> Lb8
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> Lb8
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lb8
            java.net.URL r3 = new java.net.URL     // Catch: java.lang.Throwable -> Lb8
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb8
            r4.<init>()     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r5 = com.umeng.commonsdk.stateless.a.g     // Catch: java.lang.Throwable -> Lb8
            r4.append(r5)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r5 = "/"
            r4.append(r5)     // Catch: java.lang.Throwable -> Lb8
            r4.append(r9)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r9 = r4.toString()     // Catch: java.lang.Throwable -> Lb8
            r3.<init>(r9)     // Catch: java.lang.Throwable -> Lb8
            java.net.URLConnection r9 = r3.openConnection(r2)     // Catch: java.lang.Throwable -> Lb8
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch: java.lang.Throwable -> Lb8
            goto L61
        L40:
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> Lb8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb8
            r3.<init>()     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r4 = com.umeng.commonsdk.stateless.a.g     // Catch: java.lang.Throwable -> Lb8
            r3.append(r4)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch: java.lang.Throwable -> Lb8
            r3.append(r9)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r9 = r3.toString()     // Catch: java.lang.Throwable -> Lb8
            r2.<init>(r9)     // Catch: java.lang.Throwable -> Lb8
            java.net.URLConnection r9 = r2.openConnection()     // Catch: java.lang.Throwable -> Lb8
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch: java.lang.Throwable -> Lb8
        L61:
            java.lang.String r2 = "X-Umeng-UTC"
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> Lb3
            r9.setRequestProperty(r2, r3)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r2 = "Msg-Type"
            java.lang.String r3 = "envelope/json"
            r9.setRequestProperty(r2, r3)     // Catch: java.lang.Throwable -> Lb3
            r2 = 30000(0x7530, float:4.2039E-41)
            r9.setConnectTimeout(r2)     // Catch: java.lang.Throwable -> Lb3
            r9.setReadTimeout(r2)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r2 = "POST"
            r9.setRequestMethod(r2)     // Catch: java.lang.Throwable -> Lb3
            r2 = 1
            r9.setDoOutput(r2)     // Catch: java.lang.Throwable -> Lb3
            r9.setDoInput(r2)     // Catch: java.lang.Throwable -> Lb3
            r9.setUseCaches(r0)     // Catch: java.lang.Throwable -> Lb3
            java.io.OutputStream r3 = r9.getOutputStream()     // Catch: java.lang.Throwable -> Lb3
            r3.write(r8)     // Catch: java.lang.Throwable -> Lb0
            r3.flush()     // Catch: java.lang.Throwable -> Lb0
            r9.connect()     // Catch: java.lang.Throwable -> Lb0
            int r8 = r9.getResponseCode()     // Catch: java.lang.Throwable -> Lb0
            r1 = 200(0xc8, float:2.8E-43)
            if (r8 != r1) goto La2
            r0 = 1
        La2:
            if (r3 == 0) goto La7
            r3.close()     // Catch: java.lang.Exception -> La7
        La7:
            if (r9 == 0) goto Lc7
        La9:
            r9.disconnect()
            goto Lc7
        Lad:
            r8 = move-exception
            r1 = r3
            goto Lc9
        Lb0:
            r8 = move-exception
            r1 = r3
            goto Lba
        Lb3:
            r8 = move-exception
            goto Lba
        Lb5:
            r8 = move-exception
            r9 = r1
            goto Lc9
        Lb8:
            r8 = move-exception
            r9 = r1
        Lba:
            android.content.Context r2 = r7.c     // Catch: java.lang.Throwable -> Lc8
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r2, r8)     // Catch: java.lang.Throwable -> Lc8
            if (r1 == 0) goto Lc4
            r1.close()     // Catch: java.lang.Exception -> Lc4
        Lc4:
            if (r9 == 0) goto Lc7
            goto La9
        Lc7:
            return r0
        Lc8:
            r8 = move-exception
        Lc9:
            if (r1 == 0) goto Lce
            r1.close()     // Catch: java.lang.Exception -> Lce
        Lce:
            if (r9 == 0) goto Ld3
            r9.disconnect()
        Ld3:
            throw r8
        Ld4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.e.b(byte[], java.lang.String):boolean");
    }
}