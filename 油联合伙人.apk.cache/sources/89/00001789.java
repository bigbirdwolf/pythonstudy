package com.yltx.oil.partner.modules.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class ShareDetailsActivity_ViewBinding implements Unbinder {
    private ShareDetailsActivity target;

    @UiThread
    public ShareDetailsActivity_ViewBinding(ShareDetailsActivity shareDetailsActivity) {
        this(shareDetailsActivity, shareDetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public ShareDetailsActivity_ViewBinding(ShareDetailsActivity shareDetailsActivity, View view) {
        this.target = shareDetailsActivity;
        shareDetailsActivity.tvShareText = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_share_text, "field 'tvShareText'", TextView.class);
        shareDetailsActivity.tvShareImg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_share_img, "field 'tvShareImg'", TextView.class);
        shareDetailsActivity.tvShareCon = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_share_con, "field 'tvShareCon'", TextView.class);
        shareDetailsActivity.btnCopy = (Button) Utils.findRequiredViewAsType(view, R.id.btn_copy, "field 'btnCopy'", Button.class);
        shareDetailsActivity.llFxView = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_fx_view, "field 'llFxView'", LinearLayout.class);
        shareDetailsActivity.llFximgView = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_fximg_view, "field 'llFximgView'", LinearLayout.class);
        shareDetailsActivity.ivIco = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_ico, "field 'ivIco'", ImageView.class);
        shareDetailsActivity.ivEncode = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_encode, "field 'ivEncode'", ImageView.class);
        shareDetailsActivity.tvFxname = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_fxname, "field 'tvFxname'", TextView.class);
        shareDetailsActivity.tvYuanjia = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_yuanjia, "field 'tvYuanjia'", TextView.class);
        shareDetailsActivity.tvDazhe = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dazhe, "field 'tvDazhe'", TextView.class);
        shareDetailsActivity.tvFxWx = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_fx_wx, "field 'tvFxWx'", TextView.class);
        shareDetailsActivity.tvFxWxPyq = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_fx_wx_pyq, "field 'tvFxWxPyq'", TextView.class);
        shareDetailsActivity.tvGuize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_guize, "field 'tvGuize'", TextView.class);
        shareDetailsActivity.fxz = (TextView) Utils.findRequiredViewAsType(view, R.id.fxz, "field 'fxz'", TextView.class);
        shareDetailsActivity.imCesi = (ImageView) Utils.findRequiredViewAsType(view, R.id.im_cesi, "field 'imCesi'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ShareDetailsActivity shareDetailsActivity = this.target;
        if (shareDetailsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        shareDetailsActivity.tvShareText = null;
        shareDetailsActivity.tvShareImg = null;
        shareDetailsActivity.tvShareCon = null;
        shareDetailsActivity.btnCopy = null;
        shareDetailsActivity.llFxView = null;
        shareDetailsActivity.llFximgView = null;
        shareDetailsActivity.ivIco = null;
        shareDetailsActivity.ivEncode = null;
        shareDetailsActivity.tvFxname = null;
        shareDetailsActivity.tvYuanjia = null;
        shareDetailsActivity.tvDazhe = null;
        shareDetailsActivity.tvFxWx = null;
        shareDetailsActivity.tvFxWxPyq = null;
        shareDetailsActivity.tvGuize = null;
        shareDetailsActivity.fxz = null;
        shareDetailsActivity.imCesi = null;
    }
}