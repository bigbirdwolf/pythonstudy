package com.chad.library.adapter.base.entity;

/* loaded from: classes.dex */
public abstract class SectionEntity<T> {
    public String header;
    public boolean isHeader;
    public T t;

    public SectionEntity(boolean z, String str) {
        this.isHeader = z;
        this.header = str;
        this.t = null;
    }

    public SectionEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.t = t;
    }
}