package me.zhanghai.android.materialprogressbar;

import android.graphics.Path;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/* loaded from: classes.dex */
class Interpolators {

    /* loaded from: classes.dex */
    public static class LINEAR {
        public static final Interpolator INSTANCE = new LinearInterpolator();
    }

    /* loaded from: classes.dex */
    public static class INDETERMINATE_HORIZONTAL_RECT1_TRANSLATE_X {
        public static final Interpolator INSTANCE;
        private static final Path PATH_INDETERMINATE_HORIZONTAL_RECT1_TRANSLATE_X = new Path();

        static {
            PATH_INDETERMINATE_HORIZONTAL_RECT1_TRANSLATE_X.moveTo(0.0f, 0.0f);
            PATH_INDETERMINATE_HORIZONTAL_RECT1_TRANSLATE_X.lineTo(0.2f, 0.0f);
            PATH_INDETERMINATE_HORIZONTAL_RECT1_TRANSLATE_X.cubicTo(0.39583334f, 0.0f, 0.47484508f, 0.20679761f, 0.59166664f, 0.41708294f);
            PATH_INDETERMINATE_HORIZONTAL_RECT1_TRANSLATE_X.cubicTo(0.715161f, 0.6393796f, 0.81625f, 0.9745569f, 1.0f, 1.0f);
            INSTANCE = PathInterpolatorCompat.create(PATH_INDETERMINATE_HORIZONTAL_RECT1_TRANSLATE_X);
        }
    }

    /* loaded from: classes.dex */
    public static class INDETERMINATE_HORIZONTAL_RECT1_SCALE_X {
        public static final Interpolator INSTANCE;
        private static final Path PATH_INDETERMINATE_HORIZONTAL_RECT1_SCALE_X = new Path();

        static {
            PATH_INDETERMINATE_HORIZONTAL_RECT1_SCALE_X.moveTo(0.0f, 0.0f);
            PATH_INDETERMINATE_HORIZONTAL_RECT1_SCALE_X.lineTo(0.3665f, 0.0f);
            PATH_INDETERMINATE_HORIZONTAL_RECT1_SCALE_X.cubicTo(0.4725262f, 0.06240991f, 0.6154161f, 0.5f, 0.68325f, 0.5f);
            PATH_INDETERMINATE_HORIZONTAL_RECT1_SCALE_X.cubicTo(0.7547506f, 0.5f, 0.7572583f, 0.8145101f, 1.0f, 1.0f);
            INSTANCE = PathInterpolatorCompat.create(PATH_INDETERMINATE_HORIZONTAL_RECT1_SCALE_X);
        }
    }

    /* loaded from: classes.dex */
    public static class INDETERMINATE_HORIZONTAL_RECT2_TRANSLATE_X {
        public static final Interpolator INSTANCE;
        private static final Path PATH_INDETERMINATE_HORIZONTAL_RECT2_TRANSLATE_X = new Path();

        static {
            PATH_INDETERMINATE_HORIZONTAL_RECT2_TRANSLATE_X.moveTo(0.0f, 0.0f);
            PATH_INDETERMINATE_HORIZONTAL_RECT2_TRANSLATE_X.cubicTo(0.0375f, 0.0f, 0.12876461f, 0.0895381f, 0.25f, 0.21855351f);
            PATH_INDETERMINATE_HORIZONTAL_RECT2_TRANSLATE_X.cubicTo(0.32241032f, 0.2956106f, 0.43666667f, 0.41759142f, 0.48333332f, 0.48982617f);
            PATH_INDETERMINATE_HORIZONTAL_RECT2_TRANSLATE_X.cubicTo(0.69f, 0.80972296f, 0.79333335f, 0.95001614f, 1.0f, 1.0f);
            INSTANCE = PathInterpolatorCompat.create(PATH_INDETERMINATE_HORIZONTAL_RECT2_TRANSLATE_X);
        }
    }

    /* loaded from: classes.dex */
    public static class INDETERMINATE_HORIZONTAL_RECT2_SCALE_X {
        public static final Interpolator INSTANCE;
        private static final Path PATH_INDETERMINATE_HORIZONTAL_RECT2_SCALE_X = new Path();

        static {
            PATH_INDETERMINATE_HORIZONTAL_RECT2_SCALE_X.moveTo(0.0f, 0.0f);
            PATH_INDETERMINATE_HORIZONTAL_RECT2_SCALE_X.cubicTo(0.06834272f, 0.019925667f, 0.19220331f, 0.15855429f, 0.33333334f, 0.3492616f);
            PATH_INDETERMINATE_HORIZONTAL_RECT2_SCALE_X.cubicTo(0.38410434f, 0.41477913f, 0.5494579f, 0.6813603f, 0.6666667f, 0.68279964f);
            PATH_INDETERMINATE_HORIZONTAL_RECT2_SCALE_X.cubicTo(0.75258625f, 0.6817962f, 0.73725396f, 0.8788962f, 1.0f, 1.0f);
            INSTANCE = PathInterpolatorCompat.create(PATH_INDETERMINATE_HORIZONTAL_RECT2_SCALE_X);
        }
    }

    /* loaded from: classes.dex */
    public static class TRIM_PATH_START {
        public static final Interpolator INSTANCE;
        private static final Path PATH_TRIM_PATH_START = new Path();

        static {
            PATH_TRIM_PATH_START.lineTo(0.5f, 0.0f);
            PATH_TRIM_PATH_START.cubicTo(0.7f, 0.0f, 0.6f, 1.0f, 1.0f, 1.0f);
            INSTANCE = PathInterpolatorCompat.create(PATH_TRIM_PATH_START);
        }
    }

    /* loaded from: classes.dex */
    public static class TRIM_PATH_END {
        public static final Interpolator INSTANCE;
        private static final Path PATH_TRIM_PATH_END = new Path();

        static {
            PATH_TRIM_PATH_END.cubicTo(0.2f, 0.0f, 0.1f, 1.0f, 0.5f, 1.0f);
            PATH_TRIM_PATH_END.lineTo(1.0f, 1.0f);
            INSTANCE = PathInterpolatorCompat.create(PATH_TRIM_PATH_END);
        }
    }

    private Interpolators() {
    }
}