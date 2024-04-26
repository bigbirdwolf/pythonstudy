package com.yltx.oil.partner.modules.mine.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.zxing.WriterException;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.InviteResp;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.mine.presenter.InvitePresenter;
import com.yltx.oil.partner.modules.mine.view.InviteView;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.EncodeUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import java.math.BigDecimal;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class InviteCourtesyActivity extends ToolBarActivity implements InviteView {
    @BindView(R.id.btn_yq)
    Button btnYq;
    @BindView(R.id.gz)
    TextView gz;
    @Inject
    InvitePresenter presenter;
    @BindView(R.id.tv_mx)
    TextView tvMx;
    @BindView(R.id.tv_yaoqmoney)
    TextView tvYaoqmoney;
    @BindView(R.id.tv_yqry)
    TextView tvYqry;
    private UMShareListener umShareListener = new UMShareListener() { // from class: com.yltx.oil.partner.modules.mine.activity.InviteCourtesyActivity.2
        @Override // com.umeng.socialize.UMShareListener
        public void onCancel(SHARE_MEDIA share_media) {
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onStart(SHARE_MEDIA share_media) {
        }

        {
            InviteCourtesyActivity.this = this;
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onResult(SHARE_MEDIA share_media) {
            ToastUtil.showMiddleScreenToast("分享成功");
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onError(SHARE_MEDIA share_media, Throwable th) {
            ToastUtil.showMiddleScreenToast("分享失败");
        }
    };

    @Override // com.yltx.oil.partner.modules.mine.view.InviteView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, InviteCourtesyActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_invite_courtesy);
        this.presenter.attachView(this);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
        this.presenter.getTotal(PartnerApplication.getInstance().getUserInfos().getUserInfo().getRowId());
    }

    private void setupUI() {
        setToolbarTitle("邀请有礼");
    }

    private void bindListener() {
        Rx.click(this.gz, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$InviteCourtesyActivity$hjmGEiJ4zG7qbArHRs2Z9eD88og
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                InviteCourtesyActivity.lambda$bindListener$0(InviteCourtesyActivity.this, (Void) obj);
            }
        });
        Rx.click(this.btnYq, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$InviteCourtesyActivity$dTAAxitBaXKddafZ-tOvmDikCQI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                InviteCourtesyActivity.lambda$bindListener$1(InviteCourtesyActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvMx, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$InviteCourtesyActivity$NLqgMGbfjdkWmt5vL81j9J1_2Yo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                InviteCourtesyActivity.lambda$bindListener$2(InviteCourtesyActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(InviteCourtesyActivity inviteCourtesyActivity, Void r1) {
        CommonUtils.showYaoqGzDialog(inviteCourtesyActivity.getContext());
    }

    public static /* synthetic */ void lambda$bindListener$1(InviteCourtesyActivity inviteCourtesyActivity, Void r3) {
        if (PartnerApplication.getInstance().isLoading) {
            final String concat = Config.API_WeiXin_URL.concat("#/recommendTransition?recommenderId=" + PartnerApplication.getInstance().getUserInfos().getUserInfo().getRowId());
            CommonUtils.showYaoqDialog(inviteCourtesyActivity.getContext());
            CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.mine.activity.InviteCourtesyActivity.1
                {
                    InviteCourtesyActivity.this = inviteCourtesyActivity;
                }

                @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
                public void onSureClick(View view) {
                    switch (view.getId()) {
                        case R.id.ll_wxhlj /* 2131296588 */:
                            ((ClipboardManager) InviteCourtesyActivity.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Label", concat));
                            ToastUtil.showMiddleScreenToast("已复制");
                            return;
                        case R.id.ll_wxhmdm /* 2131296589 */:
                            try {
                                CommonUtils.showYqCodeDialog(InviteCourtesyActivity.this.getContext(), EncodeUtils.createCode(InviteCourtesyActivity.this.getContext(), concat));
                                return;
                            } catch (WriterException e) {
                                e.printStackTrace();
                                Log.d(">>>>", "生成二维码出错");
                                return;
                            }
                        case R.id.ll_wxhy /* 2131296590 */:
                            InviteCourtesyActivity.this.ShareWx(1, concat);
                            return;
                        case R.id.ll_wxhyq /* 2131296591 */:
                            InviteCourtesyActivity.this.ShareWx(2, concat);
                            return;
                        default:
                            return;
                    }
                }
            });
            return;
        }
        inviteCourtesyActivity.startActivity(LoginActivity.getCallingIntent(inviteCourtesyActivity.getContext()));
    }

    public static /* synthetic */ void lambda$bindListener$2(InviteCourtesyActivity inviteCourtesyActivity, Void r2) {
        inviteCourtesyActivity.getNavigator().navigateToInvitationDetails(inviteCourtesyActivity.getContext());
    }

    void ShareWx(int i, String str) {
        if (UMShareAPI.get(getContext()).isInstall(this, SHARE_MEDIA.WEIXIN)) {
            switch (i) {
                case 1:
                    new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).withText(str).setCallback(this.umShareListener).share();
                    return;
                case 2:
                    new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withText(str).setCallback(this.umShareListener).share();
                    return;
                default:
                    return;
            }
        }
        ToastUtil.showMiddleScreenToast("请先安装微信APP哦");
    }

    @Override // com.yltx.oil.partner.modules.mine.view.InviteView
    public void onSuccess(HttpResult<InviteResp> httpResult) {
        TextView textView = this.tvYqry;
        textView.setText(httpResult.getData().getInvitedFriendsCount() + "人");
        this.tvYaoqmoney.setText(new BigDecimal(httpResult.getData().getBonus()).setScale(2, 4).toString().concat("元"));
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        UMShareAPI.get(this).onActivityResult(i, i2, intent);
    }
}