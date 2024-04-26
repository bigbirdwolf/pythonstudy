package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.t;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: TUnion.java */
/* loaded from: classes.dex */
public abstract class t<T extends t<?, ?>, F extends q> implements j<T, F> {
    private static final Map<Class<? extends aq>, ar> c = new HashMap();
    protected Object a;
    protected F b;

    protected abstract F a(short s);

    protected abstract Object a(ai aiVar, ad adVar) throws p;

    protected abstract Object a(ai aiVar, short s) throws p;

    protected abstract void a(ai aiVar) throws p;

    protected abstract void b(ai aiVar) throws p;

    protected abstract void b(F f, Object obj) throws ClassCastException;

    protected abstract ad c(F f);

    protected abstract an d();

    protected t() {
        this.b = null;
        this.a = null;
    }

    static {
        c.put(as.class, new b());
        c.put(at.class, new d());
    }

    protected t(F f, Object obj) {
        a((t<T, F>) f, obj);
    }

    protected t(t<T, F> tVar) {
        if (!tVar.getClass().equals(getClass())) {
            throw new ClassCastException();
        }
        this.b = tVar.b;
        this.a = a(tVar.a);
    }

    private static Object a(Object obj) {
        if (obj instanceof j) {
            return ((j) obj).deepCopy();
        }
        if (obj instanceof ByteBuffer) {
            return k.d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Set) {
            return a((Set) obj);
        }
        return obj instanceof Map ? a((Map<Object, Object>) obj) : obj;
    }

    private static Map a(Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            hashMap.put(a(entry.getKey()), a(entry.getValue()));
        }
        return hashMap;
    }

    private static Set a(Set set) {
        HashSet hashSet = new HashSet();
        for (Object obj : set) {
            hashSet.add(a(obj));
        }
        return hashSet;
    }

    private static List a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object obj : list) {
            arrayList.add(a(obj));
        }
        return arrayList;
    }

    public F a() {
        return this.b;
    }

    public Object b() {
        return this.a;
    }

    public Object a(F f) {
        if (f != this.b) {
            throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
        }
        return b();
    }

    public Object a(int i) {
        return a((t<T, F>) a((short) i));
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean b(F f) {
        return this.b == f;
    }

    public boolean b(int i) {
        return b((t<T, F>) a((short) i));
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) throws p {
        c.get(aiVar.D()).b().b(aiVar, this);
    }

    public void a(F f, Object obj) {
        b(f, obj);
        this.b = f;
        this.a = obj;
    }

    public void a(int i, Object obj) {
        a((t<T, F>) a((short) i), obj);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) throws p {
        c.get(aiVar.D()).b().a(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(" ");
        if (a() != null) {
            Object b2 = b();
            sb.append(c(a()).a);
            sb.append(":");
            if (b2 instanceof ByteBuffer) {
                k.a((ByteBuffer) b2, sb);
            } else {
                sb.append(b2.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.proguard.j
    public final void clear() {
        this.b = null;
        this.a = null;
    }

    /* compiled from: TUnion.java */
    /* loaded from: classes.dex */
    private static class b implements ar {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TUnion.java */
    /* loaded from: classes.dex */
    public static class a extends as<t> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: a */
        public void b(ai aiVar, t tVar) throws p {
            tVar.b = null;
            tVar.a = null;
            aiVar.j();
            ad l = aiVar.l();
            tVar.a = tVar.a(aiVar, l);
            if (tVar.a != null) {
                tVar.b = (F) tVar.a(l.c);
            }
            aiVar.m();
            aiVar.l();
            aiVar.k();
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: b */
        public void a(ai aiVar, t tVar) throws p {
            if (tVar.a() == null || tVar.b() == null) {
                throw new aj("Cannot write a TUnion with no set value!");
            }
            aiVar.a(tVar.d());
            aiVar.a(tVar.c(tVar.b));
            tVar.a(aiVar);
            aiVar.c();
            aiVar.d();
            aiVar.b();
        }
    }

    /* compiled from: TUnion.java */
    /* loaded from: classes.dex */
    private static class d implements ar {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TUnion.java */
    /* loaded from: classes.dex */
    public static class c extends at<t> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: a */
        public void b(ai aiVar, t tVar) throws p {
            tVar.b = null;
            tVar.a = null;
            short v = aiVar.v();
            tVar.a = tVar.a(aiVar, v);
            if (tVar.a != null) {
                tVar.b = (F) tVar.a(v);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: b */
        public void a(ai aiVar, t tVar) throws p {
            if (tVar.a() == null || tVar.b() == null) {
                throw new aj("Cannot write a TUnion with no set value!");
            }
            aiVar.a(tVar.b.a());
            tVar.b(aiVar);
        }
    }
}