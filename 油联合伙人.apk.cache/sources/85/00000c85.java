package com.contrarywind.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.interfaces.IPickerViewData;
import com.contrarywind.listener.LoopViewGestureListener;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.timer.InertiaTimerTask;
import com.contrarywind.timer.MessageHandler;
import com.contrarywind.timer.SmoothScrollTimerTask;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class WheelView extends View {
    private static final float SCALE_CONTENT = 0.8f;
    private static final int VELOCITY_FLING = 5;
    private float CENTER_CONTENT_OFFSET;
    private final float DEFAULT_TEXT_TARGET_SKEWX;
    private WheelAdapter adapter;
    private float centerY;
    private int change;
    private Context context;
    private int dividerColor;
    private DividerType dividerType;
    private int drawCenterContentStart;
    private int drawOutContentStart;
    private float firstLineY;
    private GestureDetector gestureDetector;
    private Handler handler;
    private int initPosition;
    private boolean isCenterLabel;
    private boolean isLoop;
    private boolean isOptions;
    private float itemHeight;
    private int itemsVisible;
    private String label;
    private float lineSpacingMultiplier;
    private ScheduledExecutorService mExecutor;
    private ScheduledFuture<?> mFuture;
    private int mGravity;
    private int mOffset;
    private int maxTextHeight;
    private int maxTextWidth;
    private int measuredHeight;
    private int measuredWidth;
    private OnItemSelectedListener onItemSelectedListener;
    private Paint paintCenterText;
    private Paint paintIndicator;
    private Paint paintOuterText;
    private int preCurrentIndex;
    private float previousY;
    private int radius;
    private float secondLineY;
    private int selectedItem;
    private long startTime;
    private int textColorCenter;
    private int textColorOut;
    private int textSize;
    private int textXOffset;
    private float totalScrollY;
    private Typeface typeface;
    private int widthMeasureSpec;

    /* loaded from: classes.dex */
    public enum ACTION {
        CLICK,
        FLING,
        DAGGLE
    }

    /* loaded from: classes.dex */
    public enum DividerType {
        FILL,
        WRAP
    }

    public WheelView(Context context) {
        this(context, null);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isOptions = false;
        this.isCenterLabel = true;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.typeface = Typeface.MONOSPACE;
        this.lineSpacingMultiplier = 1.6f;
        this.itemsVisible = 11;
        this.mOffset = 0;
        this.previousY = 0.0f;
        this.startTime = 0L;
        this.mGravity = 17;
        this.drawCenterContentStart = 0;
        this.drawOutContentStart = 0;
        this.DEFAULT_TEXT_TARGET_SKEWX = 0.5f;
        this.textSize = getResources().getDimensionPixelSize(R.dimen.pickerview_textsize);
        float f = getResources().getDisplayMetrics().density;
        if (f < 1.0f) {
            this.CENTER_CONTENT_OFFSET = 2.4f;
        } else if (1.0f <= f && f < 2.0f) {
            this.CENTER_CONTENT_OFFSET = 3.6f;
        } else if (1.0f <= f && f < 2.0f) {
            this.CENTER_CONTENT_OFFSET = 4.5f;
        } else if (2.0f <= f && f < 3.0f) {
            this.CENTER_CONTENT_OFFSET = 6.0f;
        } else if (f >= 3.0f) {
            this.CENTER_CONTENT_OFFSET = f * 2.5f;
        }
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.pickerview, 0, 0);
            this.mGravity = obtainStyledAttributes.getInt(R.styleable.pickerview_wheelview_gravity, 17);
            this.textColorOut = obtainStyledAttributes.getColor(R.styleable.pickerview_wheelview_textColorOut, -5723992);
            this.textColorCenter = obtainStyledAttributes.getColor(R.styleable.pickerview_wheelview_textColorCenter, -14013910);
            this.dividerColor = obtainStyledAttributes.getColor(R.styleable.pickerview_wheelview_dividerColor, -2763307);
            this.textSize = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.pickerview_wheelview_textSize, this.textSize);
            this.lineSpacingMultiplier = obtainStyledAttributes.getFloat(R.styleable.pickerview_wheelview_lineSpacingMultiplier, this.lineSpacingMultiplier);
            obtainStyledAttributes.recycle();
        }
        judgeLineSpace();
        initLoopView(context);
    }

    private void judgeLineSpace() {
        if (this.lineSpacingMultiplier < 1.0f) {
            this.lineSpacingMultiplier = 1.0f;
        } else if (this.lineSpacingMultiplier > 4.0f) {
            this.lineSpacingMultiplier = 4.0f;
        }
    }

    private void initLoopView(Context context) {
        this.context = context;
        this.handler = new MessageHandler(this);
        this.gestureDetector = new GestureDetector(context, new LoopViewGestureListener(this));
        this.gestureDetector.setIsLongpressEnabled(false);
        this.isLoop = true;
        this.totalScrollY = 0.0f;
        this.initPosition = -1;
        initPaints();
    }

    private void initPaints() {
        this.paintOuterText = new Paint();
        this.paintOuterText.setColor(this.textColorOut);
        this.paintOuterText.setAntiAlias(true);
        this.paintOuterText.setTypeface(this.typeface);
        this.paintOuterText.setTextSize(this.textSize);
        this.paintCenterText = new Paint();
        this.paintCenterText.setColor(this.textColorCenter);
        this.paintCenterText.setAntiAlias(true);
        this.paintCenterText.setTextScaleX(1.1f);
        this.paintCenterText.setTypeface(this.typeface);
        this.paintCenterText.setTextSize(this.textSize);
        this.paintIndicator = new Paint();
        this.paintIndicator.setColor(this.dividerColor);
        this.paintIndicator.setAntiAlias(true);
        setLayerType(1, null);
    }

    private void remeasure() {
        if (this.adapter == null) {
            return;
        }
        measureTextWidthHeight();
        int i = (int) (this.itemHeight * (this.itemsVisible - 1));
        double d = i * 2;
        Double.isNaN(d);
        this.measuredHeight = (int) (d / 3.141592653589793d);
        double d2 = i;
        Double.isNaN(d2);
        this.radius = (int) (d2 / 3.141592653589793d);
        this.measuredWidth = View.MeasureSpec.getSize(this.widthMeasureSpec);
        this.firstLineY = (this.measuredHeight - this.itemHeight) / 2.0f;
        this.secondLineY = (this.measuredHeight + this.itemHeight) / 2.0f;
        this.centerY = (this.secondLineY - ((this.itemHeight - this.maxTextHeight) / 2.0f)) - this.CENTER_CONTENT_OFFSET;
        if (this.initPosition == -1) {
            if (this.isLoop) {
                this.initPosition = (this.adapter.getItemsCount() + 1) / 2;
            } else {
                this.initPosition = 0;
            }
        }
        this.preCurrentIndex = this.initPosition;
    }

    private void measureTextWidthHeight() {
        Rect rect = new Rect();
        for (int i = 0; i < this.adapter.getItemsCount(); i++) {
            String contentText = getContentText(this.adapter.getItem(i));
            this.paintCenterText.getTextBounds(contentText, 0, contentText.length(), rect);
            int width = rect.width();
            if (width > this.maxTextWidth) {
                this.maxTextWidth = width;
            }
            this.paintCenterText.getTextBounds("星期", 0, 2, rect);
            this.maxTextHeight = rect.height() + 2;
        }
        this.itemHeight = this.lineSpacingMultiplier * this.maxTextHeight;
    }

    public void smoothScroll(ACTION action) {
        cancelFuture();
        if (action == ACTION.FLING || action == ACTION.DAGGLE) {
            this.mOffset = (int) (((this.totalScrollY % this.itemHeight) + this.itemHeight) % this.itemHeight);
            if (this.mOffset > this.itemHeight / 2.0f) {
                this.mOffset = (int) (this.itemHeight - this.mOffset);
            } else {
                this.mOffset = -this.mOffset;
            }
        }
        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new SmoothScrollTimerTask(this, this.mOffset), 0L, 10L, TimeUnit.MILLISECONDS);
    }

    public final void scrollBy(float f) {
        cancelFuture();
        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new InertiaTimerTask(this, f), 0L, 5L, TimeUnit.MILLISECONDS);
    }

    public void cancelFuture() {
        if (this.mFuture == null || this.mFuture.isCancelled()) {
            return;
        }
        this.mFuture.cancel(true);
        this.mFuture = null;
    }

    public final void setCyclic(boolean z) {
        this.isLoop = z;
    }

    public final void setTypeface(Typeface typeface) {
        this.typeface = typeface;
        this.paintOuterText.setTypeface(this.typeface);
        this.paintCenterText.setTypeface(this.typeface);
    }

    public final void setTextSize(float f) {
        if (f > 0.0f) {
            this.textSize = (int) (this.context.getResources().getDisplayMetrics().density * f);
            this.paintOuterText.setTextSize(this.textSize);
            this.paintCenterText.setTextSize(this.textSize);
        }
    }

    public final void setCurrentItem(int i) {
        this.selectedItem = i;
        this.initPosition = i;
        this.totalScrollY = 0.0f;
        invalidate();
    }

    public final void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public final void setAdapter(WheelAdapter wheelAdapter) {
        this.adapter = wheelAdapter;
        remeasure();
        invalidate();
    }

    public final WheelAdapter getAdapter() {
        return this.adapter;
    }

    public final int getCurrentItem() {
        return this.selectedItem;
    }

    public final void onItemSelected() {
        if (this.onItemSelectedListener != null) {
            postDelayed(new Runnable() { // from class: com.contrarywind.view.WheelView.1
                @Override // java.lang.Runnable
                public void run() {
                    WheelView.this.onItemSelectedListener.onItemSelected(WheelView.this.getCurrentItem());
                }
            }, 200L);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.adapter == null) {
            return;
        }
        this.initPosition = Math.min(Math.max(0, this.initPosition), this.adapter.getItemsCount() - 1);
        Object[] objArr = new Object[this.itemsVisible];
        this.change = (int) (this.totalScrollY / this.itemHeight);
        try {
            this.preCurrentIndex = this.initPosition + (this.change % this.adapter.getItemsCount());
        } catch (ArithmeticException unused) {
            Log.e("WheelView", "出错了！adapter.getItemsCount() == 0，联动数据不匹配");
        }
        if (!this.isLoop) {
            if (this.preCurrentIndex < 0) {
                this.preCurrentIndex = 0;
            }
            if (this.preCurrentIndex > this.adapter.getItemsCount() - 1) {
                this.preCurrentIndex = this.adapter.getItemsCount() - 1;
            }
        } else {
            if (this.preCurrentIndex < 0) {
                this.preCurrentIndex = this.adapter.getItemsCount() + this.preCurrentIndex;
            }
            if (this.preCurrentIndex > this.adapter.getItemsCount() - 1) {
                this.preCurrentIndex -= this.adapter.getItemsCount();
            }
        }
        float f = this.totalScrollY % this.itemHeight;
        for (int i = 0; i < this.itemsVisible; i++) {
            int i2 = this.preCurrentIndex - ((this.itemsVisible / 2) - i);
            if (this.isLoop) {
                objArr[i] = this.adapter.getItem(getLoopMappingIndex(i2));
            } else if (i2 < 0) {
                objArr[i] = "";
            } else if (i2 > this.adapter.getItemsCount() - 1) {
                objArr[i] = "";
            } else {
                objArr[i] = this.adapter.getItem(i2);
            }
        }
        if (this.dividerType == DividerType.WRAP) {
            float f2 = TextUtils.isEmpty(this.label) ? ((this.measuredWidth - this.maxTextWidth) / 2) - 12 : ((this.measuredWidth - this.maxTextWidth) / 4) - 12;
            float f3 = f2 <= 0.0f ? 10.0f : f2;
            float f4 = this.measuredWidth - f3;
            float f5 = f3;
            canvas.drawLine(f5, this.firstLineY, f4, this.firstLineY, this.paintIndicator);
            canvas.drawLine(f5, this.secondLineY, f4, this.secondLineY, this.paintIndicator);
        } else {
            canvas.drawLine(0.0f, this.firstLineY, this.measuredWidth, this.firstLineY, this.paintIndicator);
            canvas.drawLine(0.0f, this.secondLineY, this.measuredWidth, this.secondLineY, this.paintIndicator);
        }
        if (!TextUtils.isEmpty(this.label) && this.isCenterLabel) {
            canvas.drawText(this.label, (this.measuredWidth - getTextWidth(this.paintCenterText, this.label)) - this.CENTER_CONTENT_OFFSET, this.centerY, this.paintCenterText);
        }
        for (int i3 = 0; i3 < this.itemsVisible; i3++) {
            canvas.save();
            double d = ((this.itemHeight * i3) - f) / this.radius;
            Double.isNaN(d);
            float f6 = (float) (90.0d - ((d / 3.141592653589793d) * 180.0d));
            if (f6 >= 90.0f || f6 <= -90.0f) {
                canvas.restore();
            } else {
                float pow = (float) Math.pow(Math.abs(f6) / 90.0f, 2.2d);
                String contentText = (!this.isCenterLabel && !TextUtils.isEmpty(this.label) && !TextUtils.isEmpty(getContentText(objArr[i3]))) ? getContentText(objArr[i3]) + this.label : getContentText(objArr[i3]);
                reMeasureTextSize(contentText);
                measuredCenterContentStart(contentText);
                measuredOutContentStart(contentText);
                double d2 = this.radius;
                double cos = Math.cos(d);
                double d3 = this.radius;
                Double.isNaN(d3);
                Double.isNaN(d2);
                double d4 = d2 - (cos * d3);
                double sin = Math.sin(d);
                double d5 = this.maxTextHeight;
                Double.isNaN(d5);
                float f7 = (float) (d4 - ((sin * d5) / 2.0d));
                canvas.translate(0.0f, f7);
                if (f7 <= this.firstLineY && this.maxTextHeight + f7 >= this.firstLineY) {
                    canvas.save();
                    canvas.clipRect(0.0f, 0.0f, this.measuredWidth, this.firstLineY - f7);
                    canvas.scale(1.0f, ((float) Math.sin(d)) * SCALE_CONTENT);
                    canvas.drawText(contentText, this.drawOutContentStart, this.maxTextHeight, this.paintOuterText);
                    canvas.restore();
                    canvas.save();
                    canvas.clipRect(0.0f, this.firstLineY - f7, this.measuredWidth, (int) this.itemHeight);
                    canvas.scale(1.0f, ((float) Math.sin(d)) * 1.0f);
                    canvas.drawText(contentText, this.drawCenterContentStart, this.maxTextHeight - this.CENTER_CONTENT_OFFSET, this.paintCenterText);
                    canvas.restore();
                } else if (f7 <= this.secondLineY && this.maxTextHeight + f7 >= this.secondLineY) {
                    canvas.save();
                    canvas.clipRect(0.0f, 0.0f, this.measuredWidth, this.secondLineY - f7);
                    canvas.scale(1.0f, ((float) Math.sin(d)) * 1.0f);
                    canvas.drawText(contentText, this.drawCenterContentStart, this.maxTextHeight - this.CENTER_CONTENT_OFFSET, this.paintCenterText);
                    canvas.restore();
                    canvas.save();
                    canvas.clipRect(0.0f, this.secondLineY - f7, this.measuredWidth, (int) this.itemHeight);
                    canvas.scale(1.0f, ((float) Math.sin(d)) * SCALE_CONTENT);
                    canvas.drawText(contentText, this.drawOutContentStart, this.maxTextHeight, this.paintOuterText);
                    canvas.restore();
                } else if (f7 >= this.firstLineY && this.maxTextHeight + f7 <= this.secondLineY) {
                    canvas.clipRect(0, 0, this.measuredWidth, this.maxTextHeight);
                    canvas.drawText(contentText, this.drawCenterContentStart, this.maxTextHeight - this.CENTER_CONTENT_OFFSET, this.paintCenterText);
                    this.selectedItem = this.preCurrentIndex - ((this.itemsVisible / 2) - i3);
                } else {
                    canvas.save();
                    canvas.clipRect(0, 0, this.measuredWidth, (int) this.itemHeight);
                    canvas.scale(1.0f, ((float) Math.sin(d)) * SCALE_CONTENT);
                    this.paintOuterText.setTextSkewX((this.textXOffset == 0 ? 0 : this.textXOffset > 0 ? 1 : -1) * (f6 <= 0.0f ? 1 : -1) * 0.5f * pow);
                    this.paintOuterText.setAlpha((int) ((1.0f - pow) * 255.0f));
                    canvas.drawText(contentText, this.drawOutContentStart + (this.textXOffset * pow), this.maxTextHeight, this.paintOuterText);
                    canvas.restore();
                    canvas.restore();
                    this.paintCenterText.setTextSize(this.textSize);
                }
                canvas.restore();
                this.paintCenterText.setTextSize(this.textSize);
            }
        }
    }

    private void reMeasureTextSize(String str) {
        Rect rect = new Rect();
        this.paintCenterText.getTextBounds(str, 0, str.length(), rect);
        int i = this.textSize;
        for (int width = rect.width(); width > this.measuredWidth; width = rect.width()) {
            i--;
            this.paintCenterText.setTextSize(i);
            this.paintCenterText.getTextBounds(str, 0, str.length(), rect);
        }
        this.paintOuterText.setTextSize(i);
    }

    private int getLoopMappingIndex(int i) {
        if (i < 0) {
            return getLoopMappingIndex(i + this.adapter.getItemsCount());
        }
        return i > this.adapter.getItemsCount() + (-1) ? getLoopMappingIndex(i - this.adapter.getItemsCount()) : i;
    }

    private String getContentText(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof IPickerViewData) {
            return ((IPickerViewData) obj).getPickerViewText();
        }
        return obj instanceof Integer ? String.format(Locale.getDefault(), "%02d", Integer.valueOf(((Integer) obj).intValue())) : obj.toString();
    }

    private void measuredCenterContentStart(String str) {
        Rect rect = new Rect();
        this.paintCenterText.getTextBounds(str, 0, str.length(), rect);
        int i = this.mGravity;
        if (i == 3) {
            this.drawCenterContentStart = 0;
        } else if (i == 5) {
            this.drawCenterContentStart = (this.measuredWidth - rect.width()) - ((int) this.CENTER_CONTENT_OFFSET);
        } else if (i != 17) {
        } else {
            if (this.isOptions || this.label == null || this.label.equals("") || !this.isCenterLabel) {
                double width = this.measuredWidth - rect.width();
                Double.isNaN(width);
                this.drawCenterContentStart = (int) (width * 0.5d);
                return;
            }
            double width2 = this.measuredWidth - rect.width();
            Double.isNaN(width2);
            this.drawCenterContentStart = (int) (width2 * 0.25d);
        }
    }

    private void measuredOutContentStart(String str) {
        Rect rect = new Rect();
        this.paintOuterText.getTextBounds(str, 0, str.length(), rect);
        int i = this.mGravity;
        if (i == 3) {
            this.drawOutContentStart = 0;
        } else if (i == 5) {
            this.drawOutContentStart = (this.measuredWidth - rect.width()) - ((int) this.CENTER_CONTENT_OFFSET);
        } else if (i != 17) {
        } else {
            if (this.isOptions || this.label == null || this.label.equals("") || !this.isCenterLabel) {
                double width = this.measuredWidth - rect.width();
                Double.isNaN(width);
                this.drawOutContentStart = (int) (width * 0.5d);
                return;
            }
            double width2 = this.measuredWidth - rect.width();
            Double.isNaN(width2);
            this.drawOutContentStart = (int) (width2 * 0.25d);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        this.widthMeasureSpec = i;
        remeasure();
        setMeasuredDimension(this.measuredWidth, this.measuredHeight);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        double d;
        boolean onTouchEvent = this.gestureDetector.onTouchEvent(motionEvent);
        float f = (-this.initPosition) * this.itemHeight;
        float itemsCount = ((this.adapter.getItemsCount() - 1) - this.initPosition) * this.itemHeight;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startTime = System.currentTimeMillis();
            cancelFuture();
            this.previousY = motionEvent.getRawY();
        } else {
            if (action == 2) {
                float rawY = this.previousY - motionEvent.getRawY();
                this.previousY = motionEvent.getRawY();
                this.totalScrollY += rawY;
                if (!this.isLoop && (this.totalScrollY - (this.itemHeight * 0.25f) < f || this.totalScrollY + (this.itemHeight * 0.25f) > itemsCount)) {
                    this.totalScrollY -= rawY;
                    z = true;
                }
            } else {
                if (this.totalScrollY - (this.itemHeight * 0.25f) >= f && this.totalScrollY + (this.itemHeight * 0.25f) <= itemsCount) {
                    if (!onTouchEvent) {
                        double acos = Math.acos((this.radius - motionEvent.getY()) / this.radius);
                        double d2 = this.radius;
                        Double.isNaN(d2);
                        double d3 = acos * d2;
                        double d4 = this.itemHeight / 2.0f;
                        Double.isNaN(d4);
                        double d5 = d3 + d4;
                        Double.isNaN(this.itemHeight);
                        this.mOffset = (int) (((((int) (d5 / d)) - (this.itemsVisible / 2)) * this.itemHeight) - (((this.totalScrollY % this.itemHeight) + this.itemHeight) % this.itemHeight));
                        if (System.currentTimeMillis() - this.startTime > 120) {
                            smoothScroll(ACTION.DAGGLE);
                        } else {
                            smoothScroll(ACTION.CLICK);
                        }
                    }
                }
                z = true;
            }
            if (!z && motionEvent.getAction() != 0) {
                invalidate();
            }
            return true;
        }
        z = false;
        if (!z) {
            invalidate();
        }
        return true;
    }

    public int getItemsCount() {
        if (this.adapter != null) {
            return this.adapter.getItemsCount();
        }
        return 0;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void isCenterLabel(boolean z) {
        this.isCenterLabel = z;
    }

    public void setGravity(int i) {
        this.mGravity = i;
    }

    public int getTextWidth(Paint paint, String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += (int) Math.ceil(fArr[i2]);
        }
        return i;
    }

    public void setIsOptions(boolean z) {
        this.isOptions = z;
    }

    public void setTextColorOut(int i) {
        if (i != 0) {
            this.textColorOut = i;
            this.paintOuterText.setColor(this.textColorOut);
        }
    }

    public void setTextColorCenter(int i) {
        if (i != 0) {
            this.textColorCenter = i;
            this.paintCenterText.setColor(this.textColorCenter);
        }
    }

    public void setTextXOffset(int i) {
        this.textXOffset = i;
        if (i != 0) {
            this.paintCenterText.setTextScaleX(1.0f);
        }
    }

    public void setDividerColor(int i) {
        if (i != 0) {
            this.dividerColor = i;
            this.paintIndicator.setColor(this.dividerColor);
        }
    }

    public void setDividerType(DividerType dividerType) {
        this.dividerType = dividerType;
    }

    public void setLineSpacingMultiplier(float f) {
        if (f != 0.0f) {
            this.lineSpacingMultiplier = f;
            judgeLineSpace();
        }
    }

    public boolean isLoop() {
        return this.isLoop;
    }

    public float getTotalScrollY() {
        return this.totalScrollY;
    }

    public void setTotalScrollY(float f) {
        this.totalScrollY = f;
    }

    public float getItemHeight() {
        return this.itemHeight;
    }

    public void setItemHeight(float f) {
        this.itemHeight = f;
    }

    public int getInitPosition() {
        return this.initPosition;
    }

    public void setInitPosition(int i) {
        this.initPosition = i;
    }

    @Override // android.view.View
    public Handler getHandler() {
        return this.handler;
    }
}