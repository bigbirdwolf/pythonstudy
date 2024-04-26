package com.yltx.oil.partner.modules.web;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class JsBridgeWebActivity_ViewBinding implements Unbinder {
    private JsBridgeWebActivity target;

    @UiThread
    public JsBridgeWebActivity_ViewBinding(JsBridgeWebActivity jsBridgeWebActivity) {
        this(jsBridgeWebActivity, jsBridgeWebActivity.getWindow().getDecorView());
    }

    @UiThread
    public JsBridgeWebActivity_ViewBinding(JsBridgeWebActivity jsBridgeWebActivity, View view) {
        this.target = jsBridgeWebActivity;
        jsBridgeWebActivity.mWebViewInvite = (BridgeWebView) Utils.findRequiredViewAsType(view, R.id.web_view, "field 'mWebViewInvite'", BridgeWebView.class);
        jsBridgeWebActivity.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress_bar, "field 'mProgressBar'", ProgressBar.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        JsBridgeWebActivity jsBridgeWebActivity = this.target;
        if (jsBridgeWebActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        jsBridgeWebActivity.mWebViewInvite = null;
        jsBridgeWebActivity.mProgressBar = null;
    }
}