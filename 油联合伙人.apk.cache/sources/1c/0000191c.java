package com.yltx.oil.partner.modules.oiltrade.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.StoredValueCardResp;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class CZKAdapter extends BaseQuickAdapter<StoredValueCardResp, BaseViewHolder> {
    List<StoredValueCardResp> data;

    public CZKAdapter(List<StoredValueCardResp> list) {
        super(R.layout.layout_czk_item, list);
        this.data = new ArrayList();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<StoredValueCardResp> list) {
        super.setNewData(list);
        this.data = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, StoredValueCardResp storedValueCardResp) {
        AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.item_czk_goods_iamge), storedValueCardResp.getPhoto());
        baseViewHolder.setText(R.id.tv_czk_name, storedValueCardResp.getCardName());
        baseViewHolder.setText(R.id.tv_shop_name, storedValueCardResp.getUseRule());
        BigDecimal scale = new BigDecimal(storedValueCardResp.getAmt()).setScale(2, 4);
        BigDecimal scale2 = new BigDecimal(storedValueCardResp.getCommission()).setScale(2, 4);
        baseViewHolder.setText(R.id.goods_price, "¥" + scale.toString());
        baseViewHolder.setText(R.id.tvfxje, "分享赚" + scale2.toString() + "元");
    }
}