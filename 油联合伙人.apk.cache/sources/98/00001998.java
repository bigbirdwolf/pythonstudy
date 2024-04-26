package com.yltx.oil.partner.modules.profit.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class ModificationBankCardsActivity_ViewBinding implements Unbinder {
    private ModificationBankCardsActivity target;

    @UiThread
    public ModificationBankCardsActivity_ViewBinding(ModificationBankCardsActivity modificationBankCardsActivity) {
        this(modificationBankCardsActivity, modificationBankCardsActivity.getWindow().getDecorView());
    }

    @UiThread
    public ModificationBankCardsActivity_ViewBinding(ModificationBankCardsActivity modificationBankCardsActivity, View view) {
        this.target = modificationBankCardsActivity;
        modificationBankCardsActivity.tvBankcard = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_bankcard, "field 'tvBankcard'", TextView.class);
        modificationBankCardsActivity.tvXgYhk = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_xg_yhk, "field 'tvXgYhk'", TextView.class);
        modificationBankCardsActivity.tvName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_name, "field 'tvName'", TextView.class);
        modificationBankCardsActivity.tvIdcard = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_idcard, "field 'tvIdcard'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ModificationBankCardsActivity modificationBankCardsActivity = this.target;
        if (modificationBankCardsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        modificationBankCardsActivity.tvBankcard = null;
        modificationBankCardsActivity.tvXgYhk = null;
        modificationBankCardsActivity.tvName = null;
        modificationBankCardsActivity.tvIdcard = null;
    }
}