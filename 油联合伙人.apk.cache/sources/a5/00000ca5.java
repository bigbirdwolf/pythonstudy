package com.facebook.stetho.common;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ArrayListAccumulator<E> extends ArrayList<E> implements Accumulator<E> {
    @Override // com.facebook.stetho.common.Accumulator
    public void store(E e) {
        add(e);
    }
}