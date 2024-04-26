package com.facebook.stetho.inspector.elements.android;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.ViewDebug;
import com.facebook.stetho.common.ExceptionUtil;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.StringUtil;
import com.facebook.stetho.common.android.ResourcesUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.helper.IntegerFormatter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes.dex */
final class ViewDescriptor extends AbstractChainedDescriptor<View> implements HighlightableDescriptor {
    private static final String ID_NAME = "id";
    private static final String NONE_MAPPING = "<no mapping>";
    private static final String NONE_VALUE = "(none)";
    private final MethodInvoker mMethodInvoker;
    @GuardedBy("this")
    @Nullable
    private volatile List<ViewCSSProperty> mViewProperties;
    @Nullable
    private Pattern mWordBoundaryPattern;

    private Pattern getWordBoundaryPattern() {
        if (this.mWordBoundaryPattern == null) {
            this.mWordBoundaryPattern = Pattern.compile("(?<=\\p{Lower})(?=\\p{Upper})");
        }
        return this.mWordBoundaryPattern;
    }

    private List<ViewCSSProperty> getViewProperties() {
        Method[] declaredMethods;
        Field[] declaredFields;
        if (this.mViewProperties == null) {
            synchronized (this) {
                if (this.mViewProperties == null) {
                    ArrayList arrayList = new ArrayList();
                    for (Method method : View.class.getDeclaredMethods()) {
                        ViewDebug.ExportedProperty exportedProperty = (ViewDebug.ExportedProperty) method.getAnnotation(ViewDebug.ExportedProperty.class);
                        if (exportedProperty != null) {
                            arrayList.add(new MethodBackedCSSProperty(method, convertViewPropertyNameToCSSName(method.getName()), exportedProperty));
                        }
                    }
                    for (Field field : View.class.getDeclaredFields()) {
                        ViewDebug.ExportedProperty exportedProperty2 = (ViewDebug.ExportedProperty) field.getAnnotation(ViewDebug.ExportedProperty.class);
                        if (exportedProperty2 != null) {
                            arrayList.add(new FieldBackedCSSProperty(field, convertViewPropertyNameToCSSName(field.getName()), exportedProperty2));
                        }
                    }
                    Collections.sort(arrayList, new Comparator<ViewCSSProperty>() { // from class: com.facebook.stetho.inspector.elements.android.ViewDescriptor.1
                        @Override // java.util.Comparator
                        public int compare(ViewCSSProperty viewCSSProperty, ViewCSSProperty viewCSSProperty2) {
                            return viewCSSProperty.getCSSName().compareTo(viewCSSProperty2.getCSSName());
                        }
                    });
                    this.mViewProperties = Collections.unmodifiableList(arrayList);
                }
            }
        }
        return this.mViewProperties;
    }

    public ViewDescriptor() {
        this(new MethodInvoker());
    }

