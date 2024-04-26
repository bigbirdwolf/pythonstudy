package com.umeng.socialize.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.HandlerRequestCode;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.handler.UMMoreHandler;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.net.analytics.SocialAnalytics;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import com.umeng.socialize.net.dplus.DplusApi;
import com.umeng.socialize.net.dplus.db.DBManager;
import com.umeng.socialize.net.utils.SocializeNetUtils;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import com.umeng.socialize.utils.UrlUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: SocialRouter.java */
/* loaded from: classes.dex */
public final class a {
    private static final String b = "umeng_share_platform";
    private static final String c = "share_action";
    private SHARE_MEDIA a;
    private String d = "6.9.4";
    private final Map<SHARE_MEDIA, UMSSOHandler> e = new HashMap();
    private final List<Pair<SHARE_MEDIA, String>> f = new ArrayList();
    private C0032a g;
    private Context h;
    private SparseArray<UMAuthListener> i;
    private SparseArray<UMShareListener> j;
    private SparseArray<UMAuthListener> k;

    public void a(Context context) {
        this.h = context.getApplicationContext();
    }

    public a(Context context) {
        List<Pair<SHARE_MEDIA, String>> list = this.f;
        list.add(new Pair<>(SHARE_MEDIA.LAIWANG, "com.umeng.socialize.handler.UMLWHandler"));
        list.add(new Pair<>(SHARE_MEDIA.LAIWANG_DYNAMIC, "com.umeng.socialize.handler.UMLWHandler"));
        list.add(new Pair<>(SHARE_MEDIA.SINA, "com.umeng.socialize.handler.SinaSimplyHandler"));
        list.add(new Pair<>(SHARE_MEDIA.PINTEREST, "com.umeng.socialize.handler.UMPinterestHandler"));
        list.add(new Pair<>(SHARE_MEDIA.QZONE, "com.umeng.qq.handler.UmengQZoneHandler"));
        list.add(new Pair<>(SHARE_MEDIA.QQ, "com.umeng.qq.handler.UmengQQHandler"));
        list.add(new Pair<>(SHARE_MEDIA.RENREN, "com.umeng.socialize.handler.RenrenSsoHandler"));
        list.add(new Pair<>(SHARE_MEDIA.TENCENT, "com.umeng.socialize.handler.TencentWBSsoHandler"));
        list.add(new Pair<>(SHARE_MEDIA.WEIXIN, "com.umeng.weixin.handler.UmengWXHandler"));
        list.add(new Pair<>(SHARE_MEDIA.WEIXIN_CIRCLE, "com.umeng.weixin.handler.UmengWXHandler"));
        list.add(new Pair<>(SHARE_MEDIA.WEIXIN_FAVORITE, "com.umeng.weixin.handler.UmengWXHandler"));
        list.add(new Pair<>(SHARE_MEDIA.YIXIN, "com.umeng.socialize.handler.UMYXHandler"));
        list.add(new Pair<>(SHARE_MEDIA.YIXIN_CIRCLE, "com.umeng.socialize.handler.UMYXHandler"));
        list.add(new Pair<>(SHARE_MEDIA.EMAIL, "com.umeng.socialize.handler.EmailHandler"));
        list.add(new Pair<>(SHARE_MEDIA.EVERNOTE, "com.umeng.socialize.handler.UMEvernoteHandler"));
        list.add(new Pair<>(SHARE_MEDIA.FACEBOOK, "com.umeng.socialize.handler.UMFacebookHandler"));
        list.add(new Pair<>(SHARE_MEDIA.FACEBOOK_MESSAGER, "com.umeng.socialize.handler.UMFacebookHandler"));
        list.add(new Pair<>(SHARE_MEDIA.FLICKR, "com.umeng.socialize.handler.UMFlickrHandler"));
        list.add(new Pair<>(SHARE_MEDIA.FOURSQUARE, "com.umeng.socialize.handler.UMFourSquareHandler"));
        list.add(new Pair<>(SHARE_MEDIA.GOOGLEPLUS, "com.umeng.socialize.handler.UMGooglePlusHandler"));
        list.add(new Pair<>(SHARE_MEDIA.INSTAGRAM, "com.umeng.socialize.handler.UMInstagramHandler"));
        list.add(new Pair<>(SHARE_MEDIA.KAKAO, "com.umeng.socialize.handler.UMKakaoHandler"));
        list.add(new Pair<>(SHARE_MEDIA.LINE, "com.umeng.socialize.handler.UMLineHandler"));
        list.add(new Pair<>(SHARE_MEDIA.LINKEDIN, "com.umeng.socialize.handler.UMLinkedInHandler"));
        list.add(new Pair<>(SHARE_MEDIA.POCKET, "com.umeng.socialize.handler.UMPocketHandler"));
        list.add(new Pair<>(SHARE_MEDIA.WHATSAPP, "com.umeng.socialize.handler.UMWhatsAppHandler"));
        list.add(new Pair<>(SHARE_MEDIA.YNOTE, "com.umeng.socialize.handler.UMYNoteHandler"));
        list.add(new Pair<>(SHARE_MEDIA.SMS, "com.umeng.socialize.handler.SmsHandler"));
        list.add(new Pair<>(SHARE_MEDIA.DOUBAN, "com.umeng.socialize.handler.DoubanHandler"));
        list.add(new Pair<>(SHARE_MEDIA.TUMBLR, "com.umeng.socialize.handler.UMTumblrHandler"));
        list.add(new Pair<>(SHARE_MEDIA.TWITTER, "com.umeng.socialize.handler.TwitterHandler"));
        list.add(new Pair<>(SHARE_MEDIA.ALIPAY, "com.umeng.socialize.handler.AlipayHandler"));
        list.add(new Pair<>(SHARE_MEDIA.MORE, "com.umeng.socialize.handler.UMMoreHandler"));
        list.add(new Pair<>(SHARE_MEDIA.DINGTALK, "com.umeng.socialize.handler.UMDingSSoHandler"));
        list.add(new Pair<>(SHARE_MEDIA.VKONTAKTE, "com.umeng.socialize.handler.UMVKHandler"));
        list.add(new Pair<>(SHARE_MEDIA.DROPBOX, "com.umeng.socialize.handler.UMDropBoxHandler"));
        this.g = new C0032a(this.e);
        this.h = null;
        this.i = new SparseArray<>();
        this.j = new SparseArray<>();
        this.k = new SparseArray<>();
        this.h = context;
        b();
    }

