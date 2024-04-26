package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.q;
import java.io.Serializable;

/* compiled from: TBase.java */
/* loaded from: classes.dex */
public interface j<T extends j<?, ?>, F extends q> extends Serializable {
    void clear();

    j<T, F> deepCopy();

    F fieldForId(int i);

    void read(ai aiVar) throws p;

    void write(ai aiVar) throws p;
}