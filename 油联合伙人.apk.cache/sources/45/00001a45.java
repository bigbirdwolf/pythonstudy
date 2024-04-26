package com.yltx.oil.partner.modules.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alipay.sdk.app.statistic.c;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.JsWebViewClient;
import com.yltx.oil.partner.base.RxBus;
import com.yltx.oil.partner.base.RxWebCloseEvent;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.UserToken;
import com.yltx.oil.partner.data.response.ALiPayResp;
import com.yltx.oil.partner.data.response.JsBridgeDrillBean;
import com.yltx.oil.partner.data.response.PayResult;
import com.yltx.oil.partner.data.response.ShareEntity;
import com.yltx.oil.partner.modules.web.JsBridgeWebActivity;
import com.yltx.oil.partner.utils.AliPay;
import com.yltx.oil.partner.utils.CheckPermissionsUtils;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.DataCache;
import com.yltx.oil.partner.utils.ToastUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class JsBridgeWebActivity extends ToolBarActivity {
    private static final int PERMISSON_REQUESTCODE = 0;
    public static final int SDK_PAY_FLAG = 1;
    ALiPayResp aLiPayResp;
    String charset;
    String extend;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.web_view)
    BridgeWebView mWebViewInvite;
    String sanddata;
    private HashMap<String, SHARE_MEDIA> shareChannal;
    String sign;
    String signType;
    private String title;
    private String url = "";
    private String sandpayurl = "";
    @SuppressLint({"HandlerLeak"})
    public Handler mHandler = new Handler() { // from class: com.yltx.oil.partner.modules.web.JsBridgeWebActivity.2
        {
            JsBridgeWebActivity.this = this;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            PayResult payResult = new PayResult((Map) message.obj);
            payResult.getResult();
            String resultStatus = payResult.getResultStatus();
            if (TextUtils.equals(resultStatus, "9000")) {
                ToastUtil.showMiddleScreenToast("支付成功");
                JsBridgeWebActivity.this.finish();
            } else if (TextUtils.equals(resultStatus, "6001")) {
                ToastUtil.showMiddleScreenToast("支付宝支付已取消");
                JsBridgeWebActivity.this.finish();
            } else if (TextUtils.equals(resultStatus, "8000")) {
                ToastUtil.showMiddleScreenToast("支付结果确认中");
            } else if (TextUtils.equals(resultStatus, "4000")) {
                ToastUtil.showMiddleScreenToast("支付失败");
            } else if (TextUtils.equals(resultStatus, "6002")) {
                ToastUtil.showMiddleScreenToast("网络异常");
            } else {
                ToastUtil.showMiddleScreenToast("支付失败");
            }
        }
    };
    protected String[] needPermissions = {"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    private void nativeToJs() {
    }

    public static Intent getCallingIntent(Context context, String str) {
        Intent intent = new Intent(context, JsBridgeWebActivity.class);
        intent.putExtra("title", str);
        return intent;
    }

    public static Intent getCallingIntent(Context context, String str, String str2) {
        Intent intent = new Intent(context, JsBridgeWebActivity.class);
        intent.putExtra("title", str);
        intent.putExtra("url", str2);
        return intent;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, JsBridgeWebActivity.class);
    }

    private void setupUI() {
        Intent intent = getIntent();
        Config.IS_FIRST_LOGIN_FROM_WEB = true;
        this.title = intent.getStringExtra("title");
        this.url = intent.getStringExtra("url");
        this.shareChannal = new HashMap<>();
        this.shareChannal.put("wechatAppMessage", SHARE_MEDIA.WEIXIN);
        this.shareChannal.put("wechatTimeline", SHARE_MEDIA.WEIXIN_CIRCLE);
        setToolbarTitle(this.title);
        setUpWebView();
        RxBus.getDefault().toObserverable(RxWebCloseEvent.class).subscribe(new Action1() { // from class: com.yltx.oil.partner.modules.web.-$$Lambda$JsBridgeWebActivity$1ChSjZfvyJtcjogAvc6h0Jv14uY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                JsBridgeWebActivity.lambda$setupUI$0(JsBridgeWebActivity.this, (RxWebCloseEvent) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$setupUI$0(JsBridgeWebActivity jsBridgeWebActivity, RxWebCloseEvent rxWebCloseEvent) {
        jsBridgeWebActivity.finish();
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_invite_friends);
        ButterKnife.bind(this);
        setupUI();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Config.IS_FIRST_LOGIN_FROM_WEB = true;
        this.mWebViewInvite.loadUrl(this.url);
    }

    public void setVariablesToView() {
        String token;
        String userID;
        UserToken userToken = UserToken.getInstance();
        if (TextUtils.isEmpty(userToken.getToken())) {
            token = DataCache.getToken(this);
            userID = DataCache.getUserid(this);
        } else {
            token = userToken.getToken();
            userID = userToken.getUserID();
        }
        String format = String.format("{token:'%s',userId:'%s',phone:'%s'}", token, userID, userToken.getPhone());
        BridgeWebView bridgeWebView = this.mWebViewInvite;
        bridgeWebView.loadUrl("javascript: AppData=" + format);
    }

    private void setUpWebView() {
        this.mWebViewInvite.setDefaultHandler(new DefaultHandler());
        WebSettings settings = this.mWebViewInvite.getSettings();
        String userAgentString = settings.getUserAgentString();
        settings.setUserAgentString(userAgentString + " YLTXAPP/" + CommonUtils.getAppVersionName(this));
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(false);
        settings.setCacheMode(-1);
        this.mWebViewInvite.setWebViewClient(new JsWebViewClient(this.mWebViewInvite) { // from class: com.yltx.oil.partner.modules.web.JsBridgeWebActivity.1
            {
                JsBridgeWebActivity.this = this;
            }

            @Override // com.yltx.oil.partner.base.JsWebViewClient, com.github.lzyzsd.jsbridge.BridgeWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }

            @Override // com.yltx.oil.partner.base.JsWebViewClient, com.github.lzyzsd.jsbridge.BridgeWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                JsBridgeWebActivity.this.setVariablesToView();
            }

            @Override // com.yltx.oil.partner.base.JsWebViewClient, com.github.lzyzsd.jsbridge.BridgeWebViewClient, android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
        });
        this.mWebViewInvite.setWebChromeClient(new JsWebChromeClient());
        setVariablesToView();
        this.mWebViewInvite.loadUrl(this.url);
        jsToNative();
        nativeToJs();
    }

    private void jsToNative() {
        this.mWebViewInvite.registerHandler("drill", new BridgeHandler() { // from class: com.yltx.oil.partner.modules.web.-$$Lambda$JsBridgeWebActivity$M7MfPB9wJEObkTrMAWzHHLKGaQo
            @Override // com.github.lzyzsd.jsbridge.BridgeHandler
            public final void handler(String str, CallBackFunction callBackFunction) {
                JsBridgeWebActivity.lambda$jsToNative$1(JsBridgeWebActivity.this, str, callBackFunction);
            }
        });
        this.mWebViewInvite.registerHandler("login", new BridgeHandler() { // from class: com.yltx.oil.partner.modules.web.-$$Lambda$JsBridgeWebActivity$SGuIU__CvBmNaCHvCOqpZt4LS78
            @Override // com.github.lzyzsd.jsbridge.BridgeHandler
            public final void handler(String str, CallBackFunction callBackFunction) {
                JsBridgeWebActivity.lambda$jsToNative$2(str, callBackFunction);
            }
        });
        this.mWebViewInvite.registerHandler(c.U, new BridgeHandler() { // from class: com.yltx.oil.partner.modules.web.-$$Lambda$JsBridgeWebActivity$6pMw7MQF45JsvlVaKOUHOqayP5U
            @Override // com.github.lzyzsd.jsbridge.BridgeHandler
            public final void handler(String str, CallBackFunction callBackFunction) {
                JsBridgeWebActivity.lambda$jsToNative$3(JsBridgeWebActivity.this, str, callBackFunction);
            }
        });
    }

    public static /* synthetic */ void lambda$jsToNative$1(JsBridgeWebActivity jsBridgeWebActivity, String str, CallBackFunction callBackFunction) {
        JsBridgeDrillBean jsBridgeDrillBean = (JsBridgeDrillBean) new Gson().fromJson(str, (Class<Object>) JsBridgeDrillBean.class);
        HashMap<String, String> params = jsBridgeDrillBean.getParams();
        String kind = TextUtils.isEmpty(jsBridgeDrillBean.getKind()) ? "0" : jsBridgeDrillBean.getKind();
        if (params == null || params.isEmpty()) {
            if (jsBridgeDrillBean.getModule().equals(DrillType.DRILL_TYPE_1043)) {
                ToastUtil.showMiddleScreenToast("已提交申请");
                jsBridgeWebActivity.finish();
                return;
            } else if (jsBridgeDrillBean.getModule().equals(DrillType.DRILL_TYPE_2011)) {
                jsBridgeWebActivity.finish();
                return;
            } else {
                Drill.jumpToTarget(jsBridgeWebActivity, jsBridgeDrillBean.getModule(), "", kind);
                return;
            }
        }
        new HashMap();
        String str2 = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            entry.getKey();
            str2 = entry.getValue();
        }
        Drill.jumpToTarget(jsBridgeWebActivity, jsBridgeDrillBean.getModule(), str2, kind);
    }

    public static /* synthetic */ void lambda$jsToNative$2(String str, CallBackFunction callBackFunction) {
        ToastUtil.showMiddleScreenToast("请先登录");
    }

    public static /* synthetic */ void lambda$jsToNative$3(JsBridgeWebActivity jsBridgeWebActivity, String str, CallBackFunction callBackFunction) {
        jsBridgeWebActivity.aLiPayResp = (ALiPayResp) new Gson().fromJson(str, (Class<Object>) ALiPayResp.class);
        Log.d(">>>支付>>>", str);
        if (Build.VERSION.SDK_INT >= 23) {
            jsBridgeWebActivity.checkPermissions(jsBridgeWebActivity.needPermissions);
        } else {
            AliPay.payV2(jsBridgeWebActivity, jsBridgeWebActivity.aLiPayResp.getAliPayStr(), jsBridgeWebActivity.mHandler);
        }
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mWebViewInvite != null && this.mWebViewInvite.canGoBack() && TextUtils.isEmpty(this.sandpayurl)) {
            this.mWebViewInvite.goBack();
        } else {
            finish();
        }
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* loaded from: classes.dex */
    public class JsWebChromeClient extends WebChromeClient {
        private JsWebChromeClient() {
            JsBridgeWebActivity.this = r1;
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            JsBridgeWebActivity.this.mProgressBar.setProgress(i);
            if (i == 100) {
                Observable.timer(200L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.yltx.oil.partner.modules.web.-$$Lambda$JsBridgeWebActivity$JsWebChromeClient$M8Aq_wcTdU8obqDbVS1RedQEN0g
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        JsBridgeWebActivity.JsWebChromeClient.lambda$onProgressChanged$0(JsBridgeWebActivity.JsWebChromeClient.this, (Long) obj);
                    }
                });
                return;
            }
            if (JsBridgeWebActivity.this.mProgressBar.getVisibility() == 8) {
                JsBridgeWebActivity.this.mProgressBar.setVisibility(0);
            }
            JsBridgeWebActivity.this.mProgressBar.setProgress(i);
        }

        public static /* synthetic */ void lambda$onProgressChanged$0(JsWebChromeClient jsWebChromeClient, Long l) {
            JsBridgeWebActivity.this.mProgressBar.setVisibility(8);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            JsBridgeWebActivity.this.setVariablesToView();
            super.onReceivedTitle(webView, str);
            if (TextUtils.isEmpty(JsBridgeWebActivity.this.title)) {
                JsBridgeWebActivity.this.setToolbarTitle(str);
            }
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void shareByWechat(ShareEntity shareEntity, CallBackFunction callBackFunction) {
        new UserToken();
        UMWeb uMWeb = new UMWeb(shareEntity.getUrl());
        uMWeb.setThumb(new UMImage(this, shareEntity.getPic()));
        uMWeb.setTitle(shareEntity.getTitle());
        uMWeb.setDescription(shareEntity.getDesc());
        new ShareAction(this).setPlatform(this.shareChannal.get(shareEntity.getChannel().get(0))).withMedia(uMWeb).setCallback(new MyUmCallback(callBackFunction)).share();
    }

    /* loaded from: classes.dex */
    class MyUmCallback implements UMShareListener {
        private CallBackFunction function;

        @Override // com.umeng.socialize.UMShareListener
        public void onStart(SHARE_MEDIA share_media) {
        }

        public MyUmCallback() {
            JsBridgeWebActivity.this = r1;
            this.function = null;
        }

        public MyUmCallback(CallBackFunction callBackFunction) {
            JsBridgeWebActivity.this = r1;
            this.function = null;
            this.function = callBackFunction;
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onResult(SHARE_MEDIA share_media) {
            ToastUtil.showMiddleScreenToast("分享成功");
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onError(SHARE_MEDIA share_media, Throwable th) {
            ToastUtil.showMiddleScreenToast("分享失败");
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onCancel(SHARE_MEDIA share_media) {
            ToastUtil.showMiddleScreenToast("分享取消");
        }
    }

    private void checkPermissions(String... strArr) {
        List<String> findDeniedPermissions = findDeniedPermissions(strArr);
        if (findDeniedPermissions != null && findDeniedPermissions.size() > 0) {
            ActivityCompat.requestPermissions(this, (String[]) findDeniedPermissions.toArray(new String[findDeniedPermissions.size()]), 0);
        } else {
            AliPay.payV2(this, this.aLiPayResp.getAliPayStr(), this.mHandler);
        }
    }

    private List<String> findDeniedPermissions(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(getContext(), str) != 0 || ActivityCompat.shouldShowRequestPermissionRationale(this, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 0) {
            if (!CheckPermissionsUtils.verifyPermissions(iArr)) {
                CheckPermissionsUtils.showMissingPermissionDialog(getContext());
            } else {
                AliPay.payV2(this, this.aLiPayResp.getAliPayStr(), this.mHandler);
            }
        }
    }
}