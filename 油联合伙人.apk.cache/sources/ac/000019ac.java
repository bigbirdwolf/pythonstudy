package com.yltx.oil.partner.modules.profit.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.profit.response.GeneralizeResponse;
import java.util.List;

/* loaded from: classes.dex */
public class Data_Recyclerview_Adapter extends BaseQuickAdapter<GeneralizeResponse.DataBean, BaseViewHolder> {
    public Data_Recyclerview_Adapter(@Nullable List<GeneralizeResponse.DataBean> list) {
        super(R.layout.sy_tgsj_recyclerview_adapter, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, GeneralizeResponse.DataBean dataBean) {
        ((TextView) baseViewHolder.getView(R.id.sy_tgsj_qbsj_date)).setText(dataBean.getDateTime() + "");
        ((TextView) baseViewHolder.getView(R.id.sy_tgsj_qbsj_djcs_shu)).setText(dataBean.getWatchCount() + "");
        ((TextView) baseViewHolder.getView(R.id.sy_tgsj_qbsj_xse_shu)).setText(dataBean.getOrderAmount() + "");
        ((TextView) baseViewHolder.getView(R.id.sy_tgsj_qbsj_yxdds_shu)).setText(dataBean.getBuyCount() + "");
        ((TextView) baseViewHolder.getView(R.id.sy_tgsj_qbsj_ygsr_shu)).setText(dataBean.getRebateAmount() + "");
    }
}