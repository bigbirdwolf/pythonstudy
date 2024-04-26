package com.chad.library.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseSectionQuickAdapter<T extends SectionEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    protected static final int SECTION_HEADER_VIEW = 1092;
    protected int mSectionHeadResId;

    protected abstract void convertHead(K k, T t);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter, android.support.v7.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder((BaseSectionQuickAdapter<T, K>) ((BaseViewHolder) viewHolder), i);
    }

    public BaseSectionQuickAdapter(int i, int i2, List<T> list) {
        super(i, list);
        this.mSectionHeadResId = i2;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected int getDefItemViewType(int i) {
        if (((SectionEntity) this.mData.get(i)).isHeader) {
            return SECTION_HEADER_VIEW;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public K onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        if (i == SECTION_HEADER_VIEW) {
            return createBaseViewHolder(getItemView(this.mSectionHeadResId, viewGroup));
        }
        return (K) super.onCreateDefViewHolder(viewGroup, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public boolean isFixedViewType(int i) {
        return super.isFixedViewType(i) || i == SECTION_HEADER_VIEW;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void onBindViewHolder(K k, int i) {
        if (k.getItemViewType() == SECTION_HEADER_VIEW) {
            setFullSpan(k);
            convertHead(k, (SectionEntity) this.mData.get(k.getLayoutPosition() - getHeaderLayoutCount()));
            return;
        }
        super.onBindViewHolder((BaseSectionQuickAdapter<T, K>) k, i);
    }
}