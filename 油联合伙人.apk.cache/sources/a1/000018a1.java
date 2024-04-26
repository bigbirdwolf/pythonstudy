package com.yltx.oil.partner.modules.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.mine.presenter.ModifPresenter;
import com.yltx.oil.partner.modules.mine.view.ModifyusernicknamesView;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class ModifyNicknameActivity extends ToolBarActivity implements ModifyusernicknamesView {
    @BindView(R.id.activity_basic_message_nickname)
    EditText activityBasicMessageNickname;
    @Inject
    ModifPresenter modifPresenter;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Override // com.yltx.oil.partner.modules.mine.view.ModifyusernicknamesView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ModifyNicknameActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_modify_nickname);
        ButterKnife.bind(this);
        this.modifPresenter.attachView(this);
        setupUI();
        bindListener();
    }

    private void bindListener() {
        Rx.click(this.tvSubmit, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ModifyNicknameActivity$wFSa0FSdVV8RX6W_4vOs2jwU4r4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ModifyNicknameActivity.lambda$bindListener$0(ModifyNicknameActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(ModifyNicknameActivity modifyNicknameActivity, Void r2) {
        modifyNicknameActivity.modifPresenter.submitMember(modifyNicknameActivity.activityBasicMessageNickname.getText().toString());
    }

    private void setupUI() {
        setToolbarTitle("修改昵称");
    }

    @Override // com.yltx.oil.partner.modules.mine.view.ModifyusernicknamesView
    public void onModif(HttpResult<String> httpResult) {
        finish();
    }
}