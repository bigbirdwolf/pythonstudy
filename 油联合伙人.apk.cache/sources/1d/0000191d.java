package com.yltx.oil.partner.modules.oiltrade.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.GiftCardResp;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GiftCardAdapter extends BaseQuickAdapter<GiftCardResp, BaseViewHolder> {
    List<GiftCardResp> data;

    public GiftCardAdapter(List<GiftCardResp> list) {
        super(R.layout.layout_gift_card_item, list);
        this.data = new ArrayList();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<GiftCardResp> list) {
        super.setNewData(list);
        this.data = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, GiftCardResp giftCardResp) {
        ((TextView) baseViewHolder.getView(R.id.tv_jianshu)).setText("已售：" + giftCardResp.getBuyCount() + "件");
        AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.item_gift_iamge), giftCardResp.getPhoto());
        if (giftCardResp.getName() != null) {
            ((TextView) baseViewHolder.getView(R.id.tv_gifttitle)).setText(giftCardResp.getName());
        } else {
            ((TextView) baseViewHolder.getView(R.id.tv_gifttitle)).setText("暂无");
        }
        ((TextView) baseViewHolder.getView(R.id.goods_price)).setText("¥" + giftCardResp.getSaleprice() + "");
        if (giftCardResp.getCommission() != null) {
            BigDecimal scale = new BigDecimal(giftCardResp.getCommission()).setScale(2, 4);
            ((TextView) baseViewHolder.getView(R.id.tv_fxz)).setText(scale.toString() + "元");
        } else {
            ((TextView) baseViewHolder.getView(R.id.tv_fxz)).setText("0.00元");
        }
        if (giftCardResp.getCommissionRate() != null) {
            BigDecimal scale2 = new BigDecimal(giftCardResp.getCommissionRate()).multiply(new BigDecimal(100)).setScale(2, 4);
            ((TextView) baseViewHolder.getView(R.id.tv_bili)).setText(scale2.toString() + "%");
            return;
        }
        ((TextView) baseViewHolder.getView(R.id.tv_bili)).setText("0%");
    }
}