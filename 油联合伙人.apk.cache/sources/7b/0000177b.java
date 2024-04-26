package com.yltx.oil.partner.modules.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.adapter.MessageNotificationRecyclerView_Adapter;
import com.yltx.oil.partner.modules.home.presenter.MessageNotificationPresenter;
import com.yltx.oil.partner.modules.home.response.MessageNotificationResponse;
import com.yltx.oil.partner.modules.home.view.MessageNotificationView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class MessageHomeActivity extends ToolBarActivity implements MessageNotificationView, BaseQuickAdapter.OnItemClickListener {
    MessageNotificationRecyclerView_Adapter adapter;
    List<MessageNotificationResponse> arrayList = new ArrayList();
    @BindView(R.id.message_home_recyclerview)
    RecyclerView messageHomeRecyclerview;
    @Inject
    MessageNotificationPresenter messageNotificationPresenter;

    protected void bindListener() {
    }

    @Override // com.yltx.oil.partner.modules.home.view.MessageNotificationView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MessageHomeActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_message_home);
        ButterKnife.bind(this);
        this.messageNotificationPresenter.attachView(this);
        this.messageNotificationPresenter.messagesubmit(0);
        this.adapter = new MessageNotificationRecyclerView_Adapter(this.arrayList);
        this.adapter.setOnItemClickListener(this);
        this.messageHomeRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.messageHomeRecyclerview.setAdapter(this.adapter);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        setToolbarTitle("消息");
    }

    @Override // com.yltx.oil.partner.modules.home.view.MessageNotificationView
    public void onMember(HttpResult<List<MessageNotificationResponse>> httpResult) {
        if (httpResult.getData() == null || httpResult.getData().size() <= 0) {
            return;
        }
        this.arrayList = httpResult.getData();
        this.adapter.addData((List) this.arrayList);
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        startActivity(MessageDetailsActivity.getCallingIntent(this, this.adapter.getData().get(i).getRowid()));
    }
}