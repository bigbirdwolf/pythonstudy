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
public class NonoilLoginActivity_ViewBinding implements Unbinder {
    private NonoilLoginActivity target;

    @UiThread
    public NonoilLoginActivity_ViewBinding(NonoilLoginActivity nonoilLoginActivity) {
        this(nonoilLoginActivity, nonoilLoginActivity.getWindow().getDecorView());
    }

    @UiThread
    public NonoilLoginActivity_ViewBinding(NonoilLoginActivity nonoilLoginActivity, View view) {
        this.target = nonoilLoginActivity;
        nonoilLoginActivity.commomHeadLeftImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.head_left_image, "field 'commomHeadLeftImage'", ImageView.class);
        nonoilLoginActivity.commomHeadTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.head_title, "field 'commomHeadTitle'", TextView.class);
        nonoilLoginActivity.sploginButton = (Button) Utils.findRequiredViewAsType(view, R.id.splogin_button, "field 'sploginButton'", Button.class);
        nonoilLoginActivity.tvSpphone = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_spphone, "field 'tvSpphone'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        NonoilLoginActivity nonoilLoginActivity = this.target;
        if (nonoilLoginActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        nonoilLoginActivity.commomHeadLeftImage = null;
        nonoilLoginActivity.commomHeadTitle = null;
        nonoilLoginActivity.sploginButton = null;
        nonoilLoginActivity.tvSpphone = null;
    }
}