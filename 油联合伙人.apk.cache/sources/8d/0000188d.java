package com.yltx.oil.partner.modules.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.mine.adapter.MyFeedBackRecyclerView_Adapter;
import com.yltx.oil.partner.modules.mine.presenter.MyfeedbackPresenter;
import com.yltx.oil.partner.modules.mine.response.MyfeedbackResponse;
import com.yltx.oil.partner.modules.mine.view.MyfeedbackView;
import com.yltx.oil.partner.utils.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class FeedbackActivity extends ToolBarActivity implements MyfeedbackView {
    MyFeedBackRecyclerView_Adapter adapter;
    List<MyfeedbackResponse> arrayList = new ArrayList();
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.ll_list)
    LinearLayout llList;
    @BindView(R.id.ll_submit)
    LinearLayout llSubmit;
    @Inject
    MyfeedbackPresenter myfeedbackPresenter;
    @BindView(R.id.rl_feedback)
    RecyclerView rlFeedback;
    @BindView(R.id.textViewe)
    LinearLayout textViewe;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_myfeedback)
    TextView tvMyfeedback;
    @BindView(R.id.tv_sub)
    TextView tvSub;

    @Override // com.yltx.oil.partner.modules.mine.view.MyfeedbackView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, FeedbackActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        this.myfeedbackPresenter.attachView(this);
        setToolbarTitle("意见反馈");
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        initRView();
        this.myfeedbackPresenter.feedbackMember();
    }

    private void initRView() {
        this.adapter = new MyFeedBackRecyclerView_Adapter(this.arrayList);
        this.rlFeedback.setLayoutManager(new LinearLayoutManager(this));
        this.rlFeedback.setAdapter(this.adapter);
    }

    protected void bindListener() {
        Rx.click(this.tvMyfeedback, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$FeedbackActivity$boTXdYDhOvHEy1bBpwyf8yB4GMw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FeedbackActivity.lambda$bindListener$0(FeedbackActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvFeedback, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$FeedbackActivity$Yc3LME9Coe0BLo_HsYv5V951_uA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FeedbackActivity.lambda$bindListener$1(FeedbackActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvSub, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$FeedbackActivity$HlM-C_8i_82FTzco2Bad--6C9KE
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                FeedbackActivity.lambda$bindListener$2(FeedbackActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(FeedbackActivity feedbackActivity, Void r3) {
        feedbackActivity.llSubmit.setVisibility(8);
        feedbackActivity.llList.setVisibility(0);
        feedbackActivity.tvFeedback.setTextColor(feedbackActivity.getResources().getColor(R.color.colorPrimaryDark));
        feedbackActivity.tvFeedback.setBackground(feedbackActivity.getResources().getDrawable(R.drawable.lightyellow_left));
        feedbackActivity.tvMyfeedback.setTextColor(feedbackActivity.getResources().getColor(R.color.white));
        feedbackActivity.tvMyfeedback.setBackground(feedbackActivity.getResources().getDrawable(R.drawable.light_yellow_right));
        feedbackActivity.setupUI();
    }

    public static /* synthetic */ void lambda$bindListener$1(FeedbackActivity feedbackActivity, Void r3) {
        feedbackActivity.llSubmit.setVisibility(0);
        feedbackActivity.llList.setVisibility(8);
        feedbackActivity.tvMyfeedback.setTextColor(feedbackActivity.getResources().getColor(R.color.colorPrimaryDark));
        feedbackActivity.tvMyfeedback.setBackground(feedbackActivity.getResources().getDrawable(R.drawable.lightyellow_right));
        feedbackActivity.tvFeedback.setTextColor(feedbackActivity.getResources().getColor(R.color.white));
        feedbackActivity.tvFeedback.setBackground(feedbackActivity.getResources().getDrawable(R.drawable.light_yellow_left));
    }

    public static /* synthetic */ void lambda$bindListener$2(FeedbackActivity feedbackActivity, Void r4) {
        if (!TextUtils.isEmpty(feedbackActivity.etContent.getText().toString())) {
            feedbackActivity.myfeedbackPresenter.feedbacksubmit(feedbackActivity.etContent.getText().toString(), feedbackActivity.etPhone.getText().toString(), feedbackActivity.etName.getText().toString());
        } else {
            ToastUtil.showMiddleScreenToast("请填写内容！");
        }
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MyfeedbackView
    public void onMember(HttpResult<List<MyfeedbackResponse>> httpResult) {
        if (httpResult.getData() != null && httpResult.getData().size() > 0) {
            this.textViewe.setVisibility(8);
            this.arrayList.clear();
            this.arrayList = httpResult.getData();
            this.adapter.addData((List) this.arrayList);
            this.adapter.notifyDataSetChanged();
            return;
        }
        this.textViewe.setVisibility(0);
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MyfeedbackView
    public void onfeedbacksubmint(HttpResult<String> httpResult) {
        this.etContent.setText("");
        this.etName.setText("");
        this.etPhone.setText("");
        ToastUtil.showMiddleScreenToast("提交成功");
    }
}