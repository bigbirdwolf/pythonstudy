package com.umeng.socialize.net.utils;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alipay.sdk.cons.b;
import com.umeng.socialize.Config;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.UmengText;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UClient {
    private static final String END = "\r\n";
    private static final String TAG = "UClient";

    public <T extends UResponse> T execute(URequest uRequest, Class<T> cls) {
        ResponseObj httpPostRequest;
        uRequest.onPrepareRequest();
        String trim = uRequest.getHttpMethod().trim();
        verifyMethod(trim);
        if (URequest.GET.equals(trim)) {
            httpPostRequest = httpGetRequest(uRequest);
        } else {
            httpPostRequest = URequest.POST.equals(trim) ? httpPostRequest(uRequest) : null;
        }
        return (T) createResponse(httpPostRequest, cls);
    }

    protected <T extends UResponse> T createResponse(ResponseObj responseObj, Class<T> cls) {
        if (responseObj == null) {
            return null;
        }
        try {
            return cls.getConstructor(Integer.class, JSONObject.class).newInstance(Integer.valueOf(responseObj.httpResponseCode), responseObj.jsonObject);
        } catch (Throwable th) {
            SLog.error(UmengText.NET.CREATE, th);
            return null;
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
    private com.umeng.socialize.net.utils.UClient.ResponseObj httpPostRequest(com.umeng.socialize.net.utils.URequest r8) {
        /*
            Method dump skipped, instructions count: 411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.utils.UClient.httpPostRequest(com.umeng.socialize.net.utils.URequest):com.umeng.socialize.net.utils.UClient$ResponseObj");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private com.umeng.socialize.net.utils.UClient.ResponseObj httpGetRequest(com.umeng.socialize.net.utils.URequest r7) {
        /*
            r6 = this;
            r0 = 0
            java.net.HttpURLConnection r1 = r6.openUrlConnection(r7)     // Catch: java.lang.Throwable -> L54
            if (r1 != 0) goto L10
            r6.closeQuietly(r0)
            if (r1 == 0) goto Lf
            r1.disconnect()
        Lf:
            return r0
        L10:
            int r2 = r1.getResponseCode()     // Catch: java.lang.Throwable -> L4d
            com.umeng.socialize.net.utils.UClient$ResponseObj r3 = new com.umeng.socialize.net.utils.UClient$ResponseObj     // Catch: java.lang.Throwable -> L4d
            r3.<init>()     // Catch: java.lang.Throwable -> L4d
            r3.httpResponseCode = r2     // Catch: java.lang.Throwable -> L4d
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 != r4) goto L41
            java.io.InputStream r2 = r1.getInputStream()     // Catch: java.lang.Throwable -> L4d
            java.lang.String r4 = r1.getContentEncoding()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r5 = r1.getRequestMethod()     // Catch: java.lang.Throwable -> L3f
            org.json.JSONObject r7 = r6.parseResult(r7, r5, r4, r2)     // Catch: java.lang.Throwable -> L3f
            r3.jsonObject = r7     // Catch: java.lang.Throwable -> L3f
            java.lang.String r7 = com.umeng.socialize.utils.UmengText.NET.JSONRESULT     // Catch: java.lang.Throwable -> L3f
            com.umeng.socialize.utils.SLog.debug(r7)     // Catch: java.lang.Throwable -> L3f
            r6.closeQuietly(r2)
            if (r1 == 0) goto L3e
            r1.disconnect()
        L3e:
            return r3
        L3f:
            r7 = move-exception
            goto L57
        L41:
            r6.closeQuietly(r0)
            if (r1 == 0) goto L49
            r1.disconnect()
        L49:
            return r0
        L4a:
            r7 = move-exception
            r2 = r0
            goto L66
        L4d:
            r7 = move-exception
            r2 = r0
            goto L57
        L50:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto L66
        L54:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L57:
            java.lang.String r3 = com.umeng.socialize.utils.UmengText.NET.PARSEERROR     // Catch: java.lang.Throwable -> L65
            com.umeng.socialize.utils.SLog.error(r3, r7)     // Catch: java.lang.Throwable -> L65
            r6.closeQuietly(r2)
            if (r1 == 0) goto L64
            r1.disconnect()
        L64:
            return r0
        L65:
            r7 = move-exception
        L66:
            r6.closeQuietly(r2)
            if (r1 == 0) goto L6e
            r1.disconnect()
        L6e:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.utils.UClient.httpGetRequest(com.umeng.socialize.net.utils.URequest):com.umeng.socialize.net.utils.UClient$ResponseObj");
    }

    private HttpURLConnection openUrlConnection(URequest uRequest) throws IOException {
        String str;
        HttpURLConnection httpURLConnection;
        String trim = uRequest.getHttpMethod().trim();
        if (URequest.GET.equals(trim)) {
            str = uRequest.toGetUrl();
        } else {
            str = URequest.POST.equals(trim) ? uRequest.mBaseUrl : null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        URL url = new URL(str);
        if (b.a.equals(url.getProtocol())) {
            httpURLConnection = (HttpsURLConnection) url.openConnection();
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(Config.connectionTimeOut);
        httpURLConnection.setReadTimeout(Config.readSocketTimeOut);
        httpURLConnection.setRequestMethod(trim);
        if (URequest.GET.equals(trim)) {
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            if (uRequest.mHeaders != null && uRequest.mHeaders.size() > 0) {
                for (String str2 : uRequest.mHeaders.keySet()) {
                    httpURLConnection.setRequestProperty(str2, uRequest.mHeaders.get(str2));
                }
            }
        } else if (URequest.POST.equals(trim)) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    private void verifyMethod(String str) {
        if (TextUtils.isEmpty(str) || URequest.GET.equals(str.trim()) == URequest.POST.equals(str.trim())) {
            throw new RuntimeException(UmengText.netMethodError(str));
        }
    }

    private void addBodyParams(URequest uRequest, OutputStream outputStream, String str) throws IOException {
        boolean z;
        StringBuilder sb = new StringBuilder();
        Map<String, Object> bodyPair = uRequest.getBodyPair();
        for (String str2 : bodyPair.keySet()) {
            if (bodyPair.get(str2) != null) {
                addFormField(sb, str2, bodyPair.get(str2).toString(), str);
            }
        }
        if (sb.length() > 0) {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.write(sb.toString().getBytes());
            outputStream = dataOutputStream;
            z = true;
        } else {
            z = false;
        }
        Map<String, URequest.FilePair> filePair = uRequest.getFilePair();
        if (filePair != null && filePair.size() > 0) {
            for (String str3 : filePair.keySet()) {
                URequest.FilePair filePair2 = filePair.get(str3);
                byte[] bArr = filePair2.mBinaryData;
                if (bArr != null && bArr.length >= 1) {
                    addFilePart(filePair2.mFileName, bArr, str, outputStream);
                    z = true;
                }
            }
        }
        if (z) {
            finishWrite(outputStream, str);
        }
    }

    private void addFormField(StringBuilder sb, String str, String str2, String str3) {
        sb.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        sb.append(str3);
        sb.append(END);
        sb.append("Content-Disposition: form-data; name=\"");
        sb.append(str);
        sb.append("\"");
        sb.append(END);
        sb.append("Content-Type: text/plain; charset=");
        sb.append("UTF-8");
        sb.append(END);
        sb.append(END);
        sb.append(str2);
        sb.append(END);
    }

    private void addFilePart(String str, byte[] bArr, String str2, OutputStream outputStream) throws IOException {
        outputStream.write((HelpFormatter.DEFAULT_LONG_OPT_PREFIX + str2 + END + "Content-Disposition: form-data; name=\"pic\"; filename=\"" + str + "\"" + END + "Content-Type: " + OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE + END + "Content-Transfer-Encoding: binary" + END + END).getBytes());
        outputStream.write(bArr);
        outputStream.write(END.getBytes());
    }

    private void finishWrite(OutputStream outputStream, String str) throws IOException {
        outputStream.write(END.getBytes());
        outputStream.write((HelpFormatter.DEFAULT_LONG_OPT_PREFIX + str + HelpFormatter.DEFAULT_LONG_OPT_PREFIX).getBytes());
        outputStream.write(END.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    protected JSONObject parseResult(URequest uRequest, String str, String str2, InputStream inputStream) {
        InputStream inputStream2;
        String convertStreamToString;
        try {
            inputStream2 = wrapStream(str2, inputStream);
        } catch (Throwable th) {
            th = th;
            inputStream2 = null;
        }
        try {
            convertStreamToString = convertStreamToString(inputStream2);
        } catch (Throwable th2) {
            th = th2;
            try {
                SLog.error(UmengText.NET.PARSEERROR, th);
                return null;
            } finally {
                closeQuietly(inputStream2);
            }
        }
        if ("POST".equals(str)) {
            return new JSONObject(convertStreamToString);
        }
        if ("GET".equals(str)) {
            if (TextUtils.isEmpty(convertStreamToString)) {
                return null;
            }
            return decryptData(uRequest, convertStreamToString);
        }
        return null;
    }

    protected InputStream wrapStream(String str, InputStream inputStream) throws IOException {
        if (str == null || "identity".equalsIgnoreCase(str)) {
            return inputStream;
        }
        if ("gzip".equalsIgnoreCase(str)) {
            return new GZIPInputStream(inputStream);
        }
        if ("deflate".equalsIgnoreCase(str)) {
            return new InflaterInputStream(inputStream, new Inflater(false), 512);
        }
        throw new RuntimeException("unsupported content-encoding: " + str);
    }

    protected String convertStreamToString(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 512);
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine + "\n");
                } else {
                    closeQuietly(inputStreamReader);
                    closeQuietly(bufferedReader);
                    return sb.toString();
                }
            } catch (Throwable th) {
                try {
                    SLog.error(UmengText.NET.TOOL, th);
                    return null;
                } finally {
                    closeQuietly(inputStreamReader);
                    closeQuietly(bufferedReader);
                }
            }
        }
    }

    private JSONObject decryptData(URequest uRequest, String str) {
        try {
            return new JSONObject(uRequest.getDecryptString(str));
        } catch (Throwable th) {
            SLog.error(UmengText.NET.CREATE, th);
            return null;
        }
    }

    protected void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                SLog.error(UmengText.NET.CLOSE, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class ResponseObj {
        public int httpResponseCode;
        public JSONObject jsonObject;

        protected ResponseObj() {
        }
    }
}