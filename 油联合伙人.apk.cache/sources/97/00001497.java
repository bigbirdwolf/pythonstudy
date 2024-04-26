package com.umeng.socialize.net.dplus;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.dplus.cache.DplusCacheApi;
import com.umeng.socialize.net.dplus.cache.DplusCacheListener;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.UmengText;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UMWorkDispatch {
    public static String URL = "umpx_share";

    public static void sendEvent(final Context context, final int i, Object obj) {
        UMSLEnvelopeBuild.mContext = context;
        if (i == 24581) {
            DplusCacheApi.getInstance().saveFile(context, (JSONObject) obj, i, new DplusCacheListener() { // from class: com.umeng.socialize.net.dplus.UMWorkDispatch.1
                @Override // com.umeng.socialize.net.dplus.cache.DplusCacheListener
                public void onResult(JSONObject jSONObject) {
                }
            });
        } else {
            DplusCacheApi.getInstance().saveFile(context, (JSONObject) obj, i, new DplusCacheListener() { // from class: com.umeng.socialize.net.dplus.UMWorkDispatch.2
                @Override // com.umeng.socialize.net.dplus.cache.DplusCacheListener
                public void onResult(JSONObject jSONObject) {
                    UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                    JSONObject constructHeader = UMWorkDispatch.constructHeader(context, uMSLEnvelopeBuild.buildSLBaseHeader(context));
                    JSONObject readFileAsnc = DplusCacheApi.getInstance().readFileAsnc(context, i);
                    JSONObject buildSLEnvelope = (readFileAsnc == null || TextUtils.isEmpty(readFileAsnc.toString())) ? null : uMSLEnvelopeBuild.buildSLEnvelope(context, constructHeader, readFileAsnc, UMWorkDispatch.URL);
                    if (buildSLEnvelope != null) {
                        if (buildSLEnvelope.has("exception")) {
                            if (buildSLEnvelope.optInt("exception") != 101) {
                                DplusCacheApi.getInstance().deleteFileAsnc(context);
                                return;
                            }
                            return;
                        }
                        DplusCacheApi.getInstance().deleteFileAsnc(context);
                        return;
                    }
                    SLog.E(UmengText.NET.BODYNULL);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject constructHeader(Context context, JSONObject jSONObject) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("header");
            if (optJSONObject != null) {
                optJSONObject.put("s_sdk_v", "6.9.4");
                optJSONObject.put(CommonNetImpl.PCV, SocializeConstants.PROTOCOL_VERSON);
                optJSONObject.put("imei", DeviceConfig.getDeviceId(context));
            }
            jSONObject.put("header", optJSONObject);
        } catch (JSONException e) {
            SLog.error(e);
        }
        return jSONObject;
    }
}