package com.tencent.mm.opensdk.diffdev.a;

import com.yltx.oil.partner.base.HttpStatusCodes;

/* loaded from: classes.dex */
public enum g {
    UUID_EXPIRED(HttpStatusCodes.CODE_402),
    UUID_CANCELED(HttpStatusCodes.CODE_403),
    UUID_SCANED(404),
    UUID_CONFIRM(HttpStatusCodes.CODE_405),
    UUID_KEEP_CONNECT(HttpStatusCodes.CODE_408),
    UUID_ERROR(500);
    
    private int code;

    g(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "UUIDStatusCode:" + this.code;
    }
}