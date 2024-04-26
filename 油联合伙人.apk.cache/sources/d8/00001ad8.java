package com.yltx.oil.partner.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.PwdInputMethodView;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class PayPwdView extends View {
    private int count;
    private InputCallBack inputCallBack;
    private PwdInputMethodView inputMethodView;
    private int mBorderColor;
    private Paint mBorderPaint;
    private int mDotColor;
    private Paint mDotPaint;
    private int mRoundRadius;
    private RectF mRoundRect;
    private ArrayList<String> result;
    private int size;

    /* loaded from: classes.dex */
    public interface InputCallBack {
        void onInputFinish(String str);
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        return true;
    }

    public PayPwdView(Context context) {
        super(context);
        init(null);
    }

    public PayPwdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public PayPwdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    void init(AttributeSet attributeSet) {
        float f = getResources().getDisplayMetrics().density;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.result = new ArrayList<>();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.PayPwdView);
            this.mBorderColor = obtainStyledAttributes.getColor(0, -3355444);
            this.mDotColor = obtainStyledAttributes.getColor(2, -16777216);
            this.count = obtainStyledAttributes.getInt(1, 6);
            obtainStyledAttributes.recycle();
        } else {
            this.mBorderColor = -3355444;
            this.mDotColor = -7829368;
            this.count = 6;
        }
        this.size = (int) (30.0f * f);
        this.mBorderPaint = new Paint(1);
        this.mBorderPaint.setStrokeWidth(3.0f);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setColor(this.mBorderColor);
        this.mDotPaint = new Paint(1);
        this.mDotPaint.setStrokeWidth(3.0f);
        this.mDotPaint.setStyle(Paint.Style.FILL);
        this.mDotPaint.setColor(this.mDotColor);
        this.mRoundRect = new RectF();
        this.mRoundRadius = (int) (f * 5.0f);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measureWidth = measureWidth(i);
        int measureHeight = measureHeight(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (measureWidth == -1) {
            if (measureHeight != -1) {
                measureWidth = this.count * measureHeight;
                this.size = measureHeight;
            } else {
                measureWidth = this.size * this.count;
                measureHeight = this.size;
            }
        } else if (measureHeight == -1) {
            measureHeight = measureWidth / this.count;
            this.size = measureHeight;
        }
        setMeasuredDimension(Math.min(measureWidth, size), Math.min(measureHeight, size2));
    }

    private int measureWidth(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            return -1;
        }
        return size;
    }

    private int measureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            return -1;
        }
        return size;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            requestFocus();
            this.inputMethodView.setVisibility(0);
            return true;
        }
        return true;
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z) {
            this.inputMethodView.setVisibility(0);
        } else {
            this.inputMethodView.setVisibility(8);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float height = getHeight() - 2;
        this.mRoundRect.set(0.0f, 0.0f, getWidth() - 2, height);
        canvas.drawRoundRect(this.mRoundRect, 0.0f, 0.0f, this.mBorderPaint);
        for (int i = 1; i < this.count; i++) {
            float f = this.size * i;
            canvas.drawLine(f, 0.0f, f, height, this.mBorderPaint);
        }
        int i2 = this.size / 8;
        for (int i3 = 0; i3 < this.result.size(); i3++) {
            double d = this.size;
            double d2 = i3;
            Double.isNaN(d2);
            Double.isNaN(d);
            canvas.drawCircle((float) (d * (d2 + 0.5d)), this.size / 2, i2, this.mDotPaint);
        }
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        editorInfo.inputType = 2;
        editorInfo.imeOptions = 6;
        return new MyInputConnection(this, false);
    }

    public void setInputCallBack(InputCallBack inputCallBack) {
        this.inputCallBack = inputCallBack;
    }

    public void clearResult() {
        this.result.clear();
        invalidate();
    }

    /* loaded from: classes.dex */
    private class MyInputConnection extends BaseInputConnection {
        public MyInputConnection(View view, boolean z) {
            super(view, z);
        }

        @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
        public boolean commitText(CharSequence charSequence, int i) {
            return super.commitText(charSequence, i);
        }

        @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
        public boolean deleteSurroundingText(int i, int i2) {
            if (i == 1 && i2 == 0) {
                return super.sendKeyEvent(new KeyEvent(0, 67)) && super.sendKeyEvent(new KeyEvent(1, 67));
            }
            return super.deleteSurroundingText(i, i2);
        }
    }

    public void setInputMethodView(PwdInputMethodView pwdInputMethodView) {
        this.inputMethodView = pwdInputMethodView;
        this.inputMethodView.setInputReceiver(new PwdInputMethodView.InputReceiver() { // from class: com.yltx.oil.partner.widget.PayPwdView.1
            @Override // com.yltx.oil.partner.widget.PwdInputMethodView.InputReceiver
            public void receive(String str) {
                if (str.equals("-1")) {
                    if (PayPwdView.this.result.isEmpty()) {
                        return;
                    }
                    PayPwdView.this.result.remove(PayPwdView.this.result.size() - 1);
                    PayPwdView.this.invalidate();
                } else if (PayPwdView.this.result.size() < PayPwdView.this.count) {
                    PayPwdView.this.result.add(str);
                    PayPwdView.this.invalidate();
                    PayPwdView.this.ensureFinishInput();
                }
            }
        });
    }

    void ensureFinishInput() {
        if (this.result.size() != this.count || this.inputCallBack == null) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = this.result.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        this.inputCallBack.onInputFinish(stringBuffer.toString());
    }

    public String getInputText() {
        if (this.result.size() == this.count) {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator<String> it = this.result.iterator();
            while (it.hasNext()) {
                stringBuffer.append(it.next());
            }
            return stringBuffer.toString();
        }
        return null;
    }
}