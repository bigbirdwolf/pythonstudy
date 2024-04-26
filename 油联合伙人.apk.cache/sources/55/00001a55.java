package com.yltx.oil.partner.modules.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.RxBus;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.modules.web.WVJBWebViewClient;
import com.yltx.oil.partner.modules.web.WebActivity;
import com.yltx.oil.partner.utils.IOUtils;
import com.yltx.oil.partner.utils.WebViewUtil;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class WebActivity extends ToolBarActivity implements Drillable {
    private static final String EXTRA_DATA = "extra:data";
    private static final String EXTRA_TITLE = "extra:title";
    private static final String EXTRA_URL = "extra:url";
    private static final String TAG = "WebActivity";
    String data;
    private Action1<String> mHandler;
    @BindView(R.id.progress_bar)
    public ProgressBar mProgressBar;
    private boolean needChangeTheTitle;
    private String title;
    private String url;
    @BindView(R.id.web_view)
    public WebView webView;

    public static Intent loadFromUrl(Context context, String str) {
        return loadFromUrl(context, null, str);
    }

    public static Intent loadFromUrl(Context context, String str, String str2) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(EXTRA_TITLE, str);
        intent.putExtra(EXTRA_URL, str2);
        return intent;
    }

    public static Intent loadFromData(Context context, String str, String str2) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(EXTRA_TITLE, str);
        intent.putExtra(EXTRA_DATA, str2);
        return intent;
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_embed_browser);
        ButterKnife.bind(this);
        if (this.mHandler == null) {
            this.mHandler = new Action1() { // from class: com.yltx.oil.partner.modules.web.-$$Lambda$WebActivity$6ThF8EKCsALABxc9HZWH2cmlsM4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    WebActivity.lambda$onCreate$0((String) obj);
                }
            };
        }
        setupVariable();
        setupView();
    }

    public static /* synthetic */ void lambda$onCreate$0(String str) {
        try {
            RxBus.getDefault().post(new WebHandleEvent(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.webView != null && this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            if (this.webView != null && this.webView.canGoBack()) {
                this.webView.goBack();
                return true;
            }
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setupVariable() {
        Bundle extras = getIntent().getExtras();
        this.title = extras.getString(EXTRA_TITLE);
        this.url = extras.getString(EXTRA_URL);
        this.data = extras.getString(EXTRA_DATA);
    }

    private void setupView() {
        if (TextUtils.isEmpty(this.title)) {
            this.needChangeTheTitle = true;
        } else {
            this.needChangeTheTitle = false;
            setToolbarTitle(this.title);
        }
        WebViewUtil.setupWebView(this.webView, new HandleWebViewClient(this.webView), new AnonymousClass1());
        if (TextUtils.isEmpty(this.url)) {
            try {
                Document parse = Jsoup.parse(IOUtils.readAllFromAssets(this, "html/template.html"));
                parse.getElementById("content").append(this.data);
                this.webView.loadData(parse.outerHtml(), "text/html; charset=utf-8", null);
                return;
            } catch (IOException e) {
                e.printStackTrace();
                this.webView.loadData(this.data, "text/html; charset=utf-8", null);
                return;
            }
        }
        this.webView.loadUrl(this.url);
    }

    /* renamed from: com.yltx.oil.partner.modules.web.WebActivity$1 */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends WebChromeClient {
        AnonymousClass1() {
            WebActivity.this = r1;
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (WebActivity.this.needChangeTheTitle) {
                WebActivity.this.setToolbarTitle(str);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            WebActivity.this.mProgressBar.setProgress(i);
            if (i == 100) {
                Observable.timer(200L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.yltx.oil.partner.modules.web.-$$Lambda$WebActivity$1$l8tKQBEEKizVwliQvOe7Zf4GNJM
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        WebActivity.AnonymousClass1.lambda$onProgressChanged$0(WebActivity.AnonymousClass1.this, (Long) obj);
                    }
                });
                return;
            }
            if (WebActivity.this.mProgressBar.getVisibility() == 8) {
                WebActivity.this.mProgressBar.setVisibility(0);
            }
            WebActivity.this.mProgressBar.setProgress(i);
        }

        public static /* synthetic */ void lambda$onProgressChanged$0(AnonymousClass1 anonymousClass1, Long l) {
            WebActivity.this.mProgressBar.setVisibility(8);
        }
    }

    @Override // com.yltx.oil.partner.modules.web.Drillable
    public Intent makeDrillIntent(String str) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_URL, str);
        return intent;
    }

    /* loaded from: classes.dex */
    public class HandleWebViewClient extends WVJBWebViewClient {
        public static /* synthetic */ void lambda$new$0(Object obj, WVJBWebViewClient.WVJBResponseCallback wVJBResponseCallback) {
        }

        @Override // com.yltx.oil.partner.modules.web.WVJBWebViewClient, android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        HandleWebViewClient(WebView webView) {
            super(webView, new WVJBWebViewClient.WVJBHandler() { // from class: com.yltx.oil.partner.modules.web.-$$Lambda$WebActivity$HandleWebViewClient$Szjt4N2LZtfZ0Qau9UPorc5CYO0
                @Override // com.yltx.oil.partner.modules.web.WVJBWebViewClient.WVJBHandler
                public final void request(Object obj, WVJBWebViewClient.WVJBResponseCallback wVJBResponseCallback) {
                    WebActivity.HandleWebViewClient.lambda$new$0(obj, wVJBResponseCallback);
                }
            });
            WebActivity.this = r1;
            enableLogging();
            registerHandler("jsToMobileAlertCallBack", new WVJBWebViewClient.WVJBHandler() { // from class: com.yltx.oil.partner.modules.web.-$$Lambda$WebActivity$HandleWebViewClient$O9liQgErRbosbQQ96h5fY1DEMO8
                @Override // com.yltx.oil.partner.modules.web.WVJBWebViewClient.WVJBHandler
                public final void request(Object obj, WVJBWebViewClient.WVJBResponseCallback wVJBResponseCallback) {
                    WebActivity.HandleWebViewClient.lambda$new$1(WebActivity.HandleWebViewClient.this, obj, wVJBResponseCallback);
                }
            });
        }

        public static /* synthetic */ void lambda$new$1(HandleWebViewClient handleWebViewClient, Object obj, WVJBWebViewClient.WVJBResponseCallback wVJBResponseCallback) {
            if (WebActivity.this.mHandler != null) {
                WebActivity.this.mHandler.call(String.valueOf(obj));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }
    }
}