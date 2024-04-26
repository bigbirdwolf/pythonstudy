package com.yltx.oil.partner.modules.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class ClassificationActivity_ViewBinding implements Unbinder {
    private ClassificationActivity target;

    @UiThread
    public ClassificationActivity_ViewBinding(ClassificationActivity classificationActivity) {
        this(classificationActivity, classificationActivity.getWindow().getDecorView());
    }

    @UiThread
    public ClassificationActivity_ViewBinding(ClassificationActivity classificationActivity, View view) {
        this.target = classificationActivity;
        classificationActivity.headLeftImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.head_left_image, "field 'headLeftImage'", ImageView.class);
        classificationActivity.headTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.head_title, "field 'headTitle'", TextView.class);
        classificationActivity.oiltrabeLayoutTitle = (TabLayout) Utils.findRequiredViewAsType(view, R.id.oiltrabe_layout_title, "field 'oiltrabeLayoutTitle'", TabLayout.class);
        classificationActivity.vpOiltrade = (ViewPager) Utils.findRequiredViewAsType(view, R.id.vp_oiltrade, "field 'vpOiltrade'", ViewPager.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ClassificationActivity classificationActivity = this.target;
        if (classificationActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        classificationActivity.headLeftImage = null;
        classificationActivity.headTitle = null;
        classificationActivity.oiltrabeLayoutTitle = null;
        classificationActivity.vpOiltrade = null;
    }
}