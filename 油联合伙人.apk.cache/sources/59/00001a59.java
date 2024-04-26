package com.yltx.oil.partner.modules.web;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class WebActivity_ViewBinding implements Unbinder {
    private WebActivity target;

    @UiThread
    public WebActivity_ViewBinding(WebActivity webActivity) {
        this(webActivity, webActivity.getWindow().getDecorView());
    }

    @UiThread
    public WebActivity_ViewBinding(WebActivity webActivity, View view) {
        this.target = webActivity;
        webActivity.webView = (WebView) Utils.findRequiredViewAsType(view, R.id.web_view, "field 'webView'", WebView.class);
        webActivity.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress_bar, "field 'mProgressBar'", ProgressBar.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        WebActivity webActivity = this.target;
        if (webActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        webActivity.webView = null;
        webActivity.mProgressBar = null;
    }
}