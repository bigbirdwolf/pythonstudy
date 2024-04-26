package com.yltx.oil.partner.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;

/* loaded from: classes.dex */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {16843284};
    public static final int BOTH_SET = 2;
    public static final int HORIZONTAL_LIST = 0;
    public static final int VERTICAL_LIST = 1;
    private int mDividerHeight;
    private Drawable mDrawable;
    private int mOrientation;
    private Paint mPaint;

    public DividerItemDecoration(Context context, int i) {
        this.mDividerHeight = 2;
        setOrientation(i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(ATTRS);
        this.mDrawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    public DividerItemDecoration(Context context, int i, int i2) {
        this.mDividerHeight = 2;
        setOrientation(i);
        this.mDrawable = ContextCompat.getDrawable(context, i2);
        this.mDividerHeight = this.mDrawable.getIntrinsicHeight();
    }

    public DividerItemDecoration(Context context, int i, int i2, int i3) {
        this.mDividerHeight = 2;
        setOrientation(i);
        this.mDividerHeight = DensityUtils.dp2px(context, i2);
        Log.e("mDividerHeight", this.mDividerHeight + "===================");
        this.mPaint = new Paint(1);
        this.mPaint.setColor(i3);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    public void setOrientation(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("invalid orientation");
        }
        this.mOrientation = i;
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int viewLayoutPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int itemCount = recyclerView.getAdapter().getItemCount();
        switch (this.mOrientation) {
            case 0:
                rect.set(0, 0, 0, viewLayoutPosition != itemCount + (-1) ? this.mDividerHeight : 0);
                return;
            case 1:
                rect.set(0, 0, viewLayoutPosition != itemCount + (-1) ? this.mDividerHeight : 0, 0);
                return;
            case 2:
                int spanCount = getSpanCount(recyclerView);
                if (isLastRaw(recyclerView, viewLayoutPosition, spanCount, itemCount)) {
                    rect.set(0, 0, this.mDividerHeight, 0);
                    return;
                } else if (isLastColum(recyclerView, viewLayoutPosition, spanCount, itemCount)) {
                    rect.set(0, 0, 0, this.mDividerHeight);
                    return;
                } else {
                    rect.set(0, 0, this.mDividerHeight, this.mDividerHeight);
                    return;
                }
            default:
                return;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
        if (this.mOrientation == 1) {
            drawVertical(canvas, recyclerView);
        } else if (this.mOrientation == 0) {
            drawHorizontal(canvas, recyclerView);
        } else {
            drawHorizontal(canvas, recyclerView);
            drawVertical(canvas, recyclerView);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int measuredWidth = recyclerView.getMeasuredWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int bottom = childAt.getBottom() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            int i2 = this.mDividerHeight + bottom;
            Log.e(SocializeProtocolConstants.HEIGHT, i2 + "===================");
            if (this.mDrawable != null) {
                this.mDrawable.setBounds(paddingLeft, bottom, measuredWidth, i2);
                this.mDrawable.draw(canvas);
            }
            if (this.mPaint != null) {
                canvas.drawRect(paddingLeft, bottom, measuredWidth, i2, this.mPaint);
            }
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int paddingTop = recyclerView.getPaddingTop();
        int measuredHeight = recyclerView.getMeasuredHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int right = childAt.getRight() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).rightMargin;
            int i2 = this.mDividerHeight + right;
            if (this.mDrawable != null) {
                this.mDrawable.setBounds(right, paddingTop, i2, measuredHeight);
                this.mDrawable.draw(canvas);
            }
            if (this.mPaint != null) {
                canvas.drawRect(right, paddingTop, i2, measuredHeight, this.mPaint);
            }
        }
    }

    private int getSpanCount(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return -1;
    }

    private boolean isLastColum(RecyclerView recyclerView, int i, int i2, int i3) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getOrientation() == 1 ? (i + 1) % i2 == 0 : i >= i3 - (i3 % i2);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1 ? (i + 1) % i2 == 0 : i >= i3 - (i3 % i2);
        } else {
            return false;
        }
    }

    private boolean isLastRaw(RecyclerView recyclerView, int i, int i2, int i3) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int i4 = i3 - (i3 % i2);
            return ((GridLayoutManager) layoutManager).getOrientation() == 1 ? i >= i4 - (i4 % i2) : (i + 1) % i2 == 0;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1 ? i >= i3 - (i3 % i2) : (i + 1) % i2 == 0;
        } else {
            return false;
        }
    }
}