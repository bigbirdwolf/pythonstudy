package com.yltx.oil.partner.modules.profit.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.profit.response.UserAccountConsumeResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AccountRecyclerAdapter extends BaseQuickAdapter<UserAccountConsumeResponse.AccountConsumeitem, BaseViewHolder> {
    List<UserAccountConsumeResponse.AccountConsumeitem> data;

    public AccountRecyclerAdapter(List<UserAccountConsumeResponse.AccountConsumeitem> list) {
        super(R.layout.layout_account_item, list);
        this.data = new ArrayList();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<UserAccountConsumeResponse.AccountConsumeitem> list) {
        super.setNewData(list);
        this.data = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, UserAccountConsumeResponse.AccountConsumeitem accountConsumeitem) {
        ((TextView) baseViewHolder.getView(R.id.tv_phone)).setText(accountConsumeitem.getRemark());
        BigDecimal scale = new BigDecimal(accountConsumeitem.getConsumeAmt()).setScale(2, 4);
        if (accountConsumeitem.getConsumeType().equals("minus")) {
            ((TextView) baseViewHolder.getView(R.id.tv_state)).setText("- ".concat(scale.toString()));
        } else {
            ((TextView) baseViewHolder.getView(R.id.tv_state)).setText("+ ".concat(scale.toString()));
        }
        ((TextView) baseViewHolder.getView(R.id.tv_time)).setText(accountConsumeitem.getConsumeTime());
    }
}