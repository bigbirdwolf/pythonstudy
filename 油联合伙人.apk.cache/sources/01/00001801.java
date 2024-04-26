package com.yltx.oil.partner.modules.login.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class YLTXLoginActivity_ViewBinding implements Unbinder {
    private YLTXLoginActivity target;

    @UiThread
    public YLTXLoginActivity_ViewBinding(YLTXLoginActivity yLTXLoginActivity) {
        this(yLTXLoginActivity, yLTXLoginActivity.getWindow().getDecorView());
    }

    @UiThread
    public YLTXLoginActivity_ViewBinding(YLTXLoginActivity yLTXLoginActivity, View view) {
        this.target = yLTXLoginActivity;
        yLTXLoginActivity.commomHeadLeftImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.head_left_image, "field 'commomHeadLeftImage'", ImageView.class);
        yLTXLoginActivity.commomHeadTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.head_title, "field 'commomHeadTitle'", TextView.class);
        yLTXLoginActivity.sploginButton = (Button) Utils.findRequiredViewAsType(view, R.id.splogin_button, "field 'sploginButton'", Button.class);
        yLTXLoginActivity.tvSpphone = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_spphone, "field 'tvSpphone'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        YLTXLoginActivity yLTXLoginActivity = this.target;
        if (yLTXLoginActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        yLTXLoginActivity.commomHeadLeftImage = null;
        yLTXLoginActivity.commomHeadTitle = null;
        yLTXLoginActivity.sploginButton = null;
        yLTXLoginActivity.tvSpphone = null;
    }
}