package com.umeng.commonsdk.proguard;

/* compiled from: ShortStack.java */
/* loaded from: classes.dex */
public class h {
    private short[] a;
    private int b = -1;

    public h(int i) {
        this.a = new short[i];
    }

    public short a() {
        short[] sArr = this.a;
        int i = this.b;
        this.b = i - 1;
        return sArr[i];
    }

    public void a(short s) {
        if (this.a.length == this.b + 1) {
            d();
        }
        short[] sArr = this.a;
        int i = this.b + 1;
        this.b = i;
        sArr[i] = s;
    }

    private void d() {
        short[] sArr = new short[this.a.length * 2];
        System.arraycopy(this.a, 0, sArr, 0, this.a.length);
        this.a = sArr;
    }

    public short b() {
        return this.a[this.b];
    }

    public void c() {
        this.b = -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ShortStack vector:[");
        for (int i = 0; i < this.a.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            if (i == this.b) {
                sb.append(">>");
            }
            sb.append((int) this.a[i]);
            if (i == this.b) {
                sb.append("<<");
            }
        }
        sb.append("]>");
        return sb.toString();
    }
}