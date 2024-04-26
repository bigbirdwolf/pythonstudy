package com.alipay.security.mobile.module.http;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.aa;
import com.alipay.android.phone.mrpc.core.h;
import com.alipay.android.phone.mrpc.core.w;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b implements a {
    private static b d;
    private static DataReportResult e;
    private w a;
    private com.alipay.tscenter.biz.rpc.a.a b;
    private com.alipay.tscenter.biz.rpc.report.general.a c;

    private b(Context context, String str) {
        this.a = null;
        this.b = null;
        this.c = null;
        aa aaVar = new aa();
        aaVar.a(str);
        this.a = new h(context);
        this.b = (com.alipay.tscenter.biz.rpc.a.a) this.a.a(com.alipay.tscenter.biz.rpc.a.a.class, aaVar);
        this.c = (com.alipay.tscenter.biz.rpc.report.general.a) this.a.a(com.alipay.tscenter.biz.rpc.report.general.a.class, aaVar);
    }

    public static synchronized b a(Context context, String str) {
        b bVar;
        synchronized (b.class) {
            if (d == null) {
                d = new b(context, str);
            }
            bVar = d;
        }
        return bVar;
    }

    @Override // com.alipay.security.mobile.module.http.a
    public DataReportResult a(DataReportRequest dataReportRequest) {
        if (dataReportRequest == null) {
            return null;
        }
        if (this.c != null) {
            e = null;
            new Thread(new c(this, dataReportRequest)).start();
            for (int i = com.alipay.security.mobile.module.http.constant.a.a; e == null && i >= 0; i -= 50) {
                Thread.sleep(50L);
            }
        }
        return e;
    }

    @Override // com.alipay.security.mobile.module.http.a
    public boolean a(String str) {
        String str2;
        if (com.alipay.security.mobile.module.a.a.a(str) || this.b == null) {
            return false;
        }
        try {
            com.alipay.tscenter.biz.rpc.a.a aVar = this.b;
            com.alipay.security.mobile.module.a.a.f(str);
            str2 = aVar.a();
        } catch (Throwable unused) {
            str2 = null;
        }
        if (com.alipay.security.mobile.module.a.a.a(str2)) {
            return false;
        }
        return ((Boolean) new JSONObject(str2).get(CommonNetImpl.SUCCESS)).booleanValue();
    }
}