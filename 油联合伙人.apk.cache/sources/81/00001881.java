package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.ClipImageLayout;

/* loaded from: classes.dex */
public class ClipImageActivity_ViewBinding implements Unbinder {
    private ClipImageActivity target;
    private View view2131296481;
    private View view2131296482;

    @UiThread
    public ClipImageActivity_ViewBinding(ClipImageActivity clipImageActivity) {
        this(clipImageActivity, clipImageActivity.getWindow().getDecorView());
    }

    @UiThread
    public ClipImageActivity_ViewBinding(final ClipImageActivity clipImageActivity, View view) {
        this.target = clipImageActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.head_left_image, "field 'headLeftImage' and method 'onViewClicked'");
        clipImageActivity.headLeftImage = (ImageView) Utils.castView(findRequiredView, R.id.head_left_image, "field 'headLeftImage'", ImageView.class);
        this.view2131296481 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.mine.activity.ClipImageActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                clipImageActivity.onViewClicked(view2);
            }
        });
        clipImageActivity.headTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.head_title, "field 'headTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.head_rigt, "field 'headRigt' and method 'onViewClicked'");
        clipImageActivity.headRigt = (TextView) Utils.castView(findRequiredView2, R.id.head_rigt, "field 'headRigt'", TextView.class);
        this.view2131296482 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yltx.oil.partner.modules.mine.activity.ClipImageActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                clipImageActivity.onViewClicked(view2);
            }
        });
        clipImageActivity.clipImageLayout = (ClipImageLayout) Utils.findRequiredViewAsType(view, R.id.clip_image_layout, "field 'clipImageLayout'", ClipImageLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ClipImageActivity clipImageActivity = this.target;
        if (clipImageActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        clipImageActivity.headLeftImage = null;
        clipImageActivity.headTitle = null;
        clipImageActivity.headRigt = null;
        clipImageActivity.clipImageLayout = null;
        this.view2131296481.setOnClickListener(null);
        this.view2131296481 = null;
        this.view2131296482.setOnClickListener(null);
        this.view2131296482 = null;
    }
}