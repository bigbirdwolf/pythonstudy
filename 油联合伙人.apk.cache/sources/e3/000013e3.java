package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.ae;
import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ai;
import com.umeng.commonsdk.proguard.aj;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ao;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.commonsdk.proguard.aq;
import com.umeng.commonsdk.proguard.ar;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.au;
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.p;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.v;
import com.umeng.commonsdk.proguard.w;
import com.umeng.commonsdk.proguard.x;
import com.umeng.commonsdk.proguard.y;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: IdTracking.java */
/* loaded from: classes.dex */
public class c implements j<c, e>, Serializable, Cloneable {
    public static final Map<e, v> d;
    private static final long e = -5764118265293965743L;
    private static final an f = new an("IdTracking");
    private static final ad g = new ad("snapshots", ap.k, 1);
    private static final ad h = new ad("journals", ap.m, 2);
    private static final ad i = new ad("checksum", (byte) 11, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();
    public Map<String, com.umeng.commonsdk.statistics.proto.b> a;
    public List<com.umeng.commonsdk.statistics.proto.a> b;
    public String c;
    private e[] k;

    static {
        j.put(as.class, new b());
        j.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.SNAPSHOTS, (e) new v("snapshots", (byte) 1, new y(ap.k, new w((byte) 11), new aa((byte) 12, com.umeng.commonsdk.statistics.proto.b.class))));
        enumMap.put((EnumMap) e.JOURNALS, (e) new v("journals", (byte) 2, new x(ap.m, new aa((byte) 12, com.umeng.commonsdk.statistics.proto.a.class))));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new v("checksum", (byte) 2, new w((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        v.a(c.class, d);
    }

    /* compiled from: IdTracking.java */
    /* loaded from: classes.dex */
    public enum e implements q {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
        CHECKSUM(3, "checksum");
        
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
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
                case 3:
                    return CHECKSUM;
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

    public c() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public c(Map<String, com.umeng.commonsdk.statistics.proto.b> map) {
        this();
        this.a = map;
    }

    public c(c cVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (cVar.e()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.a.entrySet()) {
                hashMap.put(entry.getKey(), new com.umeng.commonsdk.statistics.proto.b(entry.getValue()));
            }
            this.a = hashMap;
        }
        if (cVar.j()) {
            ArrayList arrayList = new ArrayList();
            for (com.umeng.commonsdk.statistics.proto.a aVar : cVar.b) {
                arrayList.add(new com.umeng.commonsdk.statistics.proto.a(aVar));
            }
            this.b = arrayList;
        }
        if (cVar.m()) {
            this.c = cVar.c;
        }
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* renamed from: a */
    public c deepCopy() {
        return new c(this);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int b() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public void a(String str, com.umeng.commonsdk.statistics.proto.b bVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bVar);
    }

    public Map<String, com.umeng.commonsdk.statistics.proto.b> c() {
        return this.a;
    }

    public c a(Map<String, com.umeng.commonsdk.statistics.proto.b> map) {
        this.a = map;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.a = null;
    }

    public int f() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public Iterator<com.umeng.commonsdk.statistics.proto.a> g() {
        if (this.b == null) {
            return null;
        }
        return this.b.iterator();
    }

    public void a(com.umeng.commonsdk.statistics.proto.a aVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(aVar);
    }

    public List<com.umeng.commonsdk.statistics.proto.a> h() {
        return this.b;
    }

    public c a(List<com.umeng.commonsdk.statistics.proto.a> list) {
        this.b = list;
        return this;
    }

    public void i() {
        this.b = null;
    }

    public boolean j() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.b = null;
    }

    public String k() {
        return this.c;
    }

    public c a(String str) {
        this.c = str;
        return this;
    }

    public void l() {
        this.c = null;
    }

    public boolean m() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.c = null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* renamed from: a */
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
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        if (j()) {
            sb.append(", ");
            sb.append("journals:");
            if (this.b == null) {
                sb.append("null");
            } else {
                sb.append(this.b);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("checksum:");
            if (this.c == null) {
                sb.append("null");
            } else {
                sb.append(this.c);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void n() throws p {
        if (this.a != null) {
            return;
        }
        throw new aj("Required field 'snapshots' was not present! Struct: " + toString());
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
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdTracking.java */
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
    /* compiled from: IdTracking.java */
    /* loaded from: classes.dex */
    public static class a extends as<c> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: a */
        public void b(ai aiVar, c cVar) throws p {
            aiVar.j();
            while (true) {
                ad l = aiVar.l();
                if (l.b != 0) {
                    int i = 0;
                    switch (l.c) {
                        case 1:
                            if (l.b == 13) {
                                af n = aiVar.n();
                                cVar.a = new HashMap(n.c * 2);
                                while (i < n.c) {
                                    String z = aiVar.z();
                                    com.umeng.commonsdk.statistics.proto.b bVar = new com.umeng.commonsdk.statistics.proto.b();
                                    bVar.read(aiVar);
                                    cVar.a.put(z, bVar);
                                    i++;
                                }
                                aiVar.o();
                                cVar.a(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 15) {
                                ae p = aiVar.p();
                                cVar.b = new ArrayList(p.b);
                                while (i < p.b) {
                                    com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
                                    aVar.read(aiVar);
                                    cVar.b.add(aVar);
                                    i++;
                                }
                                aiVar.q();
                                cVar.b(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 11) {
                                cVar.c = aiVar.z();
                                cVar.c(true);
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
                    cVar.n();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: b */
        public void a(ai aiVar, c cVar) throws p {
            cVar.n();
            aiVar.a(c.f);
            if (cVar.a != null) {
                aiVar.a(c.g);
                aiVar.a(new af((byte) 11, (byte) 12, cVar.a.size()));
                for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.a.entrySet()) {
                    aiVar.a(entry.getKey());
                    entry.getValue().write(aiVar);
                }
                aiVar.e();
                aiVar.c();
            }
            if (cVar.b != null && cVar.j()) {
                aiVar.a(c.h);
                aiVar.a(new ae((byte) 12, cVar.b.size()));
                for (com.umeng.commonsdk.statistics.proto.a aVar : cVar.b) {
                    aVar.write(aiVar);
                }
                aiVar.f();
                aiVar.c();
            }
            if (cVar.c != null && cVar.m()) {
                aiVar.a(c.i);
                aiVar.a(cVar.c);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* compiled from: IdTracking.java */
    /* loaded from: classes.dex */
    private static class d implements ar {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* renamed from: a */
        public C0029c b() {
            return new C0029c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: IdTracking.java */
    /* renamed from: com.umeng.commonsdk.statistics.proto.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0029c extends at<c> {
        private C0029c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, c cVar) throws p {
            ao aoVar = (ao) aiVar;
            aoVar.a(cVar.a.size());
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.a.entrySet()) {
                aoVar.a(entry.getKey());
                entry.getValue().write(aoVar);
            }
            BitSet bitSet = new BitSet();
            if (cVar.j()) {
                bitSet.set(0);
            }
            if (cVar.m()) {
                bitSet.set(1);
            }
            aoVar.a(bitSet, 2);
            if (cVar.j()) {
                aoVar.a(cVar.b.size());
                for (com.umeng.commonsdk.statistics.proto.a aVar : cVar.b) {
                    aVar.write(aoVar);
                }
            }
            if (cVar.m()) {
                aoVar.a(cVar.c);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, c cVar) throws p {
            ao aoVar = (ao) aiVar;
            af afVar = new af((byte) 11, (byte) 12, aoVar.w());
            cVar.a = new HashMap(afVar.c * 2);
            for (int i = 0; i < afVar.c; i++) {
                String z = aoVar.z();
                com.umeng.commonsdk.statistics.proto.b bVar = new com.umeng.commonsdk.statistics.proto.b();
                bVar.read(aoVar);
                cVar.a.put(z, bVar);
            }
            cVar.a(true);
            BitSet b = aoVar.b(2);
            if (b.get(0)) {
                ae aeVar = new ae((byte) 12, aoVar.w());
                cVar.b = new ArrayList(aeVar.b);
                for (int i2 = 0; i2 < aeVar.b; i2++) {
                    com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
                    aVar.read(aoVar);
                    cVar.b.add(aVar);
                }
                cVar.b(true);
            }
            if (b.get(1)) {
                cVar.c = aoVar.z();
                cVar.c(true);
            }
        }
    }
}