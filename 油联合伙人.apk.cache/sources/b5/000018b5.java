package com.yltx.oil.partner.modules.mine.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.HomeGoodsResponse;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class InvitationRecyclerAdapter extends BaseQuickAdapter<HomeGoodsResponse, BaseViewHolder> {
    List<HomeGoodsResponse> data;

    public InvitationRecyclerAdapter(List<HomeGoodsResponse> list) {
        super(R.layout.layout_invitation_item, list);
        this.data = new ArrayList();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<HomeGoodsResponse> list) {
        super.setNewData(list);
        this.data = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, HomeGoodsResponse homeGoodsResponse) {
        ((TextView) baseViewHolder.getView(R.id.tv_title)).setText(homeGoodsResponse.getPname());
        ((TextView) baseViewHolder.getView(R.id.tv_price)).setText("¥".concat(homeGoodsResponse.getPcash()));
        ((TextView) baseViewHolder.getView(R.id.tv_shop)).setText(homeGoodsResponse.getPname());
        ((TextView) baseViewHolder.getView(R.id.tv_hejinum)).setText(homeGoodsResponse.getPname());
        ((TextView) baseViewHolder.getView(R.id.tv_hejimoney)).setText(homeGoodsResponse.getPname());
        ((TextView) baseViewHolder.getView(R.id.tv_tianshu)).setText(homeGoodsResponse.getPname());
        ((TextView) baseViewHolder.getView(R.id.tv_go)).setText(homeGoodsResponse.getPname());
        ((TextView) baseViewHolder.getView(R.id.tv_time)).setText(homeGoodsResponse.getPname());
        AlbumDisplayUtils.displayHomeGoodsImg(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_ico), homeGoodsResponse.getPphoto());
    }
}