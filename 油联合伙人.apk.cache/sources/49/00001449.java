package com.umeng.socialize.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.proguard.e;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.HandlerRequestCode;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.StringName;
import com.umeng.socialize.bean.UmengErrorCode;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import com.umeng.socialize.utils.UrlUtil;
import com.umeng.socialize.weixin.net.WXAuthUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UMWXHandler extends UMSSOHandler {
    private static final String ERRMSG = "errmsg";
    private static final String ERRORCODE = "errcode";
    private static final String ERROR_CODE_TOKEN_ACCESS_FAIL = "42002";
    private static final String ERROR_CODE_TOKEN_FAIL = "40001";
    private static final String ERROR_CODE_TOKEN_REFESH_FAIL = "40030";
    private static final String HEADIMGURL = "headimgurl";
    private static final String LANGUAGE = "language";
    private static final String NICKNAME = "nickname";
    private static final String PRIVILEGE = "privilege";
    private static final int REFRESH_TOKEN_EXPIRES = 604800;
    private static final String REFRESH_TOKEN_EXPIRES_KEY = "refresh_token_expires";
    private static final int RESP_TYPE_AUTH = 1;
    private static final int RESP_TYPE_SHARE = 2;
    private static final String SEX = "sex";
    private static final String TAG = "UMWXHandler";
    private static String sScope = "snsapi_userinfo,snsapi_friend,snsapi_message";
    private PlatformConfig.APPIDPlatform config;
    private UMAuthListener mAuthListener;
    private IWXAPI mWXApi;
    private UMShareListener umShareListener;
    private WeixinPreferences weixinPreferences;
    private String VERSION = "6.9.4";
    private SHARE_MEDIA mTarget = SHARE_MEDIA.WEIXIN;
    private IWXAPIEventHandler mEventHandler = new IWXAPIEventHandler() { // from class: com.umeng.socialize.handler.UMWXHandler.16
        @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
        public void onReq(BaseReq baseReq) {
        }

        @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
        public void onResp(BaseResp baseResp) {
            switch (baseResp.getType()) {
                case 1:
                    UMWXHandler.this.onAuthCallback((SendAuth.Resp) baseResp);
                    return;
                case 2:
                    UMWXHandler.this.onShareCallback((SendMessageToWX.Resp) baseResp);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public int getRequestCode() {
        return HandlerRequestCode.WX_REQUEST_CODE;
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public String getSDKVersion() {
        return "3.1.1";
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public boolean isSupportAuth() {
        return true;
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public void onCreate(Context context, PlatformConfig.Platform platform) {
        super.onCreate(context, platform);
        this.weixinPreferences = new WeixinPreferences(context.getApplicationContext(), "weixin");
        this.config = (PlatformConfig.APPIDPlatform) platform;
        this.mWXApi = WXAPIFactory.createWXAPI(context.getApplicationContext(), this.config.appId, getShareConfig().getOpenWXAnalytics());
        this.mWXApi.registerApp(this.config.appId);
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public String getVersion() {
        return this.VERSION;
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public void setAuthListener(UMAuthListener uMAuthListener) {
        super.setAuthListener(uMAuthListener);
        this.mAuthListener = uMAuthListener;
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public boolean isHasAuthListener() {
        return this.mAuthListener != null;
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public void authorize(final UMAuthListener uMAuthListener) {
        if (this.config != null) {
            this.mTarget = this.config.getName();
        }
        this.mAuthListener = uMAuthListener;
        if (!isInstall()) {
            if (Config.isJumptoAppStore) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("http://log.umsns.com/link/weixin/download/"));
                this.mWeakAct.get().startActivity(intent);
            }
            runInMainThread(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    UMWXHandler.this.getAuthListener(uMAuthListener).onError(UMWXHandler.this.mTarget, 0, new Throwable(UmengErrorCode.NotInstall.getMessage()));
                }
            });
        } else if (isAuthValid()) {
            String refreshToken = getRefreshToken();
            loadOauthData("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + this.config.appId + "&grant_type=refresh_token&refresh_token=" + refreshToken);
            getRefreshToken();
            final Map<String, String> authWithRefreshToken = getAuthWithRefreshToken(refreshToken);
            if (authWithRefreshToken.containsKey(ERRORCODE) && (authWithRefreshToken.get(ERRORCODE).equals(ERROR_CODE_TOKEN_ACCESS_FAIL) || authWithRefreshToken.get(ERRORCODE).equals(ERROR_CODE_TOKEN_REFESH_FAIL))) {
                weixinPreferencesDelete();
                authorize(uMAuthListener);
                return;
            }
            runInMainThread(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.2
                @Override // java.lang.Runnable
                public void run() {
                    UMWXHandler.this.getAuthListener(UMWXHandler.this.mAuthListener).onComplete(SHARE_MEDIA.WEIXIN, 0, authWithRefreshToken);
                }
            });
        } else {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = sScope;
            req.state = "none";
            this.mWXApi.sendReq(req);
        }
    }

    private void runInMainThread(Runnable runnable) {
        QueuedWork.runInMain(runnable);
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public boolean isAuthorize() {
        return this.weixinPreferences.isAuth();
    }

    private void loadOauthData(String str) {
        setBundle(parseAuthData(WXAuthUtils.request(str)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle parseAuthData(String str) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            return bundle;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
            bundle.putLong(REFRESH_TOKEN_EXPIRES_KEY, 604800L);
            bundle.putString("accessToken", bundle.getString(SocializeProtocolConstants.PROTOCOL_KEY_ACCESSTOKEN));
            bundle.putString("expiration", bundle.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXPIRE_IN));
            bundle.putString("refreshToken", bundle.getString("refresh_token"));
            bundle.putString("uid", bundle.getString(CommonNetImpl.UNIONID));
        } catch (JSONException e) {
            SLog.error(e);
        }
        return bundle;
    }

    private Map<String, String> getAuthWithRefreshToken(String str) {
        Map<String, String> map;
        try {
            map = SocializeUtils.jsonToMap(WXAuthUtils.request("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + this.config.appId + "&grant_type=refresh_token&refresh_token=" + str));
            try {
                map.put(CommonNetImpl.UNIONID, getUid());
            } catch (Exception e) {
                e = e;
                SLog.error(e);
                return map;
            }
        } catch (Exception e2) {
            e = e2;
            map = null;
        }
        return map;
    }

    private void getAuthWithCode(String str, final UMAuthListener uMAuthListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        sb.append("appid=");
        sb.append(this.config.appId);
        sb.append("&secret=");
        sb.append(this.config.appkey);
        sb.append("&code=");
        sb.append(str);
        sb.append("&grant_type=authorization_code");
        QueuedWork.runInBack(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.3
            @Override // java.lang.Runnable
            public void run() {
                String request = WXAuthUtils.request(sb.toString());
                try {
                    final Map<String, String> jsonToMap = SocializeUtils.jsonToMap(request);
                    if (jsonToMap == null || jsonToMap.size() == 0) {
                        UMWXHandler.this.getMap();
                    }
                    UMWXHandler.this.setBundle(UMWXHandler.this.parseAuthData(request));
                    QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (jsonToMap.get(UMWXHandler.ERRORCODE) != null) {
                                UMAuthListener authListener = UMWXHandler.this.getAuthListener(uMAuthListener);
                                SHARE_MEDIA share_media = SHARE_MEDIA.WEIXIN;
                                authListener.onError(share_media, 0, new Throwable(UmengErrorCode.AuthorizeFailed.getMessage() + ((String) jsonToMap.get(UMWXHandler.ERRMSG))));
                            } else {
                                UMWXHandler.this.getAuthListener(uMAuthListener).onComplete(SHARE_MEDIA.WEIXIN, 0, jsonToMap);
                            }
                            jsonToMap.put(CommonNetImpl.AID, UMWXHandler.this.config.appId);
                            jsonToMap.put(CommonNetImpl.AS, UMWXHandler.this.config.appkey);
                            jsonToMap.put("uid", jsonToMap.get(SocializeProtocolConstants.PROTOCOL_KEY_OPENID));
                            jsonToMap.put(CommonNetImpl.UNIONID, jsonToMap.get(CommonNetImpl.UNIONID));
                        }
                    });
                } catch (Exception e) {
                    SLog.error(e);
                }
            }
        }, true);
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public boolean isInstall() {
        return this.mWXApi != null && this.mWXApi.isWXAppInstalled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAuthCallback(SendAuth.Resp resp) {
        if (resp.errCode == 0) {
            getAuthWithCode(resp.code, this.mAuthListener);
        } else if (resp.errCode == -2) {
            getAuthListener(this.mAuthListener).onCancel(SHARE_MEDIA.WEIXIN, 0);
        } else if (resp.errCode == -6) {
            UMAuthListener authListener = getAuthListener(this.mAuthListener);
            SHARE_MEDIA share_media = SHARE_MEDIA.WEIXIN;
            authListener.onError(share_media, 0, new Throwable(UmengErrorCode.AuthorizeFailed.getMessage() + UmengText.errorWithUrl(UmengText.AUTH.AUTH_DENIED, UrlUtil.WX_ERROR_SIGN)));
        } else {
            CharSequence concat = TextUtils.concat("weixin auth error (", String.valueOf(resp.errCode), "):", resp.errStr);
            UMAuthListener authListener2 = getAuthListener(this.mAuthListener);
            SHARE_MEDIA share_media2 = SHARE_MEDIA.WEIXIN;
            authListener2.onError(share_media2, 0, new Throwable(UmengErrorCode.AuthorizeFailed.getMessage() + ((Object) concat)));
        }
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public void deleteAuth(final UMAuthListener uMAuthListener) {
        weixinPreferencesDelete();
        QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.4
            @Override // java.lang.Runnable
            public void run() {
                UMWXHandler.this.getAuthListener(uMAuthListener).onComplete(SHARE_MEDIA.WEIXIN, 1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchUserInfo(final UMAuthListener uMAuthListener) {
        String openid = getOpenid();
        String accessToken = getAccessToken();
        final String request = WXAuthUtils.request("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid + "&lang=zh_CN");
        if (TextUtils.isEmpty(request) || request.startsWith("##")) {
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.5
                @Override // java.lang.Runnable
                public void run() {
                    UMAuthListener authListener = UMWXHandler.this.getAuthListener(uMAuthListener);
                    SHARE_MEDIA share_media = SHARE_MEDIA.WEIXIN;
                    authListener.onError(share_media, 2, new Throwable(UmengErrorCode.RequestForUserProfileFailed.getMessage() + request));
                }
            });
            return;
        }
        final Map<String, String> parseUserInfo = parseUserInfo(request);
        if (parseUserInfo == null) {
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.6
                @Override // java.lang.Runnable
                public void run() {
                    UMAuthListener authListener = UMWXHandler.this.getAuthListener(uMAuthListener);
                    SHARE_MEDIA share_media = SHARE_MEDIA.WEIXIN;
                    authListener.onError(share_media, 2, new Throwable(UmengErrorCode.RequestForUserProfileFailed.getMessage() + request));
                }
            });
        } else if (parseUserInfo.containsKey(ERRORCODE)) {
            if (parseUserInfo.get(ERRORCODE).equals(ERROR_CODE_TOKEN_FAIL)) {
                weixinPreferencesDelete();
                authorize(uMAuthListener);
                return;
            }
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.7
                @Override // java.lang.Runnable
                public void run() {
                    UMAuthListener authListener = UMWXHandler.this.getAuthListener(uMAuthListener);
                    SHARE_MEDIA share_media = SHARE_MEDIA.WEIXIN;
                    authListener.onError(share_media, 2, new Throwable(UmengErrorCode.RequestForUserProfileFailed.getMessage() + ((String) parseUserInfo.get(UMWXHandler.ERRORCODE))));
                }
            });
        } else {
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.8
                @Override // java.lang.Runnable
                public void run() {
                    UMWXHandler.this.getAuthListener(uMAuthListener).onComplete(SHARE_MEDIA.WEIXIN, 2, parseUserInfo);
                }
            });
        }
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public void getPlatformInfo(final UMAuthListener uMAuthListener) {
        if (getShareConfig().isNeedAuthOnGetUserInfo()) {
            weixinPreferencesDelete();
        }
        authorize(new UMAuthListener() { // from class: com.umeng.socialize.handler.UMWXHandler.9
            @Override // com.umeng.socialize.UMAuthListener
            public void onStart(SHARE_MEDIA share_media) {
            }

            @Override // com.umeng.socialize.UMAuthListener
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                QueuedWork.runInBack(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        UMWXHandler.this.fetchUserInfo(uMAuthListener);
                    }
                }, true);
            }

            @Override // com.umeng.socialize.UMAuthListener
            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                UMWXHandler.this.getAuthListener(uMAuthListener).onError(share_media, i, th);
            }

            @Override // com.umeng.socialize.UMAuthListener
            public void onCancel(SHARE_MEDIA share_media, int i) {
                UMWXHandler.this.getAuthListener(uMAuthListener).onCancel(share_media, i);
            }
        });
    }

    private Map<String, String> parseUserInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(ERRORCODE)) {
                hashMap.put(ERRORCODE, jSONObject.getString(ERRORCODE));
                hashMap.put(ERRMSG, jSONObject.getString(ERRMSG));
                return hashMap;
            }
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_OPENID, jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID));
            hashMap.put("screen_name", jSONObject.optString(NICKNAME));
            hashMap.put("name", jSONObject.optString(NICKNAME));
            hashMap.put("language", jSONObject.optString("language"));
            hashMap.put("city", jSONObject.optString("city"));
            hashMap.put("province", jSONObject.optString("province"));
            hashMap.put(e.N, jSONObject.optString(e.N));
            hashMap.put("profile_image_url", jSONObject.optString(HEADIMGURL));
            hashMap.put("iconurl", jSONObject.optString(HEADIMGURL));
            hashMap.put(CommonNetImpl.UNIONID, jSONObject.optString(CommonNetImpl.UNIONID));
            hashMap.put("uid", jSONObject.optString(CommonNetImpl.UNIONID));
            hashMap.put("gender", getGender(jSONObject.optString("sex")));
            JSONArray optJSONArray = jSONObject.optJSONArray(PRIVILEGE);
            int length = optJSONArray == null ? 0 : optJSONArray.length();
            if (length > 0) {
                String[] strArr = new String[length];
                for (int i = 0; i < length; i++) {
                    strArr[i] = optJSONArray.get(i).toString();
                }
                hashMap.put(PRIVILEGE, strArr.toString());
            }
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_ACCESSTOKEN, getAccessToken());
            hashMap.put("refreshToken", getRefreshToken());
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_EXPIRE_IN, String.valueOf(getmAccessTokenTTL()));
            hashMap.put("accessToken", getAccessToken());
            hashMap.put("refreshToken", getRefreshToken());
            hashMap.put("expiration", String.valueOf(getmAccessTokenTTL()));
            return hashMap;
        } catch (JSONException e) {
            SLog.error(e);
            return Collections.emptyMap();
        }
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public String getGender(Object obj) {
        String str = StringName.male;
        String str2 = StringName.female;
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (obj.equals("m") || obj.equals("1") || obj.equals(UmengText.MAN)) ? str : (obj.equals("f") || obj.equals("2") || obj.equals(UmengText.WOMAN)) ? str2 : obj.toString();
        } else if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            return num.intValue() == 1 ? str : num.intValue() == 2 ? str2 : obj.toString();
        } else {
            return obj.toString();
        }
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public boolean share(ShareContent shareContent, final UMShareListener uMShareListener) {
        if (this.config != null) {
            this.mTarget = this.config.getName();
        }
        if (!isInstall()) {
            if (Config.isJumptoAppStore) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(SocializeConstants.DOWN_URL_WX));
                this.mWeakAct.get().startActivity(intent);
            }
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.10
                @Override // java.lang.Runnable
                public void run() {
                    UMWXHandler.this.getShareListener(uMShareListener).onError(UMWXHandler.this.mTarget, new Throwable(UmengErrorCode.NotInstall.getMessage()));
                }
            });
            return false;
        }
        WeiXinShareContent weiXinShareContent = new WeiXinShareContent(shareContent);
        if (this.mShareConfig != null) {
            weiXinShareContent.setCompressListener(this.mShareConfig.getCompressListener());
        }
        if (!isAbleShareEmoji(this.mTarget, weiXinShareContent)) {
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.11
                @Override // java.lang.Runnable
                public void run() {
                    UMShareListener shareListener = UMWXHandler.this.getShareListener(uMShareListener);
                    SHARE_MEDIA share_media = UMWXHandler.this.mTarget;
                    shareListener.onError(share_media, new Throwable(UmengErrorCode.ShareDataTypeIllegal.getMessage() + UmengText.WX.WX_CIRCLE_NOT_SUPPORT_EMOJ));
                }
            });
            return false;
        } else if (!isAbleShareMin(this.mTarget, weiXinShareContent)) {
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.12
                @Override // java.lang.Runnable
                public void run() {
                    UMShareListener shareListener = UMWXHandler.this.getShareListener(uMShareListener);
                    SHARE_MEDIA share_media = UMWXHandler.this.mTarget;
                    shareListener.onError(share_media, new Throwable(UmengErrorCode.ShareDataTypeIllegal.getMessage() + UmengText.WX.WX_CIRCLE_NOT_SUPPORT_MIN));
                }
            });
            return false;
        } else {
            this.umShareListener = uMShareListener;
            return shareTo(weiXinShareContent);
        }
    }

    private boolean isAbleShareEmoji(SHARE_MEDIA share_media, WeiXinShareContent weiXinShareContent) {
        if (weiXinShareContent.getmStyle() == 64) {
            return (share_media == SHARE_MEDIA.WEIXIN_CIRCLE || share_media == SHARE_MEDIA.WEIXIN_FAVORITE) ? false : true;
        }
        return true;
    }

    public boolean isAbleShareMin(SHARE_MEDIA share_media, WeiXinShareContent weiXinShareContent) {
        if (weiXinShareContent.getmStyle() == 128) {
            return (share_media == SHARE_MEDIA.WEIXIN_CIRCLE || share_media == SHARE_MEDIA.WEIXIN_FAVORITE) ? false : true;
        }
        return true;
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public boolean isSupport() {
        return this.mWXApi.isWXAppSupportAPI();
    }

    private boolean shareTo(WeiXinShareContent weiXinShareContent) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction(weiXinShareContent.getStrStyle());
        req.message = weiXinShareContent.getWxMediaMessage();
        switch (this.mTarget) {
            case WEIXIN:
                req.scene = 0;
                break;
            case WEIXIN_CIRCLE:
                req.scene = 1;
                break;
            case WEIXIN_FAVORITE:
                req.scene = 2;
                break;
            default:
                req.scene = 2;
                break;
        }
        if (req.message == null) {
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.13
                @Override // java.lang.Runnable
                public void run() {
                    UMShareListener shareListener = UMWXHandler.this.getShareListener(UMWXHandler.this.umShareListener);
                    SHARE_MEDIA share_media = UMWXHandler.this.mTarget;
                    shareListener.onError(share_media, new Throwable(UmengErrorCode.UnKnowCode.getMessage() + "message = null"));
                }
            });
            return false;
        } else if (req.message.mediaObject == null) {
            QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.14
                @Override // java.lang.Runnable
                public void run() {
                    UMShareListener shareListener = UMWXHandler.this.getShareListener(UMWXHandler.this.umShareListener);
                    SHARE_MEDIA share_media = UMWXHandler.this.mTarget;
                    shareListener.onError(share_media, new Throwable(UmengErrorCode.UnKnowCode.getMessage() + "mediaobject = null"));
                }
            });
            return false;
        } else {
            boolean sendReq = this.mWXApi.sendReq(req);
            if (!sendReq) {
                QueuedWork.runInMain(new Runnable() { // from class: com.umeng.socialize.handler.UMWXHandler.15
                    @Override // java.lang.Runnable
                    public void run() {
                        UMShareListener shareListener = UMWXHandler.this.getShareListener(UMWXHandler.this.umShareListener);
                        SHARE_MEDIA share_media = UMWXHandler.this.mTarget;
                        shareListener.onError(share_media, new Throwable(UmengErrorCode.UnKnowCode.getMessage() + UmengText.SHARE.SHARE_CONTENT_FAIL));
                    }
                });
            }
            return sendReq;
        }
    }

    protected void onShareCallback(SendMessageToWX.Resp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_BAN /* -6 */:
                UMShareListener shareListener = getShareListener(this.umShareListener);
                SHARE_MEDIA share_media = this.mTarget;
                shareListener.onError(share_media, new Throwable(UmengErrorCode.ShareFailed.getMessage() + UmengText.errorWithUrl(UmengText.AUTH.AUTH_DENIED, UrlUtil.WX_ERROR_SIGN)));
                return;
            case BaseResp.ErrCode.ERR_UNSUPPORT /* -5 */:
                UMShareListener shareListener2 = getShareListener(this.umShareListener);
                SHARE_MEDIA share_media2 = this.mTarget;
                shareListener2.onError(share_media2, new Throwable(UmengErrorCode.ShareFailed.getMessage() + UmengText.SHARE.VERSION_NOT_SUPPORT));
                return;
            case -4:
            default:
                UMShareListener shareListener3 = getShareListener(this.umShareListener);
                SHARE_MEDIA share_media3 = this.mTarget;
                shareListener3.onError(share_media3, new Throwable(UmengErrorCode.ShareFailed.getMessage() + "code:" + resp.errCode + "msg:" + resp.errStr));
                return;
            case -3:
            case -1:
                UMShareListener shareListener4 = getShareListener(this.umShareListener);
                SHARE_MEDIA share_media4 = this.mTarget;
                shareListener4.onError(share_media4, new Throwable(UmengErrorCode.ShareFailed.getMessage() + resp.errStr));
                return;
            case -2:
                getShareListener(this.umShareListener).onCancel(this.mTarget);
                return;
            case 0:
                new HashMap().put("uid", resp.openId);
                getShareListener(this.umShareListener).onResult(this.mTarget);
                return;
        }
    }

    public IWXAPIEventHandler getWXEventHandler() {
        return this.mEventHandler;
    }

    public IWXAPI getWXApi() {
        return this.mWXApi;
    }

    private String buildTransaction(String str) {
        if (str == null) {
            return String.valueOf(System.currentTimeMillis());
        }
        return str + System.currentTimeMillis();
    }

    private boolean isAuthValid() {
        if (this.weixinPreferences != null) {
            return this.weixinPreferences.isAuthValid();
        }
        return false;
    }

    private boolean isAccessTokenAvailable() {
        if (this.weixinPreferences != null) {
            return this.weixinPreferences.isAccessTokenAvailable();
        }
        return false;
    }

    private void weixinPreferencesDelete() {
        if (this.weixinPreferences != null) {
            this.weixinPreferences.delete();
        }
    }

    private String getRefreshToken() {
        return this.weixinPreferences != null ? this.weixinPreferences.getRefreshToken() : "";
    }

    private String getOpenid() {
        return this.weixinPreferences != null ? this.weixinPreferences.getmOpenid() : "";
    }

    private String getUid() {
        return this.weixinPreferences != null ? this.weixinPreferences.getUID() : "";
    }

    private String getAccessToken() {
        return this.weixinPreferences != null ? this.weixinPreferences.getAccessToken() : "";
    }

    private long getmAccessTokenTTL() {
        if (this.weixinPreferences != null) {
            return this.weixinPreferences.getmAccessTokenTTL();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBundle(Bundle bundle) {
        if (this.weixinPreferences != null) {
            this.weixinPreferences.setBundle(bundle).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> getMap() {
        if (this.weixinPreferences != null) {
            return this.weixinPreferences.getmap();
        }
        return null;
    }

    @Override // com.umeng.socialize.handler.UMSSOHandler
    public void release() {
        super.release();
        this.mAuthListener = null;
    }
}