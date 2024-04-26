package com.yltx.oil.partner.modules.mine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.RechargePayTypeResp;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class RecharhePayTypeAdapter extends BaseQuickAdapter<RechargePayTypeResp.OutPayBean.OutPayListBean, BaseViewHolder> {
    public RecharhePayTypeAdapter(@Nullable List<RechargePayTypeResp.OutPayBean.OutPayListBean> list) {
        super(R.layout.recycler_view_item, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, RechargePayTypeResp.OutPayBean.OutPayListBean outPayListBean) {
        baseViewHolder.setText(R.id.tv_recycler_paytype, outPayListBean.getName());
        if (outPayListBean.getType().equals("ptalipay")) {
            baseViewHolder.setImageResource(R.id.iv_pay_type, R.mipmap.zfbpay);
        } else if (outPayListBean.getType().equals("ptwechatpay")) {
            baseViewHolder.setImageResource(R.id.iv_pay_type, R.mipmap.wxpay);
        } else if (outPayListBean.getType().equals("sandpay")) {
            baseViewHolder.setImageResource(R.id.iv_pay_type, R.mipmap.kjpay);
        }
        if (outPayListBean.isChecked()) {
            baseViewHolder.getView(R.id.rb_recycler_paytype).setBackgroundResource(R.mipmap.ic_pay_checked);
        } else {
            baseViewHolder.getView(R.id.rb_recycler_paytype).setBackgroundResource(R.mipmap.ic_pay_unchecked);
        }
    }
}