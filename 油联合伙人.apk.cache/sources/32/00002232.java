package rx.schedulers;

import rx.Scheduler;

@Deprecated
/* loaded from: classes.dex */
public final class ImmediateScheduler extends Scheduler {
    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }

    private ImmediateScheduler() {
        throw new IllegalStateException("No instances!");
    }
}