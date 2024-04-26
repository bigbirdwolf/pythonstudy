package com.yltx.oil.partner.modules.profit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.BankInfoResp;
import com.yltx.oil.partner.modules.profit.presenter.ModificationPresenter;
import com.yltx.oil.partner.modules.profit.view.TxModificationView;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class ModificationBankCardsActivity extends ToolBarActivity implements TxModificationView {
    @Inject
    public ModificationPresenter presenter;
    @BindView(R.id.tv_bankcard)
    TextView tvBankcard;
    @BindView(R.id.tv_idcard)
    TextView tvIdcard;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_xg_yhk)
    TextView tvXgYhk;

    @Override // com.yltx.oil.partner.modules.profit.view.TxModificationView
    public void applyFailed() {
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ModificationBankCardsActivity.class);
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_modificationbankcards);
        ButterKnife.bind(this);
        this.presenter.attachView(this);
        setupUI();
        bindListener();
    }

    private void setupUI() {
        setToolbarTitle("绑定银行卡");
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.presenter.getBankCard();
    }

    private void bindListener() {
        Rx.click(this.tvXgYhk, new Action1() { // from class: com.yltx.oil.partner.modules.profit.activity.-$$Lambda$ModificationBankCardsActivity$4uLaxbTZi2o-pSbi_MGPnAlo6tU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ModificationBankCardsActivity.lambda$bindListener$0(ModificationBankCardsActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(ModificationBankCardsActivity modificationBankCardsActivity, Void r5) {
        modificationBankCardsActivity.getNavigator().navigateToBindingbankcards(modificationBankCardsActivity.getContext(), 1, modificationBankCardsActivity.tvName.getText().toString(), modificationBankCardsActivity.tvIdcard.getText().toString());
    }

    @Override // com.yltx.oil.partner.modules.profit.view.TxModificationView
    public void applySuccess(HttpResult<BankInfoResp> httpResult) {
        this.tvName.setText(httpResult.getData().getRealname());
        this.tvBankcard.setText(httpResult.getData().getBankNo());
        this.tvIdcard.setText(httpResult.getData().getIdcard());
    }
}