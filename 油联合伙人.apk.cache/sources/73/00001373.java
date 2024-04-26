package com.umeng.commonsdk.stateless;

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
import com.umeng.commonsdk.proguard.k;
import com.umeng.commonsdk.proguard.p;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.v;
import com.umeng.commonsdk.proguard.w;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UMSLEnvelope.java */
/* loaded from: classes.dex */
public class b implements j<b, e>, Serializable, Cloneable {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, v> k;
    private static final long l = 420342210744516016L;
    private static final an m = new an("UMSLEnvelope");
    private static final ad n = new ad("version", (byte) 11, 1);
    private static final ad o = new ad("address", (byte) 11, 2);
    private static final ad p = new ad("signature", (byte) 11, 3);
    private static final ad q = new ad("serial_num", (byte) 8, 4);
    private static final ad r = new ad("ts_secs", (byte) 8, 5);
    private static final ad s = new ad("length", (byte) 8, 6);
    private static final ad t = new ad("entity", (byte) 11, 7);
    private static final ad u = new ad("guid", (byte) 11, 8);
    private static final ad v = new ad("checksum", (byte) 11, 9);
    private static final ad w = new ad("codex", (byte) 8, 10);
    private static final Map<Class<? extends aq>, ar> x = new HashMap();
    private static final int y = 0;
    private static final int z = 1;
    private byte C;
    private e[] D;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    static {
        x.put(as.class, new C0024b());
        x.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.VERSION, (e) new v("version", (byte) 1, new w((byte) 11)));
        enumMap.put((EnumMap) e.ADDRESS, (e) new v("address", (byte) 1, new w((byte) 11)));
        enumMap.put((EnumMap) e.SIGNATURE, (e) new v("signature", (byte) 1, new w((byte) 11)));
        enumMap.put((EnumMap) e.SERIAL_NUM, (e) new v("serial_num", (byte) 1, new w((byte) 8)));
        enumMap.put((EnumMap) e.TS_SECS, (e) new v("ts_secs", (byte) 1, new w((byte) 8)));
        enumMap.put((EnumMap) e.LENGTH, (e) new v("length", (byte) 1, new w((byte) 8)));
        enumMap.put((EnumMap) e.ENTITY, (e) new v("entity", (byte) 1, new w((byte) 11, true)));
        enumMap.put((EnumMap) e.GUID, (e) new v("guid", (byte) 1, new w((byte) 11)));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new v("checksum", (byte) 1, new w((byte) 11)));
        enumMap.put((EnumMap) e.CODEX, (e) new v("codex", (byte) 2, new w((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        v.a(b.class, k);
    }

    /* compiled from: UMSLEnvelope.java */
    /* loaded from: classes.dex */
    public enum e implements q {
        VERSION(1, "version"),
        ADDRESS(2, "address"),
        SIGNATURE(3, "signature"),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");
        
        private static final Map<String, e> k = new HashMap();
        private final short l;
        private final String m;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
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
            return k.get(str);
        }

        e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        @Override // com.umeng.commonsdk.proguard.q
        public short a() {
            return this.l;
        }

        @Override // com.umeng.commonsdk.proguard.q
        public String b() {
            return this.m;
        }
    }

    public b() {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
    }

    public b(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        d(true);
        this.e = i2;
        e(true);
        this.f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public b(b bVar) {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
        this.C = bVar.C;
        if (bVar.d()) {
            this.a = bVar.a;
        }
        if (bVar.g()) {
            this.b = bVar.b;
        }
        if (bVar.j()) {
            this.c = bVar.c;
        }
        this.d = bVar.d;
        this.e = bVar.e;
        this.f = bVar.f;
        if (bVar.w()) {
            this.g = k.d(bVar.g);
        }
        if (bVar.z()) {
            this.h = bVar.h;
        }
        if (bVar.C()) {
            this.i = bVar.i;
        }
        this.j = bVar.j;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* renamed from: a */
    public b deepCopy() {
        return new b(this);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
        e(false);
        this.e = 0;
        f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
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

    public void a(boolean z2) {
        if (z2) {
            return;
        }
        this.a = null;
    }

    public String e() {
        return this.b;
    }

    public b b(String str) {
        this.b = str;
        return this;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public void b(boolean z2) {
        if (z2) {
            return;
        }
        this.b = null;
    }

    public String h() {
        return this.c;
    }

    public b c(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public void c(boolean z2) {
        if (z2) {
            return;
        }
        this.c = null;
    }

    public int k() {
        return this.d;
    }

    public b a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public void l() {
        this.C = g.b(this.C, 0);
    }

    public boolean m() {
        return g.a(this.C, 0);
    }

    public void d(boolean z2) {
        this.C = g.a(this.C, 0, z2);
    }

    public int n() {
        return this.e;
    }

    public b b(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public void o() {
        this.C = g.b(this.C, 1);
    }

    public boolean p() {
        return g.a(this.C, 1);
    }

    public void e(boolean z2) {
        this.C = g.a(this.C, 1, z2);
    }

    public int q() {
        return this.f;
    }

    public b c(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public void r() {
        this.C = g.b(this.C, 2);
    }

    public boolean s() {
        return g.a(this.C, 2);
    }

    public void f(boolean z2) {
        this.C = g.a(this.C, 2, z2);
    }

    public byte[] t() {
        a(k.c(this.g));
        if (this.g == null) {
            return null;
        }
        return this.g.array();
    }

    public ByteBuffer u() {
        return this.g;
    }

    public b a(byte[] bArr) {
        a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    public b a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void v() {
        this.g = null;
    }

    public boolean w() {
        return this.g != null;
    }

    public void g(boolean z2) {
        if (z2) {
            return;
        }
        this.g = null;
    }

    public String x() {
        return this.h;
    }

    public b d(String str) {
        this.h = str;
        return this;
    }

    public void y() {
        this.h = null;
    }

    public boolean z() {
        return this.h != null;
    }

    public void h(boolean z2) {
        if (z2) {
            return;
        }
        this.h = null;
    }

    public String A() {
        return this.i;
    }

    public b e(String str) {
        this.i = str;
        return this;
    }

    public void B() {
        this.i = null;
    }

    public boolean C() {
        return this.i != null;
    }

    public void i(boolean z2) {
        if (z2) {
            return;
        }
        this.i = null;
    }

    public int D() {
        return this.j;
    }

    public b d(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void E() {
        this.C = g.b(this.C, 3);
    }

    public boolean F() {
        return g.a(this.C, 3);
    }

    public void j(boolean z2) {
        this.C = g.a(this.C, 3, z2);
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* renamed from: e */
    public e fieldForId(int i) {
        return e.a(i);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) throws p {
        x.get(aiVar.D()).b().b(aiVar, this);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) throws p {
        x.get(aiVar.D()).b().a(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMSLEnvelope(");
        sb.append("version:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("address:");
        if (this.b == null) {
            sb.append("null");
        } else {
            sb.append(this.b);
        }
        sb.append(", ");
        sb.append("signature:");
        if (this.c == null) {
            sb.append("null");
        } else {
            sb.append(this.c);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f);
        sb.append(", ");
        sb.append("entity:");
        if (this.g == null) {
            sb.append("null");
        } else {
            k.a(this.g, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        if (this.h == null) {
            sb.append("null");
        } else {
            sb.append(this.h);
        }
        sb.append(", ");
        sb.append("checksum:");
        if (this.i == null) {
            sb.append("null");
        } else {
            sb.append(this.i);
        }
        if (F()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.j);
        }
        sb.append(")");
        return sb.toString();
    }

    public void G() throws p {
        if (this.a == null) {
            throw new aj("Required field 'version' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new aj("Required field 'address' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new aj("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new aj("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new aj("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.i != null) {
        } else {
            throw new aj("Required field 'checksum' was not present! Struct: " + toString());
        }
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
            this.C = (byte) 0;
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: UMSLEnvelope.java */
    /* renamed from: com.umeng.commonsdk.stateless.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0024b implements ar {
        private C0024b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UMSLEnvelope.java */
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
                            if (l.b == 11) {
                                bVar.b = aiVar.z();
                                bVar.b(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 11) {
                                bVar.c = aiVar.z();
                                bVar.c(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 4:
                            if (l.b == 8) {
                                bVar.d = aiVar.w();
                                bVar.d(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 5:
                            if (l.b == 8) {
                                bVar.e = aiVar.w();
                                bVar.e(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 6:
                            if (l.b == 8) {
                                bVar.f = aiVar.w();
                                bVar.f(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 7:
                            if (l.b == 11) {
                                bVar.g = aiVar.A();
                                bVar.g(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 8:
                            if (l.b == 11) {
                                bVar.h = aiVar.z();
                                bVar.h(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 9:
                            if (l.b == 11) {
                                bVar.i = aiVar.z();
                                bVar.i(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 10:
                            if (l.b == 8) {
                                bVar.j = aiVar.w();
                                bVar.j(true);
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
                    if (!bVar.m()) {
                        throw new aj("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!bVar.p()) {
                        throw new aj("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (!bVar.s()) {
                        throw new aj("Required field 'length' was not found in serialized data! Struct: " + toString());
                    } else {
                        bVar.G();
                        return;
                    }
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: b */
        public void a(ai aiVar, b bVar) throws p {
            bVar.G();
            aiVar.a(b.m);
            if (bVar.a != null) {
                aiVar.a(b.n);
                aiVar.a(bVar.a);
                aiVar.c();
            }
            if (bVar.b != null) {
                aiVar.a(b.o);
                aiVar.a(bVar.b);
                aiVar.c();
            }
            if (bVar.c != null) {
                aiVar.a(b.p);
                aiVar.a(bVar.c);
                aiVar.c();
            }
            aiVar.a(b.q);
            aiVar.a(bVar.d);
            aiVar.c();
            aiVar.a(b.r);
            aiVar.a(bVar.e);
            aiVar.c();
            aiVar.a(b.s);
            aiVar.a(bVar.f);
            aiVar.c();
            if (bVar.g != null) {
                aiVar.a(b.t);
                aiVar.a(bVar.g);
                aiVar.c();
            }
            if (bVar.h != null) {
                aiVar.a(b.u);
                aiVar.a(bVar.h);
                aiVar.c();
            }
            if (bVar.i != null) {
                aiVar.a(b.v);
                aiVar.a(bVar.i);
                aiVar.c();
            }
            if (bVar.F()) {
                aiVar.a(b.w);
                aiVar.a(bVar.j);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* compiled from: UMSLEnvelope.java */
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
    /* compiled from: UMSLEnvelope.java */
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
            aoVar.a(bVar.d);
            aoVar.a(bVar.e);
            aoVar.a(bVar.f);
            aoVar.a(bVar.g);
            aoVar.a(bVar.h);
            aoVar.a(bVar.i);
            BitSet bitSet = new BitSet();
            if (bVar.F()) {
                bitSet.set(0);
            }
            aoVar.a(bitSet, 1);
            if (bVar.F()) {
                aoVar.a(bVar.j);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, b bVar) throws p {
            ao aoVar = (ao) aiVar;
            bVar.a = aoVar.z();
            bVar.a(true);
            bVar.b = aoVar.z();
            bVar.b(true);
            bVar.c = aoVar.z();
            bVar.c(true);
            bVar.d = aoVar.w();
            bVar.d(true);
            bVar.e = aoVar.w();
            bVar.e(true);
            bVar.f = aoVar.w();
            bVar.f(true);
            bVar.g = aoVar.A();
            bVar.g(true);
            bVar.h = aoVar.z();
            bVar.h(true);
            bVar.i = aoVar.z();
            bVar.i(true);
            if (aoVar.b(1).get(0)) {
                bVar.j = aoVar.w();
                bVar.j(true);
            }
        }
    }
}