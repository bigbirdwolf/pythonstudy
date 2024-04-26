package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.SelectableRoundedImageView;

/* loaded from: classes.dex */
public class MineInfoActivity_ViewBinding implements Unbinder {
    private MineInfoActivity target;

    @UiThread
    public MineInfoActivity_ViewBinding(MineInfoActivity mineInfoActivity) {
        this(mineInfoActivity, mineInfoActivity.getWindow().getDecorView());
    }

    @UiThread
    public MineInfoActivity_ViewBinding(MineInfoActivity mineInfoActivity, View view) {
        this.target = mineInfoActivity;
        mineInfoActivity.activityUserinnfosHeadIv = (SelectableRoundedImageView) Utils.findRequiredViewAsType(view, R.id.activity_userinnfos_head_iv, "field 'activityUserinnfosHeadIv'", SelectableRoundedImageView.class);
        mineInfoActivity.activityUserinnfosBasicMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_userinnfos_basic_message, "field 'activityUserinnfosBasicMessage'", TextView.class);
        mineInfoActivity.activityUserinnfosPhonenumBinding = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_userinnfos_phonenum_binding, "field 'activityUserinnfosPhonenumBinding'", TextView.class);
        mineInfoActivity.activityUserinnfosLoginPassword = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_userinnfos_login_password, "field 'activityUserinnfosLoginPassword'", TextView.class);
        mineInfoActivity.activityUserinnfosOut = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_userinnfos_out, "field 'activityUserinnfosOut'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        MineInfoActivity mineInfoActivity = this.target;
        if (mineInfoActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        mineInfoActivity.activityUserinnfosHeadIv = null;
        mineInfoActivity.activityUserinnfosBasicMessage = null;
        mineInfoActivity.activityUserinnfosPhonenumBinding = null;
        mineInfoActivity.activityUserinnfosLoginPassword = null;
        mineInfoActivity.activityUserinnfosOut = null;
    }
}