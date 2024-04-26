package com.facebook.stetho.inspector.elements.android;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class MethodInvoker {
    private static final List<TypedMethodInvoker<?>> invokers = Arrays.asList(new StringMethodInvoker(), new CharSequenceMethodInvoker(), new IntegerMethodInvoker(), new FloatMethodInvoker(), new BooleanMethodInvoker());

    public void invoke(Object obj, String str, String str2) {
        Util.throwIfNull(obj, str, str2);
        int size = invokers.size();
        for (int i = 0; i < size; i++) {
            if (invokers.get(i).invoke(obj, str, str2)) {
                return;
            }
        }
        LogUtil.w("Method with name " + str + " not found for any of the MethodInvoker supported argument types.");
    }

    /* loaded from: classes.dex */
    private static abstract class TypedMethodInvoker<T> {
        private final Class<T> mArgType;

        abstract T convertArgument(String str);

        TypedMethodInvoker(Class<T> cls) {
            this.mArgType = cls;
        }

        boolean invoke(Object obj, String str, String str2) {
            try {
                obj.getClass().getMethod(str, this.mArgType).invoke(obj, convertArgument(str2));
                return true;
            } catch (IllegalAccessException e) {
                LogUtil.w("IllegalAccessException: " + e.getMessage());
                return false;
            } catch (IllegalArgumentException e2) {
                LogUtil.w("IllegalArgumentException: " + e2.getMessage());
                return false;
            } catch (NoSuchMethodException unused) {
                return false;
            } catch (InvocationTargetException e3) {
                LogUtil.w("InvocationTargetException: " + e3.getMessage());
                return false;
            }
        }
    }

    /* loaded from: classes.dex */
    private static class StringMethodInvoker extends TypedMethodInvoker<String> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public String convertArgument(String str) {
            return str;
        }

        StringMethodInvoker() {
            super(String.class);
        }
    }

    /* loaded from: classes.dex */
    private static class CharSequenceMethodInvoker extends TypedMethodInvoker<CharSequence> {
        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public CharSequence convertArgument(String str) {
            return str;
        }

        CharSequenceMethodInvoker() {
            super(CharSequence.class);
        }
    }

    /* loaded from: classes.dex */
    private static class IntegerMethodInvoker extends TypedMethodInvoker<Integer> {
        IntegerMethodInvoker() {
            super(Integer.TYPE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public Integer convertArgument(String str) {
            return Integer.valueOf(Integer.parseInt(str));
        }
    }

    /* loaded from: classes.dex */
    private static class FloatMethodInvoker extends TypedMethodInvoker<Float> {
        FloatMethodInvoker() {
            super(Float.TYPE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public Float convertArgument(String str) {
            return Float.valueOf(Float.parseFloat(str));
        }
    }

    /* loaded from: classes.dex */
    private static class BooleanMethodInvoker extends TypedMethodInvoker<Boolean> {
        BooleanMethodInvoker() {
            super(Boolean.TYPE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public Boolean convertArgument(String str) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
    }
}