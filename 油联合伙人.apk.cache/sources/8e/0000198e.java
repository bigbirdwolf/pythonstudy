package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.modules.profit.adapter.Order_FragmentPager_Adapter;
import com.yltx.oil.partner.modules.profit.fragment.AllFragment;
import com.yltx.oil.partner.modules.profit.fragment.RefundFragment;
import com.yltx.oil.partner.modules.profit.presenter.SettlementRecordsPresenter;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class CollectionRecordActivity extends ToolBarActivity implements PageLimitView<ManageResponse> {
    private FragmentTransaction fragmentTransaction;
    private List<Fragment> list;
    @Inject
    SettlementRecordsPresenter settlementRecordsPresenter;
    @BindView(R.id.skjl2_money_shu)
    TextView skjl2MoneyShu;
    @BindView(R.id.skjl2_sk)
    TextView skjl2Sk;
    @BindView(R.id.skjl2_sk_money)
    TextView skjl2SkMoney;
    @BindView(R.id.skjl2_sk_money2)
    TextView skjl2SkMoney2;
    @BindView(R.id.skjl2_tk)
    TextView skjl2Tk;
    @BindView(R.id.skjl2_year)
    TextView skjl2Year;
    @BindView(R.id.skjl_radiobutton_sydd)
    RadioButton skjlRadiobuttonSydd;
    @BindView(R.id.skjl_radiobutton_yxdd)
    RadioButton skjlRadiobuttonYxdd;
    @BindView(R.id.skjl_radiogroup)
    RadioGroup skjlRadiogroup;
    @BindView(R.id.skjl_viewpager)
    ViewPager skjlViewpager;

    private void bindListener() {
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(ManageResponse manageResponse) {
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(ManageResponse manageResponse) {
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CollectionRecordActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_skjl2);
        ButterKnife.bind(this);
        this.settlementRecordsPresenter.attachView(this);
        this.fragmentTransaction = getSupportFragmentManager().beginTransaction();
        this.list = new ArrayList();
        this.list.add(AllFragment.newInstance());
        this.list.add(RefundFragment.newInstance());
        this.skjlViewpager.setAdapter(new Order_FragmentPager_Adapter(getSupportFragmentManager(), this.list));
        setupUI();
        bindListener();
        viewpagerlistener();
        radiogrouplistener();
        this.settlementRecordsPresenter.fetchFirst();
    }

    private void setupUI() {
        setToolbarTitle("收款记录");
    }

    private void viewpagerlistener() {
        this.skjlViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.yltx.oil.partner.modules.profit.activity.CollectionRecordActivity.1
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
                        CollectionRecordActivity.this.skjlRadiogroup.check(R.id.skjl_radiobutton_sydd);
                        return;
                    case 1:
                        CollectionRecordActivity.this.skjlRadiogroup.check(R.id.skjl_radiobutton_yxdd);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void radiogrouplistener() {
        this.skjlRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yltx.oil.partner.modules.profit.activity.CollectionRecordActivity.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.ddmx_radiobutton_sydd /* 2131296391 */:
                        CollectionRecordActivity.this.skjlViewpager.setCurrentItem(0, true);
                        return;
                    case R.id.ddmx_radiobutton_yxdd /* 2131296392 */:
                        CollectionRecordActivity.this.skjlViewpager.setCurrentItem(1, true);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(ManageResponse manageResponse) {
        TextView textView = this.skjl2MoneyShu;
        textView.setText(manageResponse.getStationData().getRealpayAmount() + "");
        TextView textView2 = this.skjl2Sk;
        textView2.setText(manageResponse.getStationData().getGetCount() + "");
        TextView textView3 = this.skjl2Tk;
        textView3.setText(manageResponse.getStationData().getCancelCount() + "");
        TextView textView4 = this.skjl2Year;
        textView4.setText(manageResponse.getStationData().getYear() + "");
        TextView textView5 = this.skjl2SkMoney;
        textView5.setText(manageResponse.getStationData().getGetAmount() + "");
        TextView textView6 = this.skjl2SkMoney2;
        textView6.setText(manageResponse.getStationData().getCancelAmount() + "");
    }
}