    private void b(Context context) {
        String appkey = SocializeUtils.getAppkey(context);
        if (TextUtils.isEmpty(appkey)) {
            throw new SocializeException(UmengText.errorWithUrl(UmengText.CHECK.APPKEY_NOT_FOUND, UrlUtil.ALL_NO_APPKEY));
        }
        if (SocializeNetUtils.isConSpeCharacters(appkey)) {
            throw new SocializeException(UmengText.errorWithUrl(UmengText.CHECK.APPKEY_NOT_FOUND, UrlUtil.ALL_ERROR_APPKEY));
        }
        if (SocializeNetUtils.isSelfAppkey(appkey)) {
            throw new SocializeException(UmengText.errorWithUrl(UmengText.CHECK.APPKEY_NOT_FOUND, UrlUtil.ALL_ERROR_APPKEY));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b() {
        UMSSOHandler uMSSOHandler;
        for (Pair<SHARE_MEDIA, String> pair : this.f) {
            if (pair.first == SHARE_MEDIA.WEIXIN_CIRCLE || pair.first == SHARE_MEDIA.WEIXIN_FAVORITE) {
                uMSSOHandler = this.e.get(SHARE_MEDIA.WEIXIN);
            } else if (pair.first == SHARE_MEDIA.FACEBOOK_MESSAGER) {
                uMSSOHandler = this.e.get(SHARE_MEDIA.FACEBOOK);
            } else if (pair.first == SHARE_MEDIA.YIXIN_CIRCLE) {
                uMSSOHandler = this.e.get(SHARE_MEDIA.YIXIN);
            } else if (pair.first == SHARE_MEDIA.LAIWANG_DYNAMIC) {
                uMSSOHandler = this.e.get(SHARE_MEDIA.LAIWANG);
            } else if (pair.first == SHARE_MEDIA.TENCENT) {
                uMSSOHandler = a((String) pair.second);
            } else if (pair.first == SHARE_MEDIA.MORE) {
                uMSSOHandler = new UMMoreHandler();
            } else if (pair.first == SHARE_MEDIA.SINA) {
                if (Config.isUmengSina.booleanValue()) {
                    uMSSOHandler = a((String) pair.second);
                } else {
                    uMSSOHandler = a("com.umeng.socialize.handler.SinaSsoHandler");
                }
            } else if (pair.first == SHARE_MEDIA.WEIXIN) {
                if (Config.isUmengWx.booleanValue()) {
                    uMSSOHandler = a((String) pair.second);
                } else {
                    uMSSOHandler = a("com.umeng.socialize.handler.UMWXHandler");
                }
            } else if (pair.first == SHARE_MEDIA.QQ) {
                if (Config.isUmengQQ.booleanValue()) {
                    uMSSOHandler = a((String) pair.second);
                } else {
                    uMSSOHandler = a("com.umeng.socialize.handler.UMQQSsoHandler");
                }
            } else if (pair.first == SHARE_MEDIA.QZONE) {
                if (Config.isUmengQQ.booleanValue()) {
                    uMSSOHandler = a((String) pair.second);
                } else {
                    uMSSOHandler = a("com.umeng.socialize.handler.QZoneSsoHandler");
                }
            } else {
                uMSSOHandler = a((String) pair.second);
            }
            this.e.put(pair.first, uMSSOHandler);
        }
    }

    private UMSSOHandler a(String str) {
        UMSSOHandler uMSSOHandler;
        try {
            uMSSOHandler = (UMSSOHandler) Class.forName(str).newInstance();
        } catch (Exception unused) {
            uMSSOHandler = null;
        }
        if (uMSSOHandler == null) {
            if (str.contains("SinaSimplyHandler")) {
                Config.isUmengSina = false;
                return a("com.umeng.socialize.handler.SinaSsoHandler");
            } else if (str.contains("UmengQQHandler")) {
                Config.isUmengQQ = false;
                return a("com.umeng.socialize.handler.UMQQSsoHandler");
            } else if (str.contains("UmengQZoneHandler")) {
                Config.isUmengQQ = false;
                return a("com.umeng.socialize.handler.QZoneSsoHandler");
            } else if (str.contains("UmengWXHandler")) {
                Config.isUmengWx = false;
                return a("com.umeng.socialize.handler.UMWXHandler");
            }
        }
        return uMSSOHandler;
    }

    public UMSSOHandler a(SHARE_MEDIA share_media) {
        UMSSOHandler uMSSOHandler = this.e.get(share_media);
        if (uMSSOHandler != null) {
            uMSSOHandler.onCreate(this.h, PlatformConfig.getPlatform(share_media));
        }
        return uMSSOHandler;
    }

    public void a(int i, int i2, Intent intent) {
        UMSSOHandler a = a(i);
        if (a != null) {
            a.onActivityResult(i, i2, intent);
        }
    }

    @Deprecated
    public void a(Activity activity, int i, UMAuthListener uMAuthListener) {
        UMSSOHandler a = a(i);
        if (a != null) {
            if (i == 10103 || i == 11101) {
                a.onCreate(activity, PlatformConfig.getPlatform(b(i)));
                a(SHARE_MEDIA.QQ, uMAuthListener, a, String.valueOf(System.currentTimeMillis()));
            }
        }
    }

    private UMSSOHandler a(int i) {
        int i2 = HandlerRequestCode.REQUEST_QQ_SHARE;
        if (i != 10103 && i != 11101) {
            i2 = i;
        }
        if (i == 64207 || i == 64206 || i == 64208) {
            i2 = HandlerRequestCode.FACEBOOK_REQUEST_AUTH_CODE;
        }
        if (i == 32973 || i == 765) {
            i2 = HandlerRequestCode.SINA_REQUEST_CODE;
        }
        if (i == 5650) {
            i2 = HandlerRequestCode.SINA_REQUEST_CODE;
        }
        for (UMSSOHandler uMSSOHandler : this.e.values()) {
            if (uMSSOHandler != null && i2 == uMSSOHandler.getRequestCode()) {
                return uMSSOHandler;
            }
        }
        return null;
    }

    private SHARE_MEDIA b(int i) {
        if (i == 10103 || i == 11101) {
            return SHARE_MEDIA.QQ;
        }
        if (i == 32973 || i == 765) {
            return SHARE_MEDIA.SINA;
        }
        return SHARE_MEDIA.QQ;
    }

    public void a(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        if (this.g.a(activity, share_media)) {
            if (uMAuthListener == null) {
                uMAuthListener = new UMAuthListener() { // from class: com.umeng.socialize.a.a.1
                    @Override // com.umeng.socialize.UMAuthListener
                    public void onCancel(SHARE_MEDIA share_media2, int i) {
                    }

                    @Override // com.umeng.socialize.UMAuthListener
                    public void onComplete(SHARE_MEDIA share_media2, int i, Map<String, String> map) {
                    }

                    @Override // com.umeng.socialize.UMAuthListener
                    public void onError(SHARE_MEDIA share_media2, int i, Throwable th) {
                    }

                    @Override // com.umeng.socialize.UMAuthListener
                    public void onStart(SHARE_MEDIA share_media2) {
                    }
                };
            }
            this.e.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
            this.e.get(share_media).deleteAuth(uMAuthListener);
        }
    }

    public void b(Activity activity, final SHARE_MEDIA share_media, final UMAuthListener uMAuthListener) {
        if (this.g.a(activity, share_media)) {
            UMSSOHandler uMSSOHandler = this.e.get(share_media);
            uMSSOHandler.onCreate(activity, PlatformConfig.getPlatform(share_media));
            final String valueOf = String.valueOf(System.currentTimeMillis());
            if (ContextUtil.getContext() != null) {
                SocialAnalytics.getInfostart(ContextUtil.getContext(), share_media, valueOf);
            }
            final int ordinal = share_media.ordinal();
            b(ordinal, uMAuthListener);
            UMAuthListener uMAuthListener2 = new UMAuthListener() { // from class: com.umeng.socialize.a.a.2
                @Override // com.umeng.socialize.UMAuthListener
                public void onStart(SHARE_MEDIA share_media2) {
                    UMAuthListener d = a.this.d(ordinal);
                    if (d != null) {
                        d.onStart(share_media2);
                    }
                }

                @Override // com.umeng.socialize.UMAuthListener
                public void onComplete(SHARE_MEDIA share_media2, int i, Map<String, String> map) {
                    UMAuthListener d = a.this.d(ordinal);
                    if (d != null) {
                        d.onComplete(share_media2, i, map);
                    }
                    if (ContextUtil.getContext() != null) {
                        SocialAnalytics.getInfoendt(ContextUtil.getContext(), share_media2, CommonNetImpl.SUCCESS, "", valueOf, map);
                    }
                }

                @Override // com.umeng.socialize.UMAuthListener
                public void onError(SHARE_MEDIA share_media2, int i, Throwable th) {
                    UMAuthListener d = a.this.d(ordinal);
                    if (d != null) {
                        d.onError(share_media2, i, th);
                    }
                    if (th != null) {
                        SLog.E(th.getMessage());
                        SLog.E(UmengText.SOLVE + UrlUtil.ALL_AUTHFAIL);
                        SLog.runtimePrint(th.getMessage());
                    } else {
                        SLog.E(UmengText.SOLVE + UrlUtil.ALL_AUTHFAIL);
                    }
                    if (ContextUtil.getContext() == null || th == null) {
                        return;
                    }
                    SocialAnalytics.getInfoendt(ContextUtil.getContext(), share_media2, CommonNetImpl.FAIL, th.getMessage(), valueOf, null);
                }

                @Override // com.umeng.socialize.UMAuthListener
                public void onCancel(SHARE_MEDIA share_media2, int i) {
                    UMAuthListener d = a.this.d(ordinal);
                    if (d != null) {
                        d.onCancel(share_media2, i);
                    }
                    if (ContextUtil.getContext() != null) {
                        SocialAnalytics.getInfoendt(ContextUtil.getContext(), share_media2, CommonNetImpl.CANCEL, "", valueOf, null);
                    }
                }
            };
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.a.a.3
                @Override // java.lang.Runnable
                public void run() {
                    uMAuthListener.onStart(share_media);
                }
            });
            uMSSOHandler.getPlatformInfo(uMAuthListener2);
        }
    }

