package com.alipay.security.mobile.module.http;

import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

/* loaded from: classes.dex */
class c implements Runnable {
    final /* synthetic */ DataReportRequest a;
    final /* synthetic */ b b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar, DataReportRequest dataReportRequest) {
        this.b = bVar;
        this.a = dataReportRequest;
    }

    @Override // java.lang.Runnable
    public void run() {
        DataReportResult dataReportResult;
        DataReportResult dataReportResult2;
        com.alipay.tscenter.biz.rpc.report.general.a aVar;
        try {
            aVar = this.b.c;
            DataReportRequest dataReportRequest = this.a;
            DataReportResult unused = b.e = aVar.a();
        } catch (Throwable th) {
            DataReportResult unused2 = b.e = new DataReportResult();
            dataReportResult = b.e;
            dataReportResult.success = false;
            dataReportResult2 = b.e;
            dataReportResult2.resultCode = "static data rpc upload error, " + com.alipay.security.mobile.module.a.a.a(th);
            com.alipay.security.mobile.module.a.a.a(th);
        }
    }
}