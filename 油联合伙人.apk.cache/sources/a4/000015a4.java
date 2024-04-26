package com.yltx.oil.partner.data.network.adapter;

import retrofit2.Response;

@Deprecated
/* loaded from: classes.dex */
public final class HttpException extends retrofit2.HttpException {
    public HttpException(Response<?> response) {
        super(response);
    }
}