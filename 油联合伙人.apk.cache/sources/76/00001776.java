package com.yltx.oil.partner.modules.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.StateActivity;

/* loaded from: classes.dex */
public class MessageBulletinActivity extends StateActivity {
    protected void bindListener() {
    }

    protected void setupUI() {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MessageBulletinActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_message_bulletin);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
    }
}