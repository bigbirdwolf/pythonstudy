package com.yltx.oil.partner.modules.mine.adapter;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.modules.mine.response.MyfeedbackResponse;
import com.yltx.oil.partner.utils.XTViewUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MyFeedBackRecyclerView_Adapter extends BaseQuickAdapter<MyfeedbackResponse, BaseViewHolder> {
    List<MyfeedbackResponse> data;

    public MyFeedBackRecyclerView_Adapter(List<MyfeedbackResponse> list) {
        super(R.layout.myfeedback_recyclerview_adapter, list);
        this.data = new ArrayList();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void setNewData(@Nullable List<MyfeedbackResponse> list) {
        super.setNewData(list);
        this.data = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, MyfeedbackResponse myfeedbackResponse) {
        LinearLayout linearLayout = (LinearLayout) baseViewHolder.getView(R.id.feedback_linear);
        if (this.data != null && this.data.size() > 0) {
            if (baseViewHolder.getAdapterPosition() == 0) {
                XTViewUtils.setViewMargin(linearLayout, XTViewUtils.dp2pix(baseViewHolder.getConvertView().getContext(), 10), 0, 0, 0);
            } else if (baseViewHolder.getAdapterPosition() == this.data.size() - 1) {
                XTViewUtils.setViewMargin(linearLayout, 0, XTViewUtils.dp2pix(baseViewHolder.getConvertView().getContext(), 10), 0, 0);
            }
        }
        ((TextView) baseViewHolder.getView(R.id.feedback_time)).setText(myfeedbackResponse.getCreate_time() + "");
        ((TextView) baseViewHolder.getView(R.id.feedback_context)).setText(myfeedbackResponse.getContext() + "");
    }
}