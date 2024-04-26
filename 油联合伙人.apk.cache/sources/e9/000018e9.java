package com.yltx.oil.partner.modules.mine.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.utils.XTSwipeRefreshLayout;
import com.yltx.oil.partner.widget.SelectableRoundedImageView;

/* loaded from: classes.dex */
public class FragmentMine_ViewBinding implements Unbinder {
    private FragmentMine target;

    @UiThread
    public FragmentMine_ViewBinding(FragmentMine fragmentMine, View view) {
        this.target = fragmentMine;
        fragmentMine.mRefresh = (XTSwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.layout_refresh, "field 'mRefresh'", XTSwipeRefreshLayout.class);
        fragmentMine.fragmentMineHeadIv = (SelectableRoundedImageView) Utils.findRequiredViewAsType(view, R.id.fragment_mine_head_iv, "field 'fragmentMineHeadIv'", SelectableRoundedImageView.class);
        fragmentMine.tvLogin = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_login, "field 'tvLogin'", LinearLayout.class);
        fragmentMine.tvMinePhone = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_mine_phone, "field 'tvMinePhone'", TextView.class);
        fragmentMine.tvMineId = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_mine_id, "field 'tvMineId'", TextView.class);
        fragmentMine.llKefu = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_kefu, "field 'llKefu'", LinearLayout.class);
        fragmentMine.tvXiaoshoue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_xiaoshoue, "field 'tvXiaoshoue'", TextView.class);
        fragmentMine.llMineinfo = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_mineinfo, "field 'llMineinfo'", LinearLayout.class);
        fragmentMine.llMinefas = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_minefas, "field 'llMinefas'", LinearLayout.class);
        fragmentMine.llMinefriends = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_minefriends, "field 'llMinefriends'", LinearLayout.class);
        fragmentMine.llMineguide = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_mineguide, "field 'llMineguide'", LinearLayout.class);
        fragmentMine.llMinehelp = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_minehelp, "field 'llMinehelp'", LinearLayout.class);
        fragmentMine.llMinefeedback = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_minefeedback, "field 'llMinefeedback'", LinearLayout.class);
        fragmentMine.llMinecomplaint = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_minecomplaint, "field 'llMinecomplaint'", LinearLayout.class);
        fragmentMine.tvGo = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_go, "field 'tvGo'", TextView.class);
        fragmentMine.tv_dl = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dl, "field 'tv_dl'", TextView.class);
        fragmentMine.tvYongj = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_yongj, "field 'tvYongj'", TextView.class);
        fragmentMine.tvPhone = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_phone, "field 'tvPhone'", TextView.class);
        fragmentMine.llDengji = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_dengji, "field 'llDengji'", LinearLayout.class);
        fragmentMine.tvDengji = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dengji, "field 'tvDengji'", TextView.class);
        fragmentMine.imDengji = (ImageView) Utils.findRequiredViewAsType(view, R.id.im_dengji, "field 'imDengji'", ImageView.class);
        fragmentMine.imDji = (ImageView) Utils.findRequiredViewAsType(view, R.id.im_dji, "field 'imDji'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        FragmentMine fragmentMine = this.target;
        if (fragmentMine == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentMine.mRefresh = null;
        fragmentMine.fragmentMineHeadIv = null;
        fragmentMine.tvLogin = null;
        fragmentMine.tvMinePhone = null;
        fragmentMine.tvMineId = null;
        fragmentMine.llKefu = null;
        fragmentMine.tvXiaoshoue = null;
        fragmentMine.llMineinfo = null;
        fragmentMine.llMinefas = null;
        fragmentMine.llMinefriends = null;
        fragmentMine.llMineguide = null;
        fragmentMine.llMinehelp = null;
        fragmentMine.llMinefeedback = null;
        fragmentMine.llMinecomplaint = null;
        fragmentMine.tvGo = null;
        fragmentMine.tv_dl = null;
        fragmentMine.tvYongj = null;
        fragmentMine.tvPhone = null;
        fragmentMine.llDengji = null;
        fragmentMine.tvDengji = null;
        fragmentMine.imDengji = null;
        fragmentMine.imDji = null;
    }
}