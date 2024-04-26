package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSRetryHandler;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.internal.ResponseParser;
import com.alibaba.sdk.android.oss.model.OSSResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/* loaded from: classes.dex */
public class OSSRequestTask<T extends OSSResult> implements Callable<T> {
    private OkHttpClient client;
    private ExecutionContext context;
    private int currentRetryCount = 0;
    private RequestMessage message;
    private ResponseParser<T> responseParser;
    private OSSRetryHandler retryHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ProgressTouchableRequestBody extends RequestBody {
        private static final int SEGMENT_SIZE = 2048;
        private BufferedSink bufferedSink;
        private OSSProgressCallback callback;
        private long contentLength;
        private String contentType;
        private byte[] data;
        private File file;
        private InputStream inputStream;

        public ProgressTouchableRequestBody(File file, String str, OSSProgressCallback oSSProgressCallback) {
            this.file = file;
            this.contentType = str;
            this.contentLength = file.length();
            this.callback = oSSProgressCallback;
        }

        public ProgressTouchableRequestBody(byte[] bArr, String str, OSSProgressCallback oSSProgressCallback) {
            this.data = bArr;
            this.contentType = str;
            this.contentLength = bArr.length;
            this.callback = oSSProgressCallback;
        }

        public ProgressTouchableRequestBody(InputStream inputStream, long j, String str, OSSProgressCallback oSSProgressCallback) {
            this.inputStream = inputStream;
            this.contentType = str;
            this.contentLength = j;
            this.callback = oSSProgressCallback;
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            return MediaType.parse(this.contentType);
        }

        @Override // okhttp3.RequestBody
        public long contentLength() throws IOException {
            return this.contentLength;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            Source source;
            if (this.file != null) {
                source = Okio.source(this.file);
            } else if (this.data != null) {
                source = Okio.source(new ByteArrayInputStream(this.data));
            } else {
                source = this.inputStream != null ? Okio.source(this.inputStream) : null;
            }
            long j = 0;
            while (j < this.contentLength) {
                long read = source.read(bufferedSink.buffer(), Math.min(this.contentLength - j, 2048L));
                if (read == -1) {
                    break;
                }
                j += read;
                bufferedSink.flush();
                if (this.callback != null) {
                    this.callback.onProgress(OSSRequestTask.this.context.getRequest(), j, this.contentLength);
                }
            }
            if (source != null) {
                source.close();
            }
        }
    }

    public OSSRequestTask(RequestMessage requestMessage, ResponseParser responseParser, ExecutionContext executionContext, int i) {
        this.responseParser = responseParser;
        this.message = requestMessage;
        this.context = executionContext;
        this.client = executionContext.getClient();
        this.retryHandler = new OSSRetryHandler(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0291 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public T call() throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 856
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.network.OSSRequestTask.call():com.alibaba.sdk.android.oss.model.OSSResult");
    }
}