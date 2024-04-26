package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
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

/* loaded from: classes.dex */
public class Response implements j<Response, e>, Serializable, Cloneable {
    private static final int __RESP_CODE_ISSET_ID = 0;
    public static final Map<e, v> metaDataMap;
    private static final long serialVersionUID = -4549277923241195391L;
    private byte __isset_bitfield;
    public com.umeng.commonsdk.statistics.proto.d imprint;
    public String msg;
    private e[] optionals;
    public int resp_code;
    private static final an STRUCT_DESC = new an("Response");
    private static final ad RESP_CODE_FIELD_DESC = new ad("resp_code", (byte) 8, 1);
    private static final ad MSG_FIELD_DESC = new ad("msg", (byte) 11, 2);
    private static final ad IMPRINT_FIELD_DESC = new ad(com.umeng.commonsdk.proguard.e.U, (byte) 12, 3);
    private static final Map<Class<? extends aq>, ar> schemes = new HashMap();

    static {
        schemes.put(as.class, new b());
        schemes.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.RESP_CODE, (e) new v("resp_code", (byte) 1, new w((byte) 8)));
        enumMap.put((EnumMap) e.MSG, (e) new v("msg", (byte) 2, new w((byte) 11)));
        enumMap.put((EnumMap) e.IMPRINT, (e) new v(com.umeng.commonsdk.proguard.e.U, (byte) 2, new aa((byte) 12, com.umeng.commonsdk.statistics.proto.d.class)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        v.a(Response.class, metaDataMap);
    }

    /* loaded from: classes.dex */
    public enum e implements q {
        RESP_CODE(1, "resp_code"),
        MSG(2, "msg"),
        IMPRINT(3, com.umeng.commonsdk.proguard.e.U);
        
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
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
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

    public Response() {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
    }

    public Response(int i) {
        this();
        this.resp_code = i;
        setResp_codeIsSet(true);
    }

    public Response(Response response) {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new e[]{e.MSG, e.IMPRINT};
        this.__isset_bitfield = response.__isset_bitfield;
        this.resp_code = response.resp_code;
        if (response.isSetMsg()) {
            this.msg = response.msg;
        }
        if (response.isSetImprint()) {
            this.imprint = new com.umeng.commonsdk.statistics.proto.d(response.imprint);
        }
    }

    @Override // com.umeng.commonsdk.proguard.j
    public j<Response, e> deepCopy() {
        return new Response(this);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        setResp_codeIsSet(false);
        this.resp_code = 0;
        this.msg = null;
        this.imprint = null;
    }

    public int getResp_code() {
        return this.resp_code;
    }

    public Response setResp_code(int i) {
        this.resp_code = i;
        setResp_codeIsSet(true);
        return this;
    }

    public void unsetResp_code() {
        this.__isset_bitfield = g.b(this.__isset_bitfield, 0);
    }

    public boolean isSetResp_code() {
        return g.a(this.__isset_bitfield, 0);
    }

    public void setResp_codeIsSet(boolean z) {
        this.__isset_bitfield = g.a(this.__isset_bitfield, 0, z);
    }

    public String getMsg() {
        return this.msg;
    }

    public Response setMsg(String str) {
        this.msg = str;
        return this;
    }

    public void unsetMsg() {
        this.msg = null;
    }

    public boolean isSetMsg() {
        return this.msg != null;
    }

    public void setMsgIsSet(boolean z) {
        if (z) {
            return;
        }
        this.msg = null;
    }

    public com.umeng.commonsdk.statistics.proto.d getImprint() {
        return this.imprint;
    }

    public Response setImprint(com.umeng.commonsdk.statistics.proto.d dVar) {
        this.imprint = dVar;
        return this;
    }

    public void unsetImprint() {
        this.imprint = null;
    }

    public boolean isSetImprint() {
        return this.imprint != null;
    }

    public void setImprintIsSet(boolean z) {
        if (z) {
            return;
        }
        this.imprint = null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public e fieldForId(int i) {
        return e.a(i);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) throws p {
        schemes.get(aiVar.D()).b().b(aiVar, this);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) throws p {
        schemes.get(aiVar.D()).b().a(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.resp_code);
        if (isSetMsg()) {
            sb.append(", ");
            sb.append("msg:");
            if (this.msg == null) {
                sb.append("null");
            } else {
                sb.append(this.msg);
            }
        }
        if (isSetImprint()) {
            sb.append(", ");
            sb.append("imprint:");
            if (this.imprint == null) {
                sb.append("null");
            } else {
                sb.append(this.imprint);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws p {
        if (this.imprint != null) {
            this.imprint.l();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new ac(new au(objectOutputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.__isset_bitfield = (byte) 0;
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

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
    /* loaded from: classes.dex */
    public static class a extends as<Response> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: a */
        public void b(ai aiVar, Response response) throws p {
            aiVar.j();
            while (true) {
                ad l = aiVar.l();
                if (l.b != 0) {
                    switch (l.c) {
                        case 1:
                            if (l.b == 8) {
                                response.resp_code = aiVar.w();
                                response.setResp_codeIsSet(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 2:
                            if (l.b == 11) {
                                response.msg = aiVar.z();
                                response.setMsgIsSet(true);
                                break;
                            } else {
                                al.a(aiVar, l.b);
                                break;
                            }
                        case 3:
                            if (l.b == 12) {
                                response.imprint = new com.umeng.commonsdk.statistics.proto.d();
                                response.imprint.read(aiVar);
                                response.setImprintIsSet(true);
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
                    if (!response.isSetResp_code()) {
                        throw new aj("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                    }
                    response.validate();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* renamed from: b */
        public void a(ai aiVar, Response response) throws p {
            response.validate();
            aiVar.a(Response.STRUCT_DESC);
            aiVar.a(Response.RESP_CODE_FIELD_DESC);
            aiVar.a(response.resp_code);
            aiVar.c();
            if (response.msg != null && response.isSetMsg()) {
                aiVar.a(Response.MSG_FIELD_DESC);
                aiVar.a(response.msg);
                aiVar.c();
            }
            if (response.imprint != null && response.isSetImprint()) {
                aiVar.a(Response.IMPRINT_FIELD_DESC);
                response.imprint.write(aiVar);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

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
    /* loaded from: classes.dex */
    public static class c extends at<Response> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, Response response) throws p {
            ao aoVar = (ao) aiVar;
            aoVar.a(response.resp_code);
            BitSet bitSet = new BitSet();
            if (response.isSetMsg()) {
                bitSet.set(0);
            }
            if (response.isSetImprint()) {
                bitSet.set(1);
            }
            aoVar.a(bitSet, 2);
            if (response.isSetMsg()) {
                aoVar.a(response.msg);
            }
            if (response.isSetImprint()) {
                response.imprint.write(aoVar);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, Response response) throws p {
            ao aoVar = (ao) aiVar;
            response.resp_code = aoVar.w();
            response.setResp_codeIsSet(true);
            BitSet b = aoVar.b(2);
            if (b.get(0)) {
                response.msg = aoVar.z();
                response.setMsgIsSet(true);
            }
            if (b.get(1)) {
                response.imprint = new com.umeng.commonsdk.statistics.proto.d();
                response.imprint.read(aoVar);
                response.setImprintIsSet(true);
            }
        }
    }
}