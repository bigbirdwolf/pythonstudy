package com.contrarywind.timer;

import com.contrarywind.view.WheelView;
import java.util.TimerTask;

/* loaded from: classes.dex */
public final class InertiaTimerTask extends TimerTask {
    private float mCurrentVelocityY = 2.14748365E9f;
    private final float mFirstVelocityY;
    private final WheelView mWheelView;

    public InertiaTimerTask(WheelView wheelView, float f) {
        this.mWheelView = wheelView;
        this.mFirstVelocityY = f;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        if (this.mCurrentVelocityY == 2.14748365E9f) {
            if (Math.abs(this.mFirstVelocityY) > 2000.0f) {
                this.mCurrentVelocityY = this.mFirstVelocityY <= 0.0f ? -2000.0f : 2000.0f;
            } else {
                this.mCurrentVelocityY = this.mFirstVelocityY;
            }
        }
        if (Math.abs(this.mCurrentVelocityY) >= 0.0f && Math.abs(this.mCurrentVelocityY) <= 20.0f) {
            this.mWheelView.cancelFuture();
            this.mWheelView.getHandler().sendEmptyMessage(MessageHandler.WHAT_SMOOTH_SCROLL);
            return;
        }
        float f = (int) (this.mCurrentVelocityY / 100.0f);
        this.mWheelView.setTotalScrollY(this.mWheelView.getTotalScrollY() - f);
        if (!this.mWheelView.isLoop()) {
            float itemHeight = this.mWheelView.getItemHeight();
            float f2 = (-this.mWheelView.getInitPosition()) * itemHeight;
            float itemsCount = ((this.mWheelView.getItemsCount() - 1) - this.mWheelView.getInitPosition()) * itemHeight;
            double totalScrollY = this.mWheelView.getTotalScrollY();
            double d = itemHeight;
            Double.isNaN(d);
            double d2 = d * 0.25d;
            Double.isNaN(totalScrollY);
            if (totalScrollY - d2 < f2) {
                f2 = this.mWheelView.getTotalScrollY() + f;
            } else {
                double totalScrollY2 = this.mWheelView.getTotalScrollY();
                Double.isNaN(totalScrollY2);
                if (totalScrollY2 + d2 > itemsCount) {
                    itemsCount = this.mWheelView.getTotalScrollY() + f;
                }
            }
            if (this.mWheelView.getTotalScrollY() <= f2) {
                this.mCurrentVelocityY = 40.0f;
                this.mWheelView.setTotalScrollY((int) f2);
            } else if (this.mWheelView.getTotalScrollY() >= itemsCount) {
                this.mWheelView.setTotalScrollY((int) itemsCount);
                this.mCurrentVelocityY = -40.0f;
            }
        }
        if (this.mCurrentVelocityY < 0.0f) {
            this.mCurrentVelocityY += 20.0f;
        } else {
            this.mCurrentVelocityY -= 20.0f;
        }
        this.mWheelView.getHandler().sendEmptyMessage(1000);
    }
}