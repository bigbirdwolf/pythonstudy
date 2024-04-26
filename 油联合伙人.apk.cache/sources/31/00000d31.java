package com.facebook.stetho.inspector.elements.android;

import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import com.facebook.stetho.common.android.AccessibilityUtil;

/* loaded from: classes.dex */
public final class AccessibilityNodeInfoWrapper {
    public static boolean getIgnored(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2 || importantForAccessibility == 4) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return true;
            }
        }
        if (accessibilityNodeInfoCompat.isVisibleToUser()) {
            return AccessibilityUtil.isAccessibilityFocusable(accessibilityNodeInfoCompat, view) ? accessibilityNodeInfoCompat.getChildCount() > 0 && !AccessibilityUtil.isSpeakingNode(accessibilityNodeInfoCompat, view) : AccessibilityUtil.hasFocusableAncestor(accessibilityNodeInfoCompat, view) || !AccessibilityUtil.hasText(accessibilityNodeInfoCompat);
        }
        return true;
    }

    public static String getIgnoredReasons(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2) {
            return "View has importantForAccessibility set to 'NO'.";
        }
        if (importantForAccessibility == 4) {
            return "View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return "An ancestor View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
            }
        }
        return !accessibilityNodeInfoCompat.isVisibleToUser() ? "View is not visible." : AccessibilityUtil.isAccessibilityFocusable(accessibilityNodeInfoCompat, view) ? "View is actionable, but has no description." : AccessibilityUtil.hasText(accessibilityNodeInfoCompat) ? "View is not actionable, and an ancestor View has co-opted its description." : "View is not actionable and has no description.";
    }

    @Nullable
    public static String getFocusableReasons(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        boolean hasText = AccessibilityUtil.hasText(accessibilityNodeInfoCompat);
        boolean isCheckable = accessibilityNodeInfoCompat.isCheckable();
        boolean hasNonActionableSpeakingDescendants = AccessibilityUtil.hasNonActionableSpeakingDescendants(accessibilityNodeInfoCompat, view);
        if (AccessibilityUtil.isActionableForAccessibility(accessibilityNodeInfoCompat)) {
            if (accessibilityNodeInfoCompat.getChildCount() <= 0) {
                return "View is actionable and has no children.";
            }
            if (hasText) {
                return "View is actionable and has a description.";
            }
            if (isCheckable) {
                return "View is actionable and checkable.";
            }
            if (hasNonActionableSpeakingDescendants) {
                return "View is actionable and has non-actionable descendants with descriptions.";
            }
        }
        if (AccessibilityUtil.isTopLevelScrollItem(accessibilityNodeInfoCompat, view)) {
            if (hasText) {
                return "View is a direct child of a scrollable container and has a description.";
            }
            if (isCheckable) {
                return "View is a direct child of a scrollable container and is checkable.";
            }
            if (hasNonActionableSpeakingDescendants) {
                return "View is a direct child of a scrollable container and has non-actionable descendants with descriptions.";
            }
        }
        if (hasText) {
            return "View has a description and is not actionable, but has no actionable ancestor.";
        }
        return null;
    }

    @Nullable
    public static String getActions(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        StringBuilder sb = new StringBuilder();
        for (AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat : accessibilityNodeInfoCompat.getActionList()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            switch (accessibilityActionCompat.getId()) {
                case 1:
                    sb.append("focus");
                    break;
                case 2:
                    sb.append("clear-focus");
                    break;
                case 4:
                    sb.append("select");
                    break;
                case 8:
                    sb.append("clear-selection");
                    break;
                case 16:
                    sb.append("click");
                    break;
                case 32:
                    sb.append("long-click");
                    break;
                case 64:
                    sb.append("accessibility-focus");
                    break;
                case 128:
                    sb.append("clear-accessibility-focus");
                    break;
                case 256:
                    sb.append("next-at-movement-granularity");
                    break;
                case 512:
                    sb.append("previous-at-movement-granularity");
                    break;
                case 1024:
                    sb.append("next-html-element");
                    break;
                case 2048:
                    sb.append("previous-html-element");
                    break;
                case 4096:
                    sb.append("scroll-forward");
                    break;
                case 8192:
                    sb.append("scroll-backward");
                    break;
                case 16384:
                    sb.append("copy");
                    break;
                case 32768:
                    sb.append("paste");
                    break;
                case 65536:
                    sb.append("cut");
                    break;
                case 131072:
                    sb.append("set-selection");
                    break;
                default:
                    CharSequence label = accessibilityActionCompat.getLabel();
                    if (label != null) {
                        sb.append(label);
                        break;
                    } else {
                        sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                        break;
                    }
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }

    @Nullable
    public static CharSequence getDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        CharSequence contentDescription = accessibilityNodeInfoCompat.getContentDescription();
        CharSequence text = accessibilityNodeInfoCompat.getText();
        boolean z = !TextUtils.isEmpty(text);
        boolean z2 = view instanceof EditText;
        if (TextUtils.isEmpty(contentDescription) || (z2 && z)) {
            if (z) {
                return text;
            }
            if (view instanceof ViewGroup) {
                StringBuilder sb = new StringBuilder();
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
                    ViewCompat.onInitializeAccessibilityNodeInfo(childAt, obtain);
                    CharSequence description = (!AccessibilityUtil.isSpeakingNode(obtain, childAt) || AccessibilityUtil.isAccessibilityFocusable(obtain, childAt)) ? null : getDescription(obtain, childAt);
                    if (!TextUtils.isEmpty(description)) {
                        if (sb.length() > 0) {
                            sb.append(", ");
                        }
                        sb.append(description);
                    }
                    obtain.recycle();
                }
                if (sb.length() > 0) {
                    return sb.toString();
                }
                return null;
            }
            return null;
        }
        return contentDescription;
    }
}