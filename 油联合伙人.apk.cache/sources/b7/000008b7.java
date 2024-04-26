package com.alibaba.sdk.android.oss.internal;

import java.io.IOException;
import okhttp3.Response;

/* loaded from: classes.dex */
public interface ResponseParser<T> {
    T parse(Response response) throws IOException;
}