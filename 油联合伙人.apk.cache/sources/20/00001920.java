package com.yltx.oil.partner.modules.oiltrade.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.FinanceCardlResp;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class YPMYAdapter extends BaseQuickAdapter<FinanceCardlResp, BaseViewHolder> {
    private Context activity;
    List<FinanceCardlResp> data;

    public YPMYAdapter(List<FinanceCardlResp> list) {
        super(R.layout.layout_ypmy_item, list);
        this.data = new ArrayList();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<FinanceCardlResp> list) {
        super.setNewData(list);
        this.data = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, FinanceCardlResp financeCardlResp) {
        ((TextView) baseViewHolder.getView(R.id.tv_tip)).setText(financeCardlResp.getTag());
        AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.item_jyk_goods_iamge), financeCardlResp.getListPhoto());
        ((TextView) baseViewHolder.getView(R.id.tv_ypmytitle)).setText(financeCardlResp.getName());
        ((TextView) baseViewHolder.getView(R.id.tv_ypmyzq)).setText("周期：" + financeCardlResp.getDays() + "天");
        StringBuilder sb = new StringBuilder();
        sb.append("批发价：¥");
        sb.append(financeCardlResp.getTonPrice());
        ((TextView) baseViewHolder.getView(R.id.tv_original_price)).setText(sb.toString());
        ((TextView) baseViewHolder.getView(R.id.goods_price)).setText("¥" + financeCardlResp.getSummary());
        ((TextView) baseViewHolder.getView(R.id.tv_jianshu)).setText("已售：" + financeCardlResp.getBuyCount() + "件");
        if (!TextUtils.isEmpty(financeCardlResp.getCommissionRate())) {
            ((RelativeLayout) baseViewHolder.getView(R.id.ll_xf)).setVisibility(0);
            ((TextView) baseViewHolder.getView(R.id.tv_yjbl)).setVisibility(0);
            ((TextView) baseViewHolder.getView(R.id.tv_bili)).setVisibility(0);
            BigDecimal scale = new BigDecimal(Double.parseDouble(financeCardlResp.getShraeMoney())).setScale(2, 4);
            BigDecimal scale2 = new BigDecimal(financeCardlResp.getCommissionRate()).multiply(new BigDecimal(100)).setScale(2, 4);
            ((TextView) baseViewHolder.getView(R.id.tv_bili)).setText(scale2.toString() + "%");
            ((TextView) baseViewHolder.getView(R.id.tv_fxz)).setText(scale.toString() + "元");
            return;
        }
        ((TextView) baseViewHolder.getView(R.id.tv_bili)).setText("  ");
        ((TextView) baseViewHolder.getView(R.id.tv_fxz)).setText("   ");
    }
}