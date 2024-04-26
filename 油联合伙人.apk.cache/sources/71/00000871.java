package com.afollestad.materialdialogs.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ScrollView;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.R;
import com.afollestad.materialdialogs.util.DialogUtils;

/* loaded from: classes.dex */
public class MDRootLayout extends ViewGroup {
    private static final int INDEX_NEGATIVE = 1;
    private static final int INDEX_NEUTRAL = 0;
    private static final int INDEX_POSITIVE = 2;
    private ViewTreeObserver.OnScrollChangedListener mBottomOnScrollChangedListener;
    private int mButtonBarHeight;
    private GravityEnum mButtonGravity;
    private int mButtonHorizontalEdgeMargin;
    private int mButtonPaddingFull;
    private final MDButton[] mButtons;
    private View mContent;
    private Paint mDividerPaint;
    private int mDividerWidth;
    private boolean mDrawBottomDivider;
    private boolean mDrawTopDivider;
    private boolean mForceStack;
    private boolean mIsStacked;
    private boolean mNoTitleNoPadding;
    private int mNoTitlePaddingFull;
    private boolean mReducePaddingNoTitleNoButtons;
    private View mTitleBar;
    private ViewTreeObserver.OnScrollChangedListener mTopOnScrollChangedListener;
    private boolean mUseFullPadding;

    public MDRootLayout(Context context) {
        super(context);
        this.mDrawTopDivider = false;
        this.mDrawBottomDivider = false;
        this.mButtons = new MDButton[3];
        this.mForceStack = false;
        this.mIsStacked = false;
        this.mUseFullPadding = true;
        this.mButtonGravity = GravityEnum.START;
        init(context, null, 0);
    }

