package com.umeng.socialize.net.dplus;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.dplus.db.DBManager;
import com.umeng.socialize.utils.SLog;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CommonNetImpl implements UMLogDataProtocol {
    public static final String AID = "aid";
    public static final String AM = "am";
    public static final String AS = "as";
    public static final String AT = "at";
    public static final String AUTH = "auth";
    public static final String A_B = "a_b";
    public static final String CANCEL = "cancel";
    public static final String CONTENT = "content";
    public static final String CT = "ct";
    public static final String DAU = "dau";
    public static final String DURL = "durl";
    public static final String E_M = "e_m";
    public static final String FAIL = "fail";
    public static final int FLAG_AUTH = 268435456;
    public static final int FLAG_SHARE = 536870912;
    public static final int FLAG_SHARE_EDIT = 16777216;
    public static final int FLAG_SHARE_JUMP = 33554432;
    public static final String HEADER = "header";
    public static final String IMEI = "imei";
    public static final int MAX_FILE_SIZE_IN_KB = 65536;
    public static final int MAX_SEND_SIZE_IN_KB = 524288;
    public static final int MAX_SIZE_IN_KB = 5242880;
    public static final String MENUBG = "menubg";
    public static final String M_P = "m_p";
    public static final String M_U = "m_u";
    public static final String NAME = "name";
    public static final String PCV = "s_pcv";
    public static final String PF = "pf";
    public static final String PIC = "pic";
    public static final String PICURL = "picurl";
    public static final String POSITION = "position";
    public static final String REGION = "regn";
    public static final String RESULT = "result";
    public static final String SDKT = "sdkt";
    public static final String SDKVERSON = "s_sdk_v";
    public static final String SEX = "sex";
    public static final String SHARE = "share";
    public static final String SHARETYPE = "s_t";
    public static final String SM = "sm";
    public static final String STATS = "stats";
    public static final String STATS_TAG = "stats";
    public static final String STYPE = "stype";
    public static final String SUCCESS = "success";
    public static final String S_A_E = "s_a_e";
    public static final String S_A_S = "s_a_s";
    public static final String S_DAU = "s_dau";
    public static final String S_E = "s_e";
    public static final String S_I = "s_i";
    public static final String S_I_E = "s_i_e";
    public static final String S_I_S = "s_i_s";
    public static final String S_S_E = "s_s_e";
    public static final String S_S_S = "s_s_s";
    public static final String TAG = "tag";
    public static final String TITLE = "title";
    public static final String TS = "ts";
    public static final String UID = "uid";
    public static final String UMID = "umid";
    public static final String UN = "un";
    public static final String UNIONID = "unionid";
    public static final String UP = "up";
    public static final String URL = "url";
    public static final String USERINFO = "userinfo";
    public static final String U_C = "u_c";
    private static boolean isSendStats = false;
    private static CommonNetImpl singleton;
    private Context mConetxt;
    private ArrayList<Integer> shareList = new ArrayList<>();
    private ArrayList<Integer> authList = new ArrayList<>();
    private ArrayList<Integer> infoList = new ArrayList<>();
    private ArrayList<Integer> dauList = new ArrayList<>();
    private ArrayList<Integer> statsList = new ArrayList<>();

    private CommonNetImpl(Context context) {
        this.mConetxt = context;
    }

    public static CommonNetImpl get(Context context) {
        if (singleton == null) {
            singleton = new CommonNetImpl(context);
        }
        return singleton;
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i) {
        JSONObject object;
        if (i != 24584 || (object = getObject()) == null) {
            return;
        }
        JSONObject optJSONObject = object.optJSONObject("header");
        JSONObject optJSONObject2 = object.optJSONObject("content");
        if (optJSONObject == null || optJSONObject2 == null) {
            return;
        }
        UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.mConetxt, optJSONObject, optJSONObject2);
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
        if (this.shareList.size() > 0) {
            DBManager.get(this.mConetxt).delete(this.shareList, "s_e");
            this.shareList.clear();
        }
        if (this.authList.size() > 0) {
            DBManager.get(this.mConetxt).delete(this.authList, "auth");
            this.authList.clear();
        }
        if (this.dauList.size() > 0) {
            DBManager.get(this.mConetxt).delete(this.dauList, "dau");
            this.dauList.clear();
        }
        if (this.infoList.size() > 0) {
            DBManager.get(this.mConetxt).delete(this.infoList, "userinfo");
            this.infoList.clear();
        }
        if (this.statsList.size() > 0) {
            isSendStats = false;
            DBManager.get(this.mConetxt).delete(this.statsList, "stats");
            this.statsList.clear();
        }
    }

    private JSONObject getObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("header", constructHeader());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("share", new JSONObject());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            SLog.error(e);
        }
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0153 A[Catch: JSONException -> 0x016d, TryCatch #0 {JSONException -> 0x016d, blocks: (B:7:0x0018, B:9:0x003e, B:11:0x0052, B:13:0x0065, B:15:0x0079, B:17:0x008c, B:19:0x00a0, B:21:0x00b3, B:23:0x00c7, B:25:0x00d1, B:26:0x00d6, B:28:0x00dc, B:30:0x00e6, B:32:0x00ec, B:34:0x00f6, B:36:0x00fc, B:38:0x0106, B:40:0x0111, B:49:0x0143, B:51:0x0153, B:53:0x0159, B:55:0x015f, B:57:0x0165, B:44:0x011c, B:46:0x0134, B:48:0x013e), top: B:69:0x0018 }] */
    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject setupReportData(long r22) {
        /*
            Method dump skipped, instructions count: 389
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.net.dplus.CommonNetImpl.setupReportData(long):org.json.JSONObject");
    }

    private static JSONObject constructHeader() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("s_sdk_v", "6.9.4");
        jSONObject.put(PCV, SocializeConstants.PROTOCOL_VERSON);
        return jSONObject;
    }

    private void saveFile(JSONObject jSONObject, int i) {
        if (jSONObject == null) {
            return;
        }
        switch (i) {
            case SocializeConstants.DAU_EVENT /* 24577 */:
                DBManager.get(this.mConetxt).insertDau(jSONObject);
                return;
            case SocializeConstants.SHARE_EVENT /* 24578 */:
                DBManager.get(this.mConetxt).insertS_E(jSONObject);
                return;
            case SocializeConstants.AUTH_EVENT /* 24579 */:
                DBManager.get(this.mConetxt).insertAuth(jSONObject);
                return;
            case SocializeConstants.GET_EVENT /* 24580 */:
                DBManager.get(this.mConetxt).insertUserInfo(jSONObject);
                return;
            case SocializeConstants.SAVE_STATS_EVENT /* 24581 */:
            case SocializeConstants.SEND_DAU_STATS_EVENT /* 24583 */:
                DBManager.get(this.mConetxt).insertStats(jSONObject);
                return;
            case SocializeConstants.CHECK_STATS_EVENT /* 24582 */:
            default:
                DBManager.get(this.mConetxt).insertStats(jSONObject);
                return;
        }
    }
}