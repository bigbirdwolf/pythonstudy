package com.umeng.socialize.net.dplus.cache;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import com.umeng.socialize.net.dplus.db.DBConfig;
import com.umeng.socialize.net.dplus.db.DBManager;
import com.umeng.socialize.utils.ContextUtil;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DplusCacheApi {
    private static final String a = "DplusCacheApi";
    private HandlerThread b;
    private Handler c;
    private final int d;
    private ArrayList<Integer> e;
    private ArrayList<Integer> f;
    private ArrayList<Integer> g;
    private ArrayList<Integer> h;
    private ArrayList<Integer> i;

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static final DplusCacheApi a = new DplusCacheApi();

        private SingletonHolder() {
        }
    }

    public static final DplusCacheApi getInstance() {
        return SingletonHolder.a;
    }

    private DplusCacheApi() {
        this.d = 1048576;
        this.e = new ArrayList<>();
        this.f = new ArrayList<>();
        this.g = new ArrayList<>();
        this.h = new ArrayList<>();
        this.i = new ArrayList<>();
        this.b = new HandlerThread(a, 10);
        this.b.start();
        this.c = new Handler(this.b.getLooper());
    }

    public void saveFile(final Context context, final JSONObject jSONObject, final int i, final DplusCacheListener dplusCacheListener) {
        this.c.post(new Runnable() { // from class: com.umeng.socialize.net.dplus.cache.DplusCacheApi.1
            @Override // java.lang.Runnable
            public void run() {
                switch (i) {
                    case SocializeConstants.DAU_EVENT /* 24577 */:
                        DBManager.get(context).insertDau(jSONObject);
                        break;
                    case SocializeConstants.SHARE_EVENT /* 24578 */:
                        DBManager.get(context).insertS_E(jSONObject);
                        break;
                    case SocializeConstants.AUTH_EVENT /* 24579 */:
                        DBManager.get(context).insertAuth(jSONObject);
                        break;
                    case SocializeConstants.GET_EVENT /* 24580 */:
                        DBManager.get(context).insertUserInfo(jSONObject);
                        break;
                    case SocializeConstants.SAVE_STATS_EVENT /* 24581 */:
                    case SocializeConstants.SEND_DAU_STATS_EVENT /* 24583 */:
                        DBManager.get(context).insertStats(jSONObject);
                        break;
                    case SocializeConstants.CHECK_STATS_EVENT /* 24582 */:
                    default:
                        DBManager.get(context).insertStats(jSONObject);
                        break;
                }
                dplusCacheListener.onResult(null);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0151  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject readFileAsnc(android.content.Context r20, int r21) {
        /*
            Method dump skipped, instructions count: 363
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.cache.DplusCacheApi.readFileAsnc(android.content.Context, int):org.json.JSONObject");
    }

    public void readFile(final Context context, final int i, final DplusCacheListener dplusCacheListener) {
        this.c.post(new Runnable() { // from class: com.umeng.socialize.net.dplus.cache.DplusCacheApi.2
            /* JADX WARN: Removed duplicated region for block: B:52:0x016f  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 402
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.cache.DplusCacheApi.AnonymousClass2.run():void");
            }
        });
    }

    private static JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("s_sdk_v", "6.9.4");
        jSONObject.put(CommonNetImpl.PCV, SocializeConstants.PROTOCOL_VERSON);
        return jSONObject;
    }

    public void deleteFile(final Context context) {
        this.c.post(new Runnable() { // from class: com.umeng.socialize.net.dplus.cache.DplusCacheApi.3
            @Override // java.lang.Runnable
            public void run() {
                if (DplusCacheApi.this.e.size() > 0) {
                    DBManager.get(context).delete(DplusCacheApi.this.e, "s_e");
                    DplusCacheApi.this.e.clear();
                }
                if (DplusCacheApi.this.f.size() > 0) {
                    DBManager.get(context).delete(DplusCacheApi.this.f, "auth");
                    DplusCacheApi.this.f.clear();
                }
                if (DplusCacheApi.this.h.size() > 0) {
                    DBManager.get(context).delete(DplusCacheApi.this.h, "dau");
                    DplusCacheApi.this.h.clear();
                }
                if (DplusCacheApi.this.g.size() > 0) {
                    DBManager.get(context).delete(DplusCacheApi.this.g, "userinfo");
                    DplusCacheApi.this.g.clear();
                }
                if (DplusCacheApi.this.i.size() > 0) {
                    DBManager.get(context).delete(DplusCacheApi.this.i, "stats");
                    DplusCacheApi.this.i.clear();
                }
            }
        });
    }

    public void deleteFileAsnc(Context context) {
        if (this.e.size() > 0) {
            DBManager.get(context).delete(this.e, "s_e");
            this.e.clear();
        }
        if (this.f.size() > 0) {
            DBManager.get(context).delete(this.f, "auth");
            this.f.clear();
        }
        if (this.h.size() > 0) {
            DBManager.get(context).delete(this.h, "dau");
            this.h.clear();
        }
        if (this.g.size() > 0) {
            DBManager.get(context).delete(this.g, "userinfo");
            this.g.clear();
        }
        if (this.i.size() > 0) {
            DBManager.get(context).delete(this.i, "stats");
            this.i.clear();
        }
    }

    public void deleteAllAsnc(Context context) {
        DBManager.get(ContextUtil.getContext()).deleteTable("stats");
    }

    public void deleteAll(Context context) {
        this.c.post(new Runnable() { // from class: com.umeng.socialize.net.dplus.cache.DplusCacheApi.4
            @Override // java.lang.Runnable
            public void run() {
                DBManager.get(ContextUtil.getContext()).deleteTable("stats");
            }
        });
    }

    public static double checkFile() {
        File dataFile = ContextUtil.getDataFile(DBConfig.DB_NAME);
        if (dataFile == null || !dataFile.exists()) {
            return 0.0d;
        }
        return dataFile.length();
    }
}