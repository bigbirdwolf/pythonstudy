package com.umeng.commonsdk.proguard;

import java.io.ByteArrayOutputStream;

/* compiled from: TByteArrayOutputStream.java */
/* loaded from: classes.dex */
public class l extends ByteArrayOutputStream {
    public l(int i) {
        super(i);
    }

    public l() {
    }

    public byte[] a() {
        return this.buf;
    }

    public int b() {
        return this.count;
    }
}