package com.bigkoo.convenientbanner.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

/* loaded from: classes.dex */
public class CBLoopViewPager extends ViewPager {
    private static final float sens = 5.0f;
    private boolean canLoop;
    private boolean isCanScroll;
    private CBPageAdapter mAdapter;
    ViewPager.OnPageChangeListener mOuterPageChangeListener;
    private float newX;
    private float oldX;
    private OnItemClickListener onItemClickListener;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    public void setAdapter(PagerAdapter pagerAdapter, boolean z) {
        this.mAdapter = (CBPageAdapter) pagerAdapter;
        this.mAdapter.setCanLoop(z);
        this.mAdapter.setViewPager(this);
        super.setAdapter(this.mAdapter);
        setCurrentItem(getFristItem(), false);
    }

    public int getFristItem() {
        if (this.canLoop) {
            return this.mAdapter.getRealCount();
        }
        return 0;
    }

    public int getLastItem() {
        return this.mAdapter.getRealCount() - 1;
    }

    public boolean isCanScroll() {
        return this.isCanScroll;
    }

    public void setCanScroll(boolean z) {
        this.isCanScroll = z;
    }

    @Override // android.support.v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isCanScroll) {
            if (this.onItemClickListener != null) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.oldX = motionEvent.getX();
                        break;
                    case 1:
                        this.newX = motionEvent.getX();
                        if (Math.abs(this.oldX - this.newX) < sens) {
                            this.onItemClickListener.onItemClick(getRealItem());
                        }
                        this.oldX = 0.0f;
                        this.newX = 0.0f;
                        break;
                }
            }
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.support.v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isCanScroll) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.support.v4.view.ViewPager
    public CBPageAdapter getAdapter() {
        return this.mAdapter;
    }

    public int getRealItem() {
        if (this.mAdapter != null) {
            return this.mAdapter.toRealPosition(super.getCurrentItem());
        }
        return 0;
    }

    @Override // android.support.v4.view.ViewPager
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOuterPageChangeListener = onPageChangeListener;
    }

    public CBLoopViewPager(Context context) {
        super(context);
        this.isCanScroll = true;
        this.canLoop = true;
        this.oldX = 0.0f;
        this.newX = 0.0f;
        this.onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.bigkoo.convenientbanner.view.CBLoopViewPager.1
            private float mPreviousPosition = -1.0f;

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                int realPosition = CBLoopViewPager.this.mAdapter.toRealPosition(i);
                float f = realPosition;
                if (this.mPreviousPosition != f) {
                    this.mPreviousPosition = f;
                    if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
                    }
                }
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                    if (i != CBLoopViewPager.this.mAdapter.getRealCount() - 1) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, f, i2);
                    } else if (f > 0.5d) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(0, 0.0f, 0);
                    } else {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, 0.0f, 0);
                    }
                }
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                    CBLoopViewPager.this.mOuterPageChangeListener.onPageScrollStateChanged(i);
                }
            }
        };
        init();
    }

    public CBLoopViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isCanScroll = true;
        this.canLoop = true;
        this.oldX = 0.0f;
        this.newX = 0.0f;
        this.onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.bigkoo.convenientbanner.view.CBLoopViewPager.1
            private float mPreviousPosition = -1.0f;

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                int realPosition = CBLoopViewPager.this.mAdapter.toRealPosition(i);
                float f = realPosition;
                if (this.mPreviousPosition != f) {
                    this.mPreviousPosition = f;
                    if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
                    }
                }
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                    if (i != CBLoopViewPager.this.mAdapter.getRealCount() - 1) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, f, i2);
                    } else if (f > 0.5d) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(0, 0.0f, 0);
                    } else {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, 0.0f, 0);
                    }
                }
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                    CBLoopViewPager.this.mOuterPageChangeListener.onPageScrollStateChanged(i);
                }
            }
        };
        init();
    }

    private void init() {
        super.setOnPageChangeListener(this.onPageChangeListener);
    }

    public boolean isCanLoop() {
        return this.canLoop;
    }

    public void setCanLoop(boolean z) {
        this.canLoop = z;
        if (!z) {
            setCurrentItem(getRealItem(), false);
        }
        if (this.mAdapter == null) {
            return;
        }
        this.mAdapter.setCanLoop(z);
        this.mAdapter.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}