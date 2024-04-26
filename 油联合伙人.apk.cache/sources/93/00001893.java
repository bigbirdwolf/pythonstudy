package com.yltx.oil.partner.modules.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.InviteDetailResp;
import com.yltx.oil.partner.modules.mine.adapter.InvitationRecyclerAdapter;
import com.yltx.oil.partner.modules.mine.adapter.MyExpandableListView;
import com.yltx.oil.partner.modules.mine.presenter.InviteDetailPresenter;
import com.yltx.oil.partner.modules.mine.view.InviteDetailView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class InvitationDetailsActivity extends ToolBarActivity implements InviteDetailView {
    private MyExpandableListView expandableListAdapter;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    List<InviteDetailResp.ListInfoBeanX> listInfo;
    private InvitationRecyclerAdapter mAdapter;
    @Inject
    public InviteDetailPresenter presenter;
    @BindView(R.id.rl_invitation)
    RecyclerView rlInvitation;
    @BindView(R.id.tv_ljgm)
    TextView tvLjgm;
    @BindView(R.id.tv_ljyq)
    TextView tvLjyq;
    @BindView(R.id.tv_ljzc)
    TextView tvLjzc;
    @BindView(R.id.tv_yxhhr)
    TextView tvYxhhr;

    @Override // com.yltx.oil.partner.modules.mine.view.InviteDetailView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, InvitationDetailsActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_invitation_details);
        ButterKnife.bind(this);
        this.presenter.attachView(this);
        setupUI();
        bindListener();
        this.presenter.getDetail(PartnerApplication.getInstance().getUserInfos().getUserInfo().getRowId());
    }

    private void setupUI() {
        setToolbarTitle("邀请明细");
        this.listInfo = new ArrayList();
    }

    private void bindListener() {
        this.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.yltx.oil.partner.modules.mine.activity.InvitationDetailsActivity.1
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                InvitationDetailsActivity invitationDetailsActivity = InvitationDetailsActivity.this;
                Toast.makeText(invitationDetailsActivity, "点击了 " + ((Object) ((TextView) ((LinearLayout) view).getChildAt(1)).getText()) + " 列表", 0).show();
                return true;
            }
        });
        this.expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // from class: com.yltx.oil.partner.modules.mine.activity.InvitationDetailsActivity.2
            @Override // android.widget.ExpandableListView.OnGroupClickListener
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                view.setClickable(true);
                return true;
            }
        });
    }

    private void initRView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rlInvitation.setLayoutManager(linearLayoutManager);
        this.mAdapter = new InvitationRecyclerAdapter(null);
        this.rlInvitation.setAdapter(this.mAdapter);
    }

    @Override // com.yltx.oil.partner.modules.mine.view.InviteDetailView
    public void onSuccess(HttpResult<InviteDetailResp> httpResult) {
        TextView textView = this.tvYxhhr;
        textView.setText(httpResult.getData().getHeaderInfo().getEffectiveCount() + "");
        TextView textView2 = this.tvLjyq;
        textView2.setText("累计邀请：" + httpResult.getData().getHeaderInfo().getCumulativeCount() + "人");
        TextView textView3 = this.tvLjzc;
        textView3.setText("注        册：" + httpResult.getData().getHeaderInfo().getRegisterCount() + "人");
        TextView textView4 = this.tvLjgm;
        textView4.setText("购        买：" + httpResult.getData().getHeaderInfo().getPayCount() + "人");
        this.listInfo = httpResult.getData().getListInfo();
        this.expandableListAdapter = new MyExpandableListView(this, this.listInfo);
        initRView();
        this.expandableListView.setGroupIndicator(null);
        this.expandableListView.setAdapter(this.expandableListAdapter);
        for (int i = 0; i < this.expandableListAdapter.getGroupCount(); i++) {
            this.expandableListView.expandGroup(i);
        }
        this.expandableListAdapter.notifyDataSetChanged();
    }
}