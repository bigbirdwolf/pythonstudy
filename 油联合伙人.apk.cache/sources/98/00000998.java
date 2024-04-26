package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class a {
    private static final String a = "msp";
    private static final String b = "application/octet-stream;binary/octet-stream";
    private static final CookieManager c = new CookieManager();

    /* renamed from: com.alipay.sdk.net.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0007a {
        public final String a;
        public final byte[] b;
        public final Map<String, String> c;

        public C0007a(String str, Map<String, String> map, byte[] bArr) {
            this.a = str;
            this.b = bArr;
            this.c = map;
        }

        public String toString() {
            return String.format("<UrlConnectionConfigure url=%s requestBody=%s headers=%s>", this.a, this.b, this.c);
        }
    }

    /* loaded from: classes.dex */
    public static final class b {
        public final Map<String, List<String>> a;
        public final String b;
        public final byte[] c;

        public b(Map<String, List<String>> map, String str, byte[] bArr) {
            this.a = map;
            this.b = str;
            this.c = bArr;
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
    public static com.alipay.sdk.net.a.b a(android.content.Context r12, com.alipay.sdk.net.a.C0007a r13) {
        /*
            Method dump skipped, instructions count: 478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.net.a.a(android.content.Context, com.alipay.sdk.net.a$a):com.alipay.sdk.net.a$b");
    }

    private static Proxy a(Context context) {
        String c2 = c(context);
        if (c2 == null || c2.contains("wap")) {
            try {
                String property = System.getProperty("https.proxyHost");
                String property2 = System.getProperty("https.proxyPort");
                if (TextUtils.isEmpty(property)) {
                    return null;
                }
                return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, Integer.parseInt(property2)));
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    private static NetworkInfo b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    private static String c(Context context) {
        try {
            NetworkInfo b2 = b(context);
            return (b2 == null || !b2.isAvailable()) ? "none" : b2.getType() == 1 ? "wifi" : b2.getExtraInfo().toLowerCase();
        } catch (Exception unused) {
            return "none";
        }
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}