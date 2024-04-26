package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: NetworkHelper.java */
/* loaded from: classes.dex */
public class c {
    private static boolean e = false;
    private String a = "10.0.0.172";
    private int b = 80;
    private Context c;
    private b d;

    public c(Context context) {
        this.c = context;
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    private void a() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
    }

    private void b() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        String imprintProperty3 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_p", "");
        String imprintProperty4 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty3)) {
            UMServerURL.OVERSEA_DEFAULT_URL = DataHelper.assembleURL(imprintProperty3);
        }
        if (!TextUtils.isEmpty(imprintProperty4)) {
            UMServerURL.OVERSEA_SECONDARY_URL = DataHelper.assembleURL(imprintProperty4);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.OVERSEA_DEFAULT_URL, UMServerURL.OVERSEA_SECONDARY_URL};
        if (TextUtils.isEmpty(com.umeng.commonsdk.statistics.b.b)) {
            return;
        }
        if (com.umeng.commonsdk.statistics.b.b.startsWith("460") || com.umeng.commonsdk.statistics.b.b.startsWith("461")) {
            AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
        }
    }

    public byte[] a(byte[] bArr, boolean z) {
        if (SdkVersion.SDK_TYPE == 0) {
            a();
        } else {
            UMServerURL.DEFAULT_URL = UMServerURL.OVERSEA_DEFAULT_URL;
            UMServerURL.SECONDARY_URL = UMServerURL.OVERSEA_SECONDARY_URL;
            b();
        }
        int i = 0;
        byte[] bArr2 = null;
        while (true) {
            if (i >= AnalyticsConstants.APPLOG_URL_LIST.length) {
                break;
            }
            bArr2 = b(bArr, AnalyticsConstants.APPLOG_URL_LIST[i]);
            if (bArr2 != null) {
                if (this.d != null) {
                    this.d.onRequestSucceed(z);
                }
            } else {
                if (this.d != null) {
                    this.d.onRequestFailed();
                }
                i++;
            }
        }
        return bArr2;
    }

    private boolean c() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (this.c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.c.getPackageName()) != 0) {
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

    public byte[] a(byte[] bArr, String str) {
        HttpURLConnection httpURLConnection;
        try {
            if (this.d != null) {
                this.d.onRequestStart();
            }
            if (c()) {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
        } catch (Throwable th) {
            th = th;
            httpURLConnection = null;
        }
        try {
            httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
            httpURLConnection.setRequestProperty("X-Umeng-Sdk", a.a(this.c).b());
            httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
            httpURLConnection.setRequestProperty("Content-Type", a.a(this.c).a());
            httpURLConnection.setRequestProperty("X-Umeng-Pro-Ver", "1.0.0");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setRequestMethod("POST");
            boolean z = true;
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            if (Build.VERSION.SDK_INT < 8) {
                System.setProperty("http.keepAlive", "false");
            }
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
            outputStream.close();
            if (this.d != null) {
                this.d.onRequestEnd();
            }
            int responseCode = httpURLConnection.getResponseCode();
            String headerField = httpURLConnection.getHeaderField("Content-Type");
            if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                z = false;
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.d("status code : " + responseCode + "; isThrifit:" + z);
            }
            if (responseCode != 200 || !z) {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            }
            MLog.i("Send message to " + str);
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
            HelperUtils.safeClose(inputStream);
            return readStreamToByteArray;
        } catch (Throwable th2) {
            th = th2;
            try {
                MLog.e("IOException,Failed to send message.", th);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
        }
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x01de: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:92:0x01de */
    public byte[] b(byte[] bArr, String str) {
        HttpsURLConnection httpsURLConnection;
        OutputStream outputStream;
        OutputStream outputStream2;
        boolean z;
        OutputStream outputStream3 = null;
        try {
            try {
                if (this.d != null) {
                    this.d.onRequestStart();
                }
                if (c()) {
                    httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
                } else {
                    httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
                }
            } catch (UnknownHostException unused) {
                httpsURLConnection = null;
                outputStream = null;
            } catch (SSLHandshakeException unused2) {
                httpsURLConnection = null;
                outputStream = null;
            } catch (Throwable th) {
                th = th;
                httpsURLConnection = null;
            }
            try {
                if (!e) {
                    httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                    SSLContext sSLContext = SSLContext.getInstance("TLS");
                    sSLContext.init(null, null, new SecureRandom());
                    httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                    e = true;
                }
                httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpsURLConnection.setRequestProperty("X-Umeng-Sdk", a.a(this.c).b());
                httpsURLConnection.setRequestProperty("Content-Type", a.a(this.c).a());
                httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpsURLConnection.setRequestProperty("X-Umeng-Pro-Ver", "1.0.0");
                httpsURLConnection.setConnectTimeout(30000);
                httpsURLConnection.setReadTimeout(30000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                z = false;
                httpsURLConnection.setUseCaches(false);
                outputStream = httpsURLConnection.getOutputStream();
            } catch (UnknownHostException unused3) {
                outputStream = null;
            } catch (SSLHandshakeException unused4) {
                outputStream = null;
            } catch (Throwable th2) {
                th = th2;
                if (outputStream3 != null) {
                    try {
                        outputStream3.close();
                    } catch (Exception e2) {
                        UMCrashManager.reportCrash(this.c, e2);
                    }
                }
                if (httpsURLConnection != null) {
                    try {
                        httpsURLConnection.getInputStream().close();
                    } catch (IOException unused5) {
                    }
                    httpsURLConnection.disconnect();
                }
                throw th;
            }
            try {
                outputStream.write(bArr);
                outputStream.flush();
                httpsURLConnection.connect();
                if (this.d != null) {
                    this.d.onRequestEnd();
                }
                int responseCode = httpsURLConnection.getResponseCode();
                String headerField = httpsURLConnection.getHeaderField("Content-Type");
                if (!TextUtils.isEmpty(headerField) && headerField.equalsIgnoreCase("application/thrift")) {
                    z = true;
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i("status code : " + responseCode + "; isThrifit:" + z);
                }
                if (responseCode != 200 || !z) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e3) {
                            UMCrashManager.reportCrash(this.c, e3);
                        }
                    }
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.getInputStream().close();
                        } catch (IOException unused6) {
                        }
                        httpsURLConnection.disconnect();
                    }
                    return null;
                }
                MLog.i("Send message to server. status code is: " + responseCode);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "Send message to server. status code is: " + responseCode + "; url = " + str);
                InputStream inputStream = httpsURLConnection.getInputStream();
                try {
                    byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e4) {
                            UMCrashManager.reportCrash(this.c, e4);
                        }
                    }
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.getInputStream().close();
                        } catch (IOException unused7) {
                        }
                        httpsURLConnection.disconnect();
                    }
                    return readStreamToByteArray;
                } finally {
                    HelperUtils.safeClose(inputStream);
                }
            } catch (UnknownHostException unused8) {
                UMLog.aq("A_10200", 2, "\\|");
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e5) {
                        UMCrashManager.reportCrash(this.c, e5);
                    }
                }
                if (httpsURLConnection != null) {
                    try {
                        httpsURLConnection.getInputStream().close();
                    } catch (IOException unused9) {
                    }
                    httpsURLConnection.disconnect();
                }
                return null;
            } catch (SSLHandshakeException unused10) {
                UMLog.aq("A_10201", 2, "\\|");
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e6) {
                        UMCrashManager.reportCrash(this.c, e6);
                    }
                }
                if (httpsURLConnection != null) {
                    try {
                        httpsURLConnection.getInputStream().close();
                    } catch (IOException unused11) {
                    }
                    httpsURLConnection.disconnect();
                }
                return null;
            } catch (Throwable unused12) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e7) {
                        UMCrashManager.reportCrash(this.c, e7);
                    }
                }
                if (httpsURLConnection != null) {
                    try {
                        httpsURLConnection.getInputStream().close();
                    } catch (IOException unused13) {
                    }
                    httpsURLConnection.disconnect();
                }
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream3 = outputStream2;
        }
    }
}