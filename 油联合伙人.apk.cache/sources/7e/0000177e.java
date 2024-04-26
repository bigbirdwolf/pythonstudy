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
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.home.adapter.SSLS_Recyclerview_Adapter;
import com.yltx.oil.partner.modules.home.presenter.SCPresenter;
import com.yltx.oil.partner.modules.home.presenter.SSLSPresenter;
import com.yltx.oil.partner.modules.home.response.SCResponse;
import com.yltx.oil.partner.modules.home.response.SSLSResponse;
import com.yltx.oil.partner.modules.home.view.SCView;
import com.yltx.oil.partner.modules.home.view.SSLSView;
import com.yltx.oil.partner.navigation.Navigator;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class SearchHomeActivity extends ToolBarActivity implements BaseQuickAdapter.OnItemClickListener, SSLSView, SCView {
    @BindView(R.id.Seek_edittext)
    EditText SeekEdittext;
    SSLS_Recyclerview_Adapter adapter;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @Inject
    SCPresenter scPresenter;
    @Inject
    SSLSPresenter sslsPresenter;
    @BindView(R.id.ssls_recyclerview)
    RecyclerView sslsRecyclerview;
    @BindView(R.id.ssls_sc)
    ImageView sslsSc;

    @Override // com.yltx.oil.partner.modules.home.view.SSLSView, com.yltx.oil.partner.modules.home.view.SCView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SearchHomeActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_home);
        ButterKnife.bind(this);
        setToolbarTitle("搜索");
        this.sslsPresenter.attachView(this);
        this.scPresenter.attachView(this);
        this.sslsPresenter.messagesubmit();
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        this.adapter = new SSLS_Recyclerview_Adapter(null);
        this.sslsRecyclerview.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.sslsRecyclerview.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(this);
    }

    protected void bindListener() {
        Rx.click(this.btnSearch, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$SearchHomeActivity$EclTkjX3itobp-UdNvW9Lke2Etg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SearchHomeActivity.lambda$bindListener$0(SearchHomeActivity.this, (Void) obj);
            }
        });
        Rx.click(this.sslsSc, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$SearchHomeActivity$7c3Now7iWzhDLBxO9pGuIAbxdUs
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SearchHomeActivity.lambda$bindListener$1(SearchHomeActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(SearchHomeActivity searchHomeActivity, Void r4) {
        searchHomeActivity.sslsPresenter.attachView(searchHomeActivity);
        searchHomeActivity.sslsPresenter.messagesubmit();
        Navigator navigator = searchHomeActivity.getNavigator();
        Context context = searchHomeActivity.getContext();
        navigator.navigateToSearchResult(context, searchHomeActivity.SeekEdittext.getText().toString().trim() + "");
        searchHomeActivity.adapter.notifyDataSetChanged();
    }

    public static /* synthetic */ void lambda$bindListener$1(SearchHomeActivity searchHomeActivity, Void r1) {
        searchHomeActivity.scPresenter.messagesubmit();
        searchHomeActivity.adapter.notifyDataSetChanged();
    }

    @Override // com.yltx.oil.partner.modules.home.view.SSLSView
    public void onSSLS(HttpResult<List<SSLSResponse>> httpResult) {
        this.adapter.setNewData(httpResult.getData());
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.yltx.oil.partner.modules.home.view.SCView
    public void onSC(HttpResult<SCResponse> httpResult) {
        if (httpResult.getData().getCode().equals("1")) {
            this.adapter.getData().clear();
            this.adapter.notifyDataSetChanged();
        }
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Navigator navigator = getNavigator();
        Context context = getContext();
        navigator.navigateToSearchResult(context, this.adapter.getData().get(i).getGoodsName() + "");
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.sslsPresenter.messagesubmit();
    }
}