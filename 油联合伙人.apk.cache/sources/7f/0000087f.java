package com.alibaba.sdk.android.httpdns;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

/* loaded from: classes.dex */
class a {
    private static a a = new a();

    /* renamed from: a  reason: collision with other field name */
    private static ConcurrentMap f0a;

    /* renamed from: a  reason: collision with other field name */
    private static ConcurrentSkipListSet f1a;

    private a() {
        f0a = new ConcurrentHashMap();
        f1a = new ConcurrentSkipListSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a a() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public int m7a() {
        return f0a.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b a(String str) {
        return (b) f0a.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public ArrayList m8a() {
        return new ArrayList(f0a.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public void m9a(String str) {
        f1a.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, b bVar) {
        f0a.put(str, bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public boolean m10a(String str) {
        return f1a.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        f1a.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        f0a.clear();
        f1a.clear();
    }
}