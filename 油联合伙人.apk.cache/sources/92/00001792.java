package com.yltx.oil.partner.modules.home.adapter;

import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.TxHistoryResp;
import java.util.List;

/* loaded from: classes.dex */
public class TxHistoryAdapter extends BaseQuickAdapter<TxHistoryResp.TxListBean, BaseViewHolder> {
    public TxHistoryAdapter(List<TxHistoryResp.TxListBean> list) {
        super(R.layout.item_tx_histroy, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, TxHistoryResp.TxListBean txListBean) {
        char c;
        baseViewHolder.addOnClickListener(R.id.tv_detail);
        ((TextView) baseViewHolder.getView(R.id.tv_account_money)).setText(txListBean.getTxMoney().concat("元"));
        ((TextView) baseViewHolder.getView(R.id.tv_reimbursable_date)).setText(txListBean.getApplyTime());
        TextView textView = (TextView) baseViewHolder.getView(R.id.tv_status);
        textView.setText(txListBean.getValue());
        String status = txListBean.getStatus();
        int hashCode = status.hashCode();
        if (hashCode != 57) {
            switch (hashCode) {
                case 48:
                    if (status.equals("0")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 49:
                    if (status.equals("1")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 50:
                    if (status.equals("2")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 51:
                    if (status.equals("3")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (status.equals("9")) {
                c = 4;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 1:
                textView.setText("审核中");
                textView.setTextColor(this.mContext.getResources().getColor(R.color.textColorHelp));
                textView.setBackground(this.mContext.getResources().getDrawable(R.drawable.shape_status_waiting));
                return;
            case 2:
                textView.setBackground(this.mContext.getResources().getDrawable(R.drawable.shape_status_allowed));
                textView.setTextColor(this.mContext.getResources().getColor(R.color.green));
                return;
            case 3:
                textView.setBackground(this.mContext.getResources().getDrawable(R.drawable.shape_status_refused));
                textView.setTextColor(this.mContext.getResources().getColor(R.color.textColorSecondary));
                return;
            case 4:
                textView.setBackground(this.mContext.getResources().getDrawable(R.drawable.shape_status_canceled));
                textView.setTextColor(this.mContext.getResources().getColor(R.color.textColorSecondary));
                return;
            default:
                return;
        }
    }
}