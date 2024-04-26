package com.yltx.oil.partner.modules.oiltrade.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.oiltrade.response.FuelCardListResponse;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class JYKAdapter extends BaseQuickAdapter<FuelCardListResponse, BaseViewHolder> {
    List<FuelCardListResponse> data;

    public JYKAdapter(List<FuelCardListResponse> list) {
        super(R.layout.layout_jyk_item, list);
        this.data = new ArrayList();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<FuelCardListResponse> list) {
        super.setNewData(list);
        this.data = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, FuelCardListResponse fuelCardListResponse) {
        ((TextView) baseViewHolder.getView(R.id.tv_jianshu)).setText("已售：" + fuelCardListResponse.getBuyCount() + "件");
        AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.item_jyk_goods_iamge), fuelCardListResponse.getFuelImage());
        baseViewHolder.setText(R.id.jyk_Name, fuelCardListResponse.getFuelName() + "");
        baseViewHolder.setText(R.id.jyk_yue, fuelCardListResponse.getFuelFmName() + "");
        if (fuelCardListResponse.getFuelRebate() != null) {
            BigDecimal scale = new BigDecimal(fuelCardListResponse.getFuelRebate()).multiply(new BigDecimal(100)).setScale(2, 4);
            baseViewHolder.setText(R.id.jyk_yjbl, scale.toString() + "%");
        } else {
            baseViewHolder.setText(R.id.jyk_yjbl, "0%");
        }
        baseViewHolder.setText(R.id.goods_price, "¥" + fuelCardListResponse.getFuelSpecialPrice() + "");
        StringBuilder sb = new StringBuilder();
        sb.append(fuelCardListResponse.getFuelAmt());
        sb.append("");
        new BigDecimal(sb.toString()).setScale(2, 4);
        TextView textView = (TextView) baseViewHolder.getView(R.id.tv_original_price);
        textView.setText("¥" + fuelCardListResponse.getFuelAmt());
        textView.getPaint().setFlags(16);
        textView.getPaint().setAntiAlias(true);
        if (fuelCardListResponse.getRebateAmt() != null) {
            BigDecimal scale2 = new BigDecimal(fuelCardListResponse.getRebateAmt()).setScale(2, 4);
            ((TextView) baseViewHolder.getView(R.id.jyk_fxz)).setText(scale2.toString() + "元");
            return;
        }
        ((TextView) baseViewHolder.getView(R.id.jyk_fxz)).setText("0.00元");
    }
}