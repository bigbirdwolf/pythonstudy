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
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: ImprintValue.java */
/* loaded from: classes.dex */
public class e implements j<e, EnumC0031e>, Serializable, Cloneable {
    public static final Map<EnumC0031e, v> d;
    private static final long e = 7501688097813630241L;
    private static final an f = new an("ImprintValue");
    private static final ad g = new ad("value", (byte) 11, 1);
    private static final ad h = new ad("ts", (byte) 10, 2);
    private static final ad i = new ad("guid", (byte) 11, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();
    private static final int k = 0;
    public String a;
    public long b;
    public String c;
    private byte l;
    private EnumC0031e[] m;

    static {
        j.put(as.class, new b());
        j.put(at.class, new d());
        EnumMap enumMap = new EnumMap(EnumC0031e.class);
        enumMap.put((EnumMap) EnumC0031e.VALUE, (EnumC0031e) new v("value", (byte) 2, new w((byte) 11)));
        enumMap.put((EnumMap) EnumC0031e.TS, (EnumC0031e) new v("ts", (byte) 1, new w((byte) 10)));
        enumMap.put((EnumMap) EnumC0031e.GUID, (EnumC0031e) new v("guid", (byte) 1, new w((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        v.a(e.class, d);
    }

    /* compiled from: ImprintValue.java */
    /* renamed from: com.umeng.commonsdk.statistics.proto.e$e  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0031e implements q {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, "guid");
        
        private static final Map<String, EnumC0031e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it = EnumSet.allOf(EnumC0031e.class).iterator();
            while (it.hasNext()) {
                EnumC0031e enumC0031e = (EnumC0031e) it.next();
                d.put(enumC0031e.b(), enumC0031e);
            }
        }

        public static EnumC0031e a(int i) {
            switch (i) {
                case 1:
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
                default:
                    return null;
            }
        }

        public static EnumC0031e b(int i) {
            EnumC0031e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static EnumC0031e a(String str) {
            return d.get(str);
        }

        EnumC0031e(short s, String str) {
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

    public e() {
        this.l = (byte) 0;
        this.m = new EnumC0031e[]{EnumC0031e.VALUE};
    }

    public e(long j2, String str) {
        this();
        this.b = j2;
        b(true);
        this.c = str;
    }

    public e(e eVar) {
        this.l = (byte) 0;
        this.m = new EnumC0031e[]{EnumC0031e.VALUE};
        this.l = eVar.l;
        if (eVar.d()) {
            this.a = eVar.a;
        }
        this.b = eVar.b;
        if (eVar.j()) {
            this.c = eVar.c;
        }
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* renamed from: a */
    public e deepCopy() {
        return new e(this);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0L;
        this.c = null;
    }

    public String b() {
        return this.a;
    }

    public e a(String str) {
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

    public e a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void f() {
        this.l = g.b(this.l, 0);
    }

    public boolean g() {
        return g.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = g.a(this.l, 0, z);
    }

    public String h() {
        return this.c;
    }

    public e b(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
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
    public EnumC0031e fieldForId(int i2) {
        return EnumC0031e.a(i2);
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
        boolean z;
        StringBuilder sb = new StringBuilder("ImprintValue(");
        if (d()) {
            sb.append("value:");
            if (this.a == null) {
                sb.append("null");
            } else {
                sb.append(this.a);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("guid:");
        if (this.c == null) {
            sb.append("null");
        } else {
            sb.append(this.c);
        }
        sb.append(")");
        return sb.toString();
    }

    public void k() throws p {
        if (this.c != null) {
            return;
        }
        throw new aj("Required field 'guid' was not present! Struct: " + toString());
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
            this.l = (byte) 0;
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: ImprintValue.java */
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
    /* compiled from: ImprintValue.java */
    /* loaded from: classes.dex */
    public static class a extends as<e> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: a */
        public void b(ai aiVar, e eVar) throws p {
            aiVar.j();
            while (true) {
                ad l = aiVar.l();
                if (l.b != 0) {
                    switch (l.c) {
                        case 1:
                            if (l.b == 11) {
                                eVar.a = aiVar.z();
                                eVar.a(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 10) {
                                eVar.b = aiVar.x();
                                eVar.b(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 11) {
                                eVar.c = aiVar.z();
                                eVar.c(true);
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
                    if (!eVar.g()) {
                        throw new aj("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    eVar.k();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: b */
        public void a(ai aiVar, e eVar) throws p {
            eVar.k();
            aiVar.a(e.f);
            if (eVar.a != null && eVar.d()) {
                aiVar.a(e.g);
                aiVar.a(eVar.a);
                aiVar.c();
            }
            aiVar.a(e.h);
            aiVar.a(eVar.b);
            aiVar.c();
            if (eVar.c != null) {
                aiVar.a(e.i);
                aiVar.a(eVar.c);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* compiled from: ImprintValue.java */
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
    /* compiled from: ImprintValue.java */
    /* loaded from: classes.dex */
    public static class c extends at<e> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, e eVar) throws p {
            ao aoVar = (ao) aiVar;
            aoVar.a(eVar.b);
            aoVar.a(eVar.c);
            BitSet bitSet = new BitSet();
            if (eVar.d()) {
                bitSet.set(0);
            }
            aoVar.a(bitSet, 1);
            if (eVar.d()) {
                aoVar.a(eVar.a);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, e eVar) throws p {
            ao aoVar = (ao) aiVar;
            eVar.b = aoVar.x();
            eVar.b(true);
            eVar.c = aoVar.z();
            eVar.c(true);
            if (aoVar.b(1).get(0)) {
                eVar.a = aoVar.z();
                eVar.a(true);
            }
        }
    }
}