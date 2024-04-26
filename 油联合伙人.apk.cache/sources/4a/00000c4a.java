package com.chad.library.adapter.base;

import android.support.annotation.LayoutRes;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseMultiItemQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    private static final int DEFAULT_VIEW_TYPE = -255;
    private SparseArray<Integer> layouts;

    public BaseMultiItemQuickAdapter(List<T> list) {
        super(list);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected int getDefItemViewType(int i) {
        Object obj = this.mData.get(i);
        return obj instanceof MultiItemEntity ? ((MultiItemEntity) obj).getItemType() : DEFAULT_VIEW_TYPE;
    }

    protected void setDefaultViewTypeLayout(@LayoutRes int i) {
        addItemType(DEFAULT_VIEW_TYPE, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public K onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return createBaseViewHolder(viewGroup, getLayoutId(i));
    }

    private int getLayoutId(int i) {
        return this.layouts.get(i).intValue();
    }

    protected void addItemType(int i, @LayoutRes int i2) {
        if (this.layouts == null) {
            this.layouts = new SparseArray<>();
        }
        this.layouts.put(i, Integer.valueOf(i2));
    }
}