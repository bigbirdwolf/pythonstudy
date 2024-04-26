package com.umeng.socialize.shareboard.widgets;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* loaded from: classes.dex */
class ViewCompat {
    private static final long FAKE_FRAME_TIME = 10;
    private static final ViewCompatImpl IMPL;

    /* loaded from: classes.dex */
    interface OnApplyWindowInsetsListener {
        WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat);
    }

    /* loaded from: classes.dex */
    interface OnApplyWindowInsetsListenerBridge {
        Object onApplyWindowInsets(View view, Object obj);
    }

    /* loaded from: classes.dex */
    interface ViewCompatImpl {
        boolean canScrollHorizontally(View view, int i);

        WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat);

        WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat);

        void postInvalidateOnAnimation(View view);

        void postOnAnimation(View view, Runnable runnable);

        void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener);
    }

    ViewCompat() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void postInvalidateOnAnimation(View view) {
        IMPL.postInvalidateOnAnimation(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void postOnAnimation(View view, Runnable runnable) {
        IMPL.postOnAnimation(view, runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean canScrollHorizontally(View view, int i) {
        return IMPL.canScrollHorizontally(view, i);
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        IMPL.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return IMPL.onApplyWindowInsets(view, windowInsetsCompat);
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return IMPL.dispatchApplyWindowInsets(view, windowInsetsCompat);
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            IMPL = new Api24ViewCompatImpl();
        } else if (i >= 23) {
            IMPL = new MarshmallowViewCompatImpl();
        } else if (i >= 21) {
            IMPL = new LollipopViewCompatImpl();
        } else if (i >= 19) {
            IMPL = new KitKatViewCompatImpl();
        } else if (i >= 18) {
            IMPL = new JbMr2ViewCompatImpl();
        } else if (i >= 17) {
            IMPL = new JbMr1ViewCompatImpl();
        } else if (i >= 16) {
            IMPL = new JBViewCompatImpl();
        } else if (i >= 15) {
            IMPL = new ICSMr1ViewCompatImpl();
        } else if (i >= 14) {
            IMPL = new ICSViewCompatImpl();
        } else if (i >= 11) {
            IMPL = new HCViewCompatImpl();
        } else {
            IMPL = new BaseViewCompatImpl();
        }
    }

    /* loaded from: classes.dex */
    static class BaseViewCompatImpl implements ViewCompatImpl {
        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public boolean canScrollHorizontally(View view, int i) {
            return false;
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        long getFrameTime() {
            return ViewCompat.FAKE_FRAME_TIME;
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        }

        BaseViewCompatImpl() {
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public void postInvalidateOnAnimation(View view) {
            view.invalidate();
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public void postOnAnimation(View view, Runnable runnable) {
            view.postDelayed(runnable, getFrameTime());
        }
    }

    @TargetApi(11)
    /* loaded from: classes.dex */
    static class HCViewCompatImpl extends BaseViewCompatImpl {
        HCViewCompatImpl() {
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.BaseViewCompatImpl
        long getFrameTime() {
            return ValueAnimator.getFrameDelay();
        }
    }

    @TargetApi(14)
    /* loaded from: classes.dex */
    static class ICSViewCompatImpl extends HCViewCompatImpl {
        ICSViewCompatImpl() {
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.BaseViewCompatImpl, com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public boolean canScrollHorizontally(View view, int i) {
            return view.canScrollHorizontally(i);
        }
    }

    /* loaded from: classes.dex */
    static class ICSMr1ViewCompatImpl extends ICSViewCompatImpl {
        ICSMr1ViewCompatImpl() {
        }
    }

    @TargetApi(16)
    /* loaded from: classes.dex */
    static class JBViewCompatImpl extends ICSMr1ViewCompatImpl {
        JBViewCompatImpl() {
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.BaseViewCompatImpl, com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public void postInvalidateOnAnimation(View view) {
            view.postInvalidateOnAnimation();
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.BaseViewCompatImpl, com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public void postOnAnimation(View view, Runnable runnable) {
            view.postOnAnimation(runnable);
        }
    }

    /* loaded from: classes.dex */
    private static class JbMr1ViewCompatImpl extends JBViewCompatImpl {
        private JbMr1ViewCompatImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static class JbMr2ViewCompatImpl extends JbMr1ViewCompatImpl {
        private JbMr2ViewCompatImpl() {
            super();
        }
    }

    /* loaded from: classes.dex */
    private static class KitKatViewCompatImpl extends JbMr2ViewCompatImpl {
        private KitKatViewCompatImpl() {
            super();
        }
    }

    @TargetApi(20)
    /* loaded from: classes.dex */
    private static class LollipopViewCompatImpl extends KitKatViewCompatImpl {
        private LollipopViewCompatImpl() {
            super();
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.BaseViewCompatImpl, com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public void setOnApplyWindowInsetsListener(View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            if (onApplyWindowInsetsListener == null) {
                view.setOnApplyWindowInsetsListener(null);
                return;
            }
            final OnApplyWindowInsetsListenerBridge onApplyWindowInsetsListenerBridge = new OnApplyWindowInsetsListenerBridge() { // from class: com.umeng.socialize.shareboard.widgets.ViewCompat.LollipopViewCompatImpl.1
                @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.OnApplyWindowInsetsListenerBridge
                public Object onApplyWindowInsets(View view2, Object obj) {
                    return WindowInsetsCompat.unwrap(onApplyWindowInsetsListener.onApplyWindowInsets(view2, WindowInsetsCompat.wrap(obj)));
                }
            };
            view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.umeng.socialize.shareboard.widgets.ViewCompat.LollipopViewCompatImpl.2
                @Override // android.view.View.OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                    return (WindowInsets) onApplyWindowInsetsListenerBridge.onApplyWindowInsets(view2, windowInsets);
                }
            });
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.BaseViewCompatImpl, com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            return WindowInsetsCompat.wrap(onApplyWindowInsets(view, WindowInsetsCompat.unwrap(windowInsetsCompat)));
        }

        private static Object onApplyWindowInsets(View view, Object obj) {
            WindowInsets windowInsets = (WindowInsets) obj;
            WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
            return onApplyWindowInsets != windowInsets ? new WindowInsets(onApplyWindowInsets) : obj;
        }

        @Override // com.umeng.socialize.shareboard.widgets.ViewCompat.BaseViewCompatImpl, com.umeng.socialize.shareboard.widgets.ViewCompat.ViewCompatImpl
        public WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            return WindowInsetsCompat.wrap(dispatchApplyWindowInsets(view, WindowInsetsCompat.unwrap(windowInsetsCompat)));
        }

        private static Object dispatchApplyWindowInsets(View view, Object obj) {
            WindowInsets windowInsets = (WindowInsets) obj;
            WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsets);
            return dispatchApplyWindowInsets != windowInsets ? new WindowInsets(dispatchApplyWindowInsets) : obj;
        }
    }

    /* loaded from: classes.dex */
    private static class MarshmallowViewCompatImpl extends LollipopViewCompatImpl {
        private MarshmallowViewCompatImpl() {
            super();
        }
    }

    /* loaded from: classes.dex */
    private static class Api24ViewCompatImpl extends MarshmallowViewCompatImpl {
        private Api24ViewCompatImpl() {
            super();
        }
    }
}