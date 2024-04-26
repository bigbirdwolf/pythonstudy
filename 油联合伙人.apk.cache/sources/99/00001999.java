package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.utils.LunarCalendar;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.StateActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.EventBus;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class OptiondateActivity extends StateActivity {
    private String date_fa = null;
    private SimpleDateFormat df;
    private Calendar endDate;
    @BindView(R.id.head_left_image)
    ImageView headLeftImage;
    @BindView(R.id.head_title)
    TextView headTitle;
    private OptionsPickerView pvOptions;
    private TimePickerView pvTime;
    private Calendar startDate;
    private String string;
    @BindView(R.id.sy_xzsj_bt)
    Button syXzsjBt;
    @BindView(R.id.sy_xzsj_jssj_wu)
    RelativeLayout syXzsjJssjWu;
    @BindView(R.id.sy_xzsj_kssj_wu)
    RelativeLayout syXzsjKssjWu;
    @BindView(R.id.tv_xzsj_jssj_nyr)
    TextView tvXzsjJssjNyr;
    @BindView(R.id.tv_xzsj_kssj_nyr)
    TextView tvXzsjKssjNyr;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, OptiondateActivity.class);
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_optiondate);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
    }

    private void setupUI() {
        this.headTitle.setText("选择日期");
    }

    private void bindListener() {
        Rx.click(this.headLeftImage, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$OptiondateActivity$19QAD_z3AM16Xjv0qudDWBKLc-s
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                OptiondateActivity.lambda$bindListener$0(OptiondateActivity.this, (Void) obj);
            }
        });
        Rx.click(this.syXzsjKssjWu, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$OptiondateActivity$WLIYm-rW7I82Biy4xNotB3gco80
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                OptiondateActivity.lambda$bindListener$1(OptiondateActivity.this, (Void) obj);
            }
        });
        Rx.click(this.syXzsjJssjWu, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$OptiondateActivity$GmExH7uwnvKysWWVcjwy2TmEXQU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                OptiondateActivity.lambda$bindListener$2(OptiondateActivity.this, (Void) obj);
            }
        });
        Rx.click(this.syXzsjBt, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$OptiondateActivity$u-qTHfOWbk7sgdrEiNj5QUpPYtk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                OptiondateActivity.lambda$bindListener$3(OptiondateActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(OptiondateActivity optiondateActivity, Void r1) {
        optiondateActivity.finish();
    }

    public static /* synthetic */ void lambda$bindListener$1(OptiondateActivity optiondateActivity, Void r2) {
        optiondateActivity.initTimePicker();
        optiondateActivity.pvTime.show(optiondateActivity.syXzsjKssjWu);
    }

    public static /* synthetic */ void lambda$bindListener$2(OptiondateActivity optiondateActivity, Void r2) {
        optiondateActivity.initTimePicker();
        optiondateActivity.pvTime.show(optiondateActivity.syXzsjJssjWu);
    }

    public static /* synthetic */ void lambda$bindListener$3(OptiondateActivity optiondateActivity, Void r2) {
        optiondateActivity.finish();
        optiondateActivity.date_fa = optiondateActivity.tvXzsjKssjNyr.getText().toString().trim() + "一" + optiondateActivity.tvXzsjJssjNyr.getText().toString().trim();
        if (optiondateActivity.hasDigit(optiondateActivity.tvXzsjKssjNyr.getText().toString().trim())) {
            if (optiondateActivity.hasDigit(optiondateActivity.tvXzsjJssjNyr.getText().toString().trim())) {
                EventBus.getDefault().post(optiondateActivity.date_fa);
                return;
            } else {
                EventBus.getDefault().post("");
                return;
            }
        }
        EventBus.getDefault().post("");
    }

    public boolean hasDigit(String str) {
        return Pattern.compile(".*\\d+.*").matcher(str).matches();
    }

    private void initTimePicker() {
        this.startDate = Calendar.getInstance();
        this.endDate = Calendar.getInstance();
        Log.d(">>>>endDate>>>>>", this.endDate + "");
        this.startDate.set(LunarCalendar.MIN_YEAR, 0, 0);
        this.pvTime = new TimePickerBuilder(this, new MainTimeSelectListener()).setType(new boolean[]{true, true, true, false, false, false}).setTitleText("请选择时间").setContentTextSize(16).setCancelText("取消").setSubmitText("确认").setContentTextSize(16).setSubCalSize(16).setCancelColor(getContext().getResources().getColor(R.color.colorPrimaryDark)).setSubmitColor(getContext().getResources().getColor(R.color.colorPrimaryDark)).setOutSideCancelable(false).isCyclic(false).setDate(this.endDate).setRangDate(this.startDate, this.endDate).isCenterLabel(false).setLabel("年", "月", "日", "时", "分", "秒").isDialog(false).build();
    }

    /* loaded from: classes.dex */
    public class MainTimeSelectListener implements OnTimeSelectListener {
        MainTimeSelectListener() {
            OptiondateActivity.this = r1;
        }

        @Override // com.bigkoo.pickerview.listener.OnTimeSelectListener
        public void onTimeSelect(Date date, View view) {
            OptiondateActivity.this.df = new SimpleDateFormat("yyyy-MM-dd");
            switch (view.getId()) {
                case R.id.sy_xzsj_jssj_wu /* 2131296780 */:
                    OptiondateActivity.this.string = OptiondateActivity.this.df.format(date);
                    OptiondateActivity.this.tvXzsjJssjNyr.setText(OptiondateActivity.this.string);
                    return;
                case R.id.sy_xzsj_kssj_wu /* 2131296781 */:
                    OptiondateActivity.this.string = OptiondateActivity.this.df.format(date);
                    OptiondateActivity.this.tvXzsjKssjNyr.setText(OptiondateActivity.this.string);
                    return;
                default:
                    return;
            }
        }
    }
}