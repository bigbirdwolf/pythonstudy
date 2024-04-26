package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class NoviceGuideActivity_ViewBinding implements Unbinder {
    private NoviceGuideActivity target;

    @UiThread
    public NoviceGuideActivity_ViewBinding(NoviceGuideActivity noviceGuideActivity) {
        this(noviceGuideActivity, noviceGuideActivity.getWindow().getDecorView());
    }

    @UiThread
    public NoviceGuideActivity_ViewBinding(NoviceGuideActivity noviceGuideActivity, View view) {
        this.target = noviceGuideActivity;
        noviceGuideActivity.llFunctionIntroduction = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_Function_introduction, "field 'llFunctionIntroduction'", LinearLayout.class);
        noviceGuideActivity.llShareInMakingmoney = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_Share_in_makingmoney, "field 'llShareInMakingmoney'", LinearLayout.class);
        noviceGuideActivity.llCommissionWithdrawal = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_Commission_withdrawal, "field 'llCommissionWithdrawal'", LinearLayout.class);
        noviceGuideActivity.llListProblems = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_List_problems, "field 'llListProblems'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        NoviceGuideActivity noviceGuideActivity = this.target;
        if (noviceGuideActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        noviceGuideActivity.llFunctionIntroduction = null;
        noviceGuideActivity.llShareInMakingmoney = null;
        noviceGuideActivity.llCommissionWithdrawal = null;
        noviceGuideActivity.llListProblems = null;
    }
}