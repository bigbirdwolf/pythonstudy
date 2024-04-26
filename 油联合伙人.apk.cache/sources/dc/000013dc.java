package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.ai;
import com.umeng.commonsdk.proguard.aj;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ao;
import com.umeng.commonsdk.proguard.aq;
import com.umeng.commonsdk.proguard.ar;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.au;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.p;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.v;
import com.umeng.commonsdk.proguard.w;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: IdSnapshot.java */
/* loaded from: classes.dex */
public class b implements j<b, e>, Serializable, Cloneable {
    public static final Map<e, v> d;
    private static final long e = -6496538196005191531L;
    private static final an f = new an("IdSnapshot");
    private static final ad g = new ad("identity", (byte) 11, 1);
    private static final ad h = new ad("ts", (byte) 10, 2);
    private static final ad i = new ad("version", (byte) 8, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();
    private static final int k = 0;
    private static final int l = 1;
    public String a;
    public long b;
    public int c;
    private byte m;

    static {
        j.put(as.class, new C0028b());
        j.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.IDENTITY, (e) new v("identity", (byte) 1, new w((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new v("ts", (byte) 1, new w((byte) 10)));
        enumMap.put((EnumMap) e.VERSION, (e) new v("version", (byte) 1, new w((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        v.a(b.class, d);
    }

    /* compiled from: IdSnapshot.java */
    /* loaded from: classes.dex */
    public enum e implements q {
        IDENTITY(1, "identity"),
        TS(2, "ts"),
        VERSION(3, "version");
        
        private static final Map<String, e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return d.get(str);
        }

        e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        @Override // com.umeng.commonsdk.proguard.q
        public short a() {
            return this.e;
        }

        @Override // com.umeng.commonsdk.proguard.q
        public String b() {
            return this.f;
        }
    }

    public b() {
        this.m = (byte) 0;
    }

    public b(String str, long j2, int i2) {
        this();
        this.a = str;
        this.b = j2;
        b(true);
        this.c = i2;
        c(true);
    }

    public b(b bVar) {
        this.m = (byte) 0;
        this.m = bVar.m;
        if (bVar.d()) {
            this.a = bVar.a;
        }
        this.b = bVar.b;
        this.c = bVar.c;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* renamed from: a */
    public b deepCopy() {
        return new b(this);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0L;
        c(false);
        this.c = 0;
    }

    public String b() {
        return this.a;
    }

    public b a(String str) {
        this.a = str;
        return this;
    }

    public void c() {
        this.a = null;
    }

    public boolean d() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.a = null;
    }

    public long e() {
        return this.b;
    }

    public b a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void f() {
        this.m = g.b(this.m, 0);
    }

    public boolean g() {
        return g.a(this.m, 0);
    }

    public void b(boolean z) {
        this.m = g.a(this.m, 0, z);
    }

    public int h() {
        return this.c;
    }

    public b a(int i2) {
        this.c = i2;
        c(true);
        return this;
    }

    public void i() {
        this.m = g.b(this.m, 1);
    }

    public boolean j() {
        return g.a(this.m, 1);
    }

    public void c(boolean z) {
        this.m = g.a(this.m, 1, z);
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* renamed from: b */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) throws p {
        j.get(aiVar.D()).b().b(aiVar, this);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) throws p {
        j.get(aiVar.D()).b().a(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }

    public void k() throws p {
        if (this.a != null) {
            return;
        }
        throw new aj("Required field 'identity' was not present! Struct: " + toString());
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new ac(new au(objectOutputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.m = (byte) 0;
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdSnapshot.java */
    /* renamed from: com.umeng.commonsdk.statistics.proto.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0028b implements ar {
        private C0028b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: IdSnapshot.java */
    /* loaded from: classes.dex */
    public static class a extends as<b> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: a */
        public void b(ai aiVar, b bVar) throws p {
            aiVar.j();
            while (true) {
                ad l = aiVar.l();
                if (l.b != 0) {
                    switch (l.c) {
                        case 1:
                            if (l.b == 11) {
                                bVar.a = aiVar.z();
                                bVar.a(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 10) {
                                bVar.b = aiVar.x();
                                bVar.b(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 8) {
                                bVar.c = aiVar.w();
                                bVar.c(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        default:
                            al.a(aiVar, l.b);
                            break;
                    }
                    aiVar.m();
                } else {
                    aiVar.k();
                    if (!bVar.g()) {
                        throw new aj("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (!bVar.j()) {
                        throw new aj("Required field 'version' was not found in serialized data! Struct: " + toString());
                    } else {
                        bVar.k();
                        return;
                    }
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: b */
        public void a(ai aiVar, b bVar) throws p {
            bVar.k();
            aiVar.a(b.f);
            if (bVar.a != null) {
                aiVar.a(b.g);
                aiVar.a(bVar.a);
                aiVar.c();
            }
            aiVar.a(b.h);
            aiVar.a(bVar.b);
            aiVar.c();
            aiVar.a(b.i);
            aiVar.a(bVar.c);
            aiVar.c();
            aiVar.d();
            aiVar.b();
        }
    }

    /* compiled from: IdSnapshot.java */
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
    /* compiled from: IdSnapshot.java */
    /* loaded from: classes.dex */
    public static class c extends at<b> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, b bVar) throws p {
            ao aoVar = (ao) aiVar;
            aoVar.a(bVar.a);
            aoVar.a(bVar.b);
            aoVar.a(bVar.c);
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, b bVar) throws p {
            ao aoVar = (ao) aiVar;
            bVar.a = aoVar.z();
            bVar.a(true);
            bVar.b = aoVar.x();
            bVar.b(true);
            bVar.c = aoVar.w();
            bVar.c(true);
        }
    }
}