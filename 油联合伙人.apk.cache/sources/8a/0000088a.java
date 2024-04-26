package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
class l implements Callable {
    private static Context b;
    private static a hostManager = a.a();
    private String a;

    /* renamed from: b  reason: collision with other field name */
    private int f7b = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(String str) {
        this.a = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setContext(Context context) {
        b = context;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // java.util.concurrent.Callable
    /* renamed from: b */
    public java.lang.String[] call() {
        /*
            Method dump skipped, instructions count: 399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.l.call():java.lang.String[]");
    }
}