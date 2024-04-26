package com.yltx.oil.partner.modules.login.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class LoginActivity_ViewBinding implements Unbinder {
    private LoginActivity target;

    @UiThread
    public LoginActivity_ViewBinding(LoginActivity loginActivity) {
        this(loginActivity, loginActivity.getWindow().getDecorView());
    }

    @UiThread
    public LoginActivity_ViewBinding(LoginActivity loginActivity, View view) {
        this.target = loginActivity;
        loginActivity.frameLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.activity_login_fl, "field 'frameLayout'", FrameLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        LoginActivity loginActivity = this.target;
        if (loginActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        loginActivity.frameLayout = null;
    }
}