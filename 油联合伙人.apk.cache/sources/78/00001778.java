package com.yltx.oil.partner.modules.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.presenter.MessageForDetailsPresenter;
import com.yltx.oil.partner.modules.home.response.MessageForDetailsResponse;
import com.yltx.oil.partner.modules.home.view.MessageForDetailsView;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class MessageDetailsActivity extends ToolBarActivity implements MessageForDetailsView {
    @BindView(R.id.details_content)
    TextView detailsContent;
    @BindView(R.id.details_title)
    TextView detailsTitle;
    @Inject
    MessageForDetailsPresenter messageForDetailsPresenter;
    int rowid;

    protected void bindListener() {
    }

    @Override // com.yltx.oil.partner.modules.home.view.MessageForDetailsView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context, String str) {
        Intent intent = new Intent(context, MessageDetailsActivity.class);
        intent.putExtra("rowid", str);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_message_details);
        this.messageForDetailsPresenter.attachView(this);
        this.rowid = Integer.parseInt(getIntent().getStringExtra("rowid"));
        this.messageForDetailsPresenter.messagesubmit(this.rowid);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        setToolbarTitle("消息详情");
    }

    @Override // com.yltx.oil.partner.modules.home.view.MessageForDetailsView
    public void onMember(HttpResult<MessageForDetailsResponse> httpResult) {
        TextView textView = this.detailsTitle;
        textView.setText(httpResult.getData().getTitle() + "");
        TextView textView2 = this.detailsContent;
        textView2.setText(httpResult.getData().getContent() + "");
    }
}