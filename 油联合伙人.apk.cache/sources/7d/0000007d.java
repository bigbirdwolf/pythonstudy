package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        int i3;
        ChainHead[] chainHeadArr;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = i4;
            i2 = 0;
        } else {
            i2 = 2;
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            ChainHead chainHead = chainHeadArr[i5];
            chainHead.define();
            if (constraintWidgetContainer.optimizeFor(4)) {
                if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i2, chainHead)) {
                    applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
                }
            } else {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
        if (r2.mHorizontalChainStyle == 2) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0039, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0053, code lost:
        if (r2.mVerticalChainStyle == 2) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:200:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x04b9  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x050a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:280:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0521  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x054c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:320:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x019e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r48, android.support.constraint.solver.LinearSystem r49, int r50, int r51, android.support.constraint.solver.widgets.ChainHead r52) {
        /*
            Method dump skipped, instructions count: 1392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}