package com.yltx.oil.partner.modules.profit.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import java.util.List;

/* loaded from: classes.dex */
public class ManageRecyclerAdapter extends BaseQuickAdapter<ManageResponse.StationRecordData, BaseViewHolder> {
    public ManageRecyclerAdapter(List<ManageResponse.StationRecordData> list) {
        super(R.layout.layout_account_item, list);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<ManageResponse.StationRecordData> list) {
        super.setNewData(list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, ManageResponse.StationRecordData stationRecordData) {
        ((TextView) baseViewHolder.getView(R.id.tv_phone)).setText(stationRecordData.getPhone() + "");
        ((TextView) baseViewHolder.getView(R.id.tv_state)).setText("+" + stationRecordData.getRealpayAmount());
        ((TextView) baseViewHolder.getView(R.id.tv_time)).setText(stationRecordData.getTime() + "");
    }
}