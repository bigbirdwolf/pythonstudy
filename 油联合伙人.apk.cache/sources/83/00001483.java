package com.umeng.socialize.net.analytics;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import com.umeng.socialize.net.dplus.DplusApi;
import com.umeng.socialize.uploadlog.UMLog;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.SocializeSpUtils;
import com.umeng.socialize.utils.UmengText;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public final class SocialAnalytics {
    private static SocializeClient a = new SocializeClient();
    private static ExecutorService b = Executors.newCachedThreadPool();

    public static void log(final Context context, final String str, final String str2, final UMediaObject uMediaObject) {
        a(new Runnable() { // from class: com.umeng.socialize.net.analytics.SocialAnalytics.1
            @Override // java.lang.Runnable
            public void run() {
                AnalyticsReqeust analyticsReqeust = new AnalyticsReqeust(context, str, str2);
                analyticsReqeust.setMedia(uMediaObject);
                analyticsReqeust.setReqType(1);
                AnalyticsResponse analyticsResponse = (AnalyticsResponse) SocialAnalytics.a.execute(analyticsReqeust);
                if (analyticsResponse != null && analyticsResponse.isOk()) {
                    SLog.debug(UmengText.NET.SHARESELFOK);
                } else {
                    SLog.debug(UmengText.NET.SHARESELFFAIL);
                }
            }
        });
    }

    public static void authstart(Context context, SHARE_MEDIA share_media, String str, boolean z, String str2) {
        DplusApi.uploadAuthStart(context, z, share_media, str2);
    }

    public static void authendt(Context context, SHARE_MEDIA share_media, String str, boolean z, String str2, String str3, Map<String, String> map) {
        DplusApi.uploadAuthend(context, share_media, str3, str, str2);
        if (map != null) {
            DplusApi.uploadAuth(context, map, z, share_media, str3);
        }
    }

    public static void shareend(Context context, SHARE_MEDIA share_media, String str, String str2, String str3) {
        DplusApi.uploadStatsShareEnd(context, share_media, str3, str, str2);
    }

    public static void getInfostart(Context context, SHARE_MEDIA share_media, String str) {
        DplusApi.uploadInfoStart(context, share_media, str);
    }

    public static void getInfoendt(Context context, SHARE_MEDIA share_media, String str, String str2, String str3, Map<String, String> map) {
        DplusApi.uploadInfoend(context, share_media, str3, str, str2);
        if (map != null) {
            DplusApi.uploadUserInfo(context, map, share_media, str3);
        }
    }

    public static void dauStats(Context context, boolean z) {
        Bundle shareAndAuth = UMLog.getShareAndAuth();
        int i = (shareAndAuth.getBoolean("isjump") ? CommonNetImpl.FLAG_SHARE_JUMP : 0) | (shareAndAuth.getBoolean("share") ? CommonNetImpl.FLAG_SHARE : 0) | (shareAndAuth.getBoolean("auth") ? CommonNetImpl.FLAG_AUTH : 0) | (UMLog.isOpenShareEdit() ? 16777216 : 0);
        String shareBoardConfig = SocializeSpUtils.getShareBoardConfig(context);
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(shareBoardConfig)) {
            String[] split = shareBoardConfig.split(i.b);
            if (split.length == 2) {
                String str = split[0];
                String str2 = split[1];
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                    return;
                }
                hashMap.put("position", split[1]);
                hashMap.put(CommonNetImpl.MENUBG, split[0]);
                DplusApi.uploadStatsDAU(context, hashMap, i);
                return;
            }
            return;
        }
        DplusApi.uploadStatsDAU(context, null, i);
    }

    private static void a(Runnable runnable) {
        if (b == null || runnable == null) {
            return;
        }
        b.execute(runnable);
    }
}