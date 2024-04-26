package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class ViewHighlighter {
    public abstract void clearHighlight();

    public abstract void setHighlightedView(View view, int i);

    public static ViewHighlighter newInstance() {
        if (Build.VERSION.SDK_INT >= 18) {
            return new OverlayHighlighter();
        }
        LogUtil.w("Running on pre-JBMR2: View highlighting is not supported");
        return new NoopHighlighter();
    }

    protected ViewHighlighter() {
    }

    /* loaded from: classes.dex */
    private static final class NoopHighlighter extends ViewHighlighter {
        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void clearHighlight() {
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void setHighlightedView(View view, int i) {
        }

        private NoopHighlighter() {
        }
    }

    @TargetApi(18)
    /* loaded from: classes.dex */
    private static final class OverlayHighlighter extends ViewHighlighter {
        private View mHighlightedView;
        private final ViewHighlightOverlays mHighlightOverlays = ViewHighlightOverlays.newInstance();
        private AtomicReference<View> mViewToHighlight = new AtomicReference<>();
        private AtomicInteger mContentColor = new AtomicInteger();
        private final Runnable mHighlightViewOnUiThreadRunnable = new Runnable() { // from class: com.facebook.stetho.inspector.elements.android.ViewHighlighter.OverlayHighlighter.1
            @Override // java.lang.Runnable
            public void run() {
                OverlayHighlighter.this.highlightViewOnUiThread();
            }
        };
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void clearHighlight() {
            setHighlightedViewImpl(null, 0);
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void setHighlightedView(View view, int i) {
            setHighlightedViewImpl((View) Util.throwIfNull(view), i);
        }

        private void setHighlightedViewImpl(@Nullable View view, int i) {
            this.mHandler.removeCallbacks(this.mHighlightViewOnUiThreadRunnable);
            this.mViewToHighlight.set(view);
            this.mContentColor.set(i);
            this.mHandler.postDelayed(this.mHighlightViewOnUiThreadRunnable, 100L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void highlightViewOnUiThread() {
            View andSet = this.mViewToHighlight.getAndSet(null);
            if (andSet == this.mHighlightedView) {
                return;
            }
            if (this.mHighlightedView != null) {
                this.mHighlightOverlays.removeHighlight(this.mHighlightedView);
            }
            if (andSet != null) {
                this.mHighlightOverlays.highlightView(andSet, this.mContentColor.get());
            }
            this.mHighlightedView = andSet;
        }
    }
}