package com.yltx.oil.partner.modules.profit.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class CollectionRecordActivity_ViewBinding implements Unbinder {
    private CollectionRecordActivity target;

    @UiThread
    public CollectionRecordActivity_ViewBinding(CollectionRecordActivity collectionRecordActivity) {
        this(collectionRecordActivity, collectionRecordActivity.getWindow().getDecorView());
    }

    @UiThread
    public CollectionRecordActivity_ViewBinding(CollectionRecordActivity collectionRecordActivity, View view) {
        this.target = collectionRecordActivity;
        collectionRecordActivity.skjlRadiobuttonSydd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.skjl_radiobutton_sydd, "field 'skjlRadiobuttonSydd'", RadioButton.class);
        collectionRecordActivity.skjlRadiobuttonYxdd = (RadioButton) Utils.findRequiredViewAsType(view, R.id.skjl_radiobutton_yxdd, "field 'skjlRadiobuttonYxdd'", RadioButton.class);
        collectionRecordActivity.skjlViewpager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.skjl_viewpager, "field 'skjlViewpager'", ViewPager.class);
        collectionRecordActivity.skjlRadiogroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.skjl_radiogroup, "field 'skjlRadiogroup'", RadioGroup.class);
        collectionRecordActivity.skjl2MoneyShu = (TextView) Utils.findRequiredViewAsType(view, R.id.skjl2_money_shu, "field 'skjl2MoneyShu'", TextView.class);
        collectionRecordActivity.skjl2Sk = (TextView) Utils.findRequiredViewAsType(view, R.id.skjl2_sk, "field 'skjl2Sk'", TextView.class);
        collectionRecordActivity.skjl2SkMoney = (TextView) Utils.findRequiredViewAsType(view, R.id.skjl2_sk_money, "field 'skjl2SkMoney'", TextView.class);
        collectionRecordActivity.skjl2Tk = (TextView) Utils.findRequiredViewAsType(view, R.id.skjl2_tk, "field 'skjl2Tk'", TextView.class);
        collectionRecordActivity.skjl2SkMoney2 = (TextView) Utils.findRequiredViewAsType(view, R.id.skjl2_sk_money2, "field 'skjl2SkMoney2'", TextView.class);
        collectionRecordActivity.skjl2Year = (TextView) Utils.findRequiredViewAsType(view, R.id.skjl2_year, "field 'skjl2Year'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CollectionRecordActivity collectionRecordActivity = this.target;
        if (collectionRecordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        collectionRecordActivity.skjlRadiobuttonSydd = null;
        collectionRecordActivity.skjlRadiobuttonYxdd = null;
        collectionRecordActivity.skjlViewpager = null;
        collectionRecordActivity.skjlRadiogroup = null;
        collectionRecordActivity.skjl2MoneyShu = null;
        collectionRecordActivity.skjl2Sk = null;
        collectionRecordActivity.skjl2SkMoney = null;
        collectionRecordActivity.skjl2Tk = null;
        collectionRecordActivity.skjl2SkMoney2 = null;
        collectionRecordActivity.skjl2Year = null;
    }
}