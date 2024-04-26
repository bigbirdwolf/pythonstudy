package com.umeng.socialize.shareboard.widgets;

import android.os.Build;
import android.view.VelocityTracker;

/* loaded from: classes.dex */
class VelocityTrackerCompat {
    private static final VelocityTrackerVersionImpl IMPL;

    /* loaded from: classes.dex */
    interface VelocityTrackerVersionImpl {
        float getXVelocity(VelocityTracker velocityTracker, int i);
    }

    VelocityTrackerCompat() {
    }

    /* loaded from: classes.dex */
    private static class BaseVelocityTrackerVersionImpl implements VelocityTrackerVersionImpl {
        private BaseVelocityTrackerVersionImpl() {
        }

        @Override // com.umeng.socialize.shareboard.widgets.VelocityTrackerCompat.VelocityTrackerVersionImpl
        public float getXVelocity(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getXVelocity();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            IMPL = new HoneycombVelocityTrackerVersionImpl();
        } else {
            IMPL = new BaseVelocityTrackerVersionImpl();
        }
    }

    /* loaded from: classes.dex */
    private static class HoneycombVelocityTrackerVersionImpl implements VelocityTrackerVersionImpl {
        private HoneycombVelocityTrackerVersionImpl() {
        }

        @Override // com.umeng.socialize.shareboard.widgets.VelocityTrackerCompat.VelocityTrackerVersionImpl
        public float getXVelocity(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getXVelocity(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getXVelocity(VelocityTracker velocityTracker, int i) {
        return IMPL.getXVelocity(velocityTracker, i);
    }
}