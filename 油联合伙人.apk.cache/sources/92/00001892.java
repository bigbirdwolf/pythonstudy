package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class HelpCenterActivity_ViewBinding implements Unbinder {
    private HelpCenterActivity target;

    @UiThread
    public HelpCenterActivity_ViewBinding(HelpCenterActivity helpCenterActivity) {
        this(helpCenterActivity, helpCenterActivity.getWindow().getDecorView());
    }

    @UiThread
    public HelpCenterActivity_ViewBinding(HelpCenterActivity helpCenterActivity, View view) {
        this.target = helpCenterActivity;
        helpCenterActivity.llPrivacyStatement = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_Privacy_statement, "field 'llPrivacyStatement'", LinearLayout.class);
        helpCenterActivity.llNounInterpretation = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_Noun_interpretation, "field 'llNounInterpretation'", LinearLayout.class);
        helpCenterActivity.llBasicProblems = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_Basic_problems, "field 'llBasicProblems'", LinearLayout.class);
        helpCenterActivity.llExtensiveConstruction = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_extensive_construction, "field 'llExtensiveConstruction'", LinearLayout.class);
        helpCenterActivity.llCommissionInterpretation = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_Commission_interpretation, "field 'llCommissionInterpretation'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        HelpCenterActivity helpCenterActivity = this.target;
        if (helpCenterActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        helpCenterActivity.llPrivacyStatement = null;
        helpCenterActivity.llNounInterpretation = null;
        helpCenterActivity.llBasicProblems = null;
        helpCenterActivity.llExtensiveConstruction = null;
        helpCenterActivity.llCommissionInterpretation = null;
    }
}