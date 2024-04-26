package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class InvitationDetailsActivity_ViewBinding implements Unbinder {
    private InvitationDetailsActivity target;

    @UiThread
    public InvitationDetailsActivity_ViewBinding(InvitationDetailsActivity invitationDetailsActivity) {
        this(invitationDetailsActivity, invitationDetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public InvitationDetailsActivity_ViewBinding(InvitationDetailsActivity invitationDetailsActivity, View view) {
        this.target = invitationDetailsActivity;
        invitationDetailsActivity.rlInvitation = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rl_invitation, "field 'rlInvitation'", RecyclerView.class);
        invitationDetailsActivity.expandableListView = (ExpandableListView) Utils.findRequiredViewAsType(view, R.id.expandableListView, "field 'expandableListView'", ExpandableListView.class);
        invitationDetailsActivity.tvYxhhr = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_yxhhr, "field 'tvYxhhr'", TextView.class);
        invitationDetailsActivity.tvLjyq = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ljyq, "field 'tvLjyq'", TextView.class);
        invitationDetailsActivity.tvLjzc = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ljzc, "field 'tvLjzc'", TextView.class);
        invitationDetailsActivity.tvLjgm = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ljgm, "field 'tvLjgm'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        InvitationDetailsActivity invitationDetailsActivity = this.target;
        if (invitationDetailsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        invitationDetailsActivity.rlInvitation = null;
        invitationDetailsActivity.expandableListView = null;
        invitationDetailsActivity.tvYxhhr = null;
        invitationDetailsActivity.tvLjyq = null;
        invitationDetailsActivity.tvLjzc = null;
        invitationDetailsActivity.tvLjgm = null;
    }
}