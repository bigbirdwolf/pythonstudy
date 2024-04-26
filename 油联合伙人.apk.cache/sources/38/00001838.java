package com.yltx.oil.partner.modules.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.ScrollViewPager;

/* loaded from: classes.dex */
public class MainActivity_ViewBinding implements Unbinder {
    private MainActivity target;

    @UiThread
    public MainActivity_ViewBinding(MainActivity mainActivity) {
        this(mainActivity, mainActivity.getWindow().getDecorView());
    }

    @UiThread
    public MainActivity_ViewBinding(MainActivity mainActivity, View view) {
        this.target = mainActivity;
        mainActivity.llMainMore = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.ll_main_more, "field 'llMainMore'", FrameLayout.class);
        mainActivity.scrollViewPager = (ScrollViewPager) Utils.findRequiredViewAsType(view, R.id.activity_main_fragment_vp, "field 'scrollViewPager'", ScrollViewPager.class);
        mainActivity.activityMainRadiobuttonHome = (RadioButton) Utils.findRequiredViewAsType(view, R.id.activity_main_radiobutton_home, "field 'activityMainRadiobuttonHome'", RadioButton.class);
        mainActivity.activityMainRadiobuttonOiltrade = (RadioButton) Utils.findRequiredViewAsType(view, R.id.activity_main_radiobutton_Oiltrade, "field 'activityMainRadiobuttonOiltrade'", RadioButton.class);
        mainActivity.activityMainRadiobuttonProfit = (RadioButton) Utils.findRequiredViewAsType(view, R.id.activity_main_radiobutton_Profit, "field 'activityMainRadiobuttonProfit'", RadioButton.class);
        mainActivity.activityMainRadiobuttonMine = (RadioButton) Utils.findRequiredViewAsType(view, R.id.activity_main_radiobutton_mine, "field 'activityMainRadiobuttonMine'", RadioButton.class);
        mainActivity.radiogroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.activity_main_radiogroup, "field 'radiogroup'", RadioGroup.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        MainActivity mainActivity = this.target;
        if (mainActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        mainActivity.llMainMore = null;
        mainActivity.scrollViewPager = null;
        mainActivity.activityMainRadiobuttonHome = null;
        mainActivity.activityMainRadiobuttonOiltrade = null;
        mainActivity.activityMainRadiobuttonProfit = null;
        mainActivity.activityMainRadiobuttonMine = null;
        mainActivity.radiogroup = null;
    }
}