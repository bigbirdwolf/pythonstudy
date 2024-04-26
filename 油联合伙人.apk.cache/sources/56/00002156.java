package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.subscriptions.SequentialSubscription;

/* loaded from: classes.dex */
public final class SchedulePeriodicHelper {
    public static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    /* loaded from: classes.dex */
    public interface NowNanoSupplier {
        long nowNanos();
    }

    private SchedulePeriodicHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription schedulePeriodically(final Scheduler.Worker worker, final Action0 action0, long j, long j2, TimeUnit timeUnit, final NowNanoSupplier nowNanoSupplier) {
        final long nanos = timeUnit.toNanos(j2);
        final long nowNanos = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(worker.now());
        final long nanos2 = timeUnit.toNanos(j) + nowNanos;
        SequentialSubscription sequentialSubscription = new SequentialSubscription();
        final SequentialSubscription sequentialSubscription2 = new SequentialSubscription(sequentialSubscription);
        sequentialSubscription.replace(worker.schedule(new Action0() { // from class: rx.internal.schedulers.SchedulePeriodicHelper.1
            long count;
            long lastNowNanos;
            long startInNanos;

            {
                this.lastNowNanos = nowNanos;
                this.startInNanos = nanos2;
            }

            @Override // rx.functions.Action0
            public void call() {
                long j3;
                action0.call();
                if (sequentialSubscription2.isUnsubscribed()) {
                    return;
                }
                long nowNanos2 = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(worker.now());
                if (SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS + nowNanos2 < this.lastNowNanos || nowNanos2 >= this.lastNowNanos + nanos + SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS) {
                    j3 = nanos + nowNanos2;
                    long j4 = nanos;
                    long j5 = this.count + 1;
                    this.count = j5;
                    this.startInNanos = j3 - (j4 * j5);
                } else {
                    long j6 = this.startInNanos;
                    long j7 = this.count + 1;
                    this.count = j7;
                    j3 = j6 + (j7 * nanos);
                }
                this.lastNowNanos = nowNanos2;
                sequentialSubscription2.replace(worker.schedule(this, j3 - nowNanos2, TimeUnit.NANOSECONDS));
            }
        }, j, timeUnit));
        return sequentialSubscription2;
    }
}