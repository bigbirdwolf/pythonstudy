package com.chad.library.adapter.base;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseItemDraggableAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    private static final String ERROR_NOT_SAME_ITEMTOUCHHELPER = "Item drag and item swipe should pass the same ItemTouchHelper";
    private static final int NO_TOGGLE_VIEW = 0;
    protected boolean itemDragEnabled;
    protected boolean itemSwipeEnabled;
    protected boolean mDragOnLongPress;
    protected ItemTouchHelper mItemTouchHelper;
    protected OnItemDragListener mOnItemDragListener;
    protected OnItemSwipeListener mOnItemSwipeListener;
    protected View.OnLongClickListener mOnToggleViewLongClickListener;
    protected View.OnTouchListener mOnToggleViewTouchListener;
    protected int mToggleViewId;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter, android.support.v7.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder((BaseItemDraggableAdapter<T, K>) ((BaseViewHolder) viewHolder), i);
    }

    public BaseItemDraggableAdapter(List<T> list) {
        super(list);
        this.mToggleViewId = 0;
        this.itemDragEnabled = false;
        this.itemSwipeEnabled = false;
        this.mDragOnLongPress = true;
    }

    public BaseItemDraggableAdapter(int i, List<T> list) {
        super(i, list);
        this.mToggleViewId = 0;
        this.itemDragEnabled = false;
        this.itemSwipeEnabled = false;
        this.mDragOnLongPress = true;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void onBindViewHolder(K k, int i) {
        super.onBindViewHolder((BaseItemDraggableAdapter<T, K>) k, i);
        int itemViewType = k.getItemViewType();
        if (this.mItemTouchHelper == null || !this.itemDragEnabled || itemViewType == 546 || itemViewType == 273 || itemViewType == 1365 || itemViewType == 819) {
            return;
        }
        if (this.mToggleViewId != 0) {
            View view = k.getView(this.mToggleViewId);
            if (view != null) {
                view.setTag(R.id.BaseQuickAdapter_viewholder_support, k);
                if (this.mDragOnLongPress) {
                    view.setOnLongClickListener(this.mOnToggleViewLongClickListener);
                    return;
                } else {
                    view.setOnTouchListener(this.mOnToggleViewTouchListener);
                    return;
                }
            }
            return;
        }
        k.itemView.setTag(R.id.BaseQuickAdapter_viewholder_support, k);
        k.itemView.setOnLongClickListener(this.mOnToggleViewLongClickListener);
    }

    public void setToggleViewId(int i) {
        this.mToggleViewId = i;
    }

    public void setToggleDragOnLongPress(boolean z) {
        this.mDragOnLongPress = z;
        if (this.mDragOnLongPress) {
            this.mOnToggleViewTouchListener = null;
            this.mOnToggleViewLongClickListener = new View.OnLongClickListener() { // from class: com.chad.library.adapter.base.BaseItemDraggableAdapter.1
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    if (BaseItemDraggableAdapter.this.mItemTouchHelper == null || !BaseItemDraggableAdapter.this.itemDragEnabled) {
                        return true;
                    }
                    BaseItemDraggableAdapter.this.mItemTouchHelper.startDrag((RecyclerView.ViewHolder) view.getTag(R.id.BaseQuickAdapter_viewholder_support));
                    return true;
                }
            };
            return;
        }
        this.mOnToggleViewTouchListener = new View.OnTouchListener() { // from class: com.chad.library.adapter.base.BaseItemDraggableAdapter.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) != 0 || BaseItemDraggableAdapter.this.mDragOnLongPress) {
                    return false;
                }
                if (BaseItemDraggableAdapter.this.mItemTouchHelper == null || !BaseItemDraggableAdapter.this.itemDragEnabled) {
                    return true;
                }
                BaseItemDraggableAdapter.this.mItemTouchHelper.startDrag((RecyclerView.ViewHolder) view.getTag(R.id.BaseQuickAdapter_viewholder_support));
                return true;
            }
        };
        this.mOnToggleViewLongClickListener = null;
    }

    public void enableDragItem(@NonNull ItemTouchHelper itemTouchHelper) {
        enableDragItem(itemTouchHelper, 0, true);
    }

    public void enableDragItem(@NonNull ItemTouchHelper itemTouchHelper, int i, boolean z) {
        this.itemDragEnabled = true;
        this.mItemTouchHelper = itemTouchHelper;
        setToggleViewId(i);
        setToggleDragOnLongPress(z);
    }

    public void disableDragItem() {
        this.itemDragEnabled = false;
        this.mItemTouchHelper = null;
    }

    public boolean isItemDraggable() {
        return this.itemDragEnabled;
    }

    public void enableSwipeItem() {
        this.itemSwipeEnabled = true;
    }

    public void disableSwipeItem() {
        this.itemSwipeEnabled = false;
    }

    public boolean isItemSwipeEnable() {
        return this.itemSwipeEnabled;
    }

    public void setOnItemDragListener(OnItemDragListener onItemDragListener) {
        this.mOnItemDragListener = onItemDragListener;
    }

    public int getViewHolderPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() - getHeaderLayoutCount();
    }

    public void onItemDragStart(RecyclerView.ViewHolder viewHolder) {
        if (this.mOnItemDragListener == null || !this.itemDragEnabled) {
            return;
        }
        this.mOnItemDragListener.onItemDragStart(viewHolder, getViewHolderPosition(viewHolder));
    }

    public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        int viewHolderPosition = getViewHolderPosition(viewHolder);
        int viewHolderPosition2 = getViewHolderPosition(viewHolder2);
        if (viewHolderPosition < viewHolderPosition2) {
            int i = viewHolderPosition;
            while (i < viewHolderPosition2) {
                int i2 = i + 1;
                Collections.swap(this.mData, i, i2);
                i = i2;
            }
        } else {
            for (int i3 = viewHolderPosition; i3 > viewHolderPosition2; i3--) {
                Collections.swap(this.mData, i3, i3 - 1);
            }
        }
        notifyItemMoved(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        if (this.mOnItemDragListener == null || !this.itemDragEnabled) {
            return;
        }
        this.mOnItemDragListener.onItemDragMoving(viewHolder, viewHolderPosition, viewHolder2, viewHolderPosition2);
    }

    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder) {
        if (this.mOnItemDragListener == null || !this.itemDragEnabled) {
            return;
        }
        this.mOnItemDragListener.onItemDragEnd(viewHolder, getViewHolderPosition(viewHolder));
    }

    public void setOnItemSwipeListener(OnItemSwipeListener onItemSwipeListener) {
        this.mOnItemSwipeListener = onItemSwipeListener;
    }

    public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder) {
        if (this.mOnItemSwipeListener == null || !this.itemSwipeEnabled) {
            return;
        }
        this.mOnItemSwipeListener.onItemSwipeStart(viewHolder, getViewHolderPosition(viewHolder));
    }

    public void onItemSwipeClear(RecyclerView.ViewHolder viewHolder) {
        if (this.mOnItemSwipeListener == null || !this.itemSwipeEnabled) {
            return;
        }
        this.mOnItemSwipeListener.clearView(viewHolder, getViewHolderPosition(viewHolder));
    }

    public void onItemSwiped(RecyclerView.ViewHolder viewHolder) {
        if (this.mOnItemSwipeListener != null && this.itemSwipeEnabled) {
            this.mOnItemSwipeListener.onItemSwiped(viewHolder, getViewHolderPosition(viewHolder));
        }
        this.mData.remove(getViewHolderPosition(viewHolder));
        notifyItemRemoved(viewHolder.getAdapterPosition());
    }

    public void onItemSwiping(Canvas canvas, RecyclerView.ViewHolder viewHolder, float f, float f2, boolean z) {
        if (this.mOnItemSwipeListener == null || !this.itemSwipeEnabled) {
            return;
        }
        this.mOnItemSwipeListener.onItemSwipeMoving(canvas, viewHolder, f, f2, z);
    }
}