package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UMSysLocationCache.java */
/* loaded from: classes.dex */
public class c {
    public static final String a = "lng";
    public static final String b = "lat";
    public static final String c = "ts";
    public static final long d = 30000;
    public static final int e = 200;
    private static final String f = "UMSysLocationCache";
    private static boolean g = true;

    public static void a(final Context context) {
        ULog.i(f, "begin location");
        if (context == null) {
            return;
        }
        try {
            new Thread(new Runnable() { // from class: com.umeng.commonsdk.proguard.c.1
                @Override // java.lang.Runnable
                public void run() {
                    while (c.g) {
                        try {
                            JSONArray b2 = c.b(context);
                            if (b2 != null && b2.length() >= 200) {
                                boolean unused = c.g = false;
                                return;
                            }
                            ULog.i(c.f, "location status is ok, time is " + System.currentTimeMillis());
                            final b bVar = new b(context);
                            bVar.a(new d() { // from class: com.umeng.commonsdk.proguard.c.1.1
                                @Override // com.umeng.commonsdk.proguard.d
                                public void a(Location location) {
                                    if (location != null) {
                                        try {
                                            double longitude = location.getLongitude();
                                            double latitude = location.getLatitude();
                                            float accuracy = location.getAccuracy();
                                            double altitude = location.getAltitude();
                                            ULog.i(c.f, "lon is " + longitude + ", lat is " + latitude + ", acc is " + accuracy + ", alt is " + altitude);
                                            if (longitude != 0.0d && latitude != 0.0d) {
                                                long time = location.getTime();
                                                JSONObject jSONObject = new JSONObject();
                                                try {
                                                    jSONObject.put("lng", longitude);
                                                    jSONObject.put("lat", latitude);
                                                    jSONObject.put("ts", time);
                                                    jSONObject.put("acc", accuracy);
                                                    jSONObject.put("alt", altitude);
                                                } catch (JSONException e2) {
                                                    ULog.i(c.f, "e is " + e2);
                                                }
                                                ULog.i(c.f, "locationJSONObject is " + jSONObject.toString());
                                                SharedPreferences sharedPreferences = context.getSharedPreferences(com.umeng.commonsdk.internal.a.p, 0);
                                                if (sharedPreferences != null) {
                                                    String string = sharedPreferences.getString(com.umeng.commonsdk.internal.a.r, "");
                                                    String string2 = sharedPreferences.getString(com.umeng.commonsdk.internal.a.s, "");
                                                    ULog.i(c.f, "--->>> get lon is " + string + ", lat is " + string2);
                                                    if (TextUtils.isEmpty(string) || Double.parseDouble(string) != longitude || TextUtils.isEmpty(string2) || Double.parseDouble(string2) != latitude) {
                                                        JSONArray b3 = c.b(context);
                                                        if (b3 == null) {
                                                            b3 = new JSONArray();
                                                        }
                                                        b3.put(jSONObject);
                                                        SharedPreferences.Editor edit = sharedPreferences.edit();
                                                        edit.putString(com.umeng.commonsdk.internal.a.r, String.valueOf(longitude));
                                                        edit.putString(com.umeng.commonsdk.internal.a.s, String.valueOf(latitude));
                                                        edit.putString(com.umeng.commonsdk.internal.a.q, b3.toString());
                                                        edit.commit();
                                                        ULog.i(c.f, "location put is ok~~");
                                                    } else {
                                                        ULog.i(c.f, "location same");
                                                    }
                                                }
                                            }
                                        } catch (Throwable th) {
                                            ULog.i(c.f, "" + th.getMessage());
                                        }
                                    }
                                    bVar.a();
                                }
                            });
                            try {
                                Thread.sleep(c.d);
                            } catch (Exception unused2) {
                            }
                        } catch (Throwable unused3) {
                            return;
                        }
                    }
                }
            }).start();
        } catch (Exception unused) {
        }
    }

    public static JSONArray b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(com.umeng.commonsdk.internal.a.p, 0);
        JSONArray jSONArray = null;
        if (sharedPreferences == null) {
            return null;
        }
        try {
            String string = sharedPreferences.getString(com.umeng.commonsdk.internal.a.q, "");
            if (!TextUtils.isEmpty(string)) {
                jSONArray = new JSONArray(string);
            }
        } catch (JSONException e2) {
            ULog.i(f, "e is " + e2);
        } catch (Throwable th) {
            ULog.i(f, "e is " + th);
        }
        if (jSONArray != null) {
            ULog.i(f, "get json str is " + jSONArray.toString());
        }
        return jSONArray;
    }

    public static void c(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(com.umeng.commonsdk.internal.a.p, 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(com.umeng.commonsdk.internal.a.q, "");
                edit.commit();
                ULog.i(f, "delete is ok~~");
            }
        } catch (Throwable unused) {
        }
    }
}