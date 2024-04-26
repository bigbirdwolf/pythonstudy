package com.github.lzyzsd.jsbridge;

import android.webkit.WebView;

/* loaded from: classes.dex */
public class BridgeUtil {
    static final String CALLBACK_ID_FORMAT = "JAVA_CB_%s";
    static final String EMPTY_STR = "";
    public static final String JAVASCRIPT_STR = "javascript:";
    static final String JS_FETCH_QUEUE_FROM_JAVA = "javascript:WebViewJavascriptBridge._fetchQueue();";
    static final String JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');";
    static final String SPLIT_MARK = "/";
    static final String UNDERLINE_STR = "_";
    static final String YY_FETCH_QUEUE = "yy://return/_fetchQueue/";
    static final String YY_OVERRIDE_SCHEMA = "yy://";
    static final String YY_RETURN_DATA = "yy://return/";

    public static String parseFunctionName(String str) {
        return str.replace("javascript:WebViewJavascriptBridge.", "").replaceAll("\\(.*\\);", "");
    }

    public static String getDataFromReturnUrl(String str) {
        if (str.startsWith(YY_FETCH_QUEUE)) {
            return str.replace(YY_FETCH_QUEUE, "");
        }
        String[] split = str.replace(YY_RETURN_DATA, "").split(SPLIT_MARK);
        if (split.length >= 2) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < split.length; i++) {
                sb.append(split[i]);
            }
            return sb.toString();
        }
        return null;
    }

    public static String getFunctionFromReturnUrl(String str) {
        String[] split = str.replace(YY_RETURN_DATA, "").split(SPLIT_MARK);
        if (split.length >= 1) {
            return split[0];
        }
        return null;
    }

    public static void webViewLoadJs(WebView webView, String str) {
        webView.loadUrl(JAVASCRIPT_STR + (("var newscript = document.createElement(\"script\");newscript.src=\"" + str + "\";") + "document.scripts[0].parentNode.insertBefore(newscript,document.scripts[0]);"));
    }

    public static void webViewLoadLocalJs(WebView webView, String str) {
        String assetFile2Str = assetFile2Str(webView.getContext(), str);
        webView.loadUrl(JAVASCRIPT_STR + assetFile2Str);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String assetFile2Str(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = 0
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L40
            java.io.InputStream r4 = r4.open(r5)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L40
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            r1.<init>(r4)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            r5.<init>(r1)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            r1.<init>()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
        L18:
            java.lang.String r2 = r5.readLine()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            if (r2 == 0) goto L29
            java.lang.String r3 = "^\\s*\\/\\/.*"
            boolean r3 = r2.matches(r3)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            if (r3 != 0) goto L29
            r1.append(r2)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
        L29:
            if (r2 != 0) goto L18
            r5.close()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            r4.close()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L4b
            if (r4 == 0) goto L3a
            r4.close()     // Catch: java.io.IOException -> L3a
        L3a:
            return r5
        L3b:
            r5 = move-exception
            goto L42
        L3d:
            r5 = move-exception
            r4 = r0
            goto L4c
        L40:
            r5 = move-exception
            r4 = r0
        L42:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L4b
            if (r4 == 0) goto L4a
            r4.close()     // Catch: java.io.IOException -> L4a
        L4a:
            return r0
        L4b:
            r5 = move-exception
        L4c:
            if (r4 == 0) goto L51
            r4.close()     // Catch: java.io.IOException -> L51
        L51:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.lzyzsd.jsbridge.BridgeUtil.assetFile2Str(android.content.Context, java.lang.String):java.lang.String");
    }
}