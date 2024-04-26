package com.facebook.stetho.server.http;

/* loaded from: classes.dex */
public class LightHttpResponse extends LightHttpMessage {
    public LightHttpBody body;
    public int code;
    public String reasonPhrase;

    public void prepare() {
        if (this.body != null) {
            addHeader("Content-Type", this.body.contentType());
            addHeader("Content-Length", String.valueOf(this.body.contentLength()));
        }
    }

    @Override // com.facebook.stetho.server.http.LightHttpMessage
    public void reset() {
        super.reset();
        this.code = -1;
        this.reasonPhrase = null;
        this.body = null;
    }
}