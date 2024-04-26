package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.ac;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TDeserializer.java */
/* loaded from: classes.dex */
public class m {
    private final ai a;
    private final av b;

    public m() {
        this(new ac.a());
    }

    public m(ak akVar) {
        this.b = new av();
        this.a = akVar.a(this.b);
    }

    public void a(j jVar, byte[] bArr) throws p {
        try {
            this.b.a(bArr);
            jVar.read(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(j jVar, String str, String str2) throws p {
        try {
            try {
                a(jVar, str.getBytes(str2));
            } catch (UnsupportedEncodingException unused) {
                throw new p("JVM DOES NOT SUPPORT ENCODING: " + str2);
            }
        } finally {
            this.a.B();
        }
    }

    public void a(j jVar, byte[] bArr, q qVar, q... qVarArr) throws p {
        try {
            try {
                if (j(bArr, qVar, qVarArr) != null) {
                    jVar.read(this.a);
                }
            } catch (Exception e) {
                throw new p(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public Boolean a(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Boolean) a((byte) 2, bArr, qVar, qVarArr);
    }

    public Byte b(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Byte) a((byte) 3, bArr, qVar, qVarArr);
    }

    public Double c(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Double) a((byte) 4, bArr, qVar, qVarArr);
    }

    public Short d(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Short) a((byte) 6, bArr, qVar, qVarArr);
    }

    public Integer e(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Integer) a((byte) 8, bArr, qVar, qVarArr);
    }

    public Long f(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (Long) a((byte) 10, bArr, qVar, qVarArr);
    }

    public String g(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (String) a((byte) 11, bArr, qVar, qVarArr);
    }

    public ByteBuffer h(byte[] bArr, q qVar, q... qVarArr) throws p {
        return (ByteBuffer) a((byte) 100, bArr, qVar, qVarArr);
    }

    public Short i(byte[] bArr, q qVar, q... qVarArr) throws p {
        Short sh;
        try {
            try {
                if (j(bArr, qVar, qVarArr) != null) {
                    this.a.j();
                    sh = Short.valueOf(this.a.l().c);
                } else {
                    sh = null;
                }
                return sh;
            } catch (Exception e) {
                throw new p(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private Object a(byte b, byte[] bArr, q qVar, q... qVarArr) throws p {
        Object obj;
        try {
            try {
                ad j = j(bArr, qVar, qVarArr);
                if (j != null) {
                    if (b != 6) {
                        if (b != 8) {
                            if (b != 100) {
                                switch (b) {
                                    case 2:
                                        if (j.b == 2) {
                                            obj = Boolean.valueOf(this.a.t());
                                            break;
                                        }
                                        break;
                                    case 3:
                                        if (j.b == 3) {
                                            obj = Byte.valueOf(this.a.u());
                                            break;
                                        }
                                        break;
                                    case 4:
                                        if (j.b == 4) {
                                            obj = Double.valueOf(this.a.y());
                                            break;
                                        }
                                        break;
                                    default:
                                        switch (b) {
                                            case 10:
                                                if (j.b == 10) {
                                                    obj = Long.valueOf(this.a.x());
                                                    break;
                                                }
                                                break;
                                            case 11:
                                                if (j.b == 11) {
                                                    obj = this.a.z();
                                                    break;
                                                }
                                                break;
                                        }
                                }
                            } else if (j.b == 11) {
                                obj = this.a.A();
                            }
                        } else if (j.b == 8) {
                            obj = Integer.valueOf(this.a.w());
                        }
                    } else if (j.b == 6) {
                        obj = Short.valueOf(this.a.v());
                    }
                    return obj;
                }
                obj = null;
                return obj;
            } catch (Exception e) {
                throw new p(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private ad j(byte[] bArr, q qVar, q... qVarArr) throws p {
        this.b.a(bArr);
        q[] qVarArr2 = new q[qVarArr.length + 1];
        int i = 0;
        qVarArr2[0] = qVar;
        int i2 = 0;
        while (i2 < qVarArr.length) {
            int i3 = i2 + 1;
            qVarArr2[i3] = qVarArr[i2];
            i2 = i3;
        }
        this.a.j();
        ad adVar = null;
        while (i < qVarArr2.length) {
            adVar = this.a.l();
            if (adVar.b == 0 || adVar.c > qVarArr2[i].a()) {
                return null;
            }
            if (adVar.c != qVarArr2[i].a()) {
                al.a(this.a, adVar.b);
                this.a.m();
            } else {
                i++;
                if (i < qVarArr2.length) {
                    this.a.j();
                }
            }
        }
        return adVar;
    }

    public void a(j jVar, String str) throws p {
        a(jVar, str.getBytes());
    }
}