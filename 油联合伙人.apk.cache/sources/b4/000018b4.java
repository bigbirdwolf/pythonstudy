package com.yltx.oil.partner.modules.mine.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.mine.response.ComplaintResponse;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ComplainRecyclerAdapter extends BaseQuickAdapter<ComplaintResponse, BaseViewHolder> {
    List<ComplaintResponse> data;
    String status;
    int type;
    String typee;

    public ComplainRecyclerAdapter(List<ComplaintResponse> list, int i) {
        super(R.layout.layout_complain_item, list);
        this.data = new ArrayList();
        this.type = i;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<ComplaintResponse> list) {
        super.setNewData(list);
        this.data = list;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setType(String str) {
        this.typee = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, ComplaintResponse complaintResponse) {
        ((TextView) baseViewHolder.getView(R.id.tv_title)).setText(complaintResponse.getGoodsName());
        ((TextView) baseViewHolder.getView(R.id.tv_voucherCode)).setText("订单号：" + complaintResponse.getVoucherCode());
        ((TextView) baseViewHolder.getView(R.id.tv_price)).setText("¥".concat(String.valueOf(complaintResponse.getSaleAmount())));
        if (TextUtils.isEmpty(complaintResponse.getStoreName())) {
            ((TextView) baseViewHolder.getView(R.id.tv_shop)).setVisibility(8);
        } else {
            ((TextView) baseViewHolder.getView(R.id.tv_shop)).setVisibility(0);
            ((TextView) baseViewHolder.getView(R.id.tv_shop)).setText("所属店铺：" + complaintResponse.getStoreName());
        }
        ((TextView) baseViewHolder.getView(R.id.tv_hejimoney)).setText("¥".concat(String.valueOf(complaintResponse.getTotalAmount())));
        ((TextView) baseViewHolder.getView(R.id.tv_tianshu)).setText("剩余天数" + complaintResponse.getRemainDays() + "天");
        ((TextView) baseViewHolder.getView(R.id.tv_time)).setText(complaintResponse.getOrderTime());
        if (this.status.equals("1")) {
            ((TextView) baseViewHolder.getView(R.id.tv_go)).setText("处理中");
        } else if (this.status.equals("2")) {
            ((TextView) baseViewHolder.getView(R.id.tv_go)).setText("已完成");
        } else {
            ((TextView) baseViewHolder.getView(R.id.tv_go)).setText("发起投诉");
        }
        AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_ico), complaintResponse.getPhoto());
    }
}