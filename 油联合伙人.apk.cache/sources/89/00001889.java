package com.yltx.oil.partner.modules.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.modules.mine.adapter.ComplainRecyclerAdapter;
import com.yltx.oil.partner.modules.mine.presenter.ComplaintOrderPresenter;
import com.yltx.oil.partner.modules.mine.response.ComplaintResponse;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class ComplaintActivity extends ToolBarActivity implements BaseQuickAdapter.OnItemClickListener, PageLimitView<List<ComplaintResponse>>, BaseQuickAdapter.RequestLoadMoreListener {
    @Inject
    ComplaintOrderPresenter complaintPresenter;
    @BindView(R.id.et_voucherCode)
    EditText etVoucherCode;
    private ComplainRecyclerAdapter mAdapter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_complain)
    SwipeRefreshLayout srlComplain;
    @BindView(R.id.tv_chulizhong)
    TextView tvChulizhong;
    @BindView(R.id.tv_czk)
    TextView tvCzk;
    @BindView(R.id.tv_daifaqi)
    TextView tvDaifaqi;
    @BindView(R.id.tv_jyk)
    TextView tvJyk;
    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;
    @BindView(R.id.tv_sp)
    TextView tvSp;
    @BindView(R.id.tv_wancheng)
    TextView tvWancheng;
    @BindView(R.id.tv_ypmy)
    TextView tvYpmy;
    private String YPMY = "1";
    private String JYK = "2";
    private String CZK = "3";
    private String SP = "0";
    private String Status = "";
    List<ComplaintResponse> list = new ArrayList();
    private String voucherCode = "";
    private String type = this.YPMY;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ComplaintActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_complaint);
        this.complaintPresenter.attachView(this);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(2);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        setToolbarTitle("代客投诉");
        initRView();
    }

    private void initRView() {
        this.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAdapter = new ComplainRecyclerAdapter(this.list, 0);
        this.rvList.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
        this.mAdapter.setOnLoadMoreListener(this, this.rvList);
    }

    protected void bindListener() {
        Rx.click(this.tvQuxiao, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplaintActivity$1eEJXlFKufefxsAsWqq1XqxzoZA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplaintActivity.lambda$bindListener$0(ComplaintActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvJyk, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplaintActivity$iEPCalV0UGfhaAcqhuQUF4fE7Ds
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplaintActivity.lambda$bindListener$1(ComplaintActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvCzk, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplaintActivity$-Z8TSLMYEePna-CpeGnYDPoE-tk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplaintActivity.lambda$bindListener$2(ComplaintActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvSp, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplaintActivity$gMstbXpMqd3baOf7UDn3AfU6FLE
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplaintActivity.lambda$bindListener$3(ComplaintActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvYpmy, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplaintActivity$TOFkXFZSqxcOAKmldah1owe5-LI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplaintActivity.lambda$bindListener$4(ComplaintActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvDaifaqi, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplaintActivity$hdcqDtbaEwgdA8FLSSTGn8ggU1U
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplaintActivity.lambda$bindListener$5(ComplaintActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvChulizhong, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplaintActivity$WaYb9z5igvdzFXGtzVd1PfcmqJ8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplaintActivity.lambda$bindListener$6(ComplaintActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvWancheng, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplaintActivity$yzow1STvxmWX-Rtc3dWPEjf9374
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplaintActivity.lambda$bindListener$7(ComplaintActivity.this, (Void) obj);
            }
        });
        this.etVoucherCode.addTextChangedListener(new TextWatcher() { // from class: com.yltx.oil.partner.modules.mine.activity.ComplaintActivity.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            {
                ComplaintActivity.this = this;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                ComplaintActivity.this.voucherCode = ComplaintActivity.this.etVoucherCode.getText().toString();
                if (TextUtils.isEmpty(ComplaintActivity.this.voucherCode)) {
                    return;
                }
                ComplaintActivity.this.getComplain();
            }
        });
        this.srlComplain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplaintActivity$k28W6y4fjbkTE3D6pYGJp6QvEPE
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                ComplaintActivity.lambda$bindListener$8(ComplaintActivity.this);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(ComplaintActivity complaintActivity, Void r2) {
        complaintActivity.list.clear();
        complaintActivity.etVoucherCode.setText("");
        complaintActivity.voucherCode = "";
        complaintActivity.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$1(ComplaintActivity complaintActivity, Void r2) {
        complaintActivity.list.clear();
        complaintActivity.setText(complaintActivity.tvJyk, "1");
        complaintActivity.setText(complaintActivity.tvCzk, "0");
        complaintActivity.setText(complaintActivity.tvSp, "0");
        complaintActivity.setText(complaintActivity.tvYpmy, "0");
        complaintActivity.etVoucherCode.setText("");
        complaintActivity.type = complaintActivity.JYK;
        complaintActivity.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$2(ComplaintActivity complaintActivity, Void r2) {
        complaintActivity.setText(complaintActivity.tvJyk, "0");
        complaintActivity.setText(complaintActivity.tvCzk, "1");
        complaintActivity.setText(complaintActivity.tvSp, "0");
        complaintActivity.setText(complaintActivity.tvYpmy, "0");
        complaintActivity.etVoucherCode.setText("");
        complaintActivity.type = complaintActivity.CZK;
        complaintActivity.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$3(ComplaintActivity complaintActivity, Void r2) {
        complaintActivity.setText(complaintActivity.tvJyk, "0");
        complaintActivity.setText(complaintActivity.tvCzk, "0");
        complaintActivity.setText(complaintActivity.tvSp, "1");
        complaintActivity.setText(complaintActivity.tvYpmy, "0");
        complaintActivity.etVoucherCode.setText("");
        complaintActivity.type = complaintActivity.SP;
        complaintActivity.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$4(ComplaintActivity complaintActivity, Void r2) {
        complaintActivity.setText(complaintActivity.tvJyk, "0");
        complaintActivity.setText(complaintActivity.tvCzk, "0");
        complaintActivity.setText(complaintActivity.tvSp, "0");
        complaintActivity.setText(complaintActivity.tvYpmy, "1");
        complaintActivity.etVoucherCode.setText("");
        complaintActivity.type = complaintActivity.YPMY;
        complaintActivity.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$5(ComplaintActivity complaintActivity, Void r4) {
        complaintActivity.tvDaifaqi.setTextColor(complaintActivity.getResources().getColor(R.color.white));
        complaintActivity.tvDaifaqi.setBackground(complaintActivity.getResources().getDrawable(R.drawable.light_yellow_left));
        complaintActivity.tvChulizhong.setTextColor(complaintActivity.getResources().getColor(R.color.colorPrimaryDark));
        complaintActivity.tvChulizhong.setBackground(complaintActivity.getResources().getDrawable(R.drawable.lighty_ellow));
        complaintActivity.tvWancheng.setTextColor(complaintActivity.getResources().getColor(R.color.colorPrimaryDark));
        complaintActivity.tvWancheng.setBackground(complaintActivity.getResources().getDrawable(R.drawable.lightyellow_right));
        complaintActivity.Status = "";
        complaintActivity.etVoucherCode.setText("");
        complaintActivity.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$6(ComplaintActivity complaintActivity, Void r4) {
        complaintActivity.tvDaifaqi.setTextColor(complaintActivity.getResources().getColor(R.color.colorPrimaryDark));
        complaintActivity.tvDaifaqi.setBackground(complaintActivity.getResources().getDrawable(R.drawable.lightyellow_left));
        complaintActivity.tvChulizhong.setTextColor(complaintActivity.getResources().getColor(R.color.white));
        complaintActivity.tvChulizhong.setBackgroundColor(complaintActivity.getResources().getColor(R.color.colorPrimaryDark));
        complaintActivity.tvWancheng.setTextColor(complaintActivity.getResources().getColor(R.color.colorPrimaryDark));
        complaintActivity.tvWancheng.setBackground(complaintActivity.getResources().getDrawable(R.drawable.lightyellow_right));
        complaintActivity.Status = "1";
        complaintActivity.etVoucherCode.setText("");
        complaintActivity.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$7(ComplaintActivity complaintActivity, Void r4) {
        complaintActivity.tvDaifaqi.setTextColor(complaintActivity.getResources().getColor(R.color.colorPrimaryDark));
        complaintActivity.tvDaifaqi.setBackground(complaintActivity.getResources().getDrawable(R.drawable.lightyellow_left));
        complaintActivity.tvChulizhong.setTextColor(complaintActivity.getResources().getColor(R.color.colorPrimaryDark));
        complaintActivity.tvChulizhong.setBackground(complaintActivity.getResources().getDrawable(R.drawable.lighty_ellow));
        complaintActivity.tvWancheng.setTextColor(complaintActivity.getResources().getColor(R.color.white));
        complaintActivity.tvWancheng.setBackground(complaintActivity.getResources().getDrawable(R.drawable.light_yellow_right));
        complaintActivity.Status = "2";
        complaintActivity.etVoucherCode.setText("");
        complaintActivity.getComplain();
    }

    public static /* synthetic */ void lambda$bindListener$8(ComplaintActivity complaintActivity) {
        complaintActivity.complaintPresenter.fetchTop();
    }

    public void getComplain() {
        this.complaintPresenter.setStatus(this.Status);
        this.complaintPresenter.setType(this.type);
        this.complaintPresenter.setVoucherCode(this.voucherCode);
        this.complaintPresenter.fetchFirst();
        this.voucherCode = "";
    }

    private void setText(TextView textView, String str) {
        if (str.equals("1")) {
            textView.setBackground(getResources().getDrawable(R.drawable.shape_yellow_rectangle_r5));
            textView.setTextColor(getResources().getColor(R.color.white));
            return;
        }
        textView.setBackground(getResources().getDrawable(R.drawable.gray_5));
        textView.setTextColor(getResources().getColor(R.color.text_color_gray));
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (this.Status.equals("")) {
            getNavigator().navigateToComplainValet(this, this.mAdapter.getData().get(i).getVoucherCode());
        }
    }

    private void setData(List<ComplaintResponse> list) {
        if (list == null || list.size() == 0) {
            this.mAdapter.loadMoreEnd();
        } else if (list.size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.setStatus(this.Status);
        this.mAdapter.setType(this.type);
        this.mAdapter.setNewData(list);
        this.mAdapter.disableLoadMoreIfNotFullPage();
    }

    private void setMoreData(List<ComplaintResponse> list) {
        if (list.size() < 10) {
            this.mAdapter.setEnableLoadMore(false);
            this.mAdapter.loadMoreEnd();
        } else {
            this.mAdapter.setEnableLoadMore(true);
            this.mAdapter.loadMoreComplete();
        }
        this.mAdapter.setStatus(this.Status);
        this.mAdapter.setType(this.type);
        this.mAdapter.addData((List) list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void render(List<ComplaintResponse> list) {
        setData(list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshComplete(List<ComplaintResponse> list) {
        setData(list);
        this.srlComplain.setRefreshing(false);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreComplete(List<ComplaintResponse> list) {
        this.srlComplain.setRefreshing(false);
        setMoreData(list);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onRefreshError(String str) {
        this.srlComplain.setRefreshing(false);
        ToastUtil.showMiddleScreenToast(str);
    }

    @Override // com.yltx.oil.partner.mvp.views.PageLimitView
    public void onLoadMoreError(String str) {
        this.mAdapter.loadMoreFail();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
    public void onLoadMoreRequested() {
        this.complaintPresenter.fetchMore();
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.complaintPresenter.setStatus(this.Status);
        this.complaintPresenter.setType(this.type);
        this.complaintPresenter.setVoucherCode(this.voucherCode);
        this.complaintPresenter.fetchFirst();
    }
}