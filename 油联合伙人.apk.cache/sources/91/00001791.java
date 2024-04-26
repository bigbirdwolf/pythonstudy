package com.yltx.oil.partner.modules.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.home.response.SeekResponse;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.math.BigDecimal;
import java.util.List;

/* loaded from: classes.dex */
public class Seek_Recyclerview_Adapter extends BaseQuickAdapter<SeekResponse, BaseViewHolder> {
    public Seek_Recyclerview_Adapter(List<SeekResponse> list) {
        super(R.layout.layout_ss_item, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, SeekResponse seekResponse) {
        AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.item_ss_goods_iamge), seekResponse.getGoodsImage());
        baseViewHolder.setText(R.id.ss_Name, seekResponse.getGoodsName() + "");
        baseViewHolder.setText(R.id.ss_goods_price, "¥" + seekResponse.getSalePrice() + "");
        StringBuilder sb = new StringBuilder();
        sb.append("¥");
        sb.append(seekResponse.getMarketPrice());
        baseViewHolder.setText(R.id.ss_original_price, sb.toString());
        TextView textView = (TextView) baseViewHolder.getView(R.id.ss_original_price);
        textView.getPaint().setFlags(16);
        textView.getPaint().setAntiAlias(true);
        if (seekResponse.getCommissionRate() != null) {
            BigDecimal scale = new BigDecimal(seekResponse.getCommissionRate()).setScale(2, 4).multiply(new BigDecimal("100")).setScale(0, 4);
            ((TextView) baseViewHolder.getView(R.id.ss_bm)).setText(scale.toString() + "%");
        } else {
            ((TextView) baseViewHolder.getView(R.id.ss_bm)).setText("0%");
        }
        baseViewHolder.setText(R.id.tv_sl, "已售" + seekResponse.getBuyCount() + "件");
        if (seekResponse.getCommission() != null) {
            BigDecimal scale2 = new BigDecimal(seekResponse.getCommission()).setScale(2, 4);
            ((TextView) baseViewHolder.getView(R.id.ss_fxz)).setText(scale2.toString() + "元");
            return;
        }
        ((TextView) baseViewHolder.getView(R.id.ss_fxz)).setText("0.00元");
    }
}