    public MDRootLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDrawTopDivider = false;
        this.mDrawBottomDivider = false;
        this.mButtons = new MDButton[3];
        this.mForceStack = false;
        this.mIsStacked = false;
        this.mUseFullPadding = true;
        this.mButtonGravity = GravityEnum.START;
        init(context, attributeSet, 0);
    }

    @TargetApi(11)
    public MDRootLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDrawTopDivider = false;
        this.mDrawBottomDivider = false;
        this.mButtons = new MDButton[3];
        this.mForceStack = false;
        this.mIsStacked = false;
        this.mUseFullPadding = true;
        this.mButtonGravity = GravityEnum.START;
        init(context, attributeSet, i);
    }

    @TargetApi(21)
    public MDRootLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDrawTopDivider = false;
        this.mDrawBottomDivider = false;
        this.mButtons = new MDButton[3];
        this.mForceStack = false;
        this.mIsStacked = false;
        this.mUseFullPadding = true;
        this.mButtonGravity = GravityEnum.START;
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MDRootLayout, i, 0);
        this.mReducePaddingNoTitleNoButtons = obtainStyledAttributes.getBoolean(R.styleable.MDRootLayout_md_reduce_padding_no_title_no_buttons, true);
        obtainStyledAttributes.recycle();
        this.mNoTitlePaddingFull = resources.getDimensionPixelSize(R.dimen.md_notitle_vertical_padding);
        this.mButtonPaddingFull = resources.getDimensionPixelSize(R.dimen.md_button_frame_vertical_padding);
        this.mButtonHorizontalEdgeMargin = resources.getDimensionPixelSize(R.dimen.md_button_padding_frame_side);
        this.mButtonBarHeight = resources.getDimensionPixelSize(R.dimen.md_button_height);
        this.mDividerPaint = new Paint();
        this.mDividerWidth = resources.getDimensionPixelSize(R.dimen.md_divider_height);
        this.mDividerPaint.setColor(DialogUtils.resolveColor(context, R.attr.md_divider_color));
        setWillNotDraw(false);
    }

    public void noTitleNoPadding() {
        this.mNoTitleNoPadding = true;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getId() == R.id.titleFrame) {
                this.mTitleBar = childAt;
            } else if (childAt.getId() == R.id.buttonDefaultNeutral) {
                this.mButtons[0] = (MDButton) childAt;
            } else if (childAt.getId() == R.id.buttonDefaultNegative) {
                this.mButtons[1] = (MDButton) childAt;
            } else if (childAt.getId() == R.id.buttonDefaultPositive) {
                this.mButtons[2] = (MDButton) childAt;
            } else {
                this.mContent = childAt;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0104  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r12, int r13) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.internal.MDRootLayout.onMeasure(int, int):void");
    }

    private static boolean isVisible(View view) {
        boolean z = (view == null || view.getVisibility() == 8) ? false : true;
        return (z && (view instanceof MDButton)) ? ((MDButton) view).getText().toString().trim().length() > 0 : z;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mContent != null) {
            if (this.mDrawTopDivider) {
                int top = this.mContent.getTop();
                canvas.drawRect(0.0f, top - this.mDividerWidth, getMeasuredWidth(), top, this.mDividerPaint);
            }
            if (this.mDrawBottomDivider) {
                int bottom = this.mContent.getBottom();
                canvas.drawRect(0.0f, bottom, getMeasuredWidth(), bottom + this.mDividerWidth, this.mDividerPaint);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int measuredWidth;
        int i10;
        int measuredWidth2;
        MDButton[] mDButtonArr;
        if (isVisible(this.mTitleBar)) {
            int measuredHeight = this.mTitleBar.getMeasuredHeight() + i2;
            this.mTitleBar.layout(i, i2, i3, measuredHeight);
            i2 = measuredHeight;
        } else if (!this.mNoTitleNoPadding && this.mUseFullPadding) {
            i2 += this.mNoTitlePaddingFull;
        }
        if (isVisible(this.mContent)) {
            this.mContent.layout(i, i2, i3, this.mContent.getMeasuredHeight() + i2);
        }
        if (this.mIsStacked) {
            int i11 = i4 - this.mButtonPaddingFull;
            for (MDButton mDButton : this.mButtons) {
                if (isVisible(mDButton)) {
                    mDButton.layout(i, i11 - mDButton.getMeasuredHeight(), i3, i11);
                    i11 -= mDButton.getMeasuredHeight();
                }
            }
        } else {
            if (this.mUseFullPadding) {
                i4 -= this.mButtonPaddingFull;
            }
            int i12 = i4 - this.mButtonBarHeight;
            int i13 = this.mButtonHorizontalEdgeMargin;
            if (isVisible(this.mButtons[2])) {
                if (this.mButtonGravity == GravityEnum.END) {
                    measuredWidth2 = i + i13;
                    i10 = this.mButtons[2].getMeasuredWidth() + measuredWidth2;
                    i5 = -1;
                } else {
                    i10 = i3 - i13;
                    measuredWidth2 = i10 - this.mButtons[2].getMeasuredWidth();
                    i5 = measuredWidth2;
                }
                this.mButtons[2].layout(measuredWidth2, i12, i10, i4);
                i13 += this.mButtons[2].getMeasuredWidth();
            } else {
                i5 = -1;
            }
            if (isVisible(this.mButtons[1])) {
                if (this.mButtonGravity == GravityEnum.END) {
                    i9 = i13 + i;
                    measuredWidth = this.mButtons[1].getMeasuredWidth() + i9;
                } else if (this.mButtonGravity == GravityEnum.START) {
                    measuredWidth = i3 - i13;
                    i9 = measuredWidth - this.mButtons[1].getMeasuredWidth();
                } else {
                    i9 = this.mButtonHorizontalEdgeMargin + i;
                    measuredWidth = this.mButtons[1].getMeasuredWidth() + i9;
                    i6 = measuredWidth;
                    this.mButtons[1].layout(i9, i12, measuredWidth, i4);
                }
                i6 = -1;
                this.mButtons[1].layout(i9, i12, measuredWidth, i4);
            } else {
                i6 = -1;
            }
            if (isVisible(this.mButtons[0])) {
                if (this.mButtonGravity == GravityEnum.END) {
                    i8 = i3 - this.mButtonHorizontalEdgeMargin;
                    i7 = i8 - this.mButtons[0].getMeasuredWidth();
                } else if (this.mButtonGravity == GravityEnum.START) {
                    i7 = i + this.mButtonHorizontalEdgeMargin;
                    i8 = this.mButtons[0].getMeasuredWidth() + i7;
                } else {
                    if (i6 == -1 && i5 != -1) {
                        i6 = i5 - this.mButtons[0].getMeasuredWidth();
                    } else if (i5 == -1 && i6 != -1) {
                        i5 = i6 + this.mButtons[0].getMeasuredWidth();
                    } else if (i5 == -1) {
                        i6 = ((i3 - i) / 2) - (this.mButtons[0].getMeasuredWidth() / 2);
                        i5 = i6 + this.mButtons[0].getMeasuredWidth();
                    }
                    i7 = i6;
                    i8 = i5;
                }
                this.mButtons[0].layout(i7, i12, i8, i4);
            }
        }
        setUpDividersVisibility(this.mContent, true, true);
    }

    public void setForceStack(boolean z) {
        this.mForceStack = z;
        invalidate();
    }

    public void setDividerColor(int i) {
        this.mDividerPaint.setColor(i);
        invalidate();
    }

    public void setButtonGravity(GravityEnum gravityEnum) {
        this.mButtonGravity = gravityEnum;
        invertGravityIfNecessary();
    }

    private void invertGravityIfNecessary() {
        if (Build.VERSION.SDK_INT >= 17 && getResources().getConfiguration().getLayoutDirection() == 1) {
            switch (this.mButtonGravity) {
                case START:
                    this.mButtonGravity = GravityEnum.END;
                    return;
                case END:
                    this.mButtonGravity = GravityEnum.START;
                    return;
                default:
                    return;
            }
        }
    }

    public void setButtonStackedGravity(GravityEnum gravityEnum) {
        MDButton[] mDButtonArr;
        for (MDButton mDButton : this.mButtons) {
            if (mDButton != null) {
                mDButton.setStackedGravity(gravityEnum);
            }
        }
    }

    private void setUpDividersVisibility(final View view, final boolean z, final boolean z2) {
        if (view == null) {
            return;
        }
        if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            if (canScrollViewScroll(scrollView)) {
                addScrollListener(scrollView, z, z2);
                return;
            }
            if (z) {
                this.mDrawTopDivider = false;
            }
            if (z2) {
                this.mDrawBottomDivider = false;
            }
        } else if (view instanceof AdapterView) {
            AdapterView adapterView = (AdapterView) view;
            if (canAdapterViewScroll(adapterView)) {
                addScrollListener(adapterView, z, z2);
                return;
            }
            if (z) {
                this.mDrawTopDivider = false;
            }
            if (z2) {
                this.mDrawBottomDivider = false;
            }
        } else if (view instanceof WebView) {
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.afollestad.materialdialogs.internal.MDRootLayout.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    if (view.getMeasuredHeight() != 0) {
                        if (MDRootLayout.canWebViewScroll((WebView) view)) {
                            MDRootLayout.this.addScrollListener((ViewGroup) view, z, z2);
                        } else {
                            if (z) {
                                MDRootLayout.this.mDrawTopDivider = false;
                            }
                            if (z2) {
                                MDRootLayout.this.mDrawBottomDivider = false;
                            }
                        }
                        view.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                    return true;
                }
            });
        } else if (view instanceof RecyclerView) {
            boolean canRecyclerViewScroll = canRecyclerViewScroll((RecyclerView) view);
            if (z) {
                this.mDrawTopDivider = canRecyclerViewScroll;
            }
            if (z2) {
                this.mDrawBottomDivider = canRecyclerViewScroll;
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            View topView = getTopView(viewGroup);
            setUpDividersVisibility(topView, z, z2);
            View bottomView = getBottomView(viewGroup);
            if (bottomView != topView) {
                setUpDividersVisibility(bottomView, false, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addScrollListener(final ViewGroup viewGroup, final boolean z, final boolean z2) {
        if ((z2 || this.mTopOnScrollChangedListener != null) && !(z2 && this.mBottomOnScrollChangedListener == null)) {
            return;
        }
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.afollestad.materialdialogs.internal.MDRootLayout.2
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                MDButton[] mDButtonArr = MDRootLayout.this.mButtons;
                int length = mDButtonArr.length;
                boolean z3 = false;
                int i = 0;
                while (true) {
                    if (i < length) {
                        MDButton mDButton = mDButtonArr[i];
                        if (mDButton != null && mDButton.getVisibility() != 8) {
                            z3 = true;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if (viewGroup instanceof WebView) {
                    MDRootLayout.this.invalidateDividersForWebView((WebView) viewGroup, z, z2, z3);
                } else {
                    MDRootLayout.this.invalidateDividersForScrollingView(viewGroup, z, z2, z3);
                }
                MDRootLayout.this.invalidate();
            }
        };
        if (!z2) {
            this.mTopOnScrollChangedListener = onScrollChangedListener;
            viewGroup.getViewTreeObserver().addOnScrollChangedListener(this.mTopOnScrollChangedListener);
        } else {
            this.mBottomOnScrollChangedListener = onScrollChangedListener;
            viewGroup.getViewTreeObserver().addOnScrollChangedListener(this.mBottomOnScrollChangedListener);
        }
        onScrollChangedListener.onScrollChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateDividersForScrollingView(ViewGroup viewGroup, boolean z, boolean z2, boolean z3) {
        boolean z4 = true;
        if (z && viewGroup.getChildCount() > 0) {
            this.mDrawTopDivider = (this.mTitleBar == null || this.mTitleBar.getVisibility() == 8 || viewGroup.getScrollY() + viewGroup.getPaddingTop() <= viewGroup.getChildAt(0).getTop()) ? false : true;
        }
        if (!z2 || viewGroup.getChildCount() <= 0) {
            return;
        }
        this.mDrawBottomDivider = (!z3 || (viewGroup.getScrollY() + viewGroup.getHeight()) - viewGroup.getPaddingBottom() >= viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom()) ? false : false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateDividersForWebView(WebView webView, boolean z, boolean z2, boolean z3) {
        boolean z4 = true;
        if (z) {
            this.mDrawTopDivider = (this.mTitleBar == null || this.mTitleBar.getVisibility() == 8 || webView.getScrollY() + webView.getPaddingTop() <= 0) ? false : true;
        }
        if (z2) {
            this.mDrawBottomDivider = (!z3 || ((float) ((webView.getScrollY() + webView.getMeasuredHeight()) - webView.getPaddingBottom())) >= ((float) webView.getContentHeight()) * webView.getScale()) ? false : false;
        }
    }

    public static boolean canRecyclerViewScroll(RecyclerView recyclerView) {
        if (recyclerView == null || recyclerView.getAdapter() == null || recyclerView.getLayoutManager() == null) {
            return false;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            int findLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            if (findLastVisibleItemPosition == -1) {
                return false;
            }
            return !(findLastVisibleItemPosition == itemCount - 1) || (recyclerView.getChildCount() > 0 && recyclerView.getChildAt(recyclerView.getChildCount() - 1).getBottom() > recyclerView.getHeight() - recyclerView.getPaddingBottom());
        }
        throw new MaterialDialog.NotImplementedException("Material Dialogs currently only supports LinearLayoutManager. Please report any new layout managers.");
    }

    private static boolean canScrollViewScroll(ScrollView scrollView) {
        if (scrollView.getChildCount() == 0) {
            return false;
        }
        return (scrollView.getMeasuredHeight() - scrollView.getPaddingTop()) - scrollView.getPaddingBottom() < scrollView.getChildAt(0).getMeasuredHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean canWebViewScroll(WebView webView) {
        return ((float) webView.getMeasuredHeight()) < ((float) webView.getContentHeight()) * webView.getScale();
    }

    private static boolean canAdapterViewScroll(AdapterView adapterView) {
        if (adapterView.getLastVisiblePosition() == -1) {
            return false;
        }
        return !(adapterView.getFirstVisiblePosition() == 0) || !(adapterView.getLastVisiblePosition() == adapterView.getCount() - 1) || adapterView.getChildCount() <= 0 || adapterView.getChildAt(0).getTop() < adapterView.getPaddingTop() || adapterView.getChildAt(adapterView.getChildCount() - 1).getBottom() > adapterView.getHeight() - adapterView.getPaddingBottom();
    }

    @Nullable
    private static View getBottomView(ViewGroup viewGroup) {
        if (viewGroup == null || viewGroup.getChildCount() == 0) {
            return null;
        }
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt.getVisibility() == 0 && childAt.getBottom() == viewGroup.getMeasuredHeight()) {
                return childAt;
            }
        }
        return null;
    }

    @Nullable
    private static View getTopView(ViewGroup viewGroup) {
        if (viewGroup == null || viewGroup.getChildCount() == 0) {
            return null;
        }
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt.getVisibility() == 0 && childAt.getTop() == 0) {
                return childAt;
            }
        }
        return null;
    }
}