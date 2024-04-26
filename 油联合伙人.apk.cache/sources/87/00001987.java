package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.profit.adapter.AccountRecyclerAdapter;
import com.yltx.oil.partner.modules.profit.presenter.AccountBalancePresenter;
import com.yltx.oil.partner.modules.profit.presenter.UserAccountPresenter;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import com.yltx.oil.partner.modules.profit.response.UserAccountConsumeResponse;
import com.yltx.oil.partner.modules.profit.view.AccountBalanceView;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.ToastUtil;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class AccountdetailsActivity extends ToolBarActivity implements BaseQuickAdapter.RequestLoadMoreListener, PageLimitView<UserAccountConsumeResponse>, BaseQuickAdapter.OnItemClickListener, AccountBalanceView {
    @BindView(R.id.recyc_list)
    RecyclerView List;
    private AccountRecyclerAdapter mAdapter;
    @Inject
    AccountBalancePresenter mPresenter;
    @BindView(R.id.sl_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_cz)
    TextView tvCz;
    @BindView(R.id.tv_release_money)
    TextView tvReleaseMoney;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @Inject
    UserAccountPresenter userAccountPresenter;

    @Override // com.yltx.oil.partner.modules.profit.view.AccountBalanceView
    public void onError(Throwable th) {
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    @Override // com.yltx.oil.partner.modules.profit.view.AccountBalanceView
    public void onyzjxsjfx(HttpResult<ManageResponse> httpResult) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AccountdetailsActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_accountdetails);
        ButterKnife.bind(this);
        this.mPresenter.attachView(this);
        this.userAccountPresenter.attachView(this);
        setupUI();
        initView();
        bindListener();
        this.userAccountPresenter.fetchFirst();
    }

    private void setupUI() {
        setToolbarTitle("账户明细");
        this.mPresenter.getAccountBalance();
    }

    private void bindListener() {
        Rx.click(this.tvTixian, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$AccountdetailsActivity$ysv0faaNELZ66ZaCfKUtHl0ta_c
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AccountdetailsActivity.lambda$bindListener$0(AccountdetailsActivity.this, (Void) obj);
            }
        });
        this.mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$AccountdetailsActivity$yAU5h2hreKi1KJyeqhZL5orA9i0
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                AccountdetailsActivity.lambda$bindListener$1(AccountdetailsActivity.this);
            }
        });
        Rx.click(this.tvCz, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$AccountdetailsActivity$dsknZW7uVp3b_uMgjYFtDi8Isvw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AccountdetailsActivity.lambda$bindListener$2(AccountdetailsActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(AccountdetailsActivity accountdetailsActivity, Void r1) {
        accountdetailsActivity.mPresenter.getIsAuth();
    }

    public static /* synthetic */ void lambda$bindListener$1(AccountdetailsActivity accountdetailsActivity) {
        accountdetailsActivity.userAccountPresenter.fetchTop();
    }

    public static /* synthetic */ void lambda$bindListener$2(AccountdetailsActivity accountdetailsActivity, Void r1) {
        accountdetailsActivity.getNavigator().navigateToRechargeActivity(accountdetailsActivity);
    }

    private void initView() {
        this.mAdapter = new AccountRecyclerAdapter(null);
        this.List.setLayoutManager(new LinearLayoutManager(getContext()));
        this.List.setAdapter(this.mAdapter);
        this.mAdapter.setOnLoadMoreListener(this, this.List);
        this.mAdapter.setOnItemClickListener(this);
    }

    @Override // com.yltx.oil.partner.modules.profit.view.AccountBalanceView
    public void onAccountBalance(HttpResult<String> httpResult) {
        this.tvReleaseMoney.setText(httpResult.getData());
    }

    @Override // com.yltx.oil.partner.modules.profit.view.AccountBalanceView
    public void onIsAuth(HttpResult<String> httpResult) {
        if (httpResult.getData().equals("1")) {
            getNavigator().navigateToWithdraw(this, this.tvReleaseMoney.getText().toString());
        } else {
            getNavigator().navigateToBindingbankcards(getContext(), 0, "", "");
        }
    }

    private void setData(List<UserAccountConsumeResponse.AccountConsumeitem> list) {
        if (list == null || list.size() == 0) {
            this.mAdapter.loadMoreEnd();
        } else if (list.size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.setNewData(list);
        this.mAdapter.disableLoadMoreIfNotFullPage();
    }

    private void setMoreData(List<UserAccountConsumeResponse.AccountConsumeitem> list) {
        if (list.size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.addData((List) list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(UserAccountConsumeResponse userAccountConsumeResponse) {
        setData(userAccountConsumeResponse.getConsumeList());
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(UserAccountConsumeResponse userAccountConsumeResponse) {
        setData(userAccountConsumeResponse.getConsumeList());
        this.mRefreshLayout.setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(UserAccountConsumeResponse userAccountConsumeResponse) {
        this.mRefreshLayout.setRefreshing(false);
        setMoreData(userAccountConsumeResponse.getConsumeList());
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
        this.mRefreshLayout.setRefreshing(false);
        ToastUtil.showMiddleScreenToast(str);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
        this.mAdapter.loadMoreFail();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
    public void onLoadMoreRequested() {
        this.userAccountPresenter.fetchMore();
    }
}