    public boolean a(Activity activity, SHARE_MEDIA share_media) {
        this.e.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
        return this.e.get(share_media).isInstall();
    }

    public boolean b(Activity activity, SHARE_MEDIA share_media) {
        if (this.g.a(activity, share_media)) {
            this.e.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
            return this.e.get(share_media).isSupport();
        }
        return false;
    }

    public String c(Activity activity, SHARE_MEDIA share_media) {
        if (this.g.a(activity, share_media)) {
            this.e.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
            return this.e.get(share_media).getSDKVersion();
        }
        return "";
    }

    public boolean d(Activity activity, SHARE_MEDIA share_media) {
        if (this.g.a(activity, share_media)) {
            this.e.get(share_media).onCreate(activity, PlatformConfig.getPlatform(share_media));
            return this.e.get(share_media).isAuthorize();
        }
        return false;
    }

    public void c(Activity activity, final SHARE_MEDIA share_media, final UMAuthListener uMAuthListener) {
        if (this.g.a(activity, share_media)) {
            UMSSOHandler uMSSOHandler = this.e.get(share_media);
            uMSSOHandler.onCreate(activity, PlatformConfig.getPlatform(share_media));
            String valueOf = String.valueOf(System.currentTimeMillis());
            if (ContextUtil.getContext() != null) {
                SocialAnalytics.authstart(ContextUtil.getContext(), share_media, uMSSOHandler.getSDKVersion(), uMSSOHandler.isInstall(), valueOf);
            }
            int ordinal = share_media.ordinal();
            a(ordinal, uMAuthListener);
            UMAuthListener a = a(ordinal, valueOf, uMSSOHandler.isInstall());
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.a.a.4
                @Override // java.lang.Runnable
                public void run() {
                    uMAuthListener.onStart(share_media);
                }
            });
            uMSSOHandler.authorize(a);
            this.a = share_media;
        }
    }

    private UMAuthListener a(final int i, final String str, final boolean z) {
        return new UMAuthListener() { // from class: com.umeng.socialize.a.a.5
            @Override // com.umeng.socialize.UMAuthListener
            public void onStart(SHARE_MEDIA share_media) {
                UMAuthListener c2 = a.this.c(i);
                if (c2 != null) {
                    c2.onStart(share_media);
                }
            }

            @Override // com.umeng.socialize.UMAuthListener
            public void onComplete(SHARE_MEDIA share_media, int i2, Map<String, String> map) {
                UMAuthListener c2 = a.this.c(i);
                if (c2 != null) {
                    c2.onComplete(share_media, i2, map);
                }
                if (ContextUtil.getContext() != null) {
                    SocialAnalytics.authendt(ContextUtil.getContext(), share_media, CommonNetImpl.SUCCESS, z, "", str, a.this.a(share_media, map));
                }
            }

            @Override // com.umeng.socialize.UMAuthListener
            public void onError(SHARE_MEDIA share_media, int i2, Throwable th) {
                UMAuthListener c2 = a.this.c(i);
                if (c2 != null) {
                    c2.onError(share_media, i2, th);
                }
                if (th != null) {
                    SLog.E(th.getMessage());
                    SLog.runtimePrint(th.getMessage());
                } else {
                    SLog.E("null");
                    SLog.runtimePrint("null");
                }
                if (ContextUtil.getContext() == null || th == null) {
                    return;
                }
                SocialAnalytics.authendt(ContextUtil.getContext(), share_media, CommonNetImpl.FAIL, z, th.getMessage(), str, null);
            }

            @Override // com.umeng.socialize.UMAuthListener
            public void onCancel(SHARE_MEDIA share_media, int i2) {
                UMAuthListener c2 = a.this.c(i);
                if (c2 != null) {
                    c2.onCancel(share_media, i2);
                }
                if (ContextUtil.getContext() != null) {
                    SocialAnalytics.authendt(ContextUtil.getContext(), share_media, CommonNetImpl.CANCEL, z, "", str, null);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> a(SHARE_MEDIA share_media, Map<String, String> map) {
        String str = "";
        String str2 = "";
        if (PlatformConfig.getPlatform(share_media) != null) {
            str = PlatformConfig.getPlatform(share_media).getAppid();
            str2 = PlatformConfig.getPlatform(share_media).getAppSecret();
        }
        map.put(CommonNetImpl.AID, str);
        map.put(CommonNetImpl.AS, str2);
        return map;
    }

    private void a(ShareAction shareAction) {
        ShareContent shareContent = shareAction.getShareContent();
        ArrayList arrayList = new ArrayList();
        arrayList.add(UmengText.SHARE.INFO);
        arrayList.add(UmengText.SHARE.SHAREPLAT + shareAction.getPlatform().toString());
        arrayList.add(UmengText.SHARE.SHARESTYLE + shareAction.getShareContent().getShareType());
        arrayList.add(UmengText.SHARE.SHARETEXT + shareContent.mText);
        if (shareContent.mMedia != null) {
            if (shareContent.mMedia instanceof UMImage) {
                UMImage uMImage = (UMImage) shareContent.mMedia;
                if (uMImage.isUrlMedia()) {
                    arrayList.add(UmengText.SHARE.URLIMAGE + uMImage.asUrlImage());
                } else {
                    byte[] asBinImage = uMImage.asBinImage();
                    StringBuilder sb = new StringBuilder();
                    sb.append(UmengText.SHARE.LOCALIMAGE);
                    sb.append(asBinImage == null ? 0 : asBinImage.length);
                    arrayList.add(sb.toString());
                }
                if (uMImage.getThumbImage() != null) {
                    UMImage thumbImage = uMImage.getThumbImage();
                    if (thumbImage.isUrlMedia()) {
                        arrayList.add(UmengText.SHARE.URLTHUMB + thumbImage.asUrlImage());
                    } else {
                        arrayList.add(UmengText.SHARE.LOCALTHUMB + thumbImage.asBinImage().length);
                    }
                }
            }
            if (shareContent.mMedia instanceof UMVideo) {
                UMVideo uMVideo = (UMVideo) shareContent.mMedia;
                arrayList.add(UmengText.SHARE.VIDEOURL + uMVideo.toUrl());
                arrayList.add(UmengText.SHARE.VIDEOTITLE + uMVideo.getTitle());
                arrayList.add(UmengText.SHARE.VIDEODES + uMVideo.getDescription());
                if (uMVideo.getThumbImage() != null) {
                    if (uMVideo.getThumbImage().isUrlMedia()) {
                        arrayList.add(UmengText.SHARE.URLTHUMB + uMVideo.getThumbImage().asUrlImage());
                    } else {
                        arrayList.add(UmengText.SHARE.LOCALTHUMB + uMVideo.getThumbImage().asBinImage().length);
                    }
                }
            }
            if (shareContent.mMedia instanceof UMusic) {
                UMusic uMusic = (UMusic) shareContent.mMedia;
                arrayList.add(UmengText.SHARE.MUSICURL + uMusic.toUrl() + "   " + uMusic.getmTargetUrl());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(UmengText.SHARE.MUSICTITLE);
                sb2.append(uMusic.getTitle());
                arrayList.add(sb2.toString());
                arrayList.add(UmengText.SHARE.MUSICDES + uMusic.getDescription());
                if (uMusic.getThumbImage() != null) {
                    if (uMusic.getThumbImage().isUrlMedia()) {
                        arrayList.add(UmengText.SHARE.URLTHUMB + uMusic.getThumbImage().asUrlImage());
                    } else {
                        arrayList.add(UmengText.SHARE.LOCALTHUMB + uMusic.getThumbImage().asBinImage().length);
                    }
                }
            }
            if (shareContent.mMedia instanceof UMWeb) {
                UMWeb uMWeb = (UMWeb) shareContent.mMedia;
                arrayList.add(UmengText.SHARE.URLURL + uMWeb.toUrl());
                arrayList.add(UmengText.SHARE.URLTITLE + uMWeb.getTitle());
                arrayList.add(UmengText.SHARE.URLDES + uMWeb.getDescription());
                if (uMWeb.getThumbImage() != null) {
                    if (uMWeb.getThumbImage().isUrlMedia()) {
                        arrayList.add(UmengText.SHARE.URLTHUMB + uMWeb.getThumbImage().asUrlImage());
                    } else {
                        arrayList.add(UmengText.SHARE.LOCALTHUMB + uMWeb.getThumbImage().asBinImage().length);
                    }
                }
            }
        }
        if (shareContent.file != null) {
            arrayList.add(UmengText.SHARE.FILENAME + shareContent.file.getName());
        }
        SLog.mutlI((String[]) arrayList.toArray(new String[1]));
    }

    public void a(Activity activity, final ShareAction shareAction, final UMShareListener uMShareListener) {
        b(activity);
        WeakReference weakReference = new WeakReference(activity);
        if (this.g.a(shareAction)) {
            if (SLog.isDebug()) {
                SLog.E(UmengText.SHARE.VERSION + this.d);
                a(shareAction);
            }
            SHARE_MEDIA platform = shareAction.getPlatform();
            UMSSOHandler uMSSOHandler = this.e.get(platform);
            uMSSOHandler.onCreate((Context) weakReference.get(), PlatformConfig.getPlatform(platform));
            if (!platform.toString().equals("TENCENT") && !platform.toString().equals("RENREN") && !platform.toString().equals("DOUBAN")) {
                if (platform.toString().equals("WEIXIN")) {
                    SocialAnalytics.log((Context) weakReference.get(), "wxsession", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else if (platform.toString().equals("WEIXIN_CIRCLE")) {
                    SocialAnalytics.log((Context) weakReference.get(), "wxtimeline", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else if (platform.toString().equals("WEIXIN_FAVORITE")) {
                    SocialAnalytics.log((Context) weakReference.get(), "wxfavorite", shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                } else {
                    SocialAnalytics.log((Context) weakReference.get(), platform.toString().toLowerCase(), shareAction.getShareContent().mText, shareAction.getShareContent().mMedia);
                }
            }
            final String valueOf = String.valueOf(System.currentTimeMillis());
            if (ContextUtil.getContext() != null) {
                DplusApi.uploadShare(ContextUtil.getContext(), shareAction.getShareContent(), uMSSOHandler.isInstall(), platform, valueOf, shareAction.getShareContent().mMedia instanceof UMImage ? ((UMImage) shareAction.getShareContent().mMedia).isHasWaterMark() : false);
            }
            final int ordinal = platform.ordinal();
            a(ordinal, uMShareListener);
            final UMShareListener uMShareListener2 = new UMShareListener() { // from class: com.umeng.socialize.a.a.6
                @Override // com.umeng.socialize.UMShareListener
                public void onStart(SHARE_MEDIA share_media) {
                    UMShareListener e = a.this.e(ordinal);
                    if (e != null) {
                        e.onStart(share_media);
                    }
                }

                @Override // com.umeng.socialize.UMShareListener
                public void onResult(SHARE_MEDIA share_media) {
                    if (ContextUtil.getContext() != null) {
                        SocialAnalytics.shareend(ContextUtil.getContext(), share_media, CommonNetImpl.SUCCESS, "", valueOf);
                    }
                    UMShareListener e = a.this.e(ordinal);
                    if (e != null) {
                        e.onResult(share_media);
                    }
                }

                @Override // com.umeng.socialize.UMShareListener
                public void onError(SHARE_MEDIA share_media, Throwable th) {
                    if (ContextUtil.getContext() != null && th != null) {
                        SocialAnalytics.shareend(ContextUtil.getContext(), share_media, CommonNetImpl.FAIL, th.getMessage(), valueOf);
                    }
                    UMShareListener e = a.this.e(ordinal);
                    if (e != null) {
                        e.onError(share_media, th);
                    }
                    if (th != null) {
                        SLog.E(th.getMessage());
                        SLog.E(UmengText.SOLVE + UrlUtil.ALL_SHAREFAIL);
                        SLog.runtimePrint(th.getMessage());
                        return;
                    }
                    SLog.E("null");
                    SLog.E(UmengText.SOLVE + UrlUtil.ALL_SHAREFAIL);
                    SLog.runtimePrint("null");
                }

                @Override // com.umeng.socialize.UMShareListener
                public void onCancel(SHARE_MEDIA share_media) {
                    if (ContextUtil.getContext() != null) {
                        SocialAnalytics.shareend(ContextUtil.getContext(), share_media, CommonNetImpl.CANCEL, "", valueOf);
                    }
                    UMShareListener e = a.this.e(ordinal);
                    if (e != null) {
                        e.onCancel(share_media);
                    }
                }
            };
            if (!shareAction.getUrlValid()) {
                QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.a.a.7
                    @Override // java.lang.Runnable
                    public void run() {
                        UMShareListener uMShareListener3 = uMShareListener2;
                        SHARE_MEDIA platform2 = shareAction.getPlatform();
                        uMShareListener3.onError(platform2, new Throwable(UmengErrorCode.ShareFailed.getMessage() + UmengText.SHARE.WEB_HTTP));
                    }
                });
                return;
            }
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.a.a.8
                @Override // java.lang.Runnable
                public void run() {
                    if (uMShareListener != null) {
                        uMShareListener.onStart(shareAction.getPlatform());
                    }
                }
            });
            try {
                uMSSOHandler.share(shareAction.getShareContent(), uMShareListener2);
            } catch (Throwable th) {
                SLog.error(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SocialRouter.java */
    /* renamed from: com.umeng.socialize.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0032a {
        private Map<SHARE_MEDIA, UMSSOHandler> a;

        private boolean a(Context context) {
            return context != null;
        }

        public C0032a(Map<SHARE_MEDIA, UMSSOHandler> map) {
            this.a = map;
        }

        public boolean a(Context context, SHARE_MEDIA share_media) {
            if (a(context) && a(share_media)) {
                if (this.a.get(share_media).isSupportAuth()) {
                    return true;
                }
                SLog.E(share_media.toString() + UmengText.AUTH.NOT_SUPPORT_PLATFROM);
                return false;
            }
            return false;
        }

        public boolean a(ShareAction shareAction) {
            SHARE_MEDIA platform = shareAction.getPlatform();
            if (platform == null) {
                return false;
            }
            if ((platform != SHARE_MEDIA.SINA && platform != SHARE_MEDIA.QQ && platform != SHARE_MEDIA.WEIXIN) || PlatformConfig.configs.get(platform).isConfigured()) {
                return a(platform);
            }
            SLog.E(UmengText.CHECK.noKey(platform));
            return false;
        }

        private boolean a(SHARE_MEDIA share_media) {
            PlatformConfig.configs.get(share_media);
            if (this.a.get(share_media) == null) {
                SLog.mutlE(UmengText.CHECK.noJar(share_media), UrlUtil.ALL_NO_JAR);
                return false;
            }
            return true;
        }
    }

    private synchronized void a(int i, UMAuthListener uMAuthListener) {
        this.i.put(i, uMAuthListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized UMAuthListener c(int i) {
        UMAuthListener uMAuthListener;
        this.a = null;
        uMAuthListener = this.i.get(i, null);
        if (uMAuthListener != null) {
            this.i.remove(i);
        }
        return uMAuthListener;
    }

    private synchronized void b(int i, UMAuthListener uMAuthListener) {
        this.k.put(i, uMAuthListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized UMAuthListener d(int i) {
        UMAuthListener uMAuthListener;
        uMAuthListener = this.k.get(i, null);
        if (uMAuthListener != null) {
            this.k.remove(i);
        }
        return uMAuthListener;
    }

    private synchronized void a(int i, UMShareListener uMShareListener) {
        this.j.put(i, uMShareListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized UMShareListener e(int i) {
        UMShareListener uMShareListener;
        uMShareListener = this.j.get(i, null);
        if (uMShareListener != null) {
            this.j.remove(i);
        }
        return uMShareListener;
    }

    private synchronized void c() {
        this.i.clear();
        this.j.clear();
        this.k.clear();
    }

    private void a(SHARE_MEDIA share_media, UMAuthListener uMAuthListener, UMSSOHandler uMSSOHandler, String str) {
        if (uMSSOHandler.isHasAuthListener()) {
            return;
        }
        int ordinal = share_media.ordinal();
        a(ordinal, uMAuthListener);
        uMSSOHandler.setAuthListener(a(ordinal, str, uMSSOHandler.isInstall()));
    }

    public void a() {
        c();
        com.umeng.socialize.b.b.a.b();
        UMSSOHandler uMSSOHandler = this.e.get(SHARE_MEDIA.SINA);
        if (uMSSOHandler != null) {
            uMSSOHandler.release();
        }
        UMSSOHandler uMSSOHandler2 = this.e.get(SHARE_MEDIA.MORE);
        if (uMSSOHandler2 != null) {
            uMSSOHandler2.release();
        }
        UMSSOHandler uMSSOHandler3 = this.e.get(SHARE_MEDIA.DINGTALK);
        if (uMSSOHandler3 != null) {
            uMSSOHandler3.release();
        }
        UMSSOHandler uMSSOHandler4 = this.e.get(SHARE_MEDIA.WEIXIN);
        if (uMSSOHandler4 != null) {
            uMSSOHandler4.release();
        }
        UMSSOHandler uMSSOHandler5 = this.e.get(SHARE_MEDIA.QQ);
        if (uMSSOHandler5 != null) {
            uMSSOHandler5.release();
        }
        this.a = null;
        DBManager.get(ContextUtil.getContext()).closeDatabase();
    }

    public void a(Bundle bundle) {
        int i;
        String str = "";
        if (this.a == null || !(this.a == SHARE_MEDIA.WEIXIN || this.a == SHARE_MEDIA.QQ || this.a == SHARE_MEDIA.SINA)) {
            i = -1;
        } else {
            str = this.a.toString();
            i = 0;
        }
        bundle.putString(b, str);
        bundle.putInt(c, i);
        this.a = null;
    }

    public void a(Activity activity, Bundle bundle, UMAuthListener uMAuthListener) {
        SHARE_MEDIA convertToEmun;
        UMSSOHandler a;
        if (bundle == null || uMAuthListener == null) {
            return;
        }
        String string = bundle.getString(b, null);
        if (bundle.getInt(c, -1) != 0 || TextUtils.isEmpty(string) || (convertToEmun = SHARE_MEDIA.convertToEmun(string)) == null) {
            return;
        }
        if (convertToEmun == SHARE_MEDIA.QQ) {
            a = this.e.get(convertToEmun);
            a.onCreate(activity, PlatformConfig.getPlatform(convertToEmun));
        } else {
            a = a(convertToEmun);
        }
        if (a != null) {
            a(convertToEmun, uMAuthListener, a, String.valueOf(System.currentTimeMillis()));
        }
    }

    public void a(UMShareConfig uMShareConfig) {
        if (this.e == null || this.e.isEmpty()) {
            return;
        }
        for (Map.Entry<SHARE_MEDIA, UMSSOHandler> entry : this.e.entrySet()) {
            UMSSOHandler value = entry.getValue();
            if (value != null) {
                value.setShareConfig(uMShareConfig);
            }
        }
    }
}