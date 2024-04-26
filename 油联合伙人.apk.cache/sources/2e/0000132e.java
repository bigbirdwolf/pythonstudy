package com.umeng.commonsdk.proguard;

/* compiled from: TField.java */
/* loaded from: classes.dex */
public class ad {
    public final String a;
    public final byte b;
    public final short c;

    public ad() {
        this("", (byte) 0, (short) 0);
    }

    public ad(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + ((int) this.b) + " field-id:" + ((int) this.c) + ">";
    }

    public boolean a(ad adVar) {
        return this.b == adVar.b && this.c == adVar.c;
    }
}