package com.yltx.oil.partner.base;

import android.app.Activity;
import java.util.Iterator;
import java.util.Stack;

/* loaded from: classes.dex */
public class ActivityStackManager {
    private final Stack<Activity> activityStack;

    /* loaded from: classes.dex */
    private static class AppManagerHolder {
        private static final ActivityStackManager INSTANCE = new ActivityStackManager();

        private AppManagerHolder() {
        }
    }

    public static ActivityStackManager getDefault() {
        return AppManagerHolder.INSTANCE;
    }

    private ActivityStackManager() {
        this.activityStack = new Stack<>();
    }

    public boolean hasActivity(Class<?> cls) {
        Iterator<Activity> it = this.activityStack.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    public Activity getActivity(Class<?> cls) {
        Iterator<Activity> it = this.activityStack.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (next.getClass().equals(cls)) {
                return next;
            }
        }
        return null;
    }

    public void addActivity(Activity activity) {
        this.activityStack.add(activity);
    }

    public Activity currentActivity() {
        return this.activityStack.lastElement();
    }

    public void finishActivity() {
        finishActivity(this.activityStack.lastElement());
    }

    public void finishActivity(Activity activity) {
        if (activity == null || !this.activityStack.contains(activity)) {
            return;
        }
        this.activityStack.remove(activity);
        activity.finish();
    }

    public void removeActivity(Activity activity) {
        if (activity == null || !this.activityStack.contains(activity)) {
            return;
        }
        this.activityStack.remove(activity);
    }

    public void finishActivity(Class<?> cls) {
        Iterator<Activity> it = this.activityStack.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (next.getClass().equals(cls)) {
                finishActivity(next);
                return;
            }
        }
    }

    public void finishSelfActivity(Class<?> cls) {
        for (int i = 0; i < this.activityStack.size(); i++) {
            if (this.activityStack.get(i).getClass() != cls) {
                this.activityStack.get(i).finish();
                this.activityStack.remove(this.activityStack.get(i));
            }
        }
    }

    public void finishAllActivity() {
        int size = this.activityStack.size();
        for (int i = 0; i < size; i++) {
            if (this.activityStack.get(i) != null) {
                finishActivity(this.activityStack.get(i));
            }
        }
        this.activityStack.clear();
    }
}