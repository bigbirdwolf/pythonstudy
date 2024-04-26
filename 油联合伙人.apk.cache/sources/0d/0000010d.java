package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.math.MathUtils;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    int mActivePointerId;
    private BottomSheetCallback mCallback;
    private final ViewDragHelper.Callback mDragCallback;
    boolean mHideable;
    private boolean mIgnoreEvents;
    private int mInitialY;
    private int mLastNestedScrollDy;
    int mMaxOffset;
    private float mMaximumVelocity;
    int mMinOffset;
    private boolean mNestedScrolled;
    WeakReference<View> mNestedScrollingChildRef;
    int mParentHeight;
    private int mPeekHeight;
    private boolean mPeekHeightAuto;
    private int mPeekHeightMin;
    private boolean mSkipCollapsed;
    int mState;
    boolean mTouchingScrollingChild;
    private VelocityTracker mVelocityTracker;
    ViewDragHelper mViewDragHelper;
    WeakReference<V> mViewRef;

    /* loaded from: classes.dex */
    public static abstract class BottomSheetCallback {
        public abstract void onSlide(@NonNull View view, float f);

        public abstract void onStateChanged(@NonNull View view, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface State {
    }

    public BottomSheetBehavior() {
        this.mState = 4;
        this.mDragCallback = new ViewDragHelper.Callback() { // from class: android.support.design.widget.BottomSheetBehavior.2
            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                View view2;
                if (BottomSheetBehavior.this.mState == 1 || BottomSheetBehavior.this.mTouchingScrollingChild) {
                    return false;
                }
                return ((BottomSheetBehavior.this.mState == 3 && BottomSheetBehavior.this.mActivePointerId == i && (view2 = BottomSheetBehavior.this.mNestedScrollingChildRef.get()) != null && view2.canScrollVertically(-1)) || BottomSheetBehavior.this.mViewRef == null || BottomSheetBehavior.this.mViewRef.get() != view) ? false : true;
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                BottomSheetBehavior.this.dispatchOnSlide(i2);
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i) {
                if (i == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x005a  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
            @Override // android.support.v4.widget.ViewDragHelper.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onViewReleased(android.view.View r4, float r5, float r6) {
                /*
                    r3 = this;
                    r5 = 0
                    int r0 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
                    r1 = 4
                    r2 = 3
                    if (r0 >= 0) goto Ld
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mMinOffset
                Lb:
                    r1 = 3
                    goto L4c
                Ld:
                    android.support.design.widget.BottomSheetBehavior r0 = android.support.design.widget.BottomSheetBehavior.this
                    boolean r0 = r0.mHideable
                    if (r0 == 0) goto L21
                    android.support.design.widget.BottomSheetBehavior r0 = android.support.design.widget.BottomSheetBehavior.this
                    boolean r0 = r0.shouldHide(r4, r6)
                    if (r0 == 0) goto L21
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mParentHeight
                    r1 = 5
                    goto L4c
                L21:
                    int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
                    if (r5 != 0) goto L48
                    int r5 = r4.getTop()
                    android.support.design.widget.BottomSheetBehavior r6 = android.support.design.widget.BottomSheetBehavior.this
                    int r6 = r6.mMinOffset
                    int r6 = r5 - r6
                    int r6 = java.lang.Math.abs(r6)
                    android.support.design.widget.BottomSheetBehavior r0 = android.support.design.widget.BottomSheetBehavior.this
                    int r0 = r0.mMaxOffset
                    int r5 = r5 - r0
                    int r5 = java.lang.Math.abs(r5)
                    if (r6 >= r5) goto L43
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mMinOffset
                    goto Lb
                L43:
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mMaxOffset
                    goto L4c
                L48:
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mMaxOffset
                L4c:
                    android.support.design.widget.BottomSheetBehavior r6 = android.support.design.widget.BottomSheetBehavior.this
                    android.support.v4.widget.ViewDragHelper r6 = r6.mViewDragHelper
                    int r0 = r4.getLeft()
                    boolean r5 = r6.settleCapturedViewAt(r0, r5)
                    if (r5 == 0) goto L6b
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    r6 = 2
                    r5.setStateInternal(r6)
                    android.support.design.widget.BottomSheetBehavior$SettleRunnable r5 = new android.support.design.widget.BottomSheetBehavior$SettleRunnable
                    android.support.design.widget.BottomSheetBehavior r6 = android.support.design.widget.BottomSheetBehavior.this
                    r5.<init>(r4, r1)
                    android.support.v4.view.ViewCompat.postOnAnimation(r4, r5)
                    goto L70
                L6b:
                    android.support.design.widget.BottomSheetBehavior r4 = android.support.design.widget.BottomSheetBehavior.this
                    r4.setStateInternal(r1)
                L70:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetBehavior.AnonymousClass2.onViewReleased(android.view.View, float, float):void");
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                return MathUtils.clamp(i, BottomSheetBehavior.this.mMinOffset, BottomSheetBehavior.this.mHideable ? BottomSheetBehavior.this.mParentHeight : BottomSheetBehavior.this.mMaxOffset);
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return view.getLeft();
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                if (BottomSheetBehavior.this.mHideable) {
                    return BottomSheetBehavior.this.mParentHeight - BottomSheetBehavior.this.mMinOffset;
                }
                return BottomSheetBehavior.this.mMaxOffset - BottomSheetBehavior.this.mMinOffset;
            }
        };
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mState = 4;
        this.mDragCallback = new ViewDragHelper.Callback() { // from class: android.support.design.widget.BottomSheetBehavior.2
            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                View view2;
                if (BottomSheetBehavior.this.mState == 1 || BottomSheetBehavior.this.mTouchingScrollingChild) {
                    return false;
                }
                return ((BottomSheetBehavior.this.mState == 3 && BottomSheetBehavior.this.mActivePointerId == i && (view2 = BottomSheetBehavior.this.mNestedScrollingChildRef.get()) != null && view2.canScrollVertically(-1)) || BottomSheetBehavior.this.mViewRef == null || BottomSheetBehavior.this.mViewRef.get() != view) ? false : true;
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                BottomSheetBehavior.this.dispatchOnSlide(i2);
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i) {
                if (i == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onViewReleased(android.view.View r4, float r5, float r6) {
                /*
                    r3 = this;
                    r5 = 0
                    int r0 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
                    r1 = 4
                    r2 = 3
                    if (r0 >= 0) goto Ld
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mMinOffset
                Lb:
                    r1 = 3
                    goto L4c
                Ld:
                    android.support.design.widget.BottomSheetBehavior r0 = android.support.design.widget.BottomSheetBehavior.this
                    boolean r0 = r0.mHideable
                    if (r0 == 0) goto L21
                    android.support.design.widget.BottomSheetBehavior r0 = android.support.design.widget.BottomSheetBehavior.this
                    boolean r0 = r0.shouldHide(r4, r6)
                    if (r0 == 0) goto L21
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mParentHeight
                    r1 = 5
                    goto L4c
                L21:
                    int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
                    if (r5 != 0) goto L48
                    int r5 = r4.getTop()
                    android.support.design.widget.BottomSheetBehavior r6 = android.support.design.widget.BottomSheetBehavior.this
                    int r6 = r6.mMinOffset
                    int r6 = r5 - r6
                    int r6 = java.lang.Math.abs(r6)
                    android.support.design.widget.BottomSheetBehavior r0 = android.support.design.widget.BottomSheetBehavior.this
                    int r0 = r0.mMaxOffset
                    int r5 = r5 - r0
                    int r5 = java.lang.Math.abs(r5)
                    if (r6 >= r5) goto L43
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mMinOffset
                    goto Lb
                L43:
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mMaxOffset
                    goto L4c
                L48:
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    int r5 = r5.mMaxOffset
                L4c:
                    android.support.design.widget.BottomSheetBehavior r6 = android.support.design.widget.BottomSheetBehavior.this
                    android.support.v4.widget.ViewDragHelper r6 = r6.mViewDragHelper
                    int r0 = r4.getLeft()
                    boolean r5 = r6.settleCapturedViewAt(r0, r5)
                    if (r5 == 0) goto L6b
                    android.support.design.widget.BottomSheetBehavior r5 = android.support.design.widget.BottomSheetBehavior.this
                    r6 = 2
                    r5.setStateInternal(r6)
                    android.support.design.widget.BottomSheetBehavior$SettleRunnable r5 = new android.support.design.widget.BottomSheetBehavior$SettleRunnable
                    android.support.design.widget.BottomSheetBehavior r6 = android.support.design.widget.BottomSheetBehavior.this
                    r5.<init>(r4, r1)
                    android.support.v4.view.ViewCompat.postOnAnimation(r4, r5)
                    goto L70
                L6b:
                    android.support.design.widget.BottomSheetBehavior r4 = android.support.design.widget.BottomSheetBehavior.this
                    r4.setStateInternal(r1)
                L70:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetBehavior.AnonymousClass2.onViewReleased(android.view.View, float, float):void");
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                return MathUtils.clamp(i, BottomSheetBehavior.this.mMinOffset, BottomSheetBehavior.this.mHideable ? BottomSheetBehavior.this.mParentHeight : BottomSheetBehavior.this.mMaxOffset);
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return view.getLeft();
            }

            @Override // android.support.v4.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                if (BottomSheetBehavior.this.mHideable) {
                    return BottomSheetBehavior.this.mParentHeight - BottomSheetBehavior.this.mMinOffset;
                }
                return BottomSheetBehavior.this.mMaxOffset - BottomSheetBehavior.this.mMinOffset;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Layout);
        TypedValue peekValue = obtainStyledAttributes.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (peekValue != null && peekValue.data == -1) {
            setPeekHeight(peekValue.data);
        } else {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        }
        setHideable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setSkipCollapsed(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        obtainStyledAttributes.recycle();
        this.mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), this.mState);
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        if (savedState.state == 1 || savedState.state == 2) {
            this.mState = 4;
        } else {
            this.mState = savedState.state;
        }
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        int i2;
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            ViewCompat.setFitsSystemWindows(v, true);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i);
        this.mParentHeight = coordinatorLayout.getHeight();
        if (this.mPeekHeightAuto) {
            if (this.mPeekHeightMin == 0) {
                this.mPeekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            }
            i2 = Math.max(this.mPeekHeightMin, this.mParentHeight - ((coordinatorLayout.getWidth() * 9) / 16));
        } else {
            i2 = this.mPeekHeight;
        }
        this.mMinOffset = Math.max(0, this.mParentHeight - v.getHeight());
        this.mMaxOffset = Math.max(this.mParentHeight - i2, this.mMinOffset);
        if (this.mState == 3) {
            ViewCompat.offsetTopAndBottom(v, this.mMinOffset);
        } else if (this.mHideable && this.mState == 5) {
            ViewCompat.offsetTopAndBottom(v, this.mParentHeight);
        } else if (this.mState == 4) {
            ViewCompat.offsetTopAndBottom(v, this.mMaxOffset);
        } else if (this.mState == 1 || this.mState == 2) {
            ViewCompat.offsetTopAndBottom(v, top - v.getTop());
        }
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = ViewDragHelper.create(coordinatorLayout, this.mDragCallback);
        }
        this.mViewRef = new WeakReference<>(v);
        this.mNestedScrollingChildRef = new WeakReference<>(findScrollingChild(v));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0090 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.support.design.widget.CoordinatorLayout r8, V r9, android.view.MotionEvent r10) {
        /*
            r7 = this;
            boolean r0 = r9.isShown()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto Lb
            r7.mIgnoreEvents = r2
            return r1
        Lb:
            int r0 = r10.getActionMasked()
            if (r0 != 0) goto L14
            r7.reset()
        L14:
            android.view.VelocityTracker r3 = r7.mVelocityTracker
            if (r3 != 0) goto L1e
            android.view.VelocityTracker r3 = android.view.VelocityTracker.obtain()
            r7.mVelocityTracker = r3
        L1e:
            android.view.VelocityTracker r3 = r7.mVelocityTracker
            r3.addMovement(r10)
            r3 = 3
            r4 = -1
            if (r0 == r3) goto L6d
            switch(r0) {
                case 0: goto L2b;
                case 1: goto L6d;
                default: goto L2a;
            }
        L2a:
            goto L78
        L2b:
            float r3 = r10.getX()
            int r3 = (int) r3
            float r5 = r10.getY()
            int r5 = (int) r5
            r7.mInitialY = r5
            java.lang.ref.WeakReference<android.view.View> r5 = r7.mNestedScrollingChildRef
            if (r5 == 0) goto L44
            java.lang.ref.WeakReference<android.view.View> r5 = r7.mNestedScrollingChildRef
            java.lang.Object r5 = r5.get()
            android.view.View r5 = (android.view.View) r5
            goto L45
        L44:
            r5 = 0
        L45:
            if (r5 == 0) goto L5b
            int r6 = r7.mInitialY
            boolean r5 = r8.isPointInChildBounds(r5, r3, r6)
            if (r5 == 0) goto L5b
            int r5 = r10.getActionIndex()
            int r5 = r10.getPointerId(r5)
            r7.mActivePointerId = r5
            r7.mTouchingScrollingChild = r2
        L5b:
            int r5 = r7.mActivePointerId
            if (r5 != r4) goto L69
            int r4 = r7.mInitialY
            boolean r9 = r8.isPointInChildBounds(r9, r3, r4)
            if (r9 != 0) goto L69
            r9 = 1
            goto L6a
        L69:
            r9 = 0
        L6a:
            r7.mIgnoreEvents = r9
            goto L78
        L6d:
            r7.mTouchingScrollingChild = r1
            r7.mActivePointerId = r4
            boolean r9 = r7.mIgnoreEvents
            if (r9 == 0) goto L78
            r7.mIgnoreEvents = r1
            return r1
        L78:
            boolean r9 = r7.mIgnoreEvents
            if (r9 != 0) goto L85
            android.support.v4.widget.ViewDragHelper r9 = r7.mViewDragHelper
            boolean r9 = r9.shouldInterceptTouchEvent(r10)
            if (r9 == 0) goto L85
            return r2
        L85:
            java.lang.ref.WeakReference<android.view.View> r9 = r7.mNestedScrollingChildRef
            java.lang.Object r9 = r9.get()
            android.view.View r9 = (android.view.View) r9
            r3 = 2
            if (r0 != r3) goto Lc2
            if (r9 == 0) goto Lc2
            boolean r0 = r7.mIgnoreEvents
            if (r0 != 0) goto Lc2
            int r0 = r7.mState
            if (r0 == r2) goto Lc2
            float r0 = r10.getX()
            int r0 = (int) r0
            float r3 = r10.getY()
            int r3 = (int) r3
            boolean r8 = r8.isPointInChildBounds(r9, r0, r3)
            if (r8 != 0) goto Lc2
            int r8 = r7.mInitialY
            float r8 = (float) r8
            float r9 = r10.getY()
            float r8 = r8 - r9
            float r8 = java.lang.Math.abs(r8)
            android.support.v4.widget.ViewDragHelper r9 = r7.mViewDragHelper
            int r9 = r9.getTouchSlop()
            float r9 = (float) r9
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 <= 0) goto Lc2
            r1 = 1
        Lc2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetBehavior.onInterceptTouchEvent(android.support.design.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (v.isShown()) {
            int actionMasked = motionEvent.getActionMasked();
            if (this.mState == 1 && actionMasked == 0) {
                return true;
            }
            if (this.mViewDragHelper != null) {
                this.mViewDragHelper.processTouchEvent(motionEvent);
            }
            if (actionMasked == 0) {
                reset();
            }
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            if (actionMasked == 2 && !this.mIgnoreEvents && Math.abs(this.mInitialY - motionEvent.getY()) > this.mViewDragHelper.getTouchSlop()) {
                this.mViewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
            return !this.mIgnoreEvents;
        }
        return false;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
        this.mLastNestedScrollDy = 0;
        this.mNestedScrolled = false;
        return (i & 2) != 0;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        if (view != this.mNestedScrollingChildRef.get()) {
            return;
        }
        int top = v.getTop();
        int i3 = top - i2;
        if (i2 > 0) {
            if (i3 < this.mMinOffset) {
                iArr[1] = top - this.mMinOffset;
                ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                setStateInternal(3);
            } else {
                iArr[1] = i2;
                ViewCompat.offsetTopAndBottom(v, -i2);
                setStateInternal(1);
            }
        } else if (i2 < 0 && !view.canScrollVertically(-1)) {
            if (i3 <= this.mMaxOffset || this.mHideable) {
                iArr[1] = i2;
                ViewCompat.offsetTopAndBottom(v, -i2);
                setStateInternal(1);
            } else {
                iArr[1] = top - this.mMaxOffset;
                ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                setStateInternal(4);
            }
        }
        dispatchOnSlide(v.getTop());
        this.mLastNestedScrollDy = i2;
        this.mNestedScrolled = true;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view) {
        int i;
        int i2 = 3;
        if (v.getTop() == this.mMinOffset) {
            setStateInternal(3);
        } else if (this.mNestedScrollingChildRef != null && view == this.mNestedScrollingChildRef.get() && this.mNestedScrolled) {
            if (this.mLastNestedScrollDy > 0) {
                i = this.mMinOffset;
            } else if (this.mHideable && shouldHide(v, getYVelocity())) {
                i = this.mParentHeight;
                i2 = 5;
            } else {
                if (this.mLastNestedScrollDy == 0) {
                    int top = v.getTop();
                    if (Math.abs(top - this.mMinOffset) < Math.abs(top - this.mMaxOffset)) {
                        i = this.mMinOffset;
                    } else {
                        i = this.mMaxOffset;
                    }
                } else {
                    i = this.mMaxOffset;
                }
                i2 = 4;
            }
            if (this.mViewDragHelper.smoothSlideViewTo(v, v.getLeft(), i)) {
                setStateInternal(2);
                ViewCompat.postOnAnimation(v, new SettleRunnable(v, i2));
            } else {
                setStateInternal(i2);
            }
            this.mNestedScrolled = false;
        }
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        return view == this.mNestedScrollingChildRef.get() && (this.mState != 3 || super.onNestedPreFling(coordinatorLayout, v, view, f, f2));
    }

    public final void setPeekHeight(int i) {
        V v;
        boolean z = true;
        if (i == -1) {
            if (!this.mPeekHeightAuto) {
                this.mPeekHeightAuto = true;
            }
            z = false;
        } else {
            if (this.mPeekHeightAuto || this.mPeekHeight != i) {
                this.mPeekHeightAuto = false;
                this.mPeekHeight = Math.max(0, i);
                this.mMaxOffset = this.mParentHeight - i;
            }
            z = false;
        }
        if (!z || this.mState != 4 || this.mViewRef == null || (v = this.mViewRef.get()) == null) {
            return;
        }
        v.requestLayout();
    }

    public final int getPeekHeight() {
        if (this.mPeekHeightAuto) {
            return -1;
        }
        return this.mPeekHeight;
    }

    public void setHideable(boolean z) {
        this.mHideable = z;
    }

    public boolean isHideable() {
        return this.mHideable;
    }

    public void setSkipCollapsed(boolean z) {
        this.mSkipCollapsed = z;
    }

    public boolean getSkipCollapsed() {
        return this.mSkipCollapsed;
    }

    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.mCallback = bottomSheetCallback;
    }

    public final void setState(final int i) {
        if (i == this.mState) {
            return;
        }
        if (this.mViewRef == null) {
            if (i == 4 || i == 3 || (this.mHideable && i == 5)) {
                this.mState = i;
                return;
            }
            return;
        }
        final V v = this.mViewRef.get();
        if (v == null) {
            return;
        }
        ViewParent parent = v.getParent();
        if (parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(v)) {
            v.post(new Runnable() { // from class: android.support.design.widget.BottomSheetBehavior.1
                @Override // java.lang.Runnable
                public void run() {
                    BottomSheetBehavior.this.startSettlingAnimation(v, i);
                }
            });
        } else {
            startSettlingAnimation(v, i);
        }
    }

    public final int getState() {
        return this.mState;
    }

    void setStateInternal(int i) {
        if (this.mState == i) {
            return;
        }
        this.mState = i;
        V v = this.mViewRef.get();
        if (v == null || this.mCallback == null) {
            return;
        }
        this.mCallback.onStateChanged(v, i);
    }

    private void reset() {
        this.mActivePointerId = -1;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    boolean shouldHide(View view, float f) {
        if (this.mSkipCollapsed) {
            return true;
        }
        return view.getTop() >= this.mMaxOffset && Math.abs((((float) view.getTop()) + (f * HIDE_FRICTION)) - ((float) this.mMaxOffset)) / ((float) this.mPeekHeight) > HIDE_THRESHOLD;
    }

    @VisibleForTesting
    View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
                if (findScrollingChild != null) {
                    return findScrollingChild;
                }
            }
            return null;
        }
        return null;
    }

    private float getYVelocity() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
        return this.mVelocityTracker.getYVelocity(this.mActivePointerId);
    }

    void startSettlingAnimation(View view, int i) {
        int i2;
        if (i == 4) {
            i2 = this.mMaxOffset;
        } else if (i == 3) {
            i2 = this.mMinOffset;
        } else if (this.mHideable && i == 5) {
            i2 = this.mParentHeight;
        } else {
            throw new IllegalArgumentException("Illegal state argument: " + i);
        }
        if (this.mViewDragHelper.smoothSlideViewTo(view, view.getLeft(), i2)) {
            setStateInternal(2);
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
            return;
        }
        setStateInternal(i);
    }

    void dispatchOnSlide(int i) {
        V v = this.mViewRef.get();
        if (v == null || this.mCallback == null) {
            return;
        }
        if (i > this.mMaxOffset) {
            this.mCallback.onSlide(v, (this.mMaxOffset - i) / (this.mParentHeight - this.mMaxOffset));
        } else {
            this.mCallback.onSlide(v, (this.mMaxOffset - i) / (this.mMaxOffset - this.mMinOffset));
        }
    }

    @VisibleForTesting
    int getPeekHeightMin() {
        return this.mPeekHeightMin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SettleRunnable implements Runnable {
        private final int mTargetState;
        private final View mView;

        SettleRunnable(View view, int i) {
            this.mView = view;
            this.mTargetState = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BottomSheetBehavior.this.mViewDragHelper != null && BottomSheetBehavior.this.mViewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.mView, this);
            } else {
                BottomSheetBehavior.this.setStateInternal(this.mTargetState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.design.widget.BottomSheetBehavior.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        final int state;

        public SavedState(Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.state = i;
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }
    }

    public static <V extends View> BottomSheetBehavior<V> from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (!(behavior instanceof BottomSheetBehavior)) {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        return (BottomSheetBehavior) behavior;
    }
}