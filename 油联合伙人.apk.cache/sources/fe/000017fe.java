package com.yltx.oil.partner.modules.login.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.ZoomButton;

/* loaded from: classes.dex */
public class YLSPLoginActivity_ViewBinding implements Unbinder {
    private YLSPLoginActivity target;

    @UiThread
    public YLSPLoginActivity_ViewBinding(YLSPLoginActivity yLSPLoginActivity) {
        this(yLSPLoginActivity, yLSPLoginActivity.getWindow().getDecorView());
    }

    @UiThread
    public YLSPLoginActivity_ViewBinding(YLSPLoginActivity yLSPLoginActivity, View view) {
        this.target = yLSPLoginActivity;
        yLSPLoginActivity.headLeftImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.head_left_image, "field 'headLeftImage'", ImageView.class);
        yLSPLoginActivity.headTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.head_title, "field 'headTitle'", TextView.class);
        yLSPLoginActivity.tvSpphone = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_spphone, "field 'tvSpphone'", TextView.class);
        yLSPLoginActivity.sploginButton = (ZoomButton) Utils.findRequiredViewAsType(view, R.id.splogin_button, "field 'sploginButton'", ZoomButton.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        YLSPLoginActivity yLSPLoginActivity = this.target;
        if (yLSPLoginActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        yLSPLoginActivity.headLeftImage = null;
        yLSPLoginActivity.headTitle = null;
        yLSPLoginActivity.tvSpphone = null;
        yLSPLoginActivity.sploginButton = null;
    }
}