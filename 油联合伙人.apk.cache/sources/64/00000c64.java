package com.chad.library.adapter.base.callback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.chad.library.R;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;

/* loaded from: classes.dex */
public class ItemDragAndSwipeCallback extends ItemTouchHelper.Callback {
    BaseItemDraggableAdapter mAdapter;
    float mMoveThreshold = 0.1f;
    float mSwipeThreshold = 0.7f;
    int mDragMoveFlags = 15;
    int mSwipeMoveFlags = 32;

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return false;
    }

    public ItemDragAndSwipeCallback(BaseItemDraggableAdapter baseItemDraggableAdapter) {
        this.mAdapter = baseItemDraggableAdapter;
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return this.mAdapter.isItemSwipeEnable();
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 2 && !isViewCreateByAdapter(viewHolder)) {
            this.mAdapter.onItemDragStart(viewHolder);
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, true);
        } else if (i == 1 && !isViewCreateByAdapter(viewHolder)) {
            this.mAdapter.onItemSwipeStart(viewHolder);
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, true);
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (isViewCreateByAdapter(viewHolder)) {
            return;
        }
        if (viewHolder.itemView.getTag(R.id.BaseQuickAdapter_dragging_support) != null && ((Boolean) viewHolder.itemView.getTag(R.id.BaseQuickAdapter_dragging_support)).booleanValue()) {
            this.mAdapter.onItemDragEnd(viewHolder);
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, false);
        }
        if (viewHolder.itemView.getTag(R.id.BaseQuickAdapter_swiping_support) == null || !((Boolean) viewHolder.itemView.getTag(R.id.BaseQuickAdapter_swiping_support)).booleanValue()) {
            return;
        }
        this.mAdapter.onItemSwipeClear(viewHolder);
        viewHolder.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, false);
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (isViewCreateByAdapter(viewHolder)) {
            return makeMovementFlags(0, 0);
        }
        return makeMovementFlags(this.mDragMoveFlags, this.mSwipeMoveFlags);
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return viewHolder.getItemViewType() == viewHolder2.getItemViewType();
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
        super.onMoved(recyclerView, viewHolder, i, viewHolder2, i2, i3, i4);
        this.mAdapter.onItemDragMoving(viewHolder, viewHolder2);
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        if (isViewCreateByAdapter(viewHolder)) {
            return;
        }
        this.mAdapter.onItemSwiped(viewHolder);
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
        return this.mMoveThreshold;
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return this.mSwipeThreshold;
    }

    public void setSwipeThreshold(float f) {
        this.mSwipeThreshold = f;
    }

    public void setMoveThreshold(float f) {
        this.mMoveThreshold = f;
    }

    public void setDragMoveFlags(int i) {
        this.mDragMoveFlags = i;
    }

    public void setSwipeMoveFlags(int i) {
        this.mSwipeMoveFlags = i;
    }

    @Override // android.support.v7.widget.helper.ItemTouchHelper.Callback
    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        super.onChildDrawOver(canvas, recyclerView, viewHolder, f, f2, i, z);
        if (i != 1 || isViewCreateByAdapter(viewHolder)) {
            return;
        }
        View view = viewHolder.itemView;
        canvas.save();
        if (f > 0.0f) {
            canvas.clipRect(view.getLeft(), view.getTop(), view.getLeft() + f, view.getBottom());
            canvas.translate(view.getLeft(), view.getTop());
        } else {
            canvas.clipRect(view.getRight() + f, view.getTop(), view.getRight(), view.getBottom());
            canvas.translate(view.getRight() + f, view.getTop());
        }
        this.mAdapter.onItemSwiping(canvas, viewHolder, f, f2, z);
        canvas.restore();
    }

    private boolean isViewCreateByAdapter(RecyclerView.ViewHolder viewHolder) {
        int itemViewType = viewHolder.getItemViewType();
        BaseItemDraggableAdapter baseItemDraggableAdapter = this.mAdapter;
        if (itemViewType != 273) {
            BaseItemDraggableAdapter baseItemDraggableAdapter2 = this.mAdapter;
            if (itemViewType != 546) {
                BaseItemDraggableAdapter baseItemDraggableAdapter3 = this.mAdapter;
                if (itemViewType != 819) {
                    BaseItemDraggableAdapter baseItemDraggableAdapter4 = this.mAdapter;
                    return itemViewType == 1365;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}