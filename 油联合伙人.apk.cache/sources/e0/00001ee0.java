package rx.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.Subscriptions;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class LooperScheduler extends Scheduler {
    private final Handler handler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LooperScheduler(Looper looper) {
        this.handler = new Handler(looper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LooperScheduler(Handler handler) {
        this.handler = handler;
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new HandlerWorker(this.handler);
    }

    /* loaded from: classes.dex */
    static class HandlerWorker extends Scheduler.Worker {
        private final Handler handler;
        private final RxAndroidSchedulersHook hook = RxAndroidPlugins.getInstance().getSchedulersHook();
        private volatile boolean unsubscribed;

        HandlerWorker(Handler handler) {
            this.handler = handler;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.unsubscribed = true;
            this.handler.removeCallbacksAndMessages(this);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.unsubscribed;
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            if (this.unsubscribed) {
                return Subscriptions.unsubscribed();
            }
            ScheduledAction scheduledAction = new ScheduledAction(this.hook.onSchedule(action0), this.handler);
            Message obtain = Message.obtain(this.handler, scheduledAction);
            obtain.obj = this;
            this.handler.sendMessageDelayed(obtain, timeUnit.toMillis(j));
            if (this.unsubscribed) {
                this.handler.removeCallbacks(scheduledAction);
                return Subscriptions.unsubscribed();
            }
            return scheduledAction;
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            return schedule(action0, 0L, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ScheduledAction implements Runnable, Subscription {
        private final Action0 action;
        private final Handler handler;
        private volatile boolean unsubscribed;

        ScheduledAction(Action0 action0, Handler handler) {
            this.action = action0;
            this.handler = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            IllegalStateException illegalStateException;
            try {
                this.action.call();
            } catch (Throwable th) {
                if (th instanceof OnErrorNotImplementedException) {
                    illegalStateException = new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", th);
                } else {
                    illegalStateException = new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", th);
                }
                RxJavaPlugins.getInstance().getErrorHandler().handleError(illegalStateException);
                Thread currentThread = Thread.currentThread();
                currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, illegalStateException);
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.unsubscribed = true;
            this.handler.removeCallbacks(this);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.unsubscribed;
        }
    }
}