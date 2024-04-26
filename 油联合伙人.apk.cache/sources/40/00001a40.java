package com.yltx.oil.partner.modules.web;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Drill {
    private static final String TAG = "Drill";
    public static int index;
    public static final Map<String, DrillKind> mDrillMap = new HashMap();
    private static Gson mGson = new Gson();

    public static void jumpToTarget(Context context, String str, String str2, String str3) {
    }

    static {
        mDrillMap.put(DrillType.DRILL_TYPE_2001, new DrillKind(".common.ui.activity.WebActivity", ".common.ui.activity.WebActivity"));
        mDrillMap.put(DrillType.DRILL_TYPE_FUEL_CARD, new DrillKind(".modules.home.activity.JsBridgeWebActivity", ".modules.home.activity.JsBridgeWebActivity"));
    }

    public static void handle(Context context, String str) throws Exception {
        DrillBean drillBean = (DrillBean) mGson.fromJson(str, (Class<Object>) DrillBean.class);
        handle(context, drillBean.getMethod(), drillBean.getKind(), drillBean.getId());
    }

    public static void handle(Context context, String str, String str2, String str3) throws Exception {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new Exception("method or kind cannot be empty.");
        }
        if (!mDrillMap.containsKey(str)) {
            throw new Exception("module code[" + str + "] undefined");
        }
        DrillKind drillKind = mDrillMap.get(str);
        startActivity(context, str2.equals("0") ? drillKind.getListClzName() : drillKind.getDetailClzName(), str3);
    }

    private static void startActivity(Context context, String str, String str2) throws Exception {
        try {
            Class<?> cls = Class.forName("com.yltx.android" + str);
            if (Drillable.class.isAssignableFrom(cls)) {
                Intent makeDrillIntent = ((Drillable) cls.newInstance()).makeDrillIntent(str2);
                makeDrillIntent.setClass(context, cls);
                context.startActivity(makeDrillIntent);
            }
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "class not found, please check your code.", e);
            throw e;
        } catch (IllegalAccessException e2) {
            Log.e(TAG, e2.getMessage(), e2);
            throw e2;
        } catch (InstantiationException e3) {
            Log.e(TAG, e3.getMessage(), e3);
            throw e3;
        }
    }
}