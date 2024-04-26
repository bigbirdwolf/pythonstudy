package com.umeng.commonsdk.framework;

import org.json.JSONObject;

/* loaded from: classes.dex */
public interface UMLogDataProtocol {

    /* loaded from: classes.dex */
    public enum UMBusinessType {
        U_APP,
        U_INTERNAL
    }

    void removeCacheData(Object obj);

    JSONObject setupReportData(long j);

    void workEvent(Object obj, int i);
}