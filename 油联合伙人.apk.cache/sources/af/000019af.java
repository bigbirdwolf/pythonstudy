package com.yltx.oil.partner.modules.profit.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.profit.response.FragmentProfit_yjjsjl_Response;
import java.util.List;

/* loaded from: classes.dex */
public class Sett_Recyclerview_Adapter extends BaseQuickAdapter<FragmentProfit_yjjsjl_Response.RecordBean, BaseViewHolder> {
    public Sett_Recyclerview_Adapter(@Nullable List<FragmentProfit_yjjsjl_Response.RecordBean> list) {
        super(R.layout.sy_jsjl_recyclerview_adapter, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, FragmentProfit_yjjsjl_Response.RecordBean recordBean) {
        ((TextView) baseViewHolder.getView(R.id.sy_jsjl_recyclerview_year)).setText(recordBean.getDateTime() + "");
        ((TextView) baseViewHolder.getView(R.id.sy_jsjl_recyclerview_money)).setText(recordBean.getRebateAmount() + "");
    }
}