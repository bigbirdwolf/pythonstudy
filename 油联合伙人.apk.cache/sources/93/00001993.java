package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.modules.profit.adapter.Data_Recyclerview_Adapter;
import com.yltx.oil.partner.modules.profit.presenter.GeneralizePresenter;
import com.yltx.oil.partner.modules.profit.response.GeneralizeResponse;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.ToastUtil;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class DataanalysisActivity extends ToolBarActivity implements PageLimitView<GeneralizeResponse>, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.SR_list_tgsj)
    SwipeRefreshLayout SRListTgsj;
    private String[] arr;
    Data_Recyclerview_Adapter data_recyclerview_adapter;
    @Inject
    GeneralizePresenter generalizePresenter;
    @BindView(R.id.sy_tgsj_djcs_shu)
    TextView syTgsjDjcsShu;
    @BindView(R.id.sy_tgsj_qbsj_qb_wx)
    RelativeLayout syTgsjQbsjQbWx;
    @BindView(R.id.sy_tgsj_qbsj_qb_xw)
    RelativeLayout syTgsjQbsjQbXw;
    @BindView(R.id.sy_tgsj_qbsj_recyclerview)
    RecyclerView syTgsjQbsjRecyclerview;
    @BindView(R.id.sy_tgsj_xse_shu)
    TextView syTgsjXseShu;
    @BindView(R.id.sy_tgsj_xzrq_tv)
    TextView syTgsjXzrqTv;
    @BindView(R.id.sy_tgsj_xzrq_wx)
    RelativeLayout syTgsjXzrqWx;
    @BindView(R.id.sy_tgsj_xzrq_xw)
    RelativeLayout syTgsjXzrqXw;
    @BindView(R.id.sy_tgsj_yjsr_shu)
    TextView syTgsjYjsrShu;
    @BindView(R.id.sy_tgsj_yxdds_shu)
    TextView syTgsjYxddsShu;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, DataanalysisActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dataanalysis);
        ButterKnife.bind(this);
        this.generalizePresenter.attachView(this);
        this.syTgsjQbsjQbWx.setVisibility(0);
        setToolbarTitle("推广数据");
        EventBus.getDefault().register(this);
        setupUI();
        bindListener();
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMessage(String str) {
        if (str != "") {
            this.arr = str.split("一");
            Log.i("hahaha", this.arr[0]);
            Log.i("hahaha1", this.arr[1]);
            this.generalizePresenter.setBeginTime(this.arr[0]);
            this.generalizePresenter.setEndTime(this.arr[1]);
            this.generalizePresenter.fetchFirst();
            this.syTgsjXzrqWx.setVisibility(8);
            this.syTgsjXzrqXw.setVisibility(0);
            this.syTgsjXzrqTv.setText(str);
            return;
        }
        this.syTgsjXzrqXw.setVisibility(8);
        this.syTgsjXzrqWx.setVisibility(0);
    }

    private void setupUI() {
        initRView();
        if (this.arr != null) {
            this.generalizePresenter.setBeginTime(this.arr[0]);
            this.generalizePresenter.setEndTime(this.arr[1]);
            this.generalizePresenter.fetchFirst();
            return;
        }
        this.generalizePresenter.fetchFirst();
    }

    private void initRView() {
        this.data_recyclerview_adapter = new Data_Recyclerview_Adapter(null);
        this.syTgsjQbsjRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.syTgsjQbsjRecyclerview.setAdapter(this.data_recyclerview_adapter);
        this.data_recyclerview_adapter.setOnLoadMoreListener(this, this.syTgsjQbsjRecyclerview);
    }

    private void bindListener() {
        Rx.click(this.syTgsjQbsjQbWx, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$DataanalysisActivity$nVeccr0cXoG1ZlZWQKRvcLFm0tc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DataanalysisActivity.lambda$bindListener$0(DataanalysisActivity.this, (Void) obj);
            }
        });
        Rx.click(this.syTgsjQbsjQbXw, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$DataanalysisActivity$NNILz_frph08tLZpzIESDfxKtyk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DataanalysisActivity.lambda$bindListener$1(DataanalysisActivity.this, (Void) obj);
            }
        });
        Rx.click(this.syTgsjXzrqWx, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$DataanalysisActivity$FdA2jAKGJ3XFHWBHToYxdDf6cCQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DataanalysisActivity.lambda$bindListener$2(DataanalysisActivity.this, (Void) obj);
            }
        });
        Rx.click(this.syTgsjXzrqXw, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$DataanalysisActivity$ER-X1_ufQQYVMGsyxDJIUC_0Xb8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DataanalysisActivity.lambda$bindListener$3(DataanalysisActivity.this, (Void) obj);
            }
        });
        this.SRListTgsj.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$DataanalysisActivity$O9FMkNbDZx_3wIjZLcppARs1FHs
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                DataanalysisActivity.lambda$bindListener$4(DataanalysisActivity.this);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(DataanalysisActivity dataanalysisActivity, Void r3) {
        dataanalysisActivity.syTgsjQbsjQbWx.setVisibility(8);
        dataanalysisActivity.syTgsjQbsjQbXw.setVisibility(0);
        if (dataanalysisActivity.syTgsjXzrqWx.getVisibility() != 0) {
            dataanalysisActivity.syTgsjXzrqXw.setVisibility(8);
            dataanalysisActivity.syTgsjXzrqWx.setVisibility(0);
            dataanalysisActivity.arr[0] = "";
            dataanalysisActivity.arr[1] = "";
            dataanalysisActivity.setupUI();
        }
        dataanalysisActivity.setupUI();
    }

    public static /* synthetic */ void lambda$bindListener$1(DataanalysisActivity dataanalysisActivity, Void r2) {
        dataanalysisActivity.syTgsjQbsjQbXw.setVisibility(8);
        dataanalysisActivity.syTgsjQbsjQbWx.setVisibility(0);
    }

    public static /* synthetic */ void lambda$bindListener$2(DataanalysisActivity dataanalysisActivity, Void r2) {
        dataanalysisActivity.startActivity(OptiondateActivity.getCallingIntent(dataanalysisActivity));
        dataanalysisActivity.syTgsjQbsjQbXw.setVisibility(8);
        dataanalysisActivity.syTgsjQbsjQbWx.setVisibility(0);
    }

    public static /* synthetic */ void lambda$bindListener$3(DataanalysisActivity dataanalysisActivity, Void r2) {
        dataanalysisActivity.startActivity(OptiondateActivity.getCallingIntent(dataanalysisActivity));
        dataanalysisActivity.syTgsjQbsjQbXw.setVisibility(8);
        dataanalysisActivity.syTgsjQbsjQbWx.setVisibility(0);
    }

    public static /* synthetic */ void lambda$bindListener$4(DataanalysisActivity dataanalysisActivity) {
        dataanalysisActivity.generalizePresenter.fetchTop();
    }

    private void setData(GeneralizeResponse generalizeResponse) {
        if (generalizeResponse == null || generalizeResponse.getData().size() == 0) {
            this.data_recyclerview_adapter.loadMoreEnd();
        } else if (generalizeResponse.getData().size() < 10) {
            this.data_recyclerview_adapter.setEnableLoadMore(false);
            this.data_recyclerview_adapter.loadMoreEnd();
        } else {
            this.data_recyclerview_adapter.setEnableLoadMore(true);
            this.data_recyclerview_adapter.loadMoreComplete();
        }
        this.data_recyclerview_adapter.setNewData(generalizeResponse.getData());
        this.data_recyclerview_adapter.disableLoadMoreIfNotFullPage();
    }

    private void setMoreData(GeneralizeResponse generalizeResponse) {
        if (generalizeResponse.getData().size() < 10) {
            this.data_recyclerview_adapter.setEnableLoadMore(false);
            this.data_recyclerview_adapter.loadMoreEnd();
        } else {
            this.data_recyclerview_adapter.setEnableLoadMore(true);
            this.data_recyclerview_adapter.loadMoreComplete();
        }
        this.data_recyclerview_adapter.addData((List) generalizeResponse.getData());
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(GeneralizeResponse generalizeResponse) {
        setData(generalizeResponse);
        TextView textView = this.syTgsjDjcsShu;
        textView.setText(generalizeResponse.getTotalData().getWatchCount() + "");
        TextView textView2 = this.syTgsjXseShu;
        textView2.setText(String.format("%.2f", Double.valueOf(generalizeResponse.getTotalData().getOrderAmount())) + "");
        TextView textView3 = this.syTgsjYxddsShu;
        textView3.setText(generalizeResponse.getTotalData().getOrderCount() + "");
        TextView textView4 = this.syTgsjYjsrShu;
        textView4.setText(String.format("%.2f", Double.valueOf(generalizeResponse.getTotalData().getRebateAmount())) + "");
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(GeneralizeResponse generalizeResponse) {
        setData(generalizeResponse);
        this.SRListTgsj.setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(GeneralizeResponse generalizeResponse) {
        this.SRListTgsj.setRefreshing(false);
        setMoreData(generalizeResponse);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
        this.SRListTgsj.setRefreshing(false);
        ToastUtil.showMiddleScreenToast(str);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
        this.data_recyclerview_adapter.loadMoreFail();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
    public void onLoadMoreRequested() {
        this.generalizePresenter.fetchMore();
    }
}