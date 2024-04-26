package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.modules.profit.adapter.Order_FragmentPager_Adapter;
import com.yltx.oil.partner.modules.profit.fragment.AllordersFragment;
import com.yltx.oil.partner.modules.profit.fragment.EffectiveorderFragment;
import com.yltx.oil.partner.modules.profit.fragment.FailureoftheorderFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class OrderdetailsActivity extends ToolBarActivity {
    @BindView(R.id.ddmx_radiobutton_sxdd)
    RadioButton ddmxRadiobuttonSxdd;
    @BindView(R.id.ddmx_radiobutton_sydd)
    RadioButton ddmxRadiobuttonSydd;
    @BindView(R.id.ddmx_radiobutton_yxdd)
    RadioButton ddmxRadiobuttonYxdd;
    @BindView(R.id.ddmx_radiogroup)
    RadioGroup ddmxRadiogroup;
    @BindView(R.id.ddmx_viewpager)
    ViewPager ddmxViewpager;
    private FragmentTransaction fragmentTransaction;
    private List<Fragment> list;

    private void bindListener() {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, OrderdetailsActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_orderdetails);
        ButterKnife.bind(this);
        this.fragmentTransaction = getSupportFragmentManager().beginTransaction();
        this.list = new ArrayList();
        this.list.add(AllordersFragment.newInstance());
        this.list.add(EffectiveorderFragment.newInstance());
        this.list.add(FailureoftheorderFragment.newInstance());
        this.ddmxViewpager.setAdapter(new Order_FragmentPager_Adapter(getSupportFragmentManager(), this.list));
        setupUI();
        bindListener();
        viewpagerlistener();
        radiogrouplistener();
    }

    private void setupUI() {
        setToolbarTitle("订单明细");
    }

    private void viewpagerlistener() {
        this.ddmxViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yltx.oil.partner.modules.profit.activity.OrderdetailsActivity.1
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        OrderdetailsActivity.this.ddmxRadiogroup.check(R.id.ddmx_radiobutton_sydd);
                        return;
                    case 1:
                        OrderdetailsActivity.this.ddmxRadiogroup.check(R.id.ddmx_radiobutton_yxdd);
                        return;
                    case 2:
                        OrderdetailsActivity.this.ddmxRadiogroup.check(R.id.ddmx_radiobutton_sxdd);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void radiogrouplistener() {
        this.ddmxRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yltx.oil.partner.modules.profit.activity.OrderdetailsActivity.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.ddmx_radiobutton_sxdd /* 2131296390 */:
                        OrderdetailsActivity.this.ddmxViewpager.setCurrentItem(2, true);
                        return;
                    case R.id.ddmx_radiobutton_sydd /* 2131296391 */:
                        OrderdetailsActivity.this.ddmxViewpager.setCurrentItem(0, true);
                        return;
                    case R.id.ddmx_radiobutton_yxdd /* 2131296392 */:
                        OrderdetailsActivity.this.ddmxViewpager.setCurrentItem(1, true);
                        return;
                    default:
                        return;
                }
            }
        });
    }
}