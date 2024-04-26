package com.umeng.commonsdk.proguard;

/* compiled from: TMessage.java */
/* loaded from: classes.dex */
public final class ag {
    public final String a;
    public final byte b;
    public final int c;

    public ag() {
        this("", (byte) 0, 0);
    }

    public ag(String str, byte b, int i) {
        this.a = str;
        this.b = b;
        this.c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + ((int) this.b) + " seqid:" + this.c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof ag) {
            return a((ag) obj);
        }
        return false;
    }

    public boolean a(ag agVar) {
        return this.a.equals(agVar.a) && this.b == agVar.b && this.c == agVar.c;
    }
}