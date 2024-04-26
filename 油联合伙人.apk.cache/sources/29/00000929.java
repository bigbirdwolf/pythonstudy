package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes.dex */
public final class q implements Callable<u> {
    private static final HttpRequestRetryHandler e = new ad();
    protected l a;
    protected Context b;
    protected o c;
    String d;
    private HttpUriRequest f;
    private CookieManager i;
    private AbstractHttpEntity j;
    private HttpHost k;
    private URL l;
    private String q;
    private HttpContext g = new BasicHttpContext();
    private CookieStore h = new BasicCookieStore();
    private int m = 0;
    private boolean n = false;
    private boolean o = false;
    private String p = null;

    public q(l lVar, o oVar) {
        this.a = lVar;
        this.b = this.a.a;
        this.c = oVar;
    }

    private static long a(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if ("max-age".equalsIgnoreCase(strArr[i])) {
                int i2 = i + 1;
                if (strArr[i2] != null) {
                    try {
                        return Long.parseLong(strArr[i2]);
                    } catch (Exception unused) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        return 0L;
    }

    private static HttpUrlHeader a(HttpResponse httpResponse) {
        Header[] allHeaders;
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    private u a(HttpResponse httpResponse, int i, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        String str2;
        new StringBuilder("开始handle，handleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        String str3 = null;
        if (entity == null || httpResponse.getStatusLine().getStatusCode() != 200) {
            if (entity == null) {
                httpResponse.getStatusLine().getStatusCode();
                return null;
            }
            return null;
        }
        new StringBuilder("200，开始处理，handleResponse-2,threadid = ").append(Thread.currentThread().getId());
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            a(entity, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            this.o = false;
            this.a.c(System.currentTimeMillis() - currentTimeMillis);
            this.a.a(byteArray.length);
            new StringBuilder("res:").append(byteArray.length);
            p pVar = new p(a(httpResponse), i, str, byteArray);
            long b = b(httpResponse);
            Header contentType = httpResponse.getEntity().getContentType();
            if (contentType != null) {
                HashMap<String, String> a = a(contentType.getValue());
                str3 = a.get("charset");
                str2 = a.get("Content-Type");
            } else {
                str2 = null;
            }
            pVar.b(str2);
            pVar.a(str3);
            pVar.a(System.currentTimeMillis());
            pVar.b(b);
            try {
                byteArrayOutputStream.close();
                return pVar;
            } catch (IOException e2) {
                throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
            }
        } catch (Throwable th2) {
            th = th2;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    throw new RuntimeException("ArrayOutputStream close error!", e3.getCause());
                }
            }
            throw th;
        }
    }

    private static HashMap<String, String> a(String str) {
        String[] split;
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split(com.alipay.sdk.util.i.b)) {
            String[] split2 = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            hashMap.put(split2[0], split2[1]);
        }
        return hashMap;
    }

    private void a(HttpEntity httpEntity, OutputStream outputStream) {
        InputStream a = b.a(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = a.read(bArr);
                    if (read == -1 || this.c.h()) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    if (this.c.f() != null && contentLength > 0) {
                        this.c.f();
                        o oVar = this.c;
                    }
                }
                outputStream.flush();
            } catch (Exception e2) {
                e2.getCause();
                throw new IOException("HttpWorker Request Error!" + e2.getLocalizedMessage());
            }
        } finally {
            r.a(a);
        }
    }

    private static long b(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader(HttpHeaders.CACHE_CONTROL);
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return a(split);
                } catch (NumberFormatException unused) {
                }
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader(HttpHeaders.EXPIRES);
        if (firstHeader2 != null) {
            return b.b(firstHeader2.getValue()) - System.currentTimeMillis();
        }
        return 0L;
    }

    private URI b() {
        String a = this.c.a();
        if (this.d != null) {
            a = this.d;
        }
        if (a != null) {
            return new URI(a);
        }
        throw new RuntimeException("url should not be null");
    }

    private HttpUriRequest c() {
        if (this.f != null) {
            return this.f;
        }
        if (this.j == null) {
            byte[] b = this.c.b();
            String b2 = this.c.b("gzip");
            if (b != null) {
                if (TextUtils.equals(b2, "true")) {
                    this.j = b.a(b);
                } else {
                    this.j = new ByteArrayEntity(b);
                }
                this.j.setContentType(this.c.c());
            }
        }
        AbstractHttpEntity abstractHttpEntity = this.j;
        if (abstractHttpEntity != null) {
            HttpPost httpPost = new HttpPost(b());
            httpPost.setEntity(abstractHttpEntity);
            this.f = httpPost;
        } else {
            this.f = new HttpGet(b());
        }
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:46:0x011a A[Catch: Exception -> 0x0277, NullPointerException -> 0x02a0, IOException -> 0x02c5, UnknownHostException -> 0x02f6, HttpHostConnectException -> 0x0329, NoHttpResponseException -> 0x0354, SocketTimeoutException -> 0x0386, ConnectTimeoutException -> 0x03b8, ConnectionPoolTimeoutException -> 0x03e9, SSLException -> 0x041a, SSLPeerUnverifiedException -> 0x044b, SSLHandshakeException -> 0x047c, URISyntaxException -> 0x04ad, HttpException -> 0x04ba, TryCatch #8 {HttpHostConnectException -> 0x0329, ConnectionPoolTimeoutException -> 0x03e9, NoHttpResponseException -> 0x0354, blocks: (B:3:0x0005, B:17:0x0032, B:19:0x003a, B:20:0x0041, B:22:0x0049, B:24:0x004f, B:25:0x0053, B:27:0x0059, B:28:0x0067, B:30:0x00e1, B:32:0x00e7, B:34:0x00f1, B:37:0x00fa, B:39:0x0106, B:43:0x0110, B:45:0x0117, B:47:0x0132, B:49:0x013a, B:50:0x0147, B:52:0x016e, B:53:0x0175, B:55:0x017b, B:56:0x017f, B:58:0x0185, B:60:0x0191, B:64:0x01c0, B:65:0x01dc, B:73:0x01fc, B:74:0x0215, B:75:0x0216, B:77:0x021e, B:79:0x0224, B:83:0x0230, B:85:0x0234, B:86:0x0237, B:87:0x0244, B:89:0x024c, B:91:0x0256, B:46:0x011a, B:93:0x026b, B:94:0x0276, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:179:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x013a A[Catch: Exception -> 0x0277, NullPointerException -> 0x02a0, IOException -> 0x02c5, UnknownHostException -> 0x02f6, HttpHostConnectException -> 0x0329, NoHttpResponseException -> 0x0354, SocketTimeoutException -> 0x0386, ConnectTimeoutException -> 0x03b8, ConnectionPoolTimeoutException -> 0x03e9, SSLException -> 0x041a, SSLPeerUnverifiedException -> 0x044b, SSLHandshakeException -> 0x047c, URISyntaxException -> 0x04ad, HttpException -> 0x04ba, TryCatch #8 {HttpHostConnectException -> 0x0329, ConnectionPoolTimeoutException -> 0x03e9, NoHttpResponseException -> 0x0354, blocks: (B:3:0x0005, B:17:0x0032, B:19:0x003a, B:20:0x0041, B:22:0x0049, B:24:0x004f, B:25:0x0053, B:27:0x0059, B:28:0x0067, B:30:0x00e1, B:32:0x00e7, B:34:0x00f1, B:37:0x00fa, B:39:0x0106, B:43:0x0110, B:45:0x0117, B:47:0x0132, B:49:0x013a, B:50:0x0147, B:52:0x016e, B:53:0x0175, B:55:0x017b, B:56:0x017f, B:58:0x0185, B:60:0x0191, B:64:0x01c0, B:65:0x01dc, B:73:0x01fc, B:74:0x0215, B:75:0x0216, B:77:0x021e, B:79:0x0224, B:83:0x0230, B:85:0x0234, B:86:0x0237, B:87:0x0244, B:89:0x024c, B:91:0x0256, B:46:0x011a, B:93:0x026b, B:94:0x0276, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:179:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x016e A[Catch: Exception -> 0x0277, NullPointerException -> 0x02a0, IOException -> 0x02c5, UnknownHostException -> 0x02f6, HttpHostConnectException -> 0x0329, NoHttpResponseException -> 0x0354, SocketTimeoutException -> 0x0386, ConnectTimeoutException -> 0x03b8, ConnectionPoolTimeoutException -> 0x03e9, SSLException -> 0x041a, SSLPeerUnverifiedException -> 0x044b, SSLHandshakeException -> 0x047c, URISyntaxException -> 0x04ad, HttpException -> 0x04ba, TryCatch #8 {HttpHostConnectException -> 0x0329, ConnectionPoolTimeoutException -> 0x03e9, NoHttpResponseException -> 0x0354, blocks: (B:3:0x0005, B:17:0x0032, B:19:0x003a, B:20:0x0041, B:22:0x0049, B:24:0x004f, B:25:0x0053, B:27:0x0059, B:28:0x0067, B:30:0x00e1, B:32:0x00e7, B:34:0x00f1, B:37:0x00fa, B:39:0x0106, B:43:0x0110, B:45:0x0117, B:47:0x0132, B:49:0x013a, B:50:0x0147, B:52:0x016e, B:53:0x0175, B:55:0x017b, B:56:0x017f, B:58:0x0185, B:60:0x0191, B:64:0x01c0, B:65:0x01dc, B:73:0x01fc, B:74:0x0215, B:75:0x0216, B:77:0x021e, B:79:0x0224, B:83:0x0230, B:85:0x0234, B:86:0x0237, B:87:0x0244, B:89:0x024c, B:91:0x0256, B:46:0x011a, B:93:0x026b, B:94:0x0276, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:179:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x017b A[Catch: Exception -> 0x0277, NullPointerException -> 0x02a0, IOException -> 0x02c5, UnknownHostException -> 0x02f6, HttpHostConnectException -> 0x0329, NoHttpResponseException -> 0x0354, SocketTimeoutException -> 0x0386, ConnectTimeoutException -> 0x03b8, ConnectionPoolTimeoutException -> 0x03e9, SSLException -> 0x041a, SSLPeerUnverifiedException -> 0x044b, SSLHandshakeException -> 0x047c, URISyntaxException -> 0x04ad, HttpException -> 0x04ba, TryCatch #8 {HttpHostConnectException -> 0x0329, ConnectionPoolTimeoutException -> 0x03e9, NoHttpResponseException -> 0x0354, blocks: (B:3:0x0005, B:17:0x0032, B:19:0x003a, B:20:0x0041, B:22:0x0049, B:24:0x004f, B:25:0x0053, B:27:0x0059, B:28:0x0067, B:30:0x00e1, B:32:0x00e7, B:34:0x00f1, B:37:0x00fa, B:39:0x0106, B:43:0x0110, B:45:0x0117, B:47:0x0132, B:49:0x013a, B:50:0x0147, B:52:0x016e, B:53:0x0175, B:55:0x017b, B:56:0x017f, B:58:0x0185, B:60:0x0191, B:64:0x01c0, B:65:0x01dc, B:73:0x01fc, B:74:0x0215, B:75:0x0216, B:77:0x021e, B:79:0x0224, B:83:0x0230, B:85:0x0234, B:86:0x0237, B:87:0x0244, B:89:0x024c, B:91:0x0256, B:46:0x011a, B:93:0x026b, B:94:0x0276, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:179:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01f2  */
    @Override // java.util.concurrent.Callable
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.alipay.android.phone.mrpc.core.u call() {
        /*
            Method dump skipped, instructions count: 1244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.q.call():com.alipay.android.phone.mrpc.core.u");
    }

    private void e() {
        if (this.f != null) {
            this.f.abort();
        }
    }

    private String f() {
        if (TextUtils.isEmpty(this.q)) {
            this.q = this.c.b("operationType");
            return this.q;
        }
        return this.q;
    }

    private int g() {
        URL h = h();
        return h.getPort() == -1 ? h.getDefaultPort() : h.getPort();
    }

    private URL h() {
        if (this.l != null) {
            return this.l;
        }
        this.l = new URL(this.c.a());
        return this.l;
    }

    private CookieManager i() {
        if (this.i != null) {
            return this.i;
        }
        this.i = CookieManager.getInstance();
        return this.i;
    }

    public final o a() {
        return this.c;
    }
}