package com.umeng.socialize;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.net.ActionBarRequest;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.analytics.SocialAnalytics;
import com.umeng.socialize.net.dplus.DplusApi;
import com.umeng.socialize.uploadlog.UMLog;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.SocializeSpUtils;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import com.umeng.socialize.utils.UrlUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class UMShareAPI {
    private static UMShareAPI a;
    private com.umeng.socialize.a.a b;
    private UMShareConfig c = new UMShareConfig();

    private UMShareAPI(Context context) {
        ContextUtil.setContext(context.getApplicationContext());
        this.b = new com.umeng.socialize.a.a(context.getApplicationContext());
        if (a(context) == null || !a(context).equals(ContextUtil.getPackageName())) {
            return;
        }
        new a(context.getApplicationContext()).execute();
    }

    private String a(Context context) {
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || activityManager.getRunningAppProcesses() == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public static UMShareAPI get(Context context) {
        if (a == null || a.b == null) {
            a = new UMShareAPI(context);
            SLog.welcome();
        }
        a.b.a(context);
        return a;
    }

    public static void init(Context context, String str) {
        SocializeConstants.APPKEY = str;
        get(context);
    }

    @Deprecated
    public void doOauthVerify(final Activity activity, final SHARE_MEDIA share_media, final UMAuthListener uMAuthListener) {
        UMLog.putAuth();
        if (!UMConfigure.getInitStatus()) {
            SLog.selfLog(UmengText.CHECK.NOINT);
            return;
        }
        a.b.a(activity);
        if (!SLog.isDebug() || a(activity, share_media)) {
            if (activity != null) {
                new QueuedWork.DialogThread<Void>(activity) { // from class: com.umeng.socialize.UMShareAPI.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.umeng.socialize.common.QueuedWork.UMAsyncTask
                    /* renamed from: a */
                    public Void doInBackground() {
                        if (UMShareAPI.this.b == null) {
                            UMShareAPI.this.b = new com.umeng.socialize.a.a(activity);
                        }
                        UMShareAPI.this.b.c(activity, share_media, uMAuthListener);
                        return null;
                    }
                }.execute();
            } else {
                SLog.E(UmengText.CHECK.ACTIVITYNULL);
            }
        }
    }

    public void deleteOauth(final Activity activity, final SHARE_MEDIA share_media, final UMAuthListener uMAuthListener) {
        if (activity != null) {
            a.b.a(activity);
            new QueuedWork.DialogThread<Void>(activity) { // from class: com.umeng.socialize.UMShareAPI.2
                @Override // com.umeng.socialize.common.QueuedWork.UMAsyncTask
                protected Object doInBackground() {
                    if (UMShareAPI.this.b != null) {
                        UMShareAPI.this.b.a(activity, share_media, uMAuthListener);
                        return null;
                    }
                    return null;
                }
            }.execute();
            return;
        }
        SLog.E(UmengText.CHECK.ACTIVITYNULL);
    }

    public void getPlatformInfo(final Activity activity, final SHARE_MEDIA share_media, final UMAuthListener uMAuthListener) {
        if (activity != null) {
            if (!UMConfigure.getInitStatus()) {
                SLog.selfLog(UmengText.CHECK.NOINT);
                return;
            }
            UMLog.putAuth();
            if (SLog.isDebug()) {
                if (!a(activity, share_media)) {
                    return;
                }
                UrlUtil.getInfoPrint(share_media);
            }
            a.b.a(activity);
            new QueuedWork.DialogThread<Void>(activity) { // from class: com.umeng.socialize.UMShareAPI.3
                @Override // com.umeng.socialize.common.QueuedWork.UMAsyncTask
                protected Object doInBackground() {
                    if (UMShareAPI.this.b != null) {
                        UMShareAPI.this.b.b(activity, share_media, uMAuthListener);
                        return null;
                    }
                    return null;
                }
            }.execute();
            return;
        }
        SLog.E(UmengText.CHECK.ACTIVITYNULL);
    }

    public boolean isInstall(Activity activity, SHARE_MEDIA share_media) {
        if (this.b != null) {
            return this.b.a(activity, share_media);
        }
        this.b = new com.umeng.socialize.a.a(activity);
        return this.b.a(activity, share_media);
    }

    public boolean isAuthorize(Activity activity, SHARE_MEDIA share_media) {
        if (this.b != null) {
            return this.b.d(activity, share_media);
        }
        this.b = new com.umeng.socialize.a.a(activity);
        return this.b.d(activity, share_media);
    }

    public boolean isSupport(Activity activity, SHARE_MEDIA share_media) {
        if (this.b != null) {
            return this.b.b(activity, share_media);
        }
        this.b = new com.umeng.socialize.a.a(activity);
        return this.b.b(activity, share_media);
    }

    public String getversion(Activity activity, SHARE_MEDIA share_media) {
        if (this.b != null) {
            return this.b.c(activity, share_media);
        }
        this.b = new com.umeng.socialize.a.a(activity);
        return this.b.c(activity, share_media);
    }

    public void doShare(Activity activity, final ShareAction shareAction, final UMShareListener uMShareListener) {
        UMLog.putShare();
        if (!UMConfigure.getInitStatus()) {
            SLog.selfLog(UmengText.CHECK.NOINT);
            return;
        }
        final WeakReference weakReference = new WeakReference(activity);
        if (SLog.isDebug()) {
            if (!a(activity, shareAction.getPlatform())) {
                return;
            }
            UrlUtil.sharePrint(shareAction.getPlatform());
        }
        if (weakReference.get() != null && !((Activity) weakReference.get()).isFinishing()) {
            a.b.a(activity);
            new QueuedWork.DialogThread<Void>((Context) weakReference.get()) { // from class: com.umeng.socialize.UMShareAPI.4
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.umeng.socialize.common.QueuedWork.UMAsyncTask
                /* renamed from: a */
                public Void doInBackground() {
                    if (weakReference.get() == null || ((Activity) weakReference.get()).isFinishing()) {
                        return null;
                    }
                    if (UMShareAPI.this.b != null) {
                        UMShareAPI.this.b.a((Activity) weakReference.get(), shareAction, uMShareListener);
                    } else {
                        UMShareAPI.this.b = new com.umeng.socialize.a.a((Context) weakReference.get());
                        UMShareAPI.this.b.a((Activity) weakReference.get(), shareAction, uMShareListener);
                    }
                    return null;
                }
            }.execute();
            return;
        }
        SLog.E(UmengText.CHECK.ACTIVITYNULL);
    }

    private boolean a(Activity activity, SHARE_MEDIA share_media) {
        boolean z = false;
        for (Method method : activity.getClass().getDeclaredMethods()) {
            if (method.getName().equals("onActivityResult")) {
                z = true;
            }
        }
        if (!z) {
            SLog.mutlE(UmengText.CHECK.ALL_NO_ONACTIVITY, UrlUtil.ALL_NO_ONACTIVITY);
        }
        if (share_media == SHARE_MEDIA.QQ) {
            SLog.E(UmengTool.checkQQByself(activity));
            return true;
        } else if (share_media == SHARE_MEDIA.WEIXIN) {
            SLog.E(UmengTool.checkWxBySelf(activity));
            return true;
        } else if (share_media == SHARE_MEDIA.SINA) {
            SLog.E(UmengTool.checkSinaBySelf(activity));
            return true;
        } else if (share_media == SHARE_MEDIA.FACEBOOK) {
            SLog.E(UmengTool.checkFBByself(activity));
            return true;
        } else {
            if (share_media == SHARE_MEDIA.VKONTAKTE) {
                SLog.E(UmengTool.checkVKByself(activity));
            }
            if (share_media == SHARE_MEDIA.LINKEDIN) {
                SLog.E(UmengTool.checkLinkin(activity));
            }
            if (share_media == SHARE_MEDIA.KAKAO) {
                SLog.E(UmengTool.checkKakao(activity));
            }
            return true;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.b != null) {
            this.b.a(i, i2, intent);
        } else {
            SLog.E(UmengText.CHECK.ROUTERNULL);
        }
        SLog.I(UmengText.CHECK.getActivityResult(i, i2));
    }

    public UMSSOHandler getHandler(SHARE_MEDIA share_media) {
        if (this.b != null) {
            return this.b.a(share_media);
        }
        return null;
    }

    /* loaded from: classes.dex */
    private static class a extends QueuedWork.UMAsyncTask<Void> {
        private Context a;
        private boolean b;
        private boolean c;

        public a(Context context) {
            this.b = false;
            this.c = false;
            this.a = context;
            this.b = SocializeUtils.isToday(SocializeSpUtils.getTime(context));
            this.c = SocializeUtils.isHasDplusCache();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.umeng.socialize.common.QueuedWork.UMAsyncTask
        /* renamed from: a */
        public Void doInBackground() {
            boolean c = c();
            SLog.E(UmengText.CHECK.SDKVERSION + "6.9.4");
            if (!this.b) {
                RestAPI.queryShareId(new ActionBarRequest(this.a, c));
            }
            if (!this.b) {
                SocializeSpUtils.putTime(this.a);
                DplusApi.uploadDAU(ContextUtil.getContext());
                SocialAnalytics.dauStats(this.a, true);
                return null;
            } else if (this.c) {
                DplusApi.uploadDAU(ContextUtil.getContext());
                SocialAnalytics.dauStats(this.a, true);
                return null;
            } else {
                return null;
            }
        }

        private boolean c() {
            return this.a.getSharedPreferences(SocializeConstants.SOCIAL_PREFERENCE_NAME, 0).getBoolean("newinstall", false);
        }

        public void b() {
            SharedPreferences.Editor edit = this.a.getSharedPreferences(SocializeConstants.SOCIAL_PREFERENCE_NAME, 0).edit();
            edit.putBoolean("newinstall", true);
            edit.commit();
        }
    }

    public void release() {
        this.b.a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.b.a(bundle);
    }

    public void fetchAuthResultWithBundle(Activity activity, Bundle bundle, UMAuthListener uMAuthListener) {
        this.b.a(activity, bundle, uMAuthListener);
    }

    public void setShareConfig(UMShareConfig uMShareConfig) {
        this.b.a(uMShareConfig);
    }
}