    public ViewDescriptor(MethodInvoker methodInvoker) {
        this.mMethodInvoker = methodInvoker;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public String onGetNodeName(View view) {
        String name = view.getClass().getName();
        return StringUtil.removePrefix(name, "android.view.", StringUtil.removePrefix(name, "android.widget."));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public void onGetAttributes(View view, AttributeAccumulator attributeAccumulator) {
        String idAttribute = getIdAttribute(view);
        if (idAttribute != null) {
            attributeAccumulator.store("id", idAttribute);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public void onSetAttributesAsText(View view, String str) {
        for (Map.Entry<String, String> entry : parseSetAttributesAsTextArg(str).entrySet()) {
            this.mMethodInvoker.invoke(view, "set" + capitalize(entry.getKey()), entry.getValue());
        }
    }

    @Nullable
    private static String getIdAttribute(View view) {
        int id = view.getId();
        if (id == -1) {
            return null;
        }
        return ResourcesUtil.getIdStringQuietly(view, view.getResources(), id);
    }

    @Override // com.facebook.stetho.inspector.elements.android.HighlightableDescriptor
    public View getViewForHighlighting(Object obj) {
        return (View) obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public void onGetStyles(View view, StyleAccumulator styleAccumulator) {
        List<ViewCSSProperty> viewProperties = getViewProperties();
        int size = viewProperties.size();
        for (int i = 0; i < size; i++) {
            ViewCSSProperty viewCSSProperty = viewProperties.get(i);
            try {
                getStyleFromValue(view, viewCSSProperty.getCSSName(), viewCSSProperty.getValue(view), viewCSSProperty.getAnnotation(), styleAccumulator);
            } catch (Exception e) {
                if ((e instanceof IllegalAccessException) || (e instanceof InvocationTargetException)) {
                    LogUtil.e(e, "failed to get style property " + viewCSSProperty.getCSSName() + " of element= " + view.toString());
                } else {
                    throw ExceptionUtil.propagate(e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public void onGetAccessibilityStyles(View view, StyleAccumulator styleAccumulator) {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        ViewCompat.onInitializeAccessibilityNodeInfo(view, obtain);
        boolean ignored = AccessibilityNodeInfoWrapper.getIgnored(obtain, view);
        getStyleFromValue(view, "ignored", Boolean.valueOf(ignored), null, styleAccumulator);
        if (ignored) {
            getStyleFromValue(view, "ignored-reasons", AccessibilityNodeInfoWrapper.getIgnoredReasons(obtain, view), null, styleAccumulator);
        }
        getStyleFromValue(view, "focusable", Boolean.valueOf(!ignored), null, styleAccumulator);
        if (!ignored) {
            getStyleFromValue(view, "focusable-reasons", AccessibilityNodeInfoWrapper.getFocusableReasons(obtain, view), null, styleAccumulator);
            getStyleFromValue(view, "focused", Boolean.valueOf(obtain.isAccessibilityFocused()), null, styleAccumulator);
            getStyleFromValue(view, "description", AccessibilityNodeInfoWrapper.getDescription(obtain, view), null, styleAccumulator);
            getStyleFromValue(view, "actions", AccessibilityNodeInfoWrapper.getActions(obtain), null, styleAccumulator);
        }
        obtain.recycle();
    }

    private static boolean canIntBeMappedToString(@Nullable ViewDebug.ExportedProperty exportedProperty) {
        return (exportedProperty == null || exportedProperty.mapping() == null || exportedProperty.mapping().length <= 0) ? false : true;
    }

    private static String mapIntToStringUsingAnnotation(int i, @Nullable ViewDebug.ExportedProperty exportedProperty) {
        ViewDebug.IntToString[] mapping;
        if (!canIntBeMappedToString(exportedProperty)) {
            throw new IllegalStateException("Cannot map using this annotation");
        }
        for (ViewDebug.IntToString intToString : exportedProperty.mapping()) {
            if (intToString.from() == i) {
                return intToString.to();
            }
        }
        return NONE_MAPPING;
    }

    private static boolean canFlagsBeMappedToString(@Nullable ViewDebug.ExportedProperty exportedProperty) {
        return (exportedProperty == null || exportedProperty.flagMapping() == null || exportedProperty.flagMapping().length <= 0) ? false : true;
    }

    private static String mapFlagsToStringUsingAnnotation(int i, @Nullable ViewDebug.ExportedProperty exportedProperty) {
        ViewDebug.FlagToString[] flagMapping;
        if (!canFlagsBeMappedToString(exportedProperty)) {
            throw new IllegalStateException("Cannot map using this annotation");
        }
        StringBuilder sb = null;
        boolean z = false;
        for (ViewDebug.FlagToString flagToString : exportedProperty.flagMapping()) {
            if (flagToString.outputIf() == ((flagToString.mask() & i) == flagToString.equals())) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                if (z) {
                    sb.append(" | ");
                }
                sb.append(flagToString.name());
                z = true;
            }
        }
        return z ? sb.toString() : NONE_MAPPING;
    }

    private String convertViewPropertyNameToCSSName(String str) {
        String[] split = getWordBoundaryPattern().split(str);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals("get") && !split[i].equals("m")) {
                sb.append(split[i].toLowerCase());
                if (i < split.length - 1) {
                    sb.append('-');
                }
            }
        }
        return sb.toString();
    }

    private void getStyleFromValue(View view, String str, Object obj, @Nullable ViewDebug.ExportedProperty exportedProperty, StyleAccumulator styleAccumulator) {
        if (str.equals("id")) {
            getIdStyle(view, styleAccumulator);
        } else if (obj instanceof Integer) {
            getStyleFromInteger(str, (Integer) obj, exportedProperty, styleAccumulator);
        } else {
            if (obj instanceof Float) {
                styleAccumulator.store(str, String.valueOf(obj), ((Float) obj).floatValue() == 0.0f);
            } else if (obj instanceof Boolean) {
                styleAccumulator.store(str, String.valueOf(obj), false);
            } else if (obj instanceof Short) {
                styleAccumulator.store(str, String.valueOf(obj), ((Short) obj).shortValue() == 0);
            } else if (obj instanceof Long) {
                styleAccumulator.store(str, String.valueOf(obj), ((Long) obj).longValue() == 0);
            } else if (obj instanceof Double) {
                styleAccumulator.store(str, String.valueOf(obj), ((Double) obj).doubleValue() == 0.0d);
            } else if (obj instanceof Byte) {
                styleAccumulator.store(str, String.valueOf(obj), ((Byte) obj).byteValue() == 0);
            } else if (obj instanceof Character) {
                styleAccumulator.store(str, String.valueOf(obj), ((Character) obj).charValue() == 0);
            } else if (obj instanceof CharSequence) {
                styleAccumulator.store(str, String.valueOf(obj), ((CharSequence) obj).length() == 0);
            } else {
                getStylesFromObject(view, str, obj, exportedProperty, styleAccumulator);
            }
        }
    }

    private void getIdStyle(View view, StyleAccumulator styleAccumulator) {
        String idAttribute = getIdAttribute(view);
        if (idAttribute == null) {
            styleAccumulator.store("id", NONE_VALUE, false);
        } else {
            styleAccumulator.store("id", idAttribute, false);
        }
    }

    private void getStyleFromInteger(String str, Integer num, @Nullable ViewDebug.ExportedProperty exportedProperty, StyleAccumulator styleAccumulator) {
        String format = IntegerFormatter.getInstance().format(num, exportedProperty);
        if (canIntBeMappedToString(exportedProperty)) {
            styleAccumulator.store(str, format + " (" + mapIntToStringUsingAnnotation(num.intValue(), exportedProperty) + ")", false);
        } else if (canFlagsBeMappedToString(exportedProperty)) {
            styleAccumulator.store(str, format + " (" + mapFlagsToStringUsingAnnotation(num.intValue(), exportedProperty) + ")", false);
        } else {
            Boolean bool = true;
            styleAccumulator.store(str, format, ((num.intValue() != 0 || canFlagsBeMappedToString(exportedProperty) || canIntBeMappedToString(exportedProperty)) ? false : false).booleanValue());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x006a, code lost:
        if (r8.equals("topMargin") != false) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getStylesFromObject(android.view.View r15, java.lang.String r16, java.lang.Object r17, @javax.annotation.Nullable android.view.ViewDebug.ExportedProperty r18, com.facebook.stetho.inspector.elements.StyleAccumulator r19) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.inspector.elements.android.ViewDescriptor.getStylesFromObject(android.view.View, java.lang.String, java.lang.Object, android.view.ViewDebug$ExportedProperty, com.facebook.stetho.inspector.elements.StyleAccumulator):void");
    }

    private static String capitalize(String str) {
        if (str == null || str.length() == 0 || Character.isTitleCase(str.charAt(0))) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toTitleCase(sb.charAt(0)));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FieldBackedCSSProperty extends ViewCSSProperty {
        private final Field mField;

        public FieldBackedCSSProperty(Field field, String str, @Nullable ViewDebug.ExportedProperty exportedProperty) {
            super(str, exportedProperty);
            this.mField = field;
            this.mField.setAccessible(true);
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewDescriptor.ViewCSSProperty
        public Object getValue(View view) throws InvocationTargetException, IllegalAccessException {
            return this.mField.get(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MethodBackedCSSProperty extends ViewCSSProperty {
        private final Method mMethod;

        public MethodBackedCSSProperty(Method method, String str, @Nullable ViewDebug.ExportedProperty exportedProperty) {
            super(str, exportedProperty);
            this.mMethod = method;
            this.mMethod.setAccessible(true);
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewDescriptor.ViewCSSProperty
        public Object getValue(View view) throws InvocationTargetException, IllegalAccessException {
            return this.mMethod.invoke(view, new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class ViewCSSProperty {
        private final ViewDebug.ExportedProperty mAnnotation;
        private final String mCSSName;

        public abstract Object getValue(View view) throws InvocationTargetException, IllegalAccessException;

        public ViewCSSProperty(String str, @Nullable ViewDebug.ExportedProperty exportedProperty) {
            this.mCSSName = str;
            this.mAnnotation = exportedProperty;
        }

        public final String getCSSName() {
            return this.mCSSName;
        }

        @Nullable
        public final ViewDebug.ExportedProperty getAnnotation() {
            return this.mAnnotation;
        }
    }
}