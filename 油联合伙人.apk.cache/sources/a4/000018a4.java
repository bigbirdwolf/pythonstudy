package com.yltx.oil.partner.modules.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class NoviceGuideActivity extends ToolBarActivity {
    @BindView(R.id.ll_Commission_withdrawal)
    LinearLayout llCommissionWithdrawal;
    @BindView(R.id.ll_Function_introduction)
    LinearLayout llFunctionIntroduction;
    @BindView(R.id.ll_List_problems)
    LinearLayout llListProblems;
    @BindView(R.id.ll_Share_in_makingmoney)
    LinearLayout llShareInMakingmoney;

    public static /* synthetic */ void lambda$bindListener$0(Void r0) {
    }

    public static /* synthetic */ void lambda$bindListener$1(Void r0) {
    }

    public static /* synthetic */ void lambda$bindListener$2(Void r0) {
    }

    public static /* synthetic */ void lambda$bindListener$3(Void r0) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, NoviceGuideActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_novice_guide);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        setToolbarTitle("新手指引");
    }

    protected void bindListener() {
        Rx.click(this.llFunctionIntroduction, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$NoviceGuideActivity$UbqPicGTJvtsXkCCRMoSVtwHWOs
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NoviceGuideActivity.lambda$bindListener$0((Void) obj);
            }
        });
        Rx.click(this.llCommissionWithdrawal, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$NoviceGuideActivity$Hs_eDQZywZmdrvXXbr2BuFx2iqY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NoviceGuideActivity.lambda$bindListener$1((Void) obj);
            }
        });
        Rx.click(this.llListProblems, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$NoviceGuideActivity$zu9SMvvMUUc3oGvxsUXb-_KN9Gc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NoviceGuideActivity.lambda$bindListener$2((Void) obj);
            }
        });
        Rx.click(this.llShareInMakingmoney, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$NoviceGuideActivity$oWyaWP_fMwl5KlJd8BV69OjAqpA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NoviceGuideActivity.lambda$bindListener$3((Void) obj);
            }
        });
    }
}