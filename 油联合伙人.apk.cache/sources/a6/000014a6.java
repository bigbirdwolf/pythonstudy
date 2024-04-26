package com.umeng.socialize.net.dplus.cache;

/* loaded from: classes.dex */
public abstract class IReader<T> {
    private String a;
    public T result;

    public abstract void create(String str);

    public IReader(String str) {
        this.a = str;
    }

    public String getLogFileName() {
        return this.a;
    }

    public static double formatSize(long j) {
        double d = j;
        Double.isNaN(d);
        return (d / 1024.0d) / 1024.0d;
    }
}