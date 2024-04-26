package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;

/* compiled from: UMSysLocation.java */
/* loaded from: classes.dex */
public class b {
    private static final String a = "UMSysLocation";
    private static final int c = 10000;
    private LocationManager b;
    private Context d;
    private d e;

    private b() {
    }

    public b(Context context) {
        if (context == null) {
            MLog.e("Context参数不能为null");
            return;
        }
        this.d = context.getApplicationContext();
        this.b = (LocationManager) context.getApplicationContext().getSystemService("location");
    }

    public synchronized void a(d dVar) {
        boolean isProviderEnabled;
        boolean isProviderEnabled2;
        Location lastKnownLocation;
        ULog.i(a, "getSystemLocation");
        if (dVar != null && this.d != null) {
            this.e = dVar;
            boolean checkPermission = UMUtils.checkPermission(this.d, "android.permission.ACCESS_COARSE_LOCATION");
            boolean checkPermission2 = UMUtils.checkPermission(this.d, "android.permission.ACCESS_FINE_LOCATION");
            if (!checkPermission && !checkPermission2) {
                if (this.e != null) {
                    this.e.a(null);
                }
                return;
            }
            if (this.b != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    isProviderEnabled = this.b.isProviderEnabled("gps");
                    isProviderEnabled2 = this.b.isProviderEnabled("network");
                } else {
                    isProviderEnabled = checkPermission2 ? this.b.isProviderEnabled("gps") : false;
                    isProviderEnabled2 = checkPermission ? this.b.isProviderEnabled("network") : false;
                }
                if (isProviderEnabled || isProviderEnabled2) {
                    ULog.i(a, "getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)");
                    if (checkPermission2) {
                        lastKnownLocation = this.b.getLastKnownLocation("passive");
                    } else if (checkPermission) {
                        lastKnownLocation = this.b.getLastKnownLocation("network");
                    }
                    this.e.a(lastKnownLocation);
                }
                lastKnownLocation = null;
                this.e.a(lastKnownLocation);
            }
        }
    }

    public synchronized void a() {
        ULog.i(a, "destroy");
        if (this.b != null) {
            this.b = null;
        }
    }
}