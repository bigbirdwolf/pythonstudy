package org.greenrobot.eventbus;

/* loaded from: classes.dex */
interface Poster {
    void enqueue(Subscription subscription, Object obj);
}