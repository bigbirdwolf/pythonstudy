package com.yltx.oil.partner.modules.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.StateActivity;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class ApplyingPartnerActivity extends StateActivity {
    @BindView(R.id.head_left_image)
    ImageView headLeftImage;
    @BindView(R.id.head_title)
    TextView headTitle;

    protected void setupUI() {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ApplyingPartnerActivity.class);
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_applying_partner);
        ButterKnife.bind(this);
        this.headTitle.setText("申请合伙人");
        bindListener();
    }

    protected void bindListener() {
        Rx.click(this.headLeftImage, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ApplyingPartnerActivity$NsdlMW_KF6ZDgRYYNRkRhjlpp4g
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ApplyingPartnerActivity.lambda$bindListener$0(ApplyingPartnerActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(ApplyingPartnerActivity applyingPartnerActivity, Void r1) {
        applyingPartnerActivity.finish();
    }
}