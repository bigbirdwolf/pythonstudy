package com.yltx.oil.partner.data.network.interceptors;

import android.util.Log;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/* loaded from: classes.dex */
public final class HttpLoggingInterceptor implements Interceptor {
    private static final String TAG = "HttpLoggingInterceptor";
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Level level;
    private final Logger logger;

    /* loaded from: classes.dex */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    /* loaded from: classes.dex */
    public interface Logger {
        public static final Logger DEFAULT = new Logger() { // from class: com.yltx.oil.partner.data.network.interceptors.HttpLoggingInterceptor.Logger.1
            @Override // com.yltx.oil.partner.data.network.interceptors.HttpLoggingInterceptor.Logger
            public void log(String str) {
                Log.d(HttpLoggingInterceptor.TAG, str);
            }
        };

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.level = Level.NONE;
        this.logger = logger;
    }

    public HttpLoggingInterceptor(Level level) {
        this(Logger.DEFAULT);
        this.level = level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Headers headers;
        Headers headers2;
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean z = level == Level.BODY;
        boolean z2 = z || level == Level.HEADERS;
        RequestBody body = request.body();
        boolean z3 = body != null;
        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String str = "--> " + request.method() + ' ' + requestPath(request.url()) + ' ' + protocol(protocol);
        if (!z2 && z3) {
            str = str + " (" + body.contentLength() + "-byte body)";
        }
        this.logger.log(str);
        if (z2) {
            int size = request.headers().size();
            for (int i = 0; i < size; i++) {
                this.logger.log(headers2.name(i) + ": " + headers2.value(i));
            }
            String str2 = "--> END " + request.method();
            if (z && z3) {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    contentType.charset(UTF8);
                }
                this.logger.log("");
                this.logger.log(buffer.readString(charset));
                str2 = str2 + " (" + body.contentLength() + "-byte body)";
            }
            this.logger.log(str2);
        }
        long nanoTime = System.nanoTime();
        Response proceed = chain.proceed(request);
        long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
        ResponseBody body2 = proceed.body();
        Logger logger = this.logger;
        StringBuilder sb = new StringBuilder();
        sb.append("<-- ");
        sb.append(protocol(proceed.protocol()));
        sb.append(' ');
        sb.append(proceed.code());
        sb.append(' ');
        sb.append(proceed.message());
        sb.append(" (");
        sb.append(millis);
        sb.append("ms");
        sb.append(z2 ? "" : ", " + body2.contentLength() + "-byte body");
        sb.append(')');
        logger.log(sb.toString());
        if (z2) {
            int size2 = proceed.headers().size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.logger.log(headers.name(i2) + ": " + headers.value(i2));
            }
            String str3 = "<-- END HTTP";
            if (z) {
                BufferedSource source = body2.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer2 = source.buffer();
                Charset charset2 = UTF8;
                MediaType contentType2 = body2.contentType();
                if (contentType2 != null) {
                    charset2 = contentType2.charset(UTF8);
                }
                if (body2.contentLength() != 0) {
                    this.logger.log("");
                    this.logger.log(buffer2.clone().readString(charset2));
                }
                str3 = "<-- END HTTP (" + buffer2.size() + "-byte body)";
            }
            this.logger.log(str3);
        }
        return proceed;
    }

    private static String protocol(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }

    private static String requestPath(HttpUrl httpUrl) {
        String encodedPath = httpUrl.encodedPath();
        String encodedQuery = httpUrl.encodedQuery();
        if (encodedQuery != null) {
            return encodedPath + '?' + encodedQuery;
        }
        return encodedPath;
    }
}