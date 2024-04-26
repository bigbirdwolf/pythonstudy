package com.yltx.oil.partner.modules.home.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.home.response.SSLSResponse;
import java.util.List;

/* loaded from: classes.dex */
public class SSLS_Recyclerview_Adapter extends BaseQuickAdapter<SSLSResponse, BaseViewHolder> {
    public SSLS_Recyclerview_Adapter(@Nullable List<SSLSResponse> list) {
        super(R.layout.ssls_recyclerview_adapter, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, SSLSResponse sSLSResponse) {
        ((TextView) baseViewHolder.getView(R.id.ssls_text)).setText(sSLSResponse.getGoodsName() + "");
    }
}