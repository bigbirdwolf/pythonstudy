package com.yltx.oil.partner.modules.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.ShopResp;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HomeHotProdRecyclerAdapter extends BaseQuickAdapter<ShopResp, BaseViewHolder> {
    String[] arr;
    List<ShopResp> data;

    public HomeHotProdRecyclerAdapter(List<ShopResp> list) {
        super(R.layout.fragment_home_product_item, list);
        this.data = new ArrayList();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<ShopResp> list) {
        super.setNewData(list);
        this.data = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, ShopResp shopResp) {
        ((TextView) baseViewHolder.getView(R.id.item_home_goods_title)).setText(shopResp.getGoodsName());
        ((TextView) baseViewHolder.getView(R.id.goods_price)).setText("¥".concat(String.valueOf(shopResp.getSalePrice())));
        TextView textView = (TextView) baseViewHolder.getView(R.id.original_price);
        textView.setText("¥" + String.valueOf(shopResp.getMarketPrice()));
        textView.getPaint().setFlags(16);
        textView.getPaint().setAntiAlias(true);
        ((TextView) baseViewHolder.getView(R.id.item_goods_num)).setText("已售" + shopResp.getBuyCount() + "件");
        if (shopResp.getCommission() != null) {
            BigDecimal scale = new BigDecimal(shopResp.getCommission()).setScale(2, 4);
            ((TextView) baseViewHolder.getView(R.id.tv_shop)).setText(scale.toString() + "元");
        } else {
            ((TextView) baseViewHolder.getView(R.id.tv_shop)).setText("0.00元");
        }
        if (shopResp.getCommissionRate() != null) {
            BigDecimal scale2 = new BigDecimal(shopResp.getCommissionRate()).setScale(2, 4).multiply(new BigDecimal("100")).setScale(0, 4);
            ((TextView) baseViewHolder.getView(R.id.tv_shop_yjbl)).setText(scale2.toString() + "%");
        } else {
            ((TextView) baseViewHolder.getView(R.id.tv_shop_yjbl)).setText("0%");
        }
        this.arr = shopResp.getGoodsImage().split(",");
        AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.item_home_goods_iamge), this.arr[0]);
    }
}