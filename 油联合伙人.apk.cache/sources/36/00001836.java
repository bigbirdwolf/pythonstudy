package com.yltx.oil.partner.modules.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.StateActivity;
import com.yltx.oil.partner.modules.home.fragment.FragmentHome;
import com.yltx.oil.partner.modules.main.adapter.AdapterFragmentViewPager;
import com.yltx.oil.partner.modules.mine.fragment.FragmentMine;
import com.yltx.oil.partner.modules.oiltrade.fragment.FragmentOilTrade;
import com.yltx.oil.partner.modules.profit.fragment.FragmentProfit;
import com.yltx.oil.partner.widget.ScrollViewPager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MainActivity extends StateActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.activity_main_radiobutton_home)
    RadioButton activityMainRadiobuttonHome;
    @BindView(R.id.activity_main_radiobutton_mine)
    RadioButton activityMainRadiobuttonMine;
    @BindView(R.id.activity_main_radiobutton_Oiltrade)
    RadioButton activityMainRadiobuttonOiltrade;
    @BindView(R.id.activity_main_radiobutton_Profit)
    RadioButton activityMainRadiobuttonProfit;
    private AdapterFragmentViewPager adapter;
    private SharedPreferences.Editor editor;
    private int index;
    @BindView(R.id.ll_main_more)
    FrameLayout llMainMore;
    private SharedPreferences preferences;
    @BindView(R.id.activity_main_radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.activity_main_fragment_vp)
    ScrollViewPager scrollViewPager;

    protected void bindListener() {
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.mvp.views.InterfaceView
    public Context getContext() {
        return this;
    }

    public static Intent getCallingIntent(Context context, int i) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("indext", i);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBackExit(true);
        this.index = getIntent().getIntExtra("index", 0);
        setupUI();
    }

    protected void setupUI() {
        this.radiogroup.setOnCheckedChangeListener(this);
        this.adapter = new AdapterFragmentViewPager(getSupportFragmentManager(), getFragments());
        this.scrollViewPager.setAdapter(this.adapter);
        this.scrollViewPager.setEnableScroll(false);
        this.scrollViewPager.setOffscreenPageLimit(3);
    }

    public void performClick(int i, String str) {
        switch (i) {
            case 0:
                this.activityMainRadiobuttonHome.setChecked(true);
                return;
            case 1:
                this.activityMainRadiobuttonOiltrade.setChecked(true);
                ((FragmentOilTrade) this.adapter.getItem(1)).setindext(Integer.parseInt(str));
                return;
            case 2:
                this.activityMainRadiobuttonProfit.setChecked(true);
                return;
            case 3:
                this.activityMainRadiobuttonMine.setChecked(true);
                return;
            default:
                return;
        }
    }

    private List<Fragment> getFragments() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(FragmentHome.newInstance());
        arrayList.add(FragmentOilTrade.newInstance());
        arrayList.add(FragmentProfit.newInstance());
        arrayList.add(FragmentMine.newInstance());
        return arrayList;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.activity_main_radiobutton_Oiltrade /* 2131296297 */:
                this.scrollViewPager.setCurrentItem(1, false);
                performClick(1, "0");
                return;
            case R.id.activity_main_radiobutton_Profit /* 2131296298 */:
                this.scrollViewPager.setCurrentItem(2, false);
                return;
            case R.id.activity_main_radiobutton_home /* 2131296299 */:
                this.scrollViewPager.setCurrentItem(0, false);
                return;
            case R.id.activity_main_radiobutton_mine /* 2131296300 */:
                this.scrollViewPager.setCurrentItem(3, false);
                return;
            default:
                return;
        }
    }
}