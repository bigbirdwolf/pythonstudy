package com.yltx.oil.partner.modules.home.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.HomeGoodsResponse;
import java.util.List;

/* loaded from: classes.dex */
public class MessageBulletinAdapter extends BaseQuickAdapter<HomeGoodsResponse, BaseViewHolder> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, HomeGoodsResponse homeGoodsResponse) {
    }

    public MessageBulletinAdapter(@Nullable List<HomeGoodsResponse> list) {
        super(R.layout.adapter_message_bulletin, list);
    }
}