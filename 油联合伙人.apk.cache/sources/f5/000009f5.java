package com.alipay.security.mobile.module.http.model;

import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class b {
    public static c a(DataReportResult dataReportResult) {
        c cVar = new c();
        if (dataReportResult == null) {
            return null;
        }
        cVar.a = dataReportResult.success;
        cVar.b = dataReportResult.resultCode;
        Map<String, String> map = dataReportResult.resultData;
        if (map != null) {
            cVar.h = map.get("apdid");
            cVar.i = map.get("apdidToken");
            cVar.l = map.get("dynamicKey");
            cVar.m = map.get("timeInterval");
            cVar.n = map.get("webrtcUrl");
            cVar.o = "";
            String str = map.get("drmSwitch");
            if (com.alipay.security.mobile.module.a.a.b(str)) {
                if (str.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str.charAt(0));
                    cVar.j = sb.toString();
                }
                if (str.length() >= 3) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str.charAt(2));
                    cVar.k = sb2.toString();
                }
            }
            if (map.containsKey("apse_degrade")) {
                cVar.b(map.get("apse_degrade"));
            }
        }
        return cVar;
    }

    public static DataReportRequest a(d dVar) {
        DataReportRequest dataReportRequest = new DataReportRequest();
        if (dVar == null) {
            return null;
        }
        dataReportRequest.os = dVar.b();
        dataReportRequest.rpcVersion = dVar.a();
        dataReportRequest.bizType = "1";
        dataReportRequest.bizData = new HashMap();
        dataReportRequest.bizData.put("apdid", dVar.c());
        dataReportRequest.bizData.put("apdidToken", dVar.d());
        dataReportRequest.bizData.put("umidToken", dVar.e());
        dataReportRequest.bizData.put("dynamicKey", dVar.g());
        dataReportRequest.deviceData = dVar.f();
        return dataReportRequest;
    }
}