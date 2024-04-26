package com.yltx.oil.partner.modules.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.StateActivity;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.modules.home.adapter.Seek_Recyclerview_Adapter;
import com.yltx.oil.partner.modules.home.presenter.SeekPresenter;
import com.yltx.oil.partner.modules.home.response.SeekResponse;
import com.yltx.oil.partner.modules.home.view.SeekView;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.widget.CommonDialog;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class SearchResultActivity extends StateActivity implements SeekView, BaseQuickAdapter.OnItemClickListener {
    Seek_Recyclerview_Adapter adapter;
    String goodsName;
    @BindView(R.id.head_left_image)
    ImageView headLeftImage;
    @Inject
    SeekPresenter seekPresenter;
    @BindView(R.id.ss_btn_search)
    Button ssBtnSearch;
    @BindView(R.id.ss_edittext)
    EditText ssEdittext;
    @BindView(R.id.ss_list)
    RecyclerView ssList;

    @Override // com.yltx.oil.partner.modules.home.view.SeekView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context, String str) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("goodsName", str);
        return intent;
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        this.goodsName = getIntent().getStringExtra("goodsName");
        this.seekPresenter.attachView(this);
        this.ssEdittext.setText(this.goodsName);
        setupUI();
        this.seekPresenter.messagesubmit(this.goodsName);
        this.adapter.notifyDataSetChanged();
        bindListener();
    }

    protected void setupUI() {
        this.adapter = new Seek_Recyclerview_Adapter(null);
        this.ssList.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.ssList.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(this);
    }

    protected void bindListener() {
        Rx.click(this.headLeftImage, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$SearchResultActivity$vQMysei9T5p0nxjfwScGvRVOUcA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SearchResultActivity.lambda$bindListener$0(SearchResultActivity.this, (Void) obj);
            }
        });
        Rx.click(this.ssBtnSearch, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$SearchResultActivity$ugalDdNuje0GH6DtX5ihh6zpr-8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SearchResultActivity.lambda$bindListener$1(SearchResultActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(SearchResultActivity searchResultActivity, Void r1) {
        searchResultActivity.finish();
    }

    public static /* synthetic */ void lambda$bindListener$1(SearchResultActivity searchResultActivity, Void r3) {
        SeekPresenter seekPresenter = searchResultActivity.seekPresenter;
        seekPresenter.messagesubmit(searchResultActivity.ssEdittext.getText().toString().trim() + "");
    }

    @Override // com.yltx.oil.partner.modules.home.view.SeekView
    public void onSeek(List<SeekResponse> list) {
        this.adapter.setNewData(list);
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (PartnerApplication.getInstance().isLoading) {
            if (PartnerApplication.getInstance().getUserInfos().getPartnerInfo().getIsPartner().equals("1")) {
                getContext().startActivity(CommoditySharingInforActivity.getCallingIntent(getContext(), 3, String.valueOf(((SeekResponse) baseQuickAdapter.getData().get(i)).getSpecsId())));
                return;
            }
            final CommonDialog showPartnerDialog = CommonUtils.showPartnerDialog(getContext());
            CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.home.activity.SearchResultActivity.1
                {
                    SearchResultActivity.this = this;
                }

                @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
                public void onSureClick(View view2) {
                    showPartnerDialog.dismiss();
                    SearchResultActivity.this.getNavigator().navigateToJsBridgeWebActivity(SearchResultActivity.this.getContext(), "油站合伙人", Config.API_WeiXin_URL.concat("#/partner/stationpartner"));
                }
            });
            return;
        }
        startActivity(LoginActivity.getCallingIntent(getContext()));
    }
}