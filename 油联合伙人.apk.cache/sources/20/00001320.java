package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import com.alipay.sdk.app.AlipayResultActivity;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SystemLayerUtil.java */
/* loaded from: classes.dex */
public class j {
    private static final String a = "info";
    private static final String b = "stat";
    private static boolean c = false;
    private static HandlerThread d = null;
    private static Context e = null;
    private static int f = 0;
    private static int g = 0;
    private static int h = 0;
    private static int i = 1;
    private static long j = 0;
    private static long k = 0;
    private static final int l = 40;
    private static final int m = 50000;
    private static SensorManager n;
    private static ArrayList<float[]> o = new ArrayList<>();
    private static SensorEventListener p = new SensorEventListener() { // from class: com.umeng.commonsdk.internal.utils.j.1
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (j.g >= 15) {
                if (j.f < 20) {
                    j.e();
                    j.o.add(sensorEvent.values.clone());
                }
                if (j.f == 20) {
                    j.e();
                    if (j.i == 1) {
                        long unused = j.j = System.currentTimeMillis();
                    }
                    if (j.i == 2) {
                        long unused2 = j.k = System.currentTimeMillis();
                    }
                    j.h();
                    j.l();
                    return;
                }
                return;
            }
            j.c();
        }
    };

    /* compiled from: SystemLayerUtil.java */
    /* loaded from: classes.dex */
    public static class a {
        public int a;
        public int b;
        public long c;
    }

    static /* synthetic */ int c() {
        int i2 = g;
        g = i2 + 1;
        return i2;
    }

    static /* synthetic */ int e() {
        int i2 = f;
        f = i2 + 1;
        return i2;
    }

    static /* synthetic */ int h() {
        int i2 = i;
        i = i2 + 1;
        return i2;
    }

    public static List<Sensor> a(Context context) {
        SensorManager sensorManager;
        if (context == null || (sensorManager = (SensorManager) context.getSystemService(com.umeng.commonsdk.proguard.e.aa)) == null) {
            return null;
        }
        return sensorManager.getSensorList(-1);
    }

    public static void b(Context context) {
        if (context == null || a()) {
            return;
        }
        c = true;
        e = context.getApplicationContext();
        String currentProcessName = UMFrUtils.getCurrentProcessName(context);
        String packageName = context.getPackageName();
        if (currentProcessName == null || !currentProcessName.equals(packageName)) {
            return;
        }
        n = (SensorManager) context.getSystemService(com.umeng.commonsdk.proguard.e.aa);
        if (n != null) {
            final Sensor defaultSensor = n.getDefaultSensor(4);
            final Sensor defaultSensor2 = n.getDefaultSensor(1);
            if (defaultSensor != null) {
                h = 4;
                n.registerListener(p, defaultSensor, m);
            } else if (defaultSensor2 != null) {
                h = 1;
                n.registerListener(p, defaultSensor2, m);
            }
            int nextInt = (new Random().nextInt(3) * 1000) + AlipayResultActivity.c;
            d = new HandlerThread("sensor_thread");
            d.start();
            new Handler(d.getLooper()).postDelayed(new Runnable() { // from class: com.umeng.commonsdk.internal.utils.j.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        int unused = j.f = 0;
                        if (defaultSensor != null) {
                            j.n.registerListener(j.p, defaultSensor, j.m);
                        } else if (defaultSensor2 != null) {
                            j.n.registerListener(j.p, defaultSensor2, j.m);
                        }
                    } catch (Exception unused2) {
                        ULog.i("sensor exception");
                    }
                }
            }, nextInt);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void l() {
        if (n != null) {
            n.unregisterListener(p);
        }
        if (o.size() == 40) {
            f(e);
            if (o != null) {
                o.clear();
            }
            if (d != null) {
                d.quit();
                d = null;
            }
            e = null;
            c = false;
        }
    }

    public static JSONArray c(Context context) {
        String string;
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0);
        if (sharedPreferences == null || (string = sharedPreferences.getString(b, null)) == null) {
            return null;
        }
        try {
            return new JSONArray(string);
        } catch (JSONException unused) {
            return null;
        }
    }

    private static void f(Context context) {
        int i2;
        if (context == null) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i3 = 0; i3 < 2; i3++) {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                int i4 = 20;
                if (i3 == 1) {
                    i2 = 40;
                } else {
                    i4 = 0;
                    i2 = 20;
                }
                while (i4 < i2) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("x", o.get(i4)[0]);
                    jSONObject2.put("y", o.get(i4)[1]);
                    jSONObject2.put("z", o.get(i4)[2]);
                    jSONArray2.put(jSONObject2);
                    i4++;
                }
                if (h == 4) {
                    jSONObject.put("g", jSONArray2);
                } else if (h == 1) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.al, jSONArray2);
                }
                if (i3 == 0) {
                    jSONObject.put("ts", j);
                } else {
                    jSONObject.put("ts", k);
                }
                jSONArray.put(jSONObject);
                UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.l, com.umeng.commonsdk.internal.b.a(context).a(), jSONArray.toString());
            }
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
        }
    }

    public static void d(Context context) {
        if (context == null) {
            return;
        }
        context.getApplicationContext().getSharedPreferences(a, 0).edit().remove(b).commit();
    }

    public static synchronized boolean a() {
        boolean z;
        synchronized (j.class) {
            z = c;
        }
        return z;
    }

    public static List<a> e(Context context) {
        CameraManager cameraManager;
        if (context != null && DeviceConfig.checkPermission(context, "android.permission.CAMERA")) {
            ArrayList arrayList = new ArrayList();
            try {
                if (Build.VERSION.SDK_INT >= 21 && (cameraManager = (CameraManager) context.getSystemService("camera")) != null) {
                    for (String str : cameraManager.getCameraIdList()) {
                        Size size = (Size) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
                        if (size != null) {
                            a aVar = new a();
                            aVar.a = size.getWidth();
                            aVar.b = size.getHeight();
                            aVar.c = System.currentTimeMillis();
                            arrayList.add(aVar);
                        }
                    }
                }
            } catch (Exception unused) {
                ULog.i("camera access exception");
            }
            return arrayList;
        }
        return null;
    }
}