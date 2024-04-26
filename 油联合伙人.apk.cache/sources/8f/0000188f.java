package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class FeedbackActivity_ViewBinding implements Unbinder {
    private FeedbackActivity target;

    @UiThread
    public FeedbackActivity_ViewBinding(FeedbackActivity feedbackActivity) {
        this(feedbackActivity, feedbackActivity.getWindow().getDecorView());
    }

    @UiThread
    public FeedbackActivity_ViewBinding(FeedbackActivity feedbackActivity, View view) {
        this.target = feedbackActivity;
        feedbackActivity.tvFeedback = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_feedback, "field 'tvFeedback'", TextView.class);
        feedbackActivity.tvMyfeedback = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_myfeedback, "field 'tvMyfeedback'", TextView.class);
        feedbackActivity.tvSub = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sub, "field 'tvSub'", TextView.class);
        feedbackActivity.llSubmit = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_submit, "field 'llSubmit'", LinearLayout.class);
        feedbackActivity.rlFeedback = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rl_feedback, "field 'rlFeedback'", RecyclerView.class);
        feedbackActivity.llList = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_list, "field 'llList'", LinearLayout.class);
        feedbackActivity.etContent = (EditText) Utils.findRequiredViewAsType(view, R.id.et_content, "field 'etContent'", EditText.class);
        feedbackActivity.etName = (EditText) Utils.findRequiredViewAsType(view, R.id.et_name, "field 'etName'", EditText.class);
        feedbackActivity.etPhone = (EditText) Utils.findRequiredViewAsType(view, R.id.et_phone, "field 'etPhone'", EditText.class);
        feedbackActivity.textViewe = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.textViewe, "field 'textViewe'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        FeedbackActivity feedbackActivity = this.target;
        if (feedbackActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        feedbackActivity.tvFeedback = null;
        feedbackActivity.tvMyfeedback = null;
        feedbackActivity.tvSub = null;
        feedbackActivity.llSubmit = null;
        feedbackActivity.rlFeedback = null;
        feedbackActivity.llList = null;
        feedbackActivity.etContent = null;
        feedbackActivity.etName = null;
        feedbackActivity.etPhone = null;
        feedbackActivity.textViewe = null;
    }
}