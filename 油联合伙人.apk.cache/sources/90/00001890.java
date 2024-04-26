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
public class HelpCenterActivity extends ToolBarActivity {
    @BindView(R.id.ll_Basic_problems)
    LinearLayout llBasicProblems;
    @BindView(R.id.ll_Commission_interpretation)
    LinearLayout llCommissionInterpretation;
    @BindView(R.id.ll_extensive_construction)
    LinearLayout llExtensiveConstruction;
    @BindView(R.id.ll_Noun_interpretation)
    LinearLayout llNounInterpretation;
    @BindView(R.id.ll_Privacy_statement)
    LinearLayout llPrivacyStatement;

    public static /* synthetic */ void lambda$bindListener$0(Void r0) {
    }

    public static /* synthetic */ void lambda$bindListener$1(Void r0) {
    }

    public static /* synthetic */ void lambda$bindListener$2(Void r0) {
    }

    public static /* synthetic */ void lambda$bindListener$3(Void r0) {
    }

    public static /* synthetic */ void lambda$bindListener$4(Void r0) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HelpCenterActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        setToolbarTitle("帮助中心");
    }

    protected void bindListener() {
        Rx.click(this.llPrivacyStatement, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$HelpCenterActivity$XtCpPJdGVCyEbBWdsoU9bkWLHd0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HelpCenterActivity.lambda$bindListener$0((Void) obj);
            }
        });
        Rx.click(this.llCommissionInterpretation, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$HelpCenterActivity$vwMaHpNEQcGpgMgqa-su8wegmo8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HelpCenterActivity.lambda$bindListener$1((Void) obj);
            }
        });
        Rx.click(this.llBasicProblems, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$HelpCenterActivity$E4JC7AbMJWb-68AGaTbRRZWTX7Q
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HelpCenterActivity.lambda$bindListener$2((Void) obj);
            }
        });
        Rx.click(this.llExtensiveConstruction, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$HelpCenterActivity$DnnX-7bnruQpy9J5p8GFiQmg_S4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HelpCenterActivity.lambda$bindListener$3((Void) obj);
            }
        });
        Rx.click(this.llNounInterpretation, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$HelpCenterActivity$2RwIykQgdPOLBNGl2GHR2BwXRe4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HelpCenterActivity.lambda$bindListener$4((Void) obj);
            }
        });
    }
}