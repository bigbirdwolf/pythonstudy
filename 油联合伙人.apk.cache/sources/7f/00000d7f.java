package com.facebook.stetho.inspector.jsonrpc;

import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class PendingRequest {
    @Nullable
    public final PendingRequestCallback callback;
    public final long requestId;

    public PendingRequest(long j, @Nullable PendingRequestCallback pendingRequestCallback) {
        this.requestId = j;
        this.callback = pendingRequestCallback;
    }
}