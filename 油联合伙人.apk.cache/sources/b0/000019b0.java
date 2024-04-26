package com.yltx.oil.partner.modules.profit.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.profit.response.AllordersResponse;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.util.List;

/* loaded from: classes.dex */
public class Sy_Ddmx_ELV_Adapter extends BaseQuickAdapter<AllordersResponse.DataBean, BaseViewHolder> {
    private String name;
    private String sp;

    public Sy_Ddmx_ELV_Adapter(@Nullable List<AllordersResponse.DataBean> list, String str, String str2) {
        super(R.layout.sy_ddmx_list_yi, list);
        this.sp = str;
        this.name = str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, AllordersResponse.DataBean dataBean) {
        ((TextView) baseViewHolder.getView(R.id.ddmx_yi_ddh)).setText(dataBean.getVoucherCode() + "");
        ((TextView) baseViewHolder.getView(R.id.ddmx_er_gmsl_shu)).setText(dataBean.getCount() + "");
        ((TextView) baseViewHolder.getView(R.id.ddmx_er_ddze_qian_shu)).setText(dataBean.getOrderAmount() + "");
        ((TextView) baseViewHolder.getView(R.id.ddmx_tc_er_qian_shu)).setText(String.format("%.2f", Double.valueOf(dataBean.getRebateRate())));
        ((TextView) baseViewHolder.getView(R.id.ddmx_er_ygsr_qian_shu)).setText(dataBean.getRebateAmount() + "");
        ((TextView) baseViewHolder.getView(R.id.ddmx_er_date)).setText(dataBean.getOrderTime() + "");
        ((TextView) baseViewHolder.getView(R.id.ddmx_yi_image)).setText(dataBean.getStatus() + "");
        ((TextView) baseViewHolder.getView(R.id.ddmx_er_title)).setText(this.name + "");
        ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp)).setVisibility(0);
        ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setVisibility(8);
        if (this.sp != null) {
            AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.ddmx_er_image), dataBean.getPhoto() + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp)).setVisibility(8);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setVisibility(0);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_title)).setText(this.name + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setText(dataBean.getName() + "");
            this.sp = null;
            if (this.name.equals("商品")) {
                AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.ddmx_er_image), dataBean.getPhoto() + "");
                ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp)).setVisibility(8);
                ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setVisibility(8);
                ((TextView) baseViewHolder.getView(R.id.ddmx_er_title)).setText(dataBean.getName() + "");
                ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setText("");
            }
        } else if (this.name.equals("油品贸易")) {
            AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.ddmx_er_image), dataBean.getPhoto() + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp)).setVisibility(0);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setVisibility(0);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_title)).setText(this.name + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setText(dataBean.getDays() + "");
        } else if (this.name.equals("加油卡")) {
            AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.ddmx_er_image), dataBean.getPhoto() + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp)).setVisibility(0);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setVisibility(0);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_title)).setText(this.name + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setText(dataBean.getDays() + "");
        } else if (this.name.equals("商品")) {
            AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.ddmx_er_image), dataBean.getPhoto() + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp)).setVisibility(8);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setVisibility(8);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_title)).setText(dataBean.getName() + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setText("");
        } else {
            AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.ddmx_er_image), dataBean.getPhoto() + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp)).setVisibility(8);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setVisibility(0);
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_title)).setText(this.name + "");
            ((TextView) baseViewHolder.getView(R.id.ddmx_er_ssdp_title)).setText(dataBean.getName() + "");
        }
